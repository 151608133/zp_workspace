package com.zp.core.util;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	public static final String SESSION_CHECK_RECORD="sessionCheckRecord";	// 本次盘点记录
	public static final String SEESION_IS_IMPORT_SAP="sessonIsImportSAP";	//是否已导入SAP
	public static final String SESSION_USER_STORE = "sessionUserStore";		
	public static final String USER_CURRENTSTORE = "userCurrentStore";
	public static final String SESSION_SYS_LANGUAGE = "sysLanguage";
	public static final String SESSION_SELECT_STORE = "sessionSelectStore";
}
