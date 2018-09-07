package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zsh.labouCapital.comm.SysCodeEnum;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.Role;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.pojo.TWgPermission;
import com.zsh.labouCapital.service.ICompanyService;
import com.zsh.labouCapital.service.IModuleService;
import com.zsh.labouCapital.service.IRoleService;
import com.zsh.labouCapital.service.IUserService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private IUserService userService;
	
	/**********************************************************************/
	@Autowired
	private IModuleService iModuleService;
	
	@Autowired
	private ICompanyService iCompanyService;
	
	@Autowired
	private IRoleService iRoleService;
	
	
   /**
    * 验证用户名，密码
    * @param username
    * @param password
    * @return
    * @throws Exception
    */	
	@SuppressWarnings("unchecked")
	@ResponseBody()
    @RequestMapping(value={"/validatepwd"},method={RequestMethod.POST})
	public ReturnValue validatePwd(HttpServletRequest request,@RequestBody String jsonParam)throws Exception{
		 HttpSession session;
		 User user=new Gson().fromJson(jsonParam,User.class);
		 //用户名，密码验证
    	 int code=userService.validatePwd(user);
    	 ReturnValue responseData=new ReturnValue();
   	     if(code==3){//用户不存在
   	    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_USER_NOFOUND.getCode());
   		    responseData.setMessage(SysCodeEnum.RE_LOGIN_USER_NOFOUND.getDesc());
   	     }else{
   		     if(code==0){
   		    	session=request.getSession(false);
   		    	//获取用户信息
   		    	user=userService.getUserByLoginName(user.getLoginName());
   		   	    session.setAttribute(SystemConst.ACCOUNT_ID,user.getUserId());
   		   	    session.setAttribute(SystemConst.ACCOUNT_LOGINNAME,user.getLoginName());
   		   	    session.setAttribute(SystemConst.ACCOUNT_USERNAME,user.getUserName()); 
   		   	    session.setAttribute(SystemConst.ACCOUNT_COMPANY_TYPE, user.getCompanyType());
   		    	
   		    	//获取当前用户的菜单项和指令项功能
   		   	    HashMap<String, Object> hm =  iModuleService.queryModulesByUid(user.getUserId());
		   	    //HashMap<String, Object> hm = tWgPermissionService.queryPermissionsByUid(user.getUserId());
		   	    ArrayList<TWgPermission> mainItems =  (ArrayList<TWgPermission>) hm.get(SystemConst.MAIN_MENU_ITEMS);
		   	    ArrayList<TWgPermission> commItems =  (ArrayList<TWgPermission>) hm.get(SystemConst.COMMOND_ITEMS);
   		    	if(mainItems.size() == 0 && mainItems.size() ==0){
   		   	    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_NO_PERMISSION.getCode());
   		   	    	responseData.setMessage(SysCodeEnum.RE_LOGIN_NO_PERMISSION.getDesc());
   		   	    	return responseData;
		   	    }//有权限登录
		   	    responseData.setErrorCode(SysCodeEnum.RE_LOGIN_SUCESS.getCode());   		    	
		   	    //将菜单放入session
   		    	session.setAttribute(SystemConst.MAIN_MENU_ITEMS, mainItems);//主菜单
		   	    session.setAttribute(SystemConst.COMMOND_ITEMS, commItems);//指令菜单
   		    	  		   	    
   		   	   // TWgAgency agency=agencyService.findById(String.valueOf(user.getCompanyId()));
   		   	   Company company =null;
   		   	   try {
   		   		   company = iCompanyService.findById(String.valueOf(user.getCompanyId()));
    		   	   session.setAttribute(SystemConst.ACCOUNT_AGENCY, company);
    		   	   session.setAttribute(SystemConst.ACCOUNT_AGENCYID, company.getCompanyId());
    		   	   session.setAttribute(SystemConst.ACCOUNT_ATTRIBUTE_PATH, company.getCompanyPath());//机构路径
				} catch (Exception e) {
					e.printStackTrace();
				}
   		   	    
   		   	    session.setAttribute(SystemConst.ATTRIBUTE_LOGINIP, RequestRealIp.getIpAddress(request));
   		   	    /*session.setAttribute(SystemConst.ATTRIBUTE_USERLOGIN, new UserLoginHistoryListener());*/
   		   	    
   		   	    //查询并设置当前用户的角色信息
   		   	    //TWgRoles roles = twgRolesService.queryRoleByUid(user.getUserId());
   		   	    Role roles = iRoleService.queryRoleByUid(user.getUserId());
   		   	    if(roles == null){
   		   	    	session.setAttribute(SystemConst.ROLE, null);
   		   	    }else{
   		   	    	session.setAttribute(SystemConst.ROLE, roles);
   		   	    }
   		   	    //写日志
   		   	    //iloggerService.writeLog(user.getUserId(), DateUtil.formatNowTime(), SysOperateEnum.OP_LOG_IN.getModuleId(),ip, SysOperateEnum.OP_LOG_IN.getDesc());
  		     }else if(code==1){
  		    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_USER_PAUSE.getCode());
  			    responseData.setMessage(SysCodeEnum.RE_LOGIN_USER_PAUSE.getDesc());
  		     }else if(code==2){
  		    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_USER_DELETED.getCode());
   		        responseData.setMessage(SysCodeEnum.RE_LOGIN_USER_DELETED.getDesc());
  		     }else if(code==6){
  		    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_PASSWORD_OUTDATE.getCode());
   		        responseData.setMessage(SysCodeEnum.RE_LOGIN_PASSWORD_OUTDATE.getDesc());
  		     }else{
  		    	responseData.setErrorCode(SysCodeEnum.RE_LOGIN_PWD_ERR.getCode());
   		        responseData.setMessage(SysCodeEnum.RE_LOGIN_PWD_ERR.getDesc());
  		    }
   	    }
   	   return responseData;   	
    }
   /**
    *  进入主页 
    * @param request
    * @param response
    * @param username
    * @param password
    * @throws Exception
    */    
	/*@RequestMapping(value={"/index"},method={RequestMethod.GET})    
    public void login(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	HttpSession session;
    	session=request.getSession(false);
    	String loginUrl=request.getServletContext().getAttribute(SystemConst.URL_BASE).toString();
		if(null==session){			
	    	response.sendRedirect(loginUrl);
	    	return;
		}else{
			Object loginNameObj=session.getAttribute(SystemConst.ACCOUNT_LOGINNAME);
			if(null==loginNameObj){
    	    	response.sendRedirect(loginUrl); 
    	    	return;
			}else{
				request.getRequestDispatcher("/htmls/index.html").forward(request, response); 
			}
		}   	    
    }*/
   /**
    * 退出
    * @param request
    * @param response
    * @throws Exception
    */
    @RequestMapping(value={"/logout"},method={RequestMethod.GET})    
    public void logout(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	HttpSession session=request.getSession(false);    	
    	if(session!=null){
    		session.removeAttribute(SystemConst.ATTRIBUTE_USERLOGIN);
    		Enumeration<String> sessionAttrNameEnu=session.getAttributeNames();
    		while(sessionAttrNameEnu.hasMoreElements()){
    			String sessioAttrName=sessionAttrNameEnu.nextElement();
    			session.removeAttribute(sessioAttrName);
    		}
    	    session.invalidate();
    	}    
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	String loginUrl = httpRequest.getScheme()+"://"+httpRequest.getServerName()+":"+httpRequest.getLocalPort()+httpRequest.getContextPath()+"/";
		response.sendRedirect(loginUrl);
    }
   /**
    * 首页获得用户名 
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    @RequestMapping(value={"/getloginusername"},method={RequestMethod.POST})  
    @ResponseBody
    public ReturnValue getLoginUserName(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	ReturnValue rv=new ReturnValue();
    	try{
    	  HttpSession session=request.getSession(false);    	
    	  if(session!=null){
    		Object loginNameObj=session.getAttribute(SystemConst.ACCOUNT_LOGINNAME);
    		if(loginNameObj!=null){
    			rv.setMessage(loginNameObj.toString());
    			rv.setSuccess(true);
    		}else{
    			rv.setSuccess(false);    		
    		}
    	 }
       }catch(Exception ex){
    	   logger.error(ex.getMessage());
    	   rv.setSuccess(false);     	   
       }    	
       return rv;
    }
    /**
     * 登录前：查询t_wg_user表中cache_status的状态 0：浏览器缓存未清空，1：浏览器缓存已清空
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
     @ResponseBody()
     @RequestMapping(value={"/queryUserCacheStatus"},method={RequestMethod.POST})  
     public ReturnValue queryUserCacheStatus(HttpServletRequest request,@RequestBody String jsonParam)throws Exception{
 		ReturnValue rv = new ReturnValue();
 		HttpSession session = request.getSession();
 		User user=new Gson().fromJson(jsonParam,User.class);
 		try {
 			String login_name = user.getLoginName();
 			String respStatus = userService.queryUserCacheStatus(login_name);
 			if(respStatus == null || respStatus == ""){
 					rv.setSuccess(false);
 					rv.setDatas(2);
 	 				rv.setMessage("该用户名不存在，请输入正确的用户名!");
 	 				return rv;
 			}else if(Integer.parseInt(respStatus) == 0){
 					rv.setSuccess(false);
 					rv.setDatas(0);
 					rv.setMessage("未清理本台电脑的缓存,不清空缓存可能导致部分功能异常，请您尽快清空浏览器缓存，再尝试登录!");
	 				return rv;
 			}else if(Integer.parseInt(respStatus) == 1){
	 				rv.setSuccess(true);
	 				rv.setDatas(1);
	 	 			rv.setMessage("获得用户已清除浏览器缓存状态，容许登录!");
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			rv.setSuccess(false);
 			rv.setMessage(e.getMessage());
 		}
 		return rv;
     }
    /**
     * 登录前：提示用户升级后清空缓存 ,若用户选择“已清空”，则修改t_wg_user表中cache_status=1
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
     @ResponseBody()
     @RequestMapping(value={"/updateUserCacheStatus"},method={RequestMethod.POST})  
     public ReturnValue updateUserCacheClearStatus(HttpServletRequest request,@RequestBody String jsonParam)throws Exception{
 		ReturnValue rv = new ReturnValue();
 		HttpSession session = request.getSession();
 		User user=new Gson().fromJson(jsonParam,User.class);
 		try {
 			String login_name = user.getLoginName();
			userService.updateUserCacheClearStatus(login_name,1);
			rv.setSuccess(true);
 			rv.setMessage("用户清除浏览器缓存状态更新成功!");
 		} catch (Exception e) {
 			e.printStackTrace();
 			rv.setSuccess(false);
 			rv.setMessage(e.getMessage());
 		}
 		return rv;
     }
     
     /**
      * 获取是否提示要修改密码 
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
      @RequestMapping(value={"/getPasswordNotice"},method={RequestMethod.POST})  
      @ResponseBody
      public ReturnValue getPasswordNotice(HttpServletRequest request,HttpServletResponse response)throws Exception{
      	ReturnValue rv=new ReturnValue();
      	try{
      	  HttpSession session=request.getSession(false);    	
      	  if(session!=null){
      		Object userId=session.getAttribute(SystemConst.ACCOUNT_ID);
      		if(userId!=null){
      			int dayCount=userService.getPasswordNoticeDay(userId.toString());
      			if(dayCount>=60){
      				rv.setSuccess(true);
      			}else{
      				rv.setSuccess(false);    
      			}
      		}else{
      			rv.setSuccess(false);    		
      		}
      	 }
         }catch(Exception ex){
      	   logger.error(ex.getMessage());
      	   rv.setSuccess(false);     	   
         }    	
         return rv;
      }
}
