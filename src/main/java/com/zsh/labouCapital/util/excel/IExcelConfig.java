package com.zsh.labouCapital.util.excel;

import com.zsh.labouCapital.vo.excel.ExcelConfigVo;

/**
 * 从excel 格式配置文件读出excel 数据格式
 */

public interface IExcelConfig {
    public ExcelConfigVo getExcelConfigById(String id);
}
