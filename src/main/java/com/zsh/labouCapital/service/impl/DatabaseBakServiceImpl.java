package com.zsh.labouCapital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsh.labouCapital.dao.DatabaseBakMapper;
import com.zsh.labouCapital.service.IDatabaseBakService;
import com.zsh.labouCapital.util.pagination.PageBean;

@Service
public class DatabaseBakServiceImpl implements IDatabaseBakService {

	@Autowired
	private DatabaseBakMapper databaseBakMapper;

	@Override
	public HashMap<String, Object> querydatabaseBak(Map<String, Object> params) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			PageBean pageBean = (PageBean) params.get("pageBean");
			if (pageBean.getPage() == 1) {
				params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize());
			} else {
				params.put("start", (pageBean.getPage() - 1) * pageBean.getPagesize() + 1);
			}
			params.put("limit", pageBean.getPagesize() * pageBean.getPage());
			ArrayList<Map<String, Object>> list = databaseBakMapper.querydatabaseBakInfoPage(params);
			Long count = databaseBakMapper.querydatabaseBakInfoCount(params);
			hm = new HashMap<String, Object>();
			hm.put("list", list);
			hm.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;

	}

	@Override
	public void bakDatabase(int userId) {
		databaseBakMapper.bakDatabase(userId);
	}

}
