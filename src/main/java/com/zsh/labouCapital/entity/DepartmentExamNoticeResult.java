package com.zsh.labouCapital.entity;

import java.io.Serializable;

/**
 * table name: t_department_exam_notice_result desc:部门考核通知单 create time:
 * 2018-06-21 00:27:01
 */
public class DepartmentExamNoticeResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double companyId;
	private String noticeNo;
	private String yearMonthDate;
	private int examSummayId;// 与t_exam_notice表notice_id列相关联
	private String bmmc;// 部门名称
	private String statusStr;// 部门名称
	private double llggxs;// 联量挂钩系数
	private double xcxfy; // 合计：薪酬性费用
	private double jbxc;// 基本薪酬
	private double llxc;// 联量薪酬
	private double qjryxc;// 请假人员工资
	private double gdbc;// 过渡补差
	private double blc;// 保留差
	private double qtbt;// 其他补贴
	private double jbf;// 加班费
	private double qtgz;// 其他工资,汇总表里是（整月看站及借调等人员工资）
	private double zxjf;// 专项奖罚
	private double glkf;// 管理扣罚
	private double lxxc;// 劳效薪酬
	private double qjrygz;// 请假人员工资
	private int status;
	private int salaryStatus;
	private double total;// 总数
	private String fbkType;// 分版块类型

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getQjrygz() {
		return qjrygz;
	}

	public void setQjrygz(double qjrygz) {
		this.qjrygz = qjrygz;
	}

	public double getCompanyId() {
		return companyId;
	}

	public void setCompanyId(double companyId) {
		this.companyId = companyId;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
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

	public double getQjryxc() {
		return qjryxc;
	}

	public void setQjryxc(double qjryxc) {
		this.qjryxc = qjryxc;
	}

	public double getGdbc() {
		return gdbc;
	}

	public void setGdbc(double gdbc) {
		this.gdbc = gdbc;
	}

	public double getBlc() {
		return blc;
	}

	public void setBlc(double blc) {
		this.blc = blc;
	}

	public double getQtbt() {
		return qtbt;
	}

	public void setQtbt(double qtbt) {
		this.qtbt = qtbt;
	}

	public double getJbf() {
		return jbf;
	}

	public void setJbf(double jbf) {
		this.jbf = jbf;
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

	public int getSalaryStatus() {
		return salaryStatus;
	}

	public void setSalaryStatus(int salaryStatus) {
		this.salaryStatus = salaryStatus;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getFbkType() {
		return fbkType;
	}

	public void setFbkType(String fbkType) {
		this.fbkType = fbkType;
	}

	@Override
	public String toString() {
		return "DepartmentExamNoticeResult [id=" + id + ", companyId=" + companyId + ", noticeNo=" + noticeNo
				+ ", yearMonthDate=" + yearMonthDate + ", examSummayId=" + examSummayId + ", bmmc=" + bmmc + ", llggxs="
				+ llggxs + ", xcxfy=" + xcxfy + ", jbxc=" + jbxc + ", llxc=" + llxc + ", qjryxc=" + qjryxc + ", gdbc="
				+ gdbc + ", blc=" + blc + ", qtbt=" + qtbt + ", jbf=" + jbf + ", qtgz=" + qtgz + ", zxjf=" + zxjf
				+ ", glkf=" + glkf + ", lxxc=" + lxxc + ", qjrygz=" + qjrygz + ", status=" + status + ", salaryStatus="
				+ salaryStatus + ", total=" + total + "]";
	}
}
