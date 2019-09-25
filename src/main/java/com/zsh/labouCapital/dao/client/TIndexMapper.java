package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TIndex;
import com.zsh.labouCapital.dao.dto.TIndexCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TIndexMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int countByExample(TIndexCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByExample(TIndexCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByPrimaryKey(String indexId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insert(TIndex record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insertSelective(TIndex record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    List<TIndex> selectByExample(TIndexCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    TIndex selectByPrimaryKey(String indexId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") TIndex record, @Param("example") TIndexCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExample(@Param("record") TIndex record, @Param("example") TIndexCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKeySelective(TIndex record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKey(TIndex record);
}