package com.zsh.labouCapital.vo.excel;

public class ExcelLayoutVo {
    private String show;
    private String field;
    private String cell_type;
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCell_type() {
		return cell_type;
	}
	public void setCell_type(String cell_type) {
		this.cell_type = cell_type;
	}
	
	@Override
	public String toString(){
	    StringBuilder sb = new StringBuilder();
	    sb.append(getShow()+",");
	    sb.append(getField()+",");
	    sb.append(getCell_type());
	    return sb.toString();
	}
	
}
