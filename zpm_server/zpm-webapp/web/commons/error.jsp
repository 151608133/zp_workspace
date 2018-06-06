<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8"> 
 <head>
<title>应用程序异常 (500)</title> 
<link href="<%=basePath %>css/404ym.css" rel="stylesheet" type="text/css" />
</head> 
 
<body> 
 <div class="ljsb">
<img src="<%=basePath %>images/tu-500.png" /> 
 <div class="ljsb-a">
 <p class="ljsb-wz-a">很抱歉，你来晚了一步，它已任性的消失了...
你要坚强些，继续发掘别的页面去吧</p>
 <div class="ljsb-an" style="text-align:left;">
  <input type="button" value="退出"  onclick="backIndex()"class="ljsb-btn"/>
  <input type="button" value="返回上一页" onclick="backOrder()" class="ljsb-btn"/>
  </div>
 </div>
</body> 
 <script type="text/javascript">
  	function backOrder(){
  		window.history.back(-1);
  	}
  	function backIndex(){
  		window.parent.location.href="login";
  	}
  </script>
</html>
