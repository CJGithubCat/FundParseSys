package com.zsh.labouCapital.comm;
/**
 *<p> Title: DayTypeEnum </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年6月6日
 */
public enum DayTypeEnum {
    //正常工作日对应结果为 0, 法定节假日对应结果为 1, 节假日调休补班对应的结果为 2，休息日对应结果为 3 
    DAY_WORK_DAY(0,"工作日"),
    DAY_FESTIVAL_DAY(1,"法定节假日"),
    DAY_ADJUST_DAY(2,"节假日调休补班"),
    DAY_REST_DAY(3,"休息日");
    
    private Integer code;
    private String desc;
    
    private DayTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
