package com.sc.act.api.model.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccSepRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccSepRecordExample() {
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

        public Criteria andAccSepRecordIdIsNull() {
            addCriterion("acc_sep_record_id is null");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdIsNotNull() {
            addCriterion("acc_sep_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdEqualTo(Integer value) {
            addCriterion("acc_sep_record_id =", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdNotEqualTo(Integer value) {
            addCriterion("acc_sep_record_id <>", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdGreaterThan(Integer value) {
            addCriterion("acc_sep_record_id >", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("acc_sep_record_id >=", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdLessThan(Integer value) {
            addCriterion("acc_sep_record_id <", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("acc_sep_record_id <=", value, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdIn(List<Integer> values) {
            addCriterion("acc_sep_record_id in", values, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdNotIn(List<Integer> values) {
            addCriterion("acc_sep_record_id not in", values, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("acc_sep_record_id between", value1, value2, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andAccSepRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("acc_sep_record_id not between", value1, value2, "accSepRecordId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdIsNull() {
            addCriterion("user_acc_info_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdIsNotNull() {
            addCriterion("user_acc_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdEqualTo(Integer value) {
            addCriterion("user_acc_info_id =", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdNotEqualTo(Integer value) {
            addCriterion("user_acc_info_id <>", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdGreaterThan(Integer value) {
            addCriterion("user_acc_info_id >", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_acc_info_id >=", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdLessThan(Integer value) {
            addCriterion("user_acc_info_id <", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_acc_info_id <=", value, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdIn(List<Integer> values) {
            addCriterion("user_acc_info_id in", values, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdNotIn(List<Integer> values) {
            addCriterion("user_acc_info_id not in", values, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("user_acc_info_id between", value1, value2, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andUserAccInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_acc_info_id not between", value1, value2, "userAccInfoId");
            return (Criteria) this;
        }

        public Criteria andPoCardNameIsNull() {
            addCriterion("po_card_name is null");
            return (Criteria) this;
        }

        public Criteria andPoCardNameIsNotNull() {
            addCriterion("po_card_name is not null");
            return (Criteria) this;
        }

        public Criteria andPoCardNameEqualTo(String value) {
            addCriterion("po_card_name =", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameNotEqualTo(String value) {
            addCriterion("po_card_name <>", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameGreaterThan(String value) {
            addCriterion("po_card_name >", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameGreaterThanOrEqualTo(String value) {
            addCriterion("po_card_name >=", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameLessThan(String value) {
            addCriterion("po_card_name <", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameLessThanOrEqualTo(String value) {
            addCriterion("po_card_name <=", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameLike(String value) {
            addCriterion("po_card_name like", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameNotLike(String value) {
            addCriterion("po_card_name not like", value, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameIn(List<String> values) {
            addCriterion("po_card_name in", values, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameNotIn(List<String> values) {
            addCriterion("po_card_name not in", values, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameBetween(String value1, String value2) {
            addCriterion("po_card_name between", value1, value2, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoCardNameNotBetween(String value1, String value2) {
            addCriterion("po_card_name not between", value1, value2, "poCardName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameIsNull() {
            addCriterion("po_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andPoBankNameIsNotNull() {
            addCriterion("po_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andPoBankNameEqualTo(String value) {
            addCriterion("po_bank_name =", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameNotEqualTo(String value) {
            addCriterion("po_bank_name <>", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameGreaterThan(String value) {
            addCriterion("po_bank_name >", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("po_bank_name >=", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameLessThan(String value) {
            addCriterion("po_bank_name <", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameLessThanOrEqualTo(String value) {
            addCriterion("po_bank_name <=", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameLike(String value) {
            addCriterion("po_bank_name like", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameNotLike(String value) {
            addCriterion("po_bank_name not like", value, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameIn(List<String> values) {
            addCriterion("po_bank_name in", values, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameNotIn(List<String> values) {
            addCriterion("po_bank_name not in", values, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameBetween(String value1, String value2) {
            addCriterion("po_bank_name between", value1, value2, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoBankNameNotBetween(String value1, String value2) {
            addCriterion("po_bank_name not between", value1, value2, "poBankName");
            return (Criteria) this;
        }

        public Criteria andPoCardNoIsNull() {
            addCriterion("po_card_no is null");
            return (Criteria) this;
        }

        public Criteria andPoCardNoIsNotNull() {
            addCriterion("po_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andPoCardNoEqualTo(String value) {
            addCriterion("po_card_no =", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoNotEqualTo(String value) {
            addCriterion("po_card_no <>", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoGreaterThan(String value) {
            addCriterion("po_card_no >", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("po_card_no >=", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoLessThan(String value) {
            addCriterion("po_card_no <", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoLessThanOrEqualTo(String value) {
            addCriterion("po_card_no <=", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoLike(String value) {
            addCriterion("po_card_no like", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoNotLike(String value) {
            addCriterion("po_card_no not like", value, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoIn(List<String> values) {
            addCriterion("po_card_no in", values, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoNotIn(List<String> values) {
            addCriterion("po_card_no not in", values, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoBetween(String value1, String value2) {
            addCriterion("po_card_no between", value1, value2, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPoCardNoNotBetween(String value1, String value2) {
            addCriterion("po_card_no not between", value1, value2, "poCardNo");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountIsNull() {
            addCriterion("payout_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountIsNotNull() {
            addCriterion("payout_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountEqualTo(Integer value) {
            addCriterion("payout_amount =", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountNotEqualTo(Integer value) {
            addCriterion("payout_amount <>", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountGreaterThan(Integer value) {
            addCriterion("payout_amount >", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("payout_amount >=", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountLessThan(Integer value) {
            addCriterion("payout_amount <", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountLessThanOrEqualTo(Integer value) {
            addCriterion("payout_amount <=", value, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountIn(List<Integer> values) {
            addCriterion("payout_amount in", values, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountNotIn(List<Integer> values) {
            addCriterion("payout_amount not in", values, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountBetween(Integer value1, Integer value2) {
            addCriterion("payout_amount between", value1, value2, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andPayoutAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("payout_amount not between", value1, value2, "payoutAmount");
            return (Criteria) this;
        }

        public Criteria andCardNameIsNull() {
            addCriterion("card_name is null");
            return (Criteria) this;
        }

        public Criteria andCardNameIsNotNull() {
            addCriterion("card_name is not null");
            return (Criteria) this;
        }

        public Criteria andCardNameEqualTo(String value) {
            addCriterion("card_name =", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotEqualTo(String value) {
            addCriterion("card_name <>", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameGreaterThan(String value) {
            addCriterion("card_name >", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameGreaterThanOrEqualTo(String value) {
            addCriterion("card_name >=", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLessThan(String value) {
            addCriterion("card_name <", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLessThanOrEqualTo(String value) {
            addCriterion("card_name <=", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameLike(String value) {
            addCriterion("card_name like", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotLike(String value) {
            addCriterion("card_name not like", value, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameIn(List<String> values) {
            addCriterion("card_name in", values, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotIn(List<String> values) {
            addCriterion("card_name not in", values, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameBetween(String value1, String value2) {
            addCriterion("card_name between", value1, value2, "cardName");
            return (Criteria) this;
        }

        public Criteria andCardNameNotBetween(String value1, String value2) {
            addCriterion("card_name not between", value1, value2, "cardName");
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

        public Criteria andCardNumberIsNull() {
            addCriterion("card_number is null");
            return (Criteria) this;
        }

        public Criteria andCardNumberIsNotNull() {
            addCriterion("card_number is not null");
            return (Criteria) this;
        }

        public Criteria andCardNumberEqualTo(String value) {
            addCriterion("card_number =", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotEqualTo(String value) {
            addCriterion("card_number <>", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThan(String value) {
            addCriterion("card_number >", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("card_number >=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThan(String value) {
            addCriterion("card_number <", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThanOrEqualTo(String value) {
            addCriterion("card_number <=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLike(String value) {
            addCriterion("card_number like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotLike(String value) {
            addCriterion("card_number not like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberIn(List<String> values) {
            addCriterion("card_number in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotIn(List<String> values) {
            addCriterion("card_number not in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberBetween(String value1, String value2) {
            addCriterion("card_number between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotBetween(String value1, String value2) {
            addCriterion("card_number not between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNull() {
            addCriterion("income_amount is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIsNotNull() {
            addCriterion("income_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountEqualTo(Integer value) {
            addCriterion("income_amount =", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotEqualTo(Integer value) {
            addCriterion("income_amount <>", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThan(Integer value) {
            addCriterion("income_amount >", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_amount >=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThan(Integer value) {
            addCriterion("income_amount <", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("income_amount <=", value, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountIn(List<Integer> values) {
            addCriterion("income_amount in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotIn(List<Integer> values) {
            addCriterion("income_amount not in", values, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountBetween(Integer value1, Integer value2) {
            addCriterion("income_amount between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andIncomeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("income_amount not between", value1, value2, "incomeAmount");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNull() {
            addCriterion("out_order_id is null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIsNotNull() {
            addCriterion("out_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdEqualTo(Integer value) {
            addCriterion("out_order_id =", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotEqualTo(Integer value) {
            addCriterion("out_order_id <>", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThan(Integer value) {
            addCriterion("out_order_id >", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("out_order_id >=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThan(Integer value) {
            addCriterion("out_order_id <", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("out_order_id <=", value, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdIn(List<Integer> values) {
            addCriterion("out_order_id in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotIn(List<Integer> values) {
            addCriterion("out_order_id not in", values, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("out_order_id between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("out_order_id not between", value1, value2, "outOrderId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdIsNull() {
            addCriterion("out_product_id is null");
            return (Criteria) this;
        }

        public Criteria andOutProductIdIsNotNull() {
            addCriterion("out_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutProductIdEqualTo(Integer value) {
            addCriterion("out_product_id =", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdNotEqualTo(Integer value) {
            addCriterion("out_product_id <>", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdGreaterThan(Integer value) {
            addCriterion("out_product_id >", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("out_product_id >=", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdLessThan(Integer value) {
            addCriterion("out_product_id <", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("out_product_id <=", value, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdIn(List<Integer> values) {
            addCriterion("out_product_id in", values, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdNotIn(List<Integer> values) {
            addCriterion("out_product_id not in", values, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdBetween(Integer value1, Integer value2) {
            addCriterion("out_product_id between", value1, value2, "outProductId");
            return (Criteria) this;
        }

        public Criteria andOutProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("out_product_id not between", value1, value2, "outProductId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoIsNull() {
            addCriterion("handler_seq_no is null");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoIsNotNull() {
            addCriterion("handler_seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoEqualTo(String value) {
            addCriterion("handler_seq_no =", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoNotEqualTo(String value) {
            addCriterion("handler_seq_no <>", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoGreaterThan(String value) {
            addCriterion("handler_seq_no >", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("handler_seq_no >=", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoLessThan(String value) {
            addCriterion("handler_seq_no <", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoLessThanOrEqualTo(String value) {
            addCriterion("handler_seq_no <=", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoLike(String value) {
            addCriterion("handler_seq_no like", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoNotLike(String value) {
            addCriterion("handler_seq_no not like", value, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoIn(List<String> values) {
            addCriterion("handler_seq_no in", values, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoNotIn(List<String> values) {
            addCriterion("handler_seq_no not in", values, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoBetween(String value1, String value2) {
            addCriterion("handler_seq_no between", value1, value2, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andHandlerSeqNoNotBetween(String value1, String value2) {
            addCriterion("handler_seq_no not between", value1, value2, "handlerSeqNo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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