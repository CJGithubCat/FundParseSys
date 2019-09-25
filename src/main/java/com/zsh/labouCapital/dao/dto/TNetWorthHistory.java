package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TNetWorthHistory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.fund_code
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private String fundCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.fund_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private String fundName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.date_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Date dateInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.net_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double netWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.avg_line30
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double avgLine30;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.avg_line60
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double avgLine60;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.avg_line90
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double avgLine90;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.avg_line120
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double avgLine120;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.avg_line240
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double avgLine240;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.equity_return
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double equityReturn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.unit_money
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private String unitMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.week_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Integer weekInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.networth_daygrow_rate
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double networthDaygrowRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_net_worth_history.addup_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Double addupWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_net_worth_history
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.id
     *
     * @return the value of t_net_worth_history.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.id
     *
     * @param id the value for t_net_worth_history.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.fund_code
     *
     * @return the value of t_net_worth_history.fund_code
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.fund_code
     *
     * @param fundCode the value for t_net_worth_history.fund_code
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.fund_name
     *
     * @return the value of t_net_worth_history.fund_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.fund_name
     *
     * @param fundName the value for t_net_worth_history.fund_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.date_info
     *
     * @return the value of t_net_worth_history.date_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Date getDateInfo() {
        return dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.date_info
     *
     * @param dateInfo the value for t_net_worth_history.date_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.net_worth
     *
     * @return the value of t_net_worth_history.net_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getNetWorth() {
        return netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.net_worth
     *
     * @param netWorth the value for t_net_worth_history.net_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.avg_line30
     *
     * @return the value of t_net_worth_history.avg_line30
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAvgLine30() {
        return avgLine30;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.avg_line30
     *
     * @param avgLine30 the value for t_net_worth_history.avg_line30
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAvgLine30(Double avgLine30) {
        this.avgLine30 = avgLine30;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.avg_line60
     *
     * @return the value of t_net_worth_history.avg_line60
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAvgLine60() {
        return avgLine60;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.avg_line60
     *
     * @param avgLine60 the value for t_net_worth_history.avg_line60
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAvgLine60(Double avgLine60) {
        this.avgLine60 = avgLine60;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.avg_line90
     *
     * @return the value of t_net_worth_history.avg_line90
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAvgLine90() {
        return avgLine90;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.avg_line90
     *
     * @param avgLine90 the value for t_net_worth_history.avg_line90
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAvgLine90(Double avgLine90) {
        this.avgLine90 = avgLine90;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.avg_line120
     *
     * @return the value of t_net_worth_history.avg_line120
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAvgLine120() {
        return avgLine120;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.avg_line120
     *
     * @param avgLine120 the value for t_net_worth_history.avg_line120
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAvgLine120(Double avgLine120) {
        this.avgLine120 = avgLine120;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.avg_line240
     *
     * @return the value of t_net_worth_history.avg_line240
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAvgLine240() {
        return avgLine240;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.avg_line240
     *
     * @param avgLine240 the value for t_net_worth_history.avg_line240
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAvgLine240(Double avgLine240) {
        this.avgLine240 = avgLine240;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.equity_return
     *
     * @return the value of t_net_worth_history.equity_return
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getEquityReturn() {
        return equityReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.equity_return
     *
     * @param equityReturn the value for t_net_worth_history.equity_return
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setEquityReturn(Double equityReturn) {
        this.equityReturn = equityReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.unit_money
     *
     * @return the value of t_net_worth_history.unit_money
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public String getUnitMoney() {
        return unitMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.unit_money
     *
     * @param unitMoney the value for t_net_worth_history.unit_money
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setUnitMoney(String unitMoney) {
        this.unitMoney = unitMoney == null ? null : unitMoney.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.week_info
     *
     * @return the value of t_net_worth_history.week_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Integer getWeekInfo() {
        return weekInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.week_info
     *
     * @param weekInfo the value for t_net_worth_history.week_info
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setWeekInfo(Integer weekInfo) {
        this.weekInfo = weekInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.date_create
     *
     * @return the value of t_net_worth_history.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.date_create
     *
     * @param dateCreate the value for t_net_worth_history.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.networth_daygrow_rate
     *
     * @return the value of t_net_worth_history.networth_daygrow_rate
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getNetworthDaygrowRate() {
        return networthDaygrowRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.networth_daygrow_rate
     *
     * @param networthDaygrowRate the value for t_net_worth_history.networth_daygrow_rate
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setNetworthDaygrowRate(Double networthDaygrowRate) {
        this.networthDaygrowRate = networthDaygrowRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_net_worth_history.addup_worth
     *
     * @return the value of t_net_worth_history.addup_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Double getAddupWorth() {
        return addupWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_net_worth_history.addup_worth
     *
     * @param addupWorth the value for t_net_worth_history.addup_worth
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setAddupWorth(Double addupWorth) {
        this.addupWorth = addupWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_history
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fundCode=").append(fundCode);
        sb.append(", fundName=").append(fundName);
        sb.append(", dateInfo=").append(dateInfo);
        sb.append(", netWorth=").append(netWorth);
        sb.append(", avgLine30=").append(avgLine30);
        sb.append(", avgLine60=").append(avgLine60);
        sb.append(", avgLine90=").append(avgLine90);
        sb.append(", avgLine120=").append(avgLine120);
        sb.append(", avgLine240=").append(avgLine240);
        sb.append(", equityReturn=").append(equityReturn);
        sb.append(", unitMoney=").append(unitMoney);
        sb.append(", weekInfo=").append(weekInfo);
        sb.append(", dateCreate=").append(dateCreate);
        sb.append(", networthDaygrowRate=").append(networthDaygrowRate);
        sb.append(", addupWorth=").append(addupWorth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}