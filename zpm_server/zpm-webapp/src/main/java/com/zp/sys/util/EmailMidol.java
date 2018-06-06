package com.zp.sys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 邮件文件生成
 */
public class EmailMidol {  
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public boolean sendFile(String filePath,String outFilePath,
			List<Map<String, Object>> dataSetList,List<String> rankList){
		InputStream in = null;
		try {
			File file = new File(filePath);
			in = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			Row row=null;
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			for (int i = 0; i < dataSetList.size(); i++) {
				Map<String, Object> map = dataSetList.get(i);
				row=sheet.createRow(1 + i);//前面2行是模版内容
				for(int j=0;j<rankList.size();j++){
					Cell cell = row.createCell((short)j);
					Object value=map.get(rankList.get(j));
					if(value==null||"".equals(value)){
						cell.setCellValue("");
					}else{
						//判断是否为数字,是的话就输出数字,不是就按以前的
						if(StringUtils.isNumeric(value+"")&&(value+"").length()<11){
							cell.setCellValue(Integer.parseInt(value+""));
						}else{
							if(value instanceof Date){
								cell.setCellValue(sdf.format(value));
							}else{
								cell.setCellValue(value+"");								
							}
						}
					}
				}
			}
			OutputStream out = new FileOutputStream(outFilePath);	//创建输入流
			out.flush();  
			wb.write(out);  
			out.close(); 
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
