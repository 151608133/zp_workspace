package com.zp.set.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zp.set.entity.Param;
import com.zp.set.service.ParamService;
import com.zp.set.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.zp.sys.util.JsonModel;


/**
 * @author lht
 * @since  2016-09-04
 */
@Controller
@RequestMapping("/param")
public class ParamController {
	@Autowired
	private ParamService paramService;

	/**
	 * 系统设置页面
	 * @return
	 */
	@RequestMapping("/systemSet")
	public ModelAndView systemSet(){
		ModelAndView mv=new ModelAndView();
		Param param = paramService.getParam();
		mv.addObject("paramMap", param);
		mv.setViewName("mapper/set/systemSet");
		return mv;
	}
	/**
	 * 修改系统设置信息
	 * @param request
	 * @param param
	 * @return
	 * */
	@RequestMapping("/update_param2")
	public ModelAndView toUpdate(HttpServletRequest request,@ModelAttribute Param param){
		ModelAndView mv=new ModelAndView();
		paramService.updateState(param);
		mv.addObject("paramMap", param);
		mv.setViewName("mapper/set/systemSet");
		return mv;//"redirect:systemSet"
	}
	/**
	 * 修改系统设置信息
	 * @param param
	 * @return
	 * */
	@ResponseBody
	@RequestMapping("/update_param")
	public JsonModel toUpdate2(@ModelAttribute Param param){
		paramService.updateState(param);
		JsonModel jm=new JsonModel();
		if(param==null){
			jm.setCode(0);
			jm.setMsg("修改失败");
		}else{
			jm.setCode(1);
			jm.setMsg("修改成功");
		}
		return jm;
	}

	/**
	 * SOP/WI文件 下载
	 * */
	@RequestMapping("/shareFile")
	public ModelAndView shareFile(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		String path = "";
		try {
			props.load(in);
			path=props.getProperty("SHAREFILE_PATH");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileName = request.getParameter("fileName");
		File file = new File(path);
		List<Map<String,String>> _fileList = new ArrayList<Map<String,String>>();
		if(file.exists()){
			if(file.isDirectory()){
				_fileList=new ParamUtil().getFileMes(file,fileName);
			}
		}
		mv.addObject("fileName", fileName);
		mv.addObject("fileList", _fileList);
		mv.setViewName("mapper/set/shareFile");
		return mv;
	}
	
	/**
	 * SOP/WI文件 下载
	 * @throws Exception 
	 * */
	@RequestMapping("/download")
	public void downLoad(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
			String fileName = request.getParameter("filename");
	        response.setContentType("text/html;charset=UTF-8");   
	        BufferedInputStream in = null;  
	        BufferedOutputStream out = null;  
	        request.setCharacterEncoding("UTF-8");  
	        String rootpath = request.getSession().getServletContext().getRealPath( "/");  
	        System.out.println(rootpath);
	        //String fileName="EOMSModel.xlsx";
	        try {  
	            File f = new File(rootpath + "upload/" + fileName);  
	            response.setContentType("application/x-excel");  
	            response.setCharacterEncoding("UTF-8");  
	            response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
	            response.setHeader("Content-Length",String.valueOf(f.length()));  
	            in = new BufferedInputStream(new FileInputStream(f));  
	            out = new BufferedOutputStream(response.getOutputStream());  
	            byte[] data = new byte[1024];  
	            int len = 0;  
	            while (-1 != (len=in.read(data, 0, data.length))) {  
	                out.write(data, 0, len);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (in != null) {  
	                in.close();  
	            }  
	            if (out != null) {  
	                out.close();  
	            }  
	        }  
	    } 
	
	@RequestMapping("/softSversion")
	public ModelAndView softSversion(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mapper/set/softSversion");
		return mv;
	}
	/**
	 * 删除文件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delData")
	public String delData(HttpServletRequest request,HttpServletResponse response){
		String filePath = request.getParameter("filePath");
		File file = new File(filePath);
		String msg="0";
		if(file.exists()){
			if(file.isFile()){
				if(file.delete()){
					msg="1";
				}
			}
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 上传文件
	 * @return
	 */
	@RequestMapping("/uploadfile")
	public ModelAndView uploadfile(HttpServletRequest request){
		try {
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
			MultipartFile file  =   multipartRequest.getFile("file");
			if(file.getSize()>0){
				String fileName=file.getOriginalFilename();
				Properties props = new Properties();
				InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
				props.load(in);
				//文件保存的路径
				String savePath=props.getProperty("SHAREFILE_PATH");
				File saveFile = new File(savePath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				savePath+=fileName;
				saveFile = new File(savePath);
				if(saveFile.exists()){
					saveFile.delete();
				}
				//文件复制到指定文件夹
				FileOutputStream fos = new FileOutputStream(saveFile);
				InputStream is = file.getInputStream();
				byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
	            }
	            fos.close();
	            is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv=shareFile(request);
		mv.addObject("info", "ok");
		return mv;
	}
}
