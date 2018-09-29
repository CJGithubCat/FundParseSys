package com.zsh.labouCapital.entity;
/**
 *<p> Title: StrategyAdviceInfo </p>
 *<p> Description: 策略建议</p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月29日
 */
public class StrategyAdviceInfo {
    private String id;
    private String time;
    private String fundCode;
    private String fundName;
    private double netWorth;
    private double nowCost;
    private double diffValue;
    private double growRate;
    private String stageAdvice;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getFundCode() {
        return fundCode;
    }
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
    public String getFundName() {
        return fundName;
    }
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
    public double getNetWorth() {
        return netWorth;
    }
    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }
    public double getNowCost() {
        return nowCost;
    }
    public void setNowCost(double nowCost) {
        this.nowCost = nowCost;
    }
    public double getDiffValue() {
        return diffValue;
    }
    public void setDiffValue(double diffValue) {
        this.diffValue = diffValue;
    }
    public double getGrowRate() {
        return growRate;
    }
    public void setGrowRate(double growRate) {
        this.growRate = growRate;
    }
    public String getStageAdvice() {
        return stageAdvice;
    }
    public void setStageAdvice(String stageAdvice) {
        this.stageAdvice = stageAdvice;
    }
    
}
