package com.zsh.labouCapital.dao;

import java.util.List;

import com.zsh.labouCapital.entity.TExceptFund;

public interface ExceptFundMapper extends BaseSqlMapper<TExceptFund> {

	List<TExceptFund> queryExceptFundInfo(TExceptFund netWorthHistory);

	void insertExceptFund(TExceptFund tExceptFund);

}
