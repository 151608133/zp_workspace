package com.zp.set.entity;

import java.util.Date;

/**
 * 备份还原操作  实体类
 * @author CC
 * */
public class SysBakTask {
	private int task_id;//主键ID
	private long user_id;//用户ID
	private int bak_id;//备份ID
	private int file_bak_id;//文件还原的备份id
	private int data_bak_id;//数据库数据还原的备份id
	private int task_status;//状态 1 备份中 2还原中
	private Date task_data;//开始时间
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getBak_id() {
		return bak_id;
	}
	public void setBak_id(int bak_id) {
		this.bak_id = bak_id;
	}
	public int getFile_bak_id() {
		return file_bak_id;
	}
	public void setFile_bak_id(int file_bak_id) {
		this.file_bak_id = file_bak_id;
	}
	public int getData_bak_id() {
		return data_bak_id;
	}
	public void setData_bak_id(int data_bak_id) {
		this.data_bak_id = data_bak_id;
	}
	public int getTask_status() {
		return task_status;
	}
	public void setTask_status(int task_status) {
		this.task_status = task_status;
	}
	public Date getTask_data() {
		return task_data;
	}
	public void setTask_data(Date task_data) {
		this.task_data = task_data;
	}
	
	
}
