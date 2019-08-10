package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_trade_model
 * create time: 2019-06-28 16:42:16
 */ 
public class TTradeModel{

	private String modelId;
	private String fundCode;
	private Date startDate;
	private Date endDate;
	private String strategyId;
	private double buyMoney;
	private double totalBuyNum;
	private double saleMoney;
	private double earnMoney;
	private double totalSaleNum;
	private double earnRate;
	private double yearEarnRate;

	public void setModelId(String modelId){
		this.modelId=modelId;
	}
	public String getModelId(){
		return modelId;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}
	public Date getStartDate(){
		return startDate;
	}
	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	public void setStrategyId(String strategyId){
		this.strategyId=strategyId;
	}
	public String getStrategyId(){
		return strategyId;
	}
	public void setBuyMoney(double buyMoney){
		this.buyMoney=buyMoney;
	}
	public double getBuyMoney(){
		return buyMoney;
	}
	public void setTotalBuyNum(double totalBuyNum){
		this.totalBuyNum=totalBuyNum;
	}
	public double getTotalBuyNum(){
		return totalBuyNum;
	}
	public void setSaleMoney(double saleMoney){
		this.saleMoney=saleMoney;
	}
	public double getSaleMoney(){
		return saleMoney;
	}
	public void setEarnMoney(double earnMoney){
		this.earnMoney=earnMoney;
	}
	public double getEarnMoney(){
		return earnMoney;
	}
	public void setTotalSaleNum(double totalSaleNum){
		this.totalSaleNum=totalSaleNum;
	}
	public double getTotalSaleNum(){
		return totalSaleNum;
	}
	public void setEarnRate(double earnRate){
		this.earnRate=earnRate;
	}
	public double getEarnRate(){
		return earnRate;
	}
	public void setYearEarnRate(double yearEarnRate){
		this.yearEarnRate=yearEarnRate;
	}
	public double getYearEarnRate(){
		return yearEarnRate;
	}
}

