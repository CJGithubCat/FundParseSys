package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.TExceptFund;


public interface IExceptFundService extends BaseService<TExceptFund>{
	public List<TExceptFund> queryExceptFundInfo(TExceptFund netWorthHistory);

	public void updateExceptFunds(List<TExceptFund> updateList);
	
	public void insertExceptFundInfos(List<TExceptFund> addList);

}
