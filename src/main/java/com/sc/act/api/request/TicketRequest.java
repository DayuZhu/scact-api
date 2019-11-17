package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 券码请求类
 *
 * @className:TicketRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "券码请求参数", description = "券码请求参数")
public class TicketRequest extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer ticketId;

    @ApiModelProperty("券码")
    private String ticketCode;

    @ApiModelProperty("券密")
    private Integer ticketPwd;

    @ApiModelProperty("券面值")
    private Integer nominalValue;

    @ApiModelProperty("状态：0初始，1已发放，2已使用，3已冻结")
    private Integer state;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Integer getTicketPwd() {
        return this.ticketPwd;
    }

    public void setTicketPwd(Integer ticketPwd) {
        this.ticketPwd = ticketPwd;
    }

    public Integer getNominalValue() {
        return this.nominalValue;
    }

    public void setNominalValue(Integer nominalValue) {
        this.nominalValue = nominalValue;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
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
