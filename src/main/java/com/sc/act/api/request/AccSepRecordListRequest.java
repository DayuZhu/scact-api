package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述: 分账流水列表请求类
 *
 * @className:AccSepRecordListRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "分账流水请求参数", description = "分账流水请求参数")
public class AccSepRecordListRequest extends PageRequest {

    @ApiModelProperty("主键")
    private Integer accSepRecordId;

    @ApiModelProperty("账户信息id")
    private Integer userAccInfoId;

    @ApiModelProperty("入金金额(分)")
    private Integer amount;

    @ApiModelProperty("入金持卡人姓名")
    private String cardName;

    @ApiModelProperty("0-处理中，1-成功，2-失败，3-未知失败")
    private Integer status;

    public Integer getAccSepRecordId() {
        return accSepRecordId;
    }

    public void setAccSepRecordId(Integer accSepRecordId) {
        this.accSepRecordId = accSepRecordId;
    }

    public Integer getUserAccInfoId() {
        return userAccInfoId;
    }

    public void setUserAccInfoId(Integer userAccInfoId) {
        this.userAccInfoId = userAccInfoId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
