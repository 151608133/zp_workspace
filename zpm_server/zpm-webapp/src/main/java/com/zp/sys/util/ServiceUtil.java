package com.zp.sys.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceUtil {
	private static ApplicationContext	context;

	static {
		context = new ClassPathXmlApplicationContext("springmvc3-servlet-context.xml");
	}

	public static Object getService(String service) {
		System.out.println(service);
		return context.getBean(service);
	}
	
}
