package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 中奖人与产品关系列表请求类
 *
 * @className:ActivityWinsPdtListRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "中奖人与产品关系请求参数", description = "中奖人与产品关系请求参数")
public class ActivityWinsPdtListRequest extends PageRequest {

    @ApiModelProperty("主键id")
    private Integer activityWinsPdtId;

    @ApiModelProperty("中奖名单ID")
    private Integer activityWinnersId;

    @ApiModelProperty("产品ID")
    private Integer productId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Integer getActivityWinsPdtId() {
        return this.activityWinsPdtId;
    }

    public void setActivityWinsPdtId(Integer activityWinsPdtId) {
        this.activityWinsPdtId = activityWinsPdtId;
    }

    public Integer getActivityWinnersId() {
        return this.activityWinnersId;
    }

    public void setActivityWinnersId(Integer activityWinnersId) {
        this.activityWinnersId = activityWinnersId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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