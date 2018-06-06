package com.zp.set.entity;

import com.zp.core.plugin.Page;

import java.io.Serializable;
import java.util.Date;


/**
 * 操作日志实体
 */
public class OperationLog2 implements Serializable {
	
	private static final long serialVersionUID = -1162452619114906516L;
	
	private Long id;
	private Long userId;     //操作用户ID
	private String userName; //操作用户名
	private String ip;  //IP地址
	private Date operTime;  //操作时间
	private String resultCode; //操作结果编码   000 成功  001业务异常  999系统异常
	private String operType;   //操作类型
	private String description;
	
	private String clickName;//菜单模块名称
	private String searchName;//搜索关键字
	
	private String startTime;	//查询开始时间
	private String endTime;	//查询结束时间
	
	private Page page;	//分页类
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClickName() {
		return clickName;
	}
	public void setClickName(String clickName) {
		this.clickName = clickName;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	
}
