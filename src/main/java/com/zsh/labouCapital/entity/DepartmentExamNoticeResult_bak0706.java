package com.zsh.labouCapital.entity;

/**
 * table name: t_department_exam_notice_result desc:部门考核通知单 create time:
 * 2018-06-21 00:27:01
 */
public class DepartmentExamNoticeResult_bak0706 {
	private int id;
	private String noticeNo;
	private int companyId;
	private String yearMonthDate;
	private int examSummayId;//
	private String bmmc;
	private double zhdfl;// 综合得分率
	private double llggxs;
	private double xcxfy;// 合计
	private double jbxc;
	private double llxc;
	private double jbf;
	private double gdbc;
	private double qtgz;
	private double zxjf;
	private double qtbt;
	private double glkf;
	private double lxxc;
	private int status;
	private int salaryStatus;
	private String statusStr;
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getSalaryStatus() {
		return salaryStatus;
	}

	public void setSalaryStatus(int salaryStatus) {
		this.salaryStatus = salaryStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getYearMonthDate() {
		return yearMonthDate;
	}

	public void setYearMonthDate(String yearMonthDate) {
		this.yearMonthDate = yearMonthDate;
	}

	public int getExamSummayId() {
		return examSummayId;
	}

	public void setExamSummayId(int examSummayId) {
		this.examSummayId = examSummayId;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public double getZhdfl() {
		return zhdfl;
	}

	public void setZhdfl(double zhdfl) {
		this.zhdfl = zhdfl;
	}

	public double getLlggxs() {
		return llggxs;
	}

	public void setLlggxs(double llggxs) {
		this.llggxs = llggxs;
	}

	public double getXcxfy() {
		return xcxfy;
	}

	public void setXcxfy(double xcxfy) {
		this.xcxfy = xcxfy;
	}

	public double getJbxc() {
		return jbxc;
	}

	public void setJbxc(double jbxc) {
		this.jbxc = jbxc;
	}

	public double getLlxc() {
		return llxc;
	}

	public void setLlxc(double llxc) {
		this.llxc = llxc;
	}

	public double getJbf() {
		return jbf;
	}

	public void setJbf(double jbf) {
		this.jbf = jbf;
	}

	public double getGdbc() {
		return gdbc;
	}

	public void setGdbc(double gdbc) {
		this.gdbc = gdbc;
	}

	public double getQtgz() {
		return qtgz;
	}

	public void setQtgz(double qtgz) {
		this.qtgz = qtgz;
	}

	public double getZxjf() {
		return zxjf;
	}

	public void setZxjf(double zxjf) {
		this.zxjf = zxjf;
	}

	public double getQtbt() {
		return qtbt;
	}

	public void setQtbt(double qtbt) {
		this.qtbt = qtbt;
	}

	public double getGlkf() {
		return glkf;
	}

	public void setGlkf(double glkf) {
		this.glkf = glkf;
	}

	public double getLxxc() {
		return lxxc;
	}

	public void setLxxc(double lxxc) {
		this.lxxc = lxxc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	@Override
	public String toString() {
		return "DepartmentExamNoticeResult [id=" + id + ", noticeNo=" + noticeNo + ", companyId=" + companyId
				+ ", yearMonthDate=" + yearMonthDate + ", examSummayId=" + examSummayId + ", bmmc=" + bmmc + ", zhdfl="
				+ zhdfl + ", llggxs=" + llggxs + ", xcxfy=" + xcxfy + ", jbxc=" + jbxc + ", llxc=" + llxc + ", jbf="
				+ jbf + ", gdbc=" + gdbc + ", qtgz=" + qtgz + ", zxjf=" + zxjf + ", qtbt=" + qtbt + ", glkf=" + glkf
				+ ", lxxc=" + lxxc + ", status=" + status + ", salaryStatus=" + salaryStatus + ", statusStr="
				+ statusStr + ", total=" + total + "]";
	}
}
