package com.sc.act.api.model.auto;

import java.io.Serializable;
import java.util.Date;

public class BankInfo implements Serializable {
    private Integer bankInfoId;

    private String bankName;

    private String codeAcronym;

    private String provinceName;

    private Integer bankProvinceCode;

    private Integer provinceCode;

    private String cityName;

    private Integer bankCityCode;

    private Integer cityCode;

    private String bankSubCode;

    private String bankSubName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCodeAcronym() {
        return codeAcronym;
    }

    public void setCodeAcronym(String codeAcronym) {
        this.codeAcronym = codeAcronym == null ? null : codeAcronym.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(Integer bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(Integer bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getBankSubCode() {
        return bankSubCode;
    }

    public void setBankSubCode(String bankSubCode) {
        this.bankSubCode = bankSubCode == null ? null : bankSubCode.trim();
    }

    public String getBankSubName() {
        return bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName == null ? null : bankSubName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}