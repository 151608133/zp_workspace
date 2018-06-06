package com.zp.sys.listener;
  
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSessionListener;  

/** 
 * HttpSessionListener 监听器 
 */  
public class MyHttpSessionListener implements HttpSessionListener{  
	 private static int count = 0;
	   
	  public void sessionCreated(HttpSessionEvent se) {
	    count++;
	    System.out.println("session创建：" + new java.util.Date());
	  }
	   
	  public void sessionDestroyed(HttpSessionEvent se) {
		  if(count > 0){
			  count--;	  
		  }
		  System.out.println("session销毁：" + new java.util.Date());
	  }
	   
	  public static int getCount() {
	    return count;
	  }
}  