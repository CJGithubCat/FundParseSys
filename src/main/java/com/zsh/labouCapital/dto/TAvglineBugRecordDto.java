package com.zsh.labouCapital.dto;

import java.util.Date;
/**
 * table name:  t_avgline_bug_record
 * create time: 2019-06-15 12:55:15
 */ 
public class TAvglineBugRecordDto{
	private String id;
	private String netWorthId;
	private String fundCode;
	private Date dateInfo;
	private double netWorth;
	private double avgLineWorth;
	private double diffValue;
	private int avgLineType;
	private double bugMoney;
	private double bugNum;
	private double bugCost;
	private int buyModel;
	private Date dateCreate;
	private Date startDate;
	private Date endDate;
	private String batchNo;
	

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setNetWorthId(String netWorthId){
		this.netWorthId=netWorthId;
	}
	public String getNetWorthId(){
		return netWorthId;
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
	public void setAvgLineWorth(double avgLineWorth){
		this.avgLineWorth=avgLineWorth;
	}
	public double getAvgLineWorth(){
		return avgLineWorth;
	}
	public void setDiffValue(double diffValue){
		this.diffValue=diffValue;
	}
	public double getDiffValue(){
		return diffValue;
	}
	public void setAvgLineType(int avgLineType){
		this.avgLineType=avgLineType;
	}
	public int getAvgLineType(){
		return avgLineType;
	}
	public void setBugMoney(double bugMoney){
		this.bugMoney=bugMoney;
	}
	public double getBugMoney(){
		return bugMoney;
	}
	public void setBugNum(double bugNum){
		this.bugNum=bugNum;
	}
	public double getBugNum(){
		return bugNum;
	}
	public void setBugCost(double bugCost){
		this.bugCost=bugCost;
	}
	public double getBugCost(){
		return bugCost;
	}
	public void setBuyModel(int buyModel){
		this.buyModel=buyModel;
	}
	public int getBuyModel(){
		return buyModel;
	}
	public void setDateCreate(Date dateCreate){
		this.dateCreate=dateCreate;
	}
	public Date getDateCreate(){
		return dateCreate;
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
	public void setBatchNo(String batchNo){
		this.batchNo=batchNo;
	}
	public String getBatchNo(){
		return batchNo;
	}
}

