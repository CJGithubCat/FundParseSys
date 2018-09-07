package com.zsh.labouCapital.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zsh.labouCapital.pojo.tree.NodeInfo;

/**
 * 用户权限实体类
 * @author Lxb
 * @Date 2015-05-27 上午 11:45
 */
public class DataPermission implements Serializable{

	
	private static final long serialVersionUID = 9020741539811346850L;
	/** 用户ID*/
	private Long customerId;
	/** 用户名称，也是机构名称*/
	private String custName;
	/** 用户子机构编号,如果是集团客户则该字段为空*/
	private String custcoCode;
	/** 分公司机构编号*/
	private String subcoNo;
	/** 集团客户根节点ID*/
	private Long custcoNo;
	/** 用户机构分组信息,用树结点结构保存(用户所属机构作为根节点机构)*/
	private NodeInfo nodes;
	/** 用户下级机构id信息,多个用,隔开*/
	private String subOrgIds;
	/** 用户机构信息key:value=机构ID:机构Name*/
	private List<Map<String,String>> orgList;
	/** 用户所属分组机构名称*/
	private String orgName;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustcoCode() {
		return custcoCode;
	}
	public void setCustcoCode(String custcoCode) {
		this.custcoCode = custcoCode;
	}
	public Long getCustcoNo() {
		return custcoNo;
	}
	public void setCustcoNo(Long custcoNo) {
		this.custcoNo = custcoNo;
	}
	public NodeInfo getNodes() {
		return nodes;
	}
	public void setNodes(NodeInfo nodes) {
		this.nodes = nodes;
	}
	public String getSubOrgIds() {
		return subOrgIds;
	}
	public void setSubOrgIds(String subOrgIds) {
		this.subOrgIds = subOrgIds;
	}
	public String getSubcoNo() {
		return subcoNo;
	}
	public void setSubcoNo(String subcoNo) {
		this.subcoNo = subcoNo;
	}
	public List<Map<String, String>> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<Map<String, String>> orgList) {
		this.orgList = orgList;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
