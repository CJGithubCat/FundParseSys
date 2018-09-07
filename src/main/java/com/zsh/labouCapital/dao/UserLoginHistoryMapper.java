package com.zsh.labouCapital.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.pojo.UserLoginHistory;

/**
 * 用户登退录日志
 * @author gongyu
 *
 */
public interface UserLoginHistoryMapper {
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
	public List<UserLoginHistory> findUserLoginHistory(UserLoginHistory loginHistory,RowBounds rb)throws Exception;
	public Integer findUserLoginHistoryCount(UserLoginHistory loginHistory)throws Exception;
   /**
    * 导出用户登退录日志
    * @param loginHistory
    * @return
    * @throws Exception
    */
	public List<UserLoginHistory> exportUserLoginHistory(UserLoginHistory loginHistory)throws Exception;
}
