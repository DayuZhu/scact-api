package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 商户资金信息响应类
 *
 * @className:MerchantAccountResponse
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户资金信息响应参数", description = "商户资金信息响应参数")
public class MerchantAccountResponse extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer merchantAccountId;

    @ApiModelProperty("会员id")
    private Integer merchantId;

    @ApiModelProperty("余额")
    private Integer balance;

    @ApiModelProperty("用户账户状态，0-冻结，1-正常")
    private Integer state;


    public Integer getMerchantAccountId() {
        return this.merchantAccountId;
    }

    public void setMerchantAccountId(Integer merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }



}
