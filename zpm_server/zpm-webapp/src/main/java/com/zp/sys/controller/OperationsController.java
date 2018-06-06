package com.zp.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zp.core.util.Const;
import com.zp.sys.entity.Function;
import com.zp.sys.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.zp.set.entity.OperationLog2;
import com.zp.sys.entity.User;


/**
 * 日志操作类
 * @author ShenHuiYang
 * @since 2016-09-05
 */
@Controller
@RequestMapping(value="/opt")
public class OperationsController {
	@Autowired
	private FunctionService funcService;
	
	/**
	 * 左侧菜单栏默认查询
	 * @param request
	 * @param operationLog
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getRoleList(HttpServletRequest request, OperationLog2 operationLog, HttpSession session){
		User user = (User)session.getAttribute(Const.SESSION_USER);
		Map mm = new HashMap();
		String language  = session.getAttribute(Const.SESSION_SYS_LANGUAGE) == null ? "zh" : session.getAttribute(Const.SESSION_SYS_LANGUAGE).toString();
		mm.put("parentFuncId", 9);
		mm.put("userId", user.getUserId());
		List<Function> list = funcService.getOperationsByUserId(mm);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("menuList", list);
		mv.setViewName("system/operations");
		return mv;
	}
}
