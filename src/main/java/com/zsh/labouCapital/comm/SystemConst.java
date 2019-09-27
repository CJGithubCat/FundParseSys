package com.zsh.labouCapital.comm;

public class SystemConst {	
	//账户信息
	public final static String ACCOUNT_ID="userid"; //用户ID
	public final static String ACCOUNT_LOGINNAME="loingname";//用户登录名
	public final static String ACCOUNT_USERNAME="username";//用户名
	public final static String ACCOUNT_AGENCY="agency";//用户所在的机构对象
	public final static String ACCOUNT_AGENCYID="current_agency_id";//用户所在的机构的ID
	public final static String ACCOUNT_ATTRIBUTE_PATH="attributesPath";//用户所在的机构的ID
	public final static String ACCOUNT_AGENCY_CUSTOMER="agencycustomer";//用户所在的机构关联的客户对象
	public final static int ACCOUNT_DELETESTATUSCODE=2;//账户删除的状态码
	//权限管理使用
	public final static String ROLE="role";//用户角色信息
	public final static String MAIN_MENU_ITEMS = "main_menu_items";//菜单项 ：分类id（1：一类（主菜单类）
	public final static String COMMOND_ITEMS = "commond_items";//3：三类（指令操作类）
	
	//客户端响应信息
	public final static String MESSAGE_OPSUCCESS="操作成功";//操作成功
	public final static String MESSAGE_OPFAILURE="操作失败";//操作失败
	public final static String MESSAGE_USERNOTEXIT="用户名错误";//用户不存在
	public final static String MESSAGE_USERSUSPEND="账户暂停中";//账户暂停中
	public final static String MESSAGE_USERDELETE="账户已经删除";//账户已经删除
	public final static String MESSAGE_PASSWORDWORONG="密码错误";//密码错误
	public final static String MESSAGE_OLDPASSWORDWORONG="旧密码错误";//旧密码错误，用户修改密码	
	public final static String MESSAGE_NOPERMISSION="没有权限";//没有权限
	public final static String ATTRIBUTE_USERLOGIN="USERLOGIN";
	public final static String ATTRIBUTE_LOGINIP="IP";
	public final static String URL_MAIN_PAGE="indexUrl";//登录后显示的主页

	public final static String ACCOUNT_COMPANY_TYPE="companyType";//账户机构类型
	
	
}
