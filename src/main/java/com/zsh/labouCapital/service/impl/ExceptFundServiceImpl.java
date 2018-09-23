package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.dao.ExceptFundMapper;
import com.zsh.labouCapital.dao.FundSummaryMapper;
import com.zsh.labouCapital.dao.IndexMarketSituationMapper;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.MarketSituation;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TExceptFund;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IExceptFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;

@Service
public class ExceptFundServiceImpl extends BaseServiceImpl<TExceptFund> implements IExceptFundService {
	@Autowired
	private ExceptFundMapper exceptFundMapper;

	@Override
	public List<TExceptFund> queryExceptFundInfo(TExceptFund netWorthHistory) {
		// TODO Auto-generated method stub
		return exceptFundMapper.queryExceptFundInfo(netWorthHistory);
	}

	@Override
	public void updateExceptFunds(List<TExceptFund> updateList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void insertExceptFundInfos(List<TExceptFund> addList) {
		for (TExceptFund tExceptFund : addList) {
			exceptFundMapper.insertExceptFund(tExceptFund);
		}
	}
	
}
