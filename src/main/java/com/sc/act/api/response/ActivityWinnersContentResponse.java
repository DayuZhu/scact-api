package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 活动中奖名响应类
 *
 * @className:ActivityWinnersContentResponse
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "活动中奖名响应参数", description = "活动中奖名响应参数")
public class ActivityWinnersContentResponse extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer activityWinnersId;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("账户ID")
    private Integer userAccInfoId;

    @ApiModelProperty("中奖金额")
    private Integer awardAmount;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


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

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAccInfoId() {
        return this.userAccInfoId;
    }

    public void setUserAccInfoId(Integer userAccInfoId) {
        this.userAccInfoId = userAccInfoId;
    }

    public Integer getAwardAmount() {
        return this.awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
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
