package com.zp.set.entity;

import com.zp.core.plugin.Page;

import java.io.Serializable;
import java.util.Date;


/**
 * 公告  实体类
 * @author CC
 * */
public class Notice implements Serializable{
	private static final long serialVersionUID = 5232035378967603524L;
	
	private Long id;		//序号ID
	private Long userId;	//发布用户ID
	private String noticeTitle;		//公告标题
	private String noticeAuthor;		//公告发布人
	private String noticeContent;		//公告内容
	private Date createTime;		//创建时间
	private Date statusTime;	//状态时间
	private int isRead;			//是否已读
	private String sendObject;		//推送对象   id
	private String sendObjects;		//推送对象   id
	
	private int col1;		//扩展字段1
	private String col2; 	//扩展字段2   推送对象  名字
	private Page page;
	private String stateCd;		//状态   00A正常  00X 已删除
	
	private Long roleId;	//推送角色ID
	
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
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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
	public String getSendObject() {
		
		return sendObject;
	}
	
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
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
	public String getNoticeAuthor() {
		return noticeAuthor;
	}
	public void setNoticeAuthor(String noticeAuthor) {
		this.noticeAuthor = noticeAuthor;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/*
	 * if(sendObject!=null && !sendObject.equals("") && sendObject.length()>2){
			sendObject = sendObject.substring(1, sendObject.length()-1);
		}
	*/
	public String getSendObjects() {
		sendObjects = sendObject;
		return sendObjects;
	}
}
