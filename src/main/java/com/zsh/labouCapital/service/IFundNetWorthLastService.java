package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.controller.request.FundNetWorthLastRequest;
import com.zsh.labouCapital.dao.dto.TNetWorthLast;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.dto.FundNetWorthLastDto;


public interface IFundNetWorthLastService{
	public List<FundNetWorthLastDto> queryNetWorthLastList(FundNetWorthLastRequest fundRequest);

	public int queryNetWorthLastCount(FundNetWorthLastRequest fundRequest);

	public FundNetWorthLastDto parseNewestGuZhiInfo(FundNetWorthLastRequest fundRequest) throws Exception;
}
