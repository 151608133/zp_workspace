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
    <title>公告管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
  </head>
  <script type="text/javascript">
$(function(){
	$("#startdate").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	});
});
$(function(){
	$("#enddate").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	});
});
</script>
  <body>
<form action="message/findNoticeInfo" method="post" id="myform">
 <div class="xdd-bjgl">
    <div class="xdd-bjgl-ss">
      <div class="left">
        <span class="xdd-bjgl-ss-span">ID：<input type="text" class="input-sr" name="id" id="id" /></span>
        <span class="xdd-bjgl-ss-span"><fmt:message key='date'/>：<input type="text" id="startdate" name="startdate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" /><fmt:message key='to'/><input type="text" id="enddate" name="enddate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" /></span>
        <span class="xdd-bjgl-ss-span"><fmt:message key='push_role_type'/>：<select name="userType" id="userType" class="select-ck">
			<option value="0"><fmt:message key='please_select'/></option>
			<!--<c:forEach items="${typeList }" var="type" varStatus="vs">
				<option value="${type.typeId }">${type.typeName }</option>
			</c:forEach>
			-->
			<c:forEach items="${roleList }" var="role" varStatus="vs">
				<option value="${role.roleId }">${role.roleName }</option>
			</c:forEach>
		</select></span>
        <span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="findNotice()"  /></span>
      </div>
      <div class="right">
        <span><input type="button" value="<fmt:message key='release_announcement'/>" class="btn-cx" onclick="addNotice()"/></span>
      </div>
    </div>
    <table class="tab">
      <tr>
        <td class="tab-bt" width="5%">ID</td>
        <td class="tab-bt" width="25%"><fmt:message key='announcement_title'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='release_person'/></td>
        <td class="tab-bt" width="15%"><fmt:message key='release_time'/></td>
        <td class="tab-bt" width="25%"><fmt:message key='push_role_type'/></td>
        <td class="tab-bt" width="20%"><fmt:message key='operation'/></td>
      </tr>
      <c:forEach items="${listnotice }" var="notice" varStatus="vs">
	      <tr>
	        <td>${notice.id }</td>
	        <td>${notice.noticeTitle }</td>
	        <td>${notice.noticeAuthor }</td>
	        <td><fmt:formatDate value="${notice.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	        <td title="${notice.col2 }">${notice.col2 }</td>
	        <td><input type="button" value="<fmt:message key='modify'/>" class="btn-xg" onclick="edit(${notice.id})"/><input type="button" value="<fmt:message key='delete'/>" class="btn-xg" style="background:#ff6e40;" onclick="delNotice(${notice.id})"/><input type="button" value="<fmt:message key='details'/>" class="btn-xg" style="background:#3c99ef" onclick="noticepar(${notice.id})"/></td>
	      </tr>
      </c:forEach>
    </table>
    ${notice.page.pageStr}
 </div>
 </form>
  </body>
  <script type="text/javascript">
  	function addNotice(){
  		var info = "<fmt:message key='release_announcement'/>";
  		funcAddTab(info,"message/addNotice");
  	}
  	function findNotice(){
  		$("#myform").submit();
  	}
  	function changeValue(){
  		$("#id").val("${id}");
  		$("#startdate").val("${startdate}");
  		$("#enddate").val("${enddate}");
  		$("#userType").val("${userType}");
  	}
  	changeValue();
  	
  	function edit(data){
  		var info = "<fmt:message key='modification_announcement'/>";
  		funcAddTab(info,"message/editNotice?id="+data);
  	}
  	function noticepar(data){
  		var info = "<fmt:message key='announcement_details'/>";
  		funcAddTab(info,"message/notice?id="+data);
  	}
  	function delNotice(data){
  		var info = "<fmt:message key='are_you_sure_you_want_to_delete_this_announcement'/>";
  		if(confirm(info)){
  			window.location.href="message/delNotice?id="+data;
  		}
  	}
  </script>
</html>
