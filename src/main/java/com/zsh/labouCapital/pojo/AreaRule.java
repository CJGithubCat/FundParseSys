package com.zsh.labouCapital.pojo;

import java.io.Serializable;
import java.util.Date;

public class AreaRule implements Serializable{
   
	private static final long serialVersionUID = -991415328369347700L;
	
	private Long ruleId;//规则ID
    private String ruleName;//规则名称
    private Long cusId;//创建规则用户
    private short ruleType;//0=驶入,1=驶出,2区域内;3=驶入驶出'
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private short ruleDeelType;//规则对应的事件处理,0=提醒;1=报警
    private String remark;//
    private short state;//规则能否修改,0=可修改;1=不可修改'    
    private Long agencyId;//机构    
    
    private String queryTimeStart;
    private String queryTimeEnd;
    
    
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		if(ruleName!=null){
		  this.ruleName = ruleName.trim();
		}
	}
	public Long getCusId() {
		return cusId;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public short getRuleType() {
		return ruleType;
	}
	public void setRuleType(short ruleType) {
		this.ruleType = ruleType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public short getRuleDeelType() {
		return ruleDeelType;
	}
	public void setRuleDeelType(short ruleDeelType) {
		this.ruleDeelType = ruleDeelType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}

	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	public String getQueryTimeStart() {
		return queryTimeStart;
	}
	public void setQueryTimeStart(String queryTimeStart) {
		this.queryTimeStart = queryTimeStart;
	}
	public String getQueryTimeEnd() {
		return queryTimeEnd;
	}
	public void setQueryTimeEnd(String queryTimeEnd) {
		this.queryTimeEnd = queryTimeEnd;
	}
	   
    
}
