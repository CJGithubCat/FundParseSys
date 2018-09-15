package com.zsh.labouCapital.entity;
/**
 *<p> Title: FundRealTimeNetWorthDTO </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月14日
 * remark: 是15点之前的数据，发邮件提醒；
 * 是15点之后的数据，更新数据库
 */
public class FundRealTimeNetWorthDTO {
    public long gzGztime;//时间
    public long checkTime;//校验时间
    public double nowNetWorth;//净值
    public String fundCode;//基金code
    
    public long getCheckTie() {
        return checkTime;
    }

    public void setCheckTie(long checkTime) {
        this.checkTime = checkTime;
    }

    public long getGzGztime() {
        return gzGztime;
    }
    
    public void setGzGztime(long gzGztime) {
        this.gzGztime = gzGztime;
    }
    public double getNowNetWorth() {
        return nowNetWorth;
    }
    public void setNowNetWorth(double nowNetWorth) {
        this.nowNetWorth = nowNetWorth;
    }
    public String getFundCode() {
        return fundCode;
    }
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public String toString() {
        return "FundRealTimeNetWorthDTO [gzGztime=" + gzGztime + ", checkTime=" + checkTime + ", nowNetWorth=" + nowNetWorth + ", fundCode="
                + fundCode + "]";
    }
}
