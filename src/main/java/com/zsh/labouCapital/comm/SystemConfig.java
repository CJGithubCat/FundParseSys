package com.zsh.labouCapital.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("SystemConfig")
public class SystemConfig {

	@Value("${excelUploadPath}")
    private String excelUploadPath;

	public String getExcelUploadPath() {
		return excelUploadPath;
	}
	public void setExcelUploadPath(String excelUploadPath) {
		this.excelUploadPath = excelUploadPath;
	}
}
