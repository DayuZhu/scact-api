package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.MerchantAccountListRequest;
import com.sc.act.api.request.MerchantAccountRequest;
import com.sc.act.api.response.MerchantAccountContentResponse;
import com.sc.act.api.response.MerchantAccountResponse;

/**
 * 功能描述:商户资金信息服务类
 *
 * @className:MerchantAccountService
 * @projectName:
 * @author:generater-code
 * @date: 2020-01-12 16:03:06
 */
public interface MerchantAccountService {

    /**
     * 新增商户资金信息
     *
     * @param merchantAccountRequest
     */
    void insertMerchantAccount(MerchantAccountRequest merchantAccountRequest);


    /**
     * 更新商户资金信息
     *
     * @param merchantAccountRequest
     */
    void updateMerchantAccount(MerchantAccountRequest merchantAccountRequest);


    /**
     * 查询商户资金信息
     *
     * @param merchantAccountId
     * @return
     */
    MerchantAccountContentResponse selectMerchantAccountContent(Integer merchantAccountId);


    /**
     * 查询商户资金信息列表
     *
     * @param merchantAccountListRequest
     * @return
     */
    PageResponse<MerchantAccountResponse> selectMerchantAccount(MerchantAccountListRequest merchantAccountListRequest);


}
