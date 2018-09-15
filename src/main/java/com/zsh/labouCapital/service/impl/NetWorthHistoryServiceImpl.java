package com.zsh.labouCapital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.NetWorthHistoryMapper;
import com.zsh.labouCapital.entity.IntervalBuy;
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

	@Override
	public NetWorthHistory getBuyNetWorthInfo(String fundCode, String tempDateStr) {
		Map<String, String> params = new HashMap<String,String>();
		params.put("fundCode", fundCode);
		params.put("dateInfo", tempDateStr);
		return netWorthHistoryMapper.getBuyNetWorthInfo(params);
	}

	@Override
	@Transactional
	public void addIntervalTradeInfo(List<IntervalBuy> insertList) {
		for (int i = 0; i < insertList.size(); i++) {
			IntervalBuy intervalBuy = insertList.get(i);
			netWorthHistoryMapper.addIntervalTradeInfo(intervalBuy);
		}
	}

	@Override
	@Transactional
	public void updateOrinsertNetWorth(List<NetWorthHistory> revelus) {
		for (int i = 0; i < revelus.size(); i++) {
			NetWorthHistory netWorth = revelus.get(i);
			//查询是否有这个记录
			List<NetWorthHistory> tempList = netWorthHistoryMapper.queryNetWorthHistoryInfo(netWorth);
			if(tempList == null || tempList.size() == 0){
				netWorthHistoryMapper.addNetWorthInfo(netWorth);
			}
		}
	}
}
