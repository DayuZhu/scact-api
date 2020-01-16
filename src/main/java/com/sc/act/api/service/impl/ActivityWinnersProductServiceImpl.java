package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.mapper.auto.*;
import com.sc.act.api.mapper.ext.TicketExtMapper;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.model.bo.ProductPriceInfoBmo;
import com.sc.act.api.service.ActivityWinnersProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @className:ActivityWinnersProductServiceImpl
 * @projectName:scact-api-2019-1116
 * @author:Dayu
 * @date: 2020/1/15 11:54
 */
@Service
public class ActivityWinnersProductServiceImpl implements ActivityWinnersProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersProductServiceImpl.class);


    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

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
    private MerchantAccountRecordMapper merchantAccountRecordMapper;

    /**
     * undo的动作太大  不建议加事务 但是没有相应的补偿  建议活动名单控制一下不要太多
     *
     * @param list
     * @param activity
     * @param currentTime
     * @param productPriceInfoList
     */
    @Override
    public void createWinnersProductInfo(List<ExcelWinnersInfoBmo> list, Activity activity, Date currentTime, List<ProductPriceInfoBmo> productPriceInfoList) {

        LOG.info("进入中奖人和奖品绑定服务list={} activity={} currentTime={} productPriceInfoList={}",
                JSON.toJSONString(list), JSON.toJSONString(activity), currentTime, JSON.toJSONString(productPriceInfoList));

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

        MerchantAccountRecord merchantAccountRecord = new MerchantAccountRecord();
        merchantAccountRecord.setMerchantId(activity.getMerchantId());
        merchantAccountRecord.setRecordType(1);
        merchantAccountRecord.setPayoutAmount(activityWinners.getAwardAmount());
        merchantAccountRecord.setReasonDesc("活动中奖");
        merchantAccountRecord.setActivityWinnersId(activityWinners.getActivityWinnersId());
        merchantAccountRecord.setActivityId(activity.getActivityId());
        merchantAccountRecord.setCreateTime(currentTime);
        merchantAccountRecord.setUpdateTime(currentTime);
        merchantAccountRecordMapper.insertSelective(merchantAccountRecord);

        Product productInsert = new Product();
        productInsert.setMarketPrice(excelWinnersInfoBmo.getAwardAmount());
        productInsert.setSellPrice(excelWinnersInfoBmo.getAwardAmount());
        productInsert.setState(CommonConstant.PRODUCT_STATE_1);
        productInsert.setCreateTime(currentTime);
        productInsert.setUpdateTime(currentTime);
        productMapper.insertSelective(productInsert);

        ActivityWinsPdt activityWinsPdt = new ActivityWinsPdt();
        activityWinsPdt.setActivityWinnersId(activityWinners.getActivityWinnersId());
        activityWinsPdt.setActivityId(activity.getActivityId());
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

}
