package com.zp.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zp.sys.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zp.sys.entity.Role;
import com.zp.sys.entity.User;
import com.zp.sys.entity.UserType;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserService{
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录验证
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public User login(Map<String,Object> param) {
		return userMapper.login(param);
	}
	
	/**
	 * APP登录验证
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> appLogin(Map<String,Object> param) {
		return userMapper.appLogin(param);
	}
	
	/**
	 * APP登录更新app_key和last_login_time
	 * @param param
	 * @return
	 */
	public void updateAppUser(Map<String,Object> param) {
		userMapper.updateAppUser(param);
	}
	
	/**
	 * 获取某个用户
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public User getUser(Map<String,Object> param) {
		return userMapper.getUser(param);
	}
	
	public User getUserById(User user){
		return userMapper.getUserById(user);
	}

	/**
	 * 获取用户名列表
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String,Object>> getUserNameList() {
		return userMapper.getUserNameList();
	}
	@Transactional(readOnly = true)
	public List<User> listPageOfUser(Map<String,Object> map) {
		return userMapper.listPageOfUser(map);
	}
	
	/**
	 * 修改用户状态值  
	 * */
	public void updateState(User user) {
		 userMapper.updateState(user);
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * */
	public int addUser(User user){
		return userMapper.addUser(user);
	}
	/**
	 * 获取用户Id
	 */
	public Long getUserId(){
		return userMapper.getuserId();
	}
	public void deleteLcCust(User user){
		userMapper.deleteLcCust(user);
	}
	/**
	 * 修改用户信息
	 * @param user
	 * */
	public void editUser(User user){
		userMapper.editUser(user);
	}
	
	public List<UserType> findTypeList(){
		return userMapper.findTypeList();
	}
	
	public void insertUserRole(Map map){
		userMapper.insertUserRole(map);
	}
	public void insertUserStore(Map map){
		userMapper.insertUserStore(map);
	}
	
	public void deleteStoreUser(User user){
		userMapper.deleteStoreUser(user);
	}
	public void deleteRoleUser(User user){
		userMapper.deleteRoleUser(user);
	}
	
	/**
	 * 根据appkey获取原密码
	 * @param appKey
	 * @param oldPassWord
	 */
	public boolean validateUserPassWord(String appKey,String oldPassWord) {
		Map map = new HashMap();
		map.put("appKey", appKey);
		map.put("oldPassWord", oldPassWord);
		List<User> list =  userMapper.validateUserPassWord(map);
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 密码修改
	 * @param appKey
     * @param oldPassWord
	 */
	public void updateAppPassWord(String appKey,String newPassWord) {
		Map map = new HashMap();
		map.put("appKey", appKey);
		map.put("newPassWord", newPassWord);
		userMapper.updateAppPassWord(map);
	}
	

	public List<User> listPageOfUser2(User user) {
		// TODO Auto-generated method stub
		return userMapper.listPageOfUser2(user);
	}

	public void changeUserPwd(User user) {
		userMapper.changeUserPwd(user);
	}

	public String findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUserName(userName);
	}

	public void updateLastLogin(User user) {
		userMapper.updateLastLogin(user);
		
	}

	public void updateUserHeadImage(User user) {
		userMapper.updateUserHeadImage(user);
	}
	

	public String getExpressByCom(String expressName){
		return userMapper.getExpressByCom(expressName);
	}

	public List<Integer> finLocalStoreIdById(String storeName) {
		// TODO Auto-generated method stub
		return userMapper.finLocalStoreIdById(storeName);
	}

	public void delUserDeliveryPoint(User user) {
		// TODO Auto-generated method stub
		userMapper.delUserDeliveryPoint(user);
	}

	public void addContactUser(Map<String, Object> contactmap) {
		// TODO Auto-generated method stub
		userMapper.addContactUser(contactmap);
	}

	public List<UserType> findRgTypeList() {
		// TODO Auto-generated method stub
		return userMapper.findRgTypeList();
	}
	
	public List<User> getKfUserInfo(String appKey){
		return userMapper.getKfUserInfo(appKey);
	}
	
	public List<String> getUserStoresCode(Long userId){
		return userMapper.getUserStoresCode(userId);
	}

	public List<User> exportListOfUser(Map<String, Object> map) {
		return userMapper.exportListOfUser(map);
	}

	public void updateApplanguage(Map<String, Object> map) {
		userMapper.updateApplanguage(map);
	}
	public List<Role> getRoleList(){
		return userMapper.getRoleList();
	}

	public Long getUserIdBypasswordandusername(Map<String, Object> param) {
		return userMapper.getUserIdBypasswordandusername(param);
	}

	public void editContact_phone(User user) {
		userMapper.editContact_phone(user);
	}

	public List<User> getuserPwdErrorNum(Map<String, Object> map) {
		return userMapper.getuserPwdErrorNum(map);
	}

	public void setPwdErrorTimeAndNum(Map<String, Object> map2) {
		userMapper.setPwdErrorTimeAndNum(map2);
	}

	public String getoldPassword(Long id) {
		return userMapper.getoldPassword(id);
	}
	public List<User> getuserlistPage(User user){
		return userMapper.getuserlistPage(user);
	}
	
	public void jinUser(User user){
		 userMapper.jinUser(user);
	}
	
	public void recUser(User user){
		userMapper.recUser(user);
	}
	public void updateUser(User user){
		userMapper.updateUser(user);
	}
	public User getUserByName(User user){
	    return userMapper.getUserByName(user);
	}
	public User getclnumber(User user){
		return userMapper.getclnumber(user);
	}
	public User getuploadsum(User user){
		return userMapper.getuploadsum(user);
	}
	public User getnoticesum(User user){
		return userMapper.getnoticesum(user);
	}
	public void wkedituserinfo(User user){
		userMapper.wkedituserinfo(user);
	}
	public void wkeditpassword(User user){
		userMapper.wkeditpassword(user);
	}
	public List<User> wkgetmyinfomationlistPage(User user){
		return userMapper.wkgetmyinfomationlistPage(user);
	}
	public void clearallinfo(User user){
		userMapper.clearallinfo(user);
	}
	public void chooseclearinfo(User user){
		userMapper.chooseclearinfo(user);
	}
	public List<User> wkreadrecord(User user){
		return userMapper.wkreadrecord(user);
	}
	public List<User> wkgetcompanynoticelistPage(User user){
		return userMapper.wkgetcompanynoticelistPage(user);
	}
	public int wkredpoint(User user){
		return userMapper.wkredpoint(user);
	}
	public int wkjudgenoticerecord(User user){
		return userMapper.wkjudgenoticerecord(user);
	}
	public void wkinsertnoticerecord(User user){
		 userMapper.wkinsertnoticerecord(user);
	}
	public List<User> getnoticelistPage(User user){
		return userMapper.getnoticelistPage(user);
	}
	public List<User> getbName(User user){
		return userMapper.getbName(user);
	}
	public void insertnotice2(User user){
		userMapper.insertnotice2(user);
	}
    public void insertnotice1(User user){
    	userMapper.insertnotice1(user);
	}
    public void delnotice1(User user){
    	userMapper.delnotice1(user);
    }
    
	public void delnotice2(User user){
		userMapper.delnotice2(user);
	}
	public void updateUserImage(User user){
		userMapper.updateUserImage(user);
	}
	public User getreadsum(User user){
		return userMapper.getreadsum(user);
	}
	public User wklogin(Map<String,Object> param){
		return userMapper.wklogin(param);
	}
	public void insertUserCity(Map<String,Object> param){
		userMapper.insertUserCity(param);
	}
	public void deleteUserCity(User user){
		userMapper.deleteUserCity(user);
	}
}
