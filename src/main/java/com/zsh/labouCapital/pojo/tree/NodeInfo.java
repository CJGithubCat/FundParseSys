package com.zsh.labouCapital.pojo.tree;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * @author Lxb
 * @Description 数节点实体类
 * @Date 2015-05-27 下午 14:06
 */
public class NodeInfo implements Serializable{

	
	private static final long serialVersionUID = -1020771709883128425L;
	
	/**机构编号 */
	private String id;
	/** 机构名称*/
	private String text;
	/** 子机构列表*/
	private List<NodeInfo> children;
	/** 节点图标*/
	private String icon;
	/** Object对象，节点的状态，包括三个属性，如下*/
	private NodeState state;
	/** 节点的列属性，自定义*/
	private Map<String,String> li_attr;
	/** 节点链接属性，自定义*/
	private Map<String,String> a_attr;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public List<NodeInfo> getChildren() {
		return children;
	}
	public void setChildren(List<NodeInfo> children) {
		this.children = children;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public NodeState getState() {
		return state;
	}
	public void setState(NodeState state) {
		this.state = state;
	}
	public Map<String, String> getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(Map<String, String> li_attr) {
		this.li_attr = li_attr;
	}
	public Map<String, String> getA_attr() {
		return a_attr;
	}
	public void setA_attr(Map<String, String> a_attr) {
		this.a_attr = a_attr;
	}
	
}
