package com.zsh.labouCapital.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.controller.request.FundHoldRequest;
import com.zsh.labouCapital.dao.client.THoldFundMapper;
import com.zsh.labouCapital.dao.client.THoldFundSummaryMapper;
import com.zsh.labouCapital.dao.client.TWebsiteInfoMapper;
import com.zsh.labouCapital.dao.dto.THoldFund;
import com.zsh.labouCapital.dao.dto.THoldFundCriteria;
import com.zsh.labouCapital.dao.dto.THoldFundSummary;
import com.zsh.labouCapital.dao.dto.THoldFundSummaryCriteria;
import com.zsh.labouCapital.dao.dto.TWebsiteInfo;
import com.zsh.labouCapital.dao.dto.TWebsiteInfoCriteria;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.exception.GenericBizException;
import com.zsh.labouCapital.service.IFundHoldService;
import com.zsh.labouCapital.util.DateTimeUtil;
import com.zsh.labouCapital.util.HttpclientUtil;
import com.zsh.labouCapital.util.PojoConvertUtil;

@Service
public class FundHoldServiceImpl implements IFundHoldService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private THoldFundMapper fundHoldMapper;
	
	@Autowired
    private TWebsiteInfoMapper webInfoMapper;

	@Autowired
	private THoldFundSummaryMapper fundHoldSummaryMapper;
	
	@Override
	public int queryFundHoldCount(FundHoldRequest fundRequest) {
		THoldFundCriteria cr = getCriteria(fundRequest);
		return fundHoldMapper.countByExample(cr);
	}

	@Override
	public List<THoldFund> queryFundHoldList(FundHoldRequest fundRequest) {
		THoldFundCriteria cr = getCriteria(fundRequest);
		return fundHoldMapper.selectByExample(cr);
	}
	
	public THoldFundCriteria getCriteria(FundHoldRequest fundRequest){
		THoldFundCriteria cr = new THoldFundCriteria();
		THoldFundCriteria.Criteria criteria = cr.createCriteria();
		if(!StringUtils.isEmpty(fundRequest.getFundCode())){
			criteria.andFundCodeEqualTo(fundRequest.getFundCode());
		}
		if(!StringUtils.isEmpty(fundRequest.getFundName())){
			criteria.andFundNameLike(fundRequest.getFundName()+"%");
		}
		cr.setOffset(fundRequest.getOffSet());
		cr.setLimit(fundRequest.getPageSize());
		cr.setOrderByClause(" market_value desc ");
		return cr;
	}

	@Override
	public Map<String, Object> parseFundHoldList(MultipartFile summaryFile) throws IOException {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if(summaryFile != null){
			String fileStr = new String(summaryFile.getBytes(),"UTF-8");
			JSONObject fileContent = JSONObject.parseObject(fileStr);
			
			JSONObject dataJSONObj = fileContent.getJSONObject("data");
			
			if(dataJSONObj == null){
				return null;
			}
			
			//解析汇总信息
			THoldFundSummary holdFundSummary = new THoldFundSummary(); 
			holdFundSummary.setTotalAssets(dataJSONObj.getDouble("total_assets"));
			holdFundSummary.setDailyGain(dataJSONObj.getDouble("daily_gain"));
			holdFundSummary.setTotalGain(dataJSONObj.getDouble("total_gain"));
			holdFundSummary.setHoldGain(dataJSONObj.getDouble("hold_gain"));
			holdFundSummary.setTs(dataJSONObj.getDate("ts"));
			
			reMap.put("holdFundSummary", holdFundSummary);
			//解析具体基金持仓信息
			JSONArray jsonArray = dataJSONObj.getJSONArray("items");
			
			List<THoldFund> holdFundList = new ArrayList<THoldFund>();
			if(CollectionUtils.isNotEmpty(jsonArray)){
				for (Object object : jsonArray) {
					if(object == null){
						continue;
					}
					JSONObject jsonObject = (JSONObject)object;
					THoldFund temObj = new THoldFund();
					temObj.setAccumCost(jsonObject.getDouble("accum_cost"));
					temObj.setAutoInvestCount(jsonObject.getDouble("auto_invest_count"));
					temObj.setDailyGain(jsonObject.getDouble("daily_gain"));
					temObj.setDateCreate(new Date());
					temObj.setDateUpdate(new Date());
					temObj.setFundCode(jsonObject.getString("fd_code"));
					temObj.setFundName(jsonObject.getString("fd_name"));
					temObj.setHoldCost(jsonObject.getDouble("hold_cost"));
					temObj.setHoldGain(jsonObject.getDouble("hold_gain"));
					temObj.setHoldGainRate(jsonObject.getDouble("hold_gain_rate"));
					temObj.setHoldingCost(jsonObject.getDouble("holding_cost"));
					temObj.setMarketValue(jsonObject.getDouble("market_value"));
					temObj.setNav(jsonObject.getDouble("nav"));
					temObj.setNavGrtd(jsonObject.getDouble("nav_grtd"));
					temObj.setNavGuzhi(0D);
					temObj.setProfitDocuments(jsonObject.getString("profit_documents"));
					temObj.setTotalGain(jsonObject.getDouble("total_gain"));
					temObj.setTotalGainRate(jsonObject.getDouble("total_gain_rate"));
					temObj.setTs(jsonObject.getDate("ts"));
					temObj.setType(jsonObject.getString("type"));
					temObj.setUnconfirmedAmount(jsonObject.getDouble("unconfirmed_amount"));
					temObj.setUnconfirmedCount(jsonObject.getDouble("unconfirmed_count"));
					temObj.setUnconfirmedVolume(jsonObject.getDouble("unconfirmed_volume"));
					temObj.setUsableRemainShare(jsonObject.getDouble("usable_remain_share"));
					temObj.setVolume(jsonObject.getDouble("volume"));
					
					logger.error("json:item:" + temObj);
					if(temObj != null){
						holdFundList.add(temObj);
					}
				}
				reMap.put("holdFundList", holdFundList);
			}
		}
		return reMap;
	}

	@Override
	public void updateOrAddHoldFundSummary(THoldFundSummary holdFundSummary) {
		THoldFundSummaryCriteria cr = new THoldFundSummaryCriteria();
		List<THoldFundSummary> sumaryList = fundHoldSummaryMapper.selectByExample(cr);
		if(CollectionUtils.isEmpty(sumaryList)){
			fundHoldSummaryMapper.insert(holdFundSummary);
		}else{
			THoldFundSummary tempSumary = sumaryList.get(0);
			holdFundSummary.setId(tempSumary.getId());
			fundHoldSummaryMapper.updateByPrimaryKey(holdFundSummary);
		}
	}

	@Override
	public void updateOrAddHoldFund(List<THoldFund> holdFundList) {
		for (THoldFund tHoldFund : holdFundList) {
			FundHoldRequest param = PojoConvertUtil.convert(tHoldFund, FundHoldRequest.class);
			THoldFundCriteria criteria = this.getCriteria(param);
			List<THoldFund> fundHoldList = fundHoldMapper.selectByExample(criteria);
			if(CollectionUtils.isEmpty(fundHoldList)){
				fundHoldMapper.insert(tHoldFund);
			}else{
				fundHoldMapper.updateByExampleSelective(tHoldFund, criteria);
			}
		}
	}

    @Override
    public void parseNewestFundGuZhi(JobDto jobDto) throws Exception {
        if(jobDto == null || StringUtils.isEmpty(jobDto.getJobKey())){
            throw new GenericBizException("参数为空.");
        }
        
        //1.读取对应的url;
        String jobKey = jobDto.getJobKey();
        TWebsiteInfoCriteria cr = new TWebsiteInfoCriteria();
        TWebsiteInfoCriteria.Criteria criteria = cr.createCriteria();
        criteria.andJobKeyEqualTo(jobKey);
        List<TWebsiteInfo> webSiteList = webInfoMapper.selectByExample(cr);
        if(CollectionUtils.isEmpty(webSiteList)){
            throw new GenericBizException("JOBKey没有对应的URL.");
        }
        String guzhiUrl = webSiteList.get(0).getItemUrl();
        //2.分析所有持有的基金的估值信息，请求数据并解析;
        THoldFundCriteria holdCr = new THoldFundCriteria();
        THoldFundCriteria.Criteria criteria2 = holdCr.createCriteria();
        criteria2.andTypeEqualTo("fund");
        List<THoldFund> fundHoldList = fundHoldMapper.selectByExample(null);
        
        long nowStamp = DateTimeUtil.getMilliSecond(new Date());
        if(CollectionUtils.isNotEmpty(fundHoldList)){
            for (THoldFund tHoldFund : fundHoldList) {
                if(tHoldFund != null){
                    String tempUrl = guzhiUrl;
                    tempUrl = tempUrl.concat(tHoldFund.getFundCode()).concat(".js?rt=").concat(nowStamp+"");
                    THoldFund guzhiFund = parseNewestGuZhiInfo(tHoldFund.getId(),tempUrl);
                    if(guzhiFund != null){
                        //3.更新估值信息
                        fundHoldMapper.updateByPrimaryKeySelective(guzhiFund);
                    }
                }
            }
        }
    }

    /**
     * @throws URISyntaxException 
     * @throws IOException 
     * @throws ParseException    
     * @Title: parseNewestGuZhiProcess   
     * @Description: 解析最新的估值信息;   
     * @param: @param reJson
     * @param: @return      
     * @return: List<THoldFund>      
     * @throws   
     */
    private THoldFund parseNewestGuZhiInfo(long id,String guzhiUrl) throws ParseException, IOException, URISyntaxException {
        logger.error("guzhiUrl:" + guzhiUrl);
        String reJson = HttpclientUtil.get(guzhiUrl, null);
        if(StringUtils.isEmpty(reJson)){
            return null;
        }
        logger.error("reJson:" + reJson);
        if(reJson.contains("doctype") || reJson.contains("html")){
            return null;
        }
        
        if(reJson.startsWith("jsonpgz(") && reJson.endsWith(");")){
            reJson = reJson.replace("jsonpgz(", "");
            reJson = reJson.replace(");", "");
        }
        
        JSONObject allDataJson = (JSONObject) JSONObject.parse(reJson);
        if(allDataJson == null){
            return null;
        }
        //
        String fundCode = allDataJson.getString("fundcode");
        double gsz = allDataJson.getDoubleValue("gsz");
        
        THoldFund reHoldFund = null;
        if(!StringUtils.isEmpty(fundCode)){
            reHoldFund = new THoldFund();
            reHoldFund.setId(id);
            reHoldFund.setFundCode(fundCode);
            reHoldFund.setNavGuzhi(gsz);
        }
        logger.error("reHoldFund:" + JSONObject.toJSONString(reHoldFund));
        
        return reHoldFund;
    }
}
