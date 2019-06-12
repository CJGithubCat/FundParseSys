package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.dto.FundNetWorthDTO;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.entity.IntervalBuy;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.HttpclientUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 函数功能：NetWorthHistory
 */
@RestController
@RequestMapping(value = "/netWorth")
public class NetWorthHistoryController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(NetWorthHistoryController.class);

	@Autowired
	private INetWorthHistoryService netWorthHistoryService;

	@Autowired
	private IFundSummaryService fundSummaryService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：统计当前的日增长率
	 */
	@RequestMapping("/calDayGrothRate")
	@ResponseBody
	public ReturnValue calDayGrothRate(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			// 1.查询所有基金信息
			List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();

			for (int k = 0; k < fundSummaryList.size(); k++) {
				FundSummary paramFund = fundSummaryList.get(k);
				NetWorthHistory paramNetWorth = new NetWorthHistory();
				paramNetWorth.setFundCode(paramFund.getFundCode());

				List<NetWorthHistory> updateList = new ArrayList<>();
				List<NetWorthHistory> nwhList = netWorthHistoryService.queryNetWorthHistoryInfo(paramNetWorth);
				if (nwhList.size() < 1) {
					rv.setSuccess(true);
					rv.setDatas(null);
					rv.setMessage("未查询到基金净值数据，计算失败!");
					return rv;
				}
				for (int i = 0, j = 1; i < nwhList.size() - 1; i++, j++) {
					NetWorthHistory lowValue = nwhList.get(i);
					NetWorthHistory highValue = nwhList.get(j);

					if (highValue.getNetWorth() == 0) {
						System.out.println("*********为0数据：" + highValue);
						continue;
					}
					double growRate = (lowValue.getNetWorth() - highValue.getNetWorth()) / highValue.getNetWorth();

					NetWorthHistory updateInfo = new NetWorthHistory();
					updateInfo.setId(lowValue.getId());
					updateInfo.setFundCode(lowValue.getFundCode());
					updateInfo.setNetworthDaygrowRate(growRate);
					updateList.add(updateInfo);
				}
				// 更新数据库中的日增长率
				netWorthHistoryService.updateNetWorthHistorys(updateList);
			}
			rv.setSuccess(true);
			rv.setDatas(null);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：分析定投模型
	 */
	@RequestMapping("/analyseIntervalBuyModule")
	@ResponseBody
	public ReturnValue analyseIntervalBuyModule(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {
		ReturnValue rv = new ReturnValue();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int buyType;//1--每周； 2--每两周； 3--每月
        int interval;//周期
        Date startTime = null;//开始时间
		try {
			Date nowDate = new Date();
			double erveryMoney = 1000;
			
			if(fundNetWorthDTO != null && fundNetWorthDTO.getStartTime() != null){
			    startTime = fundNetWorthDTO.getStartTime();
			}
			buyType = fundNetWorthDTO.getBuyType();
			interval = fundNetWorthDTO.getInterval();

			// 1.查询所有基金信息
			List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();

			for (int k = 0; k < fundSummaryList.size(); k++) {
				FundSummary paramFund = fundSummaryList.get(k);
				String fundCode = paramFund.getFundCode();
				String estabDateStr = paramFund.getEstablishDate();
				Date estabDate = sdf.parse(estabDateStr);
				
				Date firstDate = null;
				Calendar calendar = Calendar.getInstance();
				if(startTime!= null){
				    if(startTime.getTime() <= estabDate.getTime()){//开始时间在创建之前，则取创建时间后一月为第一月
	                    calendar.setTime(estabDate);
	                    calendar.add(Calendar.MONTH, 1);
	                    firstDate = calendar.getTime();
	                }else{
	                    firstDate = startTime;
	                }
				}else{
                    calendar.setTime(estabDate);
                    calendar.add(Calendar.MONTH, 1);
                    firstDate = calendar.getTime();
				}
				calendar.setTime(firstDate);
				List<IntervalBuy> insertList = new ArrayList<IntervalBuy>();
				while (calendar.getTime().getTime() < nowDate.getTime()*1000) {//
					String tempDateStr = sdf.format(firstDate);
					System.out.println("****:FundCode:" + fundCode + "  Date:" + tempDateStr);
					//查询历史数据信息
					NetWorthHistory inNetWorthHistory = netWorthHistoryService.getBuyNetWorthInfo(fundCode,tempDateStr);
					if(inNetWorthHistory == null){
						break;
					}
					
					IntervalBuy intervalBuy = new IntervalBuy();
					intervalBuy.setFundCode(fundCode);
					intervalBuy.setTotalCost(erveryMoney);
					intervalBuy.setNetWorth(inNetWorthHistory.getNetWorth());
					if(inNetWorthHistory.getNetWorth() != 0){
						intervalBuy.setTotalNum(erveryMoney/inNetWorthHistory.getNetWorth());
					}
					intervalBuy.setTradeDay(tempDateStr);
					insertList.add(intervalBuy);
					
					//根据购买类型和间隔选择
                    if(buyType == 1){//每一周
                        calendar.add(Calendar.WEEK_OF_YEAR, 1);
                    }else if(buyType == 2){//每两周
                        calendar.add(Calendar.WEEK_OF_YEAR, 2);
                    }else if(buyType == 3){//每月
                        calendar.add(Calendar.MONTH, 1);
                    }
                    calendar.setTime(firstDate);
				}
				//插入到交易表
				netWorthHistoryService.addIntervalTradeInfo(insertList);
				System.out.println("**:" + insertList);///////////////
			}
			rv.setSuccess(true);
			rv.setDatas(null);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * @Title: getRealTimeNetWorthInfo   
	 * @Description: 获取实时的净值信息
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	public void getRealTimeNetWorthInfo(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO){
	    
	}
	
	/**
     * 解析js中的历史累计净值数据
     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
     */
	@RequestMapping("/parseSpecJsHistoryAddWorth")
    @ResponseBody
    public  void parseSpecJsHistoryAddWorth(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO){
	    System.out.println("******************开始分析累积净值信息****************");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date updateDateLine = DateTimeUtil.afterDay(new Date(), 10);
        try {
            //1.查询所有信息;
            FundSummary param = new FundSummary();
            param.setFundCode("161725");
            List<FundSummary> fundSummaryList = fundSummaryService.queryFundSummaryByEample(param);
            for(int j=0; j < fundSummaryList.size();j++){
                List<NetWorthHistory> reList = new ArrayList<>();
                List<NameValuePair> params = new ArrayList<>();
                FundSummary tempFund = fundSummaryList.get(j);
                                
                String timeStamp = this.generateTimeStamp(new Date());
                params.add(new BasicNameValuePair("v",timeStamp));
                String reBody = HttpclientUtil.get(tempFund.getHistoryUrl(), params);
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("javascript");
                engine.eval(reBody);
                
                Object array = engine.get("Data_netWorthTrend");
                JSONArray tempObj1 = JSONArray.fromObject(array);
                String tempObj1Str = tempObj1.toString().replace("},", "}},{");
                com.alibaba.fastjson.JSONArray temArr = (com.alibaba.fastjson.JSONArray) com.alibaba.fastjson.JSONArray.parse(tempObj1Str);
                System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(temArr));
                
                int len = temArr.size();
                for (int i = 0; i < len; i++) {
                    NetWorthHistory temValue = new NetWorthHistory();
                    com.alibaba.fastjson.JSONObject jsonTemp = (com.alibaba.fastjson.JSONObject) temArr.getJSONObject(i).getJSONObject(""+i);
                    
                    Date dateTime =  jsonTemp.getDate("x");
                    //只更新最近几天的数据;
                    if(dateTime.getTime() > updateDateLine.getTime()){
                        continue;
                    }
                    
                    double netWorth = (double) jsonTemp.getDoubleValue("y");
                    temValue.setFundCode(tempFund.getFundCode());
                    temValue.setNetWorth(netWorth);
                    temValue.setDateInfo(dateTime);
                    temValue.setDateInfoStr(sdf.format(dateTime));
                    temValue.setDateCreate(sdf.format(new Date()));
                    reList.add(temValue);
                }
                System.out.println("AAAAAA:"+com.alibaba.fastjson.JSONObject.toJSONString(reList));
                //2.更新到数据库
                netWorthHistoryService.updateNetWorthHistorys(reList);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * @Title: genTimeStamp   
	 * @Description: TODO   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String generateTimeStamp(Date dateTime){
	    if(dateTime == null){
	        dateTime = new Date();
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    return sdf.format(dateTime);
	}
	
	/**
     * 解析js中的历史累计净值数据
     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
     */
    @RequestMapping("/parseAllJsHistoryAddWorth")
    @ResponseBody
    public  void parseAllJsHistoryAddWorth(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO){
        System.out.println("******************开始分析累积净值信息****************");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //1.查询所有信息;
            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
            
            for(int j=0; j < fundSummaryList.size();j++){
                List<NetWorthHistory> reList = new ArrayList<>();
                List<NameValuePair> params = new ArrayList<>();
                FundSummary tempFund = fundSummaryList.get(j);
                
                params.add(new BasicNameValuePair("v","20190611161408"));
                String reBody = HttpclientUtil.get(tempFund.getHistoryUrl(), params);
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("javascript");
                engine.eval(reBody);
                Object array = engine.get("Data_ACWorthTrend");
                JSONArray tempObj = JSONArray.fromObject(array);
                int len = tempObj.size();
                for (int i = 0; i < len; i++) {
                    NetWorthHistory temValue = new NetWorthHistory();
                    JSONArray vaJsonArrary = (JSONArray) tempObj.get(i);
                    
                    Long dateTime =  vaJsonArrary.getLong(0);
                    Double addValue = (double) vaJsonArrary.getDouble(1);
                    
                    Date date = new Date(dateTime);
                    String dateStr = sdf.format(date);

                    temValue.setFundCode(tempFund.getFundCode());
                    temValue.setDateInfo(date);
                    temValue.setAddUpWorth(addValue);
                    reList.add(temValue);
                }
                System.out.println("AAAAAA:"+reList.size());
                //2.更新到数据库
                netWorthHistoryService.updateNetWorthHistorys(reList);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
     * 解析js中的信息
     * "http://fund.eastmoney.com/pingzhongdata/530010.js"
     */
	@RequestMapping("/parseJsNetWorth")
    @ResponseBody
    public void parseJsNetWorth(HttpServletRequest request,Date startDate){
        List<NameValuePair> params = new ArrayList<>();
        List<NetWorthHistory> reList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
          //1.查询所有信息;
            long startLong = startDate.getTime();
            System.out.println("开始时间:"+sdf.format(startDate));
            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
            for(int j=0; j<fundSummaryList.size();j++){
                FundSummary fundTemp = fundSummaryList.get(j);
                params.add(new BasicNameValuePair("v","20180925161408"));
                String reBody = HttpclientUtil.get(fundTemp.getHistoryUrl(), params);
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("javascript");
                engine.eval(reBody);
                Object array = engine.get("Data_netWorthTrend");
                JSONArray tempObj = JSONArray.fromObject(array);
                System.out.println(tempObj);
                int len = tempObj.size();
                for (int i = 0; i < len; i++) {
                    NetWorthHistory temValue = new NetWorthHistory();
                    JSONObject vaJsonObject = (JSONObject)tempObj.get(i);
                    Long milSecod = vaJsonObject.getLong("x");
                    if(milSecod <= startLong){//过滤大于指定日期的数据
                        continue;
                    }
                    Double value = vaJsonObject.getDouble("y");
                    String equityReturn = vaJsonObject.getString("equityReturn");
                    String unitMoney = vaJsonObject.getString("unitMoney");
                    int week = getWeekInfo(milSecod);
                    
                    String dateInfo = sdf.format(new Date(milSecod));
                    temValue.setFundCode(fundTemp.getFundCode());
                    temValue.setDateInfo(new Date(milSecod));
                    temValue.setNetWorth(value);
                    if(!StringUtils.isEmpty(equityReturn)){
                        temValue.setEquityReturn(Double.parseDouble(equityReturn));
                    }
                    temValue.setUnitMoney(unitMoney);
                    temValue.setWeekInfo(week);
                    reList.add(temValue);
                }
                
                netWorthHistoryService.addOrUpdateNetWorth(reList);
                System.out.println("AAAAAA:"+reList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
     * 判断是星期几
     * 入参：毫秒数
     * 1~7:星期几；1~7代表星期日到星期六
     */
    public static int getWeekInfo(Long datel){
        int week = Integer.MIN_VALUE;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            calendar.setTime(new Date(datel));
            int i =calendar.get(Calendar.DAY_OF_WEEK);
            week = i;
            if(i == 1){
                System.out.println("今天是星期日");
            }else{
                System.out.println("今天是星期"+(i-1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return week;
    }
}
