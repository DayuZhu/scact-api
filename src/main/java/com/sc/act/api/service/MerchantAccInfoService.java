package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.MerchantAccInfoListRequest;
import com.sc.act.api.request.MerchantAccInfoRequest;
import com.sc.act.api.response.MerchantAccInfoContentResponse;
import com.sc.act.api.response.MerchantAccInfoResponse;

/**
 * 功能描述:商户账户信息服务类
 *
 * @className:MerchantAccInfoService
 * @projectName:
 * @author:generater-code
 * @date: 2020-01-12 16:03:06
 */
public interface MerchantAccInfoService {

    /**
     * 新增商户账户信息
     *
     * @param merchantAccInfoRequest
     */
    void insertMerchantAccInfo(MerchantAccInfoRequest merchantAccInfoRequest);


    /**
     * 更新商户账户信息
     *
     * @param merchantAccInfoRequest
     */
    void updateMerchantAccInfo(MerchantAccInfoRequest merchantAccInfoRequest);


    /**
     * 查询商户账户信息
     *
     * @param merchantAccInfoId
     * @return
     */
    MerchantAccInfoContentResponse selectMerchantAccInfoContent(Integer merchantAccInfoId);


    /**
     * 查询商户账户信息列表
     *
     * @param merchantAccInfoListRequest
     * @return
     */
    PageResponse<MerchantAccInfoResponse> selectMerchantAccInfo(MerchantAccInfoListRequest merchantAccInfoListRequest);


}
