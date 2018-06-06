package com.zp.core.util;


import com.zp.sys.service.FunctionService;
import com.zp.sys.service.RoleService;
import com.zp.sys.service.UserService;

/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		//WebApplicationContextUtils.
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static UserService getUserService(){
		return (UserService) getService("userService");
	}
	
	public static RoleService getRoleService(){
		return (RoleService) getService("roleService");
	}
	
	public static FunctionService getMenuService(){
		return (FunctionService) getService("FunctionService");
	}
}
