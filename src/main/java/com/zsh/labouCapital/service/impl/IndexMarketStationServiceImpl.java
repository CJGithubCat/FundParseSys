/*package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.dao.FundSummaryMapper;
import com.zsh.labouCapital.dao.IndexMarketSituationMapper;
import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.MarketSituation;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;

@Service
public class IndexMarketStationServiceImpl implements IIndexMarketSituationService {
	@Autowired
	private IndexMarketSituationMapper indexMarketSituationMapper;

	@Override
	public List<MarketSituation> queryMarketSituationInfo(MarketSituation marketSituation) {
		return indexMarketSituationMapper.queryMarketSituationInfo(marketSituation);
	}

	@Override
	public void updateMarketSituation(List<MarketSituation> updateList) {
		for (int i = 0; i < updateList.size(); i++) {
			MarketSituation marketSituation = updateList.get(i);
			indexMarketSituationMapper.updateMarketSituation(marketSituation);
		}
	}
	
	@Override
	public List<IndexNew> queryAllIndexNewInfo(IndexNew indexNew){
		return indexMarketSituationMapper.queryAllIndexNewInfo(indexNew);
	}

	@Override
	public void updateIndexNewInfo(List<IndexNew> updateList) {
		for (int i = 0; i < updateList.size(); i++) {
			IndexNew indexNew = updateList.get(i);
			indexMarketSituationMapper.updateIndexNewInfo(indexNew);
		}		
	}

	@Override
	@Transactional
	public void insertIndexFundTemp(List<TIndexFundTemp> upList) {
		for (int i = 0; i < upList.size(); i++) {
			TIndexFundTemp indexNew = upList.get(i);
			indexMarketSituationMapper.insertIndexFundTemp(indexNew);
		}
	}

	@Override
	public List<MarketSituationDTO> queryUnderValueMarkStationIndex(MarketSituationDTO marketSituation) {
		// TODO Auto-generated method stub
		return indexMarketSituationMapper.queryUnderValueMarkStationIndex(marketSituation);
	}

	@Override
	public List<TIndexFundTemp> queryAllIndexFundTemp(TIndexFundTemp indexNew) {
		return indexMarketSituationMapper.queryAllIndexFundTemp(indexNew);
	}
	
}
*/