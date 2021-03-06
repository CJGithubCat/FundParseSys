package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class THoldFundCopy implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String fundCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double netWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.buy_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double buyMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.buy_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double buyNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.buy_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date buyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.last_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double lastAvgCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.real_net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double realNetWorth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.grow_rate
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double growRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.now_total_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double nowTotalNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_hold_fund_copy.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double nowAvgCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_hold_fund_copy
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.id
     *
     * @return the value of t_hold_fund_copy.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.id
     *
     * @param id the value for t_hold_fund_copy.id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.fund_code
     *
     * @return the value of t_hold_fund_copy.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.fund_code
     *
     * @param fundCode the value for t_hold_fund_copy.fund_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.net_worth
     *
     * @return the value of t_hold_fund_copy.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getNetWorth() {
        return netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.net_worth
     *
     * @param netWorth the value for t_hold_fund_copy.net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.buy_money
     *
     * @return the value of t_hold_fund_copy.buy_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getBuyMoney() {
        return buyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.buy_money
     *
     * @param buyMoney the value for t_hold_fund_copy.buy_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.buy_num
     *
     * @return the value of t_hold_fund_copy.buy_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getBuyNum() {
        return buyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.buy_num
     *
     * @param buyNum the value for t_hold_fund_copy.buy_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setBuyNum(Double buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.buy_date
     *
     * @return the value of t_hold_fund_copy.buy_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getBuyDate() {
        return buyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.buy_date
     *
     * @param buyDate the value for t_hold_fund_copy.buy_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.last_avg_cost
     *
     * @return the value of t_hold_fund_copy.last_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getLastAvgCost() {
        return lastAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.last_avg_cost
     *
     * @param lastAvgCost the value for t_hold_fund_copy.last_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setLastAvgCost(Double lastAvgCost) {
        this.lastAvgCost = lastAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.real_net_worth
     *
     * @return the value of t_hold_fund_copy.real_net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getRealNetWorth() {
        return realNetWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.real_net_worth
     *
     * @param realNetWorth the value for t_hold_fund_copy.real_net_worth
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setRealNetWorth(Double realNetWorth) {
        this.realNetWorth = realNetWorth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.grow_rate
     *
     * @return the value of t_hold_fund_copy.grow_rate
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getGrowRate() {
        return growRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.grow_rate
     *
     * @param growRate the value for t_hold_fund_copy.grow_rate
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setGrowRate(Double growRate) {
        this.growRate = growRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.type
     *
     * @return the value of t_hold_fund_copy.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.type
     *
     * @param type the value for t_hold_fund_copy.type
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.now_total_num
     *
     * @return the value of t_hold_fund_copy.now_total_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getNowTotalNum() {
        return nowTotalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.now_total_num
     *
     * @param nowTotalNum the value for t_hold_fund_copy.now_total_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setNowTotalNum(Double nowTotalNum) {
        this.nowTotalNum = nowTotalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_hold_fund_copy.now_avg_cost
     *
     * @return the value of t_hold_fund_copy.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getNowAvgCost() {
        return nowAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_hold_fund_copy.now_avg_cost
     *
     * @param nowAvgCost the value for t_hold_fund_copy.now_avg_cost
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setNowAvgCost(Double nowAvgCost) {
        this.nowAvgCost = nowAvgCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_hold_fund_copy
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
        sb.append(", netWorth=").append(netWorth);
        sb.append(", buyMoney=").append(buyMoney);
        sb.append(", buyNum=").append(buyNum);
        sb.append(", buyDate=").append(buyDate);
        sb.append(", lastAvgCost=").append(lastAvgCost);
        sb.append(", realNetWorth=").append(realNetWorth);
        sb.append(", growRate=").append(growRate);
        sb.append(", type=").append(type);
        sb.append(", nowTotalNum=").append(nowTotalNum);
        sb.append(", nowAvgCost=").append(nowAvgCost);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}