package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zsh.labouCapital.controller.FundAnalyseController;
import com.zsh.labouCapital.controller.NetWorthHistoryController;
import com.zsh.labouCapital.spider.FundNetWorthSpider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/*.xml"})
public class ControllerTest {
	@Autowired
	private NetWorthHistoryController netWorthController;
	
	@Autowired
	private FundNetWorthSpider netWorthSpider;
	
	@Autowired
	private FundAnalyseController fundAnalyseController;
	
/*	//@Test
	public void calDayGrothRate(){
		netWorthController.calDayGrothRate(null);
	}
	
	*//**
	 * @Title: updateHistoryNetWorth   
	 * @Description: 更新前10天的基金数据
	 * @param:       
	 * @return: void      
	 * @throws
	 *//*
	//@Test
	public void updateHistoryNetWorth(){
		netWorthSpider.updateHistoryNetWorth(null,10);
	}*/
	
	/***************指数分析************/
	/*@Test
	public void analyseBestIndex(){
		fundAnalyseController.analyseBestIndex(null);
	}*/
	
	/***
	 * @Title: analyseExceptFund   
	 * @Description: TODO  
	 * @param:解析出低估值的基金信息
	 * @return: void      
	 * @throws
	 */
	@Test
	public void analyseUnderValueExceptFund(){
		fundAnalyseController.analyseUnderValueExceptFund(null);
	}
	
}
