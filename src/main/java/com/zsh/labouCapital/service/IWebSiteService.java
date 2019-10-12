package com.zsh.labouCapital.service;
import java.security.GeneralSecurityException;
import java.util.List;

import com.zsh.labouCapital.controller.request.SourceRequest;
import com.zsh.labouCapital.dao.dto.TWebsiteInfo;

public interface IWebSiteService {
	public List<TWebsiteInfo> queryWebSiteList(SourceRequest sourceRequest);

	public int queryWebSiteCount(SourceRequest sourceRequest);
    
	public void addSource(SourceRequest sourceRequest) throws GeneralSecurityException;
}
