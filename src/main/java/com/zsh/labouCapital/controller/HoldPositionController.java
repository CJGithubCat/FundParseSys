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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.TExceptFund;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IExceptFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.HttpUtil;

import net.sf.jsqlparser.statement.create.table.Index;

/**
 * 函数功能：marketStation挑选基金
 */
@RestController
@RequestMapping(value = "/holdPosition")
public class HoldPositionController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HoldPositionController.class);

	@Autowired
	private INetWorthHistoryService netWorthHistoryService;

	@Autowired
	private IFundSummaryService fundSummaryService;

	@Autowired
	private IIndexMarketSituationService indexMarketSituationService;
	
	@Autowired
	private IExceptFundService iExceptFundService;
	
	

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * @Title: analyBuyStrategy   
	 * @Description: TODO   
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnValue      
	 * @throws
	 */
	@RequestMapping("/analyBuyStrategy")
	@ResponseBody
	public ReturnValue analyBuyStrategy(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			// 1.查询所有指数信息
			List<IndexNew> indexList = indexMarketSituationService.queryAllIndexNewInfo(null);
			List<IndexNew> updateList = new ArrayList<IndexNew>();
			int count = 1;
			
			
			System.out.println("[BBBBB]:count:" + count + "分析完成.....");
			System.out.println("**************************************");
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
}
