package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_trade_detail
 * create time: 2019-06-25 20:12:08
 */ 
public class TTradeDetail{

	private String id;
	private String modelId;
	private String fundCode;
	private Date dateInfo;
	private double netWorth;
	private double starndValue;
	private int tradeType;
	private double tradeMoney;
	private double tradeNum;
	private double nowAvgCost;
	private String tradeStrage;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
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
	public void setDateInfo(Date dateInfo){
		this.dateInfo=dateInfo;
	}
	public Date getDateInfo(){
		return dateInfo;
	}
	public void setNetWorth(double netWorth){
		this.netWorth=netWorth;
	}
	public double getNetWorth(){
		return netWorth;
	}
	public void setStarndValue(double starndValue){
		this.starndValue=starndValue;
	}
	public double getStarndValue(){
		return starndValue;
	}
	public void setTradeType(int tradeType){
		this.tradeType=tradeType;
	}
	public int getTradeType(){
		return tradeType;
	}
	public void setTradeMoney(double tradeMoney){
		this.tradeMoney=tradeMoney;
	}
	public double getTradeMoney(){
		return tradeMoney;
	}
	public void setTradeNum(double tradeNum){
		this.tradeNum=tradeNum;
	}
	public double getTradeNum(){
		return tradeNum;
	}
	public void setNowAvgCost(double nowAvgCost){
		this.nowAvgCost=nowAvgCost;
	}
	public double getNowAvgCost(){
		return nowAvgCost;
	}
	public void setTradeStrage(String tradeStrage){
		this.tradeStrage=tradeStrage;
	}
	public String getTradeStrage(){
		return tradeStrage;
	}
}

