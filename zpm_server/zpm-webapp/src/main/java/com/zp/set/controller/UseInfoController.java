package com.zp.set.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zp.core.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.zp.set.entity.OperationLog2;
import com.zp.sys.service.OperationLogService;


/**
 * 黑名单
 * @author ShenHuiYang
 * @since 2016-09-05
 */
@Controller
@RequestMapping(value="/useInfo")
public class UseInfoController {

	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 左侧菜单默认查询
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getUseInfoList(){//HttpServletRequest request,
		ModelAndView mv = new ModelAndView();
		Page page = new Page();
		page.setCurrentResult(page.getCurrentResult());
		page.setTotalPage(page.getTotalPage());
		page.setPageStr(page.getPageStr());
		int key = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		mv.addObject("useInfoList", list);
		mv.setViewName("mapper/set/useInfo");
		return mv;
	}
	/**
	 * 条件查询
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getUseInfo(){
		ModelAndView mv = new ModelAndView();
		int key = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		mv.addObject("useInfoList", list);
		mv.setViewName("mapper/set/useInfo");
		return mv;
	}
	//@RequestParam int fun_id,
	@RequestMapping(value="/getDetail")
	public ModelAndView getDetaillistPage(OperationLog2 operationLog,HttpServletRequest request,HttpSession session){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mapper/set/useInfoLog");
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		Date now = new Date();
		if(operationLog.getStartTime()==null || operationLog.getStartTime().equals("")){
			ca.setTime(now);
			ca.add(Calendar.MONTH, -1);// 月份减1
			operationLog.setStartTime(sdf.format(ca.getTime()));
		}
		if(operationLog.getEndTime()==null || operationLog.getEndTime().equals("")){
			operationLog.setEndTime(sdf.format(now));
		}*/
		List<OperationLog2> list = operationLogService.getLoglistPage(operationLog);
		mv.addObject("logList", list);
		mv.addObject("operationLog", operationLog);
		
		
		//加入操作日志
		/*User user = (User)session.getAttribute(Const.SESSION_USER);
		String language  = session.getAttribute(Const.SESSION_SYS_LANGUAGE) == null ? "zh" : session.getAttribute(Const.SESSION_SYS_LANGUAGE).toString();
		Map<String,String> params = new HashMap<String,String>();
		params.put("userName", user.getUserName());
		params.put("title", feedbackDetail.getTitle());
		operationLogService.insertOperationLog(request,"ViewFeedback",language,"000",7,68,params);
		*/
		return mv;
	}
	
	@RequestMapping(value="/log")
	public ModelAndView getOperationLog(HttpServletRequest request,OperationLog2 operationLog){
		List<OperationLog2> list = operationLogService.getLoglistPage(operationLog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapper/set/useInfoLog");
		mv.addObject("logList", list);
		mv.addObject("operationLog", operationLog);
		return mv;
	}
}
