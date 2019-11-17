package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 产品请求类
 *
 * @className:ProductRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "产品请求参数", description = "产品请求参数")
public class ProductRequest extends BasePojo {

    @ApiModelProperty("商品ID")
    private Integer productId;

    @ApiModelProperty("商品类型：0-活动券")
    private Integer productType;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("市场价")
    private Integer marketPrice;

    @ApiModelProperty("销售价")
    private Integer sellPrice;

    @ApiModelProperty("外部产品关联ID")
    private Integer outProductId;

    @ApiModelProperty("外部产品平台：0-shopXO")
    private Integer outProductPlatform;

    @ApiModelProperty("0:待审核,1:已上线,2:已下线,3:已删除")
    private Integer state;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductType() {
        return this.productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSellPrice() {
        return this.sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getOutProductId() {
        return this.outProductId;
    }

    public void setOutProductId(Integer outProductId) {
        this.outProductId = outProductId;
    }

    public Integer getOutProductPlatform() {
        return this.outProductPlatform;
    }

    public void setOutProductPlatform(Integer outProductPlatform) {
        this.outProductPlatform = outProductPlatform;
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
