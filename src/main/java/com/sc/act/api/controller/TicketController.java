package com.sc.act.api.controller;

import com.sc.act.api.request.TicketRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.TicketListRequest;
import com.sc.act.api.response.TicketContentResponse;
import com.sc.act.api.response.TicketResponse;
import com.sc.act.api.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 券码控制类
 *
 * @className:TicketController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/ticket")
@Api(value = "券码控制类", tags = "券码控制类")
public class TicketController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @ApiOperation("创建或更新券码")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid TicketRequest ticketRequest) {
        LOG.info("创建或更新券码请求参数{}", ticketRequest.toString());
        Result result = new Result();

        if (null != ticketRequest.getTicketId()) {
            ticketService.updateTicket(ticketRequest);
        } else {
            ticketService.insertTicket(ticketRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询券码")
    @GetMapping("/info")
    public Result<TicketContentResponse> queryInfo(@NotNull @RequestParam(name = "ticketId") Integer ticketId) {
        LOG.info("查询券码请求参数ticketId={}", ticketId);
        Result<TicketContentResponse> result = new Result<>();
        TicketContentResponse response =
                ticketService.selectTicketContent(ticketId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询券码列表")
    @PostMapping("/info/list")
    public Result<PageResponse<TicketResponse>> queryInfoList(@RequestBody @Valid TicketListRequest ticketListRequest) {
        LOG.info("查询券码列表请求参数{}", ticketListRequest.toString());
        Result<PageResponse<TicketResponse>> result = new Result<>();
        PageResponse<TicketResponse> response = ticketService.selectTicket(ticketListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
