package com.zsh.labouCapital.entity;

import java.util.Date;
/**
 * table name:  t_index_new
 * create time: 2018-09-08 23:05:59
 */ 
public class IndexNew{

	private String indexId;
	private String indexCode;
	private String indexSname;
	private String indexEname;
	private double basePoint;
	private Date baseDate;
	private Date onlineDate;
	private String indexCIntro;
	private String hangqingzoushi;
	private String makeMethod;
	private String weihuxize;
	private String chengfenguliebiao;
	private int agencyType;
	private Date dateCreate;
	private String hangqingfilepath;
	private String detailUrl;
	private String traceFunds;
	private int isCheck;
	
	public String getIndexSname() {
		return indexSname;
	}
	public void setIndexSname(String indexSname) {
		this.indexSname = indexSname;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public String getTraceFunds() {
		return traceFunds;
	}
	public void setTraceFunds(String traceFunds) {
		this.traceFunds = traceFunds;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public void setIndexId(String indexId){
		this.indexId=indexId;
	}
	public String getIndexId(){
		return indexId;
	}
	public void setIndexCode(String indexCode){
		this.indexCode=indexCode;
	}
	public String getIndexCode(){
		return indexCode;
	}
	public void setIndxSname(String indxSname){
		this.indexSname=indxSname;
	}
	public String getIndxSname(){
		return indexSname;
	}
	public void setIndexEname(String indexEname){
		this.indexEname=indexEname;
	}
	public String getIndexEname(){
		return indexEname;
	}
	public void setBasePoint(double basePoint){
		this.basePoint=basePoint;
	}
	public double getBasePoint(){
		return basePoint;
	}
	public void setBaseDate(Date baseDate){
		this.baseDate=baseDate;
	}
	public Date getBaseDate(){
		return baseDate;
	}
	public void setOnlineDate(Date onlineDate){
		this.onlineDate=onlineDate;
	}
	public Date getOnlineDate(){
		return onlineDate;
	}
	public void setIndexCIntro(String indexCIntro){
		this.indexCIntro=indexCIntro;
	}
	public String getIndexCIntro(){
		return indexCIntro;
	}
	public void setHangqingzoushi(String hangqingzoushi){
		this.hangqingzoushi=hangqingzoushi;
	}
	public String getHangqingzoushi(){
		return hangqingzoushi;
	}
	public void setMakeMethod(String makeMethod){
		this.makeMethod=makeMethod;
	}
	public String getMakeMethod(){
		return makeMethod;
	}
	public void setWeihuxize(String weihuxize){
		this.weihuxize=weihuxize;
	}
	public String getWeihuxize(){
		return weihuxize;
	}
	public void setChengfenguliebiao(String chengfenguliebiao){
		this.chengfenguliebiao=chengfenguliebiao;
	}
	public String getChengfenguliebiao(){
		return chengfenguliebiao;
	}
	public void setAgencyType(int agencyType){
		this.agencyType=agencyType;
	}
	public int getAgencyType(){
		return agencyType;
	}
	public void setDateCreate(Date dateCreate){
		this.dateCreate=dateCreate;
	}
	public Date getDateCreate(){
		return dateCreate;
	}
	public void setHangqingfilepath(String hangqingfilepath){
		this.hangqingfilepath=hangqingfilepath;
	}
	public String getHangqingfilepath(){
		return hangqingfilepath;
	}
}

