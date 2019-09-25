package com.zsh.labouCapital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zsh.labouCapital.controller.request.JobRequest;
import com.zsh.labouCapital.dao.client.TJobMapper;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.service.IJobService;

public class JobServiceImpl implements IJobService{

	@Autowired
	private TJobMapper jobMapper;
	
	@Override
	public List<JobDto> queryJobList(JobRequest jobRequest) throws Exception {

		return null;
	}
	
}
