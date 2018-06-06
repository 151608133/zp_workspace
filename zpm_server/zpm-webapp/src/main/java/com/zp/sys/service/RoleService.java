package com.zp.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zp.sys.dao.RoleMapper;
import com.zp.sys.entity.Function;
import com.zp.sys.entity.ParamFunction;
import com.zp.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 获取主键id
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getId() {
		return roleMapper.getRoleId();
	}
	
	/**
	 * 获取角色
	 * @return
	 */
	@Transactional(readOnly = true)
	public Role getRole(Map<String,Object> param) {
		return roleMapper.getRole(param);
	}
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}
	
	/**
	 * 获取角色列表  分页
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> getRolelistPage(Role role) {
		return roleMapper.getRolelistPage(role);
	}
	/**
	 * 获取角色菜单列表
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> getRoleMenuList() {
		return roleMapper.getRoleMenuList();
	}
	/**
	 * 新增角色
	 * @param user
	 * @param param
	 */
	public void insertRole(Role role,Map<String,Object> param) {
		//新增角色
		roleMapper.insertRole(role);
		String menuList = (String)param.get("menuList");
		if(!"".equals(menuList)) {
			//新增角色菜单关联
			insertRoleMenu(param);
		}
	}
	/**
	 * 编辑角色
	 * @param user
	 * @param param
	 */
	public void updateRole(Role role,Map<String,Object> param) {
		//编辑角色
		roleMapper.updateRole(role);
		//删除原有的角色菜单关联
		roleMapper.deleteRoleMenu(param);
		//新增角色菜单关联
		insertRoleMenu(param);
	}
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void deleteRole(int roleId) {
		roleMapper.deleteRoleById(roleId);

	}
	/**
	 * 角色菜单关联
	 * @param param
	 */
	private void insertRoleMenu(Map<String,Object> param) {
		String menuList = (String)param.get("menuList");
		String[] menuArray = menuList.split(",");
		for(int i=0;i<menuArray.length;i++) {
			param.put("menu_id", menuArray[i]);
			roleMapper.insertRoleMenu(param);
		}
	}
 /**
  * 添加角色
  */
	public void addRole(Role role,String test){
		role.setStateCd("00A");
		roleMapper.addRole(role);
		int roleId=role.getRoleId();
		Set<String> funcSet=new HashSet<String>();
		String[] func=test.split("[_,]");
		for (int i = 0; i < func.length; i++) {
			funcSet.add(func[i]);
		}
		List<Function> funcs = new ArrayList<Function>();
		for(String set:funcSet){
			Function f = new Function(Integer.valueOf(set),roleId);
			funcs.add(f);
		}		
		roleMapper.addFunction(funcs);
	}
	/**
	 * 根据id获取角色
	 * @param userId
	 * @return
	 */
	public Role getRoleById(Integer roleId) {
		return roleMapper.getRoleById(roleId);
	}
	/**
	 * 保存角色
	 */
	public void saveRole(Role role,String test){
		roleMapper.saveRole(role);
		roleMapper.deletefunc(role);
		int roleId=role.getRoleId();
		Set<String> funcSet=new HashSet<String>();
		String[] func=test.split("[_,]");
		for (int i = 0; i < func.length; i++) {
			funcSet.add(func[i]);
		}
		List<Function> funcs = new ArrayList<Function>();
		for(String set:funcSet){
			Function f = new Function(Integer.valueOf(set),roleId);
			funcs.add(f);
		}		
		roleMapper.addFunction(funcs);
	}
	
	public List<Role> getRoleListByUser(Long userid){
		return roleMapper.getRoleListByUser(userid);
	}
	
	public List<ParamFunction> getOrderList(){
		List<ParamFunction> mainFuncList=roleMapper.getMainFuncList();
		Iterator<ParamFunction> ia = mainFuncList.iterator();		
		//装载主菜单下所有的子菜单
		while(ia.hasNext()) {
			ParamFunction func = ia.next();
			int funcId = func.getFuncId();
			List<Function> mainMenuList = roleMapper.getMainMenuList(funcId);		
			Iterator<Function> it = mainMenuList.iterator();
			while(it.hasNext()) {
				Function function = it.next();
				int id = function.getFuncId();
				List<Function> subMenuList = roleMapper.getSubFuncList(id);
				function.setChildren(subMenuList);
			}
			func.setSubChildren(mainMenuList);
		}
		for(ParamFunction fun:mainFuncList){			
			for(Function sb:fun.getSubChildren()){
				sb.setQtip(fun.getFuncId()+"_"+sb.getFuncId());
				for(Function chi:sb.getChildren()){
					chi.setQtip(sb.getFuncId()+"_"+chi.getFuncId());
				}
			}
		}
		return mainFuncList;
	}
	public List<Function> getGoodsList(){
		return roleMapper.getGoodsList();
	}
	public List<Function> getCheckedList(int roleId){;
		return roleMapper.getCheckedList(roleId);
	}
	public List<Integer> userIdQueryFunctionId(String user_id){
		return roleMapper.userIdQueryFunctionId(user_id);
	}

	public List<Function> getnoRoleByRoleType(Map<String, Object> map) {
		return roleMapper.getnoRoleByRoleType(map);
	}

	public List<Role> getRoleListByroleType(Map<String, Object> map) {
		return roleMapper.getRoleListByroleType(map);
	}

	public List<Role> getRoleListByUserId(Map<String, Object> map) {
		return roleMapper.getRoleListByUserId(map);
	}

	public List<Role> getRole2listPage(Role role) {
		return roleMapper.getRole2listPage(role);
	}

	/*public List<Role> getRoleByRoleType(Map<String, Object> map1) {
		return roleMapper.getRoleByRoleType(map1);
	}*/

	public List<Role> getRoleListByUserForAdmin(Long userId) {
		return roleMapper.getRoleListByUserForAdmin(userId);
	}
}
