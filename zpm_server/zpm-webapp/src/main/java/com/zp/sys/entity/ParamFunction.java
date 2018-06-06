package com.zp.sys.entity;

import java.io.Serializable;
import java.util.List;

public class ParamFunction implements Serializable {
	private static final long serialVersionUID = -2726709540069876682L;
	
	private int funcId;
	private String funcName;
	private String image;
	private String url;
	private String qtip;
	private Integer sort_num;
	private int type;   //是否是菜单类型   1是菜单类型   0表示非菜单类型如按钮控件
	private int parentFuncId;
	private String description;
	private String css;
	private int roleId;
	private int appFuncType; //APP功能类型 1表示菜单  2表示按钮
 	private String appFuncStr; //APP功能权限 
	/**
	 * 父功能名称
	 */
	private String parent_func_name;
	
	public ParamFunction(){}
	
	
	public ParamFunction(int funcId,int roleId){
		this.funcId = funcId;
		this.roleId = roleId;
	}
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * true:默认为叶子结点，即子菜单
	 */
	private boolean leaf = true;
	private List<Function> subChildren;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public Integer getSort_num() {
		return sort_num;
	}
	public void setSort_num(Integer sort_num) {
		this.sort_num = sort_num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<Function> getSubChildren() {
		return subChildren;
	}


	public void setSubChildren(List<Function> subChildren) {
		this.subChildren = subChildren;
	}


	public int getFuncId() {
		return funcId;
	}
	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getParentFuncId() {
		return parentFuncId;
	}
	public void setParentFuncId(int parentFuncId) {
		this.parentFuncId = parentFuncId;
	}
	public String getParent_func_name() {
		return parent_func_name;
	}
	public void setParent_func_name(String parent_func_name) {
		this.parent_func_name = parent_func_name;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}


	public int getAppFuncType() {
		return appFuncType;
	}


	public void setAppFuncType(int appFuncType) {
		this.appFuncType = appFuncType;
	}


	public String getAppFuncStr() {
		return appFuncStr;
	}


	public void setAppFuncStr(String appFuncStr) {
		this.appFuncStr = appFuncStr;
	}
}
