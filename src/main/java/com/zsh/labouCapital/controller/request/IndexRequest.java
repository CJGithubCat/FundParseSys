package com.zsh.labouCapital.controller.request;

import java.util.Date;

public class IndexRequest extends BaseForm{
    private String indexId;

    private String indexCode;

    private String indexSname;

    private String indexEname;

    private Double basePoint;

    private Date baseDate;

    private Date onlineDate;

    private String indexCIntro;

    private String hangqingzoushi;

    private String makeMethod;

    private String weihuxize;

    private String chengfenguliebiao;

    private Integer agencytype;

    private Date dateCreate;

    private String hangqingfilepath;

    private String detailurl;

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getIndexSname() {
        return indexSname;
    }

    public void setIndexSname(String indexSname) {
        this.indexSname = indexSname;
    }

    public String getIndexEname() {
        return indexEname;
    }

    public void setIndexEname(String indexEname) {
        this.indexEname = indexEname;
    }

    public Double getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(Double basePoint) {
        this.basePoint = basePoint;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public String getIndexCIntro() {
        return indexCIntro;
    }

    public void setIndexCIntro(String indexCIntro) {
        this.indexCIntro = indexCIntro;
    }

    public String getHangqingzoushi() {
        return hangqingzoushi;
    }

    public void setHangqingzoushi(String hangqingzoushi) {
        this.hangqingzoushi = hangqingzoushi;
    }

    public String getMakeMethod() {
        return makeMethod;
    }

    public void setMakeMethod(String makeMethod) {
        this.makeMethod = makeMethod;
    }

    public String getWeihuxize() {
        return weihuxize;
    }

    public void setWeihuxize(String weihuxize) {
        this.weihuxize = weihuxize;
    }

    public String getChengfenguliebiao() {
        return chengfenguliebiao;
    }

    public void setChengfenguliebiao(String chengfenguliebiao) {
        this.chengfenguliebiao = chengfenguliebiao;
    }

    public Integer getAgencytype() {
        return agencytype;
    }

    public void setAgencytype(Integer agencytype) {
        this.agencytype = agencytype;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getHangqingfilepath() {
        return hangqingfilepath;
    }

    public void setHangqingfilepath(String hangqingfilepath) {
        this.hangqingfilepath = hangqingfilepath;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }
}

