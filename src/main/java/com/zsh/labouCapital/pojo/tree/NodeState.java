package com.zsh.labouCapital.pojo.tree;

import java.io.Serializable;
/**
 * @author Lxb
 * @Description 节点的状态实体类
 * @Date 2015-05-27 下午 14:12
 */
public class NodeState implements Serializable {

	
	private static final long serialVersionUID = 6893392807317773816L;
	
	/** 是否打开,默认true*/
	private Boolean opened = false;
	/** 是否可操作,默认false*/
	private Boolean disabled = false;
	/** 是否选中 ,默认true*/
	private Boolean selected = false;
	
	public Boolean getOpened() {
		return opened;
	}
	public void setOpened(Boolean opened) {
		this.opened = opened;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	

}
