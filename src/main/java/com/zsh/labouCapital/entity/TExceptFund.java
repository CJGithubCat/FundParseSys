package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_except_fund
 * create time: 2018-09-19 21:19:03
 */ 
public class TExceptFund{

	private String fundCode;
	private String indexCode;
	private String fundName;
	private double avgPe1;
	private double avgPe2;
	private double oneYearRatio;
	private double twoYearRatio;
	private double threeYearRatio;
	private double fourYearRatio;
	private double fiveYearRatio;
	private Date dateCreate;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setFundCode(String fundCode){
		this.fundCode=fundCode;
	}
	public String getFundCode(){
		return fundCode;
	}
	public void setIndexCode(String indexCode){
		this.indexCode=indexCode;
	}
	public String getIndexCode(){
		return indexCode;
	}
	public void setFundName(String fundName){
		this.fundName=fundName;
	}
	public String getFundName(){
		return fundName;
	}
	public double getAvgPe1() {
		return avgPe1;
	}
	public void setAvgPe1(double avgPe1) {
		this.avgPe1 = avgPe1;
	}
	public double getAvgPe2() {
		return avgPe2;
	}
	public void setAvgPe2(double avgPe2) {
		this.avgPe2 = avgPe2;
	}
	public void setOneYearRatio(double oneYearRatio){
		this.oneYearRatio=oneYearRatio;
	}
	public double getOneYearRatio(){
		return oneYearRatio;
	}
	public void setTwoYearRatio(double twoYearRatio){
		this.twoYearRatio=twoYearRatio;
	}
	public double getTwoYearRatio(){
		return twoYearRatio;
	}
	public void setThreeYearRatio(double threeYearRatio){
		this.threeYearRatio=threeYearRatio;
	}
	public double getThreeYearRatio(){
		return threeYearRatio;
	}
	public void setFourYearRatio(double fourYearRatio){
		this.fourYearRatio=fourYearRatio;
	}
	public double getFourYearRatio(){
		return fourYearRatio;
	}
	public void setFiveYearRatio(double fiveYearRatio){
		this.fiveYearRatio=fiveYearRatio;
	}
	public double getFiveYearRatio(){
		return fiveYearRatio;
	}
	public void setDateCreate(Date dateCreate){
		this.dateCreate=dateCreate;
	}
	public Date getDateCreate(){
		return dateCreate;
	}
}

