package test.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.script.ScriptException;

import org.apache.http.ParseException;
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
public class NetWorthHistoryControllerTest {
	@Autowired
	private NetWorthHistoryController netWorthController;
	
	@Autowired
	private FundNetWorthSpider netWorthSpider;
	
	@Autowired
	private FundAnalyseController fundAnalyseController;
	
	@Test
	public void parseJsHistoryAddWorth() throws ParseException, IOException, URISyntaxException, ScriptException{
		netWorthController.parseAllJsHistoryAddWorth(null,null);
	}
	
	@Test
    public void parseSpecJsHistoryAddWorth() throws ParseException, IOException, URISyntaxException, ScriptException{
        netWorthController.parseSpecJsHistoryAddWorth(null,null);
    }
	
	/**
	 * @Title: calDayGrothRate   
	 * @Description: TODO  
	 * @param:解析日增长率       
	 * @return: void      
	 * @throws
	 */
	@Test
	public void calDayGrothRate(){
		netWorthController.calDayGrothRate(null);
	}
}
