package com.zp.sys.entity;

import java.io.Serializable;

public class UserType implements Serializable{
	private static final long serialVersionUID = 3808308490273384326L;
	
	private int typeId;
	private String typeName;
	private String name;
	
	
	public UserType() {
		super();
	}
	public UserType(int typeId,String typeName){
		this.typeId=typeId;
		this.typeName=typeName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
