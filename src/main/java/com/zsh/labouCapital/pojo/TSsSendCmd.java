package com.zsh.labouCapital.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zsh.labouCapital.util.DateUtils;

public class TSsSendCmd {
	
	private String cmdSn;
	private int unitId;
	private String callLetter;
	private String plateNo;
	private String opId;
	private String opName;
	private String opSrc;
	private String opSrcversion;
	private String opIp;
	private int cmdId;
	private Date cmdTime;
	private int gatewayId;
	private String sendParams;
	private int mode;
	private Date sendTime;
	private String sendTimeStr;
	private int sendFlag;
	private Date resTime;
	private String resTimeStr;
	private int  resFlag;
	private int type;
	private String resParams;
	//额外添加属性命令名称
	private String cmdName;
	//车辆ID
	private int vehicleId;
	private String msg;//判断权限查询的标志
	private int customerId;
	private List<String> orgIds=new ArrayList<String>();//子用户机构或子用户的分公司机构ID用户集合
	//额外属性
	private String unittype;
	private Long agencyId;
	private String sendFlagName;
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getCmdSn() {
		return cmdSn;
	}
	public void setCmdSn(String cmdSn) {
		this.cmdSn = cmdSn;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		if(callLetter!=null){
		  this.callLetter = callLetter.trim();
		}
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		if(plateNo!=null){
		  this.plateNo = plateNo.trim();
		}
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getOpSrc() {
		return opSrc;
	}
	public void setOpSrc(String opSrc) {
		this.opSrc = opSrc;
	}
	public String getOpSrcversion() {
		return opSrcversion;
	}
	public void setOpSrcversion(String opSrcversion) {
		this.opSrcversion = opSrcversion;
	}
	public String getOpIp() {
		return opIp;
	}
	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
	public Date getCmdTime() {
		return cmdTime;
	}
	public void setCmdTime(Date cmdTime) {
		this.cmdTime = cmdTime;
	}
	public int getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(int gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getSendParams() {
		return sendParams;
	}
	public void setSendParams(String sendParams) {
		this.sendParams = sendParams;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(int sendFlag) {
		this.sendFlag = sendFlag;
	}
	public Date getResTime() {
		return resTime;
	}
	public void setResTime(Date resTime) {
		this.resTime = resTime;
	}
	public int getResFlag() {
		return resFlag;
	}
	public void setResFlag(int resFlag) {
		this.resFlag = resFlag;
	}
	public String getResParams() {
		return resParams;
	}
	public void setResParams(String resParams) {
		this.resParams = resParams;
	}
	public String getCmdName() {
		return cmdName;
	}
	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}
	public String getSendTimeStr() {
		if(sendTime!=null)
			this.sendTimeStr = DateUtils.getDateString(sendTime);
		else
			this.sendTimeStr = "";
		return sendTimeStr;
	}
	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}
	public String getResTimeStr() {
		if(resTime!=null)
			this.resTimeStr = DateUtils.getDateString(resTime);
		else
			this.resTimeStr = "";
		return resTimeStr;
	}
	public void setResTimeStr(String resTimeStr) {
		this.resTimeStr = resTimeStr;
	}
	public String getUnittype() {
		return unittype;
	}
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public String getSendFlagName() {
		return sendFlagName;
	}
	public void setSendFlagName(String sendFlagName) {
		this.sendFlagName = sendFlagName;
	}

}
