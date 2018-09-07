package com.zsh.labouCapital.pojo;

/**
 * @Package:com.gboss.pojo
 * @ClassName:Barcode
 * @Description:TODO
 * @author:liujie
 * @date:2016-01-29 下午3:39:48
 */
public class TWgMotherSonUnit {

	private Long mother_unit_id; // 母机终端ID
	private Long son_unit_id;// 子机终端id
	private Long son_unit_id_number;// 子机编号

	public Long getMother_unit_id() {
		return mother_unit_id;
	}

	public void setMother_unit_id(Long mother_unit_id) {
		this.mother_unit_id = mother_unit_id;
	}

	public Long getSon_unit_id() {
		return son_unit_id;
	}

	public void setSon_unit_id(Long son_unit_id) {
		this.son_unit_id = son_unit_id;
	}

	public Long getSon_unit_id_number() {
		return son_unit_id_number;
	}

	public void setSon_unit_id_number(Long son_unit_id_number) {
		this.son_unit_id_number = son_unit_id_number;
	}

}
