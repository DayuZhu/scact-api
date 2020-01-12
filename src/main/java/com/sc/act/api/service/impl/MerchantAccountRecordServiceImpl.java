package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.MerchantAccountRecordMapper;
import com.sc.act.api.model.auto.MerchantAccountRecord;
import com.sc.act.api.model.auto.MerchantAccountRecordExample;
import com.sc.act.api.request.MerchantAccountRecordListRequest;
import com.sc.act.api.request.MerchantAccountRecordRequest;
import com.sc.act.api.response.MerchantAccountRecordContentResponse;
import com.sc.act.api.response.MerchantAccountRecordResponse;
import com.sc.act.api.service.MerchantAccountRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:商户资金记录服务实现类
 *
 * @className:MerchantAccountRecordServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@Service
public class MerchantAccountRecordServiceImpl implements MerchantAccountRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccountRecordServiceImpl.class);

    @Autowired
    private MerchantAccountRecordMapper merchantAccountRecordMapper;

    @Override
    public void insertMerchantAccountRecord(MerchantAccountRecordRequest merchantAccountRecordRequest) {
        LOG.info("进入创建商户资金记录服务请求参数{}", merchantAccountRecordRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccountRecord merchantAccountRecord = new MerchantAccountRecord();
        BeanUtils.copyProperties(merchantAccountRecordRequest, merchantAccountRecord);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccountRecordMapper.insertSelective(merchantAccountRecord);

    }

    @Override
    public void updateMerchantAccountRecord(MerchantAccountRecordRequest merchantAccountRecordRequest) {
        LOG.info("进入更新商户资金记录服务请求参数{}", merchantAccountRecordRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccountRecord merchantAccountRecord = new MerchantAccountRecord();
        BeanUtils.copyProperties(merchantAccountRecordRequest, merchantAccountRecord);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccountRecordMapper.updateByPrimaryKeySelective(merchantAccountRecord);
    }

    @Override
    public MerchantAccountRecordContentResponse selectMerchantAccountRecordContent(Integer merchantAccountRecordId) {
        LOG.info("进入查询商户资金记录服务请求参数merchantAccountRecordId{}", merchantAccountRecordId);
        MerchantAccountRecordContentResponse merchantAccountRecordContentResponse = new MerchantAccountRecordContentResponse();
        MerchantAccountRecord merchantAccountRecord = merchantAccountRecordMapper.selectByPrimaryKey(merchantAccountRecordId);
        if (null == merchantAccountRecord) {
            return merchantAccountRecordContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(merchantAccountRecord, merchantAccountRecordContentResponse);

        return merchantAccountRecordContentResponse;
    }

    @Override
    public PageResponse<MerchantAccountRecordResponse> selectMerchantAccountRecord(MerchantAccountRecordListRequest merchantAccountRecordListRequest) {
        LOG.info("进入查询商户资金记录列表服务请求参数{}", merchantAccountRecordListRequest.toString());
        MerchantAccountRecordExample merchantAccountRecordExample = new MerchantAccountRecordExample();
        merchantAccountRecordExample.setOrderByClause("merchant_account_record_id desc");
        MerchantAccountRecordExample.Criteria criteria = merchantAccountRecordExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != merchantAccountRecordListRequest.getMerchantAccountRecordId()) {
            criteria.andMerchantAccountRecordIdEqualTo(merchantAccountRecordListRequest.getMerchantAccountRecordId());
        }

        PageHelper.startPage(merchantAccountRecordListRequest.getPageIndex(), merchantAccountRecordListRequest.getPageSize());
        List<MerchantAccountRecord> merchantAccountRecordList = merchantAccountRecordMapper.selectByExample(merchantAccountRecordExample);
        PageSerializable<MerchantAccountRecord> pageInfo = PageSerializable.of(merchantAccountRecordList);
        PageResponse<MerchantAccountRecordResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<MerchantAccountRecordResponse> list = new ArrayList<>();
        response.setList(list);
        merchantAccountRecordList.forEach(merchantAccountRecord -> {
            MerchantAccountRecordResponse merchantAccountRecordResponse = new MerchantAccountRecordResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(merchantAccountRecord, merchantAccountRecordResponse);

            list.add(merchantAccountRecordResponse);
        });
        return response;
    }


}
