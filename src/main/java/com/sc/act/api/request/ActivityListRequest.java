package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 功能描述: 活动列表请求类
 *
 * @className:ActivityListRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "活动List请求参数", description = "活动List请求参数")
public class ActivityListRequest extends PageRequest {

    @ApiModelProperty("主键id")
    private Integer activityId;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("活动状态：0下线，1上线")
    @Min(0)
    @Max(1)
    private Integer state;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
