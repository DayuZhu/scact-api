package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.ActivityMapper;
import com.sc.act.api.mapper.auto.ActivityWinnersMapper;
import com.sc.act.api.model.auto.Activity;
import com.sc.act.api.model.auto.ActivityExample;
import com.sc.act.api.model.auto.ActivityWinners;
import com.sc.act.api.request.ActivityListRequest;
import com.sc.act.api.request.ActivityRequest;
import com.sc.act.api.response.ActivityContentResponse;
import com.sc.act.api.response.ActivityResponse;
import com.sc.act.api.service.ActivityService;
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

    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertActivity(ActivityRequest activityRequest) {
        LOG.info("进入创建活动服务请求参数{}", activityRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Activity activity = new Activity();
        activity.setCreateTime(currentTime);
        BeanUtils.copyProperties(activityRequest, activity);

        //TODO 必要的逻辑补充，如默认数据状态补充

        activityMapper.insertSelective(activity);

        ActivityWinners activityWinners = new ActivityWinners();
        activityWinners.setCreateTime(currentTime);
        activityWinnersMapper.insertSelective(activityWinners);

        System.out.println("pppp");


    }

    @Override
    public void updateActivity(ActivityRequest activityRequest) {
        LOG.info("进入更新活动服务请求参数{}", activityRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Activity activity = new Activity();
        BeanUtils.copyProperties(activityRequest, activity);

        //TODO 必要的逻辑补充，如默认数据状态补充

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

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(activity, activityContentResponse);

        return activityContentResponse;
    }

    @Override
    public PageResponse<ActivityResponse> selectActivity(ActivityListRequest activityListRequest) {
        LOG.info("进入查询活动列表服务请求参数{}", activityListRequest.toString());
        ActivityExample activityExample = new ActivityExample();
        activityExample.setOrderByClause("activity_id desc");
        ActivityExample.Criteria criteria = activityExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != activityListRequest.getActivityId()) {
            criteria.andActivityIdEqualTo(activityListRequest.getActivityId());
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

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(activity, activityResponse);

            list.add(activityResponse);
        });
        return response;
    }


}
