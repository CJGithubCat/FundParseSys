package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.MarketSituation;
import com.zsh.labouCapital.entity.NetWorthHistory;


public interface IIndexMarketSituationService extends BaseService<MarketSituation>{
	public List<MarketSituation> queryMarketSituationInfo(MarketSituation marketSituation);

	public void updateMarketSituation(List<MarketSituation> updateList);

	List<IndexNew> queryAllIndexNewInfo(IndexNew indexNew);

	public void updateIndexNewInfo(List<IndexNew> updateList);

}
