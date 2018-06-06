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
    <title>热门文档</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
  </head>
  <script type="text/javascript">
</script>
  <body>
<form action="${iframeAnalyzeSrc}" method="post" id="myform">
 <div class="xdd-bjgl">
    <table class="tab">
      <tr>
        <td class="tab-bt" width="15%">文件名称</td>
        <td class="tab-bt" width="8%">文档上传</td>
        <td class="tab-bt" width="8%">文件分类</td>
        <td class="tab-bt" width="11%">时间</td>
      </tr>
      <c:forEach items="${hotDocument }" var="hd" varStatus="vs">
	      <tr>
	        <td title="${hd.file_name}">${hd.file_name}</td>
	        <td title="${hd.userName}">${hd.userName}</td>
	        <td title="${hd.typeName}">${hd.typeName}</td>
	        <td title="${hd.time}">${hd.time}</td>
	      </tr>
      </c:forEach>
      <c:if test="${empty hotDocument}">
      	<tr class="xi">
			<td colspan="4"><fmt:message key='no_relevant_data'/></td>
		</tr>
      </c:if>
    </table>
    ${Hd.page.pageStr}
 </div>
 </form>
</body>
</html>
