package com.zsh.labouCapital.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsh.labouCapital.dao.NetWorthHistoryMapper;
import com.zsh.labouCapital.dto.TAnalyseAvglineResultDto;
import com.zsh.labouCapital.dto.TAvglineBugRecordDto;
import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TAvglineBugRecord;
import com.zsh.labouCapital.entity.TTradeDetail;
import com.zsh.labouCapital.entity.TTradeModel;
import com.zsh.labouCapital.entity.TTradeSummary;
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
			netWorthHistoryMapper.deleteNetWorthHistoryByExample(tempNetWorth);
			netWorthHistoryMapper.addNetWorthInfo(tempNetWorth);
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

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#addOrUpdateNetWorth(java.util.List)
    */
    @Override
    public void addOrUpdateNetWorth(List<NetWorthHistory> reList) {
        for (int i = 0; i < reList.size(); i++) {
            NetWorthHistory element = reList.get(i);
            List<NetWorthHistory> reNetValues= netWorthHistoryMapper.queryNetWorthHistoryInfo(element);
            if(reNetValues == null || reNetValues.size() <= 0){
                netWorthHistoryMapper.addNetWorthInfo(element);
            }else{
                netWorthHistoryMapper.updateNetWorthHistory(element);
            }
        }
        
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#queryAvgLineModelData(com.zsh.labouCapital.entity.TAvglineBugRecord)
    */
    @Override
    public List<TAvglineBugRecord> queryAvgLineModelData(TAvglineBugRecordDto avglineBugRecord) {
        return netWorthHistoryMapper.queryAvgLineModelData(avglineBugRecord);
    }

    /**
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#addAvglineBugRecords(java.util.List)
    */
    @Override
    public void addAvglineBugRecords(List<TAvglineBugRecord> avglineBugRecordList) {
        for (TAvglineBugRecord tAvglineBugRecord : avglineBugRecordList) {
            netWorthHistoryMapper.addAvglineBugRecord(tAvglineBugRecord);
        }
    }
    
    @Override
    public void addAllAvgLineAnalyseResult(){
        netWorthHistoryMapper.addAllAvgLineAnalyseResult();
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#deleteAnalyseAvgLineResult(com.zsh.labouCapital.dto.TAnalyseAvglineResultDto)
    */
    @Override
    public void deleteAnalyseAvgLineResult(TAnalyseAvglineResultDto param) {
        netWorthHistoryMapper.deleteAnalyseAvgLineResult(param);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#queryTradeSummaryByExample(com.zsh.labouCapital.entity.TTradeSummary)
    */
    @Override
    public TTradeSummary queryTradeSummaryByExample(TTradeSummary params) {
        return netWorthHistoryMapper.queryTradeSummaryByExample(params);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#insertTradeDetailInfo(com.zsh.labouCapital.entity.TTradeDetail)
    */
    @Override
    public void insertTradeDetailInfo(TTradeDetail buyDatetial) {
        netWorthHistoryMapper.insertTradeDetailInfo(buyDatetial);
    }
    
    @Override
    public void insertTradeSummaryInfo(TTradeSummary params){
        netWorthHistoryMapper.insertTradeSummaryInfo(params);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#queryTradeDetailList(com.zsh.labouCapital.entity.TTradeDetail)
    */
    @Override
    public List<TTradeDetail> queryTradeDetailList(TTradeDetail buyDatetial) {
        return netWorthHistoryMapper.queryTradeDetailList(buyDatetial);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#updateTradeSummayByExample(com.zsh.labouCapital.entity.TTradeSummary)
    */
    @Override
    public void updateTradeSummayByExample(TTradeSummary tradeSummary) {
        netWorthHistoryMapper.updateTradeSummayByExample(tradeSummary);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#deleteTradeSummaryByExample(com.zsh.labouCapital.entity.TTradeSummary)
    */
    @Override
    public void deleteTradeSummaryByExample(TTradeSummary delParam) {
        netWorthHistoryMapper.deleteTradeSummaryByExample(delParam);
    }

    /****************t_trade_model****************/
    @Override
    public List<TTradeModel> queryTradeModelByExample(TTradeModel example){
        return netWorthHistoryMapper.selectTradeModelByExample(example);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#updateTradeModelInfo(com.zsh.labouCapital.entity.TTradeModel)
    */
    @Override
    public void updateTradeModelInfo(TTradeModel updateTradeModel) {
        netWorthHistoryMapper.updateTradeModelInfo(updateTradeModel);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#getMaxDateNetWorthHistory(java.util.Date)
    */
    @Override
    public NetWorthHistory getMaxDateNetWorthHistory(Date endDate) {
        return netWorthHistoryMapper.getMaxDateNetWorthHistory(endDate);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#deleteTradeDetailByExample(com.zsh.labouCapital.entity.TTradeDetail)
    */
    @Override
    public void deleteTradeDetailByExample(TTradeDetail tradeDetail) {
        netWorthHistoryMapper.deleteTradeDetailByExample(tradeDetail);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#updateTradeModelByExample(com.zsh.labouCapital.entity.TTradeModel)
    */
    @Override
    public void updateTradeModelByExample(TTradeModel updateTradeModel) {
        netWorthHistoryMapper.updateTradeModelByExample(updateTradeModel);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#updateTradeModelEarnInfo(com.zsh.labouCapital.entity.TTradeModel)
    */
    @Override
    public void updateTradeModelEarnInfo(TTradeModel uptradeModel) {
        netWorthHistoryMapper.updateTradeModelEarnInfo(uptradeModel);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#queryTradeModelById(java.lang.String)
    */
    @Override
    public TTradeModel queryTradeModelById(String modelId) {
        return netWorthHistoryMapper.queryTradeModelById(modelId);
    }

    /**
    *
    * (non-Javadoc)
    * @see com.zsh.labouCapital.service.INetWorthHistoryService#getNewestDateByFundCode(java.lang.String)
    */
    @Override
    public String getNewestDateByFundCode(String fundCode) {
        return netWorthHistoryMapper.getNewestDateByFundCode(fundCode);
    }
}
