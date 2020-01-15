package com.sc.act.api.model.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BankInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBankInfoIdIsNull() {
            addCriterion("bank_info_id is null");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdIsNotNull() {
            addCriterion("bank_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdEqualTo(Integer value) {
            addCriterion("bank_info_id =", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotEqualTo(Integer value) {
            addCriterion("bank_info_id <>", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdGreaterThan(Integer value) {
            addCriterion("bank_info_id >", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_info_id >=", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdLessThan(Integer value) {
            addCriterion("bank_info_id <", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("bank_info_id <=", value, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdIn(List<Integer> values) {
            addCriterion("bank_info_id in", values, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotIn(List<Integer> values) {
            addCriterion("bank_info_id not in", values, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("bank_info_id between", value1, value2, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_info_id not between", value1, value2, "bankInfoId");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymIsNull() {
            addCriterion("code_acronym is null");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymIsNotNull() {
            addCriterion("code_acronym is not null");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymEqualTo(String value) {
            addCriterion("code_acronym =", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymNotEqualTo(String value) {
            addCriterion("code_acronym <>", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymGreaterThan(String value) {
            addCriterion("code_acronym >", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymGreaterThanOrEqualTo(String value) {
            addCriterion("code_acronym >=", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymLessThan(String value) {
            addCriterion("code_acronym <", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymLessThanOrEqualTo(String value) {
            addCriterion("code_acronym <=", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymLike(String value) {
            addCriterion("code_acronym like", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymNotLike(String value) {
            addCriterion("code_acronym not like", value, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymIn(List<String> values) {
            addCriterion("code_acronym in", values, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymNotIn(List<String> values) {
            addCriterion("code_acronym not in", values, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymBetween(String value1, String value2) {
            addCriterion("code_acronym between", value1, value2, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andCodeAcronymNotBetween(String value1, String value2) {
            addCriterion("code_acronym not between", value1, value2, "codeAcronym");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("province_name is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("province_name is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("province_name =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("province_name <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("province_name >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("province_name >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("province_name <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("province_name <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("province_name like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("province_name not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("province_name in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("province_name not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("province_name between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("province_name not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeIsNull() {
            addCriterion("bank_province_code is null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeIsNotNull() {
            addCriterion("bank_province_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeEqualTo(Integer value) {
            addCriterion("bank_province_code =", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeNotEqualTo(Integer value) {
            addCriterion("bank_province_code <>", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeGreaterThan(Integer value) {
            addCriterion("bank_province_code >", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_province_code >=", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeLessThan(Integer value) {
            addCriterion("bank_province_code <", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeLessThanOrEqualTo(Integer value) {
            addCriterion("bank_province_code <=", value, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeIn(List<Integer> values) {
            addCriterion("bank_province_code in", values, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeNotIn(List<Integer> values) {
            addCriterion("bank_province_code not in", values, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeBetween(Integer value1, Integer value2) {
            addCriterion("bank_province_code between", value1, value2, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andBankProvinceCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_province_code not between", value1, value2, "bankProvinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNull() {
            addCriterion("province_code is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("province_code is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(Integer value) {
            addCriterion("province_code =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(Integer value) {
            addCriterion("province_code <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(Integer value) {
            addCriterion("province_code >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("province_code >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(Integer value) {
            addCriterion("province_code <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(Integer value) {
            addCriterion("province_code <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<Integer> values) {
            addCriterion("province_code in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<Integer> values) {
            addCriterion("province_code not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(Integer value1, Integer value2) {
            addCriterion("province_code between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("province_code not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeIsNull() {
            addCriterion("bank_city_code is null");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeIsNotNull() {
            addCriterion("bank_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeEqualTo(Integer value) {
            addCriterion("bank_city_code =", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeNotEqualTo(Integer value) {
            addCriterion("bank_city_code <>", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeGreaterThan(Integer value) {
            addCriterion("bank_city_code >", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_city_code >=", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeLessThan(Integer value) {
            addCriterion("bank_city_code <", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeLessThanOrEqualTo(Integer value) {
            addCriterion("bank_city_code <=", value, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeIn(List<Integer> values) {
            addCriterion("bank_city_code in", values, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeNotIn(List<Integer> values) {
            addCriterion("bank_city_code not in", values, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeBetween(Integer value1, Integer value2) {
            addCriterion("bank_city_code between", value1, value2, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andBankCityCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_city_code not between", value1, value2, "bankCityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(Integer value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(Integer value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(Integer value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(Integer value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(Integer value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<Integer> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<Integer> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(Integer value1, Integer value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeIsNull() {
            addCriterion("bank_sub_code is null");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeIsNotNull() {
            addCriterion("bank_sub_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeEqualTo(String value) {
            addCriterion("bank_sub_code =", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeNotEqualTo(String value) {
            addCriterion("bank_sub_code <>", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeGreaterThan(String value) {
            addCriterion("bank_sub_code >", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_sub_code >=", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeLessThan(String value) {
            addCriterion("bank_sub_code <", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_sub_code <=", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeLike(String value) {
            addCriterion("bank_sub_code like", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeNotLike(String value) {
            addCriterion("bank_sub_code not like", value, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeIn(List<String> values) {
            addCriterion("bank_sub_code in", values, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeNotIn(List<String> values) {
            addCriterion("bank_sub_code not in", values, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeBetween(String value1, String value2) {
            addCriterion("bank_sub_code between", value1, value2, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubCodeNotBetween(String value1, String value2) {
            addCriterion("bank_sub_code not between", value1, value2, "bankSubCode");
            return (Criteria) this;
        }

        public Criteria andBankSubNameIsNull() {
            addCriterion("bank_sub_name is null");
            return (Criteria) this;
        }

        public Criteria andBankSubNameIsNotNull() {
            addCriterion("bank_sub_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankSubNameEqualTo(String value) {
            addCriterion("bank_sub_name =", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameNotEqualTo(String value) {
            addCriterion("bank_sub_name <>", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameGreaterThan(String value) {
            addCriterion("bank_sub_name >", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_sub_name >=", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameLessThan(String value) {
            addCriterion("bank_sub_name <", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameLessThanOrEqualTo(String value) {
            addCriterion("bank_sub_name <=", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameLike(String value) {
            addCriterion("bank_sub_name like", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameNotLike(String value) {
            addCriterion("bank_sub_name not like", value, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameIn(List<String> values) {
            addCriterion("bank_sub_name in", values, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameNotIn(List<String> values) {
            addCriterion("bank_sub_name not in", values, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameBetween(String value1, String value2) {
            addCriterion("bank_sub_name between", value1, value2, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andBankSubNameNotBetween(String value1, String value2) {
            addCriterion("bank_sub_name not between", value1, value2, "bankSubName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}