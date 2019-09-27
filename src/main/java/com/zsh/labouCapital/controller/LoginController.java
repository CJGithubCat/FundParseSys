package com.zsh.labouCapital.controller;

import java.util.Enumeration;

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
import com.zsh.labouCapital.dao.dto.TUser;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.IUserService;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private IUserService userService;
	
    /**
     * @Title: validatePwd   
     * @Description: 登录验证   
     * @param: @param request
     * @param: @param jsonParam
     * @param: @return
     * @param: @throws Exception      
     * @return: ReturnValue      
     * @throws
     */
	@SuppressWarnings("unchecked")
	@ResponseBody()
    @RequestMapping(value={"/validatepwd"},method={RequestMethod.POST})
	public ReturnValue validatePwd(HttpServletRequest request,@RequestBody String jsonParam){
	    ReturnValue responseData=new ReturnValue();
	    
	    HttpSession session = request.getSession(false);
		User user=new Gson().fromJson(jsonParam,User.class);
		 //用户名，密码验证
    	int code;
        try {
            code = userService.validatePwd(user);
            if(code==3){//用户不存在
                responseData.setErrorCode(SysCodeEnum.RE_LOGIN_USER_NOFOUND.getCode());
                responseData.setMessage(SysCodeEnum.RE_LOGIN_USER_NOFOUND.getDesc());
             }else{
                 if(code==0){
                    //获取用户信息
                    TUser tuser = userService.getUserByLoginName(user.getLoginName());
                    session.setAttribute(SystemConst.ACCOUNT_ID, tuser.getUserId());
                    session.setAttribute(SystemConst.ACCOUNT_LOGINNAME, tuser.getLoginName());
                    session.setAttribute(SystemConst.ACCOUNT_USERNAME, tuser.getUserName());
                    
                    responseData.setErrorCode(SysCodeEnum.RE_LOGIN_SUCESS.getCode());
                    responseData.setMessage(SysCodeEnum.RE_LOGIN_SUCESS.getDesc());
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
        }
        catch (Exception e) {
            e.printStackTrace();
            responseData.setErrorCode(SysCodeEnum.RE_SYS_ERROR.getCode());
            responseData.setMessage(SysCodeEnum.RE_SYS_ERROR.getDesc());
        }
        
   	   return responseData;   	
    }

   
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
}
   /**
    * 首页获得用户名 
    * @param request
    * @param response
    * @return
    * @throws Exception
    *//*
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
    *//**
     * 登录前：查询t_wg_user表中cache_status的状态 0：浏览器缓存未清空，1：浏览器缓存已清空
     * @param request
     * @param response
     * @return
     * @throws Exception
     *//*
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
    *//**
     * 登录前：提示用户升级后清空缓存 ,若用户选择“已清空”，则修改t_wg_user表中cache_status=1
     * @param request
     * @param response
     * @return
     * @throws Exception
     *//*
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
     
     *//**
      * 获取是否提示要修改密码 
      * @param request
      * @param response
      * @return
      * @throws Exception
      *//*
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
}*/
