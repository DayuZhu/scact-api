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
    public void insertBankInfo(BankInfoRequest bankInfoRequest) {
        LOG.info("进入创建银行信息服务请求参数{}", bankInfoRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        BankInfo bankInfo = new BankInfo();
        BeanUtils.copyProperties(bankInfoRequest, bankInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        bankInfoMapper.insertSelective(bankInfo);

    }

    @Override
    public void updateBankInfo(BankInfoRequest bankInfoRequest) {
        LOG.info("进入更新银行信息服务请求参数{}", bankInfoRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        BankInfo bankInfo = new BankInfo();
        BeanUtils.copyProperties(bankInfoRequest, bankInfo);

        //TODO 必要的逻辑补充，如默认数据状态补充

        bankInfoMapper.updateByPrimaryKeySelective(bankInfo);
    }

    @Override
    public BankInfoContentResponse selectBankInfoContent(Integer bankInfoId) {
        LOG.info("进入查询银行信息服务请求参数bankInfoId{}", bankInfoId);
        BankInfoContentResponse bankInfoContentResponse = new BankInfoContentResponse();
        BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(bankInfoId);
        if (null == bankInfo) {
            return bankInfoContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(bankInfo, bankInfoContentResponse);

        return bankInfoContentResponse;
    }

    @Override
    public PageResponse<BankInfoResponse> selectBankInfo(BankInfoListRequest bankInfoListRequest) {
        LOG.info("进入查询银行信息列表服务请求参数{}", bankInfoListRequest.toString());
        BankInfoExample bankInfoExample = new BankInfoExample();
        bankInfoExample.setOrderByClause("bank_info_id desc");
        BankInfoExample.Criteria criteria = bankInfoExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != bankInfoListRequest.getBankInfoId()) {
            criteria.andBankInfoIdEqualTo(bankInfoListRequest.getBankInfoId());
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

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(bankInfo, bankInfoResponse);

            list.add(bankInfoResponse);
        });
        return response;
    }


}
