package com.zsh.labouCapital.entity;

/**
 * Title: ExamSummaryVo Description: 考核结果汇总vo
 * 
 * @author cj
 * @date 2018年6月24日
 */
public class ExamSummaryVo {
	private String yearMonthDate; // 汇总年月
	private String xbmName; // 县部门名
	private String wtgsName;// 委托公司
	private String jyzName;// 加油站
	private Integer status; // 状态
	private String attributesPath;

	public String getAttributesPath() {
		return attributesPath;
	}

	public void setAttributesPath(String attributesPath) {
		this.attributesPath = attributesPath;
	}

	public String getYearMonthDate() {
		return yearMonthDate;
	}

	public void setYearMonthDate(String yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}

	public String getXbmName() {
		return xbmName;
	}

	public void setXbmName(String xbmName) {
		this.xbmName = xbmName;
	}

	public String getWtgsName() {
		return wtgsName;
	}

	public void setWtgsName(String wtgsName) {
		this.wtgsName = wtgsName;
	}

	public String getJyzName() {
		return jyzName;
	}

	public void setJyzName(String jyzName) {
		this.jyzName = jyzName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ExamSummaryVo [yearMonthDate=" + yearMonthDate + ", xbmName=" + xbmName + ", wtgsName=" + wtgsName
				+ ", jyzName=" + jyzName + ", status=" + status + ", attributesPath=" + attributesPath + "]";
	}
}
