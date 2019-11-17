package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class ActivityWinners implements Serializable {
    private Integer activityWinnersId;

    private Integer activityId;

    private Integer userId;

    private Integer userAccInfoId;

    private Integer awardAmount;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getActivityWinnersId() {
        return activityWinnersId;
    }

    public void setActivityWinnersId(Integer activityWinnersId) {
        this.activityWinnersId = activityWinnersId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAccInfoId() {
        return userAccInfoId;
    }

    public void setUserAccInfoId(Integer userAccInfoId) {
        this.userAccInfoId = userAccInfoId;
    }

    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
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