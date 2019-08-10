package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IExpectFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.HttpclientUtil;


/**
 * 函数功能：marketStation挑选基金
 */
@RestController
@RequestMapping(value = "/marketStation")
public class FundAnalyseController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(FundAnalyseController.class);

	@Autowired
	private INetWorthHistoryService netWorthHistoryService;

	@Autowired
	private IFundSummaryService fundSummaryService;

	@Autowired
	private IIndexMarketSituationService indexMarketSituationService;
	
	@Autowired
	private IExpectFundService iExpectFundService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：分析出有追随基金的指数且满足： 1.十天的平均PE < 10 2.由基金追踪的指数
	 */
	@RequestMapping("/analyseBestIndex")
	@ResponseBody
	public ReturnValue analyseBestIndex(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			// 1.查询所有指数信息
			List<IndexNew> indexList = indexMarketSituationService.queryAllIndexNewInfo(null);
			List<IndexNew> updateList = new ArrayList<IndexNew>();
			int count = 1;
			for (int i = 0; i < indexList.size(); i++) {
				IndexNew tempIndex = indexList.get(i);
				String detailUrl = tempIndex.getDetailUrl();
				if (detailUrl.contains(" ")) {
					IndexNew indexFund = new IndexNew();
					indexFund.setIndexId(tempIndex.getIndexId());
					indexFund.setIsCheck(1);
					updateList.add(indexFund);
					continue;
				}
				System.out.println("[AAAAAAA]:count:" + count + "      detailUrl:" + detailUrl);
				// 使用http请求，获取页面信息，并解析
				String reBody = HttpclientUtil.get(detailUrl.trim(), null);
				Document doc = Jsoup.parse(reBody);
				Elements rows = doc.select("#item").get(0).select("tr");
				String fundCodes = "";
				int len = rows.size();
				System.out.println("MaxLen:" + len);
				List<TIndexFundTemp> upList = new ArrayList<>();
				for (int j = 0; j < len; j++) {
					TIndexFundTemp tempIndexFund = new TIndexFundTemp();
					tempIndexFund.setIndexCode(tempIndex.getIndexCode());
					Element row = rows.get(j);
					tempIndexFund.setFundCode(row.select("td").get(0).text());
					upList.add(tempIndexFund);
				}
				// 插入到数据库中
				indexMarketSituationService.insertIndexFundTemp(upList);
				// 更新这个指数记录
				IndexNew inNew = new IndexNew();
				inNew.setIndexId(tempIndex.getIndexId());
				inNew.setIndexCode(tempIndex.getIndexCode());
				inNew.setTraceFunds("");
				inNew.setIsCheck(1);
				List<IndexNew> tempupdateList = new ArrayList<IndexNew>();
				tempupdateList.add(inNew);
				indexMarketSituationService.updateIndexNewInfo(tempupdateList);
				count++;
			}
			if (updateList.size() > 0) {
				indexMarketSituationService.updateIndexNewInfo(updateList);
			}
			System.out.println("[BBBBB]:count:" + count + "分析完成.....");
			System.out.println("**************************************");
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * 函数功能：分析出有追随基金的指数且满足： 1.十天的平均PE < 10 2.由基金追踪的指数
	 */
	@RequestMapping("/analyseUnderValueExceptFund")
	@ResponseBody
	public ReturnValue analyseUnderValueExceptFund(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();
		System.out.println("******************开始分析********************");

		try {
			//1.取所有低估值的指数信息；
			List<MarketSituationDTO> marketSituationList = indexMarketSituationService.queryUnderValueMarkStationIndex(null);
			int count = 1;
			for (int i = 0; i < marketSituationList.size(); i++) {
				MarketSituationDTO marketSituationTemp = marketSituationList.get(i);
				TIndexFundTemp paramIndex = new TIndexFundTemp();
				BeanUtils.copyProperties(paramIndex, marketSituationTemp);

				List<TIndexFundTemp>  indexTempList = indexMarketSituationService.queryAllIndexFundTemp(paramIndex);
				List<ExpectFund> addExceptFunds = new ArrayList<>();
				for (int j = 0; j < indexTempList.size(); j++) {
					TIndexFundTemp indexFundTempelement = indexTempList.get(j);
					ExpectFund tExceptFund = new ExpectFund();
					BeanUtils.copyProperties(tExceptFund, indexFundTempelement);
					tExceptFund.setType(1);
					addExceptFunds.add(tExceptFund);
				}
				//插入到t_except_fund表中
				iExpectFundService.insertExceptFundInfos(addExceptFunds);
				System.out.println("[BBBBB]:第 count:" + count++ + "次分析.....");
			}
			System.out.println("******************分析完成********************");
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
	
}
