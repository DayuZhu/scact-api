package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.MerchantAccountRecordListRequest;
import com.sc.act.api.request.MerchantAccountRecordRequest;
import com.sc.act.api.response.MerchantAccountRecordContentResponse;
import com.sc.act.api.response.MerchantAccountRecordResponse;
import com.sc.act.api.service.MerchantAccountRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 商户资金记录控制类
 *
 * @className:MerchantAccountRecordController
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@RestController
@RequestMapping(value = "/mis/merchant/account/record")
@Api(value = "商户资金记录控制类", tags = "商户资金记录控制类")
public class MerchantAccountRecordController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccountRecordController.class);

    @Autowired
    private MerchantAccountRecordService merchantAccountRecordService;

    @ApiOperation("创建或更新商户资金记录")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid MerchantAccountRecordRequest merchantAccountRecordRequest) {
        LOG.info("创建或更新商户资金记录请求参数{}", merchantAccountRecordRequest.toString());
        Result result = new Result();

        if (null != merchantAccountRecordRequest.getMerchantAccountRecordId()) {
            merchantAccountRecordService.updateMerchantAccountRecord(merchantAccountRecordRequest);
        } else {
            merchantAccountRecordService.insertMerchantAccountRecord(merchantAccountRecordRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询商户资金记录")
    @GetMapping("/info")
    public Result<MerchantAccountRecordContentResponse> queryInfo(@NotNull @RequestParam(name = "merchantAccountRecordId") Integer merchantAccountRecordId) {
        LOG.info("查询商户资金记录请求参数merchantAccountRecordId={}", merchantAccountRecordId);
        Result<MerchantAccountRecordContentResponse> result = new Result<>();
        MerchantAccountRecordContentResponse response =
                merchantAccountRecordService.selectMerchantAccountRecordContent(merchantAccountRecordId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询商户资金记录列表")
    @PostMapping("/info/list")
    public Result<PageResponse<MerchantAccountRecordResponse>> queryInfoList(@RequestBody @Valid MerchantAccountRecordListRequest merchantAccountRecordListRequest) {
        LOG.info("查询商户资金记录列表请求参数{}", merchantAccountRecordListRequest.toString());
        Result<PageResponse<MerchantAccountRecordResponse>> result = new Result<>();
        PageResponse<MerchantAccountRecordResponse> response = merchantAccountRecordService.selectMerchantAccountRecord(merchantAccountRecordListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
