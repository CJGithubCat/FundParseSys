package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zsh.labouCapital.dao.client.TUserMapper;
import com.zsh.labouCapital.dao.client.UserDao;
import com.zsh.labouCapital.dao.dto.TUser;
import com.zsh.labouCapital.dao.dto.TUserCriteria;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.IUserService;
import com.zsh.labouCapital.util.PojoConvertUtil;
import com.zsh.labouCapital.util.excel.ExcelConfigJson;
import com.zsh.labouCapital.util.excel.ExcelExport;
import com.zsh.labouCapital.util.excel.IExcelConfig;
import com.zsh.labouCapital.util.pagination.Pager;
import com.zsh.labouCapital.vo.UserManageView;
import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private TUserMapper userMapper;

	@Override
	public int validatePwd(User user) throws Exception {
		TUserCriteria cr = new TUserCriteria();
		TUserCriteria.Criteria criteria = cr.createCriteria();
		criteria.andLoginNameEqualTo(user.getLoginName());
		List<TUser> userList = userMapper.selectByExample(cr);
		TUser findedUser = CollectionUtils.isEmpty(userList)? null: userList.get(0);
		int responseCode = -1;
		try {
			if (findedUser == null) {// 用户不存在
				responseCode = 3;
			} else {
				if (user.getLoginPassword().equals(findedUser.getLoginPassword())
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

	@Override
	public TUser getUserByLoginName(String loginName) {
		TUser reuser = new TUser();
		TUserCriteria cr = new TUserCriteria();
		TUserCriteria.Criteria criteria = cr.createCriteria();
		criteria.andLoginNameEqualTo(loginName);
		List<TUser> userList = userMapper.selectByExample(cr);
		if(CollectionUtils.isNotEmpty(userList)){
			reuser = userList.get(0);
		}
		return reuser;
	}

}
