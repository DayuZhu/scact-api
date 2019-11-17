package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 产品与券码关系请求类
 *
 * @className:ProductTicketRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "产品与券码关系请求参数", description = "产品与券码关系请求参数")
public class ProductTicketRequest extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer productTicketId;

    @ApiModelProperty("中奖名单ID")
    private Integer productId;

    @ApiModelProperty("券码ID")
    private Integer ticketId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Integer getProductTicketId() {
        return this.productTicketId;
    }

    public void setProductTicketId(Integer productTicketId) {
        this.productTicketId = productTicketId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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
