package com.sc.act.api.service.impl;

import com.sc.act.api.request.ProductRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.ProductMapper;
import com.sc.act.api.model.auto.Product;
import com.sc.act.api.model.auto.ProductExample;
import com.sc.act.api.request.ProductListRequest;
import com.sc.act.api.response.ProductContentResponse;
import com.sc.act.api.response.ProductResponse;
import com.sc.act.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:产品服务实现类
 *
 * @className:ProductServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insertProduct(ProductRequest productRequest) {
        LOG.info("进入创建产品服务请求参数{}", productRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);

        //TODO 必要的逻辑补充，如默认数据状态补充

        productMapper.insertSelective(product);

    }

    @Override
    public void updateProduct(ProductRequest productRequest) {
        LOG.info("进入更新产品服务请求参数{}", productRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);

        //TODO 必要的逻辑补充，如默认数据状态补充

        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public ProductContentResponse selectProductContent(Integer productId) {
        LOG.info("进入查询产品服务请求参数productId{}", productId);
        ProductContentResponse productContentResponse = new ProductContentResponse();
        Product product = productMapper.selectByPrimaryKey(productId);
        if (null == product) {
            return productContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(product, productContentResponse);

        return productContentResponse;
    }

    @Override
    public PageResponse<ProductResponse> selectProduct(ProductListRequest productListRequest) {
        LOG.info("进入查询产品列表服务请求参数{}", productListRequest.toString());
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("product_id desc");
        ProductExample.Criteria criteria = productExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != productListRequest.getProductId()) {
            criteria.andProductIdEqualTo(productListRequest.getProductId());
        }

        PageHelper.startPage(productListRequest.getPageIndex(), productListRequest.getPageSize());
        List<Product> productList = productMapper.selectByExample(productExample);
        PageSerializable<Product> pageInfo = PageSerializable.of(productList);
        PageResponse<ProductResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<ProductResponse> list = new ArrayList<>();
        response.setList(list);
        productList.forEach(product -> {
            ProductResponse productResponse = new ProductResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(product, productResponse);

            list.add(productResponse);
        });
        return response;
    }


}
