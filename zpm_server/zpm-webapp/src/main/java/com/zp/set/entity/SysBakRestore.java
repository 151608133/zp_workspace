package com.zp.set.entity;

import java.util.Date;

/**
 * 还原信息  实体类
 * @author CC
 * */
public class SysBakRestore {
	private int restore_id;//主键ID
	private int file_bak_id;//文件还原的备份id
	private int data_bak_id;//数据库数据还原的备份id
	private String restore_remark;//备注信息
	private Date restore_data;//还原时间
	private long user_id;//用户id
	private int restore_status;//状态 1 刚完成还原，没有提示用户 2 已提示用户
	public int getRestore_id() {
		return restore_id;
	}
	public void setRestore_id(int restore_id) {
		this.restore_id = restore_id;
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
	public String getRestore_remark() {
		return restore_remark;
	}
	public void setRestore_remark(String restore_remark) {
		this.restore_remark = restore_remark;
	}
	public Date getRestore_data() {
		return restore_data;
	}
	public void setRestore_data(Date restore_data) {
		this.restore_data = restore_data;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getRestore_status() {
		return restore_status;
	}
	public void setRestore_status(int restore_status) {
		this.restore_status = restore_status;
	}
	
	
}
