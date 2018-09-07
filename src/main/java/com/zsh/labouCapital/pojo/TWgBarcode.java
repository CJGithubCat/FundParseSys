package com.zsh.labouCapital.pojo;

/**
 * @Package:com.chinagps.webgis.pojo
 * @ClassName:Barcode
 * @Description:TODO
 * @author:liujie
 * @date:2016-3-1 下午3:39:48
 */
public class TWgBarcode {

	private Long id;// 条形码id
	private Long unit_id;// 终端id
	private Integer bc_type;// 条形码类型（1终端条形码2导航条形码）
	private String content;// 条形码内容
	private Long son_unit_id_number;// 子机编号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}

	public Integer getBc_type() {
		return bc_type;
	}

	public void setBc_type(Integer bc_type) {
		this.bc_type = bc_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSon_unit_id_number() {
		return son_unit_id_number;
	}

	public void setSon_unit_id_number(Long son_unit_id_number) {
		this.son_unit_id_number = son_unit_id_number;
	}

}
