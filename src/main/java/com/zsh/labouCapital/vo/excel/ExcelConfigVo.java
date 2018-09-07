package com.zsh.labouCapital.vo.excel;

import java.util.Arrays;
import java.util.List;

public class ExcelConfigVo {
	private String id;
	private String file_name;
	private List<ExcelLayoutVo> shows_fields_celltype;
	private String[] shows;
	private String[] fields;
	private String[] cell_type;
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public List<ExcelLayoutVo> getShows_fields_celltype() {
		return shows_fields_celltype;
	}
	public void setShows_fields_celltype(List<ExcelLayoutVo> shows_fields_celltype) {
		this.shows_fields_celltype = shows_fields_celltype;
	}
	public String[] getShows() {
		return shows;
	}
	public void setShows(String[] shows) {
		this.shows = shows;
	}
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public String[] getCell_type() {
		return cell_type;
	}
	public void setCell_type(String[] cell_type) {
		this.cell_type = cell_type;
	}
	@Override	
	public String toString(){
	     StringBuilder sb = new StringBuilder();
	     sb.append("ID="+getId()+"\n");
	     sb.append("Name="+getFile_name()+"\n");
	     sb.append("Shows="+Arrays.toString(this.getShows())+"\n");
	     sb.append("Fields="+Arrays.toString(this.getFields())+"\n");
	     sb.append("Cell_type="+Arrays.toString(this.getCell_type())+"\n"); 
	     sb.append("Shows_fields_celltype="+Arrays.toString(this.getShows_fields_celltype().toArray())+"\n");
	     return sb.toString();
	}	
}
