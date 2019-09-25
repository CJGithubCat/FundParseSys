package com.zsh.labouCapital.service;
import java.util.List;

import com.zsh.labouCapital.entity.ExpectFund;


public interface IExpectFundService {
	
	public void insertExceptFundInfos(List<ExpectFund> addList);

    /**   
     * @Title: queryExpectFundList   
     * @Description: TODO   
     * @param: @param object
     * @param: @return      
     * @return: List<ExpectFund>      
     * @throws   
     */
    public List<ExpectFund> queryExpectFundList(ExpectFund expectFund);

}
