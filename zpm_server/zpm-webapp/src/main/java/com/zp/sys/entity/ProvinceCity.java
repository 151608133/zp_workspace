package com.zp.sys.entity;

import java.io.Serializable;

/**
 * 省份城市数据实体类
 * @author TangHb
 * @since 2016-07-15
 */
public class ProvinceCity implements Serializable {
	private static final long serialVersionUID = -2726709590069876682L;
	
	private int id;
	private String name;
	private int uid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUid() {
		return uid;
	}
	public void setU_id(int uid) {
		this.uid = uid;
	}
}
