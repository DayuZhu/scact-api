package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 商户资金记录响应类
 *
 * @className:MerchantAccountRecordResponse
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户资金记录响应参数", description = "商户资金记录响应参数")
public class MerchantAccountRecordResponse extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer merchantAccountRecordId;

    @ApiModelProperty("商户id")
    private Integer merchantId;

    @ApiModelProperty("类型 0无 1 活动中奖")
    private Integer recordType;

    @ApiModelProperty("进账")
    private Integer incomeAmount;

    @ApiModelProperty("出账")
    private Integer payoutAmount;

    @ApiModelProperty("描述:活动中奖")
    private String reasonDesc;

    @ApiModelProperty("活动中奖名单ID")
    private Integer activityWinnersId;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public Integer getMerchantAccountRecordId() {
        return this.merchantAccountRecordId;
    }

    public void setMerchantAccountRecordId(Integer merchantAccountRecordId) {
        this.merchantAccountRecordId = merchantAccountRecordId;
    }

    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getRecordType() {
        return this.recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getIncomeAmount() {
        return this.incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getPayoutAmount() {
        return this.payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getReasonDesc() {
        return this.reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public Integer getActivityWinnersId() {
        return this.activityWinnersId;
    }

    public void setActivityWinnersId(Integer activityWinnersId) {
        this.activityWinnersId = activityWinnersId;
    }

    public Integer getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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
