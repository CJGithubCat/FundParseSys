package com.zsh.labouCapital.pojo;

import java.io.Serializable;

/**
 * 类功能：权限类;
 * */
public class TWgPermission implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer permissionid;//功能id，11004，第一位表示权限功能分类,第二,三位指明此大类中的主菜单顺序号，后二位是本主菜单子项的顺序号；
	private String itemname;//菜单项或按钮项目的名称，应与网页中名字对应；
	private String description;//中文描述，最好与菜单或按钮描述相同；
	private Integer parentid;//父节点id
	private Integer orderid;//在父节点下的默认顺序号;
	private String iconurl;//菜单或按钮图标url路径
	private String url;//菜单对应的url地址，如果不需要链接则此url为空
	private Integer categoryid;//分类id（1：一类（主菜单类），2：二类（地图操作类），3：三类（指令操作类），4：四类（面板显示控制类），5：五类（提醒提示类））
	private Integer isvalid;//是否启用（0：没有启用（默认），1：已启用）
	private Integer havsubitems;//是否有子功能项（0：无子功能项，1：有子功能项）
	private String demo;//备注说明
	
	public Integer getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public Integer getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}
	public Integer getHavsubitems() {
		return havsubitems;
	}
	public void setHavsubitems(Integer havsubitems) {
		this.havsubitems = havsubitems;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "TWgPermission [permissionid=" + permissionid + ", itemname=" + itemname + ", description=" + description
				+ ", parentid=" + parentid + ", orderid=" + orderid + ", iconurl=" + iconurl + ", url=" + url
				+ ", categoryid=" + categoryid + ", isvalid=" + isvalid + ", havsubitems=" + havsubitems + ", demo="
				+ demo + "]";
	}
}
