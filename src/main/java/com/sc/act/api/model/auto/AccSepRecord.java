package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class AccSepRecord implements Serializable {
    private Integer accSepRecordId;

    private Integer userAccInfoId;

    private Integer incomeAmount;

    private Integer payoutAmount;

    private Integer outOrderId;

    private Integer outProductId;

    private Integer productId;

    private Integer status;

    private String reason;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getAccSepRecordId() {
        return accSepRecordId;
    }

    public void setAccSepRecordId(Integer accSepRecordId) {
        this.accSepRecordId = accSepRecordId;
    }

    public Integer getUserAccInfoId() {
        return userAccInfoId;
    }

    public void setUserAccInfoId(Integer userAccInfoId) {
        this.userAccInfoId = userAccInfoId;
    }

    public Integer getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public Integer getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(Integer outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Integer getOutProductId() {
        return outProductId;
    }

    public void setOutProductId(Integer outProductId) {
        this.outProductId = outProductId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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