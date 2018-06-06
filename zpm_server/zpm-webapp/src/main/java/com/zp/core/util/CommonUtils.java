package com.zp.core.util;

public class CommonUtils {
	
	public static String getStr(Object o){
		if (null == o) {
			return "";
		} else {
			return o.toString();
		}
	}
}
