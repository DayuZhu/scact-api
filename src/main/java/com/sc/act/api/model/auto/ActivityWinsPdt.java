package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class ActivityWinsPdt implements Serializable {
    private Integer activityWinsPdtId;

    private Integer activityWinnersId;

    private Integer productId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getActivityWinsPdtId() {
        return activityWinsPdtId;
    }

    public void setActivityWinsPdtId(Integer activityWinsPdtId) {
        this.activityWinsPdtId = activityWinsPdtId;
    }

    public Integer getActivityWinnersId() {
        return activityWinnersId;
    }

    public void setActivityWinnersId(Integer activityWinnersId) {
        this.activityWinnersId = activityWinnersId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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