package com.sc.act.api.service;

import com.sc.act.api.request.ActivityRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.ActivityListRequest;
import com.sc.act.api.response.ActivityContentResponse;
import com.sc.act.api.response.ActivityResponse;

/**
 * 功能描述:活动服务类
 *
 * @className:ActivityService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ActivityService {

    /**
     * 新增活动
     *
     * @param activityRequest
     */
    void insertActivity(ActivityRequest activityRequest);


    /**
     * 更新活动
     *
     * @param activityRequest
     */
    void updateActivity(ActivityRequest activityRequest);


    /**
     * 查询活动
     *
     * @param activityId
     * @return
     */
    ActivityContentResponse selectActivityContent(Integer activityId);


    /**
     * 查询活动列表
     *
     * @param activityListRequest
     * @return
     */
    PageResponse<ActivityResponse> selectActivity(ActivityListRequest activityListRequest);


}
