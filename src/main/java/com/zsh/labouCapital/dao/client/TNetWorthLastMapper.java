package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TNetWorthLast;
import com.zsh.labouCapital.dao.dto.TNetWorthLastCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNetWorthLastMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int countByExample(TNetWorthLastCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int deleteByExample(TNetWorthLastCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int insert(TNetWorthLast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int insertSelective(TNetWorthLast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    List<TNetWorthLast> selectByExample(TNetWorthLastCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    TNetWorthLast selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByExampleSelective(@Param("record") TNetWorthLast record, @Param("example") TNetWorthLastCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByExample(@Param("record") TNetWorthLast record, @Param("example") TNetWorthLastCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByPrimaryKeySelective(TNetWorthLast record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_net_worth_last
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByPrimaryKey(TNetWorthLast record);
}