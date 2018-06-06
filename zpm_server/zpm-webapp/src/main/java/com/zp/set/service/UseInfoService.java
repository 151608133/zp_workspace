package com.zp.set.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zp.set.dao.UseInfoMapper;


@Service
@Transactional
public class UseInfoService {
	@Autowired
	private UseInfoMapper useInfoMapper;
	
	/**
	 * 使用情况分析
	 * @return
	 */
	public List<Map<String, String>> getUseInfoList(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();  
		Date now = new Date();
		String nows = "";
		Date resultDate = new Date();
		//如果起始时间为空，则默认用上个月的今天为结束时间
		String dates = "";   
		List<Map<String, String>> infolist = new ArrayList<Map<String, String>>();
		boolean flag = false;
		//从最近上个月的这天开始循环到今天
		for(int i=0;i<32;i++){
			if(flag){//如果时间循环匹配到今天，则下次进去循环时跳出循环
				break;
			}
			dates = sdf.format(resultDate);
			Map<String, String> map = getInfoMap();//new HashMap();
			map.put("date", dates);
			if(dates.equals(nows)){//如果时间循环匹配到今天，则下次进去循环时跳出循环
				flag=true;//break;
			}
			infolist.add(map);
			//日期往后一天
			ca.add(Calendar.DATE, 1);
			resultDate = ca.getTime();
		}
		List<Map<String, String>> infolist2 = new ArrayList<Map<String, String>>();
		for(int i=infolist.size()-1;i>=0;i--){
			infolist2.add(infolist.get(i));
		}
		return infolist2;
	}
	private Map<String, String> getInfoMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("sfh", "0");
		map.put("dd", "0");
		map.put("ck", "0");
		map.put("bgsc", "0");
		map.put("yh", "0");
		map.put("sz", "0");
		map.put("fk", "0");
		map.put("sh", "0");
		return map;
	}
}
