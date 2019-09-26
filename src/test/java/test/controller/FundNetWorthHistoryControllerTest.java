package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.controller.FundNetWorthHistoryController;
import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/*.xml"})
public class FundNetWorthHistoryControllerTest {
	@Autowired
	private FundNetWorthHistoryController netWorthController;
	
	/***
	 * @Title: analyseExceptFund   
	 * @Description: TODO  
	 * @param:解析出低估值的基金信息
	 * @return: void      
	 * @throws
	 */
	@Test
	public void queryFundNetWorthHistoryList(){
		
		try {
			FundNetWorthHistoryRequest fundRequest = new FundNetWorthHistoryRequest();
			fundRequest.setFundCode("000008");
			fundRequest.setStartDate("2013-03-22 00:00:00");
			fundRequest.setEndDate("2013-04-22 00:00:00");
			ReturnResponse resp = netWorthController.queryFundNetWorthHistoryList(null, fundRequest);
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
