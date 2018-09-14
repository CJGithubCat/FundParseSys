package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.NetWorthHistoryMapper;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.service.INetWorthHistoryService;

@Service
// 先不声明为原型类型，当有状态添加的时候再声明
// @Scope("prototype")
public class NetWorthHistoryServiceImpl extends BaseServiceImpl<NetWorthHistory> implements INetWorthHistoryService {
	@Autowired
	private NetWorthHistoryMapper netWorthHistoryMapper;

	@Override
	public List<NetWorthHistory> queryNetWorthHistoryInfo(NetWorthHistory netWorthHistory) {
		return netWorthHistoryMapper.queryNetWorthHistoryInfo(netWorthHistory);
	}

	@Override
	@Transactional
	public void updateNetWorthHistorys(List<NetWorthHistory> updateList) {
		for (int i = 0; i < updateList.size(); i++) {
			NetWorthHistory tempNetWorth = updateList.get(i);
			netWorthHistoryMapper.updateNetWorthHistory(tempNetWorth);
		}
	}
}
