package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_market_situation
 * create time: 2018-09-08 23:05:59
 */ 
public class MarketSituation{

	private String infoDate;
	private String indexCode;
	private String indexSname;
	private String openPoint;
	private String highestPoint;
	private String lowestPoint;
	private String closePoint;
	private String riseFall;
	private String riseFallRange;
	private String dealAmount;
	private String dealMoney;
	private int stockMemberNum;
	private double pe1Ratio;
	private double pe2Ratio;
	private double dp1Ratio;
	private double dp2Ratio;
	private double earnRatio;
	private Date dateUpdate;

	public void setInfoDate(String infoDate){
		this.infoDate=infoDate;
	}
	public String getInfoDate(){
		return infoDate;
	}
	public void setIndexCode(String indexCode){
		this.indexCode=indexCode;
	}
	public String getIndexCode(){
		return indexCode;
	}
	public void setIndexSname(String indexSname){
		this.indexSname=indexSname;
	}
	public String getIndexSname(){
		return indexSname;
	}
	public void setOpenPoint(String openPoint){
		this.openPoint=openPoint;
	}
	public String getOpenPoint(){
		return openPoint;
	}
	public void setHighestPoint(String highestPoint){
		this.highestPoint=highestPoint;
	}
	public String getHighestPoint(){
		return highestPoint;
	}
	public void setLowestPoint(String lowestPoint){
		this.lowestPoint=lowestPoint;
	}
	public String getLowestPoint(){
		return lowestPoint;
	}
	public void setClosePoint(String closePoint){
		this.closePoint=closePoint;
	}
	public String getClosePoint(){
		return closePoint;
	}
	public void setRiseFall(String riseFall){
		this.riseFall=riseFall;
	}
	public String getRiseFall(){
		return riseFall;
	}
	public void setRiseFallRange(String riseFallRange){
		this.riseFallRange=riseFallRange;
	}
	public String getRiseFallRange(){
		return riseFallRange;
	}
	public void setDealAmount(String dealAmount){
		this.dealAmount=dealAmount;
	}
	public String getDealAmount(){
		return dealAmount;
	}
	public void setDealMoney(String dealMoney){
		this.dealMoney=dealMoney;
	}
	public String getDealMoney(){
		return dealMoney;
	}
	public void setStockMemberNum(int stockMemberNum){
		this.stockMemberNum=stockMemberNum;
	}
	public int getStockMemberNum(){
		return stockMemberNum;
	}
	
	public double getPe1Ratio() {
		return pe1Ratio;
	}
	public void setPe1Ratio(double pe1Ratio) {
		this.pe1Ratio = pe1Ratio;
	}
	public double getPe2Ratio() {
		return pe2Ratio;
	}
	public void setPe2Ratio(double pe2Ratio) {
		this.pe2Ratio = pe2Ratio;
	}
	public double getDp1Ratio() {
		return dp1Ratio;
	}
	public void setDp1Ratio(double dp1Ratio) {
		this.dp1Ratio = dp1Ratio;
	}
	public double getDp2Ratio() {
		return dp2Ratio;
	}
	public void setDp2Ratio(double dp2Ratio) {
		this.dp2Ratio = dp2Ratio;
	}
	public double getEarnRatio() {
		return earnRatio;
	}
	public void setEarnRatio(double earnRatio) {
		this.earnRatio = earnRatio;
	}
	public void setDateUpdate(Date dateUpdate){
		this.dateUpdate=dateUpdate;
	}
	public Date getDateUpdate(){
		return dateUpdate;
	}
}

