package com.zp.set.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.zp.set.entity.OperationLog2;
import com.zp.sys.service.OperationLogService;


/**
 * 日志操作类
 * @author ShenHuiYang
 * @since 2016-09-05
 */
@Controller
@RequestMapping(value="/log")
public class OperationLogController {
	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 左侧菜单栏默认查询
	 * @param request
	 * @param operationLog
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getRoleList(HttpServletRequest request,OperationLog2 operationLog,HttpSession session){
		List<OperationLog2> list = operationLogService.getLoglistPage(operationLog);
		List<HashMap<String,Object>> list2 = operationLogService.getLoglistPage2(operationLog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapper/set/actionlog");
		mv.addObject("logList", list);
		mv.addObject("logList2", list2);
		mv.addObject("operationLog", operationLog);
		return mv;
	}
	/**
	 * 条件查询
	 * @param request
	 * @param operationLog
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getOperationLog(HttpServletRequest request,OperationLog2 operationLog){
		List<OperationLog2> list = operationLogService.getLoglistPage(operationLog);
		List<HashMap<String,Object>> list2 = operationLogService.getLoglistPage2(operationLog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapper/set/actionlog");
		mv.addObject("logList", list);
		mv.addObject("logList2", list2);
		mv.addObject("operationLog", operationLog);
		return mv;
	}
	/**
	 * 删除操作日志
	 * @param request
	 * @param out
	 */
	@RequestMapping(value="/delOperationLog")
	public void delOperationLog(HttpServletRequest request,PrintWriter out,HttpSession session){
		String logId = "";
		if(null!=request.getParameter("logId") && !"".equals(request.getParameter("logId"))){
			logId = request.getParameter("logId");
			operationLogService.delOperationLog(logId);//删除指定Id的log信息
		}
		out.write("success");
		out.close();
	}
}
