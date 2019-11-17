package com.sc.act.api.service;

import com.sc.act.api.request.ActivityWinnersRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.ActivityWinnersListRequest;
import com.sc.act.api.response.ActivityWinnersContentResponse;
import com.sc.act.api.response.ActivityWinnersResponse;

/**
 * 功能描述:活动中奖名服务类
 *
 * @className:ActivityWinnersService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ActivityWinnersService {

    /**
     * 新增活动中奖名
     *
     * @param activityWinnersRequest
     */
    void insertActivityWinners(ActivityWinnersRequest activityWinnersRequest);


    /**
     * 更新活动中奖名
     *
     * @param activityWinnersRequest
     */
    void updateActivityWinners(ActivityWinnersRequest activityWinnersRequest);


    /**
     * 查询活动中奖名
     *
     * @param activityWinnersId
     * @return
     */
    ActivityWinnersContentResponse selectActivityWinnersContent(Integer activityWinnersId);


    /**
     * 查询活动中奖名列表
     *
     * @param activityWinnersListRequest
     * @return
     */
    PageResponse<ActivityWinnersResponse> selectActivityWinners(ActivityWinnersListRequest activityWinnersListRequest);


}
