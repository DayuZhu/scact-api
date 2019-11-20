package com.sc.act.api.response;

import com.sc.act.api.model.auto.Product;
import com.sc.act.api.model.auto.Ticket;
import com.sc.act.api.model.auto.User;
import com.sc.act.api.model.auto.UserAccInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能描述:
 *
 * @className:ActivityContent111Response
 * @projectName:scact-api-2019-1116
 * @author:Dayu
 * @date: 2019/11/20 17:29
 */
@ApiModel(value = "活动中奖名单响应参数", description = "活动中奖名单响应参数")
public class ActivityWinnersUserAccResponse {

    @ApiModelProperty("主键id")
    private Integer activityWinnersId;

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("中奖金额")
    private Integer awardAmount;

    @ApiModelProperty("用户信息")
    private User user;

    @ApiModelProperty("账户信息")
    private UserAccInfo userAccInfo;

    @ApiModelProperty("产品信息")
    private Product product;

    @ApiModelProperty("券信息")
    private List<Ticket> tickets;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAccInfo getUserAccInfo() {
        return userAccInfo;
    }

    public void setUserAccInfo(UserAccInfo userAccInfo) {
        this.userAccInfo = userAccInfo;
    }

    public Integer getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Integer awardAmount) {
        this.awardAmount = awardAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
