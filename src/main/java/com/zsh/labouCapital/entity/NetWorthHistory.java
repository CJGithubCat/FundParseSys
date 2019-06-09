package com.zsh.labouCapital.entity;

import java.util.Date;

public class NetWorthHistory {
	private String id;
	private String fundCode;
	private Date dateInfo;
	private String dateInfoStr;
	private Double netWorth;
	private Double avgLine30;
	private Double avgLine60;
	private Double avgLine90;
	private Double avgLine120;
	private Double avgLine240;
	private Double addUpWorth;
	private Double equityReturn;
	private String unitMoney;   
	private String dateCreate;
	private int weekInfo;
	private String historyUrl;
	private Double networthDaygrowRate;
	
    public String getDateInfoStr() {
        return dateInfoStr;
    }
    public void setDateInfoStr(String dateInfoStr) {
        this.dateInfoStr = dateInfoStr;
    }
    public Double getAddUpWorth() {
		return addUpWorth;
	}
	public void setAddUpWorth(Double addUpWorth) {
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
	public Date getDateInfo() {
        return dateInfo;
    }
    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }
    public Double getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}
	public Double getEquityReturn() {
		return equityReturn;
	}
	public void setEquityReturn(Double equityReturn) {
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
	
	public Double getNetworthDaygrowRate() {
		return networthDaygrowRate;
	}
	public void setNetworthDaygrowRate(Double networthDaygrowRate) {
		this.networthDaygrowRate = networthDaygrowRate;
	}
    public Double getAvgLine30() {
        return avgLine30;
    }
    public void setAvgLine30(Double avgLine30) {
        this.avgLine30 = avgLine30;
    }
    public Double getAvgLine60() {
        return avgLine60;
    }
    public void setAvgLine60(Double avgLine60) {
        this.avgLine60 = avgLine60;
    }
    public Double getAvgLine90() {
        return avgLine90;
    }
    public void setAvgLine90(Double avgLine90) {
        this.avgLine90 = avgLine90;
    }
    public Double getAvgLine120() {
        return avgLine120;
    }
    public void setAvgLine120(Double avgLine120) {
        this.avgLine120 = avgLine120;
    }
    public Double getAvgLine240() {
        return avgLine240;
    }
    public void setAvgLine240(Double avgLine240) {
        this.avgLine240 = avgLine240;
    }
    @Override
    public String toString() {
        return "NetWorthHistory [id=" + id + ", fundCode=" + fundCode + ", dateInfo=" + dateInfo + ", netWorth=" + netWorth
                + ", addUpWorth=" + addUpWorth + ", equityReturn=" + equityReturn + ", unitMoney=" + unitMoney + ", dateCreate="
                + dateCreate + ", weekInfo=" + weekInfo + ", historyUrl=" + historyUrl + ", networthDaygrowRate=" + networthDaygrowRate
                + "]";
    }
}
