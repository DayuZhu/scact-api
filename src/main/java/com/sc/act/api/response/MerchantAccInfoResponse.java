package com.sc.act.api.response;

import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述: 商户账户信息响应类
 *
 * @className:MerchantAccInfoResponse
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户账户信息响应参数", description = "商户账户信息响应参数")
public class MerchantAccInfoResponse extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer merchantAccInfoId;

    @ApiModelProperty("商户ID")
    private Integer merchantId;

    @ApiModelProperty("开户行")
    private String merchantBank;

    @ApiModelProperty("银行卡号")
    private String merchantCard;


    @ApiModelProperty("状态：0 弃用 1使用")
    private Integer state;

    public Integer getMerchantAccInfoId() {
        return merchantAccInfoId;
    }

    public void setMerchantAccInfoId(Integer merchantAccInfoId) {
        this.merchantAccInfoId = merchantAccInfoId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantBank() {
        return merchantBank;
    }

    public void setMerchantBank(String merchantBank) {
        this.merchantBank = merchantBank;
    }

    public String getMerchantCard() {
        return merchantCard;
    }

    public void setMerchantCard(String merchantCard) {
        this.merchantCard = merchantCard;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
