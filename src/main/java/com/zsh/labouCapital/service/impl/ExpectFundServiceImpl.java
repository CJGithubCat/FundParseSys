package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.ExpectFundMapper;
import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.service.IExpectFundService;

@Service
public class ExpectFundServiceImpl extends BaseServiceImpl<ExpectFund> implements IExpectFundService {
	@Autowired
	private ExpectFundMapper exceptFundMapper;


	@Override
	public List<ExpectFund> queryExceptFundList(ExpectFund expectFund) {
		return exceptFundMapper.queryExceptFundList(expectFund);
	}

	@Override
	@Transactional
	public void insertExceptFundInfos(List<ExpectFund> addList) {
		for (ExpectFund tExceptFund : addList) {
			exceptFundMapper.insertExceptFund(tExceptFund);
		}
	}
	
}
