package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TExpectFund implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String fundCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.fund_name
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String fundName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.avg_pe1
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double avgPe1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.avg_pe2
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double avgPe2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_expect_fund.is_enable
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer isEnable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_expect_fund
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.id
     *
     * @return the value of t_expect_fund.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.id
     *
     * @param id the value for t_expect_fund.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.fund_code
     *
     * @return the value of t_expect_fund.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.fund_code
     *
     * @param fundCode the value for t_expect_fund.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.index_code
     *
     * @return the value of t_expect_fund.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexCode() {
        return indexCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.index_code
     *
     * @param indexCode the value for t_expect_fund.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.fund_name
     *
     * @return the value of t_expect_fund.fund_name
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.fund_name
     *
     * @param fundName the value for t_expect_fund.fund_name
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.avg_pe1
     *
     * @return the value of t_expect_fund.avg_pe1
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getAvgPe1() {
        return avgPe1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.avg_pe1
     *
     * @param avgPe1 the value for t_expect_fund.avg_pe1
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setAvgPe1(Double avgPe1) {
        this.avgPe1 = avgPe1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.avg_pe2
     *
     * @return the value of t_expect_fund.avg_pe2
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getAvgPe2() {
        return avgPe2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.avg_pe2
     *
     * @param avgPe2 the value for t_expect_fund.avg_pe2
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setAvgPe2(Double avgPe2) {
        this.avgPe2 = avgPe2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.type
     *
     * @return the value of t_expect_fund.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.type
     *
     * @param type the value for t_expect_fund.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.date_create
     *
     * @return the value of t_expect_fund.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.date_create
     *
     * @param dateCreate the value for t_expect_fund.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_expect_fund.is_enable
     *
     * @return the value of t_expect_fund.is_enable
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_expect_fund.is_enable
     *
     * @param isEnable the value for t_expect_fund.is_enable
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fundCode=").append(fundCode);
        sb.append(", indexCode=").append(indexCode);
        sb.append(", fundName=").append(fundName);
        sb.append(", avgPe1=").append(avgPe1);
        sb.append(", avgPe2=").append(avgPe2);
        sb.append(", type=").append(type);
        sb.append(", dateCreate=").append(dateCreate);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}