package com.zsh.labouCapital.service;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.util.pagination.Pager;
import com.zsh.labouCapital.vo.UserManageView;

public interface IUserService extends BaseService<User>{
   /**
    * 验证用户名密码
    * @param user
    * @return
    */
   public int validatePwd(User user)throws Exception;
   /**
    * 根据登录名获得用户信息
    * @param loginName
    * @return
    */
   public User getUserByLoginName(String loginName)throws Exception;
   
   public List<User> queryUserByAgencyId(Integer agencyId) throws Exception;
   /**
    * 根据用户机构路径和用户登录名模糊查询  用户信息
    * @param userManageView
    * @return
    * @throws Exception
    */
    public Map<String,Object> findUserPage(UserManageView userManageView,Pager pager) throws Exception;
    
    public void exportUserManage(Map<String, Object> params,HttpServletResponse response) throws Exception;
    /*
	 * 登录前：查询t_wg_user表中cache_status的状态 0：浏览器缓存未清空，1：浏览器缓存已清空
	 */
	public String queryUserCacheStatus(String login_name);
	/*
	 * 登录前：提示用户升级后清空缓存,若clearStatus==1 则修改对应数据库中user表的状态
	 */
    public void updateUserCacheClearStatus(String login_name, int cache);
    
    public Company getCompanyById(Integer companyId);
	public List<User> queryUserByCompanyId(Integer companyId);
	
	public int getPasswordNoticeDay(String userId);
    
}
