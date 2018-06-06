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
    <title>系统提醒</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/tishi.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
	<script type="text/javascript">
		function exportdata(){
			window.location.href="<%=request.getContextPath()%>/message/exportYujin"
		}
	</script>
  </head>
  <body>
  <div class="xdd-bjgl">
  <form action="message/SystemMessage" method="post">
  	 <div class="right">
			<input type="button" value="导出" onclick="exportdata()" class="btn-cx" style="margin-bottom: 10px;">&nbsp;&nbsp;&nbsp;
	 </div>
      <table class="tab">
		<tr >
			<td class="tab-bt" width="5%"><fmt:message key='warehouse_name'/></td>
			<td class="tab-bt" width="5%">WBS</td>
			<td class="tab-bt" width="5%"><fmt:message key="plate_number" /></td>
			<td class="tab-bt" width="4%"><fmt:message key='stock_number'/></td>
			<td class="tab-bt" width="4%">预警阈值</td>
		</tr>
       <c:choose>
			<c:when test="${not empty listStock}">
		        <c:forEach items="${listStock }" var="stock" varStatus="vs">
		        <tr>
		        	<td>${stock.storeName}</td>
		        	<td>${stock.wbs}</td>
					<td>${stock.plateCode}</td>
					<td>${stock.plateCount}</td>
					<td>${stock.threshold_value}</td>
				</tr>
		       </c:forEach>
        	</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3"><fmt:message key='no_news'/></td>
				</tr>
			</c:otherwise>
		</c:choose>
		</table>
		${stock.page.pageStr}
</form>	
</div>

  </body>

</html>
