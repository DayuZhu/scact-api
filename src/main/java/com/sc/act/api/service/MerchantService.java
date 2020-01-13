package com.sc.act.api.service;

import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.MerchantListRequest;
import com.sc.act.api.request.MerchantRequest;
import com.sc.act.api.response.MerchantContentResponse;
import com.sc.act.api.response.MerchantResponse;

/**
 * 功能描述:商户服务类
 *
 * @className:MerchantService
 * @projectName:
 * @author:generater-code
 * @date: 2020-01-12 16:03:06
 */
public interface MerchantService {

    /**
     * 新增商户
     *
     * @param merchantRequest
     */
    void insertMerchant(MerchantRequest merchantRequest);


    /**
     * 更新商户
     *
     * @param merchantRequest
     */
    void updateMerchant(MerchantRequest merchantRequest);


    /**
     * 查询商户
     *
     * @param merchantId
     * @return
     */
    MerchantContentResponse selectMerchantContent(Integer merchantId);


    /**
     * 查询商户列表
     *
     * @param merchantListRequest
     * @return
     */
    PageResponse<MerchantResponse> selectMerchant(MerchantListRequest merchantListRequest);


}
