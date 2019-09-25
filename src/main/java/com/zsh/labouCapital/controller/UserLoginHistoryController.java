/*package com.zsh.labouCapital.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.pojo.TWgOperationRecords;
import com.zsh.labouCapital.pojo.UserLoginHistory;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.IUserLoginHistoryService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.Pager;
*//**
 * 用户登录退出日志查询
 * @author gongyu
 *
 *//*
@Controller
public class UserLoginHistoryController {
	private static Logger logger = LoggerFactory.getLogger(UserLoginHistoryController.class);
	@Autowired
	private IUserLoginHistoryService loginHistoryService;
	@Autowired
    private ILoggerService iloggerService;
	@RequestMapping(value={"/test"},method={RequestMethod.POST})    
    public void login(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try{
			loginHistoryService.findUserLoginHistory(new UserLoginHistory(), new Pager());
		}catch(Exception ex){
			ex.printStackTrace();
		}
    	HttpSession session=request.getSession(false);
    	session.invalidate();
    	String test="test";
    	System.out.println(test);
    	  	    
    }
   *//**
    * 日志查询，登录，退出 	
    * @param loginHistory
    * @param page
    * @param pagesize
    *//*
	@RequestMapping(value={"/log/finduserloginhistory"},method={RequestMethod.POST}) 
	@ResponseBody
    public ReturnValue findUserLoginHistory(UserLoginHistory loginHistory,
    		HttpServletRequest request,
    		@RequestParam(value="page",required=false) String page,
			@RequestParam(value="pagesize",required=false) String pagesize){
		ReturnValue rspData=new ReturnValue();
		Pager pager=new Pager(Integer.parseInt(page), Integer.parseInt(pagesize));
		try{
			Map<String,Object> userMap=loginHistoryService.findUserLoginHistory(loginHistory,pager);
			rspData.setDatas(userMap);
			rspData.setSuccess(true);
			rspData.setMessage(SystemConst.MESSAGE_OPSUCCESS);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			rspData.setSuccess(false);
			rspData.setMessage(SystemConst.MESSAGE_OPFAILURE);
		} 
		return rspData;
    }
   *//**
    * 日志查询，导出登录退出日志 	
    * @param loginHistory
    * @param response
    *//*
	@RequestMapping(value = "/log/exportuserloginhistory",method = RequestMethod.POST)
	@ResponseBody
	public void exportloguserloginhistory(UserLoginHistory loginHistory,HttpServletRequest request,HttpServletResponse response){
		try {
			long startTime=System.currentTimeMillis(); //获取开始时间
			HttpSession session = request.getSession();
			loginHistoryService.exportUserLoginHistory(loginHistory, response);
			// 写入日志
			long endTime = System.currentTimeMillis(); // 获取结束时间
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String username = session.getAttribute("username").toString();
			String loingname = session.getAttribute("loingname").toString();
			String ip = RequestRealIp.getIpAddress(request);
			long responsetime = endTime - startTime;
			//iloggerService.writeLog(userid, loingname, username, 10502, "导出用户登退录统计", null, null, ip, responsetime);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
	}
}
*/