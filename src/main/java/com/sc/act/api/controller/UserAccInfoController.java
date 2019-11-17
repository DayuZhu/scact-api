package com.sc.act.api.controller;

import com.sc.act.api.request.UserAccInfoRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.UserAccInfoListRequest;
import com.sc.act.api.response.UserAccInfoContentResponse;
import com.sc.act.api.response.UserAccInfoResponse;
import com.sc.act.api.service.UserAccInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 账户表信息控制类
 *
 * @className:UserAccInfoController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/user/acc")
@Api(value = "账户表信息控制类", tags = "账户表信息控制类")
public class UserAccInfoController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccInfoController.class);

    @Autowired
    private UserAccInfoService userAccInfoService;

    @ApiOperation("创建或更新账户表信息")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid UserAccInfoRequest userAccInfoRequest) {
        LOG.info("创建或更新账户表信息请求参数{}", userAccInfoRequest.toString());
        Result result = new Result();

        if (null != userAccInfoRequest.getUserAccInfoId()) {
            userAccInfoService.updateUserAccInfo(userAccInfoRequest);
        } else {
            userAccInfoService.insertUserAccInfo(userAccInfoRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询账户表信息")
    @GetMapping("/info")
    public Result<UserAccInfoContentResponse> queryInfo(@NotNull @RequestParam(name = "userAccInfoId") Integer userAccInfoId) {
        LOG.info("查询账户表信息请求参数userAccInfoId={}", userAccInfoId);
        Result<UserAccInfoContentResponse> result = new Result<>();
        UserAccInfoContentResponse response =
                userAccInfoService.selectUserAccInfoContent(userAccInfoId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询账户表信息列表")
    @PostMapping("/info/list")
    public Result<PageResponse<UserAccInfoResponse>> queryInfoList(@RequestBody @Valid UserAccInfoListRequest userAccInfoListRequest) {
        LOG.info("查询账户表信息列表请求参数{}", userAccInfoListRequest.toString());
        Result<PageResponse<UserAccInfoResponse>> result = new Result<>();
        PageResponse<UserAccInfoResponse> response = userAccInfoService.selectUserAccInfo(userAccInfoListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
