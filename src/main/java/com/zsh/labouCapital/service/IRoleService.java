package com.zsh.labouCapital.service;

import java.util.HashMap;
import java.util.List;

import com.zsh.labouCapital.entity.Role;
import com.zsh.labouCapital.util.pagination.PageBean;

public interface IRoleService extends BaseService<Role>{
	//函数功能：根据角色名查找角色数;
	public long queryRolesByName(String description);
	//函数功能：更新角色信息;
	public void updateRole(Role role);
	//函数功能：删除角色信息;
	public void delteRoles(Role role);
	//函数功能：根据用户id查询角色;
	public Role queryRoleByUid(Integer userid);
	//函数功能：查询是否有用户绑定相应的角色;
	public int roleIsBindUser(Integer integer);
	public List<Role> queryChangeRolesByUid(Integer userid);
	//查询本角色之外同名的角色的数量
	public int queryExceptRoles(Role role);
	public HashMap<String, Object> queryRolesPage(Role role, PageBean pageBean, Integer uid);
	
}
