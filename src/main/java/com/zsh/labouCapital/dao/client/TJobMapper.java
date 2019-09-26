package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TJob;
import com.zsh.labouCapital.dao.dto.TJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int countByExample(TJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByExample(TJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insert(TJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insertSelective(TJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    List<TJob> selectByExample(TJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    TJob selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") TJob record, @Param("example") TJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExample(@Param("record") TJob record, @Param("example") TJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKeySelective(TJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_job
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKey(TJob record);
}