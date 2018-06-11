package com.zp.sys.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zp.core.util.Const;
import com.zp.sys.entity.Function;
import com.zp.sys.entity.ParamFunction;
import com.zp.sys.entity.Role;
import com.zp.sys.service.RoleService;
import com.zp.sys.service.UserService;
import com.zp.sys.util.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zp.sys.entity.User;
import com.zp.sys.service.OperationLogService;


@Controller
@RequestMapping(value="/role")
public class RoleController {
	@Autowired
	protected UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OperationLogService operationLogService;
	
	
	/**
	 * 显示角色列表
	 * @param role
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getRole(Role role, HttpSession session, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
		List<Role> roleList = roleService.getRolelistPage(role);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/roles");
		mv.addObject("roleList", roleList);
		mv.addObject("role", role);
		mv.addObject("user", user);
		operationLogService.insertOperationLog(request, "角色管理", "角色信息列表",
				"查询角色信息列表", "成功");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getRoleList(Role role,HttpSession session,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
		List<Role> roleList = roleService.getRolelistPage(role);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/roles");
		mv.addObject("roleList", roleList);
		mv.addObject("role", role);
		mv.addObject("user", user);
		operationLogService.insertOperationLog(request, "角色管理", "角色信息列表",
				"查询角色信息列表", "成功");
		return mv;
	}
	/**
	 * 进入添加页面
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView toAdd(HttpSession session,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
		ModelAndView mv = new ModelAndView();		
		List<ParamFunction> orderList =roleService.getOrderList();
		//根据userId获取用户对应不了的角色的功能列表
		Map<String,Object> map = new HashMap<String, Object>();
		List<Function> cantCheckedList = roleService.getnoRoleByRoleType(map);
		mv.addObject("orderList",orderList);
		mv.addObject("checkedList",cantCheckedList);
		mv.addObject("type",0);//类型0,添加 类型1 修改
		mv.setViewName("system/addRoleInfo");
		return mv;
	}
	/**
	 * 进入修改页面
	 */
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam int roleId,HttpSession session,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Const.SESSION_USER);
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRoleById(roleId);
		List<ParamFunction> orderList =roleService.getOrderList();
		List<Function> checkedList=roleService.getCheckedList(roleId);
		Map<String,Object> map = new HashMap<String, Object>();
		List<Function> cantCheckedList = roleService.getnoRoleByRoleType(map);
		mv.addObject("cantCheckedList",cantCheckedList);
		mv.addObject("orderList",orderList);
		mv.addObject("checkedList",checkedList);
		mv.addObject("role", role);
		mv.addObject("type",1);//类型0,添加 类型1 修改
		mv.setViewName("system/addRoleInfo");
		return mv;
	}
	/**
	 * 添加角色
	 */
	@RequestMapping(value="/addRole")
	public ModelAndView addRole (Role role, HttpServletRequest request, @RequestParam(value = "test") String test){
		Date date=new Date();
		ModelAndView mv = new ModelAndView();
		try {
			if(0 == role.getRoleId()){
				role.setCreateDate(date);			
				roleService.addRole(role,test);
				operationLogService.insertOperationLog(request, "角色管理", "添加角色",
						"添加角色", "成功");
			}else{
				roleService.saveRole(role,test);
				operationLogService.insertOperationLog(request, "角色管理", "修改角色",
						"修改角色", "成功");
			}
			List<Role> roleList = roleService.getRolelistPage(role);
			mv.addObject("roleList", roleList);
			mv.addObject("role", role);	
			//返回成功页面
//			mv.setViewName("redirect:/role");
			mv.addObject("flag","success");
			mv.setViewName("system/addRoleInfo");
		} catch (Exception e) {
			e.printStackTrace();
			//返回失败页面
			mv.setViewName("order/operationFailure");
		}	
//		List<ParamFunction> orderList =roleService.getOrderList();
//		//根据userId获取用户对应不了的角色的功能列表
//		Map<String,Object> map = new HashMap<String, Object>();
//		List<Function> cantCheckedList = roleService.getnoRoleByRoleType(map);
//		mv.addObject("orderList",orderList);
//		mv.addObject("checkedList",cantCheckedList);
//		mv.addObject("type",1);//类型0,添加 类型1 修改
		role = roleService.getRoleById(role.getRoleId());
		List<ParamFunction> orderList =roleService.getOrderList();
		List<Function> checkedList=roleService.getCheckedList(role.getRoleId());
		Map<String,Object> map = new HashMap<String, Object>();
		List<Function> cantCheckedList = roleService.getnoRoleByRoleType(map);
		mv.addObject("cantCheckedList",cantCheckedList);
		mv.addObject("orderList",orderList);
		mv.addObject("checkedList",checkedList);
		mv.addObject("role", role);
		mv.addObject("type",1);//类型0,添加 类型1 修改
		
		return mv;
	}
	/**
	 * 删除某个用户
	 * @param roleId
	 * @param request
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteRole(@RequestParam int roleId,HttpServletRequest request,PrintWriter out){
		User user = new User();
		user.setRoleId(String.valueOf(roleId));
		List<User> userList = userService.listPageOfUser2(user);
		if(userList.size()>0){
			out.write("fail");
		}else{
			roleService.deleteRole(roleId);
			out.write("success");
		}
		out.close();
		operationLogService.insertOperationLog(request, "角色管理", "删除角色",
				"删除角色", "成功");
	}
	
	/**
	 * 验证用户名不能相同
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/checkRole")
	public JsonModel checkUser(String roleName){
		JsonModel jm=new JsonModel();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		map.put("stateCd", "00A");
		Role role=roleService.getRole(map);
		if(role==null){
			jm.setCode(0);
		}else{
			jm.setCode(1);
			jm.setMsg("角色名已存在");
		}
		return jm;
	}
}
