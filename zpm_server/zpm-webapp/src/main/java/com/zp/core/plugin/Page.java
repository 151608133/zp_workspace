package com.zp.core.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zp.core.util.Const;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class Page {
	private int showCount = 10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		if (0 ==  totalPage) {
			totalPage = 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		if (0 == totalPage) {
			totalPage = 1;
		}
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage() && 0 != getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getPageStr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object obj = request.getSession().getAttribute(Const.SESSION_SYS_LANGUAGE); //Session中获取语言类型
		String language = "zh";
		if (null != obj) {
			language = obj.toString();
		}
		StringBuffer sb = new StringBuffer();
		if(language.equals("en")){
			//if(totalResult>0){
			sb.append("<input type=\"hidden\" name=\"page.CurrentPage\" id=\"page.CurrentPage\" value='1'>");
			sb.append("<input type=\"hidden\" name=\"page.totalResult\" id=\"page.totalResult\" value='"+totalResult+"'>");
			sb.append("<input type=\"hidden\" name=\"page.totalPage\" id=\"page.totalPage\" value='"+totalPage+"'>");
			sb.append("<div class=\"fy\">");
			sb.append("<div class=\"left\">co "+totalResult+" items</div>\n");
			sb.append("<div class=\"right\">\n"); 
			sb.append("<div class=\"fy-tz\">\n");
			sb.append("pageUS "+currentPage+" pageCN\n");
			sb.append("co "+totalPage+" page&nbsp;jump to\n"); 
			sb.append("<input type=\"text\" class=\"input-ym\" id=\"go_ys\" name=\"go_ys\"/>\n"); 
			sb.append(" page <input type=\"button\" value=\"GO\" class=\"fy-tz-go\" onclick=\"go_fy("+totalPage+","+currentPage+")\"/></div>\n");
			sb.append("	<ul class=\"fy-ym\">\n");
			if(currentPage==1){
				sb.append("	<li >First</li>\n");
				sb.append("	<li >Upper</li>\n");
			}else{	
				sb.append("	<li onclick=\"nextPage(1)\" href=\"javascript:void(0);\">First</li>\n");
				sb.append("	<li onclick=\"nextPage("+(currentPage-1)+")\" href=\"javascript:void(0);\">Upper</li>\n");
			}
			int showTag = 3;	//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li class=\"current\">"+i+"</li>\n");
				else
					sb.append("	<li onclick=\"nextPage("+i+")\" href=\"javascript:void(0);\">"+i+"</li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li>Down</li>\n");
				sb.append("	<li>End</li>\n");
			}else{
				sb.append("	<li onclick=\"nextPage("+(currentPage+1)+")\" href=\"javascript:void(0);\">Down</li>\n");
				sb.append("	<li onclick=\"nextPage("+totalPage+")\" href=\"javascript:void(0);\">End</li>\n");
			}
			//sb.append("	<li >第"+currentPage+"页</li>\n");
			//sb.append("	<li >共"+totalPage+"页</li>\n");
			sb.append("</ul>\n");
			sb.append("</div>\n");
			sb.append("</div>\n");
			sb.append("</div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	document.getElementById(\"page.CurrentPage\").value = page;\n");
			sb.append("	if(true && document.forms[0]){\n");
//			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
//			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		document.forms[0].action = url+page;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("function go_fy(totalPage,currentPage){");
			sb.append("	var value = $(\"#go_ys\").val();");
			sb.append("	if('' == value){alert(\"You must enter a number\");$(\"#go_ys\").focus();} else if(isNaN(value)){alert(\"Must be a number\");$(\"#go_ys\").val(\"\");$(\"#go_ys\").focus();}else if (value > totalPage) {alert(\"Can not be greater than the total number of pages\");$(\"#go_ys\").val(\"\");$(\"#go_ys\").focus();}else if (value == currentPage) {alert(\"Has been on the current page\");$(\"#go_ys\").focus();} else {nextPage(value);}}\n");
			sb.append("</script>\n");
		//}
		}else{
			//if(totalResult>0){
			sb.append("<input type=\"hidden\" name=\"page.CurrentPage\" id=\"page.CurrentPage\" value='1'>");
			sb.append("<input type=\"hidden\" name=\"page.totalResult\" id=\"page.totalResult\" value='"+totalResult+"'>");
			sb.append("<input type=\"hidden\" name=\"page.totalPage\" id=\"page.totalPage\" value='"+totalPage+"'>");
			sb.append("<div class=\"fy\">");
			sb.append("<div class=\"left\">共"+totalResult+"条记录</div>\n");
			sb.append("<div class=\"right\">\n"); 
			sb.append("<div class=\"fy-tz\">\n");
			sb.append("第 "+currentPage+" 页\n");
			sb.append("共 "+totalPage+" 页&nbsp;跳至\n"); 
			sb.append("<input type=\"text\" class=\"input-ym\" id=\"go_ys\" name=\"go_ys\"/>\n"); 
			sb.append(" 页 <input type=\"button\" value=\"GO\" class=\"fy-tz-go\" onclick=\"go_fy("+totalPage+","+currentPage+")\"/></div>\n");
			sb.append("	<ul class=\"fy-ym\">\n");
			if(currentPage==1){
				sb.append("	<li >首</li>\n");
				sb.append("	<li >上</li>\n");
			}else{	
				sb.append("	<li onclick=\"nextPage(1)\" href=\"javascript:void(0);\">首</li>\n");
				sb.append("	<li onclick=\"nextPage("+(currentPage-1)+")\" href=\"javascript:void(0);\">上</li>\n");
			}
			int showTag = 3;	//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li class=\"current\">"+i+"</li>\n");
				else
					sb.append("	<li onclick=\"nextPage("+i+")\" href=\"javascript:void(0);\">"+i+"</li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li>下</li>\n");
				sb.append("	<li>尾</li>\n");
			}else{
				sb.append("	<li onclick=\"nextPage("+(currentPage+1)+")\" href=\"javascript:void(0);\">下</li>\n");
				sb.append("	<li onclick=\"nextPage("+totalPage+")\" href=\"javascript:void(0);\">尾</li>\n");
			}
			//sb.append("	<li >第"+currentPage+"页</li>\n");
			//sb.append("	<li >共"+totalPage+"页</li>\n");
			sb.append("</ul>\n");
			sb.append("</div>\n");
			sb.append("</div>\n");
			sb.append("</div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	document.getElementById(\"page.CurrentPage\").value = page;\n");
			sb.append("	if(true && document.forms[0]){\n");
//			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
//			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		document.forms[0].action = url+page;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("function go_fy(totalPage,currentPage){");
			sb.append("	var value = $(\"#go_ys\").val();");
			sb.append("	if('' == value){alert(\"必须输入数字\");$(\"#go_ys\").focus();} else if('0' == value){alert(\"页数不能为0\");$(\"#go_ys\").focus();} else if(isNaN(value)){alert(\"必须是数字\");$(\"#go_ys\").val(\"\");$(\"#go_ys\").focus();}else if (value > totalPage) {alert(\"不能大于总页数\");$(\"#go_ys\").val(\"\");$(\"#go_ys\").focus();}else if (value == currentPage) {alert(\"已在当前页\");$(\"#go_ys\").focus();} else {nextPage(value);}}\n");
			sb.append("</script>\n");
		//}
		}
		pageStr = sb.toString();
		//System.out.println(pageStr);
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public boolean isEntityOrField() {
		return entityOrField;
	}
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
}
