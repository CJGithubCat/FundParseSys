package com.zsh.labouCapital.entity;

/**
 * table name:  t_interval_buy
 * create time: 2018-09-12 00:26:42
 */ 
public class IntervalBuy{

	private long id;
	private String fundCode;
	private double totalCost;
	private double netWorth;
	private double totalNum;
	private String tradeDay;
	private double bonusWorth;
	private double bonusEarn;

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setTotalCost(double totalCost){
		this.totalCost=totalCost;
	}
	public double getTotalCost(){
		return totalCost;
	}
	public void setNetWorth(double netWorth){
		this.netWorth=netWorth;
	}
	public double getNetWorth(){
		return netWorth;
	}
	public void setTotalNum(double totalNum){
		this.totalNum=totalNum;
	}
	public double getTotalNum(){
		return totalNum;
	}
	public void setTradeDay(String tradeDay){
		this.tradeDay=tradeDay;
	}
	public String getTradeDay(){
		return tradeDay;
	}
	public void setBonusWorth(double bonusWorth){
		this.bonusWorth=bonusWorth;
	}
	public double getBonusWorth(){
		return bonusWorth;
	}
	public void setBonusEarn(double bonusEarn){
		this.bonusEarn=bonusEarn;
	}
	public double getBonusEarn(){
		return bonusEarn;
	}
}

