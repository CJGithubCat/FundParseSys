package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zsh.labouCapital.controller.request.FundNetWorthLastRequest;
import com.zsh.labouCapital.dao.client.TFundMapper;
import com.zsh.labouCapital.dao.client.TNetWorthLastMapper;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dao.dto.TNetWorthLast;
import com.zsh.labouCapital.dao.dto.TNetWorthLastCriteria;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.dto.FundNetWorthLastDto;
import com.zsh.labouCapital.service.IFundNetWorthLastService;
import com.zsh.labouCapital.service.handler.FundInfoAnalyseHandler;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.PojoConvertUtil;

import test.controller.FundAnalyseControllerTest;

@Service
public class FundNetWorthLastServiceImpl implements IFundNetWorthLastService {
	@Autowired
	private TNetWorthLastMapper fundNetWorthLastMapper;
	
	@Autowired
	private FundInfoAnalyseHandler fundAnalyseHandler;
	
	@Autowired
	private TFundMapper fundMapper;

	@Override
	public List<FundNetWorthLastDto> queryNetWorthLastList(FundNetWorthLastRequest fundRequest) {
		TNetWorthLastCriteria cr = geTNetWorthLastCriteria(fundRequest);
		List<TNetWorthLast> lastList = fundNetWorthLastMapper.selectByExample(cr);
		List<FundNetWorthLastDto> lastNetDtoList = null;
		if(CollectionUtils.isNotEmpty(lastList)){
			lastNetDtoList = PojoConvertUtil.convert(lastList, FundNetWorthLastDto.class);
		}
		return lastNetDtoList;
	}
	
	@Override
	public int queryNetWorthLastCount(FundNetWorthLastRequest fundRequest) {
		TNetWorthLastCriteria cr = geTNetWorthLastCriteria(fundRequest);
		return fundNetWorthLastMapper.countByExample(cr);
	}
	
	public TNetWorthLastCriteria geTNetWorthLastCriteria(FundNetWorthLastRequest fundRequest){
		TNetWorthLastCriteria cr = new TNetWorthLastCriteria();
		TNetWorthLastCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		cr.setLimit(fundRequest.getPageSize());
		cr.setOffset(fundRequest.getOffSet());
		return cr;
	}

	@Override
	public FundNetWorthLastDto parseNewestGuZhiInfo(FundNetWorthLastRequest fundRequest) throws Exception {
		//1.查询fundCode信息
		TFund fundInfo = fundMapper.selectByPrimaryKey(fundRequest.getFundCode());
		FundNetWorthDto reqDto = PojoConvertUtil.convert(fundInfo, FundNetWorthDto.class);
		TNetWorthLastCriteria cr = geTNetWorthLastCriteria(fundRequest);
		TNetWorthLast netWorthLast = fundAnalyseHandler.parseNewestGuZhiInfo(reqDto);
		if(null != netWorthLast){
			int count = fundNetWorthLastMapper.countByExample(cr);
			if(count > 0){
				fundNetWorthLastMapper.updateByExampleSelective(netWorthLast, cr);
			}else{
				fundNetWorthLastMapper.insertSelective(netWorthLast);
			}
		}
		
		//查询最新的信息;
		FundNetWorthLastDto reLastDto = null;
		List<TNetWorthLast> netWorthLastDtoList = fundNetWorthLastMapper.selectByExample(cr);
		if(CollectionUtils.isNotEmpty(netWorthLastDtoList)){
			reLastDto = PojoConvertUtil.convert(netWorthLastDtoList.get(0), FundNetWorthLastDto.class);
		}
		return reLastDto;
	}


	

	
}
