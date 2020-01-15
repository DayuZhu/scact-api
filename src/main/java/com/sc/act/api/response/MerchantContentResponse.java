package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 商户内容响应类
 *
 * @className:MerchantContentResponse
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户内容响应参数", description = "商户内容响应参数")
public class MerchantContentResponse extends BasePojo {

    @ApiModelProperty("商户id")
    private Integer merchantId;

    @ApiModelProperty("商家名称")
    private String merchantName;

    @ApiModelProperty("社会统一代码")
    private String socialCode;

    @ApiModelProperty("法人姓名")
    private String name;

    @ApiModelProperty("法人手机号")
    private Long mobile;

    @ApiModelProperty("商家地址")
    private String address;

    @ApiModelProperty("店主名称")
    private String bossName;

    @ApiModelProperty("店主电话")
    private Long bossTel;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("账户信息")
    private MerchantAccInfoResponse merchantAccInfoResponse;

    @ApiModelProperty("账户余额")
    private MerchantAccountResponse merchantAccountResponse;

    @ApiModelProperty("银行信息")
    private BankInfoResponse bankInfoResponse;


    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSocialCode() {
        return this.socialCode;
    }

    public void setSocialCode(String socialCode) {
        this.socialCode = socialCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return this.mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBossName() {
        return this.bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public Long getBossTel() {
        return this.bossTel;
    }

    public void setBossTel(Long bossTel) {
        this.bossTel = bossTel;
    }


    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MerchantAccInfoResponse getMerchantAccInfoResponse() {
        return merchantAccInfoResponse;
    }

    public void setMerchantAccInfoResponse(MerchantAccInfoResponse merchantAccInfoResponse) {
        this.merchantAccInfoResponse = merchantAccInfoResponse;
    }

    public MerchantAccountResponse getMerchantAccountResponse() {
        return merchantAccountResponse;
    }

    public void setMerchantAccountResponse(MerchantAccountResponse merchantAccountResponse) {
        this.merchantAccountResponse = merchantAccountResponse;
    }

    public BankInfoResponse getBankInfoResponse() {
        return bankInfoResponse;
    }

    public void setBankInfoResponse(BankInfoResponse bankInfoResponse) {
        this.bankInfoResponse = bankInfoResponse;
    }
}
