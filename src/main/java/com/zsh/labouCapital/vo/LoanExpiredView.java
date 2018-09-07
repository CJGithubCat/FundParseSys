package com.zsh.labouCapital.vo;
/**
 * @author gongyu
 */
import java.util.Date;
/**
 * 车辆贷款到期日统计 报表
 * @author gongyu
 *
 */
public class LoanExpiredView {
	private Date startDate;
    private Date endDate;
    private String atrributePath;
    public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAtrributePath() {
		return atrributePath;
	}
	public void setAtrributePath(String atrributePath) {
		this.atrributePath = atrributePath;
	}
	
}
