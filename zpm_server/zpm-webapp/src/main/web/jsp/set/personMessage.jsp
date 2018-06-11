<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人消息详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
  </head>
  
  <body>
  <div class="fk-nr">
    <div class="gongao-xi-top">
      <p class="bt-gongg-xi">${mySysMessageQueue.msgTitle }</p>
      <p class="bt-gong-xi-aa"><Span class="tsshijian"><fmt:formatDate value="${mySysMessageQueue.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></Span></p>
    </div>
    <div class="neirong-gongaoguanli">
      <p>${mySysMessageQueue.msgContent }</p>
    </div>
  </div>
  </body>
</html>
