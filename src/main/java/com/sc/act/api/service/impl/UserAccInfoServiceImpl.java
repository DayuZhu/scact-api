package com.sc.act.api.service.impl;

import com.sc.act.api.request.UserAccInfoRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.UserAccInfoMapper;
import com.sc.act.api.model.auto.UserAccInfo;
import com.sc.act.api.model.auto.UserAccInfoExample;
import com.sc.act.api.request.UserAccInfoListRequest;
import com.sc.act.api.response.UserAccInfoContentResponse;
import com.sc.act.api.response.UserAccInfoResponse;
import com.sc.act.api.service.UserAccInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:账户表信息服务实现类
 *
 * @className:UserAccInfoServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class UserAccInfoServiceImpl implements UserAccInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccInfoServiceImpl.class);

    @Autowired
    private UserAccInfoMapper userAccInfoMapper;

    @Override
    public void insertUserAccInfo(UserAccInfoRequest userAccInfoRequest) {
        LOG.info("进入创建账户表信息服务请求参数{}", userAccInfoRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        UserAccInfo userAccInfo = new UserAccInfo();
        BeanUtils.copyProperties(userAccInfoRequest, userAccInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        userAccInfoMapper.insertSelective(userAccInfo);

    }

    @Override
    public void updateUserAccInfo(UserAccInfoRequest userAccInfoRequest) {
        LOG.info("进入更新账户表信息服务请求参数{}", userAccInfoRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        UserAccInfo userAccInfo = new UserAccInfo();
        BeanUtils.copyProperties(userAccInfoRequest, userAccInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        userAccInfoMapper.updateByPrimaryKeySelective(userAccInfo);
    }

    @Override
    public UserAccInfoContentResponse selectUserAccInfoContent(Integer userAccInfoId) {
        LOG.info("进入查询账户表信息服务请求参数userAccInfoId{}", userAccInfoId);
        UserAccInfoContentResponse userAccInfoContentResponse = new UserAccInfoContentResponse();
        UserAccInfo userAccInfo = userAccInfoMapper.selectByPrimaryKey(userAccInfoId);
        if (null == userAccInfo) {
            return userAccInfoContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(userAccInfo, userAccInfoContentResponse);

        return userAccInfoContentResponse;
    }

    @Override
    public PageResponse<UserAccInfoResponse> selectUserAccInfo(UserAccInfoListRequest userAccInfoListRequest) {
        LOG.info("进入查询账户表信息列表服务请求参数{}", userAccInfoListRequest.toString());
        UserAccInfoExample userAccInfoExample = new UserAccInfoExample();
        userAccInfoExample.setOrderByClause("user_acc_info_id desc");
        UserAccInfoExample.Criteria criteria = userAccInfoExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != userAccInfoListRequest.getUserAccInfoId()) {
            criteria.andUserAccInfoIdEqualTo(userAccInfoListRequest.getUserAccInfoId());
        }

        PageHelper.startPage(userAccInfoListRequest.getPageIndex(), userAccInfoListRequest.getPageSize());
        List<UserAccInfo> userAccInfoList = userAccInfoMapper.selectByExample(userAccInfoExample);
        PageSerializable<UserAccInfo> pageInfo = PageSerializable.of(userAccInfoList);
        PageResponse<UserAccInfoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<UserAccInfoResponse> list = new ArrayList<>();
        response.setList(list);
        userAccInfoList.forEach(userAccInfo -> {
            UserAccInfoResponse userAccInfoResponse = new UserAccInfoResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(userAccInfo, userAccInfoResponse);

            list.add(userAccInfoResponse);
        });
        return response;
    }


}
