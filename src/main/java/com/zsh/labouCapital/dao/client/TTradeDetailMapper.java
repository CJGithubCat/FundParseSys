package com.zsh.labouCapital.dao.client;

import com.zsh.labouCapital.dao.dto.TTradeDetail;
import com.zsh.labouCapital.dao.dto.TTradeDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTradeDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int countByExample(TTradeDetailCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int deleteByExample(TTradeDetailCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int insert(TTradeDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int insertSelective(TTradeDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    List<TTradeDetail> selectByExample(TTradeDetailCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    TTradeDetail selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByExampleSelective(@Param("record") TTradeDetail record, @Param("example") TTradeDetailCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByExample(@Param("record") TTradeDetail record, @Param("example") TTradeDetailCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByPrimaryKeySelective(TTradeDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_detail
     *
     * @mbggenerated Fri Sep 27 15:39:41 CST 2019
     */
    int updateByPrimaryKey(TTradeDetail record);
}