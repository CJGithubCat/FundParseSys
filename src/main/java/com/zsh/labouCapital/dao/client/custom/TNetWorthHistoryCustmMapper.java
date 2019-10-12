package com.zsh.labouCapital.dao.client.custom;

import com.zsh.labouCapital.dao.dto.TNetWorthHistory;
import com.zsh.labouCapital.dao.dto.TNetWorthHistoryCriteria;

public interface TNetWorthHistoryCustmMapper {
    
    /**
     * @Title: getMaxDateNetWorthByExample   
     * @Description: 最大日期的净值   
     * @param: @param example
     * @param: @return      
     * @return: TNetWorthHistory      
     * @throws
     */
    TNetWorthHistory getMaxDateNetWorthByExample(TNetWorthHistoryCriteria example);
    
}