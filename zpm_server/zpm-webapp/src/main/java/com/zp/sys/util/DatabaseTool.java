/**
 * 
 */
package com.zp.sys.util;

/**
 * @author: Administrator
 * @date: 2018-1-30
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class DatabaseTool {

    /**
     * 备份数据库 ,控制台执行命令格式 "mysql的bin目录/mysqldump --databases  -h主机ip -P端口  -u用户名 -p密码  --default-character-set=字符集  数据库名 
     * @param mysqlPath mysql路径
     * @param mysqlIp mysql主机ip
     * @param mysqlPort 端口
     * @param userName 用户名
     * @param password 密码
     * @param charset 字符集
     * @param database 数据库名
     * @param resultFile 备份文件全路径
     */
    public void backup(String mysqlPath, String mysqlIp,
    		String mysqlPort, String userName, String password, String database, String resultFile) {
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fout = null;
        OutputStreamWriter writer = null;
        /*
        mysqlPath = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin";
        mysqlIp = "127.0.0.1";
        mysqlPort = "3306";
        userName = "root";
        password = "123456";
        database = "uimsdb";
        Date currentDate = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");  
        String sdfDate = sdf.format(currentDate);  
        resultFile = "e:\\uimsdb_" + sdfDate + ".sql";
        */
        try {
            Runtime rt = Runtime.getRuntime();
            // 调用mysql的安装目录的命令
            Process process = rt.exec("\"" + mysqlPath + File.separator + "mysqldump\" --databases -h" + mysqlIp 
             + " -P" +  mysqlPort + " -u" + userName + " -p" + password 
             + " --add-drop-database --default-character-set=utf8 "+ database + " --result-file="+resultFile);
            // 设置导出编码为utf-8。这里必须是utf-8
            in = process.getInputStream();// 控制台的输出信息作为输入流
            ErrorStreamThread errStream = new ErrorStreamThread(process.getErrorStream()); //错误流另开线程，不然会阻塞
            errStream.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(writer != null){
                    writer.close();
                }
                if(fout != null){
                    fout.close();
                }
                if(br != null){
                    br.close();
                }
                if(isr != null){
                    isr.close();
                }
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}    
