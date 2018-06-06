package com.zp.set.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.zp.core.plugin.Page;

public class HotDocument implements Serializable {
	
	private static final long serialVersionUID = 1076054653508420832L;
	private Page page;
	private String typeName;//类型名称
	private String file_name;//文件名称
	private String userName;//上传人
	private String userId;
	private int document_id;//文件id
	private int read_total;//总阅读数量
	private int collect_total;//总收藏
	private int down_total;//总下载
	private int total;//上面三个总之和
	private int scNumber;//收藏数量
	private int xzNumber;//下载数量
	private int ckNumber;//查看数量
	private int nowTotal;//上面三个总之和
	private int classId;//类型id
	private int mainId;//主id
	
	private String pointName;//点击按钮
	private String type;//查询的类型
	private String startTime;
	private String endTime;
	private Date create_time;//创建时间
	private String time;//活动时间

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
		java.util.Date data = sdf.parse(time);
		sdf.format(data);
		this.time = sdf.format(data);
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Page getPage() {
		return page;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getFile_name() {
		return file_name;
	}

	public int getDocument_id() {
		return document_id;
	}

	public int getRead_total() {
		return read_total;
	}

	public int getCollect_total() {
		return collect_total;
	}

	public int getDown_total() {
		return down_total;
	}

	public int getTotal() {
		return total;
	}

	public int getScNumber() {
		return scNumber;
	}

	public int getXzNumber() {
		return xzNumber;
	}

	public int getCkNumber() {
		return ckNumber;
	}

	public int getNowTotal() {
		return nowTotal;
	}

	public int getClassId() {
		return classId;
	}

	public int getMainId() {
		return mainId;
	}

	public String getType() {
		return type;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}

	public void setRead_total(int read_total) {
		this.read_total = read_total;
	}

	public void setCollect_total(int collect_total) {
		this.collect_total = collect_total;
	}

	public void setDown_total(int down_total) {
		this.down_total = down_total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setScNumber(int scNumber) {
		this.scNumber = scNumber;
	}

	public void setXzNumber(int xzNumber) {
		this.xzNumber = xzNumber;
	}

	public void setCkNumber(int ckNumber) {
		this.ckNumber = ckNumber;
	}

	public void setNowTotal(int nowTotal) {
		this.nowTotal = nowTotal;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public void setMainId(int mainId) {
		this.mainId = mainId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
