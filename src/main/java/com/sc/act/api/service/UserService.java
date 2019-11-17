package com.sc.act.api.service;

import com.sc.act.api.request.UserRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.UserListRequest;
import com.sc.act.api.response.UserContentResponse;
import com.sc.act.api.response.UserResponse;

/**
 * 功能描述:用户服务类
 *
 * @className:UserService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param userRequest
     */
    void insertUser(UserRequest userRequest);


    /**
     * 更新用户
     *
     * @param userRequest
     */
    void updateUser(UserRequest userRequest);


    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    UserContentResponse selectUserContent(Integer userId);


    /**
     * 查询用户列表
     *
     * @param userListRequest
     * @return
     */
    PageResponse<UserResponse> selectUser(UserListRequest userListRequest);


}
