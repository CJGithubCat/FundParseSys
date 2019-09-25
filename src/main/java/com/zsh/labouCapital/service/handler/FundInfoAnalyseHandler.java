package com.zsh.labouCapital.service.handler;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.dao.dto.TNetWorthLast;
import com.zsh.labouCapital.dto.FundNetWorthDto;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.HttpclientUtil;


/**
 * @ClassName:  FundInfoAnalyseHandler   
 * @Description:基金分析的handler
 * @author: cj
 * @date:   2019年8月24日 下午2:15:56
 */
@Component
public class FundInfoAnalyseHandler extends AbstractHandler{
	
	/**
	 * @Title: parseNewestGuZhiInfo   
	 * @Description: 分析最新的估值信息 
	 * 注意:只可以分析当前时间的估值；且时间必须是: 9~15点之间;
	 * @param: @param fundRequest
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: FundNetWorthDto      
	 * @throws
	 */
	public TNetWorthLast parseNewestGuZhiInfo(FundNetWorthDto fundRequestDto) throws Exception {
		if(fundRequestDto == null || StringUtils.isEmpty(fundRequestDto.getFundCode())){
			throw new GeneralSecurityException("参数为空");
		}
		String fundCode = fundRequestDto.getFundCode();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

        String timeStamp = this.generateTimeStamp(new Date());
        params.add(new BasicNameValuePair("v", timeStamp));
        String reBody = HttpclientUtil.get(fundRequestDto.getGuzhiUrl(), params);
        //去除掉无效的字符
        reBody = reBody.replace("jsonpgz(", "").replace(");", "");
        JSONObject guzhiJson = JSONObject.parseObject(reBody);
        System.out.println(guzhiJson);

        double yesNetWorth = guzhiJson.getDoubleValue("dwjz");
        double newestNetWorth = guzhiJson.getDoubleValue("gsz");
        double netWorthAddRate = guzhiJson.getDoubleValue("gszzl");
        
        Date upateDate = null;
        Date dateInfo = null;
        
        try {
			upateDate = DateTimeUtil.parseDate(guzhiJson.getString("gztime").concat(":00"), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN2);
			dateInfo = DateTimeUtil.parseDate(guzhiJson.getString("gztime").substring(0, guzhiJson.getString("gztime").lastIndexOf(" ")), DateTimeUtil.DEFAULT_DATE_TIME_PATTERN_S);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        TNetWorthLast guzhiFundNetWorth = new TNetWorthLast();
        guzhiFundNetWorth.setFundCode(fundCode);
        guzhiFundNetWorth.setFundName(fundRequestDto.getFundName());
        guzhiFundNetWorth.setNowNetWorth(newestNetWorth);
        guzhiFundNetWorth.setYesterdayNetWorth(yesNetWorth);
        guzhiFundNetWorth.setGuzhiAddRate(netWorthAddRate);
        guzhiFundNetWorth.setDateUpdate(upateDate);
        guzhiFundNetWorth.setDateInfo(dateInfo);
		return guzhiFundNetWorth;
	}

}
