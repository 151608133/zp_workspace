package com.zp.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志实体
 * @author TangHaiBo
 * @since 2016-07-25
 */
public class OperationLog implements Serializable {
	private static final long serialVersionUID = -5268898209820709788L;
	
	private Long id;
	private Long userId;     //操作用户ID
	private String userName; //操作用户名
	private String ip;  //IP地址
	private Date operTime;  //操作时间
	private int funcId;     //功能ID
	private int subFuncId;     //子菜单功能ID
	private String resultCode; //操作结果编码   000 成功  001业务异常  999系统异常
	private String description;   //操作内容描述
	private String statusCd;
	private String language;
	
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
	public int getFuncId() {
		return funcId;
	}
	public void setFuncId(int funcId) {
		this.funcId = funcId;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public int getSubFuncId() {
		return subFuncId;
	}

	public void setSubFuncId(int subFuncId) {
		this.subFuncId = subFuncId;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
