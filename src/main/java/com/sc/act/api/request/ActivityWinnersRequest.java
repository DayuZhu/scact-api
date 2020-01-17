package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 功能描述:
 *
 * @className:ActivityWinnersRequest
 * @projectName:scact-api-2019-1116
 * @author:Dayu
 * @date: 2020/1/17 17:07
 */
@ApiModel(value = "活动请求参数", description = "活动请求参数")
public class ActivityWinnersRequest extends BasePojo {

    @ApiModelProperty(name = "activityId")
    @NotNull
    private Integer activityId;

    @ApiModelProperty("中奖金额(元)")
    @NotNull
    private Integer awardAmount;

    @ApiModelProperty("中奖人姓名")
    @NotBlank
    private String name;

    @ApiModelProperty("手机号")
    @NotBlank
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String mobile;

    @ApiModelProperty("开户行")
    @NotBlank
    private String bankName;

    @ApiModelProperty("银行卡号")
    @NotBlank
    private String cardNumber;

    @NotNull
    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(@NotNull Integer awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getMobile() {
        return mobile;
    }

    public void setMobile(@NotNull String mobile) {
        this.mobile = mobile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @NotNull
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(@NotNull Integer activityId) {
        this.activityId = activityId;
    }
}
