package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.dao.client.TFundMapper;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dao.dto.TFundCriteria;
import com.zsh.labouCapital.service.IFundService;

@Service
public class FundServiceImpl implements IFundService {
	@Autowired
	private TFundMapper fundMapper;

	@Override
	public List<TFund> queryFundList(FundRequest fundRequest) {
		TFundCriteria cr = this.getCriteria(fundRequest);
		List<TFund> fundList = fundMapper.selectByExample(cr);
		return fundList;
	}

	@Override
	public int queryFundCount(FundRequest fundRequest) {
		TFundCriteria cr = this.getCriteria(fundRequest);
		return fundMapper.countByExample(cr);
	}
	
	public TFundCriteria getCriteria(FundRequest fundRequest){
		TFundCriteria cr = new TFundCriteria();
		TFundCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		if(!StringUtils.isEmpty(fundRequest.getFundName())){
			criteria.andFundNameLike(fundRequest.getFundName()+"%");
		}
		cr.setOffset(fundRequest.getOffSet());
		cr.setLimit(fundRequest.getPageSize());
		return cr;
	}
}
