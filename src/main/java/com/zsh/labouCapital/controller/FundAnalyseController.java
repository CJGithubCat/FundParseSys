package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.HttpUtil;

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
	private ILoggerService iloggerService;

	/**
	 * 函数功能：分析出有追随基金的指数且满足：
	 * 1.十天的平均PE < 10
	 * 2.由基金追踪的指数
	 */
	@RequestMapping("/analyseBestIndex")
	@ResponseBody
	public ReturnValue analyseBestIndex(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			//1.查询所有指数信息
			List<IndexNew> indexList = indexMarketSituationService.queryAllIndexNewInfo(null);
			List<IndexNew> updateList = new ArrayList<IndexNew>();
			for (int i = 0; i < indexList.size(); i++) {
				IndexNew tempIndex = indexList.get(i);
				String detailUrl = tempIndex.getDetailUrl();
				if(detailUrl.contains(" ")){
					IndexNew indexFund = new IndexNew();
					indexFund.setIndexId(tempIndex.getIndexId());
					indexFund.setIsCheck(1);
			        updateList.add(indexFund);
					continue;
				}
				System.out.println("index:" + i + "detailUrl:" + detailUrl );
				//使用http请求，获取页面信息，并解析
				String reBody = HttpUtil.get(detailUrl.trim(), null);
		        Document doc = Jsoup.parse(reBody);
		        Elements rows = doc.select("#item").get(0).select("tr");
		        String fundCodes = "";
		        int len = rows.size();
		        if(len >= 10){
		        	System.out.println("MaxLen:" + rows.size());
		        	
		        	for(int j=0; j <= 5;j++){
			        	Element row = rows.get(j);
			        	fundCodes +=  row.select("td").get(0).text() + "," + fundCodes ;
			        }
		        	fundCodes = "LEN:"+ rows.size() + ":"+ fundCodes;
		        }else{
		        	System.out.println("MaxLen:" + len);
		        	for(int j=0; j < len;j++){
			        	Element row = rows.get(j);
			        	fundCodes +=  row.select("td").get(0).text() + "," + fundCodes ;
			        }
		        }
		        
	        	IndexNew indexFund = new IndexNew();
		        indexFund.setIndexId(tempIndex.getIndexId());
		        indexFund.setTraceFunds(fundCodes);
		        indexFund.setIsCheck(1);
		        updateList.add(indexFund);
		        if(updateList.size() >= 5){
					indexMarketSituationService.updateIndexNewInfo(updateList);
			        System.out.println("AAAAAAAAA:index:" + i + " updateList：" + updateList.size());
			        updateList = null;
			        updateList = new ArrayList<IndexNew>();
				}
			}
			if(updateList.size() > 0){
				indexMarketSituationService.updateIndexNewInfo(updateList);
			}
			
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
}
