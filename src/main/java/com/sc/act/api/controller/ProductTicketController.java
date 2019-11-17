package com.sc.act.api.controller;

import com.sc.act.api.request.ProductTicketRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.ProductTicketListRequest;
import com.sc.act.api.response.ProductTicketContentResponse;
import com.sc.act.api.response.ProductTicketResponse;
import com.sc.act.api.service.ProductTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 产品与券码关系控制类
 *
 * @className:ProductTicketController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/product/ticket")
@Api(value = "产品与券码关系控制类", tags = "产品与券码关系控制类")
public class ProductTicketController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductTicketController.class);

    @Autowired
    private ProductTicketService productTicketService;

    @ApiOperation("创建或更新产品与券码关系")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ProductTicketRequest productTicketRequest) {
        LOG.info("创建或更新产品与券码关系请求参数{}", productTicketRequest.toString());
        Result result = new Result();

        if (null != productTicketRequest.getProductTicketId()) {
            productTicketService.updateProductTicket(productTicketRequest);
        } else {
            productTicketService.insertProductTicket(productTicketRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询产品与券码关系")
    @GetMapping("/info")
    public Result<ProductTicketContentResponse> queryInfo(@NotNull @RequestParam(name = "productTicketId") Integer productTicketId) {
        LOG.info("查询产品与券码关系请求参数productTicketId={}", productTicketId);
        Result<ProductTicketContentResponse> result = new Result<>();
        ProductTicketContentResponse response =
                productTicketService.selectProductTicketContent(productTicketId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询产品与券码关系列表")
    @PostMapping("/info/list")
    public Result<PageResponse<ProductTicketResponse>> queryInfoList(@RequestBody @Valid ProductTicketListRequest productTicketListRequest) {
        LOG.info("查询产品与券码关系列表请求参数{}", productTicketListRequest.toString());
        Result<PageResponse<ProductTicketResponse>> result = new Result<>();
        PageResponse<ProductTicketResponse> response = productTicketService.selectProductTicket(productTicketListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
