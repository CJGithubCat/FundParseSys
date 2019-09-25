/*package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.comm.SysOperateEnum;
import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.entity.FundRealTimeNetWorthDTO;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.entity.HoldPosition;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.StrategyAdviceInfo;
import com.zsh.labouCapital.service.IExpectFundService;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.IHoldPositionService;
import com.zsh.labouCapital.service.IIndexMarketSituationService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;
import com.zsh.labouCapital.util.email.EmailUtil;
import com.zsh.labouCapital.util.fund.FundRealTimeUtil;

*//**
 * 函数功能：marketStation挑选基金
 *//*
@RestController
@RequestMapping(value = "/holdPosition")
public class HoldPositionController{
	private static Logger logger = LoggerFactory.getLogger(HoldPositionController.class);

	@Autowired
	private INetWorthHistoryService netWorthHistoryService;

	@Autowired
	private IFundSummaryService fundSummaryService;

	@Autowired
	private IIndexMarketSituationService indexMarketSituationService;
	
	@Autowired
	private IExpectFundService iExpectFundService;
	
	@Autowired
    private IHoldPositionService holdPositionService;
	
	

	@Autowired
	private ILoggerService iloggerService;

	
	*//**
     * @Title: analyBuyStrategy   
     * @Description: TODO   
     * @param: @param request
     * @param: @return      
     * @return: ReturnValue      
     * @throws
     *//*
    @RequestMapping("/addHoldPositionInfo")
    @ResponseBody
    public ReturnValue addHoldPositionInfo(HttpServletRequest request,HoldPosition holdPosition) {
        ReturnValue rv = new ReturnValue();

        try {
            if(holdPosition == null){
                rv.setSuccess(false);
                rv.setMessage("参数为空！");
                return rv;
            }
            //1.查询之前所有的库存记录；
            HoldPosition holdPositionParam = new HoldPosition();
            holdPositionParam.setFundCode(holdPosition.getFundCode());
            List<HoldPosition> holdPositionList = holdPositionService.queryHoldPositionList(holdPositionParam);
            //2.获取实时的净值信息;
            FundSummary fundSummary = fundSummaryService.getFundSummaryByCode(holdPosition.getFundCode());
            FundRealTimeNetWorthDTO fundRealNetWorth = FundRealTimeUtil.getFundRealTimeNetWorth(fundSummary);
            //3.计算当前买入的份额
            Double buyMoney = holdPosition.getBuyMoney();
            if(buyMoney == null){
                rv.setSuccess(false);
                rv.setMessage("参数为空！");
                return rv;
            }
            if(fundRealNetWorth.getNowNetWorth() <=0){
                rv.setSuccess(false);
                rv.setMessage("实时净值非法！");
                return rv;
            }
            double buyNum = buyMoney/fundRealNetWorth.getNowNetWorth();
            holdPosition.setBuyNum(buyNum);//当前买入的数量
            //4.计算其他参数
            double lastTotalMoney = 0;
            double lastTotalNum = 0;
            for (int i = 0; i < holdPositionList.size(); i++) {
                HoldPosition holdPositionElem = holdPositionList.get(i);
                if(holdPositionElem.getType() == 1){//持有的才计算
                    lastTotalMoney += holdPositionElem.getBuyMoney();
                    lastTotalNum += holdPositionElem.getBuyNum();
                }
            }
            double lastAvgCost = lastTotalMoney/lastTotalNum;
            holdPosition.setLastAvgCost(lastAvgCost);//上次的平均成本
            double growRate = (fundRealNetWorth.getNowNetWorth() - lastAvgCost)/lastAvgCost;
            holdPosition.setGrowRate(growRate);
            //当前成本当前数量
            double nowTaoalMoney = lastTotalMoney + holdPosition.getBuyMoney();
            double nowNum = holdPosition.getBuyNum() + lastTotalNum;
            double avgCost = nowTaoalMoney / nowNum;
            holdPosition.setNowAvgCost(avgCost);
            holdPosition.setType(1);
            //插入记录
            holdPositionService.insertHoldPositionInfo(holdPosition);
        }catch (Exception e) {
            rv.setSuccess(false);
            rv.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return rv;
    }
	
	
	
	
	*//**
	 * @Title: analyBuyStrategy   
	 * @Description: TODO   
	 * @param: @param request
	 * @param: @return      
	 * @return: ReturnValue      
	 * @throws
	 *//*
	@RequestMapping("/analyBuyStrategy")
	@ResponseBody
	public ReturnValue analyBuyStrategy(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			// 1.查询所有期待购买的基金信息;
		    int count = 1;
		    List<StrategyAdviceInfo> emailConList = new ArrayList<StrategyAdviceInfo>();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    List<ExpectFund> allExpectFundList = iExpectFundService.queryExpectFundList(null);
		    for (int i = 0; i < allExpectFundList.size(); i++) {
		      ExpectFund expectFundEle = allExpectFundList.get(i);
		      //2.获取实时的净值信息;
	          FundSummary fundSummary = fundSummaryService.getFundSummaryByCode(expectFundEle.getFundCode());
	          FundRealTimeNetWorthDTO fundRealNetWorth = FundRealTimeUtil.getFundRealTimeNetWorth(fundSummary);
	          //3.查询最后一条holdpositon记录
	          HoldPosition lastHoldPosition = holdPositionService.getTheLastHoldPosition(expectFundEle.getFundCode());
	          if(lastHoldPosition == null){
	              System.out.println("FundCode:" + expectFundEle.getFundCode() + " 持仓为空.");
                  continue;
	          }
	          if(fundRealNetWorth.getNowNetWorth() <= 0){
	              System.out.println("FundCode:" + expectFundEle.getFundCode() + " 实时净值小于0.");
                  continue;
              }
	          //4.计算增长率
	          String emailCont = "";
	          double lastnowAvgCost = lastHoldPosition.getNowAvgCost();
	          double growRate = (fundRealNetWorth.getNowNetWorth() - lastnowAvgCost)/lastnowAvgCost;
	          StrategyAdviceInfo tempAdvice = new StrategyAdviceInfo();
	          emailCont = "[时间]:"+ sdf.format(new Date())+" [实时净值]：" + fundRealNetWorth.getNowNetWorth() + " [目前成本]:" + lastnowAvgCost +" [差值]:" + (fundRealNetWorth.getNowNetWorth() - lastnowAvgCost) + "  [增长率]:" + growRate;
	          tempAdvice.setId(count+"");
	          tempAdvice.setFundCode(expectFundEle.getFundCode());
	          tempAdvice.setFundName(fundSummary.getFundName());
	          tempAdvice.setTime(sdf.format(new Date()));
	          tempAdvice.setNetWorth(fundRealNetWorth.getNowNetWorth());
	          tempAdvice.setNowCost(lastnowAvgCost);
	          tempAdvice.setDiffValue(fundRealNetWorth.getNowNetWorth() - lastnowAvgCost);
	          tempAdvice.setGrowRate(growRate);
	          String strageCon = "";
	          if(growRate > 0){//漲了
	              if(0.02< growRate && growRate <= 0.025){
	                  strageCon ="卖出20%";
	                  System.out.println(strageCon);
	              }else if(0.025 < growRate && growRate <= 0.03){
	                  strageCon = "卖出50%";
                      System.out.println("卖出30%");
                  }else if(0.03 < growRate && growRate <= 0.04){
                      strageCon = "卖出50%";
                      System.out.println("卖出50%");
                  }else if(0.04 < growRate){
                      strageCon = "卖出全部";
                      System.out.println("卖出全部");
                  }
	          }else{//跌了
	              if( 0.01< Math.abs(growRate) && growRate <= 0.02){
	                  strageCon = "买入500";
                      System.out.println("买入500");
                  }else if(0.02< Math.abs(growRate) && Math.abs(growRate) <= 0.025){
                      strageCon = "买入1000";
                      System.out.println("买入1000");
                  }else if(0.025 < Math.abs(growRate) && Math.abs(growRate) <= 0.03){
                      strageCon ="买入1500";
                      System.out.println("买入1500");
                  }else if(0.03 < Math.abs(growRate)){
                      strageCon ="买入2000";
                      System.out.println("买入2000");
                  }
	          }
	          emailCont += strageCon;
	          System.out.println(emailCont);
	          tempAdvice.setStageAdvice(strageCon);
              emailConList.add(tempAdvice);
	          count++;
            }
		    //5.发送邮件
		    if(emailConList.size() >=0){
		        MimeMessage emailMsg = EmailUtil.creatHtmlMail("策略建议", emailConList);
		        EmailUtil.sendEmail(emailMsg);
		    }
		    System.out.println("[BBBBB]:count:" + count + "分析完成.....");
			System.out.println("**************************************");
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
}
*/