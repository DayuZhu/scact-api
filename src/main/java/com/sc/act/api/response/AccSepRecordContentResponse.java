package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 分账流水响应类
 *
 * @className:AccSepRecordContentResponse
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "分账流水响应参数", description = "分账流水响应参数")
public class AccSepRecordContentResponse extends BasePojo {

    @ApiModelProperty("主键")
    private Integer accSepRecordId;

    @ApiModelProperty("账户信息id")
    private Integer userAccInfoId;

    @ApiModelProperty("金额")
    private Integer amount;

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

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
