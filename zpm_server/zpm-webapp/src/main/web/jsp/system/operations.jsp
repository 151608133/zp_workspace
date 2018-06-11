<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>操作模块</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/dingdan.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/zn_datepicker.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css" />
		<script type="text/javascript" src="<%=basePath%>js/ui.datepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
		<link href="<%=basePath %>css/jquery.searchableSelect.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.searchableSelect.js"></script>
	</head>
<body>
	<div class="xdd">
	<form action="<%=basePath %>opt.html" method="post" name="operationForm" id="operationForm">
	<div class="xdd">
    <c:forEach items="${menuList}" var="sub">
			<c:choose>
				<c:when test="${not empty sub.url}">
					 <a class="xdd-nr" href="javascript:void(0);" style="background:${sub.css};"onclick="funcAddTab('${sub.funcName}','<%=basePath %>${sub.url}');">
     				 <p class="xdd-nr-img"><img src="<%=basePath %>${sub.image}" width="70" height="70" /><c:if test="${'备件替换发货'==sub.funcName}"><c:if test="${user.orderCount!=0}"><span class="xdd-nr-img-sz">${user.orderCount}</span></c:if></c:if></p>
      				<p>${sub.funcName}</p>
    				</a>
				</c:when>
				<c:otherwise>
				 <a class="xdd-nr" href="javascript:void(0);" style="background:${sub.css};">
     				 <p class="xdd-nr-img"><img src="<%=basePath %>${sub.image}" width="70" height="70" /></p>
      				<p>${sub.funcName}</p>
    				</a>
			</c:otherwise>
			</c:choose>
		</c:forEach>	
  </div>
	</form>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">		
	</script>
</body>
</html>