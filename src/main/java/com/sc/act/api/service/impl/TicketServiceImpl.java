package com.sc.act.api.service.impl;

import com.sc.act.api.request.TicketRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.TicketMapper;
import com.sc.act.api.model.auto.Ticket;
import com.sc.act.api.model.auto.TicketExample;
import com.sc.act.api.request.TicketListRequest;
import com.sc.act.api.response.TicketContentResponse;
import com.sc.act.api.response.TicketResponse;
import com.sc.act.api.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:券码服务实现类
 *
 * @className:TicketServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void insertTicket(TicketRequest ticketRequest) {
        LOG.info("进入创建券码服务请求参数{}", ticketRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketRequest, ticket);

        //TODO 必要的逻辑补充，如默认数据状态补充

        ticketMapper.insertSelective(ticket);

    }

    @Override
    public void updateTicket(TicketRequest ticketRequest) {
        LOG.info("进入更新券码服务请求参数{}", ticketRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketRequest, ticket);

        //TODO 必要的逻辑补充，如默认数据状态补充

        ticketMapper.updateByPrimaryKeySelective(ticket);
    }

    @Override
    public TicketContentResponse selectTicketContent(Integer ticketId) {
        LOG.info("进入查询券码服务请求参数ticketId{}", ticketId);
        TicketContentResponse ticketContentResponse = new TicketContentResponse();
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        if (null == ticket) {
            return ticketContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(ticket, ticketContentResponse);

        return ticketContentResponse;
    }

    @Override
    public PageResponse<TicketResponse> selectTicket(TicketListRequest ticketListRequest) {
        LOG.info("进入查询券码列表服务请求参数{}", ticketListRequest.toString());
        TicketExample ticketExample = new TicketExample();
        ticketExample.setOrderByClause("ticket_id desc");
        TicketExample.Criteria criteria = ticketExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != ticketListRequest.getTicketId()) {
            criteria.andTicketIdEqualTo(ticketListRequest.getTicketId());
        }

        PageHelper.startPage(ticketListRequest.getPageIndex(), ticketListRequest.getPageSize());
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        PageSerializable<Ticket> pageInfo = PageSerializable.of(ticketList);
        PageResponse<TicketResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<TicketResponse> list = new ArrayList<>();
        response.setList(list);
        ticketList.forEach(ticket -> {
            TicketResponse ticketResponse = new TicketResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(ticket, ticketResponse);

            list.add(ticketResponse);
        });
        return response;
    }


}
