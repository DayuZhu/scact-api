package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.mapper.auto.*;
import com.sc.act.api.model.auto.*;
import com.sc.act.api.request.AccSepRecordListRequest;
import com.sc.act.api.request.AccSepRecordOutRequest;
import com.sc.act.api.response.AccSepRecordResponse;
import com.sc.act.api.service.AccSepRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ActivityWinsPdtMapper activityWinsPdtMapper;

    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

    @Autowired
    private UserAccInfoMapper userAccInfoMapper;

    @Autowired
    private ProductTicketMapper productTicketMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void insertAccSepRecord(AccSepRecordOutRequest accSepRecordRequest) {
        LOG.info("进入创建支付入账服务请求参数{}", accSepRecordRequest.toString());
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andOutProductIdEqualTo(accSepRecordRequest.getOutProductId());
        List<Product> products = productMapper.selectByExample(productExample);
        if (CollectionUtils.isEmpty(products)) {
            LOG.error("进入创建支付入账服务产品不存在请求参数{}", accSepRecordRequest.toString());
            throw new BaseRuntimeException(ResultEnum.PRODUCT_ISNOT_EXIST);
        }
        Product product = products.get(0);

        ActivityWinsPdtExample activityWinsPdtExample = new ActivityWinsPdtExample();
        activityWinsPdtExample.createCriteria().andProductIdEqualTo(product.getProductId());
        List<ActivityWinsPdt> activityWinsPdts = activityWinsPdtMapper.selectByExample(activityWinsPdtExample);

        if (CollectionUtils.isEmpty(activityWinsPdts)) {
            LOG.error("进入创建支付入账服务产品未配置中奖名单关系请求参数{}", accSepRecordRequest.toString());
            throw new BaseRuntimeException(ResultEnum.PRODUCT_ISNOT_WINNER_REL);
        }

        ActivityWinsPdt activityWinsPdt = activityWinsPdts.get(0);

        ActivityWinners activityWinners = activityWinnersMapper.selectByPrimaryKey(activityWinsPdt.getActivityWinnersId());
        if (null == activityWinners) {
            LOG.error("进入创建支付入账服务产品未配置中奖名单请求参数{}", accSepRecordRequest.toString());
            throw new BaseRuntimeException(ResultEnum.PRODUCT_ISNOT_WINNER);
        }

        if (!accSepRecordRequest.getAmount().equals(activityWinners.getAwardAmount())) {
            LOG.error("进入创建支付入账服务产品中奖金额不正确请求参数{}", accSepRecordRequest.toString());
            throw new BaseRuntimeException(ResultEnum.PRODUCT_WINNER_AMOUNT_ERROR);
        }

        UserAccInfo userAccInfo = userAccInfoMapper.selectByPrimaryKey(activityWinners.getUserAccInfoId());
        if (null == userAccInfo) {
            LOG.error("进入创建支付入账服务产品没有对应的账户信息请求参数{}", accSepRecordRequest.toString());
            throw new BaseRuntimeException(ResultEnum.PRODUCT_ACCOUNT_ISNOT_EXIST);
        }

        Date currentTime = new Date();

        AccSepRecord accSepRecord = new AccSepRecord();
        accSepRecord.setUserAccInfoId(userAccInfo.getUserAccInfoId());

        accSepRecord.setPayoutAmount(activityWinners.getAwardAmount());

        accSepRecord.setCardName(userAccInfo.getCardName());
        accSepRecord.setBankName(userAccInfo.getBankName());
        accSepRecord.setCardNumber(userAccInfo.getCardNumber());
        accSepRecord.setIncomeAmount(activityWinners.getAwardAmount());
        accSepRecord.setOutOrderId(accSepRecordRequest.getOutOrderId());
        accSepRecord.setOutProductId(accSepRecordRequest.getOutProductId());
        accSepRecord.setProductId(product.getProductId());
        accSepRecord.setCreateTime(currentTime);
        accSepRecord.setUpdateTime(currentTime);
        //默认处理中
        // 0-处理中，1-成功，2-失败，3-未知失败
        accSepRecord.setStatus(CommonConstant.ACC_SEP_RECORD_STATUS_0);
        //TODO 出金方信息
        //  accSepRecord.setPoCardName(copy.getPoCardName());
        //  accSepRecord.setPoBankName(copy.getPoBankName());
        //  accSepRecord.setPoCardNo(copy.getPoCardNo());

        try {
            //TODO 成功处理批次号
            //TODO调接口

            //accSepRecord.setHandlerSeqNo(copy.getHandlerSeqNo());
            accSepRecord.setStatus(CommonConstant.ACC_SEP_RECORD_STATUS_1);
            accSepRecordMapper.insertSelective(accSepRecord);

            //分账接口调用成功 更新券状态
            ProductTicketExample productTicketExample = new ProductTicketExample();
            productTicketExample.createCriteria().andProductIdEqualTo(product.getProductId());
            List<ProductTicket> productTickets = productTicketMapper.selectByExample(productTicketExample);
            List<Integer> tickets = productTickets.stream().map(ProductTicket::getTicketId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(tickets)) {
                LOG.error("进入创建支付入账服务未查询到券请求参数accSepRecordRequest={}", accSepRecordRequest.toString());
                throw new BaseRuntimeException(ResultEnum.PRODUCT_TICKET_INFO_ERROR);
            }
            Ticket record = new Ticket();
            record.setState(CommonConstant.PRODUCT_TICKET_2);
            TicketExample example = new TicketExample();
            example.createCriteria().andTicketIdIn(tickets);
            ticketMapper.updateByExampleSelective(record, example);

        } catch (Exception ex) {
            LOG.error("进入创建支付入账服务调用分账接口失败请求参数accSepRecordRequest=" + accSepRecordRequest.toString(), ex);
            // 0-处理中，1-成功，2-失败，3-未知失败
            accSepRecord.setStatus(CommonConstant.ACC_SEP_RECORD_STATUS_2);
            accSepRecord.setReason(ex.getLocalizedMessage());
            accSepRecordMapper.insertSelective(accSepRecord);
            throw new BaseRuntimeException(ResultEnum.USER_MONEY_ERROR);
        }
    }

    @Override
    public PageResponse<AccSepRecordResponse> selectAccSepRecord(AccSepRecordListRequest accSepRecordListRequest) {
        LOG.info("进入查询分账流水列表服务请求参数{}", accSepRecordListRequest.toString());
        AccSepRecordExample accSepRecordExample = new AccSepRecordExample();
        accSepRecordExample.setOrderByClause("acc_sep_record_id desc");
        AccSepRecordExample.Criteria criteria = accSepRecordExample.createCriteria();

        if (null != accSepRecordListRequest.getAccSepRecordId()) {
            criteria.andAccSepRecordIdEqualTo(accSepRecordListRequest.getAccSepRecordId());
        }

        if (null != accSepRecordListRequest.getUserAccInfoId()) {
            criteria.andUserAccInfoIdEqualTo(accSepRecordListRequest.getUserAccInfoId());
        }


        if (null != accSepRecordListRequest.getAmount()) {
            criteria.andIncomeAmountEqualTo(accSepRecordListRequest.getAmount());
        }

        if (null != accSepRecordListRequest.getCardName()) {
            criteria.andCardNameLike("%" + accSepRecordListRequest.getCardName() + "%");
        }

        if (null != accSepRecordListRequest.getStatus()) {
            criteria.andStatusEqualTo(accSepRecordListRequest.getStatus());
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
            BeanUtils.copyProperties(accSepRecord, accSepRecordResponse);
            list.add(accSepRecordResponse);
        });
        return response;
    }


}
