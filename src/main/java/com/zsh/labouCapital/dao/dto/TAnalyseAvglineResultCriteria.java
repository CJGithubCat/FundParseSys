package com.zsh.labouCapital.dao.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAnalyseAvglineResultCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected List<Criteria> oredCriteria;

    protected Integer limit;

    protected Integer offset;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public TAnalyseAvglineResultCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public BoundBuilder boundBuilder() {
        return new BoundBuilder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFundCodeIsNull() {
            addCriterion("fund_code is null");
            return (Criteria) this;
        }

        public Criteria andFundCodeIsNotNull() {
            addCriterion("fund_code is not null");
            return (Criteria) this;
        }

        public Criteria andFundCodeEqualTo(String value) {
            addCriterion("fund_code =", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotEqualTo(String value) {
            addCriterion("fund_code <>", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeGreaterThan(String value) {
            addCriterion("fund_code >", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fund_code >=", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLessThan(String value) {
            addCriterion("fund_code <", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLessThanOrEqualTo(String value) {
            addCriterion("fund_code <=", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeLike(String value) {
            addCriterion("fund_code like", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotLike(String value) {
            addCriterion("fund_code not like", value, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeIn(List<String> values) {
            addCriterion("fund_code in", values, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotIn(List<String> values) {
            addCriterion("fund_code not in", values, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeBetween(String value1, String value2) {
            addCriterion("fund_code between", value1, value2, "fundCode");
            return (Criteria) this;
        }

        public Criteria andFundCodeNotBetween(String value1, String value2) {
            addCriterion("fund_code not between", value1, value2, "fundCode");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyIsNull() {
            addCriterion("total_buy_money is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyIsNotNull() {
            addCriterion("total_buy_money is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyEqualTo(Double value) {
            addCriterion("total_buy_money =", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyNotEqualTo(Double value) {
            addCriterion("total_buy_money <>", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyGreaterThan(Double value) {
            addCriterion("total_buy_money >", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("total_buy_money >=", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyLessThan(Double value) {
            addCriterion("total_buy_money <", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyLessThanOrEqualTo(Double value) {
            addCriterion("total_buy_money <=", value, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyIn(List<Double> values) {
            addCriterion("total_buy_money in", values, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyNotIn(List<Double> values) {
            addCriterion("total_buy_money not in", values, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyBetween(Double value1, Double value2) {
            addCriterion("total_buy_money between", value1, value2, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyMoneyNotBetween(Double value1, Double value2) {
            addCriterion("total_buy_money not between", value1, value2, "totalBuyMoney");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIsNull() {
            addCriterion("total_buy_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIsNotNull() {
            addCriterion("total_buy_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumEqualTo(Double value) {
            addCriterion("total_buy_num =", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotEqualTo(Double value) {
            addCriterion("total_buy_num <>", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumGreaterThan(Double value) {
            addCriterion("total_buy_num >", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumGreaterThanOrEqualTo(Double value) {
            addCriterion("total_buy_num >=", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumLessThan(Double value) {
            addCriterion("total_buy_num <", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumLessThanOrEqualTo(Double value) {
            addCriterion("total_buy_num <=", value, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumIn(List<Double> values) {
            addCriterion("total_buy_num in", values, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotIn(List<Double> values) {
            addCriterion("total_buy_num not in", values, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumBetween(Double value1, Double value2) {
            addCriterion("total_buy_num between", value1, value2, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyNumNotBetween(Double value1, Double value2) {
            addCriterion("total_buy_num not between", value1, value2, "totalBuyNum");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeIsNull() {
            addCriterion("total_buy_fee is null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeIsNotNull() {
            addCriterion("total_buy_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeEqualTo(Double value) {
            addCriterion("total_buy_fee =", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeNotEqualTo(Double value) {
            addCriterion("total_buy_fee <>", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeGreaterThan(Double value) {
            addCriterion("total_buy_fee >", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("total_buy_fee >=", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeLessThan(Double value) {
            addCriterion("total_buy_fee <", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeLessThanOrEqualTo(Double value) {
            addCriterion("total_buy_fee <=", value, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeIn(List<Double> values) {
            addCriterion("total_buy_fee in", values, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeNotIn(List<Double> values) {
            addCriterion("total_buy_fee not in", values, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeBetween(Double value1, Double value2) {
            addCriterion("total_buy_fee between", value1, value2, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andTotalBuyFeeNotBetween(Double value1, Double value2) {
            addCriterion("total_buy_fee not between", value1, value2, "totalBuyFee");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthIsNull() {
            addCriterion("cost_net_worth is null");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthIsNotNull() {
            addCriterion("cost_net_worth is not null");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthEqualTo(Double value) {
            addCriterion("cost_net_worth =", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthNotEqualTo(Double value) {
            addCriterion("cost_net_worth <>", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthGreaterThan(Double value) {
            addCriterion("cost_net_worth >", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthGreaterThanOrEqualTo(Double value) {
            addCriterion("cost_net_worth >=", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthLessThan(Double value) {
            addCriterion("cost_net_worth <", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthLessThanOrEqualTo(Double value) {
            addCriterion("cost_net_worth <=", value, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthIn(List<Double> values) {
            addCriterion("cost_net_worth in", values, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthNotIn(List<Double> values) {
            addCriterion("cost_net_worth not in", values, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthBetween(Double value1, Double value2) {
            addCriterion("cost_net_worth between", value1, value2, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andCostNetWorthNotBetween(Double value1, Double value2) {
            addCriterion("cost_net_worth not between", value1, value2, "costNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthIsNull() {
            addCriterion("sale_net_worth is null");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthIsNotNull() {
            addCriterion("sale_net_worth is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthEqualTo(Double value) {
            addCriterion("sale_net_worth =", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthNotEqualTo(Double value) {
            addCriterion("sale_net_worth <>", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthGreaterThan(Double value) {
            addCriterion("sale_net_worth >", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthGreaterThanOrEqualTo(Double value) {
            addCriterion("sale_net_worth >=", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthLessThan(Double value) {
            addCriterion("sale_net_worth <", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthLessThanOrEqualTo(Double value) {
            addCriterion("sale_net_worth <=", value, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthIn(List<Double> values) {
            addCriterion("sale_net_worth in", values, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthNotIn(List<Double> values) {
            addCriterion("sale_net_worth not in", values, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthBetween(Double value1, Double value2) {
            addCriterion("sale_net_worth between", value1, value2, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andSaleNetWorthNotBetween(Double value1, Double value2) {
            addCriterion("sale_net_worth not between", value1, value2, "saleNetWorth");
            return (Criteria) this;
        }

        public Criteria andReturnRateIsNull() {
            addCriterion("return_rate is null");
            return (Criteria) this;
        }

        public Criteria andReturnRateIsNotNull() {
            addCriterion("return_rate is not null");
            return (Criteria) this;
        }

        public Criteria andReturnRateEqualTo(Double value) {
            addCriterion("return_rate =", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateNotEqualTo(Double value) {
            addCriterion("return_rate <>", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateGreaterThan(Double value) {
            addCriterion("return_rate >", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateGreaterThanOrEqualTo(Double value) {
            addCriterion("return_rate >=", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateLessThan(Double value) {
            addCriterion("return_rate <", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateLessThanOrEqualTo(Double value) {
            addCriterion("return_rate <=", value, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateIn(List<Double> values) {
            addCriterion("return_rate in", values, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateNotIn(List<Double> values) {
            addCriterion("return_rate not in", values, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateBetween(Double value1, Double value2) {
            addCriterion("return_rate between", value1, value2, "returnRate");
            return (Criteria) this;
        }

        public Criteria andReturnRateNotBetween(Double value1, Double value2) {
            addCriterion("return_rate not between", value1, value2, "returnRate");
            return (Criteria) this;
        }

        public Criteria andBuyModelIsNull() {
            addCriterion("buy_model is null");
            return (Criteria) this;
        }

        public Criteria andBuyModelIsNotNull() {
            addCriterion("buy_model is not null");
            return (Criteria) this;
        }

        public Criteria andBuyModelEqualTo(Integer value) {
            addCriterion("buy_model =", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelNotEqualTo(Integer value) {
            addCriterion("buy_model <>", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelGreaterThan(Integer value) {
            addCriterion("buy_model >", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_model >=", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelLessThan(Integer value) {
            addCriterion("buy_model <", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelLessThanOrEqualTo(Integer value) {
            addCriterion("buy_model <=", value, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelIn(List<Integer> values) {
            addCriterion("buy_model in", values, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelNotIn(List<Integer> values) {
            addCriterion("buy_model not in", values, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelBetween(Integer value1, Integer value2) {
            addCriterion("buy_model between", value1, value2, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyModelNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_model not between", value1, value2, "buyModel");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyIsNull() {
            addCriterion("buy_strategy is null");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyIsNotNull() {
            addCriterion("buy_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyEqualTo(String value) {
            addCriterion("buy_strategy =", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyNotEqualTo(String value) {
            addCriterion("buy_strategy <>", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyGreaterThan(String value) {
            addCriterion("buy_strategy >", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyGreaterThanOrEqualTo(String value) {
            addCriterion("buy_strategy >=", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyLessThan(String value) {
            addCriterion("buy_strategy <", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyLessThanOrEqualTo(String value) {
            addCriterion("buy_strategy <=", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyLike(String value) {
            addCriterion("buy_strategy like", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyNotLike(String value) {
            addCriterion("buy_strategy not like", value, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyIn(List<String> values) {
            addCriterion("buy_strategy in", values, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyNotIn(List<String> values) {
            addCriterion("buy_strategy not in", values, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyBetween(String value1, String value2) {
            addCriterion("buy_strategy between", value1, value2, "buyStrategy");
            return (Criteria) this;
        }

        public Criteria andBuyStrategyNotBetween(String value1, String value2) {
            addCriterion("buy_strategy not between", value1, value2, "buyStrategy");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated do_not_delete_during_merge Fri Oct 11 17:37:08 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_analyse_avgline_result
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
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

    public class BoundBuilder {
        protected Integer limit;

        protected Integer offset;

        public Integer limit() {
            return this.limit;
        }

        public BoundBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Integer offset() {
            return this.offset;
        }

        public BoundBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public TAnalyseAvglineResultCriteria build() {
            TAnalyseAvglineResultCriteria.this.limit = limit;
            TAnalyseAvglineResultCriteria.this.offset = offset;
            return TAnalyseAvglineResultCriteria.this;
        }
    }
}