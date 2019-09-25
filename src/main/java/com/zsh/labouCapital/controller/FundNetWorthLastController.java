package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.comm.SysConstant;
import com.zsh.labouCapital.controller.request.FundNetWorthLastRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TNetWorthLast;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.dto.FundNetWorthLastDto;
import com.zsh.labouCapital.service.IFundNetWorthLastService;


/**
 * @ClassName:  FundNetWorthLastController   
 * @Description:最新基金数据
 * @author: cj
 * @date:   2019年8月18日 下午9:54:46
 */
@RestController
@RequestMapping(value = "/netWorthLast")
public class FundNetWorthLastController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFundNetWorthLastService fundNetWorthLastService;

	@RequestMapping("/queryNetWorthLastList")
	@ResponseBody
	public ReturnResponse queryNetWorthLastList(@RequestBody FundNetWorthLastRequest fundRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(fundRequest));

			int count = fundNetWorthLastService.queryNetWorthLastCount(fundRequest);
			List<FundNetWorthLastDto> netHistoryList = null;
			if(count >= 0){
				netHistoryList = fundNetWorthLastService.queryNetWorthLastList(fundRequest);
				if(CollectionUtils.isNotEmpty(netHistoryList)){
					resp.setCount(count);
					resp.setData(netHistoryList);
					resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
					resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	/**
	 * @Title: parseNewestGuZhiInfo   
	 * @Description: 解析最新的估值信息;  
	 * @param: @param fundRequest
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/parseNewestGuZhiInfo")
	@ResponseBody
	public ReturnResponse parseNewestGuZhiInfo(@RequestBody FundNetWorthLastRequest fundRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(fundRequest));

			FundNetWorthLastDto netHistoryDto = fundNetWorthLastService.parseNewestGuZhiInfo(fundRequest);
			if(netHistoryDto != null){
				List<FundNetWorthLastDto> reList = new ArrayList<FundNetWorthLastDto>();
				reList.add(netHistoryDto);
				
				resp.setCount(1);
				resp.setData(reList);
				resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
				resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
}
