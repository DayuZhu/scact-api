package com.sc.act.api.controller;

import com.sc.act.api.request.ActivityWinsPdtRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.ActivityWinsPdtListRequest;
import com.sc.act.api.response.ActivityWinsPdtContentResponse;
import com.sc.act.api.response.ActivityWinsPdtResponse;
import com.sc.act.api.service.ActivityWinsPdtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 中奖人与产品关系控制类
 *
 * @className:ActivityWinsPdtController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/activity/wins/pdt")
@Api(value = "中奖人与产品关系控制类", tags = "中奖人与产品关系控制类")
public class ActivityWinsPdtController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinsPdtController.class);

    @Autowired
    private ActivityWinsPdtService activityWinsPdtService;

    @ApiOperation("创建或更新中奖人与产品关系")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ActivityWinsPdtRequest activityWinsPdtRequest) {
        LOG.info("创建或更新中奖人与产品关系请求参数{}", activityWinsPdtRequest.toString());
        Result result = new Result();

        if (null != activityWinsPdtRequest.getActivityWinsPdtId()) {
            activityWinsPdtService.updateActivityWinsPdt(activityWinsPdtRequest);
        } else {
            activityWinsPdtService.insertActivityWinsPdt(activityWinsPdtRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询中奖人与产品关系")
    @GetMapping("/info")
    public Result<ActivityWinsPdtContentResponse> queryInfo(@NotNull @RequestParam(name = "activityWinsPdtId") Integer activityWinsPdtId) {
        LOG.info("查询中奖人与产品关系请求参数activityWinsPdtId={}", activityWinsPdtId);
        Result<ActivityWinsPdtContentResponse> result = new Result<>();
        ActivityWinsPdtContentResponse response =
                activityWinsPdtService.selectActivityWinsPdtContent(activityWinsPdtId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询中奖人与产品关系列表")
    @PostMapping("/info/list")
    public Result<PageResponse<ActivityWinsPdtResponse>> queryInfoList(@RequestBody @Valid ActivityWinsPdtListRequest activityWinsPdtListRequest) {
        LOG.info("查询中奖人与产品关系列表请求参数{}", activityWinsPdtListRequest.toString());
        Result<PageResponse<ActivityWinsPdtResponse>> result = new Result<>();
        PageResponse<ActivityWinsPdtResponse> response = activityWinsPdtService.selectActivityWinsPdt(activityWinsPdtListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
