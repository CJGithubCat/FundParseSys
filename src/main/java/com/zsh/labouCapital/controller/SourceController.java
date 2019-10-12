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
import com.zsh.labouCapital.controller.request.SourceRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;
import com.zsh.labouCapital.dao.dto.TWebsiteInfo;
import com.zsh.labouCapital.service.IWebSiteService;


/**
 * 函数功能：资源Controller
 */
@RestController
@RequestMapping(value = "/source")
public class SourceController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IWebSiteService webSiteServiceImpl;

	/**
	 * @Title: queryFundList   
	 * @Description: 查询资源列表
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnResponse      
	 * @throws
	 */
	@RequestMapping("/querySourceList")
	@ResponseBody
	public ReturnResponse querySourceList(@RequestBody SourceRequest sourceRequest) {
		ReturnResponse resp = new ReturnResponse();
		try {
			logger.error("参数:" + JSON.toJSONString(sourceRequest));
			int count  = webSiteServiceImpl.queryWebSiteCount(sourceRequest);
			List<TWebsiteInfo> funList = null;
			if(count >= 0){
				funList = webSiteServiceImpl.queryWebSiteList(sourceRequest);
			}
			resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
			resp.setCount(count);
			resp.setData(funList);
			resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			resp.setCode(SysConstant.SYS_RE_FAIL_CODE);
            resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
		}
		return resp;
	}
	
	/**
     * @Title: queryFundList   
     * @Description: 查询资源列表
     * @param: @param request
     * @param: @return      
     * @return: ReturnResponse      
     * @throws
     */
    @RequestMapping("/addSource")
    @ResponseBody
    public ReturnResponse addSource(@RequestBody SourceRequest sourceRequest) {
        ReturnResponse resp = new ReturnResponse();
        try {
            logger.error("参数:" + JSON.toJSONString(sourceRequest));
            webSiteServiceImpl.addSource(sourceRequest);
            
            resp.setCode(SysConstant.SYS_RE_SUCCESS_CODE);
            resp.setMsg(SysConstant.SYS_RE_SUCCESS_MSY);
        } catch (Exception e) {
            e.printStackTrace();
            
            resp.setCode(SysConstant.SYS_RE_FAIL_CODE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
}