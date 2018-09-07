package com.zsh.labouCapital.bean;

public class TreeNode {
	private Integer id;// 当前机构id
	private String text;// 机构名称
	private Integer parentNodeId;// 父节点的id
	private Boolean isRoot;// 是否是根节点
	// private String attributesPath;//机构路径
	private String companyPath;// 机构路径
	private String companyType;// 机构类型
	private Boolean isLeaf;// 是否是叶子节点
	private Boolean ischecked = false;// 是否选中[分配模块用]

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(Integer parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getCompanyPath() {
		return companyPath;
	}

	public void setCompanyPath(String companyPath) {
		this.companyPath = companyPath;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIschecked() {
		return ischecked;
	}

	public void setIschecked(Boolean ischecked) {
		this.ischecked = ischecked;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", parentNodeId=" + parentNodeId + ", isRoot=" + isRoot
				+ ", companyPath=" + companyPath + ", companyType=" + companyType + ", isLeaf=" + isLeaf
				+ ", ischecked=" + ischecked + "]";
	}
}
