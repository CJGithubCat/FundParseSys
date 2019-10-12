package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zsh.labouCapital.controller.request.JobRequest;
import com.zsh.labouCapital.dao.client.TJobMapper;
import com.zsh.labouCapital.dao.dto.TJob;
import com.zsh.labouCapital.dao.dto.TJobCriteria;
import com.zsh.labouCapital.service.IJobService;

@Service
public class JobServiceImpl implements IJobService{

	@Autowired
	private TJobMapper jobMapper;
	
	@Override
	public List<TJob> queryJobList(JobRequest jobRequest) throws Exception {
	    TJobCriteria queryCr = getCriteria(jobRequest,"query");
	    return jobMapper.selectByExample(queryCr);
	}

    @Override
    public int queryJobCount(JobRequest jobRequest) {
        TJobCriteria queryCr = getCriteria(jobRequest,"query");
        return jobMapper.countByExample(queryCr);
    }
    
    public TJobCriteria getCriteria(JobRequest jobRequest,String opType){
        TJobCriteria cr = new TJobCriteria();
        TJobCriteria.Criteria criteria = cr.createCriteria();
        if("query".equals(opType)){
            if(!StringUtils.isEmpty(jobRequest.getJobName())){
                criteria.andJobNameLike("%"+ jobRequest.getJobName() +"%");
            }
            
        }else if("update".equals(opType)){
            if(!StringUtils.isEmpty(jobRequest.getJobName())){
                criteria.andJobNameEqualTo(jobRequest.getJobName());
            }
        }
        
        if(!StringUtils.isEmpty(jobRequest.getJobKey())){
            criteria.andJobKeyEqualTo(jobRequest.getJobKey());
        }
        
        cr.setLimit(jobRequest.getLimit());
        cr.setOffset(jobRequest.getOffSet());
        
        return cr;
    }
}
