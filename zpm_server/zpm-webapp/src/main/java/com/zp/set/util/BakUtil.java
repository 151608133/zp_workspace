package com.zp.set.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.zp.set.entity.SysBak;
import com.zp.set.entity.SysBakRestore;
import com.zp.set.entity.SysBakTask;
import com.zp.set.service.ParamService;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import com.zp.set.entity.Param;
import com.zp.set.service.BakService;


public class BakUtil {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	/**
	 * 备份
	 * @param paramService
	 * @param bakService
	 */
	public void bak(ParamService paramService, BakService bakService){
		Param param = paramService.getParam();
		//判断是否开启备份
		if(param.getIs_bak()==1){
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
			//判断是否达到用户设置的时间
			if(sdf2.format(new Date()).equals(param.getBak_time())){
				//获取当前备份还原状态
				List<SysBakTask> taskList = bakService.getSysBakTask();
				if(taskList.size()==0){
					SysBakTask sysBakTask = new SysBakTask();
					sysBakTask.setTask_status(1);
					bakService.saveSysBakTask(sysBakTask);
					Properties props = new Properties();
					InputStream in =null;
					try {
						in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
						props.load(in);
						String time=sdf.format(new Date());
						boolean fileFlag=fileBak(props,time,"file");
						boolean dataFlag=true;
						if(fileFlag){
							dataFlag=dataBak(props,time,"data");
						}
						if(fileFlag&&dataFlag){
							List<SysBak> _list = bakService.getSysBak2("file"+time);
							if(_list.size()<1){
								SysBak sysBak = new SysBak();
								String bakPath=props.getProperty("BAK_PATH");
								sysBak.setBak_file_path(bakPath+"file/");
								sysBak.setBak_data_path(bakPath+"data/");
								sysBak.setBak_file_name("file"+time);
								sysBak.setBak_data_name("data"+time);
								sysBak.setBak_status(1);
								bakService.saveSysBak(sysBak);
							}
						}
						bakService.delSysBakTask();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(in!=null){
							try {
								in.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 还原
	 * @param bakService
	 * @param sysBakRestore
	 */
	public void restore(BakService bakService,SysBakRestore sysBakRestore){
		//数据还原前，先将数据备份一次
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
		Properties props = new Properties();
		Properties props2 = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			props.load(in);
			String time=sdf2.format(new Date());
			fileBak(props,time,"restore");
			dataBak(props,time,"restore");
		}catch (Exception e) {
			e.printStackTrace();
		}
		//数据还原开始
		SysBak sysBak = new SysBak();
		//还原文件 
		if(sysBakRestore.getFile_bak_id()!=0){
			sysBak.setBak_id(sysBakRestore.getFile_bak_id());
			List<SysBak> sysBakList = bakService.getSysBak(sysBak);
			if(sysBakList.size()>0){
				//备份文件路径
				String path=sysBakList.get(0).getBak_file_path()+sysBakList.get(0).getBak_file_name()+".zip";
				File zipFile = new File(path);
				if(zipFile.exists()){
					//删除系统中的文件
					String uploadPath = props.getProperty("UPLOAD_PATH");
					File uploadFile=new File(uploadPath);
					if(uploadFile.exists()){
						delFile(uploadFile);
					}
					//将备份文件解压到系统中
					try {
						unZipFiles(zipFile, uploadFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("还原文件不存在");
				}
			}
		}
		//还原数据库数据
		if(sysBakRestore.getData_bak_id()!=0){
			sysBak.setBak_id(sysBakRestore.getData_bak_id());
			List<SysBak> sysBakList = bakService.getSysBak(sysBak);
			if(sysBakList.size()>0){
				//备份数据库数据的文件路径
				String path=sysBakList.get(0).getBak_data_path()+sysBakList.get(0).getBak_data_name()+".zip";
				File zipFile = new File(path);
				if(zipFile.exists()){
					try {
						//还原前将sys_bak、sys_bak_restore、sys_bak_task进行备份
						List<String> dataTabPathList = new ArrayList<String>();
						dataTabPathList.add(dataTabBak(props, "dataTab", "sys_bak"));
						dataTabPathList.add(dataTabBak(props, "dataTab", "sys_bak_restore"));
						dataTabPathList.add(dataTabBak(props, "dataTab", "sys_bak_task"));
						dataTabPathList.add(dataTabBak(props, "dataTab", "sys_param"));
						//将sql文件解压到系统中
						unZipFiles(zipFile, new File(sysBakList.get(0).getBak_data_path()));
						//将数据还原
		            	File file = new File(sysBakList.get(0).getBak_data_path()+sysBakList.get(0).getBak_data_name()+".sql");
						//mysql安装路径
						String mysqlPath=props.getProperty("MYSQL_PATH");
						//当前需要备份数据库名称
						String databaseName=props.getProperty("MYSQL_NAME");
						//sql文件导出的路径
						String bakPath=props.getProperty("BAK_PATH");
						InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
						props2.load(in);
						String username=props2.getProperty("jdbc.username");
						String password=props2.getProperty("jdbc.password");
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(mysqlPath).append("mysql.exe -u").append(username).append(" -p").append(password).append(" ");
						stringBuilder.append(databaseName);
		            	if(file.exists()){
							Process process = Runtime.getRuntime().exec(stringBuilder.toString());
				            restoreData(process, sysBakList.get(0).getBak_data_path()+sysBakList.get(0).getBak_data_name()+".sql");
				            file.delete();
				        }
		            	//数据库数据还原结束后，将之前备份的sys_bak、sys_bak_restore、sys_bak_task进行还原
		            	for(String dataTabPath:dataTabPathList){
		            		File dataTabFile = new File(dataTabPath);
		            		if(dataTabFile.exists()){
								Process process = Runtime.getRuntime().exec(stringBuilder.toString());
								restoreData(process, dataTabPath);
								dataTabFile.delete();
		            		}
		            	}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("还原数据库数据文件不存在");
				}
			}
		}
	}
	/**
	 * 文件备份
	 * @param props 配置文件
	 * @param time 时间
	 * @param fileName 文件类型
	 * @return
	 */
	private boolean fileBak(Properties props,String time,String fileName) {
		String uploadPath=props.getProperty("UPLOAD_PATH");
		String bakPath=props.getProperty("BAK_PATH");
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;
		if(!"".equals(uploadPath)&&!"".equals(bakPath)){
			File uploadFile = new File(uploadPath);
			if(uploadFile.exists()){
				try{
					//压缩文件的路径
					File bakFile = new File(bakPath+fileName+"/file"+time+".zip");
					bakFile.mkdirs();
					//判断文件是否存在，如果存在则进行删除
					if(bakFile.exists()){
						bakFile.delete();
					}
					fos = new FileOutputStream(bakFile);  
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					zos.setEncoding("GBK");
					fileZip(zos, uploadFile,null);
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally{
					 //关闭流  
	                try {  
	                    if(null != zos) zos.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    throw new RuntimeException(e);  
	                }  
				}
			}else{
				System.out.println("待压缩的文件目录不存在");
				return false;
			}
		}
		return true;
	}

	/**
	 * 数据库数据备份
	 * @param props 配置文件
	 * @param time 时间
	 * @param fileName 文件类型
	 * @return
	 */
	public boolean dataBak(Properties props,String time,String fileName){
		//sql文件导出的路径
		String bakPath=props.getProperty("BAK_PATH")+fileName+"/";
		//mysql安装路径
		String mysqlPath=props.getProperty("MYSQL_PATH");
		//当前需要备份数据库名称
		String databaseName=props.getProperty("MYSQL_NAME");
		//判断路径是否存在
		File bakFile = new File(bakPath);
		if(!bakFile.exists()){
			bakFile.mkdirs();
		}
		Properties props2 = new Properties();
		String username="";
		String password="";
		FileOutputStream fos = null;  
		ZipOutputStream zos = null;  
		try{
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
			props2.load(in);
			username=props2.getProperty("jdbc.username");
			password=props2.getProperty("jdbc.password");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(mysqlPath+"mysqldump.exe").append(" --opt");
		    stringBuilder.append(" --user=").append(username) .append(" --password=").append(password).append(" --lock-all-tables=true");
		    stringBuilder.append(" --result-file=").append(bakPath +"data"+time+".sql").append(" --default-character-set=utf8 ").append(databaseName);
		    Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
            	File zipFile = new File(bakPath+"data"+time+".zip");
            	if(zipFile.exists()){
            		zipFile.delete();
            	}
            	fos = new FileOutputStream(zipFile);  
				zos = new ZipOutputStream(new BufferedOutputStream(fos));
				zos.setEncoding("GBK");
				File sqlFile=new File(bakPath +"data"+ time+".sql");
				fileZip(zos, sqlFile, sqlFile.getName());
				sqlFile.delete();
            }
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			 //关闭流  
            try {  
                if(null != zos) zos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
		}
		return true;
	}
	/**
	 * 数据库表数据备份
	 * @param props 配置文件
	 * @param fileName 文件类型
	 * @param tabName 表名
	 * @return 路径
	 */
	public String dataTabBak(Properties props,String fileName,String tabName){
		String returnStr="";
		//sql文件导出的路径
		String bakPath=props.getProperty("BAK_PATH")+fileName+"/";
		//mysql安装路径
		String mysqlPath=props.getProperty("MYSQL_PATH");
		//当前需要备份数据库名称
		String databaseName=props.getProperty("MYSQL_NAME");
		//判断路径是否存在
		File bakFile = new File(bakPath);
		if(!bakFile.exists()){
			bakFile.mkdirs();
		}
		Properties props2 = new Properties();
		String username="";
		String password="";
		try{
			returnStr=bakPath +tabName+".sql";
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
			props2.load(in);
			username=props2.getProperty("jdbc.username");
			password=props2.getProperty("jdbc.password");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(mysqlPath+"mysqldump.exe").append(" --opt");
		    stringBuilder.append(" --user=").append(username) .append(" --password=").append(password).append(" --lock-all-tables=true");
		    stringBuilder.append(" --result-file=").append(returnStr).append(" --default-character-set=utf8 ").append(databaseName).append(" ").append(tabName);
		    Runtime.getRuntime().exec(stringBuilder.toString());
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return returnStr;
	}

    /**
     * 数据还原
     * @param process
     * @param sqlPath sql文件路径
     */
    private void restoreData(Process process,String sqlPath){
    	try {      
            Runtime rt = Runtime.getRuntime();      
            // 调用 mysql 的 cmd:           
            OutputStream out = process.getOutputStream();//控制台的输入信息作为输出流      
            String inStr;      
            StringBuffer sb = new StringBuffer("");      
            String outStr;      
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlPath), "utf8"));      
            while ((inStr = br.readLine()) != null) {      
                sb.append(inStr + "\r\n");      
            }      
            outStr = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");      
            writer.write(outStr);      
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免      
            writer.flush();      
            // 别忘记关闭输入输出流      
            out.close();      
            br.close();      
            writer.close();      
        } catch (Exception e) {      
            e.printStackTrace();      
        } 
    }
    /**
     * 删除文件夹
     * @param file
     */
    private void delFile(File file){
    	if(file.isDirectory()){
    		File[] files = file.listFiles();
    		for(File f:files){
    			delFile(f);
    		}
    		file.delete();
    	}else{
    		file.delete();
    	}
    }
    /**
	 * 文件压缩
	 * @param zos 压缩输入流
	 * @param file 被压缩的文件
	 * @param base 文件或目录的名称
	 */
	private static void fileZip(ZipOutputStream zos, File file, String base){
		FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        try{
        	if(file.isDirectory()){
        		File[] files = file.listFiles();
        		if(base!=null){
        			base=base+"/";
    				ZipEntry zipEntry = new ZipEntry(base);
        			zos.putNextEntry(zipEntry);
        		}else{
        			base="";
        		}
        		for(File f:files){
        			fileZip(zos, f,base+f.getName());
        		}
        	}else{
        		byte[] bufs = new byte[1024*10];  
				//创建ZIP实体，并添加进压缩包  
				ZipEntry zipEntry = new ZipEntry(base);  
				zos.putNextEntry(zipEntry); 
				//读取待压缩的文件并写进压缩包里  
				fis = new FileInputStream(file);  
				bis = new BufferedInputStream(fis);  
				int read = 0;  
				while((read=bis.read(bufs, 0, 1024*10)) != -1){  
					zos.write(bufs,0,read);  
					bufs=new byte[1024*10];
				}
				/*zos.putNextEntry(new ZipEntry(base)); 
        		fis = new FileInputStream(file);
        		int read;
        		while ((read = fis.read()) != -1){
        			zos.write(read);
        		}*/
        	}
        }catch (Exception e) {
        	e.printStackTrace();
		}finally{
			 //关闭流  
            try {  
                if(null != bis) bis.close();  
                if(null != fis) fis.close();
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
		}
	}
    /** 
     * 文件解压
     * @param zipFile 待解压的文件
     * @param pathFile  解压后的路径
     * @throws Exception 
     */  
    private void unZipFiles(File zipFile,File pathFile) throws Exception{  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        ZipFile zip = new ZipFile(zipFile,"GBK");
        for(Enumeration entries = zip.getEntries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            String outPath = (pathFile.getPath()+"/"+zipEntryName).replaceAll("\\*", "/");;  
            //判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if(!file.exists()){  
                file.mkdirs();  
            }  
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if(new File(outPath).isDirectory()){  
                continue;  
            }  
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while((len=in.read(buf1))>0){  
                out.write(buf1,0,len);  
            }  
            in.close();  
            out.close();  
        }  
    }  
    
    public static void main(String[] args) {
    	FileOutputStream fos=null;
    	ZipOutputStream zos=null;
    	try{
	    	//压缩文件的路径
			File bakFile = new File("E://Spm/bak/1.zip");
			bakFile.mkdirs();
			//判断文件是否存在，如果存在则进行删除
			if(bakFile.exists()){
				bakFile.delete();
			}
			fos = new FileOutputStream(bakFile);  
			zos = new ZipOutputStream(new BufferedOutputStream(fos));
			zos.setEncoding("GBK");
			File uploadFile = new File("E://Spm/upload/");
			fileZip(zos, uploadFile,null);
    	}catch (Exception e) {
    		e.printStackTrace();
		}finally{
			 //关闭流  
            try {  
                if(null != zos) zos.close();  
                if(null != fos) fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
		}
	}
}
