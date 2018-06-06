package com.zp.sys.util;

import java.io.Serializable;
import java.util.List;
/**
 * JsonModel传回前台的模型   code状态码    msg信息  obj 传回对象
 * @author CC
 * @since 2016-07-22
 * */
public class JsonModel implements Serializable {
	private static final long serialVersionUID = 9106902882602433353L;
	private Integer code;
	private String msg;
	private Object obj;
	public JsonModel(Integer code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	
	

	public JsonModel() {
		super();
	}
	


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
