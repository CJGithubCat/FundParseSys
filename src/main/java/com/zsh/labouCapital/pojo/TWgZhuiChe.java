package com.zsh.labouCapital.pojo;

public class TWgZhuiChe {
	private int userId;       //int(10) unsigned 追车人员id                                               
	private String userName;     //varchar(50)      追车人姓名                                               
	private String mobilePhone;  //varchar(20)      手机号码                                                 
	private int agencyId;    //int(10)          服务企业id(租赁系统机构id)  
	private String agencyName;
	private String status;        //tinyint(3)       状态,-1:未审核; 0:正常;1:暂停;2:删除;                    
	private String openId;       //varchar(50)      微信端关联id                                             
	private int grade;         //int(3)           追车人员综合评分,0-100                                   
	private String remark;        //varchar(200)     备注                                                     
	private int isPush;       //tinyint(2)       验证通过是否推送给用户,0:未推送;1:已推送                 
	private int isLogin;      //tinyint(1)       是否登陆,0:未登录;1:已登录                               
	private String stamp;         //timestamp        注册时间                
	private String shTime;         //timestamp        审核时间                 
	private String headimgurl;    //varchar(500)     追车用户头像url               
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsPush() {
		return isPush;
	}
	public void setIsPush(int isPush) {
		this.isPush = isPush;
	}
	public int getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	public String getShTime() {
		return shTime;
	}
	public void setShTime(String shTime) {
		this.shTime = shTime;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	@Override
	public String toString() {
		return "TWgZhuiChe [userId=" + userId + ", userName=" + userName + ", mobilePhone=" + mobilePhone
				+ ", agencyId=" + agencyId + ", agencyName=" + agencyName + ", status=" + status + ", openId=" + openId
				+ ", grade=" + grade + ", remark=" + remark + ", isPush=" + isPush + ", isLogin=" + isLogin + ", stamp="
				+ stamp + ", shTime=" + shTime + ", headimgurl=" + headimgurl + "]";
	}
	
}
