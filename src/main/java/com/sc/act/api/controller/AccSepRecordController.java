package com.sc.act.api.controller;

import com.sc.act.api.request.AccSepRecordRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.AccSepRecordListRequest;
import com.sc.act.api.response.AccSepRecordContentResponse;
import com.sc.act.api.response.AccSepRecordResponse;
import com.sc.act.api.service.AccSepRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 分账流水控制类
 *
 * @className:AccSepRecordController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/acc/sep/record")
@Api(value = "分账流水控制类", tags = "分账流水控制类")
public class AccSepRecordController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AccSepRecordController.class);

    @Autowired
    private AccSepRecordService accSepRecordService;

    @ApiOperation("创建或更新分账流水")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid AccSepRecordRequest accSepRecordRequest) {
        LOG.info("创建或更新分账流水请求参数{}", accSepRecordRequest.toString());
        Result result = new Result();

        if (null != accSepRecordRequest.getAccSepRecordId()) {
            accSepRecordService.updateAccSepRecord(accSepRecordRequest);
        } else {
            accSepRecordService.insertAccSepRecord(accSepRecordRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询分账流水")
    @GetMapping("/info")
    public Result<AccSepRecordContentResponse> queryInfo(@NotNull @RequestParam(name = "accSepRecordId") Integer accSepRecordId) {
        LOG.info("查询分账流水请求参数accSepRecordId={}", accSepRecordId);
        Result<AccSepRecordContentResponse> result = new Result<>();
        AccSepRecordContentResponse response =
                accSepRecordService.selectAccSepRecordContent(accSepRecordId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询分账流水列表")
    @PostMapping("/info/list")
    public Result<PageResponse<AccSepRecordResponse>> queryInfoList(@RequestBody @Valid AccSepRecordListRequest accSepRecordListRequest) {
        LOG.info("查询分账流水列表请求参数{}", accSepRecordListRequest.toString());
        Result<PageResponse<AccSepRecordResponse>> result = new Result<>();
        PageResponse<AccSepRecordResponse> response = accSepRecordService.selectAccSepRecord(accSepRecordListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
