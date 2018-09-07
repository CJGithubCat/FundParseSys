package com.zsh.labouCapital.service;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.pojo.UserLoginHistory;
import com.zsh.labouCapital.util.pagination.Pager;

public interface IUserLoginHistoryService {
	 /**
      * 添加一条日志记录
      * @throws Exception
      */
  	public void addUserLoginHistory(UserLoginHistory loginHistory)throws Exception;
     /**
      * 分页查询用户登退录日志
      * @return
      * @throws Exception
      */
  	public Map<String,Object> findUserLoginHistory(UserLoginHistory loginHistory,Pager pager)throws Exception;
   /**
    * 导出用户登退录日志
    * @param loginHistory
    * @param response
    * @throws Exception
    */
  	public void exportUserLoginHistory(UserLoginHistory loginHistory,HttpServletResponse response)throws Exception;
}
