package com.zsh.labouCapital.service;

import java.util.HashMap;
import java.util.List;

import com.zsh.labouCapital.pojo.TWgRoles;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface ITWgRolesService extends BaseService<TWgRoles>{
	//函数功能：根据角色名查找角色数;
	public long queryRolesByName(String description);
	//函数功能：更新角色信息;
	public void updateRoles(TWgRoles tWgRoles);
	//函数功能：删除角色信息;
	public void delteRoles(TWgRoles tWgRoles);
	//函数功能：根据用户id查询角色;
	public TWgRoles queryRoleByUid(Integer userid);
	//函数功能：查询是否有用户绑定相应的角色;
	public int roleIsBindUser(Integer integer);
	public List<TWgRoles> queryChangeRolesByUid(Integer userid);
	//查询本角色之外同名的角色的数量
	public int queryExceptRoles(TWgRoles tWgRoles);
	public HashMap<String, Object> queryRolesPage(TWgRoles tWgRoles, PageBean pageBean, Integer uid);
	
}
