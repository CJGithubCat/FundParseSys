package com.zsh.labouCapital.job;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zsh.labouCapital.controller.LoginController;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.util.email.EmailUtil;

public class FundOperJob{
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IFundSummaryService fundSummaryService;
	
	private static int counter = 0;
    protected void execute1()  {
    	/*logger.info("***********定时任务启动********");
        long ms = System.currentTimeMillis();  
        System.out.println("\t\t" + new Date(ms));  
        System.out.println("(" + counter++ + ")");  
        try {
        	//1.查询数据库基金信息；
        	FundSummary fundSummary = fundSummaryService.getFundSummaryByCode("001632");
        	System.out.println(fundSummary);
        	if(fundSummary != null){        		
        		//2.使用爬虫获取最新的数据信息；
        		
        		//3.分析按照相关策略进行分析；
        		
        		
        		//4.发送邮件信息;
        		//String subJect = "Fund提示";
        		//MimeMessage message = EmailUtil.createSimpleMail(subJect, "");
        		//EmailUtil.sendEmail(message);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }  
}
