package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 功能描述: 商户请求类
 *
 * @className:MerchantRequest
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户请求参数", description = "商户请求参数")
public class MerchantRequest extends BasePojo {

    @ApiModelProperty("商户id")
    private Integer merchantId;

    @ApiModelProperty("商家名称")
    @NotBlank
    private String merchantName;

    @ApiModelProperty("社会统一代码")
    @NotBlank
    private String socialCode;

    @ApiModelProperty("法人姓名")
    @NotBlank
    private String name;

    @ApiModelProperty("法人手机号")
    @NotNull
    private Long mobile;

    @ApiModelProperty("开户行")
    @NotBlank
    private String merchantBank;

    @ApiModelProperty("银行卡号")
    @NotBlank
    private String merchantCard;

    @ApiModelProperty("商家地址")
    @NotBlank
    private String address;

    @ApiModelProperty("店主名称")
    @NotBlank
    private String bossName;

    @ApiModelProperty("店主电话")
    @NotNull
    private Long bossTel;

    @ApiModelProperty("余额")
    @NotNull
    private Integer balance;

    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("最后一次更新人")
    private Integer updateUserId;

    @ApiModelProperty("最后一次更新人姓名")
    private String updateUserName;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSocialCode() {
        return socialCode;
    }

    public void setSocialCode(String socialCode) {
        this.socialCode = socialCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Long getMobile() {
        return mobile;
    }

    public void setMobile(@NotNull Long mobile) {
        this.mobile = mobile;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    @NotNull
    public Long getBossTel() {
        return bossTel;
    }

    public void setBossTel(@NotNull Long bossTel) {
        this.bossTel = bossTel;
    }

    @NotNull
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(@NotNull Integer balance) {
        this.balance = balance;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
