package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.BankInfoListRequest;
import com.sc.act.api.request.BankInfoRequest;
import com.sc.act.api.response.BankInfoContentResponse;
import com.sc.act.api.response.BankInfoResponse;
import com.sc.act.api.service.BankInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 银行信息控制类
 *
 * @className:BankInfoController
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-15 19:27:44
 */
@RestController
@RequestMapping(value = "/mis/bank")
@Api(value = "银行信息控制类", tags = "银行信息控制类")
public class BankInfoController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BankInfoController.class);

    @Autowired
    private BankInfoService bankInfoService;

    @ApiOperation("创建或更新银行信息")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid BankInfoRequest bankInfoRequest) {
        LOG.info("创建或更新银行信息请求参数{}", bankInfoRequest.toString());
        Result result = new Result();

        if (null != bankInfoRequest.getBankInfoId()) {
            bankInfoService.updateBankInfo(bankInfoRequest);
        } else {
            bankInfoService.insertBankInfo(bankInfoRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询银行信息")
    @GetMapping("/info")
    public Result<BankInfoContentResponse> queryInfo(@NotNull @RequestParam(name = "bankInfoId") Integer bankInfoId) {
        LOG.info("查询银行信息请求参数bankInfoId={}", bankInfoId);
        Result<BankInfoContentResponse> result = new Result<>();
        BankInfoContentResponse response =
                bankInfoService.selectBankInfoContent(bankInfoId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询银行信息列表")
    @PostMapping("/info/list")
    public Result<PageResponse<BankInfoResponse>> queryInfoList(@RequestBody @Valid BankInfoListRequest bankInfoListRequest) {
        LOG.info("查询银行信息列表请求参数{}", bankInfoListRequest.toString());
        Result<PageResponse<BankInfoResponse>> result = new Result<>();
        PageResponse<BankInfoResponse> response = bankInfoService.selectBankInfo(bankInfoListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
