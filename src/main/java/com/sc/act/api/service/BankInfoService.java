package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.BankInfoListRequest;
import com.sc.act.api.response.BankInfoContentResponse;
import com.sc.act.api.response.BankInfoResponse;

/**
 * 功能描述:银行信息服务类
 *
 * @className:BankInfoService
 * @projectName:
 * @author:generater-code
 * @date: 2020-01-15 19:27:45
 */
public interface BankInfoService {


    /**
     * 查询银行信息
     *
     * @param bankInfoId
     * @return
     */
    BankInfoContentResponse selectBankInfoContent(Integer bankInfoId);


    /**
     * 查询银行信息列表
     *
     * @param bankInfoListRequest
     * @return
     */
    PageResponse<BankInfoResponse> selectBankInfo(BankInfoListRequest bankInfoListRequest);


}
