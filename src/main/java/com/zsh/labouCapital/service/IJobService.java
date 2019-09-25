package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.controller.request.FundRequest;
import com.zsh.labouCapital.controller.request.JobRequest;
import com.zsh.labouCapital.dao.dto.TFund;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.entity.FundSummary;

public interface IJobService {
	public List<JobDto> queryJobList(JobRequest jobRequest) throws Exception;
}
