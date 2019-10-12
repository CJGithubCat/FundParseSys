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
import com.zsh.labouCapital.controller.request.JobRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TJob;
import com.zsh.labouCapital.service.IJobService;


/**
 * 函数功能：JobController
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IJobService JobServiceImpl;

	/**
	 * @Title: queryJobList   
	 * @Description: 查询基金列表西悉尼  
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/queryJobList")
	@ResponseBody
	public ReturnResponse queryJobList(@RequestBody JobRequest JobRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(JobRequest));
			int count  = JobServiceImpl.queryJobCount(JobRequest);
			List<TJob> funList = null;
			if(count >= 0){
				funList = JobServiceImpl.queryJobList(JobRequest);
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