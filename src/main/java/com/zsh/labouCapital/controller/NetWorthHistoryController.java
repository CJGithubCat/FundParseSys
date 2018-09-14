package com.zsh.labouCapital.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zsh.labouCapital.dto.FundNetWorthDTO;
import com.zsh.labouCapital.entity.FundSummary;
import com.zsh.labouCapital.entity.NetWorthHistory;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.service.IFundSummaryService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.INetWorthHistoryService;

/**
 * 函数功能：NetWorthHistory
 */
@RestController
@RequestMapping(value = "/netWorth")
public class NetWorthHistoryController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(NetWorthHistoryController.class);

	@Autowired
	private INetWorthHistoryService netWorthHistoryService;
	
	@Autowired
	private IFundSummaryService fundSummaryService;

	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 函数功能：统计当前的日增长率
	 * */
	@RequestMapping("/calDayGrothRate")
	@ResponseBody
	public ReturnValue calDayGrothRate(HttpServletRequest request) {
		ReturnValue rv = new ReturnValue();

		try {
			//1.查询所有基金信息
			List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
			
			for(int k =0; k < fundSummaryList.size(); k++){
				FundSummary paramFund = fundSummaryList.get(k);
				NetWorthHistory paramNetWorth = new NetWorthHistory();
				paramNetWorth.setFundCode(paramFund.getFundCode());
				
				List<NetWorthHistory> updateList = new ArrayList<>();
				List<NetWorthHistory> nwhList = netWorthHistoryService.queryNetWorthHistoryInfo(paramNetWorth);
				if(nwhList.size() < 1){
					rv.setSuccess(true);
					rv.setDatas(null);
					rv.setMessage("未查询到基金净值数据，计算失败!");
					return rv;
				}
				for(int i=0,j=1; i < nwhList.size()-1; i++,j++){
					NetWorthHistory lowValue = nwhList.get(i);
					NetWorthHistory highValue = nwhList.get(j);
					
					if(highValue.getNetWorth() == 0){
						System.out.println("*********为0数据："+highValue);
						continue;
					}
					double growRate = (lowValue.getNetWorth() - highValue.getNetWorth())/highValue.getNetWorth();
					
					NetWorthHistory updateInfo = new NetWorthHistory();
					updateInfo.setId(lowValue.getId());
					updateInfo.setFundCode(lowValue.getFundCode());
					updateInfo.setNetworthDaygrowRate(growRate);
					updateList.add(updateInfo);
				}
				//更新数据库中的日增长率
				netWorthHistoryService.updateNetWorthHistorys(updateList);
			}
			rv.setSuccess(true);
			rv.setDatas(null);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rv;
	}
	
	/**
     * 函数功能：分析定投模型
     * */
    @RequestMapping("/analyseIntervalBuyModule")
    @ResponseBody
    public ReturnValue analyseIntervalBuyModule(HttpServletRequest request,FundNetWorthDTO fundNetWorthDTO) {
        ReturnValue rv = new ReturnValue();

        try {
            String startDate = "2008-01-01";
            String endDate = "2018-01-01";
            int interval = 30;//1month
            
            
            //1.查询所有基金信息
            List<FundSummary> fundSummaryList = fundSummaryService.queryAllFundSummary();
            
            for(int k =0; k < fundSummaryList.size(); k++){
                FundSummary paramFund = fundSummaryList.get(k);
                NetWorthHistory paramNetWorth = new NetWorthHistory();
                paramNetWorth.setFundCode(paramFund.getFundCode());
                
                List<NetWorthHistory> updateList = new ArrayList<>();
                List<NetWorthHistory> nwhList = netWorthHistoryService.queryNetWorthHistoryInfo(paramNetWorth);
                if(nwhList.size() < 1){
                    rv.setSuccess(true);
                    rv.setDatas(null);
                    rv.setMessage("未查询到基金净值数据，计算失败!");
                    return rv;
                }
                for(int i=0,j=1; i < nwhList.size()-1; i++,j++){
                    NetWorthHistory lowValue = nwhList.get(i);
                    NetWorthHistory highValue = nwhList.get(j);
                    
                    if(highValue.getNetWorth() == 0){
                        System.out.println("*********为0数据："+highValue);
                        continue;
                    }
                    double growRate = (lowValue.getNetWorth() - highValue.getNetWorth())/highValue.getNetWorth();
                    
                    NetWorthHistory updateInfo = new NetWorthHistory();
                    updateInfo.setId(lowValue.getId());
                    updateInfo.setFundCode(lowValue.getFundCode());
                    updateInfo.setNetworthDaygrowRate(growRate);
                    updateList.add(updateInfo);
                }
                //更新数据库中的日增长率
                netWorthHistoryService.updateNetWorthHistorys(updateList);
            }
            rv.setSuccess(true);
            rv.setDatas(null);
        } catch (Exception e) {
            rv.setSuccess(false);
            rv.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return rv;
    }
	
	
	
}
