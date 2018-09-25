package com.zsh.labouCapital.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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
import com.zsh.labouCapital.util.HttpUtil;

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

		// 1.查询所有基金信息
		List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();

		for (int k = 0; k < fundSummaryList.size(); k++) {
			FundSummary paramFund = fundSummaryList.get(k);
			NetWorthHistory paramNetWorth = new NetWorthHistory();
			paramNetWorth.setFundCode(paramFund.getFundCode());

			List<NetWorthHistory> updateList = new ArrayList<>();
			List<NetWorthHistory> nwhList = netWorthHistoryService.queryNetWorthHistoryInfo(paramNetWorth);
			if (nwhList.size() < 1) {
				continue;
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

		int buyType;// 1--每周； 2--每两周； 3--每月
		int interval;// 周期
		Date startTime = null;// 开始时间
		try {
			Date nowDate = new Date();
			double erveryMoney = 1000;

			if (fundNetWorthDTO != null && fundNetWorthDTO.getStartTime() != null) {
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
				if (startTime != null) {
					if (startTime.getTime() <= estabDate.getTime()) {// 开始时间在创建之前，则取创建时间后一月为第一月
						calendar.setTime(estabDate);
						calendar.add(Calendar.MONTH, 1);
						firstDate = calendar.getTime();
					} else {
						firstDate = startTime;
					}
				} else {
					calendar.setTime(estabDate);
					calendar.add(Calendar.MONTH, 1);
					firstDate = calendar.getTime();
				}
				calendar.setTime(firstDate);
				List<IntervalBuy> insertList = new ArrayList<IntervalBuy>();
				while (calendar.getTime().getTime() < nowDate.getTime() * 1000) {//
					String tempDateStr = sdf.format(firstDate);
					System.out.println("****:FundCode:" + fundCode + "  Date:" + tempDateStr);
					// 查询历史数据信息
					NetWorthHistory inNetWorthHistory = netWorthHistoryService.getBuyNetWorthInfo(fundCode,
							tempDateStr);
					if (inNetWorthHistory == null) {
						break;
					}

					IntervalBuy intervalBuy = new IntervalBuy();
					intervalBuy.setFundCode(fundCode);
					intervalBuy.setTotalCost(erveryMoney);
					intervalBuy.setNetWorth(inNetWorthHistory.getNetWorth());
					if (inNetWorthHistory.getNetWorth() != 0) {
						intervalBuy.setTotalNum(erveryMoney / inNetWorthHistory.getNetWorth());
					}
					intervalBuy.setTradeDay(tempDateStr);
					insertList.add(intervalBuy);

					// 根据购买类型和间隔选择
					if (buyType == 1) {// 每一周
						calendar.add(Calendar.WEEK_OF_YEAR, 1);
					} else if (buyType == 2) {// 每两周
						calendar.add(Calendar.WEEK_OF_YEAR, 2);
					} else if (buyType == 3) {// 每月
						calendar.add(Calendar.MONTH, 1);
					}
					calendar.setTime(firstDate);
				}
				// 插入到交易表
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
	 * @Title: getRealTimeNetWorthInfo @Description: 获取实时的净值信息 @param: @return:
	 *         void @throws
	 */
	public void getRealTimeNetWorthInfo(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO) {

	}

	/**
	 * 解析js中的历史净值和历史累计净值数据 "http://fund.eastmoney.com/pingzhongdata/530010.js"
	 * 
	 * @throws ScriptException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/parseJsHistoryAddWorth")
	@ResponseBody
	public void parseJsHistoryAddWorth(HttpServletRequest request, FundNetWorthDTO fundNetWorthDTO)
			throws ParseException, IOException, URISyntaxException, ScriptException {
		// 1.查询所有信息;
		List<FundSummary> fundSummaryList = fundSummaryService.queryFundSummaryNoHsitory();
		parseFundData(fundSummaryList);
	}

	public void parseFundData(List<FundSummary> fundSummaryList)
			throws ParseException, IOException, URISyntaxException, ScriptException {
		System.out.println("******************线程：" + Thread.currentThread().getName() + " 开始累积净值信息****************");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int count = 1;
		for (int j = 0; j < fundSummaryList.size(); j++) {
			List<NameValuePair> params = new ArrayList<>();
			FundSummary tempFund = fundSummaryList.get(j);

			if (StringUtils.isEmpty(tempFund.getHistoryUrl())) {
				continue;
			}

			params.add(new BasicNameValuePair("v", "20180922161408"));
			System.out.println("COUNT:" + count++ + "  URL：" + tempFund.getHistoryUrl());
			String reBody = HttpUtil.get(tempFund.getHistoryUrl(), params);
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("javascript");
			engine.eval(reBody);

			// 1.分析累积净值
			Map<String, Double> addValueMap = new HashMap<>();
			Object array = engine.get("Data_ACWorthTrend");
			JSONArray tempObj = JSONArray.fromObject(array);
			System.out.println(tempObj);

			int len = tempObj.size();
			System.out.println(len);
			for (int i = 0; i < len; i++) {
				NetWorthHistory temValue = new NetWorthHistory();
				JSONObject vaJsonArrary = (JSONObject) tempObj.get(i);
				int size = vaJsonArrary.size();
				System.out.println(vaJsonArrary.size());

				for (int k = 0; k < size; k++) {
					JSONObject valueElem = vaJsonArrary.getJSONObject(k + "");
					// System.out.println(valueElem.get("0") + " " +
					// valueElem.get("1"));
					Long dateTime = valueElem.getLong("0");
					Double addValue = (double) valueElem.getDouble("1");
					Date date = new Date(dateTime);
					String dateStr = sdf.format(date);

					String key = tempFund.getFundCode() + "" + dateStr;
					// System.out.println("key:" + key + " addValue:"+addValue);
					addValueMap.put(key, addValue);
				}
			}
			System.out.println("AAAAAA:addValueMap:" + addValueMap.size());

			// 2.分析历史净值
			List<NetWorthHistory> reList = new ArrayList<>();
			Object hisValue = engine.get("Data_netWorthTrend");
			JSONObject hisValueObj = JSONObject.fromObject(hisValue);
			for (int i = 0; i < hisValueObj.size(); i++) {
				NetWorthHistory temValue = new NetWorthHistory();
				JSONObject vaJsonObject = (JSONObject) hisValueObj.get("" + i);
				Long milSecod = vaJsonObject.getLong("x");
				Double value = vaJsonObject.getDouble("y");
				String equityReturn = vaJsonObject.getString("equityReturn");
				String unitMoney = vaJsonObject.getString("unitMoney");
				int week = getWeekInfo(milSecod);

				String dateInfo = sdf.format(new Date(milSecod));
				temValue.setFundCode(tempFund.getFundCode());
				temValue.setDateInfo(dateInfo);
				temValue.setNetWorth(value);
				if (!StringUtils.isEmpty(equityReturn)) {
					temValue.setEquityReturn(Double.parseDouble(equityReturn));
				}
				temValue.setUnitMoney(unitMoney);
				temValue.setWeekInfo(week);
				String key = tempFund.getFundCode() + "" + dateInfo;
				Double addUpWorth = addValueMap.get(key);
				System.out.println(addUpWorth);
				temValue.setAddUpWorth(addUpWorth);
				reList.add(temValue);
			}
			System.out.println("reList:Size:" + reList.size() + "  " + reList);
			// 2.插入到到数据库
			netWorthHistoryService.updateOrinsertNetWorth(reList);
		}
	}

	/**
	 * 判断是星期几 入参：毫秒数 1~7:星期几；1~7代表星期日到星期六
	 */
	public static int getWeekInfo(Long datel) {
		int week = Integer.MIN_VALUE;
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			calendar.setTime(new Date(datel));
			int i = calendar.get(Calendar.DAY_OF_WEEK);
			week = i;
			if (i == 1) {
				System.out.println("今天是星期日");
			} else {
				System.out.println("今天是星期" + (i - 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}

}
