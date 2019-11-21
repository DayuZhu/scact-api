package com.sc.act.api.service;

import com.sc.act.api.model.auto.Ticket;

import java.util.List;

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
     * 查询产品与券码关系
     *
     * @param outProductId
     * @return
     */
    List<Ticket> selectProductTicketContent(Integer outProductId,Integer outOrderId);


}
