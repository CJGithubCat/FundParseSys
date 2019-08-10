package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_analyse_avgline_result
 * create time: 2019-06-15 15:01:28
 */ 
public class TAnalyseAvglineResult{

	private String id;
	private String fundCode;
	private String batchNo;
	private Date startDate;
	private Date endDate;
	private double totalBuyMoney;
	private double totalBuyNum;
	private double totalBuyFee;
	private double costNetWorth;
	private double saleNetWorth;
	private double returnRate;
	private int buyModel;
	private String buyStrategy;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setBatchNo(String batchNo){
		this.batchNo=batchNo;
	}
	public String getBatchNo(){
		return batchNo;
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
	public void setTotalBuyMoney(double totalBuyMoney){
		this.totalBuyMoney=totalBuyMoney;
	}
	public double getTotalBuyMoney(){
		return totalBuyMoney;
	}
	public void setTotalBuyNum(double totalBuyNum){
		this.totalBuyNum=totalBuyNum;
	}
	public double getTotalBuyNum(){
		return totalBuyNum;
	}
	public void setTotalBuyFee(double totalBuyFee){
		this.totalBuyFee=totalBuyFee;
	}
	public double getTotalBuyFee(){
		return totalBuyFee;
	}
	public void setCostNetWorth(double costNetWorth){
		this.costNetWorth=costNetWorth;
	}
	public double getCostNetWorth(){
		return costNetWorth;
	}
	public void setSaleNetWorth(double saleNetWorth){
		this.saleNetWorth=saleNetWorth;
	}
	public double getSaleNetWorth(){
		return saleNetWorth;
	}
	public void setReturnRate(double returnRate){
		this.returnRate=returnRate;
	}
	public double getReturnRate(){
		return returnRate;
	}
	public void setBuyModel(int buyModel){
		this.buyModel=buyModel;
	}
	public int getBuyModel(){
		return buyModel;
	}
	public void setBuyStrategy(String buyStrategy){
		this.buyStrategy=buyStrategy;
	}
	public String getBuyStrategy(){
		return buyStrategy;
	}
}

