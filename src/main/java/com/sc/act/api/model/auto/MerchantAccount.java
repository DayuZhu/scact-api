package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class MerchantAccount implements Serializable {
    private Integer merchantAccountId;

    private Integer merchantId;

    private Integer balance;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Integer merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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