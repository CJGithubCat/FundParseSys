package com.zsh.labouCapital.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.service.IFundHoldService;
import com.zsh.labouCapital.service.IIndexService;

/**
 *<p> Title: GetIndexInfoJob </p>
 *<p> Description:获取最新估值信息  </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年9月30日
 */
@Component
public class GetNewestFundGuZhiJob {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IFundHoldService fundHoldService;
    
    private static String JOBKEY = "JOB_NEWEST_GUZHI";
    
    public void execute(){
        try {
            //1.数据入库;
            logger.error("基金最新估值跑批开始...");
            JobDto jobDto = new JobDto();
            jobDto.setJobKey(JOBKEY);
            fundHoldService.parseNewestFundGuZhi(jobDto);
            
            logger.error("基金最新估值跑批完成...");
        }
        catch (Exception e) {
            logger.error("GetNewestGuZhiJob Fail:ERROR MSG:" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
