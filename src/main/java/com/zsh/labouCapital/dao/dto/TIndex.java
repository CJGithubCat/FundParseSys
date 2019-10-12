package com.zsh.labouCapital.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class TIndex implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.index_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexSname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.index_ename
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexEname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.base_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Double basePoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.base_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date baseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.online_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date onlineDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.index_c_intro
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String indexCIntro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.hangqingzoushi
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String hangqingzoushi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.make_method
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String makeMethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.weihuxize
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String weihuxize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.chengfenguliebiao
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String chengfenguliebiao;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.agencyType
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Integer agencytype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.hangqingfilepath
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String hangqingfilepath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_index.detailUrl
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private String detailurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_index
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.index_id
     *
     * @return the value of t_index.index_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.index_id
     *
     * @param indexId the value for t_index.index_id
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.index_code
     *
     * @return the value of t_index.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexCode() {
        return indexCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.index_code
     *
     * @param indexCode the value for t_index.index_code
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.index_sname
     *
     * @return the value of t_index.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexSname() {
        return indexSname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.index_sname
     *
     * @param indexSname the value for t_index.index_sname
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexSname(String indexSname) {
        this.indexSname = indexSname == null ? null : indexSname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.index_ename
     *
     * @return the value of t_index.index_ename
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexEname() {
        return indexEname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.index_ename
     *
     * @param indexEname the value for t_index.index_ename
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexEname(String indexEname) {
        this.indexEname = indexEname == null ? null : indexEname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.base_point
     *
     * @return the value of t_index.base_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Double getBasePoint() {
        return basePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.base_point
     *
     * @param basePoint the value for t_index.base_point
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setBasePoint(Double basePoint) {
        this.basePoint = basePoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.base_date
     *
     * @return the value of t_index.base_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getBaseDate() {
        return baseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.base_date
     *
     * @param baseDate the value for t_index.base_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.online_date
     *
     * @return the value of t_index.online_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getOnlineDate() {
        return onlineDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.online_date
     *
     * @param onlineDate the value for t_index.online_date
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.index_c_intro
     *
     * @return the value of t_index.index_c_intro
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getIndexCIntro() {
        return indexCIntro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.index_c_intro
     *
     * @param indexCIntro the value for t_index.index_c_intro
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setIndexCIntro(String indexCIntro) {
        this.indexCIntro = indexCIntro == null ? null : indexCIntro.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.hangqingzoushi
     *
     * @return the value of t_index.hangqingzoushi
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getHangqingzoushi() {
        return hangqingzoushi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.hangqingzoushi
     *
     * @param hangqingzoushi the value for t_index.hangqingzoushi
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setHangqingzoushi(String hangqingzoushi) {
        this.hangqingzoushi = hangqingzoushi == null ? null : hangqingzoushi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.make_method
     *
     * @return the value of t_index.make_method
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getMakeMethod() {
        return makeMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.make_method
     *
     * @param makeMethod the value for t_index.make_method
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setMakeMethod(String makeMethod) {
        this.makeMethod = makeMethod == null ? null : makeMethod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.weihuxize
     *
     * @return the value of t_index.weihuxize
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getWeihuxize() {
        return weihuxize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.weihuxize
     *
     * @param weihuxize the value for t_index.weihuxize
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setWeihuxize(String weihuxize) {
        this.weihuxize = weihuxize == null ? null : weihuxize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.chengfenguliebiao
     *
     * @return the value of t_index.chengfenguliebiao
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getChengfenguliebiao() {
        return chengfenguliebiao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.chengfenguliebiao
     *
     * @param chengfenguliebiao the value for t_index.chengfenguliebiao
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setChengfenguliebiao(String chengfenguliebiao) {
        this.chengfenguliebiao = chengfenguliebiao == null ? null : chengfenguliebiao.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.agencyType
     *
     * @return the value of t_index.agencyType
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Integer getAgencytype() {
        return agencytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.agencyType
     *
     * @param agencytype the value for t_index.agencyType
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setAgencytype(Integer agencytype) {
        this.agencytype = agencytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.date_create
     *
     * @return the value of t_index.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.date_create
     *
     * @param dateCreate the value for t_index.date_create
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.hangqingfilepath
     *
     * @return the value of t_index.hangqingfilepath
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getHangqingfilepath() {
        return hangqingfilepath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.hangqingfilepath
     *
     * @param hangqingfilepath the value for t_index.hangqingfilepath
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setHangqingfilepath(String hangqingfilepath) {
        this.hangqingfilepath = hangqingfilepath == null ? null : hangqingfilepath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_index.detailUrl
     *
     * @return the value of t_index.detailUrl
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public String getDetailurl() {
        return detailurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_index.detailUrl
     *
     * @param detailurl the value for t_index.detailUrl
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl == null ? null : detailurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_index
     *
     * @mbggenerated Fri Oct 11 17:37:08 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indexId=").append(indexId);
        sb.append(", indexCode=").append(indexCode);
        sb.append(", indexSname=").append(indexSname);
        sb.append(", indexEname=").append(indexEname);
        sb.append(", basePoint=").append(basePoint);
        sb.append(", baseDate=").append(baseDate);
        sb.append(", onlineDate=").append(onlineDate);
        sb.append(", indexCIntro=").append(indexCIntro);
        sb.append(", hangqingzoushi=").append(hangqingzoushi);
        sb.append(", makeMethod=").append(makeMethod);
        sb.append(", weihuxize=").append(weihuxize);
        sb.append(", chengfenguliebiao=").append(chengfenguliebiao);
        sb.append(", agencytype=").append(agencytype);
        sb.append(", dateCreate=").append(dateCreate);
        sb.append(", hangqingfilepath=").append(hangqingfilepath);
        sb.append(", detailurl=").append(detailurl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}