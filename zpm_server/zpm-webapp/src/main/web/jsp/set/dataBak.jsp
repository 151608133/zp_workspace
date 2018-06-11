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
    <title>数据备份</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
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
		maxDate : '+0d'
	});
	$("#enddate").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+0d'
	});
	
	$("#startdate").val("${startdate}");
	$("#enddate").val("${enddate}");
});
function findSysBak(){
	var startdate=$("#startdate").val();
	var enddate=$("#enddate").val();
	if(startdate!=''&&enddate!=''&&enddate<startdate){
		alert("<fmt:message key='please_select_the_correct_time_range'/>！");
		return false;
	}
	$("#myform").submit();
}
function delSysBak(bak_id){
	if(bak_id==0){
		if(confirm("<fmt:message key='are_you_sure_you_want_to_delete_all_the_backup_information'/>？")){
			window.location.href="bak/delSysBak?bak_id="+bak_id;
		}
	}else{
		if(confirm("<fmt:message key='are_you_sure_you_want_to_delete_this_backup_information'/>？")){
			window.location.href="bak/delSysBak?bak_id="+bak_id;
		}
	}
}

function downData(name,filePath){
	window.open("bak/downLoad?fileName="+name+"&filePath="+filePath);
}
</script>
  <body>
<form action="bak/findSysBakInfo" method="post" id="myform">
 <div class="xdd-bjgl">
    <div class="xdd-bjgl-ss">
      <div class="left">
        <span class="xdd-bjgl-ss-span"><fmt:message key='date'/>：<input type="text" id="startdate" name="startdate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" /><fmt:message key='to'/><input type="text" id="enddate" name="enddate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" /></span>
        <span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="findSysBak()"/></span>
        <span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='clear_all_backups'/>" class="btn-cx" style="background:#ff6e3f" onclick="delSysBak(0)"/></span>
      </div>
    </div>
    <table class="tab">
      <tr>
        <td class="tab-bt" width="16%"><fmt:message key='backup_time'/></td>
        <td class="tab-bt" width="37%"><fmt:message key='file_backup'/></td>
        <td class="tab-bt" width="37%"><fmt:message key='database_data_backup'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='operation'/></td>
      </tr>
      <c:forEach items="${sysBakList }" var="sysBak" >
	      <tr>
	        <td><fmt:formatDate value="${sysBak.bak_data}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	        <td><div style="float:left;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:80%;" title="${sysBak.bak_file_path }${sysBak.bak_file_name }">${sysBak.bak_file_path }${sysBak.bak_file_name }</div><div style="float:right;wdith:20%;"><input type="button" value="<fmt:message key='download'/>" class="btn-xg" style="background:#3dcb90;" onclick="downData('${sysBak.bak_file_name }.zip','${sysBak.bak_file_path }${sysBak.bak_file_name }.zip')"/></div></td>
	        <td><div style="float:left;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:80%;" title="${sysBak.bak_data_path }${sysBak.bak_data_name }">${sysBak.bak_data_path }${sysBak.bak_data_name }</div><div style="float:right;wdith:20%;"><input type="button" value="<fmt:message key='download'/>" class="btn-xg" style="background:#3dcb90;" onclick="downData('${sysBak.bak_data_name }.zip','${sysBak.bak_data_path }${sysBak.bak_data_name }.zip')" /></div></td>
	        <td><input type="button" value="<fmt:message key='delete'/>" class="btn-xg" style="background:#ff6e40;" onclick="delSysBak(${sysBak.bak_id})"/></td>
	      </tr>
      </c:forEach>
      <c:if test="${empty sysBakList}">
      	<tr class="xi">
			<td colspan="4"><fmt:message key='no_relevant_data'/></td>
		</tr>
      </c:if>
    </table>
    ${sysBak.page.pageStr}
 </div>
 </form>
</body>
</html>
