package com.zsh.labouCapital.dao;

import java.util.ArrayList;
import java.util.Map;
import com.zsh.labouCapital.entity.DatabaseBak;

public interface DatabaseBakMapper extends BaseSqlMapper<DatabaseBak> {

	public ArrayList<Map<String, Object>> querydatabaseBakInfoPage(Map<String, Object> params);

	long querydatabaseBakInfoCount(Map<String, Object> params);
	
	public void bakDatabase(int userId);
}
