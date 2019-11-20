package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.AccSepRecordListRequest;
import com.sc.act.api.request.AccSepRecordOutRequest;
import com.sc.act.api.response.AccSepRecordResponse;

/**
 * 功能描述:分账流水服务类
 *
 * @className:AccSepRecordService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface AccSepRecordService {

    /**
     * 新增分账流水
     *
     * @param accSepRecordOutRequest
     */
    void insertAccSepRecord(AccSepRecordOutRequest accSepRecordOutRequest);


    /**
     * 查询分账流水列表
     *
     * @param accSepRecordListRequest
     * @return
     */
    PageResponse<AccSepRecordResponse> selectAccSepRecord(AccSepRecordListRequest accSepRecordListRequest);


}
