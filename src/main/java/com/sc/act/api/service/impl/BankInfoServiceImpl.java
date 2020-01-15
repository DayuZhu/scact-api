package com.sc.act.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.BankInfoMapper;
import com.sc.act.api.model.auto.BankInfo;
import com.sc.act.api.model.auto.BankInfoExample;
import com.sc.act.api.request.BankInfoListRequest;
import com.sc.act.api.request.BankInfoRequest;
import com.sc.act.api.response.BankInfoContentResponse;
import com.sc.act.api.response.BankInfoResponse;
import com.sc.act.api.service.BankInfoService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:银行信息服务实现类
 *
 * @className:BankInfoServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-15 19:27:45
 */
@Service
public class BankInfoServiceImpl implements BankInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(BankInfoServiceImpl.class);

    @Autowired
    private BankInfoMapper bankInfoMapper;


    @Override
    public BankInfoContentResponse selectBankInfoContent(Integer bankInfoId) {
        LOG.info("进入查询银行信息服务请求参数bankInfoId{}", bankInfoId);
        BankInfoContentResponse bankInfoContentResponse = new BankInfoContentResponse();
        BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(bankInfoId);
        if (null == bankInfo) {
            return bankInfoContentResponse;
        }
        BeanUtils.copyProperties(bankInfo, bankInfoContentResponse);

        return bankInfoContentResponse;
    }

    @Override
    public PageResponse<BankInfoResponse> selectBankInfo(BankInfoListRequest bankInfoListRequest) {
        LOG.info("进入查询银行信息列表服务请求参数{}", bankInfoListRequest.toString());
        BankInfoExample bankInfoExample = new BankInfoExample();
        bankInfoExample.setOrderByClause("bank_info_id desc");
        BankInfoExample.Criteria criteria = bankInfoExample.createCriteria();

        if (null != bankInfoListRequest.getBankInfoId()) {
            criteria.andBankInfoIdEqualTo(bankInfoListRequest.getBankInfoId());
        }

        if (StringUtils.isNotBlank(bankInfoListRequest.getBankName())) {
            criteria.andBankNameLike(bankInfoListRequest.getBankName() + "%");
        }

        if (StringUtils.isNotBlank(bankInfoListRequest.getProvinceName())) {
            criteria.andProvinceNameLike(bankInfoListRequest.getProvinceName() + "%");
        }
        if (StringUtils.isNotBlank(bankInfoListRequest.getCityName())) {
            criteria.andCityNameLike(bankInfoListRequest.getCityName() + "%");
        }

        if (StringUtils.isNotBlank(bankInfoListRequest.getBankSubCode())) {
            criteria.andBankSubCodeEqualTo(bankInfoListRequest.getBankSubCode());
        }

        if (StringUtils.isNotBlank(bankInfoListRequest.getBankSubName())) {
            criteria.andBankSubNameLike(bankInfoListRequest.getBankSubName() + "%");
        }


        PageHelper.startPage(bankInfoListRequest.getPageIndex(), bankInfoListRequest.getPageSize());
        List<BankInfo> bankInfoList = bankInfoMapper.selectByExample(bankInfoExample);
        PageSerializable<BankInfo> pageInfo = PageSerializable.of(bankInfoList);
        PageResponse<BankInfoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<BankInfoResponse> list = new ArrayList<>();
        response.setList(list);
        bankInfoList.forEach(bankInfo -> {
            BankInfoResponse bankInfoResponse = new BankInfoResponse();
            BeanUtils.copyProperties(bankInfo, bankInfoResponse);

            list.add(bankInfoResponse);
        });
        return response;
    }


}
