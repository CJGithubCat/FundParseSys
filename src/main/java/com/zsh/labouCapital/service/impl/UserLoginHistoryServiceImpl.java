package com.zsh.labouCapital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.UserLoginHistoryMapper;
import com.zsh.labouCapital.pojo.UserLoginHistory;
import com.zsh.labouCapital.service.IUserLoginHistoryService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.Pager;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;
/**
 * 用户登录，退出日志
 * @author gongyu
 *
 */
@Service("loginHistoryService")
public class UserLoginHistoryServiceImpl implements IUserLoginHistoryService{
	@Autowired
	private UserLoginHistoryMapper userLoginHistoryDao;
   /**
    * 添加一条日志记录
    */
	@Override
	@Transactional
	public void addUserLoginHistory(UserLoginHistory loginHistory) throws Exception {
		userLoginHistoryDao.addUserLoginHistory(loginHistory);
		
	}
   /**
    * 分页查询用户登退录findUserLoginHistory
    */
	@Override
	public Map<String,Object> findUserLoginHistory(UserLoginHistory loginHistory,Pager pager) throws Exception {
		RowBounds rb=new RowBounds(pager.getStartRow(),pager.getCurSize());
		List<UserLoginHistory> loginHistoroyList= userLoginHistoryDao.findUserLoginHistory(loginHistory, rb);
		Integer count= userLoginHistoryDao.findUserLoginHistoryCount(loginHistory);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", loginHistoroyList);
		map.put("count", count);
		return map;
	}
    @Override
    public void exportUserLoginHistory(UserLoginHistory loginHistory, HttpServletResponse response) throws Exception {
    	List<UserLoginHistory> loginHistoryList=userLoginHistoryDao.exportUserLoginHistory(loginHistory);
    	for(int i=0;i<loginHistoryList.size();i++){
    		if(loginHistoryList.get(i).getLoginType()==1){
    			loginHistoryList.get(i).setLoginTypeStr("登录");
    		}else if(loginHistoryList.get(i).getLoginType()==2){
    			loginHistoryList.get(i).setLoginTypeStr("退出");
    		}
    	}
		IExcelConfig excelConfig=ExcelConfigJson.getInstance();
		ExcelConfigVo excelConfigVo=excelConfig.getExcelConfigById("log_loginhistory_info_excel");
		ExcelExport export=new ExcelExport();
		export.exportExcelFromBeanList(response,excelConfigVo.getFile_name(),
				excelConfigVo.getShows(),excelConfigVo.getFields(),
				excelConfigVo.getCell_type(),loginHistoryList);	
   }
	
   
}
