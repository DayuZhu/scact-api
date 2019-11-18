package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.mapper.auto.ActivityMapper;
import com.sc.act.api.model.auto.Activity;
import com.sc.act.api.model.auto.ActivityExample;
import com.sc.act.api.request.ActivityListRequest;
import com.sc.act.api.request.ActivityRequest;
import com.sc.act.api.response.ActivityContentResponse;
import com.sc.act.api.response.ActivityResponse;
import com.sc.act.api.service.ActivityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:活动服务实现类
 *
 * @className:ActivityServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertActivity(ActivityRequest activityRequest) {
        LOG.info("进入创建活动服务请求参数{}", activityRequest.toString());

        Date currentTime = new Date();

        Activity activity = new Activity();

        activity.setActivityName(activityRequest.getActivityName());
        activity.setActivityDesc(activityRequest.getActivityDesc());
        activity.setStartTime(activityRequest.getStartTime());
        activity.setEndTime(activityRequest.getEndTime());
        activity.setState(activityRequest.getState());

        if (null != activityRequest.getCreateUserId()
                && StringUtils.isNotBlank(activityRequest.getCreateUserName())) {
            activity.setCreateUserId(activityRequest.getCreateUserId());
            activity.setCreateUserName(activityRequest.getCreateUserName());
            activity.setUpdateUserId(activityRequest.getCreateUserId());
            activity.setUpdateUserName(activityRequest.getCreateUserName());
        }

        activity.setCreateTime(currentTime);
        activity.setUpdateTime(currentTime);

        activityMapper.insertSelective(activity);
    }


    @Override
    public void updateActivity(ActivityRequest activityRequest) {
        LOG.info("进入更新活动服务请求参数{}", activityRequest.toString());

        Date currentTime = new Date();

        Activity activity = new Activity();
        activity.setActivityId(activityRequest.getActivityId());
        activity.setActivityName(activityRequest.getActivityName());
        activity.setActivityDesc(activityRequest.getActivityDesc());
        activity.setStartTime(activityRequest.getStartTime());
        activity.setEndTime(activityRequest.getEndTime());
        activity.setState(activityRequest.getState());

        if (null != activityRequest.getUpdateUserId()
                && StringUtils.isNotBlank(activityRequest.getUpdateUserName())) {
            activity.setUpdateUserId(activityRequest.getUpdateUserId());
            activity.setUpdateUserName(activityRequest.getUpdateUserName());
        }
        activity.setUpdateTime(currentTime);
        activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public ActivityContentResponse selectActivityContent(Integer activityId) {
        LOG.info("进入查询活动服务请求参数activityId{}", activityId);
        ActivityContentResponse activityContentResponse = new ActivityContentResponse();
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (null == activity) {
            return activityContentResponse;
        }
        BeanUtils.copyProperties(activity, activityContentResponse);
        return activityContentResponse;
    }

    @Override
    public PageResponse<ActivityResponse> selectActivity(ActivityListRequest activityListRequest) {
        LOG.info("进入查询活动列表服务请求参数{}", activityListRequest.toString());
        ActivityExample activityExample = new ActivityExample();
        activityExample.setOrderByClause("activity_id desc");
        ActivityExample.Criteria criteria = activityExample.createCriteria();

        if (null != activityListRequest.getActivityId()) {
            criteria.andActivityIdEqualTo(activityListRequest.getActivityId());
        }

        if (StringUtils.isNotBlank(activityListRequest.getActivityName())) {
            criteria.andActivityNameLike(
                    CommonConstant.STRING_PERCENT
                            + activityListRequest.getActivityName()
                            + CommonConstant.STRING_PERCENT);
        }

        if (null != activityListRequest.getState()) {
            criteria.andStateEqualTo(activityListRequest.getState());
        }

        PageHelper.startPage(activityListRequest.getPageIndex(), activityListRequest.getPageSize());
        List<Activity> activityList = activityMapper.selectByExample(activityExample);
        PageSerializable<Activity> pageInfo = PageSerializable.of(activityList);
        PageResponse<ActivityResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<ActivityResponse> list = new ArrayList<>();
        response.setList(list);
        activityList.forEach(activity -> {
            ActivityResponse activityResponse = new ActivityResponse();
            BeanUtils.copyProperties(activity, activityResponse);
            list.add(activityResponse);
        });
        return response;
    }


}
