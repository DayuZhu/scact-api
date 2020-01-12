package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.MerchantAccountMapper;
import com.sc.act.api.model.auto.MerchantAccount;
import com.sc.act.api.model.auto.MerchantAccountExample;
import com.sc.act.api.request.MerchantAccountListRequest;
import com.sc.act.api.request.MerchantAccountRequest;
import com.sc.act.api.response.MerchantAccountContentResponse;
import com.sc.act.api.response.MerchantAccountResponse;
import com.sc.act.api.service.MerchantAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:商户资金信息服务实现类
 *
 * @className:MerchantAccountServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@Service
public class MerchantAccountServiceImpl implements MerchantAccountService {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantAccountServiceImpl.class);

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Override
    public void insertMerchantAccount(MerchantAccountRequest merchantAccountRequest) {
        LOG.info("进入创建商户资金信息服务请求参数{}", merchantAccountRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccount merchantAccount = new MerchantAccount();
        BeanUtils.copyProperties(merchantAccountRequest, merchantAccount);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccountMapper.insertSelective(merchantAccount);

    }

    @Override
    public void updateMerchantAccount(MerchantAccountRequest merchantAccountRequest) {
        LOG.info("进入更新商户资金信息服务请求参数{}", merchantAccountRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        MerchantAccount merchantAccount = new MerchantAccount();
        BeanUtils.copyProperties(merchantAccountRequest, merchantAccount);

        //TODO 必要的逻辑补充，如默认数据状态补充

        merchantAccountMapper.updateByPrimaryKeySelective(merchantAccount);
    }

    @Override
    public MerchantAccountContentResponse selectMerchantAccountContent(Integer merchantAccountId) {
        LOG.info("进入查询商户资金信息服务请求参数merchantAccountId{}", merchantAccountId);
        MerchantAccountContentResponse merchantAccountContentResponse = new MerchantAccountContentResponse();
        MerchantAccount merchantAccount = merchantAccountMapper.selectByPrimaryKey(merchantAccountId);
        if (null == merchantAccount) {
            return merchantAccountContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(merchantAccount, merchantAccountContentResponse);

        return merchantAccountContentResponse;
    }

    @Override
    public PageResponse<MerchantAccountResponse> selectMerchantAccount(MerchantAccountListRequest merchantAccountListRequest) {
        LOG.info("进入查询商户资金信息列表服务请求参数{}", merchantAccountListRequest.toString());
        MerchantAccountExample merchantAccountExample = new MerchantAccountExample();
        merchantAccountExample.setOrderByClause("merchant_account_id desc");
        MerchantAccountExample.Criteria criteria = merchantAccountExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != merchantAccountListRequest.getMerchantAccountId()) {
            criteria.andMerchantAccountIdEqualTo(merchantAccountListRequest.getMerchantAccountId());
        }

        PageHelper.startPage(merchantAccountListRequest.getPageIndex(), merchantAccountListRequest.getPageSize());
        List<MerchantAccount> merchantAccountList = merchantAccountMapper.selectByExample(merchantAccountExample);
        PageSerializable<MerchantAccount> pageInfo = PageSerializable.of(merchantAccountList);
        PageResponse<MerchantAccountResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<MerchantAccountResponse> list = new ArrayList<>();
        response.setList(list);
        merchantAccountList.forEach(merchantAccount -> {
            MerchantAccountResponse merchantAccountResponse = new MerchantAccountResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(merchantAccount, merchantAccountResponse);

            list.add(merchantAccountResponse);
        });
        return response;
    }


}
