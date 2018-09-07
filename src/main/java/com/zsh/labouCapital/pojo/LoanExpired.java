package com.zsh.labouCapital.pojo;
/**
 * @author gongyu
 */
import java.util.Date;
/**
 * 车辆贷款到期日统计 报表
 * @author gongyu
 *
 */
public class LoanExpired {
    private String plateNo;
    private String callLetter;
    private String agencyName;
    private Date squareDate;
    
    /**
	 * 2017-12-20 新增字段
	 * @return
	 */
	private Integer financing_count;//融资期限
	private Integer repay_count;//已还期数
	private String repay_status;//还款状态
	private String contract_no;//合同号
	private String work_address;//工作地址
	private String home_address;//家庭住址
	private String driver_name;//客户姓名
	private String distributor;//经销商
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public Date getSquareDate() {
		return squareDate;
	}
	public void setSquareDate(Date squareDate) {
		this.squareDate = squareDate;
	}
	public Integer getFinancing_count() {
		return financing_count;
	}
	public void setFinancing_count(Integer financing_count) {
		this.financing_count = financing_count;
	}
	public Integer getRepay_count() {
		return repay_count;
	}
	public void setRepay_count(Integer repay_count) {
		this.repay_count = repay_count;
	}
	public String getRepay_status() {
		return repay_status;
	}
	public void setRepay_status(String repay_status) {
		this.repay_status = repay_status;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getWork_address() {
		return work_address;
	}
	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	@Override
	public String toString() {
		return "LoanExpired [plateNo=" + plateNo + ", callLetter=" + callLetter + ", agencyName=" + agencyName
				+ ", squareDate=" + squareDate + ", financing_count=" + financing_count + ", repay_count=" + repay_count
				+ ", repay_status=" + repay_status + ", contract_no=" + contract_no + ", work_address=" + work_address
				+ ", home_address=" + home_address + ", driver_name=" + driver_name + ", distributor=" + distributor
				+ "]";
	}
}
