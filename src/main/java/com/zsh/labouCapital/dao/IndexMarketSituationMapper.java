package com.zsh.labouCapital.dao;

import java.util.List;

import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.MarketSituation;

public interface IndexMarketSituationMapper extends BaseSqlMapper<MarketSituation> {

	void updateMarketSituation(MarketSituation marketSituation);

	List<MarketSituation> queryMarketSituationInfo(MarketSituation marketSituation);
	
	void addMarketSituation(List<MarketSituation> marketSituationList);

	List<IndexNew> queryAllIndexNewInfo(IndexNew indexNew);

	void updateIndexNewInfo(IndexNew indexNew);
}
