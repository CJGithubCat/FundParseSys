package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zsh.labouCapital.controller.FundController;
import com.zsh.labouCapital.controller.FundNetWorthHistoryController;
import com.zsh.labouCapital.controller.FundNetWorthLastController;
import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.controller.request.FundNetWorthLastRequest;
import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.controller.response.ReturnResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/*.xml"})
public class FundControllerTest {
	@Autowired
	private FundController netWorthController;
	@Autowired
	private FundNetWorthLastController lasetNetController;
	
	/***
	 * @Title: analyseExceptFund   
	 * @Description: TODO  
	 * @param:解析出低估值的基金信息
	 * @return: void      
	 * @throws
	 */
	@Test
	public void queryFundList(){
		try {
			FundRequest fundRequest = new FundRequest();
//			fundRequest.setFundCode("000008");
			fundRequest.setFundName("嘉实");
			ReturnResponse resp = netWorthController.queryFundList(fundRequest);
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * @Title: analyseExceptFund   
	 * @Description: TODO  
	 * @param:解析出低估值的基金信息
	 * @return: void      
	 * @throws
	 */
	@Test
	public void parseNewestGuZhiInfo(){
		try {
			FundNetWorthLastRequest fundRequest = new FundNetWorthLastRequest();
			fundRequest.setFundCode("000008");
			fundRequest.setGuzhiUrl("http://fundgz.1234567.com.cn/js/000008.js?rt=");
			ReturnResponse resp = lasetNetController.parseNewestGuZhiInfo(fundRequest);
			System.out.println(JSON.toJSONString(resp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
