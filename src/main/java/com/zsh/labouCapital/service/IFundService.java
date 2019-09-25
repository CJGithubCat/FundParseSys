package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.entity.FundSummary;

public interface IFundService {
	public List<TFund> queryFundList(FundRequest fundRequest);

	public int queryFundCount(FundRequest fundRequest);
    
}
