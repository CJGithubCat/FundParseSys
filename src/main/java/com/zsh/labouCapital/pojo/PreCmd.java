package com.zsh.labouCapital.pojo;

import java.util.Date;
/**
 * 预存指令
 * @author gongyu
 *
 */
public class PreCmd {
    private Long preId;
    private Long unitId;
    private String callLetter;
    private String opName;
    private short opSrc;
    private String opIp;
    private Integer cmdId;
    private String sendParams;
    private Date stamp;
    private Integer resFlag;
    private Integer sendCount;
    private String sn;//指令唯一编号
	public Long getPreId() {
		return preId;
	}
	public void setPreId(Long preId) {
		this.preId = preId;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public short getOpSrc() {
		return opSrc;
	}
	public void setOpSrc(short opSrc) {
		this.opSrc = opSrc;
	}
	public String getOpIp() {
		return opIp;
	}
	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}
	public Integer getCmdId() {
		return cmdId;
	}
	public void setCmdId(Integer cmdId) {
		this.cmdId = cmdId;
	}
	public String getSendParams() {
		return sendParams;
	}
	public void setSendParams(String sendParams) {
		if("".equals(sendParams)){
			sendParams=null;
		}else{
		   this.sendParams = sendParams;
		}
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public Integer getResFlag() {
		return resFlag;
	}
	public void setResFlag(Integer resFlag) {
		this.resFlag = resFlag;
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	@Override
	public String toString() {
		return "PreCmd [preId=" + preId + ", unitId=" + unitId + ", callLetter=" + callLetter + ", opName=" + opName
				+ ", opSrc=" + opSrc + ", opIp=" + opIp + ", cmdId=" + cmdId + ", sendParams=" + sendParams + ", stamp="
				+ stamp + ", resFlag=" + resFlag + ", sendCount=" + sendCount + ", sn=" + sn + "]";
	}
}
