package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.controller.request.IndexRequest;
import com.zsh.labouCapital.dao.client.TIndexInfoMapper;
import com.zsh.labouCapital.dao.client.TIndexMapper;
import com.zsh.labouCapital.dao.client.TWebsiteInfoMapper;
import com.zsh.labouCapital.dao.dto.TIndex;
import com.zsh.labouCapital.dao.dto.TIndexCriteria;
import com.zsh.labouCapital.dao.dto.TIndexInfo;
import com.zsh.labouCapital.dao.dto.TIndexInfoCriteria;
import com.zsh.labouCapital.dao.dto.TWebsiteInfo;
import com.zsh.labouCapital.dao.dto.TWebsiteInfoCriteria;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.entity.IndexInfo;
import com.zsh.labouCapital.exception.GenericBizException;
import com.zsh.labouCapital.service.IIndexService;
import com.zsh.labouCapital.util.HttpclientUtil;
import com.zsh.labouCapital.util.PojoConvertUtil;

@Service
public class IndexServiceImpl implements IIndexService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
	private TIndexMapper indexMapper;

    @Autowired
    private TWebsiteInfoMapper webInfoMapper;
    
    @Autowired
    private TIndexInfoMapper indexInfoMapper;
    
    @Override
    public int queryIndexCount(IndexRequest indexRequest) {
        TIndexCriteria countCr = geCriteria(indexRequest,"query");
        return indexMapper.countByExample(countCr);
    }

    @Override
    public List<TIndex> queryIndexList(IndexRequest indexRequest) {
        TIndexCriteria queryCr = geCriteria(indexRequest,"query");
        return indexMapper.selectByExample(queryCr);
    }
    
    public TIndexCriteria geCriteria(IndexRequest indexRequest,String opType){
        if(indexRequest == null){
            return null;
        }
        TIndexCriteria cr = new TIndexCriteria();
        TIndexCriteria.Criteria criteria = cr.createCriteria();
        
        if(!StringUtils.isEmpty(indexRequest.getIndexCode())){
            criteria.andIndexCodeEqualTo(indexRequest.getIndexCode());
        }
        if("query".equals(opType)){
            if(!StringUtils.isEmpty(indexRequest.getIndexEname())){
                criteria.andIndexEnameLike("%"+ indexRequest.getIndexEname() +"%");
            }
            if(!StringUtils.isEmpty(indexRequest.getIndexSname())){
                criteria.andIndexSnameLike("%"+ indexRequest.getIndexSname() +"%");
            }
        }else if("update".equals(opType)){
            if(!StringUtils.isEmpty(indexRequest.getIndexEname())){
                criteria.andIndexEnameEqualTo("%"+ indexRequest.getIndexEname() +"%");
            }
            if(!StringUtils.isEmpty(indexRequest.getIndexSname())){
                criteria.andIndexSnameEqualTo("%"+ indexRequest.getIndexSname() +"%");
            }
        }
        
        cr.setLimit(indexRequest.getLimit());
        cr.setOffset(indexRequest.getOffSet());
        
        return cr;
    }

    @Override
    public int addIndexInfo(IndexRequest indexRequest) throws GenericBizException {
        int count = queryIndexCount(indexRequest);
        if(count > 0){
            throw new GenericBizException("新增记录已存在");
        }
        
        TIndex index = PojoConvertUtil.convert(indexRequest, TIndex.class);
        return indexMapper.insertSelective(index);
    }

    @Override
    public void parseIndexInfo(JobDto jobDto) throws Exception{
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
        //2.请求数据并解析;
        String reJson = HttpclientUtil.get(guzhiUrl, null);
        List<TIndexInfo> indexInfoList = parseIndexGuZhiParse(reJson);
        //3.入库;
        if(CollectionUtils.isNotEmpty(indexInfoList)){
            for (TIndexInfo tIndexInfo : indexInfoList) {
                if(tIndexInfo == null){
                    continue;
                }
                logger.error(JSONObject.toJSONString(tIndexInfo));
                //3-1.判断是否存在记录,存在则更新，不存在则插入;
                TIndexInfoCriteria inxInfocr = new TIndexInfoCriteria();
                TIndexInfoCriteria.Criteria infCr = inxInfocr.createCriteria();
                infCr.andDjIndexCodeEqualTo(tIndexInfo.getDjIndexCode());
                int count = indexInfoMapper.countByExample(inxInfocr);
                if(count > 0){
                    indexInfoMapper.updateByPrimaryKeySelective(tIndexInfo);
                }else{
                    indexInfoMapper.insertSelective(tIndexInfo);
                }
            }
        }
        return;
    }
    
    public List<TIndexInfo> parseIndexGuZhiParse(String dataStr){
        if(StringUtils.isEmpty(dataStr)){
            return null;
        }
        JSONObject allDataJson = (JSONObject) JSONObject.parse(dataStr);
        JSONObject dataJson = allDataJson.getJSONObject("data");
        
        JSONArray itemArr = dataJson.getJSONArray("items");
        List<TIndexInfo> indexInfoList = new ArrayList<TIndexInfo>();
        if(!CollectionUtils.isEmpty(itemArr)){
            for (Object object : itemArr) {
                TIndexInfo tempIndex = new TIndexInfo();
                JSONObject tempObj = (JSONObject)object;
                tempIndex.setIndexCode(tempObj.getString("index_code"));
                tempIndex.setId(tempObj.getInteger("id"));
                String djIndexCode = tempObj.getString("index_code");
                String djIndexName = tempObj.getString("name");
                
                tempIndex.setDjIndexCode(djIndexCode);
                tempIndex.setDjIndexName(djIndexName);
                
                String indexCode = "";
                if(djIndexCode.length() >= 6){
                    indexCode = djIndexCode.substring(djIndexCode.length() - 6);
                }else{
                    indexCode = djIndexCode;
                }
                tempIndex.setIndexCode(indexCode);
                tempIndex.setIndexName("");
                
                tempIndex.setBeginAt(tempObj.getDate("begin_at"));
                tempIndex.setBondYeild(tempObj.getDouble("bond_yeild"));
                tempIndex.setDateUpdate(new Date());
                tempIndex.setEvaType(tempObj.getString("eva_type"));
                tempIndex.setEvaTypeInt(tempObj.getIntValue("eva_type_int"));                
                tempIndex.setPb(tempObj.getDouble("pb"));
                tempIndex.setPbPercentile(tempObj.getDouble("pb_percentile"));
                tempIndex.setPe(tempObj.getDouble("pe"));
                tempIndex.setPePercentile(tempObj.getDouble("pe_percentile"));
                tempIndex.setRoe(tempObj.getDouble("roe"));
                tempIndex.setUrl(tempObj.getString("url"));
                tempIndex.setYeild(tempObj.getDouble("yeild"));
                
                indexInfoList.add(tempIndex);
            }
        }
        logger.error("reList:" + indexInfoList);
        
        return indexInfoList;
    }

    @Override
    public void reletationIndexData() {
        List<TIndexInfo> indexInfoList = indexInfoMapper.selectByExample(null);
        if(CollectionUtils.isNotEmpty(indexInfoList)){
            for (TIndexInfo tIndexInfo : indexInfoList) {
                if(tIndexInfo == null || StringUtils.isEmpty(tIndexInfo.getIndexCode())){
                    continue;
                }
                
                TIndex tindex = indexMapper.selectByPrimaryKey(tIndexInfo.getIndexCode());
                if(tindex != null){
                    TIndexInfo tempIndexInfo = new TIndexInfo();
                    tempIndexInfo.setId(tIndexInfo.getId());
                    tempIndexInfo.setIndexName(tindex.getIndexEname());
                    logger.error("AA:" + JSONObject.toJSONString(tindex));
                    logger.error("BB:" + JSONObject.toJSONString(tempIndexInfo));

                    indexInfoMapper.updateByPrimaryKeySelective(tempIndexInfo);
                }else{
                    //数据插入到t_index表
                }
            }
        }
    }
}
