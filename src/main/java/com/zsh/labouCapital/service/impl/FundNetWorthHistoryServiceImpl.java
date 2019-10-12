package com.zsh.labouCapital.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.zsh.labouCapital.comm.FundNetWorthConstant;
import com.zsh.labouCapital.controller.request.FundNetWorthHistoryRequest;
import com.zsh.labouCapital.dao.client.TFundMapper;
import com.zsh.labouCapital.dao.client.TNetWorthHistoryMapper;
import com.zsh.labouCapital.dao.client.custom.TNetWorthHistoryCustmMapper;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dao.dto.TFundCriteria;
import com.zsh.labouCapital.dao.dto.TNetWorthHistory;
import com.zsh.labouCapital.dao.dto.TNetWorthHistoryCriteria;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.service.IFundNetWorthHistoryService;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.HttpclientUtil;
import com.zsh.labouCapital.util.PojoConvertUtil;

import net.sf.json.JSONObject;

@Service
public class FundNetWorthHistoryServiceImpl implements IFundNetWorthHistoryService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private TNetWorthHistoryMapper fundNetWorthHistoryMapper;
	
	@Autowired
	private TFundMapper tFundMapper;
	
	@Autowired
	private TNetWorthHistoryMapper tNetWorthHistoryMapper;
	
	@Autowired
    private TNetWorthHistoryCustmMapper tNetWorthHistoryCustmMapper;

	@Override
	public List<FundNetWorthDto> queryNetWorthHistoryList(FundNetWorthHistoryRequest fundRequest) {
		TNetWorthHistoryCriteria cr = geTNetWorthHistoryCriteria(fundRequest);
		List<TNetWorthHistory> historyList = fundNetWorthHistoryMapper.selectByExample(cr);
		List<FundNetWorthDto> hisNetDtoList = null;
		if(CollectionUtils.isNotEmpty(historyList)){
			hisNetDtoList = PojoConvertUtil.convert(historyList, FundNetWorthDto.class);
			for (FundNetWorthDto fundNetWortDto : hisNetDtoList) {
				if(fundNetWortDto.getDateInfo() != null){
					fundNetWortDto.setDateInfoStr(DateTimeUtil.formatDate(fundNetWortDto.getDateInfo(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2));
				}
			}
		}
		
		return hisNetDtoList;
	}
	
	@Override
	public int queryNetWorthHistoryCount(FundNetWorthHistoryRequest fundRequest) {
		TNetWorthHistoryCriteria cr = geTNetWorthHistoryCriteria(fundRequest);
		return fundNetWorthHistoryMapper.countByExample(cr);
	}
	
	public TNetWorthHistoryCriteria geTNetWorthHistoryCriteria(FundNetWorthHistoryRequest fundRequest){
		TNetWorthHistoryCriteria cr = new TNetWorthHistoryCriteria();
		TNetWorthHistoryCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		if(!StringUtils.isEmpty(fundRequest.getStartDate())){
			criteria.andDateInfoGreaterThanOrEqualTo(DateTimeUtil.parseDate(fundRequest.getStartDate(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN_S));
		}
		if(!StringUtils.isEmpty(fundRequest.getEndDate())){
			criteria.andDateInfoLessThanOrEqualTo(DateTimeUtil.parseDate(fundRequest.getEndDate(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN_S));
		}
		cr.setLimit(fundRequest.getPageSize());
		cr.setOffset(fundRequest.getOffSet());
		return cr;
	}

    @Override
    public void parseFundNetWorthHistoryInfo(JobDto jobDto) {
        //1.查询所有的expect_fund中的关注的基金数据;
        TFundCriteria cr = new TFundCriteria();
        TFundCriteria.Criteria criteria = cr.createCriteria();
        criteria.andIsExceptEqualTo(1);
        List<TFund> fundList = tFundMapper.selectByExample(cr);
        
        if(CollectionUtils.isNotEmpty(fundList)){
            for (TFund tFund : fundList) {
                if(tFund == null){
                    continue;
                }
                String historyUrl = tFund.getHistoryUrl();
                if(StringUtils.isEmpty(historyUrl)){
                    continue;
                }
                
                long startL = calStartDateL(tFund.getFundCode());
                //2.请求并解析数据；
                List<TNetWorthHistory> reList = parseJsNetWorth(historyUrl,tFund.getFundCode(),startL);
                
                //3.新增或者更新数据;
                if(CollectionUtils.isNotEmpty(reList)){
                    for (TNetWorthHistory tNetWorthHistory : reList) {
                        if(tNetWorthHistory == null){
                            continue;
                        }
                        TNetWorthHistoryCriteria historyCr = new TNetWorthHistoryCriteria();
                        TNetWorthHistoryCriteria.Criteria criteria2 = historyCr.createCriteria();
                        criteria2.andFundCodeEqualTo(tNetWorthHistory.getFundCode());
                        criteria2.andDateInfoEqualTo(tNetWorthHistory.getDateInfo());
                        int num = tNetWorthHistoryMapper.countByExample(historyCr);
                        if(num <= 0){
                            tNetWorthHistoryMapper.insert(tNetWorthHistory);
                            logger.error("新增:" + com.alibaba.fastjson.JSONObject.toJSONString(tNetWorthHistory));
                        }
                    }
                }
            }
        }
    }
    
    
    /**
     * @Title: getStartDateL   
     * @Description: 计算开始时间
     *  获取t_fund_history表最大的数据的天数，下一天就是要获取的开始日期;
     *  如果没有数据，获取全量数据;
     * @param: @return      
     * @return: long      
     * @throws
     */
    public long calStartDateL(String fundCode){
        long reSatrtL = Long.MIN_VALUE;
        TNetWorthHistoryCriteria cr = new TNetWorthHistoryCriteria();
        TNetWorthHistoryCriteria.Criteria criteria = cr.createCriteria();
        criteria.andFundCodeEqualTo(fundCode);
        TNetWorthHistory maxNetWorth = tNetWorthHistoryCustmMapper.getMaxDateNetWorthByExample(cr);
        if(maxNetWorth != null){
            reSatrtL = maxNetWorth.getDateInfo().getTime();
        }
        return reSatrtL;
    }

    
    public static  List<TNetWorthHistory> parseJsNetWorth(String url,String fundCode,long startDayL){
        //1.请求数据
        List<NameValuePair> params = new ArrayList<>();
        List<TNetWorthHistory> reList = new ArrayList<TNetWorthHistory>();
        
        try {
            String timStamp = DateTimeUtil.formatDate(new Date(), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN6);
            params.add(new BasicNameValuePair("v",timStamp));
            String reBody = HttpclientUtil.get(url, params);
            //2.解析js数据
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            engine.eval(reBody);
            Object array = engine.get(FundNetWorthConstant.DATA_NETWORTH_Trend);
            JSONObject tempObj = JSONObject.fromObject(array);
            int len = tempObj.size();
            
            for (int i = 0; i < len; i++) {
                TNetWorthHistory temValue = new TNetWorthHistory();
                JSONObject vaJsonObject = (JSONObject) tempObj.get(""+i);
                long milSecod = vaJsonObject.getLong("x");
                if(milSecod <= startDayL){//小于等于最大值都跳过；大于目前最大值的才保留；
                    continue;
                }
                
                Double value = vaJsonObject.getDouble("y");
                String equityReturn = vaJsonObject.getString("equityReturn");
                String unitMoney = vaJsonObject.getString("unitMoney");
                int week = getWeekInfo(milSecod);
                
                String dateInfo = DateTimeUtil.formatDate(new Date(milSecod),DateTimeUtil.DEFAULT_DATE_PATTERN2);
                temValue.setFundCode(fundCode);
                temValue.setDateInfo(DateTimeUtil.parseDate(dateInfo, DateTimeUtil.DEFAULT_DATE_PATTERN2));
                temValue.setNetWorth(value);
                if(!StringUtils.isEmpty(equityReturn)){
                    temValue.setEquityReturn(Double.parseDouble(equityReturn));
                }
                temValue.setUnitMoney(unitMoney);
                temValue.setWeekInfo(week);
                reList.add(temValue);
            }
            System.out.println("AAAAAA:"+reList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reList;
    }

    /**
     * 判断是星期几
     * 入参：毫秒数
     * 1~7:星期几；1~7代表星期日到星期六
     */
    public static int getWeekInfo(Long datel){
        int week = Integer.MIN_VALUE;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            calendar.setTime(new Date(datel));
            int i =calendar.get(Calendar.DAY_OF_WEEK);
            
            if(i == 1){
                week = 7;
                System.out.println("今天是星期日");
            }else{
                week = i -1;
                System.out.println("今天是星期"+(i-1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return week;
    }
	

	
}
