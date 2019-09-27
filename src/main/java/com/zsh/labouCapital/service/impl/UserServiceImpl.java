package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zsh.labouCapital.dao.client.TUserMapper;
import com.zsh.labouCapital.dao.dto.TUser;
import com.zsh.labouCapital.dao.dto.TUserCriteria;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {
    
	@Autowired
	private TUserMapper tuserMapper;

	@Override
	public int validatePwd(User user) throws Exception {
		TUserCriteria cr = new TUserCriteria();
		TUserCriteria.Criteria criteria = cr.createCriteria();
		criteria.andLoginNameEqualTo(user.getLoginName());
		criteria.andLoginPasswordEqualTo(user.getLoginPassword());
		
		List<TUser> userList = tuserMapper.selectByExample(cr);
		
		TUser findedUser = null;
	    int responseCode = -1;
		if(CollectionUtils.isEmpty(userList)){
		    responseCode = 3;
		}
		
		//状态校验
		findedUser = userList.get(0);
		if (findedUser == null) {// 用户不存在
            responseCode = 3;
        } else {
            if (findedUser.getStatus() == 0) {// 登录成功
                responseCode = 0;
            } else if (findedUser.getStatus() == 1) {// 账号暂停中
                responseCode = 1;
            } else if (findedUser.getStatus() == 2) {//账号已经删除
                responseCode = 2;
            } else {// 密码错误
                responseCode = -1;
            }
        }
		
		return responseCode;
	}

	@Override
	public TUser getUserByLoginName(String loginName) {
		TUser reuser = new TUser();
		TUserCriteria cr = new TUserCriteria();
		TUserCriteria.Criteria criteria = cr.createCriteria();
		criteria.andLoginNameEqualTo(loginName);
		List<TUser> userList = tuserMapper.selectByExample(cr);
		if(CollectionUtils.isNotEmpty(userList)){
			reuser = userList.get(0);
		}
		return reuser;
	}

}
