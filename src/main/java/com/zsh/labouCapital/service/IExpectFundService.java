package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.ExpectFund;


public interface IExpectFundService extends BaseService<ExpectFund>{
    public List<ExpectFund> queryExceptFundList(ExpectFund expectFund);
	
	public void insertExceptFundInfos(List<ExpectFund> addList);

}
