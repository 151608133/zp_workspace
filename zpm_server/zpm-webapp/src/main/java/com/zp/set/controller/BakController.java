package com.zp.set.controller;

import com.zp.set.entity.SysBak;
import com.zp.set.entity.SysBakRestore;
import com.zp.set.entity.SysBakTask;
import com.zp.set.service.BakService;
import com.zp.set.util.RestoreTask;
import com.zp.core.plugin.Page;
import com.zp.core.util.Const;
import com.zp.core.util.PageUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zp.sys.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bak")
public class BakController {
	
	/**
	 * 数据备份页面
	 */
	@Autowired
	private BakService bakService;
	@RequestMapping("/dataBak")
	public ModelAndView dataBak(SysBak sysBak){
		ModelAndView mv=new ModelAndView();
		List<SysBak> sysBakList = bakService.listPageSysBak(sysBak);
		mv.addObject("sysBakList", sysBakList);
		mv.setViewName("mapper/set/dataBak");
		return mv;
	}
	

	/**
	 * 根据条件查询公告信息
	 * */
	@RequestMapping("/findSysBakInfo")
	public ModelAndView findSysBakInfo(String startdate,String enddate,HttpServletRequest request,SysBak sysBak){
		ModelAndView mv=new ModelAndView();
		mv.addObject("startdate", startdate);
		mv.addObject("enddate", enddate);
		Map<String,Object> map=new HashMap<String,Object>();
		if("".equals(startdate)){
			startdate = null;
		}
		if("".equals(enddate)){
			enddate=null;
		}else{
			enddate+=" 23:59:59";
		}
		PageUtil.getPage(request, map);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		List<SysBak> sysBakList = bakService.findlistPageSysBak(map);
		Page page = (Page)map.get("page");
		sysBak.setPage(page);
		mv.addObject("sysBakList", sysBakList);
		mv.setViewName("mapper/set/dataBak");
		return mv;
	}
	
	/**
	 * 删除公告信息
	 * */
	@RequestMapping("/delSysBak")
	public ModelAndView delSysBak(SysBak sysBak){
		ModelAndView mv=new ModelAndView();
		bakService.updateSysBakStatus(sysBak);
		List<SysBak> sysBakList = bakService.listPageSysBak(sysBak);
		mv.addObject("sysBakList", sysBakList);
		mv.setViewName("mapper/set/dataBak");
		return mv;
	}
	
	/**
	 * 数据恢复页面
	 * @param sysBak
	 * @return
	 */
	@RequestMapping("/dataRestart")
	public ModelAndView dataRestart(SysBak sysBak){
		ModelAndView mv=new ModelAndView();
		List<SysBak> sysBakList = bakService.getSysBak(sysBak);
		mv.addObject("sysBakList", sysBakList);
		mv.setViewName("mapper/set/dataRestart");
		return mv;
	}
	/**
	 * 获取当前系统操作备份还原情况
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryBakTask")
	public String queryBakTask(HttpServletRequest request,HttpServletResponse response){
		List<String> _returnList = new ArrayList<String>();
		List<SysBakTask> sysBakTaskList = bakService.getSysBakTask();
		if(sysBakTaskList.size()>0){
			_returnList.add("1");
			_returnList.add("0");
		}else{
			_returnList.add("0");
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			if(user!=null){
				List<SysBakRestore> sysBakRestoreList = bakService.querySysBakRestore(user.getUserId());
				if(sysBakRestoreList.size()>0){
					//有还原信息且没有进行提示
					_returnList.add("1");
					sysBakRestoreList.get(0).setRestore_status(2);
					bakService.updateSysBakRestore(sysBakRestoreList.get(0));
				}else{
					_returnList.add("0");
				}
			}else{
				_returnList.add("0");
			}
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(_returnList));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 数据还原操作
	 * @param sysBakRestore
	 * @param request
	 * @return
	 */
	@RequestMapping("/restore")
	public ModelAndView restore(SysBakRestore sysBakRestore,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<SysBakTask> sysBakTaskList = bakService.getSysBakTask();
		//判断当前系统是否有备份还原操作在进行
		if(sysBakTaskList.size()>0){
			mv.addObject("info", sysBakTaskList.get(0).getTask_status()+"");
		}else{
			User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
			RestoreTask restoreTask = new RestoreTask(bakService,sysBakRestore,user);
			Thread thread = new Thread(restoreTask);
			thread.start();
			mv.addObject("info", "-1");
		}
		SysBak sysBak = new SysBak();
		sysBak.setBak_id(0);
		List<SysBak> sysBakList = bakService.listPageSysBak(sysBak);
		mv.addObject("sysBakList", sysBakList);
		mv.setViewName("mapper/set/dataRestart");
		return mv;
	}
	/**
	 * 数据下载
	 */
	@RequestMapping("/downLoad")
	public String downLoad(String fileName,String filePath,HttpServletRequest request,HttpServletResponse response){
		FileInputStream in = null;
		OutputStream out = null;
		try{	
			if(fileName!=null&&fileName.equals(new String(fileName.getBytes("iso-8859-1"),"iso-8859-1"))){
				fileName=new String(fileName.getBytes("iso-8859-1"),"UTF-8");
			}
			if(filePath!=null&&filePath.equals(new String(filePath.getBytes("iso-8859-1"),"iso-8859-1"))){
				filePath=new String(filePath.getBytes("iso-8859-1"),"UTF-8");
			}
			if(filePath.indexOf("/")==0){
				filePath = request.getRealPath("/") +(filePath.indexOf("/")==0?"":"/")+filePath;
			}
			File file = new File(filePath);
			//如果文件不存在
			if(!file.exists()){
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print("<script>alert(\"文件已删除，无法下载\");window.close();</script>");
				return null;
			}
			//处理文件名
			//设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//读取要下载的文件，保存到文件输入流
			in = new FileInputStream(filePath);
			//创建输出流
			out = response.getOutputStream();
			//创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			//循环将输入流中的内容读取到缓冲区当中
			while((len=in.read(buffer))>0){
				//输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			//关闭文件输入流
			in.close();
			//关闭输出流
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if(in!=null){
					in.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
