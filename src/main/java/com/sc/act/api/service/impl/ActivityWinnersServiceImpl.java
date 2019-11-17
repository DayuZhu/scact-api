package com.sc.act.api.service.impl;

import com.sc.act.api.request.ActivityWinnersRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.ActivityWinnersMapper;
import com.sc.act.api.model.auto.ActivityWinners;
import com.sc.act.api.model.auto.ActivityWinnersExample;
import com.sc.act.api.request.ActivityWinnersListRequest;
import com.sc.act.api.response.ActivityWinnersContentResponse;
import com.sc.act.api.response.ActivityWinnersResponse;
import com.sc.act.api.service.ActivityWinnersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

    @Override
    public void insertActivityWinners(ActivityWinnersRequest activityWinnersRequest) {
        LOG.info("进入创建活动中奖名服务请求参数{}", activityWinnersRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ActivityWinners activityWinners = new ActivityWinners();
        BeanUtils.copyProperties(activityWinnersRequest, activityWinners);

        //TODO 必要的逻辑补充，如默认数据状态补充

        activityWinnersMapper.insertSelective(activityWinners);

    }

    @Override
    public void updateActivityWinners(ActivityWinnersRequest activityWinnersRequest) {
        LOG.info("进入更新活动中奖名服务请求参数{}", activityWinnersRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ActivityWinners activityWinners = new ActivityWinners();
        BeanUtils.copyProperties(activityWinnersRequest, activityWinners);

        //TODO 必要的逻辑补充，如默认数据状态补充

        activityWinnersMapper.updateByPrimaryKeySelective(activityWinners);
    }

    @Override
    public ActivityWinnersContentResponse selectActivityWinnersContent(Integer activityWinnersId) {
        LOG.info("进入查询活动中奖名服务请求参数activityWinnersId{}", activityWinnersId);
        ActivityWinnersContentResponse activityWinnersContentResponse = new ActivityWinnersContentResponse();
        ActivityWinners activityWinners = activityWinnersMapper.selectByPrimaryKey(activityWinnersId);
        if (null == activityWinners) {
            return activityWinnersContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(activityWinners, activityWinnersContentResponse);

        return activityWinnersContentResponse;
    }

    @Override
    public PageResponse<ActivityWinnersResponse> selectActivityWinners(ActivityWinnersListRequest activityWinnersListRequest) {
        LOG.info("进入查询活动中奖名列表服务请求参数{}", activityWinnersListRequest.toString());
        ActivityWinnersExample activityWinnersExample = new ActivityWinnersExample();
        activityWinnersExample.setOrderByClause("activity_winners_id desc");
        ActivityWinnersExample.Criteria criteria = activityWinnersExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != activityWinnersListRequest.getActivityWinnersId()) {
            criteria.andActivityWinnersIdEqualTo(activityWinnersListRequest.getActivityWinnersId());
        }

        PageHelper.startPage(activityWinnersListRequest.getPageIndex(), activityWinnersListRequest.getPageSize());
        List<ActivityWinners> activityWinnersList = activityWinnersMapper.selectByExample(activityWinnersExample);
        PageSerializable<ActivityWinners> pageInfo = PageSerializable.of(activityWinnersList);
        PageResponse<ActivityWinnersResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<ActivityWinnersResponse> list = new ArrayList<>();
        response.setList(list);
        activityWinnersList.forEach(activityWinners -> {
            ActivityWinnersResponse activityWinnersResponse = new ActivityWinnersResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(activityWinners, activityWinnersResponse);

            list.add(activityWinnersResponse);
        });
        return response;
    }


}
