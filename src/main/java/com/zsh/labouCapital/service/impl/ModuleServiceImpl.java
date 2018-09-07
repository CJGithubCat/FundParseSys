package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.bean.TreeNode;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.dao.ModuleMapper;
import com.zsh.labouCapital.entity.Module;
import com.zsh.labouCapital.service.IModuleService;
import com.zsh.labouCapital.vo.DistPermissionVo;

@Service("moduleServiceImpl")
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements IModuleService {
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public HashMap<String, Object> queryModulesByUid(Integer userid) {
		ArrayList<Module> list = null;
		ArrayList<Module> list1 = null;
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		try {
			// 1.查询主菜单类权限;
			params.put("userid", userid);
			params.put("categoryid", 1);
			list = moduleMapper.queryModulesByUid(params);
			hm.put(SystemConst.MAIN_MENU_ITEMS, list);
			// 2.查询指令权限;
			params.put("categoryid", 3);
			list1 = moduleMapper.queryModulesByUid(params);
			hm.put(SystemConst.COMMOND_ITEMS, list1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

	@Override
	public ArrayList<TreeNode> queryModulesByRoleid(Integer nowUserid, Integer roleid) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ArrayList<TreeNode> list3 = new ArrayList<TreeNode>();
		try {
			// 1.查询当前用户拥有的所有的权限;
			hm = queryModulesByUid(nowUserid);
			ArrayList<Module> list = (ArrayList<Module>) hm.get(SystemConst.MAIN_MENU_ITEMS);
			ArrayList<Module> list1 = (ArrayList<Module>) hm.get(SystemConst.COMMOND_ITEMS);
			// 2.组装对应的TreeNode
			TreeNode treeNode = null;

			for (Module module : list) {
				treeNode = new TreeNode();
				treeNode.setId(module.getModuleId());// id
				treeNode.setParentNodeId(module.getParentid());// parentId
				treeNode.setText(module.getDescription());// description
				list3.add(treeNode);
			}
			for (Module module : list1) {
				treeNode = new TreeNode();
				treeNode.setId(module.getModuleId());// id
				treeNode.setParentNodeId(module.getParentid());// parentId
				treeNode.setText(module.getDescription());// description
				list3.add(treeNode);
			}

			// 3.查询已经分配的角色拥有哪些权限；【对应树中的状态是‘checked’】
			ArrayList<Module> cehecList = moduleMapper.queryModulesByRoleid(roleid);
			for (TreeNode treeNode2 : list3) {
				if (treeNode2.getParentNodeId().intValue() == 0 || treeNode2.getId() == 10601
						|| treeNode2.getId() == 10602 || treeNode2.getId() == 10401 || treeNode2.getId() == 10402
						|| treeNode2.getId() == 10502 || treeNode2.getId() == 10503|| treeNode2.getId() == 10400) {
					treeNode2.setIsRoot(true);
					treeNode2.setIsLeaf(false);
				} else {
					treeNode2.setIsRoot(false);
					treeNode2.setIsLeaf(true);
				}
				for (Module module : cehecList) {
					int pid = module.getModuleId().intValue();
					int nid = treeNode2.getId().intValue();
					if (pid == nid) {
						treeNode2.setIschecked(true);
					}
				}
			}
			// 父节点也是选中状态
			for (TreeNode treeNode2 : list3) {
				for (TreeNode treeNode3 : list3) {
					if (treeNode2.getIschecked() == true) {
						if (treeNode2.getParentNodeId().intValue() == treeNode3.getId().intValue()) {
							treeNode3.setIschecked(true);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list3;
	}

	@Override
	@Transactional
	public boolean distRoleModules(DistPermissionVo rolePermiss) {
		boolean flag = false;
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			String roleidStr = rolePermiss.getRoleid();
			Integer roleid = Integer.parseInt(roleidStr);
			Integer pid = null;
			String permissionids = rolePermiss.getPermissionids();
			String[] arr = permissionids.split(",");
			// 1.删除之前为该角色分配的虽有权限;
			moduleMapper.deleteModulesByRoleid(roleid);
			// 2.添加新的权限;
			for (int i = 1; i < arr.length; i++) {
				System.out.println("  i:" + arr[i]);
				if (arr[i] != null) {
					pid = Integer.parseInt(arr[i]);
					params.put("roleid", roleid);
					params.put("permissionid", pid);
					moduleMapper.addRoleModules(params);
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 函数功能：根据用户id和当前模块的id，也就是子模块的父id，查询当前模块的按钮权限
	 */
	@Override
	public HashMap<String, Object> queryInfoManageModulesByUid(Integer userid, String modulesParentID) {
		ArrayList<Module> list = null;
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		try {
			// 根据父菜单的id，查询子菜单的所有按钮权限;
			params.put("userid", userid);
			params.put("permissionsParentID", modulesParentID);
			list = moduleMapper.queryInfoManageModulesByUid(params);
			hm.put("buttonItems", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
}
