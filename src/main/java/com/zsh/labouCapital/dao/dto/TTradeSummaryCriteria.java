package com.zsh.labouCapital.dao.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTradeSummaryCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    protected List<Criteria> oredCriteria;

    protected Integer limit;

    protected Integer offset;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public TTradeSummaryCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
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
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
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
     * This class corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
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

        public Criteria andNowTotalCostIsNull() {
            addCriterion("now_total_cost is null");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostIsNotNull() {
            addCriterion("now_total_cost is not null");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostEqualTo(Double value) {
            addCriterion("now_total_cost =", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostNotEqualTo(Double value) {
            addCriterion("now_total_cost <>", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostGreaterThan(Double value) {
            addCriterion("now_total_cost >", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostGreaterThanOrEqualTo(Double value) {
            addCriterion("now_total_cost >=", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostLessThan(Double value) {
            addCriterion("now_total_cost <", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostLessThanOrEqualTo(Double value) {
            addCriterion("now_total_cost <=", value, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostIn(List<Double> values) {
            addCriterion("now_total_cost in", values, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostNotIn(List<Double> values) {
            addCriterion("now_total_cost not in", values, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostBetween(Double value1, Double value2) {
            addCriterion("now_total_cost between", value1, value2, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalCostNotBetween(Double value1, Double value2) {
            addCriterion("now_total_cost not between", value1, value2, "nowTotalCost");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumIsNull() {
            addCriterion("now_total_num is null");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumIsNotNull() {
            addCriterion("now_total_num is not null");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumEqualTo(Double value) {
            addCriterion("now_total_num =", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumNotEqualTo(Double value) {
            addCriterion("now_total_num <>", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumGreaterThan(Double value) {
            addCriterion("now_total_num >", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumGreaterThanOrEqualTo(Double value) {
            addCriterion("now_total_num >=", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumLessThan(Double value) {
            addCriterion("now_total_num <", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumLessThanOrEqualTo(Double value) {
            addCriterion("now_total_num <=", value, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumIn(List<Double> values) {
            addCriterion("now_total_num in", values, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumNotIn(List<Double> values) {
            addCriterion("now_total_num not in", values, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumBetween(Double value1, Double value2) {
            addCriterion("now_total_num between", value1, value2, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowTotalNumNotBetween(Double value1, Double value2) {
            addCriterion("now_total_num not between", value1, value2, "nowTotalNum");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostIsNull() {
            addCriterion("now_avg_cost is null");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostIsNotNull() {
            addCriterion("now_avg_cost is not null");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostEqualTo(Double value) {
            addCriterion("now_avg_cost =", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostNotEqualTo(Double value) {
            addCriterion("now_avg_cost <>", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostGreaterThan(Double value) {
            addCriterion("now_avg_cost >", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostGreaterThanOrEqualTo(Double value) {
            addCriterion("now_avg_cost >=", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostLessThan(Double value) {
            addCriterion("now_avg_cost <", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostLessThanOrEqualTo(Double value) {
            addCriterion("now_avg_cost <=", value, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostIn(List<Double> values) {
            addCriterion("now_avg_cost in", values, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostNotIn(List<Double> values) {
            addCriterion("now_avg_cost not in", values, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostBetween(Double value1, Double value2) {
            addCriterion("now_avg_cost between", value1, value2, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andNowAvgCostNotBetween(Double value1, Double value2) {
            addCriterion("now_avg_cost not between", value1, value2, "nowAvgCost");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNull() {
            addCriterion("date_info is null");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNotNull() {
            addCriterion("date_info is not null");
            return (Criteria) this;
        }

        public Criteria andDateInfoEqualTo(Date value) {
            addCriterion("date_info =", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotEqualTo(Date value) {
            addCriterion("date_info <>", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThan(Date value) {
            addCriterion("date_info >", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThanOrEqualTo(Date value) {
            addCriterion("date_info >=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThan(Date value) {
            addCriterion("date_info <", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThanOrEqualTo(Date value) {
            addCriterion("date_info <=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoIn(List<Date> values) {
            addCriterion("date_info in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotIn(List<Date> values) {
            addCriterion("date_info not in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoBetween(Date value1, Date value2) {
            addCriterion("date_info between", value1, value2, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotBetween(Date value1, Date value2) {
            addCriterion("date_info not between", value1, value2, "dateInfo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_summary
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 27 15:39:41 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_summary
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
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

        public TTradeSummaryCriteria build() {
            TTradeSummaryCriteria.this.limit = limit;
            TTradeSummaryCriteria.this.offset = offset;
            return TTradeSummaryCriteria.this;
        }
    }
}