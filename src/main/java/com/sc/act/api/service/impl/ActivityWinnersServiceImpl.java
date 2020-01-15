package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.mapper.auto.*;
import com.sc.act.api.mapper.ext.MerchantAccountExtMapper;
import com.sc.act.api.mapper.ext.TicketExtMapper;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.model.bo.ProductPriceInfoBmo;
import com.sc.act.api.model.bo.ProductShopXoBmo;
import com.sc.act.api.response.ActivityWinnersUserAccResponse;
import com.sc.act.api.service.ActivityWinnersProductService;
import com.sc.act.api.service.ActivityWinnersService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:活动中奖名服务实现类
 *
 * @className:ActivityWinnersServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ActivityWinnersServiceImpl implements ActivityWinnersService {


    @Value("${shopxo.url}")
    private String b2cUrl;

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccInfoMapper userAccInfoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ActivityWinsPdtMapper activityWinsPdtMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketExtMapper ticketExtMapper;

    @Autowired
    private ProductTicketMapper productTicketMapper;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Autowired
    private MerchantAccountExtMapper merchantAccountExtMapper;

    @Autowired
    private ActivityWinnersProductService activityWinnersProductService;

    @Override
    public void handlerWinnersInfo(List<ExcelWinnersInfoBmo> list, Integer activityId) {
        LOG.info("进入处理中奖名单服务请求参数list={} activityId={}", JSON.toJSONString(list), activityId);
        //

        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if (null == activity) {
            LOG.error("处理中奖名单为查询到活动list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_NULL);
        }

        ActivityWinsPdtExample activityWinsPdtExample = new ActivityWinsPdtExample();
        activityWinsPdtExample.createCriteria().andActivityIdEqualTo(activityId);
        List<ActivityWinsPdt> activityWinsPdts = activityWinsPdtMapper.selectByExample(activityWinsPdtExample);
        if (CollectionUtils.isNotEmpty(activityWinsPdts)) {
            LOG.error("处理中奖名单该活动已绑定产品请重新建立活动list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_PRODUCT_EXIST);
        }

        int sum = list.stream().mapToInt(ExcelWinnersInfoBmo::getAwardAmount).sum();
        Integer total = ticketExtMapper.selectNominalValueTotal();
        if (null == total) {
            LOG.error("处理中奖名单没有可以券list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_COUPON_NO);
        }

        if (sum > total) {
            LOG.error("处理中奖名单券数量较少无法匹配list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_COUPON_INSUFFICIENT);
        }

        MerchantAccountExample merchantAccountExample = new MerchantAccountExample();
        merchantAccountExample.createCriteria().andMerchantIdEqualTo(activity.getMerchantId());
        List<MerchantAccount> merchantAccounts = merchantAccountMapper.selectByExample(merchantAccountExample);
        if (CollectionUtils.isEmpty(merchantAccounts)) {
            LOG.error("处理中奖名单券商户账户不存在list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.MERCHANT_ACCOUNT_INFO_ERROR);
        }

        MerchantAccount merchantAccount = merchantAccounts.get(0);

        Integer balance = merchantAccount.getBalance();
        if (sum > balance) {
            LOG.error("处理中奖名单券商户资金不足list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.MERCHANT_ACCOUNT_MONEY_ERROR);
        }

        Date currentTime = new Date();
        int updateAccFlag = merchantAccountExtMapper.updateByBalanceAndMerchantIdSelective(
                currentTime,
                sum,
                balance,
                activity.getMerchantId());

        if (updateAccFlag == 0) {
            LOG.error("处理中奖名单券商户资金扣减异常list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.MERCHANT_ACC_MONEY_ERROR);
        }


        List<ProductPriceInfoBmo> productPriceInfoList = new ArrayList<>();
        //处理中奖名单
        activityWinnersProductService.createWinnersProductInfo(list, activity, currentTime, productPriceInfoList);

        if (CollectionUtils.isEmpty(productPriceInfoList)) {
            LOG.info("进入处理中奖名单调用B2C新建产品信息为空开始productIdList={}服务参数list={} activityId={}",
                    JSON.toJSONString(productPriceInfoList), JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_INFO_NULL_ERROR);
        }
        //调B2C

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set(CommonConstant.X_REQUESTED_WITH, CommonConstant.XMLHTTPREQUEST);
        ResponseEntity<Result<List<ProductShopXoBmo>>> responseEntity = restTemplate
                .exchange(
                        b2cUrl,
                        HttpMethod.POST,
                        new HttpEntity<>(JSON.toJSONString(productPriceInfoList), headers),
                        new ParameterizedTypeReference<Result<List<ProductShopXoBmo>>>() {
                        });

        Result<List<ProductShopXoBmo>> body = responseEntity.getBody();
        if (!ResultEnum.SUCCESS.getCode().equals(body.getRetCode())) {
            LOG.error("处理中奖名单调用B2C返回码={}错误list={} activityId={}", body.getRetCode(), JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_OUT_PRODUCTID_B2C_ERROR);
        }

        // 测试数据
//        List<ProductShopXoBmo> listResponse = new ArrayList<>();
//        productPriceInfoList.forEach(productPriceInfoBmo -> {
//            ProductShopXoBmo productShopXoBmo = new ProductShopXoBmo();
//            productShopXoBmo.setProductId(productPriceInfoBmo.getProductId());
//            productShopXoBmo.setProductName("测试" + productPriceInfoBmo.getProductId());
//            productShopXoBmo.setOutProductId((int) ((Math.random() * 9 + 1) * 100000));
//            listResponse.add(productShopXoBmo);
//        });
        List<ProductShopXoBmo> listResponse = body.getData();
        if (CollectionUtils.isEmpty(listResponse)) {
            LOG.error("处理中奖名单调用B2C返回实体错误list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_OUT_PRODUCTID_B2C_NULL_ERROR);
        }

        List<Integer> outProductIds = listResponse.stream().map(ProductShopXoBmo::getOutProductId).collect(Collectors.toList());
        ProductExample productSelectExample = new ProductExample();
        productSelectExample.createCriteria().andOutProductIdIn(outProductIds);
        List<Product> products = productMapper.selectByExample(productSelectExample);
        if (CollectionUtils.isNotEmpty(products)) {
            LOG.error("处理中奖名单调用B2C产品不能绑定多个外部产品IDlist={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_OUT_PRODUCTID_IS_ERROR);
        }

        //更新product
        listResponse.forEach(productShopXoBmo -> {
            Product updRecord = new Product();
            updRecord.setProductId(productShopXoBmo.getProductId());
            updRecord.setProductName(productShopXoBmo.getProductName());
            updRecord.setOutProductId(productShopXoBmo.getOutProductId());
            updRecord.setOutProductPlatform(CommonConstant.PRODUCT_PLATFORM_SHOPXO_0);
            productMapper.updateByPrimaryKeySelective(updRecord);
        });

        LOG.info("进入处理中奖名单调用B2C新建产品信息结束b2c返回{}productIdList={}服务参数list={} activityId={}",
                JSON.toJSONString(listResponse),
                JSON.toJSONString(productPriceInfoList),
                JSON.toJSONString(list),
                activityId);


    }


    @Override
    public List<ActivityWinnersUserAccResponse> selectActivityWinnersContent(Integer activityId) {
        LOG.info("进入查询活动服务请求参数activityId{}", activityId);
        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if (null == activity) {
            LOG.error("查询活动中奖名单activityId={}", activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_NULL);
        }

        List<ActivityWinnersUserAccResponse> list = new ArrayList<>();

        ActivityWinnersExample activityWinnersExample = new ActivityWinnersExample();
        ActivityWinnersExample.Criteria activityWinnersExampleCriteria = activityWinnersExample.createCriteria();
        activityWinnersExampleCriteria.andActivityIdEqualTo(activityId);
        List<ActivityWinners> activityWinners = activityWinnersMapper.selectByExample(activityWinnersExample);
        if (CollectionUtils.isNotEmpty(activityWinners)) {
            activityWinners.forEach(activityWinner -> {
                ActivityWinnersUserAccResponse activityWinnersUserAccResponse = new ActivityWinnersUserAccResponse();
                BeanUtils.copyProperties(activityWinner, activityWinnersUserAccResponse);
                User user = userMapper.selectByPrimaryKey(activityWinner.getUserId());
                if (null != user) {
                    activityWinnersUserAccResponse.setUser(user);
                }

                UserAccInfo userAccInfo = userAccInfoMapper.selectByPrimaryKey(activityWinner.getUserAccInfoId());
                if (null != userAccInfo) {
                    activityWinnersUserAccResponse.setUserAccInfo(userAccInfo);
                }
                ActivityWinsPdtExample activityWinsPdtExample = new ActivityWinsPdtExample();
                activityWinsPdtExample.createCriteria().andActivityWinnersIdEqualTo(activityWinner.getActivityWinnersId());
                List<ActivityWinsPdt> activityWinsPdts = activityWinsPdtMapper.selectByExample(activityWinsPdtExample);
                if (CollectionUtils.isNotEmpty(activityWinsPdts)) {
                    ActivityWinsPdt activityWinsPdt = activityWinsPdts.get(0);
                    ProductExample productExample = new ProductExample();
                    productExample.createCriteria()
                            .andOutProductIdNotEqualTo(0)
                            .andProductIdEqualTo(activityWinsPdt.getProductId());
                    Product product = productMapper.selectByExample(productExample).stream().findFirst().orElse(null);
                    if (null != product) {
                        product.setCreateTime(null);
                        product.setUpdateTime(null);
                        activityWinnersUserAccResponse.setProduct(product);
                        ProductTicketExample productTicketExample = new ProductTicketExample();
                        productTicketExample.createCriteria().andProductIdEqualTo(product.getProductId());

                        List<ProductTicket> productTickets = productTicketMapper.selectByExample(productTicketExample);
                        if (CollectionUtils.isNotEmpty(productTickets)) {
                            TicketExample ticketExample = new TicketExample();
                            ticketExample.createCriteria()
                                    .andTicketIdIn(
                                            productTickets
                                                    .stream()
                                                    .map(ProductTicket::getTicketId)
                                                    .collect(Collectors.toList()));
                            List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
                            if (CollectionUtils.isNotEmpty(tickets)) {

                                tickets.forEach(ticket -> {
                                    ticket.setCreateTime(null);
                                    ticket.setUpdateTime(null);
                                    ticket.setRemark3(null);
                                    ticket.setRemark2(null);
                                    ticket.setRemark1(null);
                                    ticket.setTicketPwd(null);
                                });
                                activityWinnersUserAccResponse.setTickets(tickets);
                            }
                        }
                    }
                }

                list.add(activityWinnersUserAccResponse);
            });
        }

        return list;
    }

}
