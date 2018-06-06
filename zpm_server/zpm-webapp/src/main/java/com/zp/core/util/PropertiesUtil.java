package com.zp.core.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {
	private static Map<String, Properties> propsMap = new HashMap<String,Properties>();
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);

	/**
	 * 
	 * @Title: getProperty 
	 * @Description: 基于Spring读取Properties的文件，返回属性值
	 * @param @param propertyKey 要获取属性值对应的键名
	 * @param @param propertyFileName 属性文件名
	 * @param @return
	 * @return String 属性值
	 * @throws 
	 * @author zhupengren
	 * @date 2014年4月17日 下午5:55:13
	 */
	public static String getProperty(String propertyKey,String propertyFileName){
		if(propsMap.containsKey(propertyFileName)){
			return propsMap.get(propertyFileName).getProperty(propertyKey);
		}
		String value=null;
		try {
			Properties props = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
			value = props.getProperty(propertyKey);
			propsMap.put(propertyFileName, props);
		} catch (IOException e) {
			logger.info(propertyFileName+"文件未找到!=========================");
			logger.error(e.getMessage(), e);
		}
		return value;
	}
}
