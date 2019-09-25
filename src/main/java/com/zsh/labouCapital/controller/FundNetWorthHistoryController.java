package com.zsh.labouCapital.controller;

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
import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.service.IFundNetWorthHistoryService;


/**
 * 函数功能：基金Controller
 */
@RestController
@RequestMapping(value = "/netWorthHistory")
public class FundNetWorthHistoryController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IFundNetWorthHistoryService fundNetWorthHistoryService;

	@RequestMapping("/queryNetWorthHistoryList")
	@ResponseBody
	public ReturnResponse queryNetWorthHistoryList(@RequestBody FundNetWorthHistoryRequest fundRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(fundRequest));

			int count = fundNetWorthHistoryService.queryNetWorthHistoryCount(fundRequest);
			List<FundNetWorthDto> netHistoryList = null;
			if(count >= 0){
				netHistoryList = fundNetWorthHistoryService.queryNetWorthHistoryList(fundRequest);
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
	
	
}
