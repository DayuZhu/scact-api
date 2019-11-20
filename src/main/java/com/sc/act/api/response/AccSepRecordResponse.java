package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 分账流水响应类
 *
 * @className:AccSepRecordResponse
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-20 19:20:16
 */
@ApiModel(value = "分账流水响应参数", description = "分账流水响应参数")
public class AccSepRecordResponse extends BasePojo {

    @ApiModelProperty("主键")
    private Integer accSepRecordId;

    @ApiModelProperty("账户信息id")
    private Integer userAccInfoId;

    @ApiModelProperty("出金持卡人")
    private String poCardName;

    @ApiModelProperty("出金银行名称")
    private String poBankName;

    @ApiModelProperty("出金银行卡号")
    private String poCardNo;

    @ApiModelProperty("出金金额(分）")
    private Integer payoutAmount;

    @ApiModelProperty("入金持卡人姓名")
    private String cardName;

    @ApiModelProperty("入金银行名称")
    private String bankName;

    @ApiModelProperty("入金持卡人姓名")
    private String cardNumber;

    @ApiModelProperty("入金金额(分)")
    private Integer incomeAmount;

    @ApiModelProperty("外部订单ID")
    private Integer outOrderId;

    @ApiModelProperty("外部商品ID")
    private Integer outProductId;

    @ApiModelProperty("产品ID")
    private Integer productId;

    @ApiModelProperty("处理批次号")
    private String handlerSeqNo;

    @ApiModelProperty("0-处理中，1-成功，2-失败，3-未知失败")
    private Integer status;

    @ApiModelProperty("失败原因")
    private String reason;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public Integer getAccSepRecordId() {
        return this.accSepRecordId;
    }

    public void setAccSepRecordId(Integer accSepRecordId) {
        this.accSepRecordId = accSepRecordId;
    }

    public Integer getUserAccInfoId() {
        return this.userAccInfoId;
    }

    public void setUserAccInfoId(Integer userAccInfoId) {
        this.userAccInfoId = userAccInfoId;
    }

    public String getPoCardName() {
        return this.poCardName;
    }

    public void setPoCardName(String poCardName) {
        this.poCardName = poCardName;
    }

    public String getPoBankName() {
        return this.poBankName;
    }

    public void setPoBankName(String poBankName) {
        this.poBankName = poBankName;
    }

    public String getPoCardNo() {
        return this.poCardNo;
    }

    public void setPoCardNo(String poCardNo) {
        this.poCardNo = poCardNo;
    }

    public Integer getPayoutAmount() {
        return this.payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getIncomeAmount() {
        return this.incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getOutOrderId() {
        return this.outOrderId;
    }

    public void setOutOrderId(Integer outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Integer getOutProductId() {
        return this.outProductId;
    }

    public void setOutProductId(Integer outProductId) {
        this.outProductId = outProductId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getHandlerSeqNo() {
        return this.handlerSeqNo;
    }

    public void setHandlerSeqNo(String handlerSeqNo) {
        this.handlerSeqNo = handlerSeqNo;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
