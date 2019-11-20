package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 功能描述: 分账流水请求类
 *
 * @className:AccSepRecordRequest
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@ApiModel(value = "分账流水请求参数", description = "分账流水请求参数")
public class AccSepRecordOutRequest extends BasePojo {

    @ApiModelProperty("订单ID")
    @NotNull
    private Integer outOrderId;

    @ApiModelProperty("产品Id")
    @NotNull
    private Integer outProductId;

    @ApiModelProperty("订单金额(分)")
    @NotNull
    private Integer amount;

    @NotNull
    public Integer getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(@NotNull Integer outOrderId) {
        this.outOrderId = outOrderId;
    }

    @NotNull
    public Integer getOutProductId() {
        return outProductId;
    }

    public void setOutProductId(@NotNull Integer outProductId) {
        this.outProductId = outProductId;
    }

    @NotNull
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Integer amount) {
        this.amount = amount;
    }
}
