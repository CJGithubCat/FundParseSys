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
import com.zsh.labouCapital.controller.request.IndexRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dao.dto.TIndex;
import com.zsh.labouCapital.dto.MarketSituationDTO;
import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.entity.IndexNew;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.TIndexFundTemp;
import com.zsh.labouCapital.service.IExpectFundService;
import com.zsh.labouCapital.service.IFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.IIndexService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.HttpclientUtil;


/**
 * 函数功能：指数Controller
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IIndexService indexServiceImpl;

	/**
	 * @Title: queryFundList   
	 * @Description: 查询基金列表西悉尼  
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/queryIndexList")
	@ResponseBody
	public ReturnResponse queryIndexList(@RequestBody IndexRequest indexRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(indexRequest));
			int count  = indexServiceImpl.queryIndexCount(indexRequest);
			List<TIndex> indexList = null;
			if(count >= 0){
			    indexList = indexServiceImpl.queryIndexList(indexRequest);
			}
			resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
			resp.setCount(count);
			resp.setData(indexList);
			resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping("/addIndexInfo")
    @ResponseBody
    public ReturnResponse addIndexInfo(@RequestBody IndexRequest indexRequest) {
        ReturnResponse resp = new ReturnResponse();
        try {
            logger.error("参数:" + JSON.toJSONString(indexRequest));
            indexServiceImpl.addIndexInfo(indexRequest);

            resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
            resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
            
        } catch (Exception e) {
            resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
	
}