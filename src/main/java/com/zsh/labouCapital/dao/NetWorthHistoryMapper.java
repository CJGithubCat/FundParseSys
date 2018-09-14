package com.zsh.labouCapital.dao;

import java.util.List;

import com.zsh.labouCapital.entity.NetWorthHistory;


public interface NetWorthHistoryMapper extends BaseSqlMapper<NetWorthHistory> {
	 List<NetWorthHistory> queryNetWorthHistoryInfo(NetWorthHistory netWorthHistory);

	void updateNetWorthHistory(NetWorthHistory tempNetWorth);

}
