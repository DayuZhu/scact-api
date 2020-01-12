package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public void handlerWinnersInfo(List<ExcelWinnersInfoBmo> list, Integer activityId) {
        LOG.info("进入处理中奖名单服务请求参数list={} activityId={}", JSON.toJSONString(list), activityId);
        //

        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if (null == activity) {
            LOG.error("处理中奖名单为查询到活动list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_NULL);
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
        for (ExcelWinnersInfoBmo excelWinnersInfoBmo : list) {

            Integer awardAmount = excelWinnersInfoBmo.getAwardAmount();
            if (awardAmount == 0) {
                continue;
            }

            UserExample userExample = new UserExample();
            UserExample.Criteria userCriteria = userExample.createCriteria();
            userCriteria.andMobileEqualTo(excelWinnersInfoBmo.getMobile());
            List<User> users = userMapper.selectByExample(userExample);

            if (CollectionUtils.isNotEmpty(users)) {
                User user = users.get(0);
                User userRecord = new User();
                userRecord.setUserId(user.getUserId());
                userRecord.setName(excelWinnersInfoBmo.getName());
                userRecord.setUpdateTime(currentTime);
                userMapper.updateByPrimaryKeySelective(userRecord);
                UserAccInfo userAccInfo = insertOrUpdateUserAccInfo(excelWinnersInfoBmo, user, currentTime);
                //创建中奖人
                ProductPriceInfoBmo productPriceInfoBmo = insertActivityWinners(excelWinnersInfoBmo, userAccInfo, user, currentTime, activity);
                productPriceInfoList.add(productPriceInfoBmo);

            } else {
                User user = new User();
                user.setName(excelWinnersInfoBmo.getName());
                user.setMobile(excelWinnersInfoBmo.getMobile());
                user.setCreateTime(currentTime);
                user.setUpdateTime(currentTime);
                userMapper.insertSelective(user);
                UserAccInfo userAccInfo = insertOrUpdateUserAccInfo(excelWinnersInfoBmo, user, currentTime);
                //创建中奖人
                ProductPriceInfoBmo productPriceInfoBmo = insertActivityWinners(excelWinnersInfoBmo, userAccInfo, user, currentTime, activity);
                productPriceInfoList.add(productPriceInfoBmo);
            }


        }

        if (CollectionUtils.isEmpty(productPriceInfoList)) {
            LOG.info("进入处理中奖名单调用B2C新建产品信息为空开始productIdList={}服务参数list={} activityId={}",
                    JSON.toJSONString(productPriceInfoList), JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_INFO_NULL_ERROR);
        }
        //调B2C

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<Result<List<ProductShopXoBmo>>> responseEntity = restTemplate
                .exchange(
                        b2cUrl,
                        HttpMethod.POST,
                        new HttpEntity<>(JSON.toJSONString(productPriceInfoList), headers),
                        new ParameterizedTypeReference<Result<List<ProductShopXoBmo>>>() {
                        });

        Result<List<ProductShopXoBmo>> body = responseEntity.getBody();
        if (!ResultEnum.SUCCESS.getCode().equals(body.getRetCode())) {
            LOG.error("处理中奖名单调用B2C返回码错误list={} activityId={}", JSON.toJSONString(list), activityId);
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
        for (ProductShopXoBmo productShopXoBmo : listResponse) {
            Product updRecord = new Product();
            updRecord.setProductId(productShopXoBmo.getProductId());
            updRecord.setProductName(productShopXoBmo.getProductName());
            updRecord.setOutProductId(productShopXoBmo.getOutProductId());
            updRecord.setOutProductPlatform(CommonConstant.PRODUCT_PLATFORM_SHOPXO_0);
            productMapper.updateByPrimaryKeySelective(updRecord);
        }

        LOG.info("进入处理中奖名单调用B2C新建产品信息结束b2c返回{}productIdList={}服务参数list={} activityId={}",
                JSON.toJSONString(listResponse),
                JSON.toJSONString(productPriceInfoList),
                JSON.toJSONString(list),
                activityId);


    }


    private UserAccInfo insertOrUpdateUserAccInfo(ExcelWinnersInfoBmo excelWinnersInfoBmo, User user, Date currentTime) {

        UserAccInfoExample userAccInfoExample = new UserAccInfoExample();
        UserAccInfoExample.Criteria userAccInfoCriteria = userAccInfoExample.createCriteria();
        userAccInfoCriteria.andUserIdEqualTo(user.getUserId());
        userAccInfoCriteria.andCardNumberEqualTo(excelWinnersInfoBmo.getCardNumber());
        List<UserAccInfo> userAccInfos = userAccInfoMapper.selectByExample(userAccInfoExample);
        if (CollectionUtils.isNotEmpty(userAccInfos)) {
            UserAccInfo userAccInfo = userAccInfos.get(0);
            UserAccInfo userAccInfoUpdate = new UserAccInfo();
            userAccInfoUpdate.setUserAccInfoId(userAccInfo.getUserAccInfoId());
            userAccInfoUpdate.setCardName(excelWinnersInfoBmo.getCardName());
            userAccInfoUpdate.setBankName(excelWinnersInfoBmo.getBankName());
            userAccInfoUpdate.setUpdateTime(currentTime);
            userAccInfoMapper.updateByPrimaryKeySelective(userAccInfoUpdate);
            return userAccInfo;
        } else {
            UserAccInfo userAccInfoInsert = new UserAccInfo();
            userAccInfoInsert.setUserId(user.getUserId());
            userAccInfoInsert.setCardName(excelWinnersInfoBmo.getCardName());
            userAccInfoInsert.setBankName(excelWinnersInfoBmo.getBankName());
            userAccInfoInsert.setCardNumber(excelWinnersInfoBmo.getCardNumber());
            userAccInfoInsert.setCreateTime(currentTime);
            userAccInfoInsert.setUpdateTime(currentTime);
            userAccInfoMapper.insertSelective(userAccInfoInsert);
            return userAccInfoInsert;
        }
    }

    private ProductPriceInfoBmo insertActivityWinners(ExcelWinnersInfoBmo excelWinnersInfoBmo,
                                                      UserAccInfo userAccInfo,
                                                      User user,
                                                      Date currentTime,
                                                      Activity activity) {
        ActivityWinners activityWinners = new ActivityWinners();
        activityWinners.setActivityId(activity.getActivityId());
        activityWinners.setUserId(user.getUserId());
        activityWinners.setUserAccInfoId(userAccInfo.getUserAccInfoId());
        activityWinners.setAwardAmount(excelWinnersInfoBmo.getAwardAmount());
        activityWinners.setCreateTime(currentTime);
        activityWinners.setUpdateTime(currentTime);
        activityWinnersMapper.insertSelective(activityWinners);

        Product productInsert = new Product();
        productInsert.setMarketPrice(excelWinnersInfoBmo.getAwardAmount());
        productInsert.setSellPrice(excelWinnersInfoBmo.getAwardAmount());
        productInsert.setState(CommonConstant.PRODUCT_STATE_1);
        productInsert.setCreateTime(currentTime);
        productInsert.setUpdateTime(currentTime);
        productMapper.insertSelective(productInsert);

        ActivityWinsPdt activityWinsPdt = new ActivityWinsPdt();
        activityWinsPdt.setActivityWinnersId(activityWinners.getActivityWinnersId());
        activityWinsPdt.setProductId(productInsert.getProductId());
        activityWinsPdt.setCreateTime(currentTime);
        activityWinsPdt.setUpdateTime(currentTime);
        activityWinsPdtMapper.insertSelective(activityWinsPdt);


        //关联券

        List<Integer> list = ticketExtMapper.selectDistinctNominalValue();

        List<Ticket> resultTicket = new ArrayList<>();

        matchTiket(excelWinnersInfoBmo, list, resultTicket);

        if (CollectionUtils.isEmpty(resultTicket)) {
            LOG.error("处理中奖名单没用匹配到券信息");
            throw new BaseRuntimeException(ResultEnum.PRODUCT_TICKET_INFO_ERROR);
        }

        for (Ticket ticket : resultTicket) {
            ProductTicket productTicket = new ProductTicket();
            productTicket.setProductId(productInsert.getProductId());
            productTicket.setTicketId(ticket.getTicketId());
            productTicket.setCreateTime(currentTime);
            productTicket.setUpdateTime(currentTime);
            productTicketMapper.insertSelective(productTicket);
        }

        Ticket updTicket = new Ticket();
        updTicket.setState(CommonConstant.PRODUCT_TICKET_1);
        TicketExample updTicketExample = new TicketExample();
        updTicketExample.createCriteria().andTicketIdIn(resultTicket.stream().map(Ticket::getTicketId).collect(Collectors.toList()));
        ticketMapper.updateByExampleSelective(updTicket, updTicketExample);

        ProductPriceInfoBmo productPriceInfoBmo = new ProductPriceInfoBmo();
        productPriceInfoBmo.setProductId(productInsert.getProductId());
        productPriceInfoBmo.setPrice(productInsert.getSellPrice());
        return productPriceInfoBmo;

    }

    private void matchTiket(ExcelWinnersInfoBmo excelWinnersInfoBmo, List<Integer> list, List<Ticket> resultTicket) {
        int yu = excelWinnersInfoBmo.getAwardAmount();
        for (Integer price : list) {
            int sum = resultTicket.stream().mapToInt(Ticket::getNominalValue).sum();
            if (sum == excelWinnersInfoBmo.getAwardAmount()) {
                break;
            }
            if (yu < price) {
                continue;
            }

            int count = queryTicketCount(price);
            if (count == 0) {
                continue;
            }

            int i = yu % price;
            if (i == 0) {
                int pageSize = yu / price;
                if (pageSize > count) {
                    List<Ticket> tickets = queryTicketInfo(price, count);
                    //绑定券
                    resultTicket.addAll(tickets);
                    yu = yu - price * count;
                    continue;
                }

                List<Ticket> tickets = queryTicketInfo(price, pageSize);
                //绑定券
                resultTicket.addAll(tickets);

            } else {
                int pageSize = yu / price;
                if (pageSize > count) {
                    List<Ticket> tickets = queryTicketInfo(price, count);
                    //绑定券
                    resultTicket.addAll(tickets);
                    yu = yu - price * count;
                    continue;
                }

                List<Ticket> tickets = queryTicketInfo(price, pageSize);
                //绑定券
                resultTicket.addAll(tickets);
                yu = yu % price;
            }
        }
    }

    private int queryTicketCount(Integer price) {

        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andStateEqualTo(CommonConstant.PRODUCT_TICKET_0);
        criteria.andNominalValueEqualTo(price);
        long l = ticketMapper.countByExample(ticketExample);
        return (int) l;
    }

    private List<Ticket> queryTicketInfo(Integer price, int pageSize) {

        TicketExample ticketExample = new TicketExample();
        ticketExample.setOrderByClause(" ticket_id desc");
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andStateEqualTo(CommonConstant.PRODUCT_TICKET_0);
        criteria.andNominalValueEqualTo(price);
        PageHelper.startPage(1, pageSize, false);
        return ticketMapper.selectByExample(ticketExample);
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
                    Product product = productMapper.selectByPrimaryKey(activityWinsPdt.getProductId());
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
