package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TTradeDetail implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.model_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String modelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String fundCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.date_info
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date dateInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double netWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.starnd_value
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double starndValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.trade_type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer tradeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.trade_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double tradeMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.trade_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double tradeNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double nowAvgCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trade_detail.trade_strage
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String tradeStrage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.id
     *
     * @return the value of t_trade_detail.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.id
     *
     * @param id the value for t_trade_detail.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.model_id
     *
     * @return the value of t_trade_detail.model_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.model_id
     *
     * @param modelId the value for t_trade_detail.model_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.fund_code
     *
     * @return the value of t_trade_detail.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.fund_code
     *
     * @param fundCode the value for t_trade_detail.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.date_info
     *
     * @return the value of t_trade_detail.date_info
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getDateInfo() {
        return dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.date_info
     *
     * @param dateInfo the value for t_trade_detail.date_info
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.net_worth
     *
     * @return the value of t_trade_detail.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getNetWorth() {
        return netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.net_worth
     *
     * @param netWorth the value for t_trade_detail.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.starnd_value
     *
     * @return the value of t_trade_detail.starnd_value
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getStarndValue() {
        return starndValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.starnd_value
     *
     * @param starndValue the value for t_trade_detail.starnd_value
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setStarndValue(Double starndValue) {
        this.starndValue = starndValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.trade_type
     *
     * @return the value of t_trade_detail.trade_type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getTradeType() {
        return tradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.trade_type
     *
     * @param tradeType the value for t_trade_detail.trade_type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.trade_money
     *
     * @return the value of t_trade_detail.trade_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getTradeMoney() {
        return tradeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.trade_money
     *
     * @param tradeMoney the value for t_trade_detail.trade_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setTradeMoney(Double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.trade_num
     *
     * @return the value of t_trade_detail.trade_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getTradeNum() {
        return tradeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.trade_num
     *
     * @param tradeNum the value for t_trade_detail.trade_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setTradeNum(Double tradeNum) {
        this.tradeNum = tradeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.now_avg_cost
     *
     * @return the value of t_trade_detail.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getNowAvgCost() {
        return nowAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.now_avg_cost
     *
     * @param nowAvgCost the value for t_trade_detail.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setNowAvgCost(Double nowAvgCost) {
        this.nowAvgCost = nowAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trade_detail.trade_strage
     *
     * @return the value of t_trade_detail.trade_strage
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getTradeStrage() {
        return tradeStrage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trade_detail.trade_strage
     *
     * @param tradeStrage the value for t_trade_detail.trade_strage
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setTradeStrage(String tradeStrage) {
        this.tradeStrage = tradeStrage == null ? null : tradeStrage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
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
        sb.append(", modelId=").append(modelId);
        sb.append(", fundCode=").append(fundCode);
        sb.append(", dateInfo=").append(dateInfo);
        sb.append(", netWorth=").append(netWorth);
        sb.append(", starndValue=").append(starndValue);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", tradeMoney=").append(tradeMoney);
        sb.append(", tradeNum=").append(tradeNum);
        sb.append(", nowAvgCost=").append(nowAvgCost);
        sb.append(", tradeStrage=").append(tradeStrage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}