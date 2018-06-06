package com.zp.set.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamUtil {

	/**
	 * 获取路径下所有文件的信息
	 * @param path
	 */
	public List<Map<String,String>> getFileMes(File file,String fileName){
		List<Map<String,String>> _returnList = new ArrayList<Map<String,String>>();
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f:files){
				_returnList.addAll(getFileMes(f,fileName));
			}
		}else{
			if(fileName==null||"".equals(fileName)||file.getName().indexOf(fileName)!=-1){
				Map<String,String> map = new HashMap<String,String>();
				map.put("filePath", file.getPath().replace("\\", "/"));
				map.put("fileName", file.getName());
				_returnList.add(map);
			}
		}
		return _returnList;
	}
}
