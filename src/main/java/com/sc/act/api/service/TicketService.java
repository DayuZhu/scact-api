package com.sc.act.api.service;

import com.sc.act.api.request.TicketRequest;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.TicketListRequest;
import com.sc.act.api.response.TicketContentResponse;
import com.sc.act.api.response.TicketResponse;

/**
 * 功能描述:券码服务类
 *
 * @className:TicketService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface TicketService {

    /**
     * 新增券码
     *
     * @param ticketRequest
     */
    void insertTicket(TicketRequest ticketRequest);


    /**
     * 更新券码
     *
     * @param ticketRequest
     */
    void updateTicket(TicketRequest ticketRequest);


    /**
     * 查询券码
     *
     * @param ticketId
     * @return
     */
    TicketContentResponse selectTicketContent(Integer ticketId);


    /**
     * 查询券码列表
     *
     * @param ticketListRequest
     * @return
     */
    PageResponse<TicketResponse> selectTicket(TicketListRequest ticketListRequest);


}
