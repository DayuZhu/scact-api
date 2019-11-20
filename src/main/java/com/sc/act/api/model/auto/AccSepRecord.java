package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class AccSepRecord implements Serializable {
    private Integer accSepRecordId;

    private Integer userAccInfoId;

    private String poCardName;

    private String poBankName;

    private String poCardNo;

    private Integer payoutAmount;

    private String cardName;

    private String bankName;

    private String cardNumber;

    private Integer incomeAmount;

    private Integer outOrderId;

    private Integer outProductId;

    private Integer productId;

    private String handlerSeqNo;

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

    public String getPoCardName() {
        return poCardName;
    }

    public void setPoCardName(String poCardName) {
        this.poCardName = poCardName == null ? null : poCardName.trim();
    }

    public String getPoBankName() {
        return poBankName;
    }

    public void setPoBankName(String poBankName) {
        this.poBankName = poBankName == null ? null : poBankName.trim();
    }

    public String getPoCardNo() {
        return poCardNo;
    }

    public void setPoCardNo(String poCardNo) {
        this.poCardNo = poCardNo == null ? null : poCardNo.trim();
    }

    public Integer getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Integer getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
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

    public String getHandlerSeqNo() {
        return handlerSeqNo;
    }

    public void setHandlerSeqNo(String handlerSeqNo) {
        this.handlerSeqNo = handlerSeqNo == null ? null : handlerSeqNo.trim();
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