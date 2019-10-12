package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.controller.request.JobRequest;
import com.zsh.labouCapital.dao.dto.TJob;

public interface IJobService {
	public List<TJob> queryJobList(JobRequest jobRequest) throws Exception;

    /**   
     * @Title: queryJobCount   
     * @Description: TODO   
     * @param: @param jobRequest
     * @param: @return      
     * @return: int      
     * @throws   
     */
    public int queryJobCount(JobRequest jobRequest);
    
    
}
