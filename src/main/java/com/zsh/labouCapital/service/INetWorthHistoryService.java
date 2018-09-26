package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;


public interface INetWorthHistoryService extends BaseService<NetWorthHistory>{
	public List<NetWorthHistory> queryNetWorthHistoryInfo(NetWorthHistory netWorthHistory);

	public void updateNetWorthHistorys(List<NetWorthHistory> updateList);

	public NetWorthHistory getBuyNetWorthInfo(String fundCode, String tempDateStr);

	public void addIntervalTradeInfo(List<IntervalBuy> insertList);

	public void updateOrinsertNetWorth(List<NetWorthHistory> revelus);

    /**   
     * @Title: addOrUpdateNetWorth   
     * @Description: TODO   
     * @param: @param reList      
     * @return: void      
     * @throws   
     */
    public void addOrUpdateNetWorth(List<NetWorthHistory> reList);
}
