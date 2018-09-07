package com.zsh.labouCapital.pojo;


/**
 * @Package:com.chinagps.webgis.pojo TWgCPGJ
 * @Description:租赁版热点分析/常跑轨迹开始分析
 */
public class TWgCPGJ {

	private String plateNo;//车牌号
	private String startTime;//查询开始时间
	private String endTime;//查询结束时间
	private String analysisStime;//调用中间件开始分析时间
	private String analysisEtime;//调用中间件结束分析时间
	private int status;//状态:0 分析进行中,1 分析已完成
	private String callLetter;//呼号
	private int userId;//查询者ID 
	private int operateType;//操作类型:0:常跑轨迹,1:热点分析
	private int showUser;//用于弹窗提示客户:0:分析完成,1:客户点击确认
	
	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAnalysisStime() {
		return analysisStime;
	}

	public void setAnalysisStime(String analysisStime) {
		this.analysisStime = analysisStime;
	}

	public String getAnalysisEtime() {
		return analysisEtime;
	}

	public void setAnalysisEtime(String analysisEtime) {
		this.analysisEtime = analysisEtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCallLetter() {
		return callLetter;
	}

	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public int getShowUser() {
		return showUser;
	}

	public void setShowUser(int showUser) {
		this.showUser = showUser;
	}

	@Override
	public String toString() {
		return "TWgCPGJ [plateNo=" + plateNo + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", analysisStime=" + analysisStime + ", analysisEtime=" + analysisEtime + ", status=" + status
				+ "]";
	}
}
