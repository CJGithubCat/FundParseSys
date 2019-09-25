package com.zsh.labouCapital.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:  GuzhiAnalyseJobHandler   
 * @Description:估值分析处理器
 * @author: cj
 * @date:   2019年8月25日 下午12:19:39
 */
public class GuzhiAnalyseJobHandler extends AbstractJobHandler{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public void excution() {
		logger.error("估值分析定时任务...");
	}
	
}
