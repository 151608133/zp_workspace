/**
 * 
 */
package com.zp.sys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: Administrator
 * @date: 2018-1-31
 */
public class TimeQueue implements ServletContextListener {
	
	private Timer timer = null;
	
	private DatabaseTool databaseTool = null;
	
	Date currentDate = new Date();  
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");  
    String sdfDate = sdf.format(currentDate); 
    
	private String mysqlPath = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin";
	private String mysqlIp = "127.0.0.1";
	private String mysqlPort = "3306";
	private String userName = "root";
	private String password = "123456";
	private String database = "uimsdb";
	private String resultFile = "e:\\uimsdb_" + sdfDate + ".sql";
	
	public void contextInitialized(ServletContextEvent event) {
		
		timer = new Timer(true);
		timer.schedule(new Task_Data(timer), 0, 60 * 60 * 1000);
	}
	
	class Task_Data extends TimerTask {
		private Timer timer = null;
		public Task_Data(Timer stimer) {
			timer = stimer;
		}

		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date()));
			Calendar cal=Calendar.getInstance();
			int hour=cal.get(Calendar.HOUR_OF_DAY);
			try { 
				if(hour == 1){
					databaseTool.backup(mysqlPath, mysqlIp, mysqlPort, userName, password, database, resultFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {	
		// TODO Auto-generated method stub
		
	}

}
