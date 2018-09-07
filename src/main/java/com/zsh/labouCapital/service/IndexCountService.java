package com.zsh.labouCapital.service;

/**
 * @author
 */
public interface IndexCountService {
	/**
	* @Name: queryJyzSalaryNoticeCount  
	* @Description:统计加油站工资单相关状态的数量  
	* @param @param companyPath
	* @param @param status
	* @param @return    参数  
	* @return int    返回类型  
	* @throws
	 */
	public int queryJyzSalaryNoticeCount(String companyPath,String status);
	
	/**
	* @Name: queryXgsSalaryNoticeCount  
	* @Description:统计县公司工资单相关状态的数量  
	* @param @param companyPath
	* @param @param status
	* @param @return    参数  
	* @return int    返回类型  
	* @throws
	 */
	public int queryXgsSalaryNoticeCount(String companyPath,String status);
	/**
	* @Name: queryJyzExamNoticeCount  
	* @Description:查询加油站考核通知单相关状态数量  
	* @param @param companyPath
	* @param @param status
	* @param @return    参数  
	* @return int    返回类型  
	* @throws
	 */
	public int queryJyzExamNoticeCount(String companyPath,String status);
	/**
	* @Name: queryXgsExamNoticeCount  
	* @Description:查询县公司考核通知单相关状态数量  
	* @param @param companyPath
	* @param @param status
	* @param @return    参数  
	* @return int    返回类型  
	* @throws
	 */
	public int queryXgsExamNoticeCount(String companyPath,String status);
}
