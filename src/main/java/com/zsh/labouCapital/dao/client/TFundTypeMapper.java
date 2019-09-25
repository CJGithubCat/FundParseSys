package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TFundType;
import com.zsh.labouCapital.dao.dto.TFundTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFundTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int countByExample(TFundTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByExample(TFundTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insert(TFundType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insertSelective(TFundType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    List<TFundType> selectByExample(TFundTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    TFundType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") TFundType record, @Param("example") TFundTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExample(@Param("record") TFundType record, @Param("example") TFundTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKeySelective(TFundType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKey(TFundType record);
}