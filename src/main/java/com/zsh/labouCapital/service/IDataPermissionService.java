package com.zsh.labouCapital.service;

import java.util.List;
import java.util.Map;

import com.zsh.labouCapital.pojo.DataPermission;
import com.zsh.labouCapital.pojo.TBaOrgInfo;
/**
 * 
 * @author Lxb
 * @Description 用户数据权限
 * @Date 2015-05-27 下午 2:30
 */
public interface IDataPermissionService extends BaseService<DataPermission> {
	
	/**
	 * 函数名称：findDataPermission
	 * 函数功能：条件查询用户的数据权限信息
	 * @author Lxb
	 * @Date 2015-05-27 下午2:32
	 * @param param
	 * @return
	 */
	public DataPermission findDataPermission(Map<String,Object> param);
	
	
	/**
	 * 函数名称：findOrg
	 * 函数功能：条件查询机构的下级机构信息
	 * @author Lxb
	 * @Date 2015-05-28 下午4:40
	 * @param param
	 * @return
	 */
	public List<TBaOrgInfo> findOrg(Map<String,Object> param);


	/**
	 * 函数名称：findOrgList
	 * 函数功能：查询用户相关的所有机构信息
	 * @author Lxb
	 * @Date 2015-06-16 下午4:40
	 * @param params
	 * @return
	 */
	public List<TBaOrgInfo> findOrgList(Map<String, Object> params);


	/**
	 * 函数名称：adUpOrg
	 * 函数功能：新增或修改机构信息
	 * @author Lxb
	 * @Date 2015-06-27  上午11:17
	 * @param params
	 * @return int 0=成功;1=失败;2=参数有误;
	 */
	public int addUpOrg(Map<String, Object> params);

	/**
	 * 函数名称：deleteOrg
	 * 函数功能：删除机构信息
	 * @author Lxb
	 * @Date 2015-06-27  下午4:06
	 * @param params
	 * @return
	 */
	public int deleteOrg(Map<String, Object> params);


	/**
	 * 函数名称：countOrgVehicle
	 * 函数功能：统计机构车辆总数
	 * @author Lxb
	 * @Date 2015-08-21  下午5:06
	 * @param id
	 * @return
	 */
	public int countOrgVehicle(String id);


	/**
	 * 函数名称：findDataPermission2
	 * 函数功能：查询用户分组机构信息（只查询一级）
	 * @param params
	 * @return
	 */
	public DataPermission findDataPermission2(Map<String, Object> params);

    /**
     * 函数名称：countAllVehicle
	 * 函数功能：统计根用户车辆数量
     * @param parentOrgId
     * @return
     */
	public int countAllVehicle(Long parentOrgId);
}
