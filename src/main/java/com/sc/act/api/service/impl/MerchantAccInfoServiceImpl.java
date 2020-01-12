package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.MerchantAccInfoMapper;
import com.sc.act.api.model.auto.MerchantAccInfo;
import com.sc.act.api.model.auto.MerchantAccInfoExample;
import com.sc.act.api.request.MerchantAccInfoListRequest;
import com.sc.act.api.request.MerchantAccInfoRequest;
import com.sc.act.api.response.MerchantAccInfoContentResponse;
import com.sc.act.api.response.MerchantAccInfoResponse;
import com.sc.act.api.service.MerchantAccInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:商户账户信息服务实现类
 *
 * @className:MerchantAccInfoServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@Service
public class MerchantAccInfoServiceImpl implements MerchantAccInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccInfoServiceImpl.class);

    @Autowired
    private MerchantAccInfoMapper merchantAccInfoMapper;

    @Override
    public void insertMerchantAccInfo(MerchantAccInfoRequest merchantAccInfoRequest) {
        LOG.info("进入创建商户账户信息服务请求参数{}", merchantAccInfoRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccInfo merchantAccInfo = new MerchantAccInfo();
        BeanUtils.copyProperties(merchantAccInfoRequest, merchantAccInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccInfoMapper.insertSelective(merchantAccInfo);

    }

    @Override
    public void updateMerchantAccInfo(MerchantAccInfoRequest merchantAccInfoRequest) {
        LOG.info("进入更新商户账户信息服务请求参数{}", merchantAccInfoRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccInfo merchantAccInfo = new MerchantAccInfo();
        BeanUtils.copyProperties(merchantAccInfoRequest, merchantAccInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccInfoMapper.updateByPrimaryKeySelective(merchantAccInfo);
    }

    @Override
    public MerchantAccInfoContentResponse selectMerchantAccInfoContent(Integer merchantAccInfoId) {
        LOG.info("进入查询商户账户信息服务请求参数merchantAccInfoId{}", merchantAccInfoId);
        MerchantAccInfoContentResponse merchantAccInfoContentResponse = new MerchantAccInfoContentResponse();
        MerchantAccInfo merchantAccInfo = merchantAccInfoMapper.selectByPrimaryKey(merchantAccInfoId);
        if (null == merchantAccInfo) {
            return merchantAccInfoContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(merchantAccInfo, merchantAccInfoContentResponse);

        return merchantAccInfoContentResponse;
    }

    @Override
    public PageResponse<MerchantAccInfoResponse> selectMerchantAccInfo(MerchantAccInfoListRequest merchantAccInfoListRequest) {
        LOG.info("进入查询商户账户信息列表服务请求参数{}", merchantAccInfoListRequest.toString());
        MerchantAccInfoExample merchantAccInfoExample = new MerchantAccInfoExample();
        merchantAccInfoExample.setOrderByClause("merchant_acc_info_id desc");
        MerchantAccInfoExample.Criteria criteria = merchantAccInfoExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != merchantAccInfoListRequest.getMerchantAccInfoId()) {
            criteria.andMerchantAccInfoIdEqualTo(merchantAccInfoListRequest.getMerchantAccInfoId());
        }

        PageHelper.startPage(merchantAccInfoListRequest.getPageIndex(), merchantAccInfoListRequest.getPageSize());
        List<MerchantAccInfo> merchantAccInfoList = merchantAccInfoMapper.selectByExample(merchantAccInfoExample);
        PageSerializable<MerchantAccInfo> pageInfo = PageSerializable.of(merchantAccInfoList);
        PageResponse<MerchantAccInfoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<MerchantAccInfoResponse> list = new ArrayList<>();
        response.setList(list);
        merchantAccInfoList.forEach(merchantAccInfo -> {
            MerchantAccInfoResponse merchantAccInfoResponse = new MerchantAccInfoResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(merchantAccInfo, merchantAccInfoResponse);

            list.add(merchantAccInfoResponse);
        });
        return response;
    }


}
