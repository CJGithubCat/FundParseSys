package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_hold_position
 * create time: 2018-09-25 16:34:08
 */ 
public class THoldPosition{

	private long id;
	private String fundCode;
	private Double netWorth;
	private Double buyMoney;
	private Double buyNum;
	private Date buyDate;
	private Double lastAvgCost;
	private Double realNetWorth;
	private Double growRate;
	private int type;
	private Double nowTotalNum;
	private Double nowAvgCost;

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
	public void setNetWorth(Double netWorth){
		this.netWorth=netWorth;
	}
	public Double getNetWorth(){
		return netWorth;
	}
	public void setBuyMoney(Double buyMoney){
		this.buyMoney=buyMoney;
	}
	public Double getBuyMoney(){
		return buyMoney;
	}
	public void setBuyNum(Double buyNum){
		this.buyNum=buyNum;
	}
	public Double getBuyNum(){
		return buyNum;
	}
	public void setBuyDate(Date buyDate){
		this.buyDate=buyDate;
	}
	public Date getBuyDate(){
		return buyDate;
	}
	public void setLastAvgCost(Double lastAvgCost){
		this.lastAvgCost=lastAvgCost;
	}
	public Double getLastAvgCost(){
		return lastAvgCost;
	}
	public void setRealNetWorth(Double realNetWorth){
		this.realNetWorth=realNetWorth;
	}
	public Double getRealNetWorth(){
		return realNetWorth;
	}
	public void setGrowRate(Double growRate){
		this.growRate=growRate;
	}
	public Double getGrowRate(){
		return growRate;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getType(){
		return type;
	}
	public void setNowTotalNum(Double nowTotalNum){
		this.nowTotalNum=nowTotalNum;
	}
	public Double getNowTotalNum(){
		return nowTotalNum;
	}
	public void setNowAvgCost(Double nowAvgCost){
		this.nowAvgCost=nowAvgCost;
	}
	public Double getNowAvgCost(){
		return nowAvgCost;
	}
}

