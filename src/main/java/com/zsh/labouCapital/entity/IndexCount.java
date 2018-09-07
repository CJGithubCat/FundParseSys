package com.zsh.labouCapital.entity;

public class IndexCount {
	private Integer companyId;// 机构id
	private String companyPath;// 当前用户的attributes;
	private String companyType = "-1";//
	private int flag;// 是否首次登录，0--首次登陆； 1--不是首次登录
	private int xgs_gzd_daishenhe = -1;//县公司工资单待审核
	private int jyz_gzd_daishenhe = -1;//加油站工资单待审核
	private int xgs_khtzd_weixifa = -1;//县公司考核通知单未下发
	private int jyz_khtzd_weixifa = -1;//加油站考核通知单未下发
	private int jyz_khtzd_daiqueren = -1;//加油站考核通知单待确认
	private int xgs_khtzd_daiqueren = -1;//县公司考核通知单待确认
	private int xgs_gzd_weitongguo = -1;//县公司工资单未通过
	private int jyz_gzd_weitongguo = -1;//加油站工资单未通过
	
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyPath() {
		return companyPath;
	}
	public void setCompanyPath(String companyPath) {
		this.companyPath = companyPath;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getXgs_gzd_daishenhe() {
		return xgs_gzd_daishenhe;
	}
	public void setXgs_gzd_daishenhe(int xgs_gzd_daishenhe) {
		this.xgs_gzd_daishenhe = xgs_gzd_daishenhe;
	}
	public int getJyz_gzd_daishenhe() {
		return jyz_gzd_daishenhe;
	}
	public void setJyz_gzd_daishenhe(int jyz_gzd_daishenhe) {
		this.jyz_gzd_daishenhe = jyz_gzd_daishenhe;
	}
	public int getXgs_khtzd_weixifa() {
		return xgs_khtzd_weixifa;
	}
	public void setXgs_khtzd_weixifa(int xgs_khtzd_weixifa) {
		this.xgs_khtzd_weixifa = xgs_khtzd_weixifa;
	}
	public int getJyz_khtzd_weixifa() {
		return jyz_khtzd_weixifa;
	}
	public void setJyz_khtzd_weixifa(int jyz_khtzd_weixifa) {
		this.jyz_khtzd_weixifa = jyz_khtzd_weixifa;
	}
	public int getJyz_khtzd_daiqueren() {
		return jyz_khtzd_daiqueren;
	}
	public void setJyz_khtzd_daiqueren(int jyz_khtzd_daiqueren) {
		this.jyz_khtzd_daiqueren = jyz_khtzd_daiqueren;
	}
	public int getXgs_khtzd_daiqueren() {
		return xgs_khtzd_daiqueren;
	}
	public void setXgs_khtzd_daiqueren(int xgs_khtzd_daiqueren) {
		this.xgs_khtzd_daiqueren = xgs_khtzd_daiqueren;
	}
	public int getXgs_gzd_weitongguo() {
		return xgs_gzd_weitongguo;
	}
	public void setXgs_gzd_weitongguo(int xgs_gzd_weitongguo) {
		this.xgs_gzd_weitongguo = xgs_gzd_weitongguo;
	}
	public int getJyz_gzd_weitongguo() {
		return jyz_gzd_weitongguo;
	}
	public void setJyz_gzd_weitongguo(int jyz_gzd_weitongguo) {
		this.jyz_gzd_weitongguo = jyz_gzd_weitongguo;
	}
	@Override
	public String toString() {
		return "IndexCount [companyId=" + companyId + ", companyPath=" + companyPath + ", companyType=" + companyType
				+ ", flag=" + flag + ", xgs_gzd_daishenhe=" + xgs_gzd_daishenhe + ", jyz_gzd_daishenhe="
				+ jyz_gzd_daishenhe + ", xgs_khtzd_weixifa=" + xgs_khtzd_weixifa + ", jyz_khtzd_weixifa="
				+ jyz_khtzd_weixifa + ", jyz_khtzd_daiqueren=" + jyz_khtzd_daiqueren + ", xgs_khtzd_daiqueren="
				+ xgs_khtzd_daiqueren + ", xgs_gzd_weitongguo=" + xgs_gzd_weitongguo + ", jyz_gzd_weitongguo="
				+ jyz_gzd_weitongguo + "]";
	}
}
