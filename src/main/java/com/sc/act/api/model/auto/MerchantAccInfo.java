package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class MerchantAccInfo implements Serializable {
    private Integer merchantAccInfoId;

    private Integer merchantId;

    private Integer bankInfoId;

    private String accName;

    private String merchantBank;

    private String merchantCard;

    private Long mobile;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public Integer getBankInfoId() {
        return bankInfoId;
    }

    public void setBankInfoId(Integer bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName == null ? null : accName.trim();
    }

    public String getMerchantBank() {
        return merchantBank;
    }

    public void setMerchantBank(String merchantBank) {
        this.merchantBank = merchantBank == null ? null : merchantBank.trim();
    }

    public String getMerchantCard() {
        return merchantCard;
    }

    public void setMerchantCard(String merchantCard) {
        this.merchantCard = merchantCard == null ? null : merchantCard.trim();
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}