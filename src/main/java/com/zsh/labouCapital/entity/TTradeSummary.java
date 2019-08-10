package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_trade_summary
 * create time: 2019-06-21 17:24:19
 */ 
public class TTradeSummary{

	private String id;
	private String fundCode;
	private double nowTotalCost;
	private double nowTotalNum;
	private double nowAvgCost;
	private Date dateInfo;
	private String modelId;

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
	public void setNowTotalCost(double nowTotalCost){
		this.nowTotalCost=nowTotalCost;
	}
	public double getNowTotalCost(){
		return nowTotalCost;
	}
	public void setNowTotalNum(double nowTotalNum){
		this.nowTotalNum=nowTotalNum;
	}
	public double getNowTotalNum(){
		return nowTotalNum;
	}
	public void setNowAvgCost(double nowAvgCost){
		this.nowAvgCost=nowAvgCost;
	}
	public double getNowAvgCost(){
		return nowAvgCost;
	}
	public void setDateInfo(Date dateInfo){
		this.dateInfo=dateInfo;
	}
	public Date getDateInfo(){
		return dateInfo;
	}
    public String getModelId() {
        return modelId;
    }
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}

