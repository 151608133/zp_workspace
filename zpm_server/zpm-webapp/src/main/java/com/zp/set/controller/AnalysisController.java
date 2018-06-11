package com.zp.set.controller;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zp.set.entity.HotDocument;
import com.zp.set.entity.LitterType;
import com.zp.set.entity.MainAClass;
import com.zp.set.entity.MainType;
import com.zp.set.service.AnalysisService;
import com.zp.sys.util.JsonModel;

@Controller
@RequestMapping(value="/analysis")
public class AnalysisController {
	
	@Autowired
	private AnalysisService analysisService;
	
	@RequestMapping("/analyze")
	public ModelAndView analyze(HttpServletRequest request,String startTime,String endTime,String type,String iframeAnalyzeSrc){
		if(null == type)
			type = "read";
		if(null == iframeAnalyzeSrc)
			iframeAnalyzeSrc = "analysis/iframeAnalyze";
		ModelAndView mv=new ModelAndView();
		//根据条件获取文档大类和小类的维度参数
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("type", type);
		//文档大类的参数
		List<MainType> mainList = analysisService.getMainLists(map);
		mv.addObject("mainList", JSONArray.fromObject(mainList));
		//文档小类下的参数
		List<Map<String,String>> litterlist = analysisService.getaLists(map);
		List<LitterType> alist = new ArrayList<LitterType>();
		LitterType lt1 = new LitterType();
		LitterType lt2 = new LitterType();
		LitterType lt3 = new LitterType();
		LitterType lt4 = new LitterType();
		List<List<Object>> data1 = new ArrayList<List<Object>>();
		List<List<Object>> data2 = new ArrayList<List<Object>>();
		List<List<Object>> data3 = new ArrayList<List<Object>>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
 		String name = "";int sum = 0;
		for(Map<String,String> map1:litterlist){
				List<Object> list = new ArrayList<Object>();
				name = map1.get("name");
				Object ob = map1.get("sum");
				 sum = Integer.parseInt(ob.toString());
				String value = map1.get("type");
	            if(value.equals("1")){//官方文档
	            	lt1.setName("项目流程");
	            	lt1.setId("1");
	            	list.add(name);list.add(sum);
	            	data1.add(list);
	            	lt1.setData(data1);
	            }else if(value.equals("2")){//官方文档
	            	lt2.setName("指导手册");
	            	lt2.setId("2");
	            	list.add(name);list.add(sum);
	            	data2.add(list);
	            	lt2.setData(data2);
	            }else if(value.equals("3")){//官方文档
	            	lt3.setName("官方文档");
	            	lt3.setId("3");
	            	list.add(name);list.add(sum);
	            	data3.add(list);
	            	lt3.setData(data3);
	            }else if(value.equals("4")){//官方文档
	            	lt4.setName("案例库");
	            	lt4.setId("4");
	            	list.add(name);list.add(sum);
	            	data4.add(list);
	            	lt4.setData(data4);
	            }
		}
		alist.add(lt1);
		alist.add(lt2);
		alist.add(lt3);
		alist.add(lt4);
		mv.addObject("alist",JSONArray.fromObject(alist));
		mv.setViewName("mapper/set/analyze");
		mv.addObject("startTime",startTime);
		mv.addObject("endTime",endTime);
		mv.addObject("type",type);
		mv.addObject("iframeAnalyzeSrc",iframeAnalyzeSrc);
		return mv;
	}
	
	/**
	 * 热门文档
	 */
	@RequestMapping("/analyze2")
	public ModelAndView analyze2(HotDocument hd){
		ModelAndView mv=new ModelAndView();
		if(null == hd.getType())
		hd.setType("d");
		List<HotDocument> hotDocumentList = analysisService.listPageHotDocument(hd);
		mv.addObject("hotDocument", hotDocumentList);
		mv.addObject("Hd", hd);
		mv.setViewName("mapper/set/analyze2");
		return mv;
	}
	
	/**
	 * 获取分类
	 */
	@ResponseBody
	@RequestMapping("/getClass")
	public JsonModel getClass(MainAClass mac){
		JsonModel jm = new JsonModel();
		try {
			List<MainAClass> maclist = analysisService.getClassMainAClass(mac);
			jm.setCode(1);
			jm.setObj(maclist);
			return jm;
		} catch (Exception e) {
			jm.setCode(0);
			jm.setMsg("获取子类型失败，如有问题请联系开发");
			return jm;
		}
	}
	
	/**
	 * 文档类型
	 */
	@RequestMapping("/analyze3")
	public ModelAndView analyze3(String mainId,String classId,String audit_status,String iframeAnalyzeSrc){
		if(null == iframeAnalyzeSrc)
			iframeAnalyzeSrc = "analysis/iframeAnalyze3";
		ModelAndView mv=new ModelAndView();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mainId", mainId);
		map.put("classId", classId);
		map.put("audit_status", audit_status);
		List<MainType> list = analysisService.getFileType(map);
		mv.addObject("hotDocument", JSONArray.fromObject(list));
		mv.addObject("mainId", mainId);
		mv.addObject("classId", classId);
		mv.addObject("iframeAnalyzeSrc", iframeAnalyzeSrc);
		mv.addObject("audit_status",audit_status);
		mv.setViewName("mapper/set/analyze3");
		return mv;
	}
	
	/**
	 * 文档
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/iframeAnalyze")
	public ModelAndView iframeAnalyze(HotDocument hd) throws UnsupportedEncodingException{
		ModelAndView mv=new ModelAndView();
		if(null == hd.getType())
			hd.setType("read");
		if(null != hd.getPointName()){
			String pointName = hd.getPointName();
			pointName = URLDecoder.decode(pointName, "UTF-8");  
			hd.setPointName(pointName);
		}
		List<HotDocument> iframeAnalyze = analysisService.listPageiframeAnalyze(hd);
		mv.addObject("hotDocument", iframeAnalyze);
		mv.addObject("Hd", hd);
		mv.setViewName("mapper/set/iframeAnalyze");
		return mv;
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/iframeAnalyze3")
	public ModelAndView iframeAnalyze3(HotDocument hd) throws UnsupportedEncodingException{
		ModelAndView mv=new ModelAndView();
		if(null == hd.getType())
			hd.setType("read");
		if(null != hd.getPointName()){
			String pointName = hd.getPointName();
			pointName = URLDecoder.decode(pointName, "UTF-8");  
			hd.setPointName(pointName);
		}
		List<HotDocument> iframeAnalyze = analysisService.listPageiframeAnalyze(hd);
		mv.addObject("hotDocument", iframeAnalyze);
		mv.addObject("Hd", hd);
		mv.setViewName("mapper/set/iframeAnalyze");
		return mv;
	}
	
	/**
	 * 榜单(上传，下载，收藏，查看)
	 */
	@RequestMapping("/analyze4")
	public ModelAndView analyze4(String startTime,String endTime,String mainId,String classId,String type,String userName,String iframeAnalyzeSrc){
		ModelAndView mv=new ModelAndView();
		Map<String,String> map = new HashMap<String, String>();
		if(null == type)
			type = "contribution";
		if(null == iframeAnalyzeSrc)
			iframeAnalyzeSrc = "analysis/iframeAnalyze4";
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("mainId", mainId);
		map.put("classId", classId);
		map.put("type", type);
		map.put("userName", userName);
		List<MainType> mainList = new ArrayList<MainType>();
		if(type.equals("contribution")){
			mainList = analysisService.getMainLists4_1(map);
		}else{
			mainList = analysisService.getMainLists4(map);
		}
		mv.setViewName("mapper/set/analyze4");
		mv.addObject("mainList",JSONArray.fromObject(mainList));
		mv.addObject("startTime",startTime);
		mv.addObject("endTime",endTime);
		mv.addObject("mainId",mainId);
		mv.addObject("classId",classId);
		mv.addObject("type",type);
		mv.addObject("userName",userName);
		mv.addObject("iframeAnalyzeSrc", iframeAnalyzeSrc);
		return mv;
	}
	
	/**
	 * 文档
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/iframeAnalyze4")
	public ModelAndView iframeAnalyze4(HotDocument hd) throws UnsupportedEncodingException{
		ModelAndView mv=new ModelAndView();
		if(null == hd.getType())
			hd.setType("contribution");
		if(null != hd.getUserName()){
			String userName = hd.getUserName();
			userName = URLDecoder.decode(userName, "UTF-8");  
			hd.setUserName(userName);
		}
		List<HotDocument> iframeAnalyze = new ArrayList<HotDocument>();
		if(hd.getType().equals("contribution")){
			iframeAnalyze = analysisService.listPageiframeAnalyze4_1(hd);
		}else{
			iframeAnalyze = analysisService.listPageiframeAnalyze4(hd);
		}
		mv.addObject("hotDocument", iframeAnalyze);
		mv.addObject("Hd", hd);
		mv.setViewName("mapper/set/iframeAnalyze4");
		return mv;
	}
}
