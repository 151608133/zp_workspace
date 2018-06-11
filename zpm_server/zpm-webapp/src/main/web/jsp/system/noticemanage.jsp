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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
<link href="<%=request.getContextPath()%>/css/index.css"  type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/dingdan.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.validate.min.js"></script>
<title>公告管理</title>
</head>
<body>
	<form method="post" name="Form" id="Form" action="<%=basePath %>user/getNoticelist">
	<div class="xdd-bjgl">
	<div class="xdd-bjgl-ss2">
	<div class="left">
		<span class="xdd-bjgl-ss-span">
		创建者：<input type="text" name="realName" id="realName" class="input-sr"/>
		</span>	
		<span class="xdd-bjgl-ss-span">
		<input type="button" value="<fmt:message key="query" />" class="btn-cx" onclick="search()"/>
		</span>
		<span class="xdd-bjgl-ss-span">
		</span>
		<span class="xdd-bjgl-ss-span">
		<input type="button" value="<fmt:message key="add_to" />" class="btn-cx" style="background:#3dcb90;float:right;margin-bottom: 5px;" onclick="funcAddTab('添加消息','<%=basePath %>user/Addnotice')" />
		</span>
		</div>
	</div>
	<table class="tab" style="text-align: center;">
		<tr >
			<th class="tab-bt" width="5%"><fmt:message key="serial_number" /></th>
			<th class="tab-bt" width="6%">创建者</th>
			<th class="tab-bt" width="10%">消息标题</th>
			<th class="tab-bt" width="10%">消息内容</th>
			<th class="tab-bt" width="11%">创建时间</th>
			<th class="tab-bt" width="5%">消息类型</th>
			<th class="tab-bt" width="8%">简介</th>
			<th class="tab-bt" width="6%">接收者</th>
			<th class="tab-bt" width="5%"><fmt:message key="operation" /></th>
		</tr>
		<c:choose>
			<c:when test="${not empty noticelist}">
				<c:forEach items="${noticelist}" var="n" varStatus="vs">
				<tr id="p${n.noticeId}">
				<td>${vs.index+1}</td>
				<td>${n.realName}</td>
				<td>${n.noticeTitle}</td>
				<td>${n.noticeContent}</td>
				<td><fmt:formatDate value="${n.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<c:if test="${n.sendObject=='P'}">个人消息</c:if>
				<c:if test="${n.sendObject=='A'}">公司公告</c:if>
				</td> 
				<td>${n.smallTitle}</td> 		
				<td>
				<c:if test="${n.bName!=null}">${n.bName}</c:if>
				<c:if test="${n.bName==null}">全体</c:if>
				</td>
				<td>
				<input type="button"  class="btn-xg" value="删除" onclick="del(${n.noticeId});" style="background:#ff6e40;"/>				            
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="9"><fmt:message key='no_relevant_data'/></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
		${user.page.pageStr}
	</div>
	
	</form>
	
	<script type="text/javascript">
	function search(){		
		$("#Form").submit();
	}
	function del(data){
		if(confirm("是否删除？")){
        	window.location.href="deletenotice?noticeId="+data;
        } 
	}
</script>
</body>
</html>