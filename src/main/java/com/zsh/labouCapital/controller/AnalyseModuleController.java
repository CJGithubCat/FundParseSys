/*package com.zsh.labouCapital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IIndexMarketSituationService;

@Controller
@RequestMapping("/analyseModule")
public class AnalyseModuleController {

	@Autowired
    private IIndexMarketSituationService indexMarketSituationService;

	*//**
	 * 函数功能：分析有基金追随的指数信息;
	 * 
	 * @throws IOException
	 * @throws ServletException
	 *//*
	@RequestMapping("/ananlyIndexFund")
	@ResponseBody
	public ReturnValue ananlyIndexFund(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ReturnValue rv = new ReturnValue();

		try {
		    //1.获取所有指数信息
		    indexMarketSituationService.queryAllIndexNewInfo(null);
		    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		
		rv.setErrorCode(-1);
		rv.setMessage("sucess");
		rv.setSuccess(true);
		return rv;
	}
}
*/