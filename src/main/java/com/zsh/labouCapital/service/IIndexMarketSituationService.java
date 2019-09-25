package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.MarketSituation;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TIndexFundTemp;


public interface IIndexMarketSituationService {
	public List<MarketSituation> queryMarketSituationInfo(MarketSituation marketSituation);

	public void updateMarketSituation(List<MarketSituation> updateList);

	List<IndexNew> queryAllIndexNewInfo(IndexNew indexNew);

	public void updateIndexNewInfo(List<IndexNew> updateList);

	public void insertIndexFundTemp(List<TIndexFundTemp> upList);

	List<TIndexFundTemp> queryAllIndexFundTemp(TIndexFundTemp indexNew);
	
	public List<MarketSituationDTO> queryUnderValueMarkStationIndex(MarketSituationDTO marketSituation);
}
