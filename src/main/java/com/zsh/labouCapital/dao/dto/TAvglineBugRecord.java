package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TAvglineBugRecord implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.net_worth_id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private String netWorthId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.fund_code
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private String fundCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.date_info
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Date dateInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.net_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double netWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.avg_line_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double avgLineWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.diff_value
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double diffValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.avg_line_type
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Integer avgLineType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.bug_money
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double bugMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.bug_num
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double bugNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.bug_cost
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Double bugCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.buy_model
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Integer buyModel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.date_create
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.start_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Date startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.end_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_avgline_bug_record.batch_no
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private String batchNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.id
     *
     * @return the value of t_avgline_bug_record.id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.id
     *
     * @param id the value for t_avgline_bug_record.id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.net_worth_id
     *
     * @return the value of t_avgline_bug_record.net_worth_id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public String getNetWorthId() {
        return netWorthId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.net_worth_id
     *
     * @param netWorthId the value for t_avgline_bug_record.net_worth_id
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setNetWorthId(String netWorthId) {
        this.netWorthId = netWorthId == null ? null : netWorthId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.fund_code
     *
     * @return the value of t_avgline_bug_record.fund_code
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.fund_code
     *
     * @param fundCode the value for t_avgline_bug_record.fund_code
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.date_info
     *
     * @return the value of t_avgline_bug_record.date_info
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Date getDateInfo() {
        return dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.date_info
     *
     * @param dateInfo the value for t_avgline_bug_record.date_info
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.net_worth
     *
     * @return the value of t_avgline_bug_record.net_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getNetWorth() {
        return netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.net_worth
     *
     * @param netWorth the value for t_avgline_bug_record.net_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.avg_line_worth
     *
     * @return the value of t_avgline_bug_record.avg_line_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getAvgLineWorth() {
        return avgLineWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.avg_line_worth
     *
     * @param avgLineWorth the value for t_avgline_bug_record.avg_line_worth
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setAvgLineWorth(Double avgLineWorth) {
        this.avgLineWorth = avgLineWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.diff_value
     *
     * @return the value of t_avgline_bug_record.diff_value
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getDiffValue() {
        return diffValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.diff_value
     *
     * @param diffValue the value for t_avgline_bug_record.diff_value
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setDiffValue(Double diffValue) {
        this.diffValue = diffValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.avg_line_type
     *
     * @return the value of t_avgline_bug_record.avg_line_type
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Integer getAvgLineType() {
        return avgLineType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.avg_line_type
     *
     * @param avgLineType the value for t_avgline_bug_record.avg_line_type
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setAvgLineType(Integer avgLineType) {
        this.avgLineType = avgLineType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.bug_money
     *
     * @return the value of t_avgline_bug_record.bug_money
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getBugMoney() {
        return bugMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.bug_money
     *
     * @param bugMoney the value for t_avgline_bug_record.bug_money
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setBugMoney(Double bugMoney) {
        this.bugMoney = bugMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.bug_num
     *
     * @return the value of t_avgline_bug_record.bug_num
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getBugNum() {
        return bugNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.bug_num
     *
     * @param bugNum the value for t_avgline_bug_record.bug_num
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setBugNum(Double bugNum) {
        this.bugNum = bugNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.bug_cost
     *
     * @return the value of t_avgline_bug_record.bug_cost
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Double getBugCost() {
        return bugCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.bug_cost
     *
     * @param bugCost the value for t_avgline_bug_record.bug_cost
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setBugCost(Double bugCost) {
        this.bugCost = bugCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.buy_model
     *
     * @return the value of t_avgline_bug_record.buy_model
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Integer getBuyModel() {
        return buyModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.buy_model
     *
     * @param buyModel the value for t_avgline_bug_record.buy_model
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setBuyModel(Integer buyModel) {
        this.buyModel = buyModel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.date_create
     *
     * @return the value of t_avgline_bug_record.date_create
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.date_create
     *
     * @param dateCreate the value for t_avgline_bug_record.date_create
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.start_date
     *
     * @return the value of t_avgline_bug_record.start_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.start_date
     *
     * @param startDate the value for t_avgline_bug_record.start_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.end_date
     *
     * @return the value of t_avgline_bug_record.end_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.end_date
     *
     * @param endDate the value for t_avgline_bug_record.end_date
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_avgline_bug_record.batch_no
     *
     * @return the value of t_avgline_bug_record.batch_no
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_avgline_bug_record.batch_no
     *
     * @param batchNo the value for t_avgline_bug_record.batch_no
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", netWorthId=").append(netWorthId);
        sb.append(", fundCode=").append(fundCode);
        sb.append(", dateInfo=").append(dateInfo);
        sb.append(", netWorth=").append(netWorth);
        sb.append(", avgLineWorth=").append(avgLineWorth);
        sb.append(", diffValue=").append(diffValue);
        sb.append(", avgLineType=").append(avgLineType);
        sb.append(", bugMoney=").append(bugMoney);
        sb.append(", bugNum=").append(bugNum);
        sb.append(", bugCost=").append(bugCost);
        sb.append(", buyModel=").append(buyModel);
        sb.append(", dateCreate=").append(dateCreate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}