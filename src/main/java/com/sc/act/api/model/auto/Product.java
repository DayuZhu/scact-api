package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private Integer productId;

    private Integer productType;

    private String productName;

    private Integer marketPrice;

    private Integer sellPrice;

    private Integer outProductId;

    private Integer outProductPlatform;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getOutProductId() {
        return outProductId;
    }

    public void setOutProductId(Integer outProductId) {
        this.outProductId = outProductId;
    }

    public Integer getOutProductPlatform() {
        return outProductPlatform;
    }

    public void setOutProductPlatform(Integer outProductPlatform) {
        this.outProductPlatform = outProductPlatform;
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