package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.MerchantAccountRecordListRequest;
import com.sc.act.api.request.MerchantAccountRecordRequest;
import com.sc.act.api.response.MerchantAccountRecordContentResponse;
import com.sc.act.api.response.MerchantAccountRecordResponse;

/**
 * 功能描述:商户资金记录服务类
 *
 * @className:MerchantAccountRecordService
 * @projectName:
 * @author:generater-code
 * @date: 2020-01-12 16:03:06
 */
public interface MerchantAccountRecordService {

    /**
     * 新增商户资金记录
     *
     * @param merchantAccountRecordRequest
     */
    void insertMerchantAccountRecord(MerchantAccountRecordRequest merchantAccountRecordRequest);


    /**
     * 更新商户资金记录
     *
     * @param merchantAccountRecordRequest
     */
    void updateMerchantAccountRecord(MerchantAccountRecordRequest merchantAccountRecordRequest);


    /**
     * 查询商户资金记录
     *
     * @param merchantAccountRecordId
     * @return
     */
    MerchantAccountRecordContentResponse selectMerchantAccountRecordContent(Integer merchantAccountRecordId);


    /**
     * 查询商户资金记录列表
     *
     * @param merchantAccountRecordListRequest
     * @return
     */
    PageResponse<MerchantAccountRecordResponse> selectMerchantAccountRecord(MerchantAccountRecordListRequest merchantAccountRecordListRequest);


}
