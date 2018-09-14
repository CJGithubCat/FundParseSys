package com.zsh.labouCapital.dao;

import java.util.List;

import com.zsh.labouCapital.entity.FundSummary;

public interface FundSummaryMapper extends BaseSqlMapper<FundSummary> {
	FundSummary getFundSummaryByCode(String fundCode);

	List<FundSummary> queryAllFundSummary();
	
}
