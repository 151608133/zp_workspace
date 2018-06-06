package com.zp.sys.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zp.sys.dao.FunctionMapper;
import com.zp.sys.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class FunctionService {
	
	@Autowired
	private FunctionMapper funcMapper;
	
	/**
	 * 获取用户所对应的权限菜单
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> getMenuListByUserId(Long userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		List<Function> mainMenuList = funcMapper.getMainMenuList(userId);
		Iterator<Function> it = mainMenuList.iterator();
		//装载主菜单下所有的子菜单
		while(it.hasNext()) {
			Function function = it.next();
			//false:表示为主菜单
			function.setLeaf(false);
			int id = function.getFuncId();
			param.put("userId", userId);
			param.put("id", id);
			List<Function> subMenuList = funcMapper.getSubMenuList(param);
			function.setChildren(subMenuList);
		}
		return mainMenuList;
	}
	/**
	 * 获取用户所对应的权限菜单
	 * @param m
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> getMenuListByUserId2(Map m) {
		List<Function> mainMenuList = funcMapper.getMainMenuList2(m);
		Iterator<Function> it = mainMenuList.iterator();
		//装载主菜单下所有的子菜单
		while(it.hasNext()) {
			Function function = it.next();
			//false:表示为主菜单
			function.setLeaf(false);
			int id = function.getFuncId();
			/*param.put("userId", userId);
			param.put("id", id);*/
			m.put("id", id);
			List<Function> subMenuList = funcMapper.getSubMenuList(m);
			function.setChildren(subMenuList);
		}
		return mainMenuList;
	}
	/**
	 * 获取用户所对应的权限菜单
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> getMenuByUserId(Long userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		List<Function> mainMenuList = funcMapper.getMainMenuList(userId);
		Iterator<Function> it = mainMenuList.iterator();
		//装载主菜单下所有的子菜单
		while(it.hasNext()) {
			Function function = it.next();
			//false:表示为主菜单
			function.setLeaf(false);
			int id = function.getFuncId();
			param.put("userId", userId);
			param.put("id", id);
			List<Function> subMenuList = funcMapper.getSubButtonList(param);
			function.setChildren(subMenuList);
		}
		return mainMenuList;
	}
	
	/**
	 * 获取订单权限按钮
	 */
	public List<Function> getOrderByUserId(Long userId){
		return funcMapper.getOrderByUserId(userId);
	}
	/**
	 * 获取订单权限按钮
	 */
	public List<Function> getOrderByUserId2(Map m){
		return funcMapper.getOrderByUserId2(m);
	}
	/**
	 * 获取操作模块权限按钮
	 */
	public List<Function> getOperationsByUserId(Map m){
		return funcMapper.getOperationsByUserId(m);
	}
	/**
	 * 获取用户所对应的权限菜单
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> getRoleFuncList(Long userId) {
		return funcMapper.getRoleFuncList(userId);
	}
	/**
	 * 获取用户所对应的订单分析权限
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> getAnalysisByUserId(Long userId) {
		return funcMapper.getAnalysisByUserId(userId);
	}
	/**
	 * 获取所有的菜单(只包含了菜单id和菜单name)
	 * @return
	 *//*
	@Transactional(readOnly = true)
	public List<Menu> getMenuList() {
		List<Menu> menuList = menuMapper.getMenuList();
		return menuList;
	}
	*//**
	 * 获取所有的菜单(包含了所有信息)
	 * @return
	 *//*
	@Transactional(readOnly = true)
	public List<Menu> getMenus() {
		List<Menu> menuList = menuMapper.getMenus();
		return menuList;
	}
	*//**
	 * 获取所有的父菜单(只包含了菜单id和菜单name)
	 * @return
	 *//*
	@Transactional(readOnly = true)
	public List<Menu> getParentMenus() {
		List<Menu> menuList = menuMapper.getParentMenus();
		return menuList;
	}*/
	
	@Transactional(readOnly = true)
	public int getFuncId() {
		int id = funcMapper.getFuncId();
		return id;
	}
	/**
	 * 新增菜单
	 * @param menu
	 *//*
	public void insertMenu(Menu menu) {
		menuMapper.insertMenu(menu);
	}
	*//**
	 * 修改菜单
	 * @param menu
	 *//*
	public void updateMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}
	*//**
	 * 删除菜单
	 * @param menu
	 *//*
	public void deleteMenu(Map<String,Object> param) {
		String ids = (String)param.get("ids");
		String[] idsArray = ids.split(",");
		for(int i=0;i<idsArray.length;i++) {
			param.put("menuId", idsArray[i]);
			menuMapper.deleteMenu(param);
		}
	}*/
	
	
	/**
	 * 根据用户ID获取APP功能列表
	 * @param userId
	 * @return
	 */
	public List<Function> getAppFuncByUserId(String userTypeId,String userId){
		Map map = new HashMap();
		map.put("userTypeId", userTypeId);
		map.put("userId", userId);
		return funcMapper.getAppFuncByUserId(map);
	}
}
