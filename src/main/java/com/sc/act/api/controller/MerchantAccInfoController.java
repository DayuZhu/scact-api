package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.MerchantAccInfoListRequest;
import com.sc.act.api.request.MerchantAccInfoRequest;
import com.sc.act.api.response.MerchantAccInfoContentResponse;
import com.sc.act.api.response.MerchantAccInfoResponse;
import com.sc.act.api.service.MerchantAccInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 商户账户信息控制类
 *
 * @className:MerchantAccInfoController
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@RestController
@RequestMapping(value = "/mis/merchant/acc")
@Api(value = "商户账户信息控制类", tags = "商户账户信息控制类")
public class MerchantAccInfoController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccInfoController.class);

    @Autowired
    private MerchantAccInfoService merchantAccInfoService;

    @ApiOperation("创建或更新商户账户信息")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid MerchantAccInfoRequest merchantAccInfoRequest) {
        LOG.info("创建或更新商户账户信息请求参数{}", merchantAccInfoRequest.toString());
        Result result = new Result();

        if (null != merchantAccInfoRequest.getMerchantAccInfoId()) {
            merchantAccInfoService.updateMerchantAccInfo(merchantAccInfoRequest);
        } else {
            merchantAccInfoService.insertMerchantAccInfo(merchantAccInfoRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询商户账户信息")
    @GetMapping("/info")
    public Result<MerchantAccInfoContentResponse> queryInfo(@NotNull @RequestParam(name = "merchantAccInfoId") Integer merchantAccInfoId) {
        LOG.info("查询商户账户信息请求参数merchantAccInfoId={}", merchantAccInfoId);
        Result<MerchantAccInfoContentResponse> result = new Result<>();
        MerchantAccInfoContentResponse response =
                merchantAccInfoService.selectMerchantAccInfoContent(merchantAccInfoId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询商户账户信息列表")
    @PostMapping("/info/list")
    public Result<PageResponse<MerchantAccInfoResponse>> queryInfoList(@RequestBody @Valid MerchantAccInfoListRequest merchantAccInfoListRequest) {
        LOG.info("查询商户账户信息列表请求参数{}", merchantAccInfoListRequest.toString());
        Result<PageResponse<MerchantAccInfoResponse>> result = new Result<>();
        PageResponse<MerchantAccInfoResponse> response = merchantAccInfoService.selectMerchantAccInfo(merchantAccInfoListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
