package com.zp.core.util;

import com.zp.core.plugin.Page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class PageUtil {
	public static void getPage(HttpServletRequest request,Map<String,Object> map){
		if(null !=request.getParameter("page.CurrentPage") && !"".equals(request.getParameter("page.CurrentPage"))
				&& null !=request.getParameter("page.totalResult") && !"".equals(request.getParameter("page.totalResult"))
				&& null !=request.getParameter("page.totalPage") && !"".equals(request.getParameter("page.totalPage"))){
				int currentPage = Integer.parseInt(request.getParameter("page.CurrentPage"));
				int totalResult = Integer.parseInt(request.getParameter("page.totalResult"));
				int totalPage = Integer.parseInt(request.getParameter("page.totalPage"));
				Page page = new Page();
				page.setCurrentPage(currentPage);
				page.setTotalPage(totalPage);
				page.setTotalResult(totalResult);
				map.put("page", page);
		}
	}
}
