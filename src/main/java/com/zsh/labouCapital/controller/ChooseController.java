package com.zsh.labouCapital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.comm.SysConstant;
import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.service.IFundService;


/**
 * 函数功能：JobController
 */
@RestController
@RequestMapping(value = "/choose")
public class ChooseController {
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