package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TMarketSituation;
import com.zsh.labouCapital.dao.dto.TMarketSituationCriteria;
import com.zsh.labouCapital.dao.dto.TMarketSituationKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMarketSituationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int countByExample(TMarketSituationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int deleteByExample(TMarketSituationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int deleteByPrimaryKey(TMarketSituationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int insert(TMarketSituation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int insertSelective(TMarketSituation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    List<TMarketSituation> selectByExample(TMarketSituationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    TMarketSituation selectByPrimaryKey(TMarketSituationKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByExampleSelective(@Param("record") TMarketSituation record, @Param("example") TMarketSituationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByExample(@Param("record") TMarketSituation record, @Param("example") TMarketSituationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByPrimaryKeySelective(TMarketSituation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_market_situation
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    int updateByPrimaryKey(TMarketSituation record);
}