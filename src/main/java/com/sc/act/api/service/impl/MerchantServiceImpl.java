package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.MerchantMapper;
import com.sc.act.api.model.auto.Merchant;
import com.sc.act.api.model.auto.MerchantExample;
import com.sc.act.api.request.MerchantListRequest;
import com.sc.act.api.request.MerchantRequest;
import com.sc.act.api.response.MerchantContentResponse;
import com.sc.act.api.response.MerchantResponse;
import com.sc.act.api.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:商户服务实现类
 *
 * @className:MerchantServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public void insertMerchant(MerchantRequest merchantRequest) {
        LOG.info("进入创建商户服务请求参数{}", merchantRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantRequest, merchant);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantMapper.insertSelective(merchant);

    }

    @Override
    public void updateMerchant(MerchantRequest merchantRequest) {
        LOG.info("进入更新商户服务请求参数{}", merchantRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantRequest, merchant);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantMapper.updateByPrimaryKeySelective(merchant);
    }

    @Override
    public MerchantContentResponse selectMerchantContent(Integer merchantId) {
        LOG.info("进入查询商户服务请求参数merchantId{}", merchantId);
        MerchantContentResponse merchantContentResponse = new MerchantContentResponse();
        Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
        if (null == merchant) {
            return merchantContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(merchant, merchantContentResponse);

        return merchantContentResponse;
    }

    @Override
    public PageResponse<MerchantResponse> selectMerchant(MerchantListRequest merchantListRequest) {
        LOG.info("进入查询商户列表服务请求参数{}", merchantListRequest.toString());
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.setOrderByClause("merchant_id desc");
        MerchantExample.Criteria criteria = merchantExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != merchantListRequest.getMerchantId()) {
            criteria.andMerchantIdEqualTo(merchantListRequest.getMerchantId());
        }

        PageHelper.startPage(merchantListRequest.getPageIndex(), merchantListRequest.getPageSize());
        List<Merchant> merchantList = merchantMapper.selectByExample(merchantExample);
        PageSerializable<Merchant> pageInfo = PageSerializable.of(merchantList);
        PageResponse<MerchantResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<MerchantResponse> list = new ArrayList<>();
        response.setList(list);
        merchantList.forEach(merchant -> {
            MerchantResponse merchantResponse = new MerchantResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(merchant, merchantResponse);

            list.add(merchantResponse);
        });
        return response;
    }


}
