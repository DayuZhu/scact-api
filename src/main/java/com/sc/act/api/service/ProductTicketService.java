package com.sc.act.api.service;

import com.sc.act.api.request.ProductTicketRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.ProductTicketListRequest;
import com.sc.act.api.response.ProductTicketContentResponse;
import com.sc.act.api.response.ProductTicketResponse;

/**
 * 功能描述:产品与券码关系服务类
 *
 * @className:ProductTicketService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ProductTicketService {

    /**
     * 新增产品与券码关系
     *
     * @param productTicketRequest
     */
    void insertProductTicket(ProductTicketRequest productTicketRequest);


    /**
     * 更新产品与券码关系
     *
     * @param productTicketRequest
     */
    void updateProductTicket(ProductTicketRequest productTicketRequest);


    /**
     * 查询产品与券码关系
     *
     * @param productTicketId
     * @return
     */
    ProductTicketContentResponse selectProductTicketContent(Integer productTicketId);


    /**
     * 查询产品与券码关系列表
     *
     * @param productTicketListRequest
     * @return
     */
    PageResponse<ProductTicketResponse> selectProductTicket(ProductTicketListRequest productTicketListRequest);


}
