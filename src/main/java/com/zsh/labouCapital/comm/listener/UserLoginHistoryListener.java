/*package com.zsh.labouCapital.comm.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.controller.LoginController;
import com.zsh.labouCapital.pojo.IpMappedAddress;
import com.zsh.labouCapital.pojo.UserLoginHistory;
import com.zsh.labouCapital.service.IUserLoginHistoryService;
import com.zsh.labouCapital.util.ipaddress.FindIpAddress;
import com.zsh.labouCapital.util.ipaddress.IpToAddress;

*//**
 * 
 * @author gongyu
 *
 *//*
public class UserLoginHistoryListener implements HttpSessionBindingListener{
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private String interfaceUrl="http://ip.taobao.com/service/getIpInfo.php";
	private String encode="UTF-8";
	//String requestIp="116.25.161.196";
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		 HttpSession currentSession=event.getSession();
		 short loginType=1;
		 try{
		     addloginHistory(currentSession,loginType);
		 }catch(Exception ex){
			 ex.printStackTrace();
			 logger.error(ex.getMessage());
		 }		
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {		
		HttpSession currentSession=event.getSession();
		short loginType=2;
		try{
		    addloginHistory(currentSession,loginType);
		}catch(IllegalStateException isexeption){
			logger.info("java.lang.IllegalStateException: getAttribute: Session already invalidated");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}
	public void addloginHistory(HttpSession currentSession,short loginType){
        String requestIp=currentSession.getAttribute(SystemConst.ATTRIBUTE_LOGINIP)==null?null:currentSession.getAttribute(SystemConst.ATTRIBUTE_LOGINIP).toString();	
		UserLoginHistory loginHistory=new UserLoginHistory();
		loginHistory.setUserId(
				Long.parseLong(currentSession.getAttribute(SystemConst.ACCOUNT_ID)
						.toString()));
		loginHistory.setIp(requestIp);
		loginHistory.setLoginType(loginType);
		
		String realAddress=null;
		if(requestIp!=null){
		   try{
		      IpToAddress ipToAddr=new FindIpAddress();
		      IpMappedAddress address=ipToAddr.getAddress(interfaceUrl, requestIp, encode);
		      if(address!=null){
		    	  if(address.getRegion()==null||"".equals(address.getRegion())){
		    		  realAddress=null;  
		    	  }else{
		    		  if(address.getRegion().equals(address.getCity())){
				    	 realAddress=address.getCity();
				      }else{
				         realAddress=address.getRegion()+address.getCity();
				      }  
		    	  }		        
		      }
		   }catch(Exception ex){
			   logger.error(ex.getMessage());
		   }
		}
		loginHistory.setReferenceposition(realAddress);
		try{
			ServletContext servletContext=currentSession.getServletContext();
			ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			IUserLoginHistoryService  loginHistoryService=applicationContext.getBean("loginHistoryService",IUserLoginHistoryService.class);
			loginHistoryService.addUserLoginHistory(loginHistory);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

}
*/