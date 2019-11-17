package com.sc.act.api.service.impl;

import com.sc.act.api.request.ActivityWinsPdtRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.ActivityWinsPdtMapper;
import com.sc.act.api.model.auto.ActivityWinsPdt;
import com.sc.act.api.model.auto.ActivityWinsPdtExample;
import com.sc.act.api.request.ActivityWinsPdtListRequest;
import com.sc.act.api.response.ActivityWinsPdtContentResponse;
import com.sc.act.api.response.ActivityWinsPdtResponse;
import com.sc.act.api.service.ActivityWinsPdtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:中奖人与产品关系服务实现类
 *
 * @className:ActivityWinsPdtServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ActivityWinsPdtServiceImpl implements ActivityWinsPdtService {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinsPdtServiceImpl.class);

    @Autowired
    private ActivityWinsPdtMapper activityWinsPdtMapper;

    @Override
    public void insertActivityWinsPdt(ActivityWinsPdtRequest activityWinsPdtRequest) {
        LOG.info("进入创建中奖人与产品关系服务请求参数{}", activityWinsPdtRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ActivityWinsPdt activityWinsPdt = new ActivityWinsPdt();
        BeanUtils.copyProperties(activityWinsPdtRequest, activityWinsPdt);

        //TODO 必要的逻辑补充，如默认数据状态补充

        activityWinsPdtMapper.insertSelective(activityWinsPdt);

    }

    @Override
    public void updateActivityWinsPdt(ActivityWinsPdtRequest activityWinsPdtRequest) {
        LOG.info("进入更新中奖人与产品关系服务请求参数{}", activityWinsPdtRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ActivityWinsPdt activityWinsPdt = new ActivityWinsPdt();
        BeanUtils.copyProperties(activityWinsPdtRequest, activityWinsPdt);

        //TODO 必要的逻辑补充，如默认数据状态补充

        activityWinsPdtMapper.updateByPrimaryKeySelective(activityWinsPdt);
    }

    @Override
    public ActivityWinsPdtContentResponse selectActivityWinsPdtContent(Integer activityWinsPdtId) {
        LOG.info("进入查询中奖人与产品关系服务请求参数activityWinsPdtId{}", activityWinsPdtId);
        ActivityWinsPdtContentResponse activityWinsPdtContentResponse = new ActivityWinsPdtContentResponse();
        ActivityWinsPdt activityWinsPdt = activityWinsPdtMapper.selectByPrimaryKey(activityWinsPdtId);
        if (null == activityWinsPdt) {
            return activityWinsPdtContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(activityWinsPdt, activityWinsPdtContentResponse);

        return activityWinsPdtContentResponse;
    }

    @Override
    public PageResponse<ActivityWinsPdtResponse> selectActivityWinsPdt(ActivityWinsPdtListRequest activityWinsPdtListRequest) {
        LOG.info("进入查询中奖人与产品关系列表服务请求参数{}", activityWinsPdtListRequest.toString());
        ActivityWinsPdtExample activityWinsPdtExample = new ActivityWinsPdtExample();
        activityWinsPdtExample.setOrderByClause("activity_wins_pdt_id desc");
        ActivityWinsPdtExample.Criteria criteria = activityWinsPdtExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != activityWinsPdtListRequest.getActivityWinsPdtId()) {
            criteria.andActivityWinsPdtIdEqualTo(activityWinsPdtListRequest.getActivityWinsPdtId());
        }

        PageHelper.startPage(activityWinsPdtListRequest.getPageIndex(), activityWinsPdtListRequest.getPageSize());
        List<ActivityWinsPdt> activityWinsPdtList = activityWinsPdtMapper.selectByExample(activityWinsPdtExample);
        PageSerializable<ActivityWinsPdt> pageInfo = PageSerializable.of(activityWinsPdtList);
        PageResponse<ActivityWinsPdtResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<ActivityWinsPdtResponse> list = new ArrayList<>();
        response.setList(list);
        activityWinsPdtList.forEach(activityWinsPdt -> {
            ActivityWinsPdtResponse activityWinsPdtResponse = new ActivityWinsPdtResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(activityWinsPdt, activityWinsPdtResponse);

            list.add(activityWinsPdtResponse);
        });
        return response;
    }


}
