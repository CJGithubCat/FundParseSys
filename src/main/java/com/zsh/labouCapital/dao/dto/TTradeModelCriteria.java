package com.zsh.labouCapital.dao.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTradeModelCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected List<Criteria> oredCriteria;

    protected Integer limit;

    protected Integer offset;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public TTradeModelCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
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
     * This method corresponds to the database table t_trade_model
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
     * This method corresponds to the database table t_trade_model
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_model
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
     * This class corresponds to the database table t_trade_model
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

        public Criteria andModelIdIsNull() {
            addCriterion("model_id is null");
            return (Criteria) this;
        }

        public Criteria andModelIdIsNotNull() {
            addCriterion("model_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelIdEqualTo(String value) {
            addCriterion("model_id =", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotEqualTo(String value) {
            addCriterion("model_id <>", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThan(String value) {
            addCriterion("model_id >", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThanOrEqualTo(String value) {
            addCriterion("model_id >=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThan(String value) {
            addCriterion("model_id <", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThanOrEqualTo(String value) {
            addCriterion("model_id <=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLike(String value) {
            addCriterion("model_id like", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotLike(String value) {
            addCriterion("model_id not like", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdIn(List<String> values) {
            addCriterion("model_id in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotIn(List<String> values) {
            addCriterion("model_id not in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdBetween(String value1, String value2) {
            addCriterion("model_id between", value1, value2, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotBetween(String value1, String value2) {
            addCriterion("model_id not between", value1, value2, "modelId");
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

        public Criteria andStrategyIdIsNull() {
            addCriterion("strategy_id is null");
            return (Criteria) this;
        }

        public Criteria andStrategyIdIsNotNull() {
            addCriterion("strategy_id is not null");
            return (Criteria) this;
        }

        public Criteria andStrategyIdEqualTo(String value) {
            addCriterion("strategy_id =", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdNotEqualTo(String value) {
            addCriterion("strategy_id <>", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdGreaterThan(String value) {
            addCriterion("strategy_id >", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdGreaterThanOrEqualTo(String value) {
            addCriterion("strategy_id >=", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdLessThan(String value) {
            addCriterion("strategy_id <", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdLessThanOrEqualTo(String value) {
            addCriterion("strategy_id <=", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdLike(String value) {
            addCriterion("strategy_id like", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdNotLike(String value) {
            addCriterion("strategy_id not like", value, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdIn(List<String> values) {
            addCriterion("strategy_id in", values, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdNotIn(List<String> values) {
            addCriterion("strategy_id not in", values, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdBetween(String value1, String value2) {
            addCriterion("strategy_id between", value1, value2, "strategyId");
            return (Criteria) this;
        }

        public Criteria andStrategyIdNotBetween(String value1, String value2) {
            addCriterion("strategy_id not between", value1, value2, "strategyId");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyIsNull() {
            addCriterion("buy_money is null");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyIsNotNull() {
            addCriterion("buy_money is not null");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyEqualTo(Double value) {
            addCriterion("buy_money =", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyNotEqualTo(Double value) {
            addCriterion("buy_money <>", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyGreaterThan(Double value) {
            addCriterion("buy_money >", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("buy_money >=", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyLessThan(Double value) {
            addCriterion("buy_money <", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyLessThanOrEqualTo(Double value) {
            addCriterion("buy_money <=", value, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyIn(List<Double> values) {
            addCriterion("buy_money in", values, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyNotIn(List<Double> values) {
            addCriterion("buy_money not in", values, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyBetween(Double value1, Double value2) {
            addCriterion("buy_money between", value1, value2, "buyMoney");
            return (Criteria) this;
        }

        public Criteria andBuyMoneyNotBetween(Double value1, Double value2) {
            addCriterion("buy_money not between", value1, value2, "buyMoney");
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

        public Criteria andSaleMoneyIsNull() {
            addCriterion("sale_money is null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIsNotNull() {
            addCriterion("sale_money is not null");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyEqualTo(Double value) {
            addCriterion("sale_money =", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotEqualTo(Double value) {
            addCriterion("sale_money <>", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThan(Double value) {
            addCriterion("sale_money >", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("sale_money >=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThan(Double value) {
            addCriterion("sale_money <", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyLessThanOrEqualTo(Double value) {
            addCriterion("sale_money <=", value, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyIn(List<Double> values) {
            addCriterion("sale_money in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotIn(List<Double> values) {
            addCriterion("sale_money not in", values, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyBetween(Double value1, Double value2) {
            addCriterion("sale_money between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andSaleMoneyNotBetween(Double value1, Double value2) {
            addCriterion("sale_money not between", value1, value2, "saleMoney");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumIsNull() {
            addCriterion("total_sale_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumIsNotNull() {
            addCriterion("total_sale_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumEqualTo(Double value) {
            addCriterion("total_sale_num =", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumNotEqualTo(Double value) {
            addCriterion("total_sale_num <>", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumGreaterThan(Double value) {
            addCriterion("total_sale_num >", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumGreaterThanOrEqualTo(Double value) {
            addCriterion("total_sale_num >=", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumLessThan(Double value) {
            addCriterion("total_sale_num <", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumLessThanOrEqualTo(Double value) {
            addCriterion("total_sale_num <=", value, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumIn(List<Double> values) {
            addCriterion("total_sale_num in", values, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumNotIn(List<Double> values) {
            addCriterion("total_sale_num not in", values, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumBetween(Double value1, Double value2) {
            addCriterion("total_sale_num between", value1, value2, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNumNotBetween(Double value1, Double value2) {
            addCriterion("total_sale_num not between", value1, value2, "totalSaleNum");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyIsNull() {
            addCriterion("earn_money is null");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyIsNotNull() {
            addCriterion("earn_money is not null");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyEqualTo(Double value) {
            addCriterion("earn_money =", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyNotEqualTo(Double value) {
            addCriterion("earn_money <>", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyGreaterThan(Double value) {
            addCriterion("earn_money >", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("earn_money >=", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyLessThan(Double value) {
            addCriterion("earn_money <", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyLessThanOrEqualTo(Double value) {
            addCriterion("earn_money <=", value, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyIn(List<Double> values) {
            addCriterion("earn_money in", values, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyNotIn(List<Double> values) {
            addCriterion("earn_money not in", values, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyBetween(Double value1, Double value2) {
            addCriterion("earn_money between", value1, value2, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnMoneyNotBetween(Double value1, Double value2) {
            addCriterion("earn_money not between", value1, value2, "earnMoney");
            return (Criteria) this;
        }

        public Criteria andEarnRateIsNull() {
            addCriterion("earn_rate is null");
            return (Criteria) this;
        }

        public Criteria andEarnRateIsNotNull() {
            addCriterion("earn_rate is not null");
            return (Criteria) this;
        }

        public Criteria andEarnRateEqualTo(Double value) {
            addCriterion("earn_rate =", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateNotEqualTo(Double value) {
            addCriterion("earn_rate <>", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateGreaterThan(Double value) {
            addCriterion("earn_rate >", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateGreaterThanOrEqualTo(Double value) {
            addCriterion("earn_rate >=", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateLessThan(Double value) {
            addCriterion("earn_rate <", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateLessThanOrEqualTo(Double value) {
            addCriterion("earn_rate <=", value, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateIn(List<Double> values) {
            addCriterion("earn_rate in", values, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateNotIn(List<Double> values) {
            addCriterion("earn_rate not in", values, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateBetween(Double value1, Double value2) {
            addCriterion("earn_rate between", value1, value2, "earnRate");
            return (Criteria) this;
        }

        public Criteria andEarnRateNotBetween(Double value1, Double value2) {
            addCriterion("earn_rate not between", value1, value2, "earnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateIsNull() {
            addCriterion("year_earn_rate is null");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateIsNotNull() {
            addCriterion("year_earn_rate is not null");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateEqualTo(Double value) {
            addCriterion("year_earn_rate =", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateNotEqualTo(Double value) {
            addCriterion("year_earn_rate <>", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateGreaterThan(Double value) {
            addCriterion("year_earn_rate >", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateGreaterThanOrEqualTo(Double value) {
            addCriterion("year_earn_rate >=", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateLessThan(Double value) {
            addCriterion("year_earn_rate <", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateLessThanOrEqualTo(Double value) {
            addCriterion("year_earn_rate <=", value, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateIn(List<Double> values) {
            addCriterion("year_earn_rate in", values, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateNotIn(List<Double> values) {
            addCriterion("year_earn_rate not in", values, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateBetween(Double value1, Double value2) {
            addCriterion("year_earn_rate between", value1, value2, "yearEarnRate");
            return (Criteria) this;
        }

        public Criteria andYearEarnRateNotBetween(Double value1, Double value2) {
            addCriterion("year_earn_rate not between", value1, value2, "yearEarnRate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_model
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
     * This class corresponds to the database table t_trade_model
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

        public TTradeModelCriteria build() {
            TTradeModelCriteria.this.limit = limit;
            TTradeModelCriteria.this.offset = offset;
            return TTradeModelCriteria.this;
        }
    }
}