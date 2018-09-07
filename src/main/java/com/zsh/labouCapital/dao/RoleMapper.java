package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zsh.labouCapital.entity.Role;

public interface RoleMapper extends BaseSqlMapper<Role> {

	long queryRolesByName(String description);

	void updateRole(Role role);

	ArrayList<Role> queryRolePage(HashMap<String, Object> params);

	long queryRolePageCount(HashMap<String, Object> params);

	Role queryRoleByUid(Integer userid);

	ArrayList<HashMap<String, Object>> queryChangeRoleLevel(Role nowRole);

	ArrayList<Role> queryChangeRole(HashMap<String, Object> params);

	ArrayList<Role> queryChangeRoleByPath(HashMap<String, Object> params);

	int roleIsBindUser(Integer roleid);

	List<Role> queryChangeRolesByUid(Integer userid);

	int queryExceptRoles(Role tWgRole);

}
