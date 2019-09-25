package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TFundType implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund_type.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund_type.type_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private String typeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund_type.infomation
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private String infomation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund_type.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund_type.date_update
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private Date dateUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund_type.id
     *
     * @return the value of t_fund_type.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund_type.id
     *
     * @param id the value for t_fund_type.id
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund_type.type_name
     *
     * @return the value of t_fund_type.type_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund_type.type_name
     *
     * @param typeName the value for t_fund_type.type_name
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund_type.infomation
     *
     * @return the value of t_fund_type.infomation
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public String getInfomation() {
        return infomation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund_type.infomation
     *
     * @param infomation the value for t_fund_type.infomation
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setInfomation(String infomation) {
        this.infomation = infomation == null ? null : infomation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund_type.date_create
     *
     * @return the value of t_fund_type.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund_type.date_create
     *
     * @param dateCreate the value for t_fund_type.date_create
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund_type.date_update
     *
     * @return the value of t_fund_type.date_update
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund_type.date_update
     *
     * @param dateUpdate the value for t_fund_type.date_update
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fund_type
     *
     * @mbggenerated Sun Sep 08 17:14:58 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeName=").append(typeName);
        sb.append(", infomation=").append(infomation);
        sb.append(", dateCreate=").append(dateCreate);
        sb.append(", dateUpdate=").append(dateUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}