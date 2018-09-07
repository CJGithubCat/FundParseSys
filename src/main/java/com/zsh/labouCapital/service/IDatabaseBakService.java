package com.zsh.labouCapital.service;

import java.util.HashMap;
import java.util.Map;

public interface IDatabaseBakService{
	public HashMap<String, Object> querydatabaseBak(Map<String, Object> params);
	
	public void bakDatabase(int userId);
}
