package com.sc.act.api.service.impl;

import com.sc.act.api.request.AccSepRecordRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.AccSepRecordMapper;
import com.sc.act.api.model.auto.AccSepRecord;
import com.sc.act.api.model.auto.AccSepRecordExample;
import com.sc.act.api.request.AccSepRecordListRequest;
import com.sc.act.api.response.AccSepRecordContentResponse;
import com.sc.act.api.response.AccSepRecordResponse;
import com.sc.act.api.service.AccSepRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:分账流水服务实现类
 *
 * @className:AccSepRecordServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class AccSepRecordServiceImpl implements AccSepRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(AccSepRecordServiceImpl.class);

    @Autowired
    private AccSepRecordMapper accSepRecordMapper;

    @Override
    public void insertAccSepRecord(AccSepRecordRequest accSepRecordRequest) {
        LOG.info("进入创建分账流水服务请求参数{}", accSepRecordRequest.toString());

        //TODO 必要的校验，如去重校验 需要修改

        //TODO 统一入库时间
        Date currentTime = new Date();


        AccSepRecord accSepRecord = new AccSepRecord();
        BeanUtils.copyProperties(accSepRecordRequest, accSepRecord);

        //TODO 必要的逻辑补充，如默认数据状态补充

        accSepRecordMapper.insertSelective(accSepRecord);

    }

    @Override
    public void updateAccSepRecord(AccSepRecordRequest accSepRecordRequest) {
        LOG.info("进入更新分账流水服务请求参数{}", accSepRecordRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        AccSepRecord accSepRecord = new AccSepRecord();
        BeanUtils.copyProperties(accSepRecordRequest, accSepRecord);

        //TODO 必要的逻辑补充，如默认数据状态补充

        accSepRecordMapper.updateByPrimaryKeySelective(accSepRecord);
    }

    @Override
    public AccSepRecordContentResponse selectAccSepRecordContent(Integer accSepRecordId) {
        LOG.info("进入查询分账流水服务请求参数accSepRecordId{}", accSepRecordId);
        AccSepRecordContentResponse accSepRecordContentResponse = new AccSepRecordContentResponse();
        AccSepRecord accSepRecord = accSepRecordMapper.selectByPrimaryKey(accSepRecordId);
        if (null == accSepRecord) {
            return accSepRecordContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(accSepRecord, accSepRecordContentResponse);

        return accSepRecordContentResponse;
    }

    @Override
    public PageResponse<AccSepRecordResponse> selectAccSepRecord(AccSepRecordListRequest accSepRecordListRequest) {
        LOG.info("进入查询分账流水列表服务请求参数{}", accSepRecordListRequest.toString());
        AccSepRecordExample accSepRecordExample = new AccSepRecordExample();
        accSepRecordExample.setOrderByClause("acc_sep_record_id desc");
        AccSepRecordExample.Criteria criteria = accSepRecordExample.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != accSepRecordListRequest.getAccSepRecordId()) {
            criteria.andAccSepRecordIdEqualTo(accSepRecordListRequest.getAccSepRecordId());
        }

        PageHelper.startPage(accSepRecordListRequest.getPageIndex(), accSepRecordListRequest.getPageSize());
        List<AccSepRecord> accSepRecordList = accSepRecordMapper.selectByExample(accSepRecordExample);
        PageSerializable<AccSepRecord> pageInfo = PageSerializable.of(accSepRecordList);
        PageResponse<AccSepRecordResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<AccSepRecordResponse> list = new ArrayList<>();
        response.setList(list);
        accSepRecordList.forEach(accSepRecord -> {
            AccSepRecordResponse accSepRecordResponse = new AccSepRecordResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(accSepRecord, accSepRecordResponse);

            list.add(accSepRecordResponse);
        });
        return response;
    }


}
