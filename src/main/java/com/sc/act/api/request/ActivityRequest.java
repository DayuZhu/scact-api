package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 功能描述: 活动请求类
 *
 * @className:ActivityRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "活动请求参数", description = "活动请求参数")
public class ActivityRequest extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer activityId;

    @ApiModelProperty("活动名称")
    @NotBlank
    @Length(min = 1, max = 128)
    private String activityName;

    @ApiModelProperty("活动描述")
    @NotBlank
    @Length(min = 1, max = 1000)
    private String activityDesc;

    @ApiModelProperty("活动开始时间")
    @NotNull
    private Date startTime;

    @ApiModelProperty("活动结束时间")
    @NotNull
    private Date endTime;

    @ApiModelProperty("活动状态：0下线，1上线")
    @NotNull
    @Min(0)
    @Max(1)
    private Integer state;

    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("最后一次更新人")
    private Integer updateUserId;

    @ApiModelProperty("最后一次更新人姓名")
    private String updateUserName;

    public Integer getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getUpdateUserId() {
        return this.updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return this.updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }


}
