package com.zsh.labouCapital.dao;

import java.util.List;
import java.util.Map;

import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;


public interface NetWorthHistoryMapper extends BaseSqlMapper<NetWorthHistory> {
	 List<NetWorthHistory> queryNetWorthHistoryInfo(NetWorthHistory netWorthHistory);

	void updateNetWorthHistory(NetWorthHistory tempNetWorth);
	NetWorthHistory getBuyNetWorthInfo(Map<String, String> params);

	void addIntervalTradeInfo(IntervalBuy intervalBuy);
}
