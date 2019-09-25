package com.zsh.labouCapital.dto;

import java.io.Serializable;
import java.util.Date;

import com.zsh.labouCapital.util.DateTimeUtil;

public class FundNetWorthLastDto implements Serializable {

	private Long id;

    private String fundCode;

    private String fundName;

    private Double nowNetWorth;

    private Double yesterdayNetWorth;

    private Double guzhiAddRate;

    private Double avgLine30;

    private Double avgLine60;

    private Double avgLine90;

    private Double avgLine120;

    private Double avgLine240;

    private String indexCode;

    private Double indexPe;

    private Integer weekInfo;
    
    private String weekStr;
    
    private Date dateInfo;
    
    private String dateInfoStr;

    private Date dateUpdate;
    
    private String dateUpdateStr;

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

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Double getNowNetWorth() {
		return nowNetWorth;
	}

	public void setNowNetWorth(Double nowNetWorth) {
		this.nowNetWorth = nowNetWorth;
	}

	public Double getYesterdayNetWorth() {
		return yesterdayNetWorth;
	}

	public void setYesterdayNetWorth(Double yesterdayNetWorth) {
		this.yesterdayNetWorth = yesterdayNetWorth;
	}

	public Double getGuzhiAddRate() {
		return guzhiAddRate;
	}

	public void setGuzhiAddRate(Double guzhiAddRate) {
		this.guzhiAddRate = guzhiAddRate;
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

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public Double getIndexPe() {
		return indexPe;
	}

	public void setIndexPe(Double indexPe) {
		this.indexPe = indexPe;
	}

	public Integer getWeekInfo() {
		return weekInfo;
	}

	public void setWeekInfo(Integer weekInfo) {
		this.weekInfo = weekInfo;
	}

	public Date getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
		this.dateInfoStr = DateTimeUtil.formatDate(dateInfo, DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
		this.dateUpdateStr = DateTimeUtil.formatDate(dateUpdate, DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDateUpdateStr() {
		return dateUpdateStr;
	}

	public void setDateUpdateStr(String dateUpdateStr) {
		this.dateUpdateStr = dateUpdateStr;
	}
}