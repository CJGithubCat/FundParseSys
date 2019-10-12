package com.zsh.labouCapital.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.service.IFundNetWorthHistoryService;
import com.zsh.labouCapital.service.IIndexService;

/**
 *<p> Title: GetNetWorthHistoryJob </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年10月11日
 */
@Component
public class GetNetWorthHistoryJob {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IFundNetWorthHistoryService netWorthHistoryService;;
    
    private static String JOBKEY = "JOB_FUND_NETWORT_HISTORY";
    
    public void execute(){
        try {
            //1.数据入库;
            logger.error("获取历史净值跑批开始...");
            JobDto jobDto = new JobDto();
            jobDto.setJobKey(JOBKEY);
            netWorthHistoryService.parseFundNetWorthHistoryInfo(jobDto);
            logger.error("跑批完成...");
        }
        catch (Exception e) {
            logger.error("GetNetWorthHistoryJob Fail:ERROR MSG:" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
