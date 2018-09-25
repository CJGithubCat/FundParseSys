package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.FundSummary;

public interface IFundSummaryService extends BaseService<FundSummary>{
   
	public FundSummary getFundSummaryByCode(String fundCode);

	public List<FundSummary> queryAllFundSummary();
	
	public List<FundSummary> 	queryFundSummaryNoHsitory();
    
}
