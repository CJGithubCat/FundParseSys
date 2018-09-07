package com.zsh.labouCapital.comm;

import java.awt.List;
import java.util.ArrayList;

public class SystemConst {	
	
    public final static String ACCOUNT_DEPARTID="departid";
    public final static String ACCOUNT_ROLEIDS="roleid";
    public final static String ACCOUNT_TYPE="usertype";//登录帐号类型
    public final static String ACCOUNT_GROUPID="groupid";
    public final static String ACCOUNT_USER="user";
    public final static String ACCOUNT_ORGID="orgid";
    public final static String ACCOUNT_COMPANYID="companyid";//分公司机构编号
    public final static String ACCOUNT_COMPANYCODE="companycode";//分公司机构编号
    public final static String ACCOUNT_COMPANYNAME="companyname";//分公司机构名称
    /** 网查根机构编号*/
    public final static String ACCOUNT_WG_COMPANYID = "wgcompanyid";
    /** 网查分机构编号*/
    public static final String ACCOUNT_WG_ORGID = "wgorgid";
    /** 网查子机构编号*/
    public final static String ACCOUNT_WG_COMPANYCODE = "wgcompanycode";
    /** 根用户所在机构名称*/
    public final static String ACCOUNT_WG_COMPANYNAME = "wgcompanyname";
    /** 分组用户所属机构*/
    public final static String ACCOUNT_WG_ORGNAME = "wgorgname";
    /** 根用户customerid*/
    public static final String ACCOUNT_WG_CUSTOMERID = "wgcustomerid";
    /** ldap用户ID*/
    public static final String ACCOUNT_WG_OPID = "wgopid";
    /** Memcached缓存权限数据*/
    public final static String ACCOUNT_WG_MEMCACHED_DATA_PERMISSION = "_datapermisson_";
    /** Memcached缓存权限数据时长,单位：秒*/
    public final static int ACCOUNT_WG_MEMCACHED_DATA_TIME = 30*24*60*60;
    /** 判断是否网查用户*/
    public static final String ACCOUNT_WG_ISNOTWGUSER = "iswguser";
   
	public final static int DEFAULT_PAGE_SIZE = 10; // 每页行数
	//权限系统接口地址列表
	public final static String HTTP_RIS_OPERATOR = "/operator/listByOrgid";//这个是加了orgid根据登录名获取用户
	public final static String HTTP_RIS_GET_OPERATOR ="/getOperator";//根据登录名获取用户
	public final static String HTTP_RIS_ADD_OPERATOR = "/operator/addOperator";//新增操作员
	public static final String HTTP_RIS_UPDATE_OPERATOR = "/operator/updateOperatorInfo";//修改操作员
	public static final String HTTP_RIS_DELETE_OPERATOR = "/operator/deleteOperator";//删除操作员
	public static final String HTTP_RIS_GET_USERROLE = "/role/findSysRole";//获取系统角色列表
	public static final String HTTP_RIS_SET_USERROLE = "/role/setUserRole";//设值用户角色
	public static final String HTTP_RIS_SET_PASSWORD = "/setPassword";//修改密码
    public static final String HTTP_RIS_GET_USERMOUDLES = "/moduleList";//模块信息
    public static final String HTTP_RIS_GET_VERIFYPWD = "/verifyOperator";//验证登录密码是否正确
	
	//预存指令下发http请求
	public static final String HTTP_PRE_CMD_SET_PASSWORD = "/sendcmd";//修改密码
	
	//public final static String SYSTEMID="9c7fc19936a34a26ba6af3d3aae2472f";//LDAP中本系统的systemid
	public final static String SYSTEMID="2da1a64cea404c0eacf58c5c4a883564";//二版门店子系统
	public static final String LOGID = "f233580102da4553b1a118a449c23774";
	public final static String TOPCOMPANYNO="3";//用于判断是否属于分公司
	public final static String HEADQUARTERS="2";//用于判断是否属于总部
	public final static String GROUPCOMPANYNO="4";//用于判断是否属于集团机构
	
	public final static String ROLEID_SALES_MANAGER="3b91ee9136a841a8ac4a912133ca4a64";//销售经理的角色ID
	public final static String ROLEID_ORG_MANAGER="6c4209011b384bfc8c01eb8e5f079c42";//部门经理的角色ID
	public final static String ROLEID_SALES_DIRECTOR="fa98735655e34dffae93f4d5fd1836b7";//销售总监的角色ID
	public final static String ROLEID_ELCTRICIAN="af0f3e9e83ec4c6ba623a6b2f6b409c9";//营业处电工
	public final static String ROLEID_ORDERS_RECIPIENT="b89b54165cc249c69fb9b90bff64ee62";//订单接收人
	
	
	//操作结果描述
	public final static String OP_SUCCESS="操作成功";
	public final static String OP_FAILURE="操作失败";
	public final static String SUCCESS="success";
	public final static String MSG="msg";
	public final static String MESSAGE="message";
	
	//操作日志表-操作类型
	public final static String OPERATELOG_LOGIN="10001";
	public final static String OPERATELOG_ADD="10002";
	public final static String OPERATELOG_DEL="10003";
	public final static String OPERATELOG_UPDATE="10004";
	public final static String OPERATELOG_SEARCHKEY="10005";
	public final static String OPERATELOG_LOGOUT="10006";
	public final static String OPERATELOG_SAVE="10007";
	
	
	//操作日志参数key
	public final static String OPLOG_PARAMS_KEY="oplog_params_key";
	
	//Memcached读取通信中心信息关键字
	public final static String MEMCACHED_GPS ="gateway";
	
	//---以上为湖南旧代码---------------------------------------------
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
