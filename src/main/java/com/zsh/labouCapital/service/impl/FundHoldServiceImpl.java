package com.zsh.labouCapital.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.controller.request.FundHoldRequest;
import com.zsh.labouCapital.dao.client.THoldFundMapper;
import com.zsh.labouCapital.dao.client.THoldFundSummaryMapper;
import com.zsh.labouCapital.dao.dto.THoldFund;
import com.zsh.labouCapital.dao.dto.THoldFundCriteria;
import com.zsh.labouCapital.dao.dto.THoldFundSummary;
import com.zsh.labouCapital.dao.dto.THoldFundSummaryCriteria;
import com.zsh.labouCapital.service.IFundHoldService;
import com.zsh.labouCapital.util.PojoConvertUtil;

@Service
public class FundHoldServiceImpl implements IFundHoldService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private THoldFundMapper fundHoldMapper;

	@Autowired
	private THoldFundSummaryMapper fundHoldSummaryMapper;
	
	@Override
	public int queryFundHoldCount(FundHoldRequest fundRequest) {
		THoldFundCriteria cr = getCriteria(fundRequest);
		return fundHoldMapper.countByExample(cr);
	}

	@Override
	public List<THoldFund> queryFundHoldList(FundHoldRequest fundRequest) {
		THoldFundCriteria cr = getCriteria(fundRequest);
		return fundHoldMapper.selectByExample(cr);
	}
	
	public THoldFundCriteria getCriteria(FundHoldRequest fundRequest){
		THoldFundCriteria cr = new THoldFundCriteria();
		THoldFundCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		if(!StringUtils.isEmpty(fundRequest.getFundName())){
			criteria.andFundNameLike(fundRequest.getFundName()+"%");
		}
		cr.setOffset(fundRequest.getOffSet());
		cr.setLimit(fundRequest.getPageSize());
		cr.setOrderByClause(" market_value desc ");
		return cr;
	}

	@Override
	public Map<String, Object> parseFundHoldList(MultipartFile summaryFile) throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if(summaryFile != null){
			String fileStr = new String(summaryFile.getBytes(),"UTF-8");
			JSONObject fileContent = JSONObject.parseObject(fileStr);
			
			JSONObject dataJSONObj = fileContent.getJSONObject("data");
			
			if(dataJSONObj == null){
				return null;
			}
			
			//解析汇总信息
			THoldFundSummary holdFundSummary = new THoldFundSummary(); 
			holdFundSummary.setTotalAssets(dataJSONObj.getDouble("total_assets"));
			holdFundSummary.setDailyGain(dataJSONObj.getDouble("daily_gain"));
			holdFundSummary.setTotalGain(dataJSONObj.getDouble("total_gain"));
			holdFundSummary.setHoldGain(dataJSONObj.getDouble("hold_gain"));
			holdFundSummary.setTs(dataJSONObj.getDate("ts"));
			
			reMap.put("holdFundSummary", holdFundSummary);
			//解析具体基金持仓信息
			JSONArray jsonArray = dataJSONObj.getJSONArray("items");
			
			List<THoldFund> holdFundList = new ArrayList<THoldFund>();
			if(CollectionUtils.isNotEmpty(jsonArray)){
				for (Object object : jsonArray) {
					if(object == null){
						continue;
					}
					JSONObject jsonObject = (JSONObject)object;
					THoldFund temObj = new THoldFund();
					temObj.setAccumCost(jsonObject.getDouble("accum_cost"));
					temObj.setAutoInvestCount(jsonObject.getDouble("auto_invest_count"));
					temObj.setDailyGain(jsonObject.getDouble("daily_gain"));
					temObj.setDateCreate(new Date());
					temObj.setDateUpdate(new Date());
					temObj.setFundCode(jsonObject.getString("fd_code"));
					temObj.setFundName(jsonObject.getString("fd_name"));
					temObj.setHoldCost(jsonObject.getDouble("hold_cost"));
					temObj.setHoldGain(jsonObject.getDouble("hold_gain"));
					temObj.setHoldGainRate(jsonObject.getDouble("hold_gain_rate"));
					temObj.setHoldingCost(jsonObject.getDouble("holding_cost"));
					temObj.setMarketValue(jsonObject.getDouble("market_value"));
					temObj.setNav(jsonObject.getDouble("nav"));
					temObj.setNavGrtd(jsonObject.getDouble("nav_grtd"));
					temObj.setNavGuzhi(0D);
					temObj.setProfitDocuments(jsonObject.getString("profit_documents"));
					temObj.setTotalGain(jsonObject.getDouble("total_gain"));
					temObj.setTotalGainRate(jsonObject.getDouble("total_gain_rate"));
					temObj.setTs(jsonObject.getDate("ts"));
					temObj.setType(jsonObject.getString("type"));
					temObj.setUnconfirmedAmount(jsonObject.getDouble("unconfirmed_amount"));
					temObj.setUnconfirmedCount(jsonObject.getDouble("unconfirmed_count"));
					temObj.setUnconfirmedVolume(jsonObject.getDouble("unconfirmed_volume"));
					temObj.setUsableRemainShare(jsonObject.getDouble("usable_remain_share"));
					temObj.setVolume(jsonObject.getDouble("volume"));
					
					logger.error("json:item:" + temObj);
					if(temObj != null){
						holdFundList.add(temObj);
					}
				}
				reMap.put("holdFundList", holdFundList);
			}
		}
		return reMap;
	}

	@Override
	public void updateOrAddHoldFundSummary(THoldFundSummary holdFundSummary) {
		THoldFundSummaryCriteria cr = new THoldFundSummaryCriteria();
		List<THoldFundSummary> sumaryList = fundHoldSummaryMapper.selectByExample(cr);
		if(CollectionUtils.isEmpty(sumaryList)){
			fundHoldSummaryMapper.insert(holdFundSummary);
		}else{
			THoldFundSummary tempSumary = sumaryList.get(0);
			holdFundSummary.setId(tempSumary.getId());
			fundHoldSummaryMapper.updateByPrimaryKey(holdFundSummary);
		}
	}

	@Override
	public void updateOrAddHoldFund(List<THoldFund> holdFundList) {
		for (THoldFund tHoldFund : holdFundList) {
			FundHoldRequest param = PojoConvertUtil.convert(tHoldFund, FundHoldRequest.class);
			THoldFundCriteria criteria = this.getCriteria(param);
			List<THoldFund> fundHoldList = fundHoldMapper.selectByExample(criteria);
			if(CollectionUtils.isEmpty(fundHoldList)){
				fundHoldMapper.insert(tHoldFund);
			}else{
				fundHoldMapper.updateByExampleSelective(tHoldFund, criteria);
			}
		}
	}
}
