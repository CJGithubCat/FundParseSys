package com.zsh.labouCapital.entity;

/**
 * 操作日志实体类
 */
public class OperationLog implements java.io.Serializable {
	private static final long serialVersionUID = 420425080011217721L;

	private int logId;
	private int userId;
	private String opTime;
	private int moduleId;
	private String remoteIp;
	private String operateDescription;
	private String userName;
	private String loginName;
	private String description;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getOperateDescription() {
		return operateDescription;
	}

	public void setOperateDescription(String operateDescription) {
		this.operateDescription = operateDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "OperationLog [logId=" + logId + ", userId=" + userId + ", opTime=" + opTime + ", moduleId=" + moduleId
				+ ", remoteIp=" + remoteIp + ", operateDescription=" + operateDescription + ", userName=" + userName
				+ ", loginName=" + loginName + ", description=" + description + "]";
	}

}