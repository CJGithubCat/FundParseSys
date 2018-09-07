package com.zsh.labouCapital.comm;

/**
 * All rights Reserved, Designed By LDY
 * Copyright:   Copyright(C) 2018
 * todo:系统操作枚举 
 * Createdate:  2018年6月8日下午5:50:06
 */
public enum SysOperateEnum {
	OP_LOG_IN(1,"登录"),
	OP_LOG_OUT(999,"退录");
	
	
	public int moduleId;
	public String desc;
	private SysOperateEnum(int moduleId,String desc){
		this.moduleId = moduleId;
		this.desc = desc;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
