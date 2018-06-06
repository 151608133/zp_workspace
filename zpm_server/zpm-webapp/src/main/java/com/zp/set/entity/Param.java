package com.zp.set.entity;

import java.io.Serializable;

public class Param implements Serializable {

	/**
	 * 系统设置
	 * @author lht
	 * @since 2016-09-04
	 */
	private static final long serialVersionUID = 8433535833997829849L;
	private int id;
	private int is_alarm; //是否开启消息提醒 1表示是 0表示否
	private int message; //是否开启短信提醒 1表示是 0表示否
	private int email; //是否开启邮件提醒 1表示是 0表示否
	private int app_message; //是否开启app消息提醒 1表示是 0表示否
	private int is_bak; //是否开启数据备份 1表示是 0表示否

	private String message_gateway; //短信网关
	private String message_password; // 短信密码
	private String email_address; // 邮箱地址
	private String email_account; // 邮箱账号
	private String email_password; // 邮箱密码
	private String bak_time; // 备份时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIs_alarm() {
		return is_alarm;
	}
	public void setIs_alarm(int is_alarm) {
		this.is_alarm = is_alarm;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public int getEmail() {
		return email;
	}
	public void setEmail(int email) {
		this.email = email;
	}
	public int getApp_message() {
		return app_message;
	}
	public void setApp_message(int app_message) {
		this.app_message = app_message;
	}
	public int getIs_bak() {
		return is_bak;
	}
	public void setIs_bak(int is_bak) {
		this.is_bak = is_bak;
	}
	public String getMessage_gateway() {
		return message_gateway;
	}
	public void setMessage_gateway(String message_gateway) {
		this.message_gateway = message_gateway;
	}
	public String getMessage_password() {
		return message_password;
	}
	public void setMessage_password(String message_password) {
		this.message_password = message_password;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getEmail_account() {
		return email_account;
	}
	public void setEmail_account(String email_account) {
		this.email_account = email_account;
	}
	public String getEmail_password() {
		return email_password;
	}
	public void setEmail_password(String email_password) {
		this.email_password = email_password;
	}
	public String getBak_time() {
		return bak_time;
	}
	public void setBak_time(String bak_time) {
		this.bak_time = bak_time;
	}
	
}
