package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.zsh.labouCapital.dao.UserDao;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.IUserService;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.Pager;
import com.zsh.labouCapital.vo.UserManageView;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
// 先不声明为原型类型，当有状态添加的时候再声明
// @Scope("prototype")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Autowired
	private UserDao userDao;

	/**
	 * 验证用户密码 responseCode ,需要配置在系统常量类（SystemConst）里边
	 */
	@Override
	public int validatePwd(User user) throws Exception {
		User findedUser = userDao.getUserByLoginName(user.getLoginName());
		int responseCode = -1;
		try {
			if (findedUser == null) {// 用户不存在
				responseCode = 3;
			} else {
				int passwordOutDay = Integer.parseInt(StringUtils.isEmpty(findedUser.getPasswordOutDay())?"91":findedUser.getPasswordOutDay());
				if (passwordOutDay > 90) {
					responseCode = 6;// 密码超时未修改
				} else if (user.getLoginPassword().equals(findedUser.getLoginPassword())
						&& findedUser.getStatus() == 0) {// 登录成功
					responseCode = 0;
				} else if (user.getLoginPassword().equals(findedUser.getLoginPassword())
						&& findedUser.getStatus() == 1) {// 账号暂停中
					responseCode = 1;
				} else if (user.getLoginPassword().equals(findedUser.getLoginPassword())
						&& findedUser.getStatus() == 2) {// 站好已经删除
					responseCode = 2;
				} else {// 密码错误
					responseCode = -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}

	/**
	 * 根据登录名查找用户对象
	 */
	@Override
	public User getUserByLoginName(String login_name) throws Exception {
		return userDao.getUserByLoginName(login_name);
	}

	@Override
	public List<User> queryUserByAgencyId(Integer agencyId) throws Exception {
		return userDao.queryUserByAgencyId(agencyId);
	}

	/**
	 * 根据用户机构路径和用户登录名模糊查询 用户信息
	 * 
	 * @param userManageView
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> findUserPage(UserManageView userManageView, Pager pager) throws Exception {
		// mybatis 分页对象
		RowBounds rb = new RowBounds(pager.getStartRow(), pager.getCurSize());
		List<User> userList = userDao.findUserPage(userManageView, rb);
		Integer count = userDao.findUserCount(userManageView);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", userList);
		map.put("count", count);
		return map;
	}

	/**
	 * 用户信息报表导出
	 */
	@Override
	public void exportUserManage(Map<String, Object> params, HttpServletResponse response) throws Exception {
		try {
			List list = new ArrayList();
			list = userDao.exportUserManage(params);
			IExcelConfig excelConfig = ExcelConfigJson.getInstance();
			ExcelConfigVo excelConfigVo = excelConfig.getExcelConfigById("user_MES_info_excel");
			ExcelExport export = new ExcelExport();
			export.exportExcelFromBeanList(response, excelConfigVo.getFile_name(), excelConfigVo.getShows(),
					excelConfigVo.getFields(), excelConfigVo.getCell_type(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 登录前：提示用户升级后清空缓存,若clearStatus==1 则修改对应数据库中user表的状态
	 */
	@Override
	public void updateUserCacheClearStatus(String login_name, int cache) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("login_name", login_name);
			map.put("cache", cache);
			userDao.updateUserCacheClearStatus(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String queryUserCacheStatus(String login_name) {
		String respStatus = "";
		try {
			respStatus = userDao.queryUserCacheStatus(login_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respStatus;
	}

	/*
	 * @Override public TWgAgency getAgencyById(Integer agencyId) { TWgAgency
	 * agency =null; try { agency = userDao.getAgencyById( agencyId); } catch
	 * (Exception e) { e.printStackTrace(); } return agency; }
	 */
	@Override
	public Company getCompanyById(Integer companyId) {
		Company company = null;
		try {
			company = userDao.getCompanyById(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<User> queryUserByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPasswordNoticeDay(String userId) {
		int dayCount = 0;
		dayCount = userDao.getPasswordNoticeDay(userId);
		return dayCount;
	}

}
