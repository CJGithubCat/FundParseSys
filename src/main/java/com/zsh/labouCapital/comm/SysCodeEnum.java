package com.zsh.labouCapital.comm;

/**
 * All rights Reserved, Designed By LDY Copyright: Copyright(C) 2018
 * todo:系统状态码，返回码 Createdate: 2018年6月8日下午5:52:30
 */
public enum SysCodeEnum {
	RE_LOGIN_SUCESS(0, "登录成功"), 
	RE_LOGIN_USER_PAUSE(1, "账号暂停中"), 
	RE_LOGIN_USER_DELETED(2,"账号已删除"), 
	RE_LOGIN_USER_NOFOUND(3,"用户不存在"), 
	RE_LOGIN_PWD_ERR(4, "密码错误"),
	RE_LOGIN_NO_PERMISSION(5, "用户无权限"),
	RE_LOGIN_PASSWORD_OUTDATE(6, "密码超期未修改,请联系管理员"),
	RE_SYS_ERROR(-1, "系统异常,请联系管理员"),
	;

	private Integer code;
	private String desc;

	private SysCodeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
