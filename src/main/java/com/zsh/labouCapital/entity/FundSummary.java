package com.zsh.labouCapital.entity;

/**
 * table name:  t_fund_summary
 * create time: 2018-09-08 23:05:59
 */ 
public class FundSummary{

	private String fundCode;
	private String indexCode;
	private String fundName;
	private int buyStatus;
	private int redemptionStatus;
	private double poundage;
	private int fundType;
	private int traceType;
	private int traceTarget;
	private long indexId;
	private String detailUrl;
	private String fundScale;
	private String fundManager;
	private String establishDate;
	private String companyName;
	private String traceError;
	private String grade;
	private String risk;
	private String type2;
	private String companyCode;
	private String historyUrl;

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
	public void setBuyStatus(int buyStatus){
		this.buyStatus=buyStatus;
	}
	public int getBuyStatus(){
		return buyStatus;
	}
	public void setRedemptionStatus(int redemptionStatus){
		this.redemptionStatus=redemptionStatus;
	}
	public int getRedemptionStatus(){
		return redemptionStatus;
	}
	public void setPoundage(double poundage){
		this.poundage=poundage;
	}
	public double getPoundage(){
		return poundage;
	}
	public void setFundType(int fundType){
		this.fundType=fundType;
	}
	public int getFundType(){
		return fundType;
	}
	public void setTraceType(int traceType){
		this.traceType=traceType;
	}
	public int getTraceType(){
		return traceType;
	}
	public void setTraceTarget(int traceTarget){
		this.traceTarget=traceTarget;
	}
	public int getTraceTarget(){
		return traceTarget;
	}
	public void setIndexId(long indexId){
		this.indexId=indexId;
	}
	public long getIndexId(){
		return indexId;
	}
	public void setDetailUrl(String detailUrl){
		this.detailUrl=detailUrl;
	}
	public String getDetailUrl(){
		return detailUrl;
	}
	public void setFundScale(String fundScale){
		this.fundScale=fundScale;
	}
	public String getFundScale(){
		return fundScale;
	}
	public void setFundManager(String fundManager){
		this.fundManager=fundManager;
	}
	public String getFundManager(){
		return fundManager;
	}
	public void setEstablishDate(String establishDate){
		this.establishDate=establishDate;
	}
	public String getEstablishDate(){
		return establishDate;
	}
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setTraceError(String traceError){
		this.traceError=traceError;
	}
	public String getTraceError(){
		return traceError;
	}
	public void setGrade(String grade){
		this.grade=grade;
	}
	public String getGrade(){
		return grade;
	}
	public void setRisk(String risk){
		this.risk=risk;
	}
	public String getRisk(){
		return risk;
	}
	public void setType2(String type2){
		this.type2=type2;
	}
	public String getType2(){
		return type2;
	}
	public void setCompanyCode(String companyCode){
		this.companyCode=companyCode;
	}
	public String getCompanyCode(){
		return companyCode;
	}
	public void setHistoryUrl(String historyUrl){
		this.historyUrl=historyUrl;
	}
	public String getHistoryUrl(){
		return historyUrl;
	}
	@Override
	public String toString() {
		return "FundSummary [fundCode=" + fundCode + ", indexCode=" + indexCode + ", fundName=" + fundName
				+ ", buyStatus=" + buyStatus + ", redemptionStatus=" + redemptionStatus + ", poundage=" + poundage
				+ ", fundType=" + fundType + ", traceType=" + traceType + ", traceTarget=" + traceTarget + ", indexId="
				+ indexId + ", detailUrl=" + detailUrl + ", fundScale=" + fundScale + ", fundManager=" + fundManager
				+ ", establishDate=" + establishDate + ", companyName=" + companyName + ", traceError=" + traceError
				+ ", grade=" + grade + ", risk=" + risk + ", type2=" + type2 + ", companyCode=" + companyCode
				+ ", historyUrl=" + historyUrl + "]";
	}
}

