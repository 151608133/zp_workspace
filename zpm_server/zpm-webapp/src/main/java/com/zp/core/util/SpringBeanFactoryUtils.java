package com.zp.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class SpringBeanFactoryUtils implements ApplicationContextAware {
	
	 private static ApplicationContext appCtx;


	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        appCtx = applicationContext;
	    }


	    public static ApplicationContext getApplicationContext() {
	        return appCtx;
	    }


	    public static Object getBean(String beanName) {
	        return appCtx.getBean(beanName);
	    }
}