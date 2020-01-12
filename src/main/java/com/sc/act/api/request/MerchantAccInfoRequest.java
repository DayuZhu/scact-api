package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 商户账户信息请求类
 *
 * @className:MerchantAccInfoRequest
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-12 16:03:06
 */
@ApiModel(value = "商户账户信息请求参数", description = "商户账户信息请求参数")
public class MerchantAccInfoRequest extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer merchantAccInfoId;

    @ApiModelProperty("商户ID")
    private Integer merchantId;

    @ApiModelProperty("姓名")
    private String accName;

    @ApiModelProperty("开户行")
    private String merchantBank;

    @ApiModelProperty("银行卡号")
    private String merchantCard;

    @ApiModelProperty("手机号")
    private Long mobile;

    @ApiModelProperty("状态：0 弃用 1使用")
    private Integer state;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


    public Integer getMerchantAccInfoId() {
        return this.merchantAccInfoId;
    }

    public void setMerchantAccInfoId(Integer merchantAccInfoId) {
        this.merchantAccInfoId = merchantAccInfoId;
    }

    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccName() {
        return this.accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getMerchantBank() {
        return this.merchantBank;
    }

    public void setMerchantBank(String merchantBank) {
        this.merchantBank = merchantBank;
    }

    public String getMerchantCard() {
        return this.merchantCard;
    }

    public void setMerchantCard(String merchantCard) {
        this.merchantCard = merchantCard;
    }

    public Long getMobile() {
        return this.mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
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
