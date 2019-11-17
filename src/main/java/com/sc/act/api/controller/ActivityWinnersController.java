package com.sc.act.api.controller;

import com.sc.act.api.request.ActivityWinnersRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.ActivityWinnersListRequest;
import com.sc.act.api.response.ActivityWinnersContentResponse;
import com.sc.act.api.response.ActivityWinnersResponse;
import com.sc.act.api.service.ActivityWinnersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 活动中奖名控制类
 *
 * @className:ActivityWinnersController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/activity/winners")
@Api(value = "活动中奖名控制类", tags = "活动中奖名控制类")
public class ActivityWinnersController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersController.class);

    @Autowired
    private ActivityWinnersService activityWinnersService;

    @ApiOperation("创建或更新活动中奖名")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ActivityWinnersRequest activityWinnersRequest) {
        LOG.info("创建或更新活动中奖名请求参数{}", activityWinnersRequest.toString());
        Result result = new Result();

        if (null != activityWinnersRequest.getActivityWinnersId()) {
            activityWinnersService.updateActivityWinners(activityWinnersRequest);
        } else {
            activityWinnersService.insertActivityWinners(activityWinnersRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询活动中奖名")
    @GetMapping("/info")
    public Result<ActivityWinnersContentResponse> queryInfo(@NotNull @RequestParam(name = "activityWinnersId") Integer activityWinnersId) {
        LOG.info("查询活动中奖名请求参数activityWinnersId={}", activityWinnersId);
        Result<ActivityWinnersContentResponse> result = new Result<>();
        ActivityWinnersContentResponse response =
                activityWinnersService.selectActivityWinnersContent(activityWinnersId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询活动中奖名列表")
    @PostMapping("/info/list")
    public Result<PageResponse<ActivityWinnersResponse>> queryInfoList(@RequestBody @Valid ActivityWinnersListRequest activityWinnersListRequest) {
        LOG.info("查询活动中奖名列表请求参数{}", activityWinnersListRequest.toString());
        Result<PageResponse<ActivityWinnersResponse>> result = new Result<>();
        PageResponse<ActivityWinnersResponse> response = activityWinnersService.selectActivityWinners(activityWinnersListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
