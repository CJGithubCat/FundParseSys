///*package com.zsh.labouCapital.controller;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSON;
//import com.zsh.labouCapital.dto.FundNetWorthDTO;
//import com.zsh.labouCapital.dto.TAnalyseAvglineResultDto;
//import com.zsh.labouCapital.dto.TAvglineBugRecordDto;
//import com.zsh.labouCapital.entity.ExpectFund;
//import com.zsh.labouCapital.entity.FundSummary;
//import com.zsh.labouCapital.entity.IntervalBuy;
//import com.zsh.labouCapital.entity.NetWorthHistory;
//import com.zsh.labouCapital.entity.ReturnValue;
//import com.zsh.labouCapital.entity.ReturnValue2;
//import com.zsh.labouCapital.entity.TAvglineBugRecord;
//import com.zsh.labouCapital.entity.TTradeDetail;
//import com.zsh.labouCapital.entity.TTradeModel;
//import com.zsh.labouCapital.entity.TTradeSummary;
//import com.zsh.labouCapital.service.IExpectFundService;
//import com.zsh.labouCapital.service.IFundSummaryService;
//import com.zsh.labouCapital.service.ILoggerService;
//import com.zsh.labouCapital.service.INetWorthHistoryService;
//import com.zsh.labouCapital.util.DateTimeUtil;
//import com.zsh.labouCapital.util.HttpclientUtil;
//import com.zsh.labouCapital.util.UUIDUtils;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//*//**
// * 函数功能：NetWorthHistory
// *//*
//@RestController
//@RequestMapping(value = "/netWorth")
//public class NetWorthHistoryController {
//    private static Logger logger = LoggerFactory.getLogger(NetWorthHistoryController.class);
//
//    @Autowired
//    private INetWorthHistoryService netWorthHistoryService;
//
//    @Autowired
//    private IFundSummaryService fundSummaryService;
//
//    @Autowired
//    private IExpectFundService iExpectFundServiceImpl;
//
//    *//**
//     * 函数功能：统计当前的日增长率
//     *//*
//    @RequestMapping("/queryNetWorthInfoList")
//    @ResponseBody
//    public ReturnValue2 queryNetWorthInfoList(HttpServletRequest request) {
//    	List<NetWorthHistory> netWorthList = new ArrayList<>();
//    	NetWorthHistory tempNetWorth1 = new NetWorthHistory();
//    	tempNetWorth1.setFundCode("160091");
//    	tempNetWorth1.setNetWorth(0.987);
//    	netWorthList.add(tempNetWorth1);
//    	
//    	NetWorthHistory tempNetWorth2 = new NetWorthHistory();
//    	tempNetWorth2.setFundCode("160091");
//    	tempNetWorth2.setNetWorth(0.987);
//    	netWorthList.add(tempNetWorth2);
//    	
//    	NetWorthHistory tempNetWorth3 = new NetWorthHistory();
//    	tempNetWorth3.setFundCode("160091");
//    	tempNetWorth3.setNetWorth(0.987);
//    	netWorthList.add(tempNetWorth3);
//    	
//    	ReturnValue2 rv = new ReturnValue2();
//    	rv.setCode("0");
//    	rv.setCount(1);
//    	rv.setData(netWorthList);
//    	rv.setMsg("success");
//    	
//    	return rv;
//    }
//    
//    *//**
//     * 函数功能：统计当前的日增长率
//     *//*
//    @RequestMapping("/calDayGrothRate")
//    @ResponseBody
//    public ReturnValue calDayGrothRate(HttpServletRequest request) {
//        ReturnValue rv = new ReturnValue();
//
//        try {
//            // 1.查询所有基金信息
//            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
//
//            for (int k = 0; k < fundSummaryList.size(); k++) {
//                FundSummary paramFund = fundSummaryList.get(k);
//                NetWorthHistory paramNetWorth = new NetWorthHistory();
//                paramNetWorth.setFundCode(paramFund.getFundCode());
//
//                List<NetWorthHistory> updateList = new ArrayList<>();
//                List<NetWorthHistory> nwhList = netWorthHistoryService.queryNetWorthHistoryInfo(paramNetWorth);
//                if (nwhList.size() < 1) {
//                    rv.setSuccess(true);
//                    rv.setDatas(null);
//                    rv.setMessage("未查询到基金净值数据，计算失败!");
//                    return rv;
//                }
//                for (int i = 0, j = 1; i < nwhList.size() - 1; i++, j++) {
//                    NetWorthHistory lowValue = nwhList.get(i);
//                    NetWorthHistory highValue = nwhList.get(j);
//
//                    if (highValue.getNetWorth() == 0) {
//                        System.out.println("*********为0数据：" + highValue);
//                        continue;
//                    }
//                    double growRate = (lowValue.getNetWorth() - highValue.getNetWorth()) / highValue.getNetWorth();
//
//                    NetWorthHistory updateInfo = new NetWorthHistory();
//                    updateInfo.setId(lowValue.getId());
//                    updateInfo.setFundCode(lowValue.getFundCode());
//                    updateInfo.setNetworthDaygrowRate(growRate);
//                    updateList.add(updateInfo);
//                }
//                // 更新数据库中的日增长率
//                netWorthHistoryService.updateNetWorthHistorys(updateList);
//            }
//            rv.setSuccess(true);
//            rv.setDatas(null);
//        }
//        catch (Exception e) {
//            rv.setSuccess(false);
//            rv.setMessage(e.getMessage());
//            e.printStackTrace();
//        }
//        return rv;
//    }
//
//    *//**
//     * 函数功能：分析定投模型
//     *//*
//    @RequestMapping("/analyseIntervalBuyModule")
//    @ResponseBody
//    public ReturnValue analyseIntervalBuyModule(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {
//        ReturnValue rv = new ReturnValue();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        int buyType;// 1--每周； 2--每两周； 3--每月
//        int interval;// 周期
//        Date startTime = null;// 开始时间
//        try {
//            Date nowDate = new Date();
//            double erveryMoney = 1000;
//
//            if (fundNetWorthDTO != null && fundNetWorthDTO.getStartTime() != null) {
//                startTime = fundNetWorthDTO.getStartTime();
//            }
//            buyType = fundNetWorthDTO.getBuyType();
//            interval = fundNetWorthDTO.getInterval();
//
//            // 1.查询所有基金信息
//            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
//
//            for (int k = 0; k < fundSummaryList.size(); k++) {
//                FundSummary paramFund = fundSummaryList.get(k);
//                String fundCode = paramFund.getFundCode();
//                String estabDateStr = paramFund.getEstablishDate();
//                Date estabDate = sdf.parse(estabDateStr);
//
//                Date firstDate = null;
//                Calendar calendar = Calendar.getInstance();
//                if (startTime != null) {
//                    if (startTime.getTime() <= estabDate.getTime()) {// 开始时间在创建之前，则取创建时间后一月为第一月
//                        calendar.setTime(estabDate);
//                        calendar.add(Calendar.MONTH, 1);
//                        firstDate = calendar.getTime();
//                    }
//                    else {
//                        firstDate = startTime;
//                    }
//                }
//                else {
//                    calendar.setTime(estabDate);
//                    calendar.add(Calendar.MONTH, 1);
//                    firstDate = calendar.getTime();
//                }
//                calendar.setTime(firstDate);
//                List<IntervalBuy> insertList = new ArrayList<IntervalBuy>();
//                while (calendar.getTime().getTime() < nowDate.getTime() * 1000) {//
//                    String tempDateStr = sdf.format(firstDate);
//                    System.out.println("****:FundCode:" + fundCode + "  Date:" + tempDateStr);
//                    // 查询历史数据信息
//                    NetWorthHistory inNetWorthHistory = netWorthHistoryService.getBuyNetWorthInfo(fundCode, tempDateStr);
//                    if (inNetWorthHistory == null) {
//                        break;
//                    }
//
//                    IntervalBuy intervalBuy = new IntervalBuy();
//                    intervalBuy.setFundCode(fundCode);
//                    intervalBuy.setTotalCost(erveryMoney);
//                    intervalBuy.setNetWorth(inNetWorthHistory.getNetWorth());
//                    if (inNetWorthHistory.getNetWorth() != 0) {
//                        intervalBuy.setTotalNum(erveryMoney / inNetWorthHistory.getNetWorth());
//                    }
//                    intervalBuy.setTradeDay(tempDateStr);
//                    insertList.add(intervalBuy);
//
//                    // 根据购买类型和间隔选择
//                    if (buyType == 1) {// 每一周
//                        calendar.add(Calendar.WEEK_OF_YEAR, 1);
//                    }
//                    else if (buyType == 2) {// 每两周
//                        calendar.add(Calendar.WEEK_OF_YEAR, 2);
//                    }
//                    else if (buyType == 3) {// 每月
//                        calendar.add(Calendar.MONTH, 1);
//                    }
//                    calendar.setTime(firstDate);
//                }
//                // 插入到交易表
//                netWorthHistoryService.addIntervalTradeInfo(insertList);
//                System.out.println("**:" + insertList);///////////////
//            }
//            rv.setSuccess(true);
//            rv.setDatas(null);
//        }
//        catch (Exception e) {
//            rv.setSuccess(false);
//            rv.setMessage(e.getMessage());
//            e.printStackTrace();
//        }
//        return rv;
//    }
//
//    *//**
//     * @Title: getRealTimeNetWorthInfo
//     * @Description: 获取实时的净值信息
//     * @param:
//     * @return: void
//     * @throws
//     *//*
//    public void getRealTimeNetWorthInfo(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {
//
//    }
//
//    *//**
//     * 解析js中的历史累计净值数据
//     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
//     *//*
//    @RequestMapping("/parseSpecJsHistoryAddWorth")
//    @ResponseBody
//    public void parseSpecJsHistoryAddWorth(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {
//        System.out.println("******************开始分析累积净值信息****************");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        //查询关注的基金信息;
//        List<ExpectFund> allExpectFundList = iExpectFundServiceImpl.queryExpectFundList(null);
//        for (int i = 0; i < allExpectFundList.size(); i++) {
//          ExpectFund expectFundEle = allExpectFundList.get(i);
//          parseNewestNetworthInfo(expectFundEle.getFundCode());
//        }
//        
//    }
//
//    *//**   
//     * @Title: parseNewestNetworthInfo   
//     * @Description: 解析关注的基金净值信息;   
//     * @param: @param sdf
//     * @param: @param fundCode      
//     * @return: void      
//     * @throws   
//     */
//    private void parseNewestNetworthInfo(String fundCode) {
//        //获取到指定基金当前最大的日期
//        String updateDateStr = netWorthHistoryService.getNewestDateByFundCode(fundCode);
//        if(StringUtils.isEmpty(updateDateStr)){
//            updateDateStr = "1900-01-01 00:00:00";//给个最小的值，保证所有数据都能够写进来;
//        }
//        Date updateDateLine = DateTimeUtil.parseDate(updateDateStr, DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
//
//        try {
//            // 1.查询所有信息;
//            FundSummary param = new FundSummary();
//            param.setFundCode(fundCode);
//            List<FundSummary> fundSummaryList = fundSummaryService.queryFundSummaryByEample(param);
//            for (int j = 0; j < fundSummaryList.size(); j++) {
//                List<NetWorthHistory> reList = new ArrayList<>();
//                List<NameValuePair> params = new ArrayList<>();
//                FundSummary tempFund = fundSummaryList.get(j);
//
//                String timeStamp = this.generateTimeStamp(new Date());
//                params.add(new BasicNameValuePair("v", timeStamp));
//                String reBody = HttpclientUtil.get(tempFund.getHistoryUrl(), params);
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("javascript");
//                engine.eval(reBody);
//
//                Object array = engine.get("Data_netWorthTrend");
//                JSONArray tempObj1 = JSONArray.fromObject(array);
//                String tempObj1Str = tempObj1.toString().replace("},", "}},{");
//                com.alibaba.fastjson.JSONArray temArr = (com.alibaba.fastjson.JSONArray) com.alibaba.fastjson.JSONArray.parse(tempObj1Str);
//                System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(temArr));
//
//                int len = temArr.size();
//                for (int i = 0; i < len; i++) {
//                    NetWorthHistory temValue = new NetWorthHistory();
//                    com.alibaba.fastjson.JSONObject jsonTemp =
//                            (com.alibaba.fastjson.JSONObject) temArr.getJSONObject(i).getJSONObject("" + i);
//
//                    Date dateTime = jsonTemp.getDate("x");
//                    // 只更新最近几天的数据;
//                    if (dateTime.getTime() > updateDateLine.getTime()) {
//                        double netWorth = (double) jsonTemp.getDoubleValue("y");
//                        temValue.setFundCode(tempFund.getFundCode());
//                        temValue.setNetWorth(netWorth);
//                        temValue.setDateInfo(dateTime);
//                        temValue.setDateInfoStr(DateTimeUtil.formatDate(dateTime, DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
//                        temValue.setDateCreate(DateTimeUtil.formatDate(new Date(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
//                        reList.add(temValue);
//                    }
//                }
//                System.out.println("AAAAAA:" + com.alibaba.fastjson.JSONObject.toJSONString(reList));
//                // 2.更新到数据库
//                netWorthHistoryService.updateNetWorthHistorys(reList);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @Title: genTimeStamp
//     * @Description: TODO
//     * @param: @return
//     * @return: String
//     * @throws
//     *//*
//    public String generateTimeStamp(Date dateTime) {
//        if (dateTime == null) {
//            dateTime = new Date();
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        return sdf.format(dateTime);
//    }
//
//    *//**
//     * 解析js中的历史累计净值数据
//     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
//     */
//    @RequestMapping("/parseAllJsHistoryAddWorth")
//    @ResponseBody
//    public void parseAllJsHistoryAddWorth(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {
//        System.out.println("******************开始分析累积净值信息****************");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            // 1.查询所有信息;
//            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
//
//            for (int j = 0; j < fundSummaryList.size(); j++) {
//                List<NetWorthHistory> reList = new ArrayList<>();
//                List<NameValuePair> params = new ArrayList<>();
//                FundSummary tempFund = fundSummaryList.get(j);
//
//                params.add(new BasicNameValuePair("v", "20190611161408"));
//                String reBody = HttpclientUtil.get(tempFund.getHistoryUrl(), params);
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("javascript");
//                engine.eval(reBody);
//                Object array = engine.get("Data_ACWorthTrend");
//                JSONArray tempObj = JSONArray.fromObject(array);
//                int len = tempObj.size();
//                for (int i = 0; i < len; i++) {
//                    NetWorthHistory temValue = new NetWorthHistory();
//                    JSONArray vaJsonArrary = (JSONArray) tempObj.get(i);
//
//                    Long dateTime = vaJsonArrary.getLong(0);
//                    Double addValue = (double) vaJsonArrary.getDouble(1);
//
//                    Date date = new Date(dateTime);
//                    String dateStr = sdf.format(date);
//
//                    temValue.setFundCode(tempFund.getFundCode());
//                    temValue.setDateInfo(date);
//                    temValue.setAddUpWorth(addValue);
//                    reList.add(temValue);
//                }
//                System.out.println("AAAAAA:" + reList.size());
//                // 2.更新到数据库
//                netWorthHistoryService.updateNetWorthHistorys(reList);
//            }
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 解析js中的信息
//     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
//     *//*
//    @RequestMapping("/parseJsNetWorth")
//    @ResponseBody
//    public void parseJsNetWorth(HttpServletRequest request, Date startDate) {
//        List<NameValuePair> params = new ArrayList<>();
//        List<NetWorthHistory> reList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            // 1.查询所有信息;
//            long startLong = startDate.getTime();
//            System.out.println("开始时间:" + sdf.format(startDate));
//            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
//            for (int j = 0; j < fundSummaryList.size(); j++) {
//                FundSummary fundTemp = fundSummaryList.get(j);
//                params.add(new BasicNameValuePair("v", "20180925161408"));
//                String reBody = HttpclientUtil.get(fundTemp.getHistoryUrl(), params);
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("javascript");
//                engine.eval(reBody);
//                Object array = engine.get("Data_netWorthTrend");
//                JSONArray tempObj = JSONArray.fromObject(array);
//                System.out.println(tempObj);
//                int len = tempObj.size();
//                for (int i = 0; i < len; i++) {
//                    NetWorthHistory temValue = new NetWorthHistory();
//                    JSONObject vaJsonObject = (JSONObject) tempObj.get(i);
//                    Long milSecod = vaJsonObject.getLong("x");
//                    if (milSecod <= startLong) {// 过滤大于指定日期的数据
//                        continue;
//                    }
//                    Double value = vaJsonObject.getDouble("y");
//                    String equityReturn = vaJsonObject.getString("equityReturn");
//                    String unitMoney = vaJsonObject.getString("unitMoney");
//                    int week = getWeekInfo(milSecod);
//
//                    String dateInfo = sdf.format(new Date(milSecod));
//                    temValue.setFundCode(fundTemp.getFundCode());
//                    temValue.setDateInfo(new Date(milSecod));
//                    temValue.setNetWorth(value);
//                    if (!StringUtils.isEmpty(equityReturn)) {
//                        temValue.setEquityReturn(Double.parseDouble(equityReturn));
//                    }
//                    temValue.setUnitMoney(unitMoney);
//                    temValue.setWeekInfo(week);
//                    reList.add(temValue);
//                }
//
//                netWorthHistoryService.addOrUpdateNetWorth(reList);
//                System.out.println("AAAAAA:" + reList.size());
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    *//**
//     * 函数功能：根据均线进行购买;
//     * 
//     * @throws IOException
//     * @throws ServletException
//     *//*
//    @RequestMapping("/ananlyBuyByAvgLineModel")
//    @ResponseBody
//    public ReturnValue ananlyBuyByAvgLineModel(HttpServletRequest request) throws ServletException, IOException {
//        ReturnValue rv = new ReturnValue();
//        try {
//            int[] buyModelArr = new int[] { 1, 2 };
//            int[] avgLineTypeArr = new int[] { 30, 60, 90, 120, 240 };
//            int startYear = 2015;
//            Calendar calendar = Calendar.getInstance();
//            int nowYear = calendar.get(Calendar.YEAR);
//            for (int i = 0; i < buyModelArr.length; i++) {
//                int buyModel = buyModelArr[i];
//                for (int j = 0; j < avgLineTypeArr.length; j++) {
//                    int avgLineType = avgLineTypeArr[j];
//                    if (startYear > nowYear) {
//                        continue;
//                    }
//                    for (int y = startYear; y <= nowYear; y++) {
//                        Date startDate = DateTimeUtil.parseDate(y + "-01-01 00:00:00", DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
//                        Date endDate = DateTimeUtil.parseDate(y + "-12-31 00:00:00", DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
//                        addAvglineBugRecordDB(startDate, endDate, avgLineType, buyModel);
//                    }
//                }
//
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        rv.setErrorCode(-1);
//        rv.setMessage("sucess");
//        rv.setSuccess(true);
//        return rv;
//    }
//
//    *//**
//     * @Title: addAvglineBugRecordDB
//     * @Description: 组装并入库操作;
//     * @param: @param startDate
//     * @param: @param endDate
//     * @param: @param avgLineType
//     * @param: @param buyModel
//     * @return: void
//     * @throws
//     *//*
//    public void addAvglineBugRecordDB(Date startDate, Date endDate, int avgLineType, int buyModel) {
//        // 1.获取所有指数信息
//        TAvglineBugRecordDto params = new TAvglineBugRecordDto();
//        params.setFundCode("161725");
//        params.setStartDate(startDate);
//        params.setEndDate(endDate);
//        params.setAvgLineType(avgLineType);
//        // 2.插入导数据库
//        List<TAvglineBugRecord> avglineBugRecordList = netWorthHistoryService.queryAvgLineModelData(params);
//        // 3.数据分析及汇总
//        logger.error("avglineBugRecordList:" + com.alibaba.fastjson.JSONObject.toJSONString(avglineBugRecordList));
//        String batchNo = DateTimeUtil.formatDate(new Date(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN4);
//        for (TAvglineBugRecord tAvglineBugRecord : avglineBugRecordList) {
//            long buyMoney = getBuyMoney(buyModel, tAvglineBugRecord);
//            double buyNum = buyMoney / tAvglineBugRecord.getNetWorth();
//            tAvglineBugRecord.setId(UUIDUtils.get());
//            tAvglineBugRecord.setBuyModel(buyModel);
//            tAvglineBugRecord.setAvgLineType(avgLineType);
//            tAvglineBugRecord.setBugMoney(buyMoney);
//            tAvglineBugRecord.setBugNum(buyNum);
//            tAvglineBugRecord.setStartDate(startDate);
//            tAvglineBugRecord.setEndDate(endDate);
//            tAvglineBugRecord.setBatchNo(batchNo);
//        }
//        // 4.插入数据
//        netWorthHistoryService.addAvglineBugRecords(avglineBugRecordList);
//    }
//
//    *//**
//     * 函数功能：统计均线的结果;
//     * 
//     * @throws IOException
//     * @throws ServletException
//     *//*
//    @RequestMapping("/addAllAvgLineAnalyseResult")
//    @ResponseBody
//    public ReturnValue addAllAvgLineAnalyseResult(HttpServletRequest request) throws ServletException, IOException {
//        ReturnValue rv = new ReturnValue();
//        try {
//            TAnalyseAvglineResultDto param = new TAnalyseAvglineResultDto();
//            netWorthHistoryService.deleteAnalyseAvgLineResult(param);
//            netWorthHistoryService.addAllAvgLineAnalyseResult();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        rv.setErrorCode(-1);
//        rv.setMessage("sucess");
//        rv.setSuccess(true);
//        return rv;
//    }
//
//    *//**
//     * 函数功能：模拟交易模型分析;
//     * 
//     * @throws IOException
//     * @throws ServletException
//     *//*
//    @RequestMapping("/simulateTradeModelAnalyse")
//    @ResponseBody
//    public ReturnValue simulateTradeModelAnalyse(HttpServletRequest request) throws ServletException, IOException {
//        ReturnValue rv = new ReturnValue();
//        try {
//            String fundCode = "161725";
//            Date startDate = DateTimeUtil.parseDate("2015-05-27 00:00:00", DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
//            Date nowDate = DateTimeUtil.now();
//            // 根据时间遍历
//            while (startDate.getTime() <= nowDate.getTime()) {
//                NetWorthHistory params = new NetWorthHistory();
//                params.setFundCode(fundCode);
//                params.setDateInfo(startDate);
//                List<NetWorthHistory> netWorthHistorieList = netWorthHistoryService.queryNetWorthHistoryInfo(params);
//                startDate = DateTimeUtil.afterDay(startDate, 1);
//                logger.error("日期:" + DateTimeUtil.formatDate(startDate));
//                if (netWorthHistorieList == null || netWorthHistorieList.size() != 1) {
//                    // logger.error("ERROR:日期:"+DateTimeUtil.formatDate(startDate) + " 基金净值数据错误！");
//                    continue;
//                }
//
//                NetWorthHistory netWorthHistory = netWorthHistorieList.get(0);
//                if (netWorthHistory.getAvgLine30() == null) {
//                    continue;
//                }
//                // 卖出
//                TTradeDetail saleDatetial = saleModel(null,netWorthHistory);
//                if (saleDatetial != null) {
//                    logger.error("saleDatetial:" + JSON.toJSONString(saleDatetial));
//                    netWorthHistoryService.insertTradeDetailInfo(saleDatetial);
//                    updateTradeSummary(saleDatetial);
//                }
//
//                // 买入
//                TTradeDetail buyDatetial = buyModel(null,netWorthHistory);
//                if (buyDatetial != null) {
//                    logger.error("buyDatetial:" + JSON.toJSONString(buyDatetial));
//                    netWorthHistoryService.insertTradeDetailInfo(buyDatetial);
//                    updateTradeSummary(buyDatetial);
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        rv.setErrorCode(-1);
//        rv.setMessage("sucess");
//        rv.setSuccess(true);
//        return rv;
//    }
//
//    @RequestMapping("/simulateTradeModelAnalyseFromModel")
//    @ResponseBody
//    public ReturnValue simulateTradeModelAnalyseFromModel(HttpServletRequest request) throws ServletException, IOException {
//        ReturnValue rv = new ReturnValue();
//        try {
//            // 1.查询t_trade_model里面的数据;
//            TTradeModel tradeModel = new TTradeModel();
////            tradeModel.setModelId("0c898a78547e44f8a50b5b095007ffaf");
//            tradeModel.setFundCode("161725");
//            List<TTradeModel> tradeModelList = netWorthHistoryService.queryTradeModelByExample(tradeModel);
//            if (tradeModelList == null || tradeModelList.size() <= 0) {
//                rv.setMessage("tradeModelList 为空.");
//                return rv;
//            }
//
//            String fundCode = null;
//            Date startDate = null;
//            Date endDate = null;
//            for (TTradeModel tTradeModel : tradeModelList) {
//                fundCode = tTradeModel.getFundCode();
//                startDate = tTradeModel.getStartDate();
//                endDate = tTradeModel.getEndDate();
//                endDate = getLastWorkDay(endDate);
//                
//                //清空数据
//                clearTempData(tTradeModel);
//                // 根据时间遍历
//                while (DateTimeUtil.compare(startDate, "<=", endDate)) {
//                    NetWorthHistory params = new NetWorthHistory();
//                    params.setFundCode(fundCode);
//                    params.setDateInfo(startDate);
//                    List<NetWorthHistory> netWorthHistorieList = netWorthHistoryService.queryNetWorthHistoryInfo(params);
//                    
//                    logger.error("日期:" + DateTimeUtil.formatDate(startDate));
//                    if (netWorthHistorieList == null || netWorthHistorieList.size() != 1) {
//                        // logger.error("ERROR:日期:"+DateTimeUtil.formatDate(startDate) + " 基金净值数据错误！");
//                        startDate = DateTimeUtil.afterDay(startDate, 1);
//                        continue;
//                    }
//
//                    NetWorthHistory netWorthHistory = netWorthHistorieList.get(0);
//                    if (netWorthHistory.getAvgLine30() == null) {
//                        startDate = DateTimeUtil.afterDay(startDate, 1);
//                        continue;
//                    }
//                    
//                    if(DateTimeUtil.compare(startDate, "=", endDate)){//最后一天
//                        lastDaySalModel(startDate,endDate,tTradeModel,netWorthHistory);
//                    }else{
//                        // 卖出
//                        TTradeDetail saleDatetial = saleModel(tTradeModel,netWorthHistory);
//                        if (saleDatetial != null) {
//                            logger.error("saleDatetial:" + JSON.toJSONString(saleDatetial));
//                            netWorthHistoryService.insertTradeDetailInfo(saleDatetial);
//                            updateTradeSummary(saleDatetial);
//                            updateTradeModel(saleDatetial);
//                        }
//
//                        // 买入
//                        TTradeDetail buyDatetial = buyModel(tTradeModel,netWorthHistory);
//                        if (buyDatetial != null) {
//                            logger.error("buyDatetial:" + JSON.toJSONString(buyDatetial));
//                            netWorthHistoryService.insertTradeDetailInfo(buyDatetial);
//                            updateTradeSummary(buyDatetial);
//                            updateTradeModel(buyDatetial);
//                        }
//                    }
//                    //日期增长1
//                    startDate = DateTimeUtil.afterDay(startDate, 1);
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        rv.setErrorCode(-1);
//        rv.setMessage("sucess");
//        rv.setSuccess(true);
//        return rv;
//    }
//
//    *//**
//     * @Title: earnMoneyCount   
//     * @Description: 收益计算   
//     * @param:       
//     * @return: void      
//     * @throws
//     *//*
//    public void earnMoneyCount(String modelId){
//        TTradeModel tradeModel = netWorthHistoryService.queryTradeModelById(modelId);
//        if(tradeModel == null || tradeModel.getBuyMoney() <= 0){
//            return;
//        }
//        
//        double earnMoney = tradeModel.getSaleMoney() - tradeModel.getBuyMoney();
//        double earnRate = earnMoney / tradeModel.getBuyMoney();
//        int totalDays = DateTimeUtil.daysBetween(tradeModel.getStartDate(),tradeModel.getEndDate());
//        double yearEarnRate = (earnMoney/tradeModel.getBuyMoney())/totalDays * 365;
//        
//        TTradeModel uptradeModel = new TTradeModel();
//        uptradeModel.setModelId(tradeModel.getModelId());
//        uptradeModel.setEarnMoney(earnMoney);
//        uptradeModel.setEarnRate(earnRate);
//        uptradeModel.setYearEarnRate(yearEarnRate);
//        netWorthHistoryService.updateTradeModelEarnInfo(uptradeModel);
//    }
//    
//    *//**   
//     * @Title: updateTradeModel   
//     * @Description: TODO   
//     * @param:       
//     * @return: void      
//     * @throws   
//     *//*
//    private void updateTradeModel(TTradeDetail tradeDatetial) {
//        TTradeModel tradeModel = new TTradeModel();
//        tradeModel.setModelId(tradeDatetial.getModelId());
//        tradeModel.setFundCode(tradeDatetial.getFundCode());
//        if(tradeDatetial.getTradeType() == 1){
//            tradeModel.setBuyMoney(tradeDatetial.getTradeMoney());
//            tradeModel.setTotalBuyNum(tradeDatetial.getTradeNum());
//        }
//        else if(tradeDatetial.getTradeType() == 2){
//            tradeModel.setSaleMoney(tradeDatetial.getTradeMoney());
//            tradeModel.setTotalSaleNum(tradeDatetial.getTradeNum());
//        }
//        //更新tradeModel
//        netWorthHistoryService.updateTradeModelInfo(tradeModel);
//    }
//    
//    *//**
//     * @Title: clearTempData   
//     * @Description: 清空旧数据   
//     * @param: @param tradeModel      
//     * @return: void      
//     * @throws
//     *//*
//    public void clearTempData(TTradeModel tradeModel){
//        TTradeDetail tradeDetail = new TTradeDetail();
//        tradeDetail.setModelId(tradeModel.getModelId());
//        netWorthHistoryService.deleteTradeDetailByExample(tradeDetail);
//        
//        TTradeSummary tradeSummary = new TTradeSummary();
//        tradeSummary.setModelId(tradeModel.getModelId());
//        tradeSummary.setFundCode(tradeModel.getFundCode());
//        netWorthHistoryService.deleteTradeSummaryByExample(tradeSummary);
//        
//        TTradeModel updateTradeModel = new TTradeModel();
//        updateTradeModel.setBuyMoney(0);
//        updateTradeModel.setTotalBuyNum(0);
//        updateTradeModel.setSaleMoney(0);
//        updateTradeModel.setTotalSaleNum(0);
//        updateTradeModel.setModelId(tradeModel.getModelId());
//        netWorthHistoryService.updateTradeModelByExample(updateTradeModel);
//    }
//    
//    *//**
//     * @Title: getLastWorkDay   
//     * @Description:获取指定日期之前最大有净值的日期；
//     * @param: @return      
//     * @return: Date      
//     * @throws
//     *//*
//    public Date getLastWorkDay(Date endDate){
//        Date reDate = null;
//        if(endDate == null){
//            return null;
//        }
//        NetWorthHistory netWorthHistory = netWorthHistoryService.getMaxDateNetWorthHistory(endDate);
//        if(netWorthHistory != null){
//            reDate = netWorthHistory.getDateInfo();
//        }
//        return reDate;
//    }
//    
//    
//    *//**
//     * @Title: buyModel
//     * @Description: 买入模型
//     * @param: @param netWorthHistory
//     * @return: void
//     * @throws
//     *//*
//    public TTradeDetail buyModel(TTradeModel tradeModel,NetWorthHistory netWorthHistory) {
//        TTradeDetail tradeDetail = null;
//        if (netWorthHistory.getAvgLine30() > netWorthHistory.getNetWorth()
//                && (netWorthHistory.getAvgLine30() - netWorthHistory.getNetWorth()) >= 0.1) {
//            double buyMoney = (netWorthHistory.getAvgLine30() - netWorthHistory.getNetWorth()) * 10000;
//            double buyNum = buyMoney / netWorthHistory.getNetWorth();
//            tradeDetail = new TTradeDetail();
//            tradeDetail.setId(UUIDUtils.get());
//            tradeDetail.setFundCode(netWorthHistory.getFundCode());
//            tradeDetail.setNetWorth(netWorthHistory.getNetWorth());
//            tradeDetail.setTradeMoney(buyMoney);
//            tradeDetail.setTradeNum(buyNum);
//            tradeDetail.setTradeStrage("1");
//            tradeDetail.setTradeType(1);
//            tradeDetail.setStarndValue(netWorthHistory.getAvgLine30());
//            tradeDetail.setDateInfo(netWorthHistory.getDateInfo());
//            tradeDetail.setModelId(tradeModel.getModelId());
//            return tradeDetail;
//        }
//        return tradeDetail;
//    }
//    
//    *//**
//     * @Title: lastDaySalModel   
//     * @Description: 最后一天  
//     * @param:       
//     * @return: void      
//     * @throws
//     *//*
//    public void lastDaySalModel(Date curDate,Date endDate,TTradeModel tTradeModel,NetWorthHistory netWorthHistory){
//        if(DateTimeUtil.compare(curDate, "=", endDate)){//最后一天如果有数据，则按照当天的价格卖完
//            TTradeSummary params = new TTradeSummary();
//            params.setModelId(tTradeModel.getModelId());
//            params.setFundCode(netWorthHistory.getFundCode());
//            TTradeSummary tradeSummary = netWorthHistoryService.queryTradeSummaryByExample(params);
//            if(tradeSummary != null){
//                double saleMoney = tradeSummary.getNowTotalNum() * netWorthHistory.getNetWorth();
//                double saleNum = tradeSummary.getNowTotalNum();
//                //更新模型中的资金数据
//                TTradeModel updateTradeModel = new TTradeModel();
//                updateTradeModel.setModelId(tTradeModel.getModelId());
//                updateTradeModel.setFundCode(tTradeModel.getFundCode());
//                updateTradeModel.setTotalSaleNum(saleNum);
//                updateTradeModel.setSaleMoney(saleMoney);
//                netWorthHistoryService.updateTradeModelInfo(updateTradeModel);
//                //新增TradeDetail卖出
//                TTradeDetail tradeDetail = new TTradeDetail();
//                tradeDetail.setId(UUIDUtils.get());
//                tradeDetail.setFundCode(tTradeModel.getFundCode());
//                tradeDetail.setDateInfo(curDate);
//                tradeDetail.setNetWorth(netWorthHistory.getNetWorth());
//                tradeDetail.setStarndValue(netWorthHistory.getNetWorth());
//                tradeDetail.setTradeType(2);
//                tradeDetail.setTradeMoney(saleMoney);
//                tradeDetail.setTradeNum(saleNum);
//                tradeDetail.setTradeStrage(tTradeModel.getStrategyId());
//                tradeDetail.setModelId(tTradeModel.getModelId());
//                netWorthHistoryService.insertTradeDetailInfo(tradeDetail);
//                //删除TradeSummary
//                TTradeSummary delTrasumary = new TTradeSummary();
//                delTrasumary.setFundCode(tTradeModel.getFundCode());
//                delTrasumary.setModelId(tTradeModel.getModelId());
//                
//                netWorthHistoryService.deleteTradeSummaryByExample(delTrasumary);
//            }
//            //计算收益率
//            earnMoneyCount(tTradeModel.getModelId());
//        }
//    }
//    
//    *//**
//     * @Title: saleModel
//     * @Description: 卖出模型
//     * @param: @param netWorthHistory
//     * @return: void
//     * @throws
//     *//*
//    public TTradeDetail saleModel(TTradeModel tTradeModel,NetWorthHistory netWorthHistory) {
//        TTradeDetail tradeDetail = null;
//        TTradeSummary params = new TTradeSummary();
//        params.setModelId(tTradeModel.getModelId());
//        params.setFundCode(netWorthHistory.getFundCode());
//        TTradeSummary tradeSummary = netWorthHistoryService.queryTradeSummaryByExample(params);
//        if (tradeSummary != null && tradeSummary.getNowTotalNum() > 0 && netWorthHistory.getNetWorth() > tradeSummary.getNowAvgCost()
//                && tradeSummary.getNowTotalNum() > 0) {
//            double rate = (netWorthHistory.getNetWorth() - tradeSummary.getNowAvgCost()) / tradeSummary.getNowAvgCost();
//            logger.error("########：rate:" + rate + " netWorthHistory.getNetWorth():" + netWorthHistory.getNetWorth()
//                    + "  tradeSummary.getNowAvgCost():" + tradeSummary.getNowAvgCost());
//            double saleLot = getSalRate(rate, 1);
//            if (saleLot <= 0) {
//                return null;
//            }
//            tradeDetail = new TTradeDetail();
//            tradeDetail.setId(UUIDUtils.get());
//            tradeDetail.setFundCode(netWorthHistory.getFundCode());
//            tradeDetail.setNetWorth(netWorthHistory.getNetWorth());
//            tradeDetail.setStarndValue(tradeSummary.getNowAvgCost());
//
//            double nowTotalNum = tradeSummary.getNowTotalNum();
//            double saleNum = nowTotalNum * saleLot;
//            double tradeMoney = saleNum * netWorthHistory.getNetWorth();
//            tradeDetail.setTradeMoney(tradeMoney);
//            tradeDetail.setTradeNum(saleNum);
//            tradeDetail.setTradeStrage("1");
//            tradeDetail.setTradeType(2);
//            tradeDetail.setDateInfo(netWorthHistory.getDateInfo());
//            tradeDetail.setModelId(tTradeModel.getModelId());
//            return tradeDetail;
//        }
//        return tradeDetail;
//    }
//
//    *//**
//     * @Title: getSalRate
//     * @Description: TODO
//     * @param: @param rate
//     * @param: @return
//     * @return: double
//     * @throws
//     *//*
//    private double getSalRate(double rate, int straage) {
//        double saleLot = 0;
//        if (straage == 1) {
//            if (0.1 <= rate && rate < 0.2) {// 5%份额
//                saleLot = 0.05;
//            }
//            else if (0.2 <= rate && rate < 0.3) {// 15%
//                saleLot = 0.15;
//            }
//            else if (0.3 <= rate && rate < 0.4) {// 50%
//                saleLot = 0.50;
//            }
//            else if (0.4 <= rate && rate < 0.7) {// 90%
//                saleLot = 0.90;
//            }
//            else if (rate >= 0.7) {// 100%
//                saleLot = 1;
//            }
//            else {
//                return -1;
//            }
//        }
//        else if (straage == 2) {
//            if (0.3 <= rate && rate < 0.4) {// 50%
//                saleLot = 0.20;
//            }
//            else if (0.4 <= rate && rate < 0.7) {// 90%
//                saleLot = 0.80;
//            }
//            else if (rate >= 0.7) {// 100%
//                saleLot = 1;
//            }
//            else {
//                return -1;
//            }
//        }
//
//        return saleLot;
//    }
//
//    *//**
//     * @throws Exception
//     * @Title: updateTradeSummary
//     * @Description: 更新交易汇总信息
//     * @param:
//     * @return: void
//     * @throws
//     *//*
//    public void updateTradeSummary(TTradeDetail tradeDatetial) throws Exception {
//        TTradeSummary params = new TTradeSummary();
//        String fundCode = tradeDatetial.getFundCode();
//        params.setFundCode(fundCode);
//        params.setModelId(tradeDatetial.getModelId());
//        TTradeSummary tradeSummary = netWorthHistoryService.queryTradeSummaryByExample(params);
//
//        double totalNum = 0;
//        double totalMoney = 0;
//        
//        if (tradeDatetial.getTradeType() == 1) {// 买入
//            if (tradeSummary != null) {
//                totalMoney = tradeSummary.getNowTotalCost() + tradeDatetial.getTradeMoney();
//                totalNum = tradeSummary.getNowTotalNum() + tradeDatetial.getTradeNum();
//                // Update
//                tradeSummary.setDateInfo(tradeDatetial.getDateInfo());
//                tradeSummary.setNowAvgCost(totalMoney / totalNum);
//                tradeSummary.setNowTotalCost(totalMoney);
//                tradeSummary.setNowTotalNum(totalNum);
//                tradeSummary.setModelId(tradeDatetial.getModelId());
//                netWorthHistoryService.updateTradeSummayByExample(tradeSummary);
//                logger.error("$$$:updateTradeSummary:UPDATE:" + JSON.toJSONString(tradeSummary));
//            }
//            else {
//                totalNum = tradeDatetial.getTradeNum();
//                totalMoney = tradeDatetial.getTradeMoney();
//                // add
//                TTradeSummary newTradSummary = new TTradeSummary();
//                newTradSummary.setId(UUIDUtils.get());
//                newTradSummary.setFundCode(fundCode);
//                newTradSummary.setDateInfo(tradeDatetial.getDateInfo());
//                newTradSummary.setNowAvgCost(totalMoney / totalNum);
//                newTradSummary.setNowTotalCost(totalMoney);
//                newTradSummary.setNowTotalNum(totalNum);
//                newTradSummary.setModelId(tradeDatetial.getModelId());
//                netWorthHistoryService.insertTradeSummaryInfo(newTradSummary);
//                logger.error("$$$:updateTradeSummary:ADD:" + JSON.toJSONString(newTradSummary));
//            }
//        }
//        else if (tradeDatetial.getTradeType() == 2) {// 卖出
//            if (tradeSummary != null) {
//                totalNum = tradeSummary.getNowTotalNum() - tradeDatetial.getTradeNum();
//                if (totalNum == 0) {
//                    // 删除
//                    TTradeSummary delParam = new TTradeSummary();
//                    delParam.setFundCode(tradeDatetial.getFundCode());
//                    netWorthHistoryService.deleteTradeSummaryByExample(delParam);
//                    return;
//                }
//                //
//                tradeSummary.setDateInfo(tradeDatetial.getDateInfo());
//                tradeSummary.setNowAvgCost(tradeSummary.getNowAvgCost());
//                tradeSummary.setNowTotalCost(totalMoney);
//                tradeSummary.setNowTotalNum(totalNum);
//                tradeSummary.setModelId(tradeDatetial.getModelId());
//                netWorthHistoryService.updateTradeSummayByExample(tradeSummary);
//                logger.error("$$$:updateTradeSummary:UPDATE:" + JSON.toJSONString(tradeSummary));
//            }
//            else {
//                totalNum = tradeDatetial.getTradeNum();
//                totalMoney = tradeDatetial.getTradeMoney();
//                // add
//                TTradeSummary newTradSummary = new TTradeSummary();
//                newTradSummary.setId(UUIDUtils.get());
//                newTradSummary.setFundCode(fundCode);
//                newTradSummary.setDateInfo(tradeDatetial.getDateInfo());
//                newTradSummary.setNowAvgCost(totalMoney / totalNum);
//                newTradSummary.setNowTotalCost(totalMoney);
//                newTradSummary.setNowTotalNum(totalNum);
//                newTradSummary.setModelId(tradeDatetial.getModelId());
//                netWorthHistoryService.insertTradeSummaryInfo(newTradSummary);
//                logger.error("$$$:updateTradeSummary:ADD:" + JSON.toJSONString(newTradSummary));
//            }
//        }
//    }
//
//    *//**
//     * @Title: getBuyMoney
//     * @Description: 获取购买金额
//     * @param: @param buyMode
//     * @param: @param avglineBugRecord
//     * @param: @return
//     * @return: int
//     * @throws
//     *//*
//    public long getBuyMoney(int buyMode, TAvglineBugRecord avglineBugRecord) {
//        long buyMoney = 0;
//        if (buyMode == 1) {// 定值100
//            buyMoney = 100;
//        }
//        if (buyMode == 2) {// 1000倍 0.3113 ---> 3000
//            buyMoney = Math.round(avglineBugRecord.getDiffValue() * 10000);
//        }
//        return buyMoney;
//    }
//
//    *//**
//     * 判断是星期几
//     * 入参：毫秒数
//     * 1~7:星期几；1~7代表星期日到星期六
//     *//*
//    public static int getWeekInfo(Long datel) {
//        int week = Integer.MIN_VALUE;
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            calendar.setTime(new Date(datel));
//            int i = calendar.get(Calendar.DAY_OF_WEEK);
//            week = i;
//            if (i == 1) {
//                System.out.println("今天是星期日");
//            }
//            else {
//                System.out.println("今天是星期" + (i - 1));
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return week;
//    }
//
//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.get(Calendar.YEAR));
//    }
//}
//*/
//}