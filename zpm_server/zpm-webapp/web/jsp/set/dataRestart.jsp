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
    <title>数据恢复</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	$(function(){
		var info="${info}";
		if(info!=null&&info!=""){
			if(info=="-1"){
				alert("<fmt:message key='successful_operation'/>，<fmt:message key='the_system_is_being_restored'/>，<fmt:message key='please_be_patient.'/>！");
			}else if(info=="1"){
				alert("<fmt:message key='operation_failed'/>，<fmt:message key='data_backup_is_being_carried_out_in_the_system'/>！");
			}else if(info=="2"){
				alert("<fmt:message key='operation_failed'/>，<fmt:message key='the_system_is_being_restored'/>！");
			}
		}
		queryBakTask();
		setInterval("queryBakTask()",10000);
	});
	
	function queryBakTask(){
		$.post("bak/queryBakTask",function(data){
			if(data[0]==1){
				document.getElementById("submitBtn").disabled=true;
				$("#submitBtn").css("background-color","gray");
			}else{
				document.getElementById("submitBtn").disabled=false;
				$("#submitBtn").css("background-color","#1a57be");
				if(data[1]==1){
					alert("<fmt:message key='data_reduction_success'/>！");
				}
			}
		},'json')
	}
	
	function submitRestore(){
		var file_bak_id=$("#file_bak_id").val();
		var data_bak_id=$("#data_bak_id").val();
		if(file_bak_id==0&&data_bak_id==0){
			alert("<fmt:message key='please_select_at_least_one_data_for_data_recovery'/>！");
			return false;
		}
		$("#myform").submit();
	}
	</script>
  </head>
  
  <body>
  <form action="bak/restore" method="post" id="myform">
  <div class="sy-right">
  <div class="shujuhf-nr">
    <div class="shujuhf-nr-bt">
      <p class="shujuhf-nr-zi"><fmt:message key='data_recovery'/></p>
    </div>
    <div class="sjhf-a">
      <p class="sjhf-bf"><fmt:message key='backup_file'/>：</p>
      <select name="file_bak_id" class="sjhf-xiala" id="file_bak_id" style="width:270px;">
      	<option value="0"><fmt:message key='please_select'/></option>
      	<c:forEach items="${sysBakList }" var="sysBak" >
      		<option value="${sysBak.bak_id }">${sysBak.bak_file_name }</option>
      	</c:forEach>
      </select>
    </div>
    <div class="sjhf-a" style="float:right;">
      <p class="sjhf-bf"><fmt:message key='backup_database'/>：</p>
      <select name="data_bak_id" class="sjhf-xiala" id="data_bak_id" style="width:270px;">
      	<option value="0"><fmt:message key='please_select'/></option>
      	<c:forEach items="${sysBakList }" var="sysBak" >
      		<option value="${sysBak.bak_id }">${sysBak.bak_data_name }</option>
      	</c:forEach>
      </select>
    </div>
    <div class="clear"></div>
    <div class="sjhf-b">
      <p class="sjhf-bf-a"><fmt:message key='recovery_instructions'/>：</p>
      <textarea class="sjhf-shuoming" id="restore_remark" name="restore_remark" ></textarea>
    </div>
  </div>
  <div class="xdd-bjgl-tj"><input type="button" value="<fmt:message key='submit'/>" class="btn-tj" disabled="disabled" id="submitBtn" onclick="submitRestore()" style="background-color: gray"/></div>
</div>
</form>
  </body>
</html>
