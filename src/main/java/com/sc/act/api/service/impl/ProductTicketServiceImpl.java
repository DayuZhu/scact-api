package com.sc.act.api.service.impl;

import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.mapper.auto.ProductMapper;
import com.sc.act.api.mapper.auto.ProductTicketMapper;
import com.sc.act.api.mapper.auto.TicketMapper;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.response.ProductTicketContentResponse;
import com.sc.act.api.service.ProductTicketService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述:产品与券码关系服务实现类
 *
 * @className:ProductTicketServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ProductTicketServiceImpl implements ProductTicketService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductTicketServiceImpl.class);

    @Autowired
    private ProductTicketMapper productTicketMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private TicketMapper ticketMapper;


    @Override
    public List<Ticket> selectProductTicketContent(Integer outProductId) {
        LOG.info("进入查询券明细信息服务请求参数outProductId{}", outProductId);
        ProductTicketContentResponse productTicketContentResponse = new ProductTicketContentResponse();

        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andOutProductIdEqualTo(outProductId);
        List<Product> products = productMapper.selectByExample(productExample);
        if (CollectionUtils.isEmpty(products)) {
            LOG.error("进入查询券明细信息服务产品不存在请求参数outProductId{}", outProductId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_ISNOT_EXIST);
        }

        ProductTicketExample productTicketExample = new ProductTicketExample();
        productTicketExample.createCriteria().andProductIdEqualTo(products.get(0).getProductId());
        List<ProductTicket> productTickets = productTicketMapper.selectByExample(productTicketExample);
        if (CollectionUtils.isEmpty(productTickets)) {
            LOG.error("进入查询券明细信息服务产品对应券不存在请求参数outProductId{}", outProductId);
            throw new BaseRuntimeException(ResultEnum.PRODUCT_TICKET_ISNOT_EXIST);
        }

        List<Integer> tickeIds = productTickets.stream().map(ProductTicket::getTicketId).collect(Collectors.toList());

        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketIdIn(tickeIds);
        return ticketMapper.selectByExample(ticketExample);
    }


}
