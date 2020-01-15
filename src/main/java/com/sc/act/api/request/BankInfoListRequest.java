package com.sc.act.api.request;

import com.sc.act.api.commons.web.base.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述: 银行信息列表请求类
 *
 * @className:BankInfoListRequest
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-15 19:27:45
 */
@ApiModel(value = "银行信息列表请求参数", description = "银行信息列表请求参数")
public class BankInfoListRequest extends PageRequest {

    @ApiModelProperty("主键id")
    private Integer bankInfoId;

    @ApiModelProperty("总行名称")
    private String bankName;

    @ApiModelProperty("省名称")
    private String provinceName;

    @ApiModelProperty("市名称")
    private String cityName;

    @ApiModelProperty("支行编码")
    private String bankSubCode;

    @ApiModelProperty("支行名称")
    private String bankSubName;

    public Integer getBankInfoId() {
        return bankInfoId;
    }

    public void setBankInfoId(Integer bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBankSubCode() {
        return bankSubCode;
    }

    public void setBankSubCode(String bankSubCode) {
        this.bankSubCode = bankSubCode;
    }

    public String getBankSubName() {
        return bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName;
    }
}
