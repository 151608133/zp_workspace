package com.zp.set.entity;

import java.util.List;

public class LitterType {
	
	private String name;
	private String id;
	private List<List<Object>> data;
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public List<List<Object>> getData() {
		return data;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setData(List<List<Object>> data) {
		this.data = data;
	}
	
	
}
