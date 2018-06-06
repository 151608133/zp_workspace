package com.zp.sys.dao;

import com.zp.sys.entity.Function;
import com.zp.sys.entity.ParamFunction;
import com.zp.sys.entity.Role;

import java.util.List;
import java.util.Map;



public interface RoleMapper {
	int getRoleId();
	Role getRole(Map<String,Object> param);
	List<Role> getRoleList();
	List<Role> getRolelistPage(Role role);
	List<Role> getRoleMenuList();
	void insertRole(Role role);
	void insertRoleMenu(Map<String,Object> param);
	void updateRole(Role role);
	void updateRoleMenu(Map<String,Object> param);
	void deleteRole(Map<String,Object> param);
	void deleteRoleMenu(Map<String,Object> param);
	List<Map<String,String>> getUserCount();
	int addRole(Role role);
	void deleteRoleById(int roleId);
	void deletFuncById (int roleId);
	Role getRoleById(int roleId);
	void saveRole(Role role);
	List<Role> getRoleListByUser(Long userid);
	List<Function> getGoodsList();
	List<Function> getOrderList();
	void addFunction(List<Function> func);
	void deletefunc(Role role);
	List<Function> getCheckedList(int roleId);
	List<ParamFunction> getMainFuncList();
	List<Function> getMainMenuList(int id);
	List<Function> getSubFuncList(int id);
	/**
	 * 获取用户拥有的权限
	 */
	List<Integer> userIdQueryFunctionId(String user_id);
	List<Function> getnoRoleByRoleType(Map<String, Object> map);
	List<Role> getRoleListByroleType(Map<String, Object> map);
	List<Role> getRoleListByUserId(Map<String, Object> map);
	List<Role> getRole2listPage(Role role);
	List<Role> getRoleListByUserForAdmin(Long userId);
}
