package com.zsh.labouCapital.dao.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.vo.UserManageView;

/**
 * 用户信息操作
 */
public interface UserDao {
   /**
    * 根据用户登录名查找用户对象
    * @param login_name
    * @return
    * @throws Exception
    */ 
    public User getUserByLoginName(String login_name)throws Exception;
   /**
    * 根据机构ID 查找用户  
    * @param agencyId
    * @return
    * @throws Exception
    */
    public List<User> queryUserByAgencyId(Integer agencyId) throws Exception;
   /**
    * (分页)根据用户机构路径和用户登录名模糊查询  用户信息
    *  com.chinagps.webgis.util.pagination.PaginationInterceptor
    *   拦截每一个数据库连接，增加了RowBounds 分页参数。
    *   实现了MyBatis物理分页
    * @param userManageView
    * @return
    * @throws Exception
    */
    public List<User> findUserPage(UserManageView userManageView,RowBounds rb) throws Exception;
   /**
    * 计算数据条数与上边 （findUserPage）分页查询配合
    * @param userManageView
    * @return
    * @throws Exception
    */
    public Integer findUserCount(UserManageView userManageView) throws Exception;
    
    public ArrayList exportUserManage(Map<String,?> params) throws Exception;
    /*
	 * 登录前：查询t_wg_user表中cache_status的状态 0：浏览器缓存未清空，1：浏览器缓存已清空
	 */
    public String queryUserCacheStatus(String login_name);
    /*
	 * 登录前：提示用户升级后清空缓存,若clearStatus==1 则修改对应数据库中user表的状态
	 */
	public void updateUserCacheClearStatus(Map<String, Object> map);
    
	public Company getCompanyById(Integer companyId);
	
	public int getPasswordNoticeDay(String userId);
}
