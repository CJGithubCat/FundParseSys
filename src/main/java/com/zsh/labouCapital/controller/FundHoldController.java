package com.zsh.labouCapital.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.comm.SysConstant;
import com.zsh.labouCapital.controller.request.FundHoldRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.THoldFund;
import com.zsh.labouCapital.dao.dto.THoldFundSummary;
import com.zsh.labouCapital.service.IFundHoldService;


/**
 * 函数功能：持有基金Controller
 */
@RestController
@RequestMapping(value = "/fundHold")
public class FundHoldController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFundHoldService fundHoldServiceImpl;

	/**
	 * @Title: queryFundList   
	 * @Description: 查询基金列表西悉尼  
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/queryFundHoldList")
	@ResponseBody
	public ReturnResponse queryFundHoldList(@RequestBody FundHoldRequest fundRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(fundRequest));
			int count  = fundHoldServiceImpl.queryFundHoldCount(fundRequest);
			List<THoldFund> funList = null;
			if(count >= 0){
				funList = fundHoldServiceImpl.queryFundHoldList(fundRequest);
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
	
	/**
	 * @Title: uploadFundHoldSummary   
	 * @Description: 上传持有基金汇总数据  
	 * @param: @param fundRequest
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/uploadFundHoldSummary")
	@ResponseBody
	public ReturnResponse uploadFundHoldSummary(@RequestParam(value="file") MultipartFile summaryFile,HttpServletRequest request) {
		ReturnResponse resp = new ReturnResponse();
		try {
			if(summaryFile != null){
				Map<String, Object> reMap = fundHoldServiceImpl.parseFundHoldList(summaryFile);
				if(reMap != null){
					THoldFundSummary holdFundSummary = (THoldFundSummary) reMap.get("holdFundSummary");
					if(holdFundSummary != null){
						fundHoldServiceImpl.updateOrAddHoldFundSummary(holdFundSummary);
					}
					List<THoldFund> holdFundList = (List<THoldFund>)reMap.get("holdFundList");
					if(CollectionUtils.isNotEmpty(holdFundList)){
						fundHoldServiceImpl.updateOrAddHoldFund(holdFundList);
					}
				}
			}
			resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
			resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}