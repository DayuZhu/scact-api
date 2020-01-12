package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.MerchantListRequest;
import com.sc.act.api.request.MerchantRequest;
import com.sc.act.api.response.MerchantContentResponse;
import com.sc.act.api.response.MerchantResponse;
import com.sc.act.api.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 商户控制类
 *
 * @className:MerchantController
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@RestController
@RequestMapping(value = "/mis/merchant")
@Api(value = "商户控制类", tags = "商户控制类")
public class MerchantController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("创建或更新商户")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid MerchantRequest merchantRequest) {
        LOG.info("创建或更新商户请求参数{}", merchantRequest.toString());
        Result result = new Result();

        if (null != merchantRequest.getMerchantId()) {
            merchantService.updateMerchant(merchantRequest);
        } else {
            merchantService.insertMerchant(merchantRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询商户")
    @GetMapping("/info")
    public Result<MerchantContentResponse> queryInfo(@NotNull @RequestParam(name = "merchantId") Integer merchantId) {
        LOG.info("查询商户请求参数merchantId={}", merchantId);
        Result<MerchantContentResponse> result = new Result<>();
        MerchantContentResponse response =
                merchantService.selectMerchantContent(merchantId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询商户列表")
    @PostMapping("/info/list")
    public Result<PageResponse<MerchantResponse>> queryInfoList(@RequestBody @Valid MerchantListRequest merchantListRequest) {
        LOG.info("查询商户列表请求参数{}", merchantListRequest.toString());
        Result<PageResponse<MerchantResponse>> result = new Result<>();
        PageResponse<MerchantResponse> response = merchantService.selectMerchant(merchantListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
