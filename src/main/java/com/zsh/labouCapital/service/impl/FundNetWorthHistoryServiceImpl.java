package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.dao.client.TNetWorthHistoryMapper;
import com.zsh.labouCapital.dao.dto.TNetWorthHistory;
import com.zsh.labouCapital.dao.dto.TNetWorthHistoryCriteria;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.service.IFundNetWorthHistoryService;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.PojoConvertUtil;

@Service
public class FundNetWorthHistoryServiceImpl implements IFundNetWorthHistoryService {
	@Autowired
	private TNetWorthHistoryMapper fundNetWorthHistoryMapper;

	@Override
	public List<FundNetWorthDto> queryNetWorthHistoryList(FundNetWorthHistoryRequest fundRequest) {
		TNetWorthHistoryCriteria cr = geTNetWorthHistoryCriteria(fundRequest);
		List<TNetWorthHistory> historyList = fundNetWorthHistoryMapper.selectByExample(cr);
		List<FundNetWorthDto> hisNetDtoList = null;
		if(CollectionUtils.isNotEmpty(historyList)){
			hisNetDtoList = PojoConvertUtil.convert(historyList, FundNetWorthDto.class);
			for (FundNetWorthDto fundNetWortDto : hisNetDtoList) {
				if(fundNetWortDto.getDateInfo() != null){
					fundNetWortDto.setDateInfoStr(DateTimeUtil.formatDate(fundNetWortDto.getDateInfo(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
				}
			}
		}
		
		return hisNetDtoList;
	}
	
	@Override
	public int queryNetWorthHistoryCount(FundNetWorthHistoryRequest fundRequest) {
		TNetWorthHistoryCriteria cr = geTNetWorthHistoryCriteria(fundRequest);
		return fundNetWorthHistoryMapper.countByExample(cr);
	}
	
	public TNetWorthHistoryCriteria geTNetWorthHistoryCriteria(FundNetWorthHistoryRequest fundRequest){
		TNetWorthHistoryCriteria cr = new TNetWorthHistoryCriteria();
		TNetWorthHistoryCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		if(!StringUtils.isEmpty(fundRequest.getStartDate())){
			criteria.andDateInfoGreaterThanOrEqualTo(DateTimeUtil.parseDate(fundRequest.getStartDate(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN_S));
		}
		if(!StringUtils.isEmpty(fundRequest.getEndDate())){
			criteria.andDateInfoLessThanOrEqualTo(DateTimeUtil.parseDate(fundRequest.getEndDate(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN_S));
		}
		cr.setLimit(fundRequest.getPageSize());
		cr.setOffset(fundRequest.getOffSet());
		return cr;
	}


	

	
}
