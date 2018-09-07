package com.zsh.labouCapital.entity;

import java.io.Serializable;

/**
 * <p>
 * Title: Salary
 * </p>
 * <p>
 * Description: 工资类
 * </p>
 * 
 * @author cj
 * @date 2018年7月1日
 */
public class Salary implements Serializable {
	private static final long serialVersionUID = -1881675913369649678L;
	private int id;
	private int xh;
	private int companyId;
	private String yearMonthDate;
	private int noticeId;
	private String noticeNo;//
	private String dw;// 单位
	private String jyzCode;//
	private String jyzmc;//
	private String xm;// 姓名
	private String sfzh;// 身份证号
	private String gw;// 岗位
	private Double jbxc;// 基本薪酬
	private Double llxc;// 联量薪酬
	private Double jykxc;// 加油卡薪酬
	private Double fypxc;// 非油品薪酬
	private Double jbf;// 加班费
	private Double gdbc;// 过渡补差
	private Double qtgz;// 其他工资
	private Double zxjj;// 专项奖金
	private Double zxkf;// 专项扣罚
	private Double qtjf;// 其他奖罚
	private Double qtjtbzd;// 其他津贴补助等
	private Double yfhj;// 应发合计
	private Double gjj;// 公积金
	private Double ylj;// 养老金
	private Double syj;// 失业金
	private Double ybj;// 医保金
	private Double nj;// 年金
	private Double grsds;// 个人所得税
	private Double sfgz;// 实发工资
	private String zh;// 帐号
	private String qm;// 签名
	private String xkhrq;// 新开户日期
	private Double jz;// 借支
	private Double wsf;// 卫生费
	private Double ff;// 房费
	private Double df;// 电费
	private Double nqf;// 暖气费
	private Double dkje;// 打卡金额
	private String sfzh2;// 身份证号2
	private Double yfxyhbksxhj;// 已发需要合并扣税项合计
	private Double gjjdwmyjf;// 公积金单位每月缴费
	private Double yldwmyjf;// 养老单位每月缴费
	private Double sydwmyjf;// 失业单位每月缴费
	private Double gsdwmyjf;// 工伤单位每月缴费
	private Double ybdwmyjf;// 医保单位每月缴费
	private Double sybxdwmyjf;// 生育保险单位每月缴费
	private Double dbbxdwmyjf;// 大病保险单位每月缴费
	private Double njqyjfmyhrgrzhje;// 年金企业缴费每月划入个人账户金额
	private Double njqyjfjrgrzhje;//年金企业缴费每月划入个人账户金额
	private Double njqyjf;// 年金企业缴费
	private Double glf;// 管理费
	private String wtgsmc;
	private String sbcbd;// 社保参保地
	private String status;
	private int salaryStatus;// 工资状态
	private int salaryType;// 工资单类型 0 --加油站；1--部门;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
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
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getJyzCode() {
		return jyzCode;
	}
	public void setJyzCode(String jyzCode) {
		this.jyzCode = jyzCode;
	}
	public String getJyzmc() {
		return jyzmc;
	}
	public void setJyzmc(String jyzmc) {
		this.jyzmc = jyzmc;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	public Double getJbxc() {
		return jbxc;
	}
	public void setJbxc(Double jbxc) {
		this.jbxc = jbxc;
	}
	public Double getLlxc() {
		return llxc;
	}
	public void setLlxc(Double llxc) {
		this.llxc = llxc;
	}
	public Double getJykxc() {
		return jykxc;
	}
	public void setJykxc(Double jykxc) {
		this.jykxc = jykxc;
	}
	public Double getFypxc() {
		return fypxc;
	}
	public void setFypxc(Double fypxc) {
		this.fypxc = fypxc;
	}
	public Double getJbf() {
		return jbf;
	}
	public void setJbf(Double jbf) {
		this.jbf = jbf;
	}
	public Double getGdbc() {
		return gdbc;
	}
	public void setGdbc(Double gdbc) {
		this.gdbc = gdbc;
	}
	public Double getQtgz() {
		return qtgz;
	}
	public void setQtgz(Double qtgz) {
		this.qtgz = qtgz;
	}
	public Double getZxjj() {
		return zxjj;
	}
	public void setZxjj(Double zxjj) {
		this.zxjj = zxjj;
	}
	public Double getZxkf() {
		return zxkf;
	}
	public void setZxkf(Double zxkf) {
		this.zxkf = zxkf;
	}
	public Double getQtjf() {
		return qtjf;
	}
	public void setQtjf(Double qtjf) {
		this.qtjf = qtjf;
	}
	public Double getQtjtbzd() {
		return qtjtbzd;
	}
	public void setQtjtbzd(Double qtjtbzd) {
		this.qtjtbzd = qtjtbzd;
	}
	public Double getYfhj() {
		return yfhj;
	}
	public void setYfhj(Double yfhj) {
		this.yfhj = yfhj;
	}
	public Double getGjj() {
		return gjj;
	}
	public void setGjj(Double gjj) {
		this.gjj = gjj;
	}
	public Double getYlj() {
		return ylj;
	}
	public void setYlj(Double ylj) {
		this.ylj = ylj;
	}
	public Double getSyj() {
		return syj;
	}
	public void setSyj(Double syj) {
		this.syj = syj;
	}
	public Double getYbj() {
		return ybj;
	}
	public void setYbj(Double ybj) {
		this.ybj = ybj;
	}
	public Double getNj() {
		return nj;
	}
	public void setNj(Double nj) {
		this.nj = nj;
	}
	public Double getGrsds() {
		return grsds;
	}
	public void setGrsds(Double grsds) {
		this.grsds = grsds;
	}
	public Double getSfgz() {
		return sfgz;
	}
	public void setSfgz(Double sfgz) {
		this.sfgz = sfgz;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getQm() {
		return qm;
	}
	public void setQm(String qm) {
		this.qm = qm;
	}
	public String getXkhrq() {
		return xkhrq;
	}
	public void setXkhrq(String xkhrq) {
		this.xkhrq = xkhrq;
	}
	public Double getJz() {
		return jz;
	}
	public void setJz(Double jz) {
		this.jz = jz;
	}
	public Double getWsf() {
		return wsf;
	}
	public void setWsf(Double wsf) {
		this.wsf = wsf;
	}
	public Double getFf() {
		return ff;
	}
	public void setFf(Double ff) {
		this.ff = ff;
	}
	public Double getDf() {
		return df;
	}
	public void setDf(Double df) {
		this.df = df;
	}
	public Double getNqf() {
		return nqf;
	}
	public void setNqf(Double nqf) {
		this.nqf = nqf;
	}
	public Double getDkje() {
		return dkje;
	}
	public void setDkje(Double dkje) {
		this.dkje = dkje;
	}
	public String getSfzh2() {
		return sfzh2;
	}
	public void setSfzh2(String sfzh2) {
		this.sfzh2 = sfzh2;
	}
	public Double getYfxyhbksxhj() {
		return yfxyhbksxhj;
	}
	public void setYfxyhbksxhj(Double yfxyhbksxhj) {
		this.yfxyhbksxhj = yfxyhbksxhj;
	}
	public Double getGjjdwmyjf() {
		return gjjdwmyjf;
	}
	public void setGjjdwmyjf(Double gjjdwmyjf) {
		this.gjjdwmyjf = gjjdwmyjf;
	}
	public Double getYldwmyjf() {
		return yldwmyjf;
	}
	public void setYldwmyjf(Double yldwmyjf) {
		this.yldwmyjf = yldwmyjf;
	}
	public Double getSydwmyjf() {
		return sydwmyjf;
	}
	public void setSydwmyjf(Double sydwmyjf) {
		this.sydwmyjf = sydwmyjf;
	}
	public Double getGsdwmyjf() {
		return gsdwmyjf;
	}
	public void setGsdwmyjf(Double gsdwmyjf) {
		this.gsdwmyjf = gsdwmyjf;
	}
	public Double getYbdwmyjf() {
		return ybdwmyjf;
	}
	public void setYbdwmyjf(Double ybdwmyjf) {
		this.ybdwmyjf = ybdwmyjf;
	}
	public Double getSybxdwmyjf() {
		return sybxdwmyjf;
	}
	public void setSybxdwmyjf(Double sybxdwmyjf) {
		this.sybxdwmyjf = sybxdwmyjf;
	}
	public Double getDbbxdwmyjf() {
		return dbbxdwmyjf;
	}
	public void setDbbxdwmyjf(Double dbbxdwmyjf) {
		this.dbbxdwmyjf = dbbxdwmyjf;
	}
	public Double getNjqyjfmyhrgrzhje() {
		return njqyjfmyhrgrzhje;
	}
	public void setNjqyjfmyhrgrzhje(Double njqyjfmyhrgrzhje) {
		this.njqyjfmyhrgrzhje = njqyjfmyhrgrzhje;
	}
	public Double getNjqyjfjrgrzhje() {
		return njqyjfjrgrzhje;
	}
	public void setNjqyjfjrgrzhje(Double njqyjfjrgrzhje) {
		this.njqyjfjrgrzhje = njqyjfjrgrzhje;
	}
	public Double getNjqyjf() {
		return njqyjf;
	}
	public void setNjqyjf(Double njqyjf) {
		this.njqyjf = njqyjf;
	}
	public Double getGlf() {
		return glf;
	}
	public void setGlf(Double glf) {
		this.glf = glf;
	}
	public String getWtgsmc() {
		return wtgsmc;
	}
	public void setWtgsmc(String wtgsmc) {
		this.wtgsmc = wtgsmc;
	}
	public String getSbcbd() {
		return sbcbd;
	}
	public void setSbcbd(String sbcbd) {
		this.sbcbd = sbcbd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSalaryStatus() {
		return salaryStatus;
	}
	public void setSalaryStatus(int salaryStatus) {
		this.salaryStatus = salaryStatus;
	}
	public int getSalaryType() {
		return salaryType;
	}
	public void setSalaryType(int salaryType) {
		this.salaryType = salaryType;
	}
	@Override
	public String toString() {
		return "Salary [id=" + id + ", xh=" + xh + ", companyId=" + companyId + ", yearMonthDate=" + yearMonthDate
				+ ", noticeId=" + noticeId + ", noticeNo=" + noticeNo + ", dw=" + dw + ", jyzCode=" + jyzCode
				+ ", jyzmc=" + jyzmc + ", xm=" + xm + ", sfzh=" + sfzh + ", gw=" + gw + ", jbxc=" + jbxc + ", llxc="
				+ llxc + ", jykxc=" + jykxc + ", fypxc=" + fypxc + ", jbf=" + jbf + ", gdbc=" + gdbc + ", qtgz=" + qtgz
				+ ", zxjj=" + zxjj + ", zxkf=" + zxkf + ", qtjf=" + qtjf + ", qtjtbzd=" + qtjtbzd + ", yfhj=" + yfhj
				+ ", gjj=" + gjj + ", ylj=" + ylj + ", syj=" + syj + ", ybj=" + ybj + ", nj=" + nj + ", grsds=" + grsds
				+ ", sfgz=" + sfgz + ", zh=" + zh + ", qm=" + qm + ", xkhrq=" + xkhrq + ", jz=" + jz + ", wsf=" + wsf
				+ ", ff=" + ff + ", df=" + df + ", nqf=" + nqf + ", dkje=" + dkje + ", sfzh2=" + sfzh2
				+ ", yfxyhbksxhj=" + yfxyhbksxhj + ", gjjdwmyjf=" + gjjdwmyjf + ", yldwmyjf=" + yldwmyjf + ", sydwmyjf="
				+ sydwmyjf + ", gsdwmyjf=" + gsdwmyjf + ", ybdwmyjf=" + ybdwmyjf + ", sybxdwmyjf=" + sybxdwmyjf
				+ ", dbbxdwmyjf=" + dbbxdwmyjf + ", njqyjfmyhrgrzhje=" + njqyjfmyhrgrzhje + ", njqyjfjrgrzhje="
				+ njqyjfjrgrzhje + ", njqyjf=" + njqyjf + ", glf=" + glf + ", wtgsmc=" + wtgsmc + ", sbcbd=" + sbcbd
				+ ", status=" + status + ", salaryStatus=" + salaryStatus + ", salaryType=" + salaryType + "]";
	}
}