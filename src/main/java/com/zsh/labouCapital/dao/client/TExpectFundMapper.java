package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TExpectFund;
import com.zsh.labouCapital.dao.dto.TExpectFundCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TExpectFundMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int countByExample(TExpectFundCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByExample(TExpectFundCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insert(TExpectFund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int insertSelective(TExpectFund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    List<TExpectFund> selectByExample(TExpectFundCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    TExpectFund selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") TExpectFund record, @Param("example") TExpectFundCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByExample(@Param("record") TExpectFund record, @Param("example") TExpectFundCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKeySelective(TExpectFund record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_expect_fund
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    int updateByPrimaryKey(TExpectFund record);
}