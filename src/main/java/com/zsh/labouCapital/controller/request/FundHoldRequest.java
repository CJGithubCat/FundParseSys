package com.zsh.labouCapital.controller.request;

import java.util.Date;

/**
 * table name:  t_fund_summary
 * create time: 2018-09-08 23:05:59
 */ 
public class FundHoldRequest extends BaseForm{
	private Long id;
	private String fundCode;
	private String fundName;
	private Double holdCost;
	private Double accumCost;
	private Double marketValue;
	private Double volume;
	private Double totalGain;
	private Double holdGain;
	private Double dailyGain;
	private Double unconfirmedAmount;
	private Double unconfirmedVolume;
	private Double unconfirmedCount;
	private Double usableRemainShare;
	private Double autoInvestCount;
	private Double holdGainRate;
	private Double totalGainRate;
	private String type;
	private Double holdingCost;
	private Double navGuzhi;
	private Double nav;
	private Double navGrtd;
	private Date ts;
	private String profitDocuments;
	private Date dateCreate;
	private Date dateUpdate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Double getHoldCost() {
		return holdCost;
	}
	public void setHoldCost(Double holdCost) {
		this.holdCost = holdCost;
	}
	public Double getAccumCost() {
		return accumCost;
	}
	public void setAccumCost(Double accumCost) {
		this.accumCost = accumCost;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getTotalGain() {
		return totalGain;
	}
	public void setTotalGain(Double totalGain) {
		this.totalGain = totalGain;
	}
	public Double getHoldGain() {
		return holdGain;
	}
	public void setHoldGain(Double holdGain) {
		this.holdGain = holdGain;
	}
	public Double getDailyGain() {
		return dailyGain;
	}
	public void setDailyGain(Double dailyGain) {
		this.dailyGain = dailyGain;
	}
	public Double getUnconfirmedAmount() {
		return unconfirmedAmount;
	}
	public void setUnconfirmedAmount(Double unconfirmedAmount) {
		this.unconfirmedAmount = unconfirmedAmount;
	}
	public Double getUnconfirmedVolume() {
		return unconfirmedVolume;
	}
	public void setUnconfirmedVolume(Double unconfirmedVolume) {
		this.unconfirmedVolume = unconfirmedVolume;
	}
	public Double getUnconfirmedCount() {
		return unconfirmedCount;
	}
	public void setUnconfirmedCount(Double unconfirmedCount) {
		this.unconfirmedCount = unconfirmedCount;
	}
	public Double getUsableRemainShare() {
		return usableRemainShare;
	}
	public void setUsableRemainShare(Double usableRemainShare) {
		this.usableRemainShare = usableRemainShare;
	}
	public Double getAutoInvestCount() {
		return autoInvestCount;
	}
	public void setAutoInvestCount(Double autoInvestCount) {
		this.autoInvestCount = autoInvestCount;
	}
	public Double getHoldGainRate() {
		return holdGainRate;
	}
	public void setHoldGainRate(Double holdGainRate) {
		this.holdGainRate = holdGainRate;
	}
	public Double getTotalGainRate() {
		return totalGainRate;
	}
	public void setTotalGainRate(Double totalGainRate) {
		this.totalGainRate = totalGainRate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getHoldingCost() {
		return holdingCost;
	}
	public void setHoldingCost(Double holdingCost) {
		this.holdingCost = holdingCost;
	}
	public Double getNavGuzhi() {
		return navGuzhi;
	}
	public void setNavGuzhi(Double navGuzhi) {
		this.navGuzhi = navGuzhi;
	}
	public Double getNav() {
		return nav;
	}
	public void setNav(Double nav) {
		this.nav = nav;
	}
	public Double getNavGrtd() {
		return navGrtd;
	}
	public void setNavGrtd(Double navGrtd) {
		this.navGrtd = navGrtd;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public String getProfitDocuments() {
		return profitDocuments;
	}
	public void setProfitDocuments(String profitDocuments) {
		this.profitDocuments = profitDocuments;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	
}

