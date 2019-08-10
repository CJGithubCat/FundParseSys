package com.zsh.labouCapital.service;
import java.util.Date;
import java.util.List;

import com.zsh.labouCapital.dto.TAnalyseAvglineResultDto;
import com.zsh.labouCapital.dto.TAvglineBugRecordDto;
import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.TAvglineBugRecord;
import com.zsh.labouCapital.entity.TTradeDetail;
import com.zsh.labouCapital.entity.TTradeModel;
import com.zsh.labouCapital.entity.TTradeSummary;


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

    /**   
     * @Title: queryAvgLineModelData   
     * @Description: TODO   
     * @param: @param avglineBugRecord
     * @param: @return      
     * @return: List<TAvglineBugRecord>      
     * @throws   
     */
    public List<TAvglineBugRecord> queryAvgLineModelData(TAvglineBugRecordDto avglineBugRecord);

    /**   
     * @Title: addAvglineBugRecords   
     * @Description: TODO   
     * @param: @param avglineBugRecordList      
     * @return: void      
     * @throws   
     */
    public void addAvglineBugRecords(List<TAvglineBugRecord> avglineBugRecordList);

    /**   
     * @Title: addAllAvgLineAnalyseResult   
     * @Description: TODO   
     * @param:       
     * @return: void      
     * @throws   
     */
    void addAllAvgLineAnalyseResult();

    /**   
     * @Title: deleteAnalyseAvgLineResult   
     * @Description: TODO   
     * @param: @param param      
     * @return: void      
     * @throws   
     */
    public void deleteAnalyseAvgLineResult(TAnalyseAvglineResultDto param);

    /**   
     * @Title: queryTradeSummaryByExample   
     * @Description: TODO   
     * @param: @param params
     * @param: @return      
     * @return: TTradeSummary      
     * @throws   
     */
    public TTradeSummary queryTradeSummaryByExample(TTradeSummary params);

    /**   
     * @Title: insertTradeDetailInfo   
     * @Description: TODO   
     * @param: @param buyDatetial      
     * @return: void      
     * @throws   
     */
    public void insertTradeDetailInfo(TTradeDetail buyDatetial);

    /**   
     * @Title: insertTradeSummaryInfo   
     * @Description: TODO   
     * @param: @param params      
     * @return: void      
     * @throws   
     */
    void insertTradeSummaryInfo(TTradeSummary params);
    
    public List<TTradeDetail> queryTradeDetailList(TTradeDetail buyDatetial);

    /**   
     * @Title: updateTradeSummayByExample   
     * @Description: TODO   
     * @param: @param tradeSummary      
     * @return: void      
     * @throws   
     */
    public void updateTradeSummayByExample(TTradeSummary tradeSummary);

    /**   
     * @Title: deleteTradeSummaryByExample   
     * @Description: TODO   
     * @param: @param delParam      
     * @return: void      
     * @throws   
     */
    public void deleteTradeSummaryByExample(TTradeSummary delParam);

    /**   
     * @Title: updateTradeModelInfo   
     * @Description: TODO   
     * @param: @param updateTradeModel      
     * @return: void      
     * @throws   
     */
    public void updateTradeModelInfo(TTradeModel updateTradeModel);
    
    /**   
     * @Title: selectTradeModelByExample   
     * @Description: TODO   
     * @param: @param example
     * @param: @return      
     * @return: List<TTradeModel>      
     * @throws   
     */
    List<TTradeModel> queryTradeModelByExample(TTradeModel example);
    
    TTradeModel queryTradeModelById(String modelId);

    /**   
     * @Title: getMaxDateNetWorthHistory   
     * @Description: TODO   
     * @param: @param endDate
     * @param: @return      
     * @return: NetWorthHistory      
     * @throws   
     */
    public NetWorthHistory getMaxDateNetWorthHistory(Date endDate);

    /**   
     * @Title: deleteTradeDetailByExample   
     * @Description: TODO   
     * @param: @param tradeDetail      
     * @return: void      
     * @throws   
     */
    public void deleteTradeDetailByExample(TTradeDetail tradeDetail);

    /**   
     * @Title: updateTradeModelByExample   
     * @Description: TODO   
     * @param: @param updateTradeModel      
     * @return: void      
     * @throws   
     */
    public void updateTradeModelByExample(TTradeModel updateTradeModel);

    /**   
     * @Title: updateTradeModelEarnInfo   
     * @Description: TODO   
     * @param: @param uptradeModel      
     * @return: void      
     * @throws   
     */
    public void updateTradeModelEarnInfo(TTradeModel uptradeModel);

    /**   
     * @Title: getNewestDateByFundCode   
     * @Description: TODO   
     * @param: @param fundCode
     * @param: @return      
     * @return: Date      
     * @throws   
     */
    public String getNewestDateByFundCode(String fundCode);
}
