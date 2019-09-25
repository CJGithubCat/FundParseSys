package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.comm.SysConstant;
import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IExpectFundService;
import com.zsh.labouCapital.service.IFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.HttpclientUtil;


/**
 * 函数功能：基金Controller
 */
@RestController
@RequestMapping(value = "/fund")
public class FundController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFundService fundServiceImpl;

	/**
	 * @Title: queryFundList   
	 * @Description: 查询基金列表西悉尼  
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/queryFundList")
	@ResponseBody
	public ReturnResponse queryFundList(@RequestBody FundRequest fundRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(fundRequest));
			int count  = fundServiceImpl.queryFundCount(fundRequest);
			List<TFund> funList = null;
			if(count >= 0){
				funList = fundServiceImpl.queryFundList(fundRequest);
			}
			resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
			resp.setCount(count);
			resp.setData(funList);
			resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}