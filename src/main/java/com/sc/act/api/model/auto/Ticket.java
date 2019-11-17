package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private Integer ticketId;

    private String ticketCode;

    private Integer ticketPwd;

    private Integer nominalValue;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode == null ? null : ticketCode.trim();
    }

    public Integer getTicketPwd() {
        return ticketPwd;
    }

    public void setTicketPwd(Integer ticketPwd) {
        this.ticketPwd = ticketPwd;
    }

    public Integer getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(Integer nominalValue) {
        this.nominalValue = nominalValue;
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