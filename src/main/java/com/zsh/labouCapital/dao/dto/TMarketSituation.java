package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TMarketSituation extends TMarketSituationKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.index_fname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexFname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexSname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.open_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String openPoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.highest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String highestPoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.lowest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String lowestPoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.close_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String closePoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.rise_fall
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String riseFall;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.rise_fall_range
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String riseFallRange;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.deal_amount
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String dealAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.deal_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String dealMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.stock_member_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer stockMemberNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.pe1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double pe1Ratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.pe2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double pe2Ratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.dp1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double dp1Ratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.dp2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double dp2Ratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.earn_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double earnRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_market_situation.date_update
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date dateUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.index_fname
     *
     * @return the value of t_market_situation.index_fname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexFname() {
        return indexFname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.index_fname
     *
     * @param indexFname the value for t_market_situation.index_fname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexFname(String indexFname) {
        this.indexFname = indexFname == null ? null : indexFname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.index_sname
     *
     * @return the value of t_market_situation.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexSname() {
        return indexSname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.index_sname
     *
     * @param indexSname the value for t_market_situation.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexSname(String indexSname) {
        this.indexSname = indexSname == null ? null : indexSname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.open_point
     *
     * @return the value of t_market_situation.open_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getOpenPoint() {
        return openPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.open_point
     *
     * @param openPoint the value for t_market_situation.open_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setOpenPoint(String openPoint) {
        this.openPoint = openPoint == null ? null : openPoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.highest_point
     *
     * @return the value of t_market_situation.highest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getHighestPoint() {
        return highestPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.highest_point
     *
     * @param highestPoint the value for t_market_situation.highest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setHighestPoint(String highestPoint) {
        this.highestPoint = highestPoint == null ? null : highestPoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.lowest_point
     *
     * @return the value of t_market_situation.lowest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getLowestPoint() {
        return lowestPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.lowest_point
     *
     * @param lowestPoint the value for t_market_situation.lowest_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setLowestPoint(String lowestPoint) {
        this.lowestPoint = lowestPoint == null ? null : lowestPoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.close_point
     *
     * @return the value of t_market_situation.close_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getClosePoint() {
        return closePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.close_point
     *
     * @param closePoint the value for t_market_situation.close_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setClosePoint(String closePoint) {
        this.closePoint = closePoint == null ? null : closePoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.rise_fall
     *
     * @return the value of t_market_situation.rise_fall
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getRiseFall() {
        return riseFall;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.rise_fall
     *
     * @param riseFall the value for t_market_situation.rise_fall
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setRiseFall(String riseFall) {
        this.riseFall = riseFall == null ? null : riseFall.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.rise_fall_range
     *
     * @return the value of t_market_situation.rise_fall_range
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getRiseFallRange() {
        return riseFallRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.rise_fall_range
     *
     * @param riseFallRange the value for t_market_situation.rise_fall_range
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setRiseFallRange(String riseFallRange) {
        this.riseFallRange = riseFallRange == null ? null : riseFallRange.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.deal_amount
     *
     * @return the value of t_market_situation.deal_amount
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getDealAmount() {
        return dealAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.deal_amount
     *
     * @param dealAmount the value for t_market_situation.deal_amount
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount == null ? null : dealAmount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.deal_money
     *
     * @return the value of t_market_situation.deal_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getDealMoney() {
        return dealMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.deal_money
     *
     * @param dealMoney the value for t_market_situation.deal_money
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDealMoney(String dealMoney) {
        this.dealMoney = dealMoney == null ? null : dealMoney.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.stock_member_num
     *
     * @return the value of t_market_situation.stock_member_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getStockMemberNum() {
        return stockMemberNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.stock_member_num
     *
     * @param stockMemberNum the value for t_market_situation.stock_member_num
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setStockMemberNum(Integer stockMemberNum) {
        this.stockMemberNum = stockMemberNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.pe1_ratio
     *
     * @return the value of t_market_situation.pe1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getPe1Ratio() {
        return pe1Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.pe1_ratio
     *
     * @param pe1Ratio the value for t_market_situation.pe1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setPe1Ratio(Double pe1Ratio) {
        this.pe1Ratio = pe1Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.pe2_ratio
     *
     * @return the value of t_market_situation.pe2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getPe2Ratio() {
        return pe2Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.pe2_ratio
     *
     * @param pe2Ratio the value for t_market_situation.pe2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setPe2Ratio(Double pe2Ratio) {
        this.pe2Ratio = pe2Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.dp1_ratio
     *
     * @return the value of t_market_situation.dp1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getDp1Ratio() {
        return dp1Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.dp1_ratio
     *
     * @param dp1Ratio the value for t_market_situation.dp1_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDp1Ratio(Double dp1Ratio) {
        this.dp1Ratio = dp1Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.dp2_ratio
     *
     * @return the value of t_market_situation.dp2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getDp2Ratio() {
        return dp2Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.dp2_ratio
     *
     * @param dp2Ratio the value for t_market_situation.dp2_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDp2Ratio(Double dp2Ratio) {
        this.dp2Ratio = dp2Ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.earn_ratio
     *
     * @return the value of t_market_situation.earn_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getEarnRatio() {
        return earnRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.earn_ratio
     *
     * @param earnRatio the value for t_market_situation.earn_ratio
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setEarnRatio(Double earnRatio) {
        this.earnRatio = earnRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_market_situation.date_update
     *
     * @return the value of t_market_situation.date_update
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_market_situation.date_update
     *
     * @param dateUpdate the value for t_market_situation.date_update
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indexFname=").append(indexFname);
        sb.append(", indexSname=").append(indexSname);
        sb.append(", openPoint=").append(openPoint);
        sb.append(", highestPoint=").append(highestPoint);
        sb.append(", lowestPoint=").append(lowestPoint);
        sb.append(", closePoint=").append(closePoint);
        sb.append(", riseFall=").append(riseFall);
        sb.append(", riseFallRange=").append(riseFallRange);
        sb.append(", dealAmount=").append(dealAmount);
        sb.append(", dealMoney=").append(dealMoney);
        sb.append(", stockMemberNum=").append(stockMemberNum);
        sb.append(", pe1Ratio=").append(pe1Ratio);
        sb.append(", pe2Ratio=").append(pe2Ratio);
        sb.append(", dp1Ratio=").append(dp1Ratio);
        sb.append(", dp2Ratio=").append(dp2Ratio);
        sb.append(", earnRatio=").append(earnRatio);
        sb.append(", dateUpdate=").append(dateUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}