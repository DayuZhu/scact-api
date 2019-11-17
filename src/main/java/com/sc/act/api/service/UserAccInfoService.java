package com.sc.act.api.service;

import com.sc.act.api.request.UserAccInfoRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.UserAccInfoListRequest;
import com.sc.act.api.response.UserAccInfoContentResponse;
import com.sc.act.api.response.UserAccInfoResponse;

/**
 * 功能描述:账户表信息服务类
 *
 * @className:UserAccInfoService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface UserAccInfoService {

    /**
     * 新增账户表信息
     *
     * @param userAccInfoRequest
     */
    void insertUserAccInfo(UserAccInfoRequest userAccInfoRequest);


    /**
     * 更新账户表信息
     *
     * @param userAccInfoRequest
     */
    void updateUserAccInfo(UserAccInfoRequest userAccInfoRequest);


    /**
     * 查询账户表信息
     *
     * @param userAccInfoId
     * @return
     */
    UserAccInfoContentResponse selectUserAccInfoContent(Integer userAccInfoId);


    /**
     * 查询账户表信息列表
     *
     * @param userAccInfoListRequest
     * @return
     */
    PageResponse<UserAccInfoResponse> selectUserAccInfo(UserAccInfoListRequest userAccInfoListRequest);


}
