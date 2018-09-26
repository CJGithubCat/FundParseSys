package com.zsh.labouCapital.dao;

import java.util.List;

import com.zsh.labouCapital.entity.ExpectFund;

public interface ExpectFundMapper extends BaseSqlMapper<ExpectFund> {

    List<ExpectFund> queryExceptFundList(ExpectFund expectFund);

	void insertExceptFund(ExpectFund tExceptFund);

}
