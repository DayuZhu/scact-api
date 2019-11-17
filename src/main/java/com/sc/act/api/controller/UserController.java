package com.sc.act.api.controller;

import com.sc.act.api.request.UserRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.UserListRequest;
import com.sc.act.api.response.UserContentResponse;
import com.sc.act.api.response.UserResponse;
import com.sc.act.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 用户控制类
 *
 * @className:UserController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/user")
@Api(value = "用户控制类", tags = "用户控制类")
public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("创建或更新用户")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid UserRequest userRequest) {
        LOG.info("创建或更新用户请求参数{}", userRequest.toString());
        Result result = new Result();

        if (null != userRequest.getUserId()) {
            userService.updateUser(userRequest);
        } else {
            userService.insertUser(userRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询用户")
    @GetMapping("/info")
    public Result<UserContentResponse> queryInfo(@NotNull @RequestParam(name = "userId") Integer userId) {
        LOG.info("查询用户请求参数userId={}", userId);
        Result<UserContentResponse> result = new Result<>();
        UserContentResponse response =
                userService.selectUserContent(userId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询用户列表")
    @PostMapping("/info/list")
    public Result<PageResponse<UserResponse>> queryInfoList(@RequestBody @Valid UserListRequest userListRequest) {
        LOG.info("查询用户列表请求参数{}", userListRequest.toString());
        Result<PageResponse<UserResponse>> result = new Result<>();
        PageResponse<UserResponse> response = userService.selectUser(userListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
