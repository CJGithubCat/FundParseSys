package com.zsh.labouCapital.entity;

public class NetWorthHistory {
	private String id;
	private String fundCode;
	private String dateInfo;
	private double netWorth;
	private double addUpWorth;
	private double equityReturn;
	private String unitMoney;   
	private String dateCreate;
	private int weekInfo;
	private String historyUrl;
	private double networthDaygrowRate;
	
	public double getAddUpWorth() {
        return addUpWorth;
    }
    public void setAddUpWorth(double addUpWorth) {
        this.addUpWorth = addUpWorth;
    }
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getDateInfo() {
		return dateInfo;
	}
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}
	public double getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(double netWorth) {
		this.netWorth = netWorth;
	}
	public double getEquityReturn() {
		return equityReturn;
	}
	public void setEquityReturn(double equityReturn) {
		this.equityReturn = equityReturn;
	}
	public String getUnitMoney() {
		return unitMoney;
	}
	public void setUnitMoney(String unitMoney) {
		this.unitMoney = unitMoney;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public int getWeekInfo() {
		return weekInfo;
	}
	public void setWeekInfo(int weekInfo) {
		this.weekInfo = weekInfo;
	}
	
	public String getHistoryUrl() {
		return historyUrl;
	}
	public void setHistoryUrl(String historyUrl) {
		this.historyUrl = historyUrl;
	}
	
	public double getNetworthDaygrowRate() {
		return networthDaygrowRate;
	}
	public void setNetworthDaygrowRate(double networthDaygrowRate) {
		this.networthDaygrowRate = networthDaygrowRate;
	}
    @Override
    public String toString() {
        return "NetWorthHistory [id=" + id + ", fundCode=" + fundCode + ", dateInfo=" + dateInfo + ", netWorth=" + netWorth
                + ", addUpWorth=" + addUpWorth + ", equityReturn=" + equityReturn + ", unitMoney=" + unitMoney + ", dateCreate="
                + dateCreate + ", weekInfo=" + weekInfo + ", historyUrl=" + historyUrl + ", networthDaygrowRate=" + networthDaygrowRate
                + "]";
    }
}
