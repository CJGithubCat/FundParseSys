package com.zsh.labouCapital.service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zsh.labouCapital.controller.request.FundHoldRequest;
import com.zsh.labouCapital.dao.dto.THoldFund;
import com.zsh.labouCapital.dao.dto.THoldFundSummary;

public interface IFundHoldService {

	public int queryFundHoldCount(FundHoldRequest fundRequest);

	public List<THoldFund> queryFundHoldList(FundHoldRequest fundRequest);

	public Map<String, Object> parseFundHoldList(MultipartFile summaryFile) throws IOException;

	public void updateOrAddHoldFundSummary(THoldFundSummary holdFundSummary);

	public void updateOrAddHoldFund(List<THoldFund> holdFundList);
    
}