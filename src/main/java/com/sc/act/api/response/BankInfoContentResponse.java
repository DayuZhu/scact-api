package com.sc.act.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.act.api.commons.web.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述: 银行信息内容响应类
 *
 * @className:BankInfoContentResponse
 * @projectName:
 * @author: generater-code
 * @date: 2020-01-15 19:27:45
 */
@ApiModel(value = "银行信息内容响应参数", description = "银行信息内容响应参数")
public class BankInfoContentResponse extends BasePojo {

    @ApiModelProperty("主键id")
    private Integer bankInfoId;

    @ApiModelProperty("总行名称")
    private String bankName;

    @ApiModelProperty("总行编码")
    private String codeAcronym;

    @ApiModelProperty("省名称")
    private String provinceName;

    @ApiModelProperty("省编码")
    private Integer bankProvinceCode;

    @ApiModelProperty("省编码6位")
    private Integer provinceCode;

    @ApiModelProperty("市名称")
    private String cityName;

    @ApiModelProperty("市编码")
    private Integer bankCityCode;

    @ApiModelProperty("市编码6位")
    private Integer cityCode;

    @ApiModelProperty("支行编码")
    private String bankSubCode;

    @ApiModelProperty("支行名称")
    private String bankSubName;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public Integer getBankInfoId() {
        return this.bankInfoId;
    }

    public void setBankInfoId(Integer bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCodeAcronym() {
        return this.codeAcronym;
    }

    public void setCodeAcronym(String codeAcronym) {
        this.codeAcronym = codeAcronym;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getBankProvinceCode() {
        return this.bankProvinceCode;
    }

    public void setBankProvinceCode(Integer bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public Integer getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getBankCityCode() {
        return this.bankCityCode;
    }

    public void setBankCityCode(Integer bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public Integer getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getBankSubCode() {
        return this.bankSubCode;
    }

    public void setBankSubCode(String bankSubCode) {
        this.bankSubCode = bankSubCode;
    }

    public String getBankSubName() {
        return this.bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName;
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
