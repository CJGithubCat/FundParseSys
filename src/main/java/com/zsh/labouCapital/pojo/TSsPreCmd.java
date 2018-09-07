package com.zsh.labouCapital.pojo;

import java.io.Serializable;
/**
 * 预存指令信息实体类
 */
public class TSsPreCmd implements Serializable {

	
	private static final long serialVersionUID = -5223344559628329202L;

	private Long preId;
	private Long unitId;
	private String plateNo;
	private String callLetter;
	/** 操作员名称*/
	private String opName;
	/** 发送源, 1=座席, 2=网上查车, 3=App*/
	private Integer opSrc;
	/** 客户端IP和端口,IP:Port*/
	private String opIp;
	/** 指令字*/
	private Integer cmdId;
	/** 指令参数*/
	private String sendParams;
	/** 预存时间*/
	private String stamp;
	/** 终端应答时间，可能没有*/
	private String resTime;
	/** 终端应答标记, 0=命令成功, >0为错误代码*/
	private Integer resFlag;
	
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
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public Integer getOpSrc() {
		return opSrc;
	}
	public void setOpSrc(Integer opSrc) {
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
		this.sendParams = sendParams;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getResTime() {
		return resTime;
	}
	public void setResTime(String resTime) {
		this.resTime = resTime;
	}
	public Integer getResFlag() {
		return resFlag;
	}
	public void setResFlag(Integer resFlag) {
		this.resFlag = resFlag;
	}
	
}
