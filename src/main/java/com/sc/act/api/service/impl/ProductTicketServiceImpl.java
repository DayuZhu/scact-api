package com.sc.act.api.service.impl;

import com.sc.act.api.request.ProductTicketRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.ProductTicketMapper;
import com.sc.act.api.model.auto.ProductTicket;
import com.sc.act.api.model.auto.ProductTicketExample;
import com.sc.act.api.request.ProductTicketListRequest;
import com.sc.act.api.response.ProductTicketContentResponse;
import com.sc.act.api.response.ProductTicketResponse;
import com.sc.act.api.service.ProductTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public void insertProductTicket(ProductTicketRequest productTicketRequest) {
        LOG.info("进入创建产品与券码关系服务请求参数{}", productTicketRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ProductTicket productTicket = new ProductTicket();
        BeanUtils.copyProperties(productTicketRequest, productTicket);

        //TODO 必要的逻辑补充，如默认数据状态补充

        productTicketMapper.insertSelective(productTicket);

    }

    @Override
    public void updateProductTicket(ProductTicketRequest productTicketRequest) {
        LOG.info("进入更新产品与券码关系服务请求参数{}", productTicketRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ProductTicket productTicket = new ProductTicket();
        BeanUtils.copyProperties(productTicketRequest, productTicket);

        //TODO 必要的逻辑补充，如默认数据状态补充

        productTicketMapper.updateByPrimaryKeySelective(productTicket);
    }

    @Override
    public ProductTicketContentResponse selectProductTicketContent(Integer productTicketId) {
        LOG.info("进入查询产品与券码关系服务请求参数productTicketId{}", productTicketId);
        ProductTicketContentResponse productTicketContentResponse = new ProductTicketContentResponse();
        ProductTicket productTicket = productTicketMapper.selectByPrimaryKey(productTicketId);
        if (null == productTicket) {
            return productTicketContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(productTicket, productTicketContentResponse);

        return productTicketContentResponse;
    }

    @Override
    public PageResponse<ProductTicketResponse> selectProductTicket(ProductTicketListRequest productTicketListRequest) {
        LOG.info("进入查询产品与券码关系列表服务请求参数{}", productTicketListRequest.toString());
        ProductTicketExample productTicketExample = new ProductTicketExample();
        productTicketExample.setOrderByClause("product_ticket_id desc");
        ProductTicketExample.Criteria criteria = productTicketExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != productTicketListRequest.getProductTicketId()) {
            criteria.andProductTicketIdEqualTo(productTicketListRequest.getProductTicketId());
        }

        PageHelper.startPage(productTicketListRequest.getPageIndex(), productTicketListRequest.getPageSize());
        List<ProductTicket> productTicketList = productTicketMapper.selectByExample(productTicketExample);
        PageSerializable<ProductTicket> pageInfo = PageSerializable.of(productTicketList);
        PageResponse<ProductTicketResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<ProductTicketResponse> list = new ArrayList<>();
        response.setList(list);
        productTicketList.forEach(productTicket -> {
            ProductTicketResponse productTicketResponse = new ProductTicketResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(productTicket, productTicketResponse);

            list.add(productTicketResponse);
        });
        return response;
    }


}
