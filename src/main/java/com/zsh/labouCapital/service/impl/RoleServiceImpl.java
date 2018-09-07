package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.RoleMapper;
import com.zsh.labouCapital.entity.Role;
import com.zsh.labouCapital.service.IRoleService;
import com.zsh.labouCapital.util.pagination.PageBean;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public long queryRolesByName(String description) {
		long count = -1;
		try {
			count = roleMapper.queryRolesByName(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Transactional
	@Override
	public void updateRole(Role role) {
		try {
			roleMapper.updateRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	@Override
	public void delteRoles(Role role) {
		try {
			roleMapper.remove(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public HashMap<String, Object> queryRolesPage(Role role, PageBean pageBean, Integer uid) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<Role> list = new ArrayList<Role>();
		long count;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", role.getRoleName());
		params.put("userid", uid);
		if (pageBean.getPage() == 1) {
			params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
		} else {
			params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
		}
		params.put("limit", pageBean.getPagesize() * pageBean.getPage());
		try {
			list = roleMapper.queryRolePage(params);
			count = roleMapper.queryRolePageCount(params);
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	@Override
	public Role queryRoleByUid(Integer userid) {
		Role role = null;
		try {
			role = roleMapper.queryRoleByUid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public int roleIsBindUser(Integer roleid) {
		int count = 0;
		try {
			count = roleMapper.roleIsBindUser(roleid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Role> queryChangeRolesByUid(Integer userid) {
		List<Role> list = null;
		try {
			list = roleMapper.queryChangeRolesByUid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int queryExceptRoles(Role role) {
		int count = 0;
		try {
			count = roleMapper.queryExceptRoles(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
