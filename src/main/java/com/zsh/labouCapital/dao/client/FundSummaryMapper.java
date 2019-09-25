package com.zsh.labouCapital.dao.client;

import java.util.List;

import com.zsh.labouCapital.entity.FundSummary;

public interface FundSummaryMapper  {
	FundSummary getFundSummaryByCode(String fundCode);

	List<FundSummary> queryAllFundSummary();

	List<FundSummary> queryFundSummaryNoHsitory();

    /**   
     * @Title: queryFundSummaryByEample   
     * @Description: TODO   
     * @param: @param examPle
     * @param: @return      
     * @return: List<FundSummary>      
     * @throws   
     */
    List<FundSummary> queryFundSummaryByEample(FundSummary examPle);
	
}
