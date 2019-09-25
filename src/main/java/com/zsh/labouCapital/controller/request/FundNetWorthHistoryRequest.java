package com.zsh.labouCapital.controller.request;

import java.util.Date;

/**
 *<p> Title: FundNetWorthDTO </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2018 </p>
 *
 * @author HP
 * @version
 * @since 2018年9月11日
 */
public class FundNetWorthHistoryRequest extends BaseForm{
	private Long id;

    private String fundCode;

    private Date dateInfo;

    private Double netWorth;

    private Double avgLine30;

    private Double avgLine60;

    private Double avgLine90;

    private Double avgLine120;

    private Double avgLine240;

    private Double equityReturn;

    private String unitMoney;

    private Integer weekInfo;

    private Date dateCreate;

    private Double networthDaygrowRate;

    private Double addupWorth;

    private static final long serialVersionUID = 1L;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public Date getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}

	public Double getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}

	public Double getAvgLine30() {
		return avgLine30;
	}

	public void setAvgLine30(Double avgLine30) {
		this.avgLine30 = avgLine30;
	}

	public Double getAvgLine60() {
		return avgLine60;
	}

	public void setAvgLine60(Double avgLine60) {
		this.avgLine60 = avgLine60;
	}

	public Double getAvgLine90() {
		return avgLine90;
	}

	public void setAvgLine90(Double avgLine90) {
		this.avgLine90 = avgLine90;
	}

	public Double getAvgLine120() {
		return avgLine120;
	}

	public void setAvgLine120(Double avgLine120) {
		this.avgLine120 = avgLine120;
	}

	public Double getAvgLine240() {
		return avgLine240;
	}

	public void setAvgLine240(Double avgLine240) {
		this.avgLine240 = avgLine240;
	}

	public Double getEquityReturn() {
		return equityReturn;
	}

	public void setEquityReturn(Double equityReturn) {
		this.equityReturn = equityReturn;
	}

	public String getUnitMoney() {
		return unitMoney;
	}

	public void setUnitMoney(String unitMoney) {
		this.unitMoney = unitMoney;
	}

	public Integer getWeekInfo() {
		return weekInfo;
	}

	public void setWeekInfo(Integer weekInfo) {
		this.weekInfo = weekInfo;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Double getNetworthDaygrowRate() {
		return networthDaygrowRate;
	}

	public void setNetworthDaygrowRate(Double networthDaygrowRate) {
		this.networthDaygrowRate = networthDaygrowRate;
	}

	public Double getAddupWorth() {
		return addupWorth;
	}

	public void setAddupWorth(Double addupWorth) {
		this.addupWorth = addupWorth;
	}

}
