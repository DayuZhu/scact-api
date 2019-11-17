package com.sc.act.api.service;

import com.sc.act.api.request.ProductRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.ProductListRequest;
import com.sc.act.api.response.ProductContentResponse;
import com.sc.act.api.response.ProductResponse;

/**
 * 功能描述:产品服务类
 *
 * @className:ProductService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ProductService {

    /**
     * 新增产品
     *
     * @param productRequest
     */
    void insertProduct(ProductRequest productRequest);


    /**
     * 更新产品
     *
     * @param productRequest
     */
    void updateProduct(ProductRequest productRequest);


    /**
     * 查询产品
     *
     * @param productId
     * @return
     */
    ProductContentResponse selectProductContent(Integer productId);


    /**
     * 查询产品列表
     *
     * @param productListRequest
     * @return
     */
    PageResponse<ProductResponse> selectProduct(ProductListRequest productListRequest);


}
