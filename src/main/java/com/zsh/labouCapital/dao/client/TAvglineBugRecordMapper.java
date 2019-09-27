package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TAvglineBugRecord;
import com.zsh.labouCapital.dao.dto.TAvglineBugRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAvglineBugRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int countByExample(TAvglineBugRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int deleteByExample(TAvglineBugRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int insert(TAvglineBugRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int insertSelective(TAvglineBugRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    List<TAvglineBugRecord> selectByExample(TAvglineBugRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    TAvglineBugRecord selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByExampleSelective(@Param("record") TAvglineBugRecord record, @Param("example") TAvglineBugRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByExample(@Param("record") TAvglineBugRecord record, @Param("example") TAvglineBugRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByPrimaryKeySelective(TAvglineBugRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_avgline_bug_record
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByPrimaryKey(TAvglineBugRecord record);
}