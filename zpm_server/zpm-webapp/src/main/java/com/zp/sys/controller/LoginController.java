package com.zp.sys.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zp.core.util.Const;
import com.zp.core.util.Tools;
import com.zp.sys.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.zp.sys.dao.UserMapper;
import com.zp.sys.entity.Function;
import com.zp.sys.entity.ProvinceCity;
import com.zp.sys.entity.User;
import com.zp.sys.listener.MyHttpSessionListener;
import com.zp.sys.util.EncryptUtils;
import com.zp.sys.util.IpMacUtils;
import com.zp.sys.util.JsonModel;


@Controller
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FunctionService funcService;
	@Autowired
	private ProvinceCityService provinceCityService;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserMapper userMapper;
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginGet(){
		//List list = provinceCityService.provinceCityList();
		logger.info("123123");
		return "login";
	}
	
	/**
	 * 跳转到修改初始密码页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginIsBegin",method=RequestMethod.POST)
	public JsonModel loginIsTest666(HttpServletRequest request,HttpSession session){
		JsonModel jm=new JsonModel();
		Object session_obj = session.getAttribute(Const.SESSION_USER); 
		
		if(null != session_obj){//限制每个浏览器只能登陆一个账号,一个session防止session被篡改
			jm.setCode(2);
			jm.setMsg("同浏览器不允许多用户同时登陆,请先关闭浏览器重新打开登陆");
		} else {
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			password = new String(decode(password));
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("name", loginname);
			param.put("password", EncryptUtils.passToCode(password));
			try {
				User user = userService.login(param);
				if(user!=null&&"test666".equals(password)){
					jm.setCode(1);
					jm.setMsg("请修改初始密码!");
				}else{
					if(null == user){
						jm.setCode(0);
					}else if(null != user.getEndEdit_pwd_time()){
						//判断是否超过90天
						long day=(new Date().getTime()-user.getEndEdit_pwd_time().getTime())/(24*60*60*1000);
						if(day>=90){
							jm.setCode(1);
							jm.setMsg("距离上次修改密码超过90天,请重新修改密码!");
						}else{
							jm.setCode(0);
						}
					}else if(null == user.getEndEdit_pwd_time()){
						jm.setCode(1);
						jm.setMsg("根据诺基亚安全访问要求,您需要根据括号内密码规则重新设置密码,(密码规则:必须包含大写字母,小写字母,数字,特殊字符(~!@#$%^&*?)中的三种及以上组合,长度至少8位)");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				jm.setCode(1);
				jm.setMsg("服务器连接错误！");
				return jm;
			}
			
		}
		return jm;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView wkLoginPost(HttpSession session,HttpServletRequest request, HttpServletResponse response){
		String errInfo = "";
		String language = "zh";
		ModelAndView mv = new ModelAndView();
		String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		password = new String(decode(password));
		String code = request.getParameter("code");
		if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("name", loginname);
			param.put("password",EncryptUtils.passToCode(password));
			User user = userService.wklogin(param);
			
			if(null != user && !"00X".equals(user.getStateCd())){
				session.setAttribute(Const.SESSION_USER, user);
				session.removeAttribute(Const.SESSION_SECURITY_CODE);
				//插入操作日志
				operationLogService.insertOperationLog(request, "登陆", "用户登录成功",  loginname,"成功");
			}else{
				//密码错误 被禁用 用户不存在
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("userName", loginname);
				List<User> user2 = userService.getuserPwdErrorNum(map);//通过用户名,获取用户的24小时内是否存在密码输入次数
				if(user2 != null && user2.size()!=0){
					//用户存在但是密码错误/被禁用
					if("00A".equals(user2.get(0).getStateCd())){
						//密码错误(检查密码错误次数)
						Map<String,Object> map2 = new HashMap<String, Object>();
						map2.put("userId", user2.get(0).getUserId());
						if(user2.size()==1){
							//密码错误 没有错误次数 插入错误时间 错误次数
							map2.put("num", 1);
							map2.put("type", "timeAndnum");
							userService.setPwdErrorTimeAndNum(map2);
							errInfo = "密码错误!您在24小时内还有4次密码输入机会";
						}else{
							//密码错误 有错误时间以及次数 判断次数
							int time = user2.get(1).getPwd_errer_number();
							if(time==4){
								map2.put("num", 0);
								map2.put("type", "00X");
								userService.setPwdErrorTimeAndNum(map2);
								errInfo = "密码错误!您在24小时内密码输入错误5次,账号已被禁用,请联系诺基亚客服或者管理员激活";
							}else{
								map2.put("num", time+1);
								map2.put("type", "num");
								userService.setPwdErrorTimeAndNum(map2);
								errInfo = "密码错误!您的账号在24小时内输入错误密码"+(time+1)+"次,剩余"+(4-time)+"次";
							}
						}
						operationLogService.insertOperationLog(request, "登陆", "密码错误", loginname, "失败");
					}else{
						//被禁用
						errInfo = "账号已被禁用,请联系诺基亚客服或者管理员激活";
						operationLogService.insertOperationLog(request, "登陆", "账号已被禁用", loginname, "失败");
					}
				}else{
					//用户不存在
					errInfo = "用户不存在！";
					operationLogService.insertOperationLog(request, "登陆", "用户不存在", loginname, "失败");
				}
				mv.addObject("loginname","");
				mv.addObject("password","");
			}
		}else{
			mv.addObject("loginname",loginname);
			mv.addObject("password",password);
			errInfo = "验证码错误！";
			operationLogService.insertOperationLog(request, "登陆", "验证码错误", loginname, "失败");
		}
		if(Tools.isEmpty(errInfo)){
			mv.setViewName("redirect:index.html");
		}else{
			mv.addObject("errInfo", errInfo);
			mv.setViewName("login");
		}
		return mv;
	}
	
	 
	/**
	 * 访问系统首页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpSession session,Model model){
		User myuser=(User)request.getSession().getAttribute(Const.SESSION_USER);
		User user = (User)session.getAttribute(Const.SESSION_USER);
		if(user==null){
			return "login";
		}
		Map mm = new HashMap();
		mm.put("userId", user.getUserId());
		List<Function> list = funcService.getMenuListByUserId2(mm);
		List<Function> list2 = funcService.getRoleFuncList(user.getUserId());
//		String typeName=userService.getTypeNameByTypeId(user.getUserType());
		StringBuilder role_menus = new StringBuilder();
		int i = 0;
		for (Function function : list2) {
			i++;
			if (i == 1) {
				role_menus.append(function.getUrl());
			}else{
				role_menus.append(";"+function.getUrl());
			}
		}
		
		List<ProvinceCity> cityList = provinceCityService.provinceCityListByUser(user.getUserId());
		
		session.setAttribute("role_menus", role_menus);

		model.addAttribute("user", user);
		model.addAttribute("menuList", list);
//		model.addAttribute("typeName", typeName);
		model.addAttribute("cityList", cityList);
//		model.addAttribute("currentStore", "湖南");
	    return "index";
	}
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value="/default")
	public ModelAndView defaultPage(HttpSession session,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		//shipmentsOrder.setCol11(user.getUserId());
		Date loadtime = user.getAppLastLoginTime();		
		userService.updateLastLogin(user);
		String ip = IpMacUtils.getRemoteAddress(request);
		mv.addObject("user", user);
		mv.addObject("ip", ip);
		mv.addObject("loadtime", loadtime);
		mv.addObject("userCounts", MyHttpSessionListener.getCount());
		mv.setViewName("default");		
		return mv;
	}
	
	
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session,HttpServletRequest request){
		//添加操作日志
		User user = (User)session.getAttribute(Const.SESSION_USER);
		String username = "";
		if(user != null){
			username = user.getUserName();
		}
		operationLogService.insertOperationLog(request, "注销", "退出登录", username, "成功");
		ModelAndView mv = new ModelAndView();
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_USER_RIGHTS);
		session.removeAttribute(Const.SESSION_USER_STORE);
		session.removeAttribute(Const.USER_CURRENTSTORE);
		//注销session
		session.invalidate();
		
		//设置返回视图名
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 修改密码
	 * */
	@RequestMapping("/changePwd")
	public ModelAndView changePwd(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("changepwd");
		return mv;
	}
	/**
	 * 验证密码
	 * */
	@ResponseBody
	@RequestMapping("/checkPwd")
	public JsonModel checkPwd(String password,HttpServletRequest request){
		JsonModel jm=new JsonModel();
		User oldUser=(User) request.getSession().getAttribute(Const.SESSION_USER);
		password = new String(decode(password));		
		Map<String,Object> param=new HashMap<String,Object>();
		String name =userMapper.getUserNameById(oldUser.getUserId());
		param.put("name", name);
		param.put("password", EncryptUtils.passToCode(password));
		User user = userService.login(param);
		if(user==null ){
			jm.setCode(1);
			jm.setMsg("原始密码错误");
		}else{
			jm.setCode(0);
		}
		return jm;
	}
	
	@RequestMapping("/changepwdInfo")
	public ModelAndView changepwdInfo(String password,String password5,String userName,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv=new ModelAndView();
		User user=(User) request.getSession().getAttribute(Const.SESSION_USER);
		if(user==null){//首页直接修改
			password = new String(decode(password));
			password5 = new String(decode(password5));
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("name", userName);
			param.put("password",EncryptUtils.passToCode(password5));
			Long id = userService.getUserIdBypasswordandusername(param);
			User user2 = new User();
			user2.setUserId(id);
			user2.setPassword(EncryptUtils.passToCode(password));
			String oldPassword = userService.getoldPassword(id);
			if(null != oldPassword){
				String[] oldPasswords = oldPassword.split(",");
				int time = 0;
				for(String a:oldPasswords){
					if(a.equals(EncryptUtils.passToCode(password))){
						String errInfo = "修改的密码不能和前十次重复";
						mv.addObject("errInfo",errInfo);
						mv.addObject("password",password5);
						mv.addObject("userName",userName);
						mv.setViewName("login");
						mv.addObject("errType",1);
					    time++;
					    break;
					}
				}
				if(time==0){
					response.setContentType("text/html; charset=UTF-8"); //转码
					PrintWriter out = response.getWriter();
				    out.flush();
					mv.setViewName("login");
					userService.changeUserPwd(user2);
					out.println("<script>");
				    out.println("alert('密码修改成功!请重新登录!');");
				    out.println("</script>"); 
				    //密码修改成功
				    Map<String,Object> map2 = new HashMap<String,Object>();
				    if(null != oldPassword){
				    	if("".equals(oldPassword)){
				    		map2.put("oldPassword",user2.getPassword());
				    	}else if(oldPasswords.length>=10){
					    	oldPassword = oldPassword.substring(oldPassword.indexOf(",",1)+1)+","+user2.getPassword();
					    	map2.put("oldPassword",oldPassword);
					    }else{
					    	oldPassword = oldPassword+","+user2.getPassword();
					    	map2.put("oldPassword",oldPassword);
					    }
				    }else{
				    	map2.put("oldPassword",user2.getPassword());
				    }
				    map2.put("type","EditTime");
				    map2.put("userId",id);
				    userService.setPwdErrorTimeAndNum(map2);
				}
			}else{
				userService.changeUserPwd(user2);
				response.setContentType("text/html; charset=UTF-8"); //转码
			    PrintWriter out = response.getWriter();
			    out.flush();
			    out.println("<script>");
			    out.println("alert('密码修改成功!请重新登录');");
			    out.println("</script>"); 
				mv.setViewName("login");
				 //密码修改成功
			    Map<String,Object> map2 = new HashMap<String,Object>();
			    map2.put("oldPassword",user2.getPassword());
			    map2.put("type","EditTime");
			    map2.put("userId",id);
			    userService.setPwdErrorTimeAndNum(map2);
			}
			return mv;
		}else{
			password = new String(decode(password));
			user.setPassword(EncryptUtils.passToCode(password));
			String oldPassword = userService.getoldPassword(user.getUserId());
			if(null != oldPassword){
				String[] oldPasswords = oldPassword.split(",");
				int time = 0;
				for(String a:oldPasswords){
					if(a.equals(EncryptUtils.passToCode(password))){
						mv.addObject("msg","修改的密码不能和前十次重复");
						mv.setViewName("changepwd");
						time++;
					    break;
					}
				}
				if(time==0){
					userService.changeUserPwd(user);
					response.setContentType("text/html; charset=UTF-8"); //转码
				    PrintWriter out = response.getWriter();
				    out.flush();
				    out.println("<script>");
				    out.println("alert('密码修改成功!请重新登录');");
				    out.println("</script>"); 
					mv.setViewName("changepwd");
					 //密码修改成功
				    Map<String,Object> map2 = new HashMap<String,Object>();
				    if("".equals(oldPassword)){
			    		map2.put("oldPassword",user.getPassword());
			    	}else if(oldPasswords.length>=10){
				    	oldPassword = oldPassword.substring(oldPassword.indexOf(",",1)+1)+","+user.getPassword();
				    	map2.put("oldPassword",oldPassword);
				    }else{
				    	oldPassword = oldPassword+","+user.getPassword();
				    	map2.put("oldPassword",oldPassword);
				    }
				    map2.put("type","EditTime");
				    map2.put("userId",user.getUserId());
				    userService.setPwdErrorTimeAndNum(map2);
				}
				return mv;
			}else{
				userService.changeUserPwd(user);
				response.setContentType("text/html; charset=UTF-8"); //转码
			    PrintWriter out = response.getWriter();
			    out.flush();
			    out.println("<script>");
			    out.println("alert('密码修改成功!请重新登录');");
			    out.println("</script>"); 
				mv.setViewName("changepwd");
				 //密码修改成功
			    Map<String,Object> map2 = new HashMap<String,Object>();
			    map2.put("oldPassword",user.getPassword());
			    map2.put("type","EditTime");
			    map2.put("userId",user.getUserId());
			    userService.setPwdErrorTimeAndNum(map2);
				return mv;
			}
		}
	}
	
	/**
	 * 排序 升序
	 * @param str
	 * @return
	 */
	public String sortNum(String str){
		String[] strs = str.split(",");
		String temp;
		for (int i = 0; i < strs.length; i++) {
			int k = new Integer(strs[i]);
			for (int j = 0; j < strs.length; j++) {
				int f = new Integer(strs[j]);
				if (k <= f) {
					temp = strs[i];
					strs[i] = strs[j];
					strs[j] = temp;
				}
				
			}
		}
		
		StringBuffer sb = new StringBuffer();
		int g = 0;
		for (String s : strs) {
			g ++;
			if (1 == g) {
				sb.append(s);
			} else {
				sb.append(","+s);
			}
		}
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping("/uploadHeadImage")
	public JsonModel uploadFile(HttpServletRequest request){
		String basePath = System.getProperty("user.dir");
		String path = basePath.substring(0, basePath.length()-3)+"/webapps/"; // 与服务器根目级的文件夹名
		long fileSize = 3 * 1024 * 1024;
		 JsonModel jm=new JsonModel();
		 try {
			 MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
			 MultipartFile file =  multipartRequest.getFile("file");
			 if (file.getSize() > fileSize) {
	                jm.setCode(1);
	                jm.setMsg("图片大小不能超过3M");
	                return jm;
	         }
			 User user=(User) request.getSession().getAttribute(Const.SESSION_USER);
			 //旧头像删除
			 String oldfileName=user.getHeadImage();
			 File oldfile=new File(path + "headimages/"+ oldfileName);
			 if(oldfile.exists()){
				 oldfile.delete();
		     }
		     InputStream inStream=file.getInputStream();
		     //虚拟文件路径
		     Date date=new Date();
		     String fileName=date.getTime()+".jpg";
		     FileOutputStream fs=new FileOutputStream( path + "headimages/"+ fileName);
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
			        userService.updateUserHeadImage(user);
			        jm.setCode(0);
		 }catch(Exception e){
			 e.printStackTrace();
			 jm.setCode(1);
			 jm.setMsg(e.getMessage().toString());
		 }
		 return jm;
	}
	
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',  
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',  
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',  
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',  
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',  
        '4', '5', '6', '7', '8', '9', '+', '/', };  
  
	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,  
	        60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  
	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,  
	        -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,  
	        38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,  
	        -1, -1 };  
  
/** 
 * 解密 
 * @param str 
 * @return 
 */  
public static byte[] decode(String str) {  
    byte[] data = str.getBytes();  
    int len = data.length;  
    ByteArrayOutputStream buf = new ByteArrayOutputStream(len);  
    int i = 0;  
    int b1, b2, b3, b4;  
  
    while (i < len) {  
        do {  
            b1 = base64DecodeChars[data[i++]];  
        } while (i < len && b1 == -1);  
        if (b1 == -1) {  
            break;  
        }  
  
        do {  
            b2 = base64DecodeChars[data[i++]];  
        } while (i < len && b2 == -1);  
        if (b2 == -1) {  
            break;  
        }  
        buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));  
  
        do {  
            b3 = data[i++];  
            if (b3 == 61) {  
                return buf.toByteArray();  
            }  
            b3 = base64DecodeChars[b3];  
        } while (i < len && b3 == -1);  
        if (b3 == -1) {  
            break;  
        }  
        buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));  
  
        do {  
            b4 = data[i++];  
            if (b4 == 61) {  
                return buf.toByteArray();  
            }  
            b4 = base64DecodeChars[b4];  
        } while (i < len && b4 == -1);  
        if (b4 == -1) {  
            break;  
        }  
        buf.write((int) (((b3 & 0x03) << 6) | b4));  
    }  
    return buf.toByteArray();  
}  
}
