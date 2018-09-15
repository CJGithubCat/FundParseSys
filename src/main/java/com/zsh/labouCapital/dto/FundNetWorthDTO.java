package com.zsh.labouCapital.dto;

import java.util.Date;

/**
 *<p> Title: FundNetWorthDTO </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月11日
 */
public class FundNetWorthDTO extends FundBaseDTO{
   
    private int buyType;//1--每周； 2--每两周； 3--每月
    private int interval;//周期
    private Date startTime;//开始时间
    public int getBuyType() {
        return buyType;
    }
    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }
    public int getInterval() {
        return interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Override
    public String toString() {
        return "FundNetWorthDTO [buyType=" + buyType + ", interval=" + interval + ", startTime=" + startTime + "]";
    }
}
