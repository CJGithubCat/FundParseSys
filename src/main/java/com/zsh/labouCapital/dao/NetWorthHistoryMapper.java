package com.zsh.labouCapital.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zsh.labouCapital.dto.TAnalyseAvglineResultDto;
import com.zsh.labouCapital.dto.TAvglineBugRecordDto;
import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TAvglineBugRecord;
import com.zsh.labouCapital.entity.TTradeDetail;
import com.zsh.labouCapital.entity.TTradeModel;
import com.zsh.labouCapital.entity.TTradeSummary;


public interface NetWorthHistoryMapper extends BaseSqlMapper<NetWorthHistory> {
	List<NetWorthHistory> queryNetWorthHistoryInfo(NetWorthHistory netWorthHistory);
	void updateNetWorthHistory(NetWorthHistory tempNetWorth);
	void deleteNetWorthHistoryByExample(NetWorthHistory tempNetWorth);
	NetWorthHistory getBuyNetWorthInfo(Map<String, String> params);

	void addIntervalTradeInfo(IntervalBuy intervalBuy);

	void addNetWorthInfo(NetWorthHistory temp);
    /**
     * @return    
     * @Title: queryAvgLineModelData   
     * @Description: TODO   
     * @param: @param avglineBugRecord      
     * @return: void      
     * @throws   
     */
    List<TAvglineBugRecord> queryAvgLineModelData(TAvglineBugRecordDto avglineBugRecord);
    /**   
     * @Title: addAvglineBugRecord   
     * @Description: TODO   
     * @param: @param tAvglineBugRecord      
     * @return: void      
     * @throws   
     */
    void addAvglineBugRecord(TAvglineBugRecord tAvglineBugRecord);
    
    void addAllAvgLineAnalyseResult();
    /**   
     * @Title: deleteAnalyseAvgLineResult   
     * @Description: TODO   
     * @param: @param param      
     * @return: void      
     * @throws   
     */
    void deleteAnalyseAvgLineResult(TAnalyseAvglineResultDto param);
    /**   
     * @Title: queryTradeSummaryByExample   
     * @Description: TODO   
     * @param: @param params
     * @param: @return      
     * @return: TTradeSummary      
     * @throws   
     */
    TTradeSummary queryTradeSummaryByExample(TTradeSummary params);
    /**   
     * @Title: insertTradeDetailInfo   
     * @Description: TODO   
     * @param: @param buyDatetial      
     * @return: void      
     * @throws   
     */
    void insertTradeDetailInfo(TTradeDetail buyDatetial);
    
    void insertTradeSummaryInfo(TTradeSummary params);
    /**
     * @return    
     * @Title: queryTradeDetailList   
     * @Description: TODO   
     * @param: @param buyDatetial      
     * @return: void      
     * @throws   
     */
    List<TTradeDetail> queryTradeDetailList(TTradeDetail buyDatetial);
    /**   
     * @Title: updateTradeSummayByExample   
     * @Description: TODO   
     * @param: @param tradeSummary      
     * @return: void      
     * @throws   
     */
    void updateTradeSummayByExample(TTradeSummary tradeSummary);
    /**   
     * @Title: deleteAnalyseAvgLineResult   
     * @Description: TODO   
     * @param: @param delParam      
     * @return: void      
     * @throws   
     */
    void deleteAnalyseAvgLineResult(TTradeSummary delParam);
    /**   
     * @Title: deleteTradeSummaryByExample   
     * @Description: TODO   
     * @param: @param delParam      
     * @return: void      
     * @throws   
     */
    void deleteTradeSummaryByExample(TTradeSummary delParam);
    /**   
     * @Title: selectTradeModelByExample   
     * @Description: TODO   
     * @param: @param example
     * @param: @return      
     * @return: List<TTradeModel>      
     * @throws   
     */
    List<TTradeModel> selectTradeModelByExample(TTradeModel example);
    /**   
     * @Title: updateTradeModelInfo   
     * @Description: TODO   
     * @param: @param updateTradeModel      
     * @return: void      
     * @throws   
     */
    void updateTradeModelInfo(TTradeModel updateTradeModel);
    /**   
     * @Title: getMaxDateNetWorthHistory   
     * @Description: TODO   
     * @param: @param endDate
     * @param: @return      
     * @return: NetWorthHistory      
     * @throws   
     */
    NetWorthHistory getMaxDateNetWorthHistory(Date endDate);
    /**   
     * @Title: deleteTradeDetailByExample   
     * @Description: TODO   
     * @param: @param tradeDetail      
     * @return: void      
     * @throws   
     */
    void deleteTradeDetailByExample(TTradeDetail tradeDetail);
    /**   
     * @Title: updateTradeModelByExample   
     * @Description: TODO   
     * @param: @param updateTradeModel      
     * @return: void      
     * @throws   
     */
    void updateTradeModelByExample(TTradeModel updateTradeModel);
    /**   
     * @Title: updateTradeModelEarnInfo   
     * @Description: TODO   
     * @param: @param uptradeModel      
     * @return: void      
     * @throws   
     */
    void updateTradeModelEarnInfo(TTradeModel uptradeModel);
    /**   
     * @Title: queryTradeModelById   
     * @Description: TODO   
     * @param: @param modelId
     * @param: @return      
     * @return: TTradeModel      
     * @throws   
     */
    TTradeModel queryTradeModelById(String modelId);
    /**   
     * @Title: getNewestDateByFundCode   
     * @Description: TODO   
     * @param: @param fundCode
     * @param: @return      
     * @return: Date      
     * @throws   
     */
    String getNewestDateByFundCode(String fundCode);
}
