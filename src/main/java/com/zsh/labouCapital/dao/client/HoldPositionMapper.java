package com.zsh.labouCapital.dao.client;

import java.util.List;

import com.zsh.labouCapital.entity.ExpectFund;
import com.zsh.labouCapital.entity.HoldPosition;


public interface HoldPositionMapper {

    /**   
     * @Title: queryHoldPositionList   
     * @Description: TODO   
     * @param: @param holdPositionParam
     * @param: @return      
     * @return: List<HoldPosition>      
     * @throws   
     */
    List<HoldPosition> queryHoldPositionList(HoldPosition holdPositionParam);

    /**   
     * @Title: insertHoldPositionInfo   
     * @Description: TODO   
     * @param: @param holdPosition      
     * @return: void      
     * @throws   
     */
    void insertHoldPositionInfo(HoldPosition holdPosition);

    /**   
     * @Title: getTheLastHoldPosition   
     * @Description: TODO   
     * @param: @param fundCode
     * @param: @return      
     * @return: HoldPosition      
     * @throws   
     */
    HoldPosition getTheLastHoldPosition(String fundCode);
    
}
