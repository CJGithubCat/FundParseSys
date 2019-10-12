package com.zsh.labouCapital.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.service.IIndexService;

/**
 *<p> Title: GetIndexInfoJob </p>
 *<p> Description:获取指数估值信息的job </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年9月30日
 */
@Component
public class GetIndexInfoJob {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IIndexService indexService;
    
    private static String JOBKEY = "JOB_INDEXINFO";
    
    public void execute(){
        try {
            //1.数据入库;
            logger.error("指数估值跑批开始...");
            JobDto jobDto = new JobDto();
            jobDto.setJobKey(JOBKEY);
            indexService.parseIndexInfo(jobDto);
            //2.数据关联;关联t_index_info 和t_index表的数据;
            indexService.reletationIndexData();
            logger.error("指数估值跑批完成...");
        }
        catch (Exception e) {
            logger.error("GetIndexInfoJob Fail:ERROR MSG:" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
