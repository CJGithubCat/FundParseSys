package com.zsh.labouCapital.pojo;

import java.util.Date;
import java.util.List;

/**
 * @Package:com.chinagps.webgis.pojo TWgStock
 * @Description:租赁版集客车入网
 * @author: liujie
 * @date:2016-1-19 下午20:35:09
 */
public class TWgStock {

	private int agencyId;// 机构id
	private String agencyName;// 机构名称
	private String thirdAgencyName;// 三级机构名称
	private int TRG80count;//TRG80 数量
	private int TRG8001count;//TRG80-01数量
	private int TRG8003count;//TRG80-03数量
	private int TRG8008count;//TRG80-08数量
	private int TRG9003count;//TRG90-03数量
	private int TRG9008count;//TRG90-08数量
	private int TGO0301count;//TGO03-01数量
	private int TZM30count;//TZM30 数量
	private int TRG30Acount;//TRG30A 数量
	private int TRG30Bcount;//TRG30B 数量
	private int TRG90count;//TRG90 数量
	private int TRG9003Bcount ;
	private int TRG8003Dcount ;
	private int TRG8088count ;
	private int TRG8089count ;
	private int TRG9088count ;
	private int TRG9088Ccount ;
	private int TRG9016count ;
	private int TRG9003Ccount ;
	private int TRG8005count ;
	private int TRG9005count ;
	private int TRG9016Bcount ;
	private int TRG9003Ecount ;
	
	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public int getTRG80count() {
		return TRG80count;
	}

	public void setTRG80count(int tRG80count) {
		TRG80count = tRG80count;
	}

	public int getTRG8001count() {
		return TRG8001count;
	}

	public void setTRG8001count(int tRG8001count) {
		TRG8001count = tRG8001count;
	}

	public int getTRG8003count() {
		return TRG8003count;
	}

	public void setTRG8003count(int tRG8003count) {
		TRG8003count = tRG8003count;
	}

	public int getTRG8008count() {
		return TRG8008count;
	}

	public void setTRG8008count(int tRG8008count) {
		TRG8008count = tRG8008count;
	}

	public int getTRG9003count() {
		return TRG9003count;
	}

	public void setTRG9003count(int tRG9003count) {
		TRG9003count = tRG9003count;
	}

	public int getTRG9008count() {
		return TRG9008count;
	}

	public void setTRG9008count(int tRG9008count) {
		TRG9008count = tRG9008count;
	}

	public int getTZM30count() {
		return TZM30count;
	}

	public void setTZM30count(int tZM30count) {
		TZM30count = tZM30count;
	}

	public int getTRG30Acount() {
		return TRG30Acount;
	}

	public void setTRG30Acount(int tRG30Acount) {
		TRG30Acount = tRG30Acount;
	}

	public int getTRG30Bcount() {
		return TRG30Bcount;
	}

	public void setTRG30Bcount(int tRG30Bcount) {
		TRG30Bcount = tRG30Bcount;
	}

	public int getTRG90count() {
		return TRG90count;
	}

	public void setTRG90count(int tRG90count) {
		TRG90count = tRG90count;
	}

	public int getTGO0301count() {
		return TGO0301count;
	}

	public void setTGO0301count(int tGO0301count) {
		TGO0301count = tGO0301count;
	}

	public String getThirdAgencyName() {
		return thirdAgencyName;
	}

	public void setThirdAgencyName(String thirdAgencyName) {
		this.thirdAgencyName = thirdAgencyName;
	}
	
	public int getTRG9003Bcount() {
		return TRG9003Bcount;
	}

	public void setTRG9003Bcount(int tRG9003Bcount) {
		TRG9003Bcount = tRG9003Bcount;
	}

	public int getTRG8003Dcount() {
		return TRG8003Dcount;
	}

	public void setTRG8003Dcount(int tRG8003Dcount) {
		TRG8003Dcount = tRG8003Dcount;
	}

	public int getTRG8088count() {
		return TRG8088count;
	}

	public void setTRG8088count(int tRG8088count) {
		TRG8088count = tRG8088count;
	}

	public int getTRG9088count() {
		return TRG9088count;
	}

	public void setTRG9088count(int tRG9088count) {
		TRG9088count = tRG9088count;
	}

	public int getTRG9016count() {
		return TRG9016count;
	}

	public void setTRG9016count(int tRG9016count) {
		TRG9016count = tRG9016count;
	}

	public int getTRG9003Ccount() {
		return TRG9003Ccount;
	}

	public void setTRG9003Ccount(int tRG9003Ccount) {
		TRG9003Ccount = tRG9003Ccount;
	}

	public int getTRG8005count() {
		return TRG8005count;
	}

	public void setTRG8005count(int tRG8005count) {
		TRG8005count = tRG8005count;
	}

	public int getTRG9005count() {
		return TRG9005count;
	}

	public void setTRG9005count(int tRG9005count) {
		TRG9005count = tRG9005count;
	}

	public int getTRG8089count() {
		return TRG8089count;
	}

	public void setTRG8089count(int tRG8089count) {
		TRG8089count = tRG8089count;
	}

	public int getTRG9016Bcount() {
		return TRG9016Bcount;
	}

	public void setTRG9016Bcount(int tRG9016Bcount) {
		TRG9016Bcount = tRG9016Bcount;
	}

	public int getTRG9088Ccount() {
		return TRG9088Ccount;
	}

	public void setTRG9088Ccount(int tRG9088Ccount) {
		TRG9088Ccount = tRG9088Ccount;
	}
	
	public int getTRG9003Ecount() {
		return TRG9003Ecount;
	}

	public void setTRG9003Ecount(int tRG9003Ecount) {
		TRG9003Ecount = tRG9003Ecount;
	}

	@Override
	public String toString() {
		return "TWgStock [agencyId=" + agencyId + ", agencyName=" + agencyName + ", thirdAgencyName=" + thirdAgencyName
				+ ", TRG80count=" + TRG80count + ", TRG8001count=" + TRG8001count + ", TRG8003count=" + TRG8003count
				+ ", TRG8008count=" + TRG8008count + ", TRG9003count=" + TRG9003count + ", TRG9008count=" + TRG9008count
				+ ", TGO0301count=" + TGO0301count + ", TZM30count=" + TZM30count + ", TRG30Acount=" + TRG30Acount
				+ ", TRG30Bcount=" + TRG30Bcount + ", TRG90count=" + TRG90count + ", TRG9003Bcount=" + TRG9003Bcount
				+ ", TRG8003Dcount=" + TRG8003Dcount + ", TRG8088count=" + TRG8088count + ", TRG8089count="
				+ TRG8089count + ", TRG9088count=" + TRG9088count + ", TRG9088Ccount=" + TRG9088Ccount
				+ ", TRG9016count=" + TRG9016count + ", TRG9003Ccount=" + TRG9003Ccount + ", TRG8005count="
				+ TRG8005count + ", TRG9005count=" + TRG9005count + ", TRG9016Bcount=" + TRG9016Bcount
				+ ", TRG9003Ecount=" + TRG9003Ecount + "]";
	}

}
