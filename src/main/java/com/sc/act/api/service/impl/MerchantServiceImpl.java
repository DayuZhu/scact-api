package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.commons.web.util.StringUtil;
import com.sc.act.api.mapper.auto.BankInfoMapper;
import com.sc.act.api.mapper.auto.MerchantAccInfoMapper;
import com.sc.act.api.mapper.auto.MerchantAccountMapper;
import com.sc.act.api.mapper.auto.MerchantMapper;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.request.MerchantListRequest;
import com.sc.act.api.request.MerchantRequest;
import com.sc.act.api.response.*;
import com.sc.act.api.service.MerchantService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MerchantAccInfoMapper merchantAccInfoMapper;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Autowired
    private BankInfoMapper bankInfoMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertMerchant(MerchantRequest merchantRequest) {
        LOG.info("进入创建商户服务请求参数{}", merchantRequest.toString());

        if (!StringUtil.isTelNum(String.valueOf(merchantRequest.getMobile()))) {
            LOG.error("进入创建商户服务手机号不正确请求参数{}", merchantRequest.toString());
            throw new BaseRuntimeException(ResultEnum.MERCHANT_TEL_ERROR);
        }

        Date currentTime = new Date();

        //创建商户
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantRequest.getMerchantName());
        merchant.setSocialCode(merchantRequest.getSocialCode());
        merchant.setName(merchantRequest.getName());
        merchant.setMobile(merchantRequest.getMobile());
        merchant.setAddress(merchantRequest.getAddress());
        merchant.setBossName(merchantRequest.getBossName());
        merchant.setBossTel(merchantRequest.getBossTel());
        merchant.setCreateUserId(merchantRequest.getCreateUserId());
        merchant.setCreateUserName(merchantRequest.getCreateUserName());
        merchant.setUpdateUserId(merchantRequest.getUpdateUserId());
        merchant.setUpdateUserName(merchantRequest.getUpdateUserName());
        merchant.setCreateTime(currentTime);
        merchant.setUpdateTime(currentTime);
        merchantMapper.insertSelective(merchant);

        //创建商户
        MerchantAccInfo merchantAccInfo = new MerchantAccInfo();
        merchantAccInfo.setMerchantId(merchant.getMerchantId());
        merchantAccInfo.setAccName(merchantRequest.getName());
        merchantAccInfo.setMerchantBank(merchantRequest.getMerchantBank());
        merchantAccInfo.setMerchantCard(merchantRequest.getMerchantCard());
        merchantAccInfo.setMobile(merchantRequest.getMobile());
        merchantAccInfo.setState(1);
        merchantAccInfo.setCreateTime(currentTime);
        merchantAccInfo.setUpdateTime(currentTime);
        merchantAccInfo.setBankInfoId(merchantRequest.getBankInfoId());
        merchantAccInfoMapper.insertSelective(merchantAccInfo);

        //录入商户余额
        MerchantAccount merchantAccount = new MerchantAccount();
        merchantAccount.setMerchantId(merchant.getMerchantId());
        merchantAccount.setBalance(merchantRequest.getBalance());
        merchantAccount.setState(1);
        merchantAccount.setCreateTime(currentTime);
        merchantAccount.setUpdateTime(currentTime);
        merchantAccountMapper.insertSelective(merchantAccount);

    }

    @Override
    public void updateMerchant(MerchantRequest merchantRequest) {
        LOG.info("进入更新商户服务请求参数{}", merchantRequest.toString());

    }

    @Override
    public MerchantContentResponse selectMerchantContent(Integer merchantId) {
        LOG.info("进入查询商户服务请求参数merchantId{}", merchantId);
        MerchantContentResponse merchantContentResponse = new MerchantContentResponse();
        Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
        if (null == merchant) {
            return merchantContentResponse;
        }
        BeanUtils.copyProperties(merchant, merchantContentResponse);

        MerchantAccInfoExample merchantAccInfoExample = new MerchantAccInfoExample();
        MerchantAccInfoExample.Criteria merchantAccInfoCriteria = merchantAccInfoExample.createCriteria();
        merchantAccInfoCriteria.andMerchantIdEqualTo(merchantId);
        List<MerchantAccInfo> merchantAccInfos = merchantAccInfoMapper.selectByExample(merchantAccInfoExample);
        if (CollectionUtils.isNotEmpty(merchantAccInfos)) {
            MerchantAccInfo merchantAccInfo = merchantAccInfos.get(0);
            MerchantAccInfoResponse merchantAccInfoResponse = new MerchantAccInfoResponse();
            BeanUtils.copyProperties(merchantAccInfo, merchantAccInfoResponse);
            merchantContentResponse.setMerchantAccInfoResponse(merchantAccInfoResponse);

            BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(merchantAccInfo.getBankInfoId());
            if (null != bankInfo) {
                BankInfoResponse bankInfoResponse = new BankInfoResponse();
                BeanUtils.copyProperties(bankInfo, bankInfoResponse);
                merchantContentResponse.setBankInfoResponse(bankInfoResponse);
            }
        }

        MerchantAccountExample merchantAccountExample = new MerchantAccountExample();
        MerchantAccountExample.Criteria merchantAccountCriteria = merchantAccountExample.createCriteria();
        merchantAccountCriteria.andMerchantIdEqualTo(merchantId);
        List<MerchantAccount> merchantAccounts = merchantAccountMapper.selectByExample(merchantAccountExample);
        if (CollectionUtils.isNotEmpty(merchantAccounts)) {
            MerchantAccount merchantAccount = merchantAccounts.get(0);
            MerchantAccountResponse merchantAccountResponse = new MerchantAccountResponse();
            BeanUtils.copyProperties(merchantAccount, merchantAccountResponse);
            merchantContentResponse.setMerchantAccountResponse(merchantAccountResponse);
        }


        return merchantContentResponse;
    }

    @Override
    public PageResponse<MerchantResponse> selectMerchant(MerchantListRequest merchantListRequest) {
        LOG.info("进入查询商户列表服务请求参数{}", merchantListRequest.toString());
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.setOrderByClause("merchant_id desc");
        MerchantExample.Criteria criteria = merchantExample.createCriteria();

        if (null != merchantListRequest.getMerchantId()) {
            criteria.andMerchantIdEqualTo(merchantListRequest.getMerchantId());
        }

        if (StringUtils.isNotBlank(merchantListRequest.getMerchantName())) {
            criteria.andMerchantNameLike(merchantListRequest.getMerchantName() + "%");
        }

        if (null != merchantListRequest.getMobile()) {
            criteria.andMobileEqualTo(merchantListRequest.getMobile());
        }

        if (StringUtils.isNotBlank(merchantListRequest.getName())) {
            criteria.andNameLike(merchantListRequest.getName() + "%");
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
            BeanUtils.copyProperties(merchant, merchantResponse);

            MerchantAccInfoExample merchantAccInfoExample = new MerchantAccInfoExample();
            MerchantAccInfoExample.Criteria merchantAccInfoCriteria = merchantAccInfoExample.createCriteria();
            merchantAccInfoCriteria.andMerchantIdEqualTo(merchant.getMerchantId());
            List<MerchantAccInfo> merchantAccInfos = merchantAccInfoMapper.selectByExample(merchantAccInfoExample);
            if (CollectionUtils.isNotEmpty(merchantAccInfos)) {
                MerchantAccInfoResponse merchantAccInfoResponse = new MerchantAccInfoResponse();
                BeanUtils.copyProperties(merchantAccInfos.get(0), merchantAccInfoResponse);
                merchantResponse.setMerchantAccInfoResponse(merchantAccInfoResponse);

                BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(merchantAccInfos.get(0).getBankInfoId());
                if (null != bankInfo) {
                    BankInfoResponse bankInfoResponse = new BankInfoResponse();
                    BeanUtils.copyProperties(bankInfo, bankInfoResponse);
                    merchantResponse.setBankInfoResponse(bankInfoResponse);
                }
            }

            MerchantAccountExample merchantAccountExample = new MerchantAccountExample();
            MerchantAccountExample.Criteria merchantAccountCriteria = merchantAccountExample.createCriteria();
            merchantAccountCriteria.andMerchantIdEqualTo(merchant.getMerchantId());
            List<MerchantAccount> merchantAccounts = merchantAccountMapper.selectByExample(merchantAccountExample);
            if (CollectionUtils.isNotEmpty(merchantAccounts)) {
                MerchantAccountResponse merchantAccountResponse = new MerchantAccountResponse();
                BeanUtils.copyProperties(merchantAccounts.get(0), merchantAccountResponse);
                merchantResponse.setMerchantAccountResponse(merchantAccountResponse);
            }

            list.add(merchantResponse);
        });
        return response;
    }


}
