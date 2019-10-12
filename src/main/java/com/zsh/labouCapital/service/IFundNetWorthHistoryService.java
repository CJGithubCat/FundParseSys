package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.dao.dto.TNetWorthHistory;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.dto.JobDto;


public interface IFundNetWorthHistoryService{
	public List<FundNetWorthDto> queryNetWorthHistoryList(FundNetWorthHistoryRequest fundRequest);

	public int queryNetWorthHistoryCount(FundNetWorthHistoryRequest fundRequest);

    /**   
     * @Title: parseFundNetWorthHistoryInfo   
     * @Description: TODO   
     * @param: @param jobDto      
     * @return: void      
     * @throws   
     */
    public void parseFundNetWorthHistoryInfo(JobDto jobDto);
}
