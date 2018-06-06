package com.zp.sys.util;

import com.zp.sys.entity.User;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class UserExcelUtil {  
	public String exportStock(List<User> userList, HttpServletRequest request){
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("用户表");
        sheet.setColumnWidth(0, 4500);
        sheet.setColumnWidth(1, 4500);
        sheet.setColumnWidth(2, 4500);
        sheet.setColumnWidth(3, 5500);
        sheet.setColumnWidth(4, 4500);
        sheet.setColumnWidth(5, 7500);
        
        sheet.setColumnWidth(6, 10500);
        
        sheet.setColumnWidth(7, 2000);
        sheet.setColumnWidth(8, 4500);
        sheet.setColumnWidth(9, 4500);
        sheet.setColumnWidth(10, 4500);
        sheet.setColumnWidth(11, 4500);
        sheet.setColumnWidth(12, 4500);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("用户名");  
        cell = row.createCell(1);
        cell.setCellValue("用户姓名");  
        cell = row.createCell(2);
        cell.setCellValue("省份");  
        cell = row.createCell(3);
        cell.setCellValue("城市");  
        cell = row.createCell(4);
        cell.setCellValue("手机号");  
        cell = row.createCell(5);
        cell.setCellValue("邮箱"); 
        
        cell = row.createCell(6);
        cell.setCellValue("地址");
        cell = row.createCell(7);
        cell.setCellValue("状态");
        cell = row.createCell(8);
        cell.setCellValue("客户名称");
        cell = row.createCell(9);
        cell.setCellValue("用户类型");  
        cell = row.createCell(10);
        cell.setCellValue("角色"); 
        cell = row.createCell(11);
        cell.setCellValue("ship to party"); 
        cell = row.createCell(12);
        cell.setCellValue("(LC)归属客户"); 
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
       // List list = CreateSimpleExcelToDisk.getStudent();  
  
        for (int i = 0; i < userList.size(); i++)  
        {  
            row = sheet.createRow(i + 1);  
            User user = userList.get(i);  
            // 第四步，创建单元格，并设置值  
            String a = "";
            if(user.getStateCd().equals("00A")){
            	a ="有效";
            }else{
            	a="无效";
            }
            row.createCell(0).setCellValue(user.getUserName());
            row.createCell(1).setCellValue(user.getRealName());
            row.createCell(2).setCellValue(user.getProvinceName());
            row.createCell(3).setCellValue(user.getCityName());
            row.createCell(4).setCellValue(user.getContactPhone());
            row.createCell(5).setCellValue(user.getEmail());
            
            row.createCell(6).setCellValue(user.getCompanyAddress());
            
            row.createCell(7).setCellValue(a);
            row.createCell(8).setCellValue(user.getCustName());
            
            row.createCell(9).setCellValue(user.getTypeName());
            row.createCell(10).setCellValue(user.getListRoleName());
            row.createCell(11).setCellValue(user.getShipToParty());
            row.createCell(12).setCellValue(user.getCustNames());
        }  
        String fileName = null;
        // 第六步，将文件存到指定位置  
        try  
        {  	
        	Date date=new Date();
//        	//获取桌面路径
//        	File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//    		String desktopPath = desktopDir.getAbsolutePath(); 
        	String rootpath = request.getSession().getServletContext().getRealPath( "/");  
        	fileName=date.getTime()+".xls";
            FileOutputStream fout = new FileOutputStream(rootpath+"upload/"+fileName);  
            wb.write(fout);  
            fout.close();  
          
        }  
        catch (Exception e)  
        { 
            e.printStackTrace();  
            
        }  
        return fileName;
	}
}
