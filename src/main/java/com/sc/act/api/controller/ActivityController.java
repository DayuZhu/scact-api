package com.sc.act.api.controller;

import com.sc.act.api.request.ActivityRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.ActivityListRequest;
import com.sc.act.api.response.ActivityContentResponse;
import com.sc.act.api.response.ActivityResponse;
import com.sc.act.api.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 活动控制类
 *
 * @className:ActivityController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:47
 */
@RestController
@RequestMapping(value = "/mis/activity")
@Api(value = "活动控制类", tags = "活动控制类")
public class ActivityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @ApiOperation("创建或更新活动")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ActivityRequest activityRequest) {
        LOG.info("创建或更新活动请求参数{}", activityRequest.toString());
        Result result = new Result();

        if (null != activityRequest.getActivityId()) {
            activityService.updateActivity(activityRequest);
        } else {
            activityService.insertActivity(activityRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询活动")
    @GetMapping("/info")
    public Result<ActivityContentResponse> queryInfo(@NotNull @RequestParam(name = "activityId") Integer activityId) {
        LOG.info("查询活动请求参数activityId={}", activityId);
        Result<ActivityContentResponse> result = new Result<>();
        ActivityContentResponse response =
                activityService.selectActivityContent(activityId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询活动列表")
    @PostMapping("/info/list")
    public Result<PageResponse<ActivityResponse>> queryInfoList(@RequestBody @Valid ActivityListRequest activityListRequest) {
        LOG.info("查询活动列表请求参数{}", activityListRequest.toString());
        Result<PageResponse<ActivityResponse>> result = new Result<>();
        PageResponse<ActivityResponse> response = activityService.selectActivity(activityListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
