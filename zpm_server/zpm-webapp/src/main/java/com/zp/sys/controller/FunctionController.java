package com.zp.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zp.sys.service.FunctionService;


@Controller
@RequestMapping("/func")
public class FunctionController {
	@Autowired
	private FunctionService funcService;
	/**
	 * 获取权限列表(即菜单列表)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/funcList",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getRoleList(HttpServletRequest request,   
            HttpServletResponse response){
		/*Map<String,Object> responseMap = new HashMap<String,Object>();
		List<Menu> menuList = funcService.getMenuList();
		responseMap.put("totalCount", menuList.size());
		responseMap.put("rows", menuList);
		return responseMap;*/
		return null;
	}
	/**
	 * 获取菜单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/funcs",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getMenus(HttpServletRequest request,   
            HttpServletResponse response){
		/*Map<String,Object> responseMap = new HashMap<String,Object>();
		List<Menu> menuList = menuService.getMenus();
		responseMap.put("totalCount", menuList.size());
		responseMap.put("rows", menuList);
		return responseMap;*/
		return null;
	}
	
	/**
	 * 获取一级菜单(即父菜单)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/parentfuncs",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getParentMenus(HttpServletRequest request,   
            HttpServletResponse response){
		/*Map<String,Object> responseMap = new HashMap<String,Object>();
		List<Menu> menuList = menuService.getParentMenus();
		responseMap.put("totalCount", menuList.size());
		responseMap.put("rows", menuList);
		return responseMap;*/
		return null;
	}
	
	/**
	 * 保存菜单信息(新增菜单or编辑菜单)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveOrUpdate(HttpServletRequest request,   
            HttpServletResponse response){
		/*Map<String,Object> responseMap = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String sort_num = request.getParameter("sort_num");
		String parent_id = request.getParameter("parent_id");
		String description = request.getParameter("description");
		try {
			//编辑菜单信息
			if(!"".equals(id)) {
				Menu menu = new Menu();
				menu.setMenuId(Integer.valueOf(id));
				//表示该菜单为子菜单，包含了父菜单的id
				if(parent_id!=null && !"".equals(parent_id)) {
					menu.setParent_id(Integer.valueOf(parent_id));
				}
				menu.setDescription(description);
				menu.setMenuName(name);
				menu.setQtip(name);
				menu.setSort_num(Integer.valueOf(sort_num));
				menu.setUrl(url);
				menuService.updateMenu(menu);
				responseMap.put("success", "true");
				responseMap.put("info", "编辑成功！");
			}
			//新增菜单信息
			else {
				Menu menu = new Menu();
				int newId = menuService.getId();
				//表示表数据的第一条记录
				if(newId==0) {
					newId = 1;
				}else {
					newId = newId+1;
				}
				menu.setMenuId(newId);
				//表示该菜单为子菜单，包含了父菜单的id
				if(parent_id!=null && !"".equals(parent_id)) {
					menu.setParent_id(Integer.valueOf(parent_id));
				}
				menu.setImage("images/plugin.gif");
				menu.setDescription(description);
				menu.setMenuName(name);
				menu.setQtip(name);
				menu.setSort_num(Integer.valueOf(sort_num));
				menu.setUrl(url);
				menuService.insertMenu(menu);
				responseMap.put("method", "Create");
				responseMap.put("success", "true");
				responseMap.put("info", "新增成功！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			responseMap.put("info", e.getClass()+":"+e.getMessage());
			return responseMap;
		}
		return responseMap;*/
		return null;
	}
	
	/**
	 * 删除菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> remove(HttpServletRequest request,   
            HttpServletResponse response){
		/*Map<String,Object> responseMap = new HashMap<String,Object>();
		String ids = request.getParameter("ids");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ids", ids);
		try {
			menuService.deleteMenu(param);
			responseMap.put("success", "true");
			responseMap.put("info", "删除成功！");
			return responseMap;
		}catch(Exception e) {
			e.printStackTrace();
			responseMap.put("info", e.getClass()+":"+e.getMessage());
			return responseMap;
		}*/
		return null;
	}
}
