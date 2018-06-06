package com.zp.sys.dao;

import com.zp.sys.entity.Function;

import java.util.List;
import java.util.Map;


public interface FunctionMapper {
	int getFuncId();
	List<Function> getMainMenuList(Long userId);
	List<Function> getSubMenuList(Map<String,Object> param);
	List<Function> getSubButtonList(Map<String,Object> param);
	List<Function> getRoleFuncList(Long userId);
	List<Function> getFuncList();
	List<Function> getFuncs();
	List<Function> getParentFuncs();
	void insertMenu(Function func);
	void updateMenu(Function func);
	void deleteMenu(Map<String,Object> param);
	List<Function> getOrderByUserId(Long userId);
	List<Function> getAppFuncByUserId(Map map);
	List<Function> getAnalysisByUserId(Long userId);
	
	List<Function> getMainMenuList2(Map map);
	List<Function> getOrderByUserId2(Map m);
	List<Function> getOperationsByUserId(Map<String,Object> param);
}
