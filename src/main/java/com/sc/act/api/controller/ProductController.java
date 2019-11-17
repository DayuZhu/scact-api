package com.sc.act.api.controller;

import com.sc.act.api.request.ProductRequest;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.ProductListRequest;
import com.sc.act.api.response.ProductContentResponse;
import com.sc.act.api.response.ProductResponse;
import com.sc.act.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: 产品控制类
 *
 * @className:ProductController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/product")
@Api(value = "产品控制类", tags = "产品控制类")
public class ProductController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation("创建或更新产品")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ProductRequest productRequest) {
        LOG.info("创建或更新产品请求参数{}", productRequest.toString());
        Result result = new Result();

        if (null != productRequest.getProductId()) {
            productService.updateProduct(productRequest);
        } else {
            productService.insertProduct(productRequest);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询产品")
    @GetMapping("/info")
    public Result<ProductContentResponse> queryInfo(@NotNull @RequestParam(name = "productId") Integer productId) {
        LOG.info("查询产品请求参数productId={}", productId);
        Result<ProductContentResponse> result = new Result<>();
        ProductContentResponse response =
                productService.selectProductContent(productId);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询产品列表")
    @PostMapping("/info/list")
    public Result<PageResponse<ProductResponse>> queryInfoList(@RequestBody @Valid ProductListRequest productListRequest) {
        LOG.info("查询产品列表请求参数{}", productListRequest.toString());
        Result<PageResponse<ProductResponse>> result = new Result<>();
        PageResponse<ProductResponse> response = productService.selectProduct(productListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
