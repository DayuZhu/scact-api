package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.mapper.auto.*;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.service.ActivityWinnersService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersServiceImpl.class);
    /**
     * 面值单位为分
     */
    private static final List<Integer> LIST_TICKET_PRICE = Lists.newArrayList(100, 500, 1000, 10000, 50000, 100000);


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handlerWinnersInfo(List<ExcelWinnersInfoBmo> list, Integer activityId) {
        LOG.info("进入处理中奖名单服务请求参数list={} activityId={}", JSON.toJSONString(list), activityId);
        //

        Activity activity = activityMapper.selectByPrimaryKey(activityId);

        if (null == activity) {
            LOG.error("处理中奖名单为查询到活动list={} activityId={}", JSON.toJSONString(list), activityId);
            throw new BaseRuntimeException(ResultEnum.ACTIVITY_NULL);
        }

        Date currentTime = new Date();
        for (ExcelWinnersInfoBmo excelWinnersInfoBmo : list) {
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
                insertActivityWinners(excelWinnersInfoBmo, userAccInfo, user, currentTime, activity);

            } else {
                User user = new User();
                user.setName(excelWinnersInfoBmo.getName());
                user.setMobile(excelWinnersInfoBmo.getMobile());
                user.setCreateTime(currentTime);
                user.setUpdateTime(currentTime);
                userMapper.insertSelective(user);
                UserAccInfo userAccInfo = insertOrUpdateUserAccInfo(excelWinnersInfoBmo, user, currentTime);
                //创建中奖人
                insertActivityWinners(excelWinnersInfoBmo, userAccInfo, user, currentTime, activity);
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

    private void insertActivityWinners(ExcelWinnersInfoBmo excelWinnersInfoBmo,
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
        productInsert.setProductName(activity.getActivityDesc());
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
        //调B2C
        //更新product


    }

}
