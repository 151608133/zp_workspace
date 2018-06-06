package com.zp.set.entity;

import com.zp.core.plugin.Page;

import java.io.Serializable;
import java.util.Date;


/**
 * 个人消息实体类
 * @author CC
 * */
public class SysMessageQueue implements Serializable {
	private static final long serialVersionUID = -7445235142640836819L;
	
	private Long id;		//序号ID
	private Long userId;		//用户ID
	private String msgTitle;	//消息标题
	private String msgContent; 	//消息内容
	private Date createTime;	//创建时间
	private Date statusTime;		//状态时间
	private int isRead;		//是否已读		0 已读  1未读
	private int col1;		//扩展字段  1
	private String col2;	//扩展字段  2
	private String templateCode;		//消息模板
	private String orderItemId;		//订单号
	private String language;
	
	private Page page;
	
	
	
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
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
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public int getCol1() {
		return col1;
	}
	public void setCol1(int col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
