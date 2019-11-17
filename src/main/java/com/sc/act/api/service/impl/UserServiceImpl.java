package com.sc.act.api.service.impl;

import com.sc.act.api.request.UserRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.UserMapper;
import com.sc.act.api.model.auto.User;
import com.sc.act.api.model.auto.UserExample;
import com.sc.act.api.request.UserListRequest;
import com.sc.act.api.response.UserContentResponse;
import com.sc.act.api.response.UserResponse;
import com.sc.act.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:用户服务实现类
 *
 * @className:UserServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(UserRequest userRequest) {
        LOG.info("进入创建用户服务请求参数{}", userRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        //TODO 必要的逻辑补充，如默认数据状态补充

        userMapper.insertSelective(user);

    }

    @Override
    public void updateUser(UserRequest userRequest) {
        LOG.info("进入更新用户服务请求参数{}", userRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        //TODO 必要的逻辑补充，如默认数据状态补充

        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserContentResponse selectUserContent(Integer userId) {
        LOG.info("进入查询用户服务请求参数userId{}", userId);
        UserContentResponse userContentResponse = new UserContentResponse();
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user) {
            return userContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(user, userContentResponse);

        return userContentResponse;
    }

    @Override
    public PageResponse<UserResponse> selectUser(UserListRequest userListRequest) {
        LOG.info("进入查询用户列表服务请求参数{}", userListRequest.toString());
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_id desc");
        UserExample.Criteria criteria = userExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != userListRequest.getUserId()) {
            criteria.andUserIdEqualTo(userListRequest.getUserId());
        }

        PageHelper.startPage(userListRequest.getPageIndex(), userListRequest.getPageSize());
        List<User> userList = userMapper.selectByExample(userExample);
        PageSerializable<User> pageInfo = PageSerializable.of(userList);
        PageResponse<UserResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<UserResponse> list = new ArrayList<>();
        response.setList(list);
        userList.forEach(user -> {
            UserResponse userResponse = new UserResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(user, userResponse);

            list.add(userResponse);
        });
        return response;
    }


}
