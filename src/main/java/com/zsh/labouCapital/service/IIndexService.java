package com.zsh.labouCapital.service;

import java.util.List;

import com.zsh.labouCapital.controller.request.IndexRequest;
import com.zsh.labouCapital.dao.dto.TIndex;
import com.zsh.labouCapital.dao.dto.TIndexInfo;
import com.zsh.labouCapital.dto.JobDto;
import com.zsh.labouCapital.exception.GenericBizException;

/**
 *<p> Title: IIndexService </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年9月29日
 */
public interface IIndexService {

    /**   
     * @Title: queryIndexCount   
     * @Description: TODO   
     * @param: @param indexRequest
     * @param: @return      
     * @return: int      
     * @throws   
     */
    int queryIndexCount(IndexRequest indexRequest);

    /**   
     * @Title: queryIndexList   
     * @Description: TODO   
     * @param: @param indexRequest
     * @param: @return      
     * @return: List<TIndex>      
     * @throws   
     */
    List<TIndex> queryIndexList(IndexRequest indexRequest);

    /**
     * @throws GenericBizException    
     * @Title: addIndexInfo   
     * @Description: TODO   
     * @param: @param fundRequest
     * @param: @return      
     * @return: int      
     * @throws   
     */
    int addIndexInfo(IndexRequest indexRequest) throws GenericBizException;

    /**
     * @throws Exception    
     * @Title: parseIndexInfo   
     * @Description: TODO   
     * @param: @param jobDto      
     * @return: void      
     * @throws   
     */
    void parseIndexInfo(JobDto jobDto) throws Exception;

    /**   
     * @Title: reletationIndexData   
     * @Description: 关联t_index和t_index_info的关系   
     * @param:       
     * @return: void      
     * @throws   
     */
    void reletationIndexData();
}
