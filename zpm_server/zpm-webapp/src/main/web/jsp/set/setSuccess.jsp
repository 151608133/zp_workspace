<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>设置/反馈--操作成功</title>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
	
	<link href="<%=basePath %>css/tishi.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
</head>

<body>

<div class="sy-right">

  <div class="tishi" style="text-align:center;">
  
    <p class="tishi-a"><img src="<%=basePath %>images/chengong.png" />
    <span style="margin-left:10px;"><fmt:message key='successful_operation'/>！</span>
    <!-- <fmt:message key='successful_operation'/> -->
    
    </p>
     
  </div> 
</div>
</body>
</html>