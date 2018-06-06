package com.zp.sys.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zp.core.util.Const;
import com.zp.sys.entity.ProvinceCity;
import com.zp.sys.entity.Role;
import com.zp.sys.service.ProvinceCityService;
import com.zp.sys.service.RoleService;
import com.zp.sys.service.UserService;
import com.zp.sys.util.EncryptUtils;
import com.zp.sys.util.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.zp.sys.entity.User;
import com.zp.sys.service.OperationLogService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProvinceCityService provinceCityService;
	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping
	public ModelAndView getUsers(HttpServletRequest request,User user,HttpSession session){
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
		user.setUserId(myuser.getUserId());
		List<User> userList = userService.listPageOfUser2(user);
		List<Role> rolelist=userService.getRoleList();
		String roleId=request.getParameter("roleId");
		String stateCd=request.getParameter("stateCd");
		ModelAndView mv = new ModelAndView();
		//设置返回视图的名字
		mv.addObject("userList", userList);
		mv.addObject("myuser", user);
		mv.addObject("rolelist", rolelist);
		mv.addObject("roleId", roleId);
		mv.addObject("stateCd", stateCd);
		mv.setViewName("system/users");
		operationLogService.insertOperationLog(request, "用户管理", "用户管理-展示用户列表",
				"查询用户列表", "成功");
		return mv;
	}
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView postUsers(HttpServletRequest request,User user,HttpSession session){
//		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
//		User user1= (User)session.getAttribute(Const.SESSION_USER);
//		user.setUserId(myuser.getUserId());
//		user.setUserType1(user1.getUserType());
//		List<User> userList = userService.listPageOfUser2(user);
//		List<Role> rolelist=userService.getRoleList();
//		ModelAndView mv = new ModelAndView();
//		//设置返回视图的名字
//		mv.addObject("userList", userList);
//		mv.addObject("rolelist", rolelist);
//		mv.setViewName("system/users");
//		return mv;
//	}
	/**
	 * 禁用
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jinUser")
	public JsonModel jinUser(HttpServletRequest request, User user){
		JsonModel jm=new JsonModel();
		userService.jinUser(user);
		jm.setCode(0);
		operationLogService.insertOperationLog(request, "用户管理", "禁用用户",
				"禁用用户", "成功");
		return jm;
	}
	/**
	 * 恢复
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/RecoveryUser")
	public JsonModel recUser(HttpServletRequest request,User user){
		JsonModel jm=new JsonModel();
		userService.recUser(user);
		jm.setCode(0);
		operationLogService.insertOperationLog(request, "用户管理", "恢复用户",
				"恢复用户", "成功");
		return jm;
	}
	/**
	 * 请求修改用户页面
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(HttpServletRequest request,User user){
		ModelAndView mv=new ModelAndView();
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);//当前用户
		User thisuser = userService.getUserById(user);//要修改的用户
		thisuser.setPassword(EncryptUtils.codeToPass(thisuser.getPassword()));
		Long userid=(long) Integer.parseInt(request.getParameter("userId"));
		List<Role> rolelist=userService.getRoleList();
		Role allRolListOfMine=roleService.getRoleListByUser(userid).get(0);
		List<ProvinceCity> citylist =provinceCityService.provinceCityListByUid(1);
		mv.addObject("citylist", citylist);
	    mv.addObject("thisuser", thisuser);
		mv.setViewName("system/editUserInfo");
		mv.addObject("rolelist", rolelist);
		mv.addObject("allRolListOfMine", allRolListOfMine);
		return mv;
	}
	/**
	 * 修改用户信息
	 * */
	@RequestMapping("/editUser1")
	public ModelAndView editUser(User user,String roleId,String cityId,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		Date date=new Date();					
		User it = userService.getUserById(user);
		if(it.getPassword().equals(user.getPassword())){//没有更改密码
			try{
				userService.updateUser(user);	
				mv.setViewName("system/editUserInfo");
				mv.addObject("flag","success");
				}catch(Exception e){
					e.printStackTrace();
					mv.setViewName("order/operationFailure");
				}
		}else{//更改 密码
						user.setEndEdit_pwd_time(date);
						String password=EncryptUtils.passToCode(user.getPassword());
						user.setPassword(password);
						try{
							userService.updateUser(user);	
							mv.setViewName("system/editUserInfo");
							mv.addObject("flag","success");
							}catch(Exception e){
								e.printStackTrace();
								mv.setViewName("order/operationFailure");
							}
		}
		userService.deleteRoleUser(user);
		Long userId=user.getUserId();
		Map<String,Object> map=null;
		map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		userService.insertUserRole(map);
		
		List<Role> rolelist=userService.getRoleList();
		Role allRolListOfMine=roleService.getRoleListByUser(userId).get(0);
		List<ProvinceCity> citylist =provinceCityService.provinceCityListByUid(1);
		mv.addObject("citylist", citylist);
		mv.addObject("thisuser", user);
		mv.addObject("rolelist", rolelist);
		mv.addObject("allRolListOfMine", allRolListOfMine);
		operationLogService.insertOperationLog(request, "用户管理", "修改用户",
				"修改用户信息", "成功");
		return mv;
	}
	/**
	 * 请求新增用户页面
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView toAdd(HttpServletRequest request,User user){
		ModelAndView mv=new ModelAndView();
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
		List<Role> rolelist=userService.getRoleList();
		List<ProvinceCity> citylist =provinceCityService.provinceCityListByUid(1);
		mv.addObject("citylist", citylist);
		mv.setViewName("system/addUsersInfo");
		mv.addObject("rolelist", rolelist);
		return mv;
	}
	/**
	 * 添加用户信息
	 * */
	@RequestMapping("/addUser")
	public ModelAndView addUser(User user,String roleId,String cityId,HttpServletRequest request,HttpSession session){		
		ModelAndView mv = new ModelAndView();
//		Date date=new Date();
//		user.setCreateDate(date);
		String password=EncryptUtils.passToCode(user.getPassword());
		user.setPassword(password);
		try{
		userService.addUser(user);	
		Long userId=user.getUserId();
		Map<String,Object> map=null;
		map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("roleId", Integer.parseInt(roleId));
		userService.insertUserRole(map);
		mv.addObject("flag","success");
		mv.setViewName("system/addUsersInfo");
		
		List<Role> rolelist=userService.getRoleList();
		Role allRolListOfMine=roleService.getRoleListByUser(userId).get(0);
		List<ProvinceCity> citylist =provinceCityService.provinceCityListByUid(1);
		mv.addObject("citylist", citylist);
		mv.addObject("user", user);
		mv.addObject("rolelist", rolelist);
		mv.addObject("allRolListOfMine", allRolListOfMine);
		}catch(Exception e){
			e.printStackTrace();
			mv.setViewName("order/operationFailure");
		}
		operationLogService.insertOperationLog(request, "用户管理", "添加用户",
				"添加用户信息", "成功");
		return mv;
	}
	/**
	 * 获取消息管理列表
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNoticelist")
	public ModelAndView getnoticelist(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		List<User> noticelist = userService.getnoticelistPage(user);
		mv.addObject("noticelist", noticelist);
		mv.setViewName("system/noticemanage");
		return mv;
	}
	/**
	 * 进入修改消息页面
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/Editnotice")
	public ModelAndView editnotice(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/noticemanage");
		return mv;
	}
	/**
	 * 修改消息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("updnotice")
	public ModelAndView updnotice(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/noticemanage");
		return mv;
	}
	/**
	 * 进入添加消息页面
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/Addnotice")
	public ModelAndView Addnotice(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();	
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);//当前用户
		List<User> userlist = userService.getbName(myuser);
		mv.addObject("userlist", userlist);
		mv.setViewName("system/addnotice");		
		return mv;
	}
	/**
	 * 添加消息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertnotice")
	public ModelAndView insertnotice(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);//当前用户
		try{
			user.setUserId(myuser.getUserId());
			userService.insertnotice1(user);
			if("P".equals(user.getSendObject())){
			userService.insertnotice2(user);
			}
			mv.setViewName("system/AddSuccess");
			}catch(Exception e){
				e.printStackTrace();
				mv.setViewName("system/AddFailure");
			}
			return mv;
	}
	/**
	 * 删除消息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletenotice")
	public ModelAndView deletenotice(User user,HttpServletRequest request){		
		ModelAndView mv = new ModelAndView();
		try{
			userService.delnotice1(user);
			userService.delnotice2(user);
			mv.setViewName("system/AddSuccess");
	}catch(Exception e){
		e.printStackTrace();
		mv.setViewName("system/AddFailure");
	}
			
			return mv;
	}
//---------------------------------------------以上管理-------------------------------------------------
	/**
	 * 个人中心
	 */
	@RequestMapping("/PersonalCenter")
	public ModelAndView PersonalCenter(User user,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
		if(myuser!=null){
			User cl = userService.getclnumber(myuser);
			User notice = userService.getnoticesum(myuser);
			User upload = userService.getuploadsum(myuser);
		    mv.setViewName("wkmycenter");
		    mv.addObject("cl", cl);
		    mv.addObject("notice", notice);
		    mv.addObject("upload", upload);
		    mv.addObject("realName", myuser.getRealName());
		    mv.addObject("userId", myuser.getUserId());
		    mv.addObject("toux", myuser.getHeadImage());
		}else{
			mv.setViewName("login");	
		}
		return mv;
	}
	/**
	 * 我的信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/MyInfo")
	public ModelAndView MyInfo(User user,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
		if(myuser!=null){
			User user1 = userService.getUserById(myuser);
		    mv.setViewName("wkmyInfo");
		    mv.addObject("user", user1);
		}else{
			mv.setViewName("login");	
		}
		return mv;
	}
	 
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editUserinfo")
	public JsonModel dongUser(User user){
		JsonModel jm = new JsonModel();
		userService.wkedituserinfo(user);
		User user1 = userService.getUserById(user);
		jm.setCode(0);
		jm.setObj(user1);
		return jm;
	}
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editpassword")
	public JsonModel editpassword(User user){
		JsonModel jm = new JsonModel();
		user.setPassword(EncryptUtils.passToCode(user.getPassword()));
		userService.wkeditpassword(user);
		User user1 = userService.getUserById(user);
		
		
		jm.setCode(0);
		jm.setObj(user1);
		return jm;
	}
	/**
	 * 修改头像
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/Editphoto")
	public JsonModel uploadFile(HttpServletRequest request,User user,HttpSession session){
		String basePath = System.getProperty("user.dir");
		String path = basePath.substring(0, basePath.length()-3)+"/webapps/"; // 与服务器根目级的文件夹名
		long fileSize = 3 * 1024 * 1024;
		JsonModel jm=new JsonModel();
		 try {
			 MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
			 MultipartFile file =  multipartRequest.getFile("imagefile");
			 if (file.getSize() > fileSize) {
	                jm.setCode(1);
	                jm.setMsg("图片大小不能超过3M");
	                return jm;
	         }
			//旧头像删除
			 String oldfileName=user.getHeadImage();
			 File oldfile=new File(path + "	/"+ oldfileName);
			 if(oldfile.exists()){
				 oldfile.delete();
		     }
			 InputStream inStream=file.getInputStream();
			//虚拟文件路径
		     Date date=new Date();
		     String fileName=date.getTime()+".jpg";
		     FileOutputStream fs=new FileOutputStream( path + "WkImage/"+ fileName);
			        byte[] buffer =new byte[1024*1024];
			        int bytesum = 0;
			        int byteread = 0; 
			        while ((byteread=inStream.read(buffer))!=-1){
			           bytesum+=byteread;
			           fs.write(buffer,0,byteread);
			           fs.flush();
			        } 
			        fs.close();
			        inStream.close();
			        
			        
			        user.setHeadImage(fileName);
			        userService.updateUserImage(user);
			        User newuser = userService.getUserById(user);
			        session.setAttribute(Const.SESSION_USER, newuser);			    	
			        jm.setCode(0);
		 }catch(Exception e){
			 e.printStackTrace();
			 jm.setCode(1);
			 jm.setMsg(e.getMessage().toString());
		 }  
		return jm;
	}
//----------------------------------------------以上书库------------------------------------------------	
	/**
	 * 验证用户名不能相同
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/checkUser")
	public JsonModel checkUser(String userName){
		JsonModel jm=new JsonModel();
		String name=userService.findUserByUserName(userName);
		if(name==null){
			jm.setCode(0);
		}else{
			jm.setCode(1);
			jm.setMsg("用户名已存在");
		}
		return jm;
	}
	
}
