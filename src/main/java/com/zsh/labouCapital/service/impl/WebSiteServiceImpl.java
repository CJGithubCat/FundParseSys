package com.zsh.labouCapital.service.impl;

import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.controller.request.SourceRequest;
import com.zsh.labouCapital.dao.client.TWebsiteInfoMapper;
import com.zsh.labouCapital.dao.dto.TWebsiteInfo;
import com.zsh.labouCapital.dao.dto.TWebsiteInfoCriteria;
import com.zsh.labouCapital.service.IWebSiteService;
import com.zsh.labouCapital.util.PojoConvertUtil;

@Service
public class WebSiteServiceImpl implements IWebSiteService {
    
    @Autowired
    private TWebsiteInfoMapper websiteInfoMapper;

    @Override
    public List<TWebsiteInfo> queryWebSiteList(SourceRequest fundRequest) {
        TWebsiteInfoCriteria queryCriteria = getCriteria(fundRequest,"query");
        return websiteInfoMapper.selectByExample(queryCriteria);
    }

    @Override
    public int queryWebSiteCount(SourceRequest fundRequest) {
        TWebsiteInfoCriteria queryCriteria = getCriteria(fundRequest,"query");
        return websiteInfoMapper.countByExample(queryCriteria);
    }
    
    public TWebsiteInfoCriteria getCriteria(SourceRequest sourceRequest,String opType){
        TWebsiteInfoCriteria cr = new TWebsiteInfoCriteria();
        TWebsiteInfoCriteria.Criteria criteria = cr.createCriteria();
        if("query".equals(opType)){
            if(!StringUtils.isEmpty(sourceRequest.getItemName())){
                criteria.andItemNameLike("%"+sourceRequest.getItemName() + "%");
             }
        }else if("update".equals(opType)){
            if(!StringUtils.isEmpty(sourceRequest.getItemName())){
                criteria.andItemNameLike(sourceRequest.getItemName());
             }
        }
        
        if(sourceRequest.getType() > 0){
            criteria.andTypeEqualTo(sourceRequest.getType());
        }
        
        cr.setOffset(sourceRequest.getOffSet());
        cr.setLimit(sourceRequest.getPageSize());
        return cr;
    }

    @Override
    public void addSource(SourceRequest sourceRequest) throws GeneralSecurityException {
        if(StringUtils.isEmpty(sourceRequest)){
            return;
        }
        
        TWebsiteInfoCriteria criteria = getCriteria(sourceRequest,"update");
        int reCount = websiteInfoMapper.countByExample(criteria);
        if(reCount > 0){//已经存在
            throw new GeneralSecurityException("资源信息已存在");
        }
        
        TWebsiteInfo record = PojoConvertUtil.convert(sourceRequest, TWebsiteInfo.class);
        websiteInfoMapper.insertSelective(record);
    }
}
