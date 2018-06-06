package com.zp.sys.entity;

import com.zp.core.plugin.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class Role implements Serializable {

	private static final long serialVersionUID = -6179411339504526803L;
	
	private int roleId;
	private String roleName;
	private long userId;
	private String description;
	private Date  createDate;
	private int userCount;
	private Page page;
	private String stateCd;
	private String roleType;//角色类型

	/**
	 * 关联菜单
	 */
	private List<Function> funcs;
	
	



	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Function> getFuncs() {
		return funcs;
	}
	public void setFuncs(List<Function> funcs) {
		this.funcs = funcs;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
