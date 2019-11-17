package com.sc.act.api.service;

import com.sc.act.api.request.ActivityWinsPdtRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.ActivityWinsPdtListRequest;
import com.sc.act.api.response.ActivityWinsPdtContentResponse;
import com.sc.act.api.response.ActivityWinsPdtResponse;

/**
 * 功能描述:中奖人与产品关系服务类
 *
 * @className:ActivityWinsPdtService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ActivityWinsPdtService {

    /**
     * 新增中奖人与产品关系
     *
     * @param activityWinsPdtRequest
     */
    void insertActivityWinsPdt(ActivityWinsPdtRequest activityWinsPdtRequest);


    /**
     * 更新中奖人与产品关系
     *
     * @param activityWinsPdtRequest
     */
    void updateActivityWinsPdt(ActivityWinsPdtRequest activityWinsPdtRequest);


    /**
     * 查询中奖人与产品关系
     *
     * @param activityWinsPdtId
     * @return
     */
    ActivityWinsPdtContentResponse selectActivityWinsPdtContent(Integer activityWinsPdtId);


    /**
     * 查询中奖人与产品关系列表
     *
     * @param activityWinsPdtListRequest
     * @return
     */
    PageResponse<ActivityWinsPdtResponse> selectActivityWinsPdt(ActivityWinsPdtListRequest activityWinsPdtListRequest);


}
