package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.MerchantAccountListRequest;
import com.sc.act.api.request.MerchantAccountRequest;
import com.sc.act.api.response.MerchantAccountContentResponse;
import com.sc.act.api.response.MerchantAccountResponse;
import com.sc.act.api.service.MerchantAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 商户资金信息控制类
 *
 * @className:MerchantAccountController
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@RestController
@RequestMapping(value = "/mis/merchant/account")
@Api(value = "商户资金信息控制类", tags = "商户资金信息控制类")
public class MerchantAccountController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccountController.class);

    @Autowired
    private MerchantAccountService merchantAccountService;

    @ApiOperation("创建或更新商户资金信息")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid MerchantAccountRequest merchantAccountRequest) {
        LOG.info("创建或更新商户资金信息请求参数{}", merchantAccountRequest.toString());
        Result result = new Result();

        if (null != merchantAccountRequest.getMerchantAccountId()) {
            merchantAccountService.updateMerchantAccount(merchantAccountRequest);
        } else {
            merchantAccountService.insertMerchantAccount(merchantAccountRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询商户资金信息")
    @GetMapping("/info")
    public Result<MerchantAccountContentResponse> queryInfo(@NotNull @RequestParam(name = "merchantAccountId") Integer merchantAccountId) {
        LOG.info("查询商户资金信息请求参数merchantAccountId={}", merchantAccountId);
        Result<MerchantAccountContentResponse> result = new Result<>();
        MerchantAccountContentResponse response =
                merchantAccountService.selectMerchantAccountContent(merchantAccountId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询商户资金信息列表")
    @PostMapping("/info/list")
    public Result<PageResponse<MerchantAccountResponse>> queryInfoList(@RequestBody @Valid MerchantAccountListRequest merchantAccountListRequest) {
        LOG.info("查询商户资金信息列表请求参数{}", merchantAccountListRequest.toString());
        Result<PageResponse<MerchantAccountResponse>> result = new Result<>();
        PageResponse<MerchantAccountResponse> response = merchantAccountService.selectMerchantAccount(merchantAccountListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
