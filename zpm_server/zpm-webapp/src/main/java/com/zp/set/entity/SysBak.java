package com.zp.set.entity;

import com.zp.core.plugin.Page;

import java.util.Date;

/**
 * 备份信息  实体类
 * @author CC
 * */
public class SysBak {
	private int bak_id;//主键ID
	private Date bak_data;//备份时间
	private String bak_file_path;//备份文件路径
	private String bak_file_name;//备份文件的文件名
	private String bak_data_path;//备份数据库数据路径
	private String bak_data_name;//备份数据库数据的文件名
	private int bak_status;//备份状态 1有效 0无效
	private Page page;
	
	public int getBak_id() {
		return bak_id;
	}
	public void setBak_id(int bak_id) {
		this.bak_id = bak_id;
	}
	public Date getBak_data() {
		return bak_data;
	}
	public void setBak_data(Date bak_data) {
		this.bak_data = bak_data;
	}
	public String getBak_file_path() {
		return bak_file_path;
	}
	public void setBak_file_path(String bak_file_path) {
		this.bak_file_path = bak_file_path;
	}
	public String getBak_file_name() {
		return bak_file_name;
	}
	public void setBak_file_name(String bak_file_name) {
		this.bak_file_name = bak_file_name;
	}
	public String getBak_data_path() {
		return bak_data_path;
	}
	public void setBak_data_path(String bak_data_path) {
		this.bak_data_path = bak_data_path;
	}
	public String getBak_data_name() {
		return bak_data_name;
	}
	public void setBak_data_name(String bak_data_name) {
		this.bak_data_name = bak_data_name;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public int getBak_status() {
		return bak_status;
	}
	public void setBak_status(int bak_status) {
		this.bak_status = bak_status;
	}
	
	
}
