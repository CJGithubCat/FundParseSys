package com.zsh.labouCapital.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.pojo.TBaVehicle;
import com.zsh.labouCapital.pojo.TWgLastPos;


/**
 * @author Lxb
 * @Description 最后位置信息
 * @Date 2015-05-14 上午09:32
 *
 */
public interface LastPosMapper extends BaseSqlMapper<TWgLastPos>{
	
	/**
	 * 功能：根据条件查询最后位置信息，如根据机构查询
	 * @param params
	 * @return
	 */
	public List<TWgLastPos> findByParams(Map<String,Object> params);
	
	
	/**
	 * 功能：查询总记录
	 * @author zhouwei
	 * @param params
	 * @return
	 */
	public  int findBySomeGetCount(TWgLastPos top);
	/**
	 * 功能：分页查询未上报
	 * @author zhouwei
	 * @param top
	 * @param rb
	 * @return list
	 */
	public List<TWgLastPos> findTWgLastPosBySome(TWgLastPos top,RowBounds rb);
	
	public List<TWgLastPos> findAllTWgLastPos(TWgLastPos top);

	/**
	 * 功能：判断是否是总用户
	 * @author zhouwei
	 * @param customerId
	 * @return int
	 */
	public int isTBaUnit(int customerId);
	
	/**
	 * 功能：查询在线终端数量，数据权限相关
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public int countOnline(Map<String,Object> param);

	/**
	 * 功能：查询所有终端数量，数据权限相关
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public int countOffline(Map<String, Object> params);
	
	/**
	 * 功能：查询在线车辆数量，数据权限相关
	 * @param param
	 * @return
	 */
	public int countVehicleOnline(Map<String,Object> param);

	public List<HashMap<String, Object>> queryVehicleOnline(Map<String,Object> param);
	/**
	 * 功能：查询不在线车辆数量，数据权限相关
	 * @param param
	 * @return
	 */
	public int countVehicleOffline(Map<String, Object> params);
	
	public List<HashMap<String, Object>> queryVehicleOffline(Map<String,Object> param);
	/**
	 * 功能：查询在线终端详情，数据权限相关,分页
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public List<TBaVehicle> findOnLinePage(Map<String, Object> params,
			RowBounds rowBounds);
	
	/**
	 * 功能：查询在线终端详情，数据权限相关
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public List<TBaVehicle> findOnLine(Map<String, Object> params);

	/**
	 * 功能：查询不在线终端详情，数据权限相关，分页
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public List<TBaVehicle> findOffLinePage(Map<String, Object> params,
			RowBounds rowBounds);
	/**
	 * 功能：查询不在线终端详情，数据权限相关
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public List<TBaVehicle> findOffLine(Map<String, Object> params);

	/**
	 * 功能：查询区域内的车辆位置信息
	 * @author Lxb
	 * @param param
	 * @return
	 */
	public List<TWgLastPos> findVehiclePossZoom(Map<String, Object> params);
	
	/**
	 * 功能：查询首页统计车辆总数数据
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int vehicleTotalCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计在线总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int vehicleOnlineCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计离线总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int vehicleOfflineCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计当月新增总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int theMonthAddCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计当月新增上线总数
	 * @author 
	 * @param param
	 * @return
	 */
	public int theMonthAddOnlineCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计当月结清总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int theMonthJieQingCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计高风险总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int highRiskCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计逾期总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int overTimeCount(Map<String,Object> param);
	
	/**
	 * 功能：查询首页统计5天不在线总数
	 * @author Liuj
	 * @param param
	 * @return
	 */
	public int fiveDaysNotOnlineCount(Map<String,Object> param);
	
	
	public int countQueryVehicle(TBaVehicle vehicle) throws Exception;
	
	
	public List<HashMap<String, Object>> queryVehiclePage(TBaVehicle vehicle,RowBounds rowBounds)throws Exception ;
  
	//有线终端类型
	public int countQueryVehicleLine(TBaVehicle vehicle) throws Exception;
	
	public List<HashMap<String, Object>> queryVehiclePageLine(TBaVehicle vehicle,RowBounds rowBounds)throws Exception ;
   /**
	/**
    *划定一个区域，查询地图区域里边的车
    * @param map
    * @param rowBounds
    * @return
    * @throws Exception
    */
	public List<HashMap<String,Object>> queryVehicleByGspPosPage(Map<String,Object> map,RowBounds rowBounds)throws Exception ;
   /**
    * 划定一个区域，查询地图区域里边的车的数量
    * @param map
    * @return
    * @throws Exception
    */
	public int queryVehicleCountByGspPos(Map<String,Object> map)throws Exception;
	
	/**
	 * 查询权限内所有车辆最后位置
	 * @param params
	 * @return
	 */
	public List<TWgLastPos> allVehiclePosition(Map<String, Object> params);

	/**
	 * 查询权限内查询权限内所有离线指令(全80设备)
	 */
//	public List<TWgLastPos> OffLineCommandAllEighty(Map<String, Object> param);
}
