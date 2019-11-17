package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class ProductTicket implements Serializable {
    private Integer productTicketId;

    private Integer productId;

    private Integer ticketId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getProductTicketId() {
        return productTicketId;
    }

    public void setProductTicketId(Integer productTicketId) {
        this.productTicketId = productTicketId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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