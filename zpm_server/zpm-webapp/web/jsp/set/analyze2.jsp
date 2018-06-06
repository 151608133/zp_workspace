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
  $(function(){
		$("#startTime").datepicker({
			showHms:false,
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			showButtonPanel:true,
			maxDate : '+3Y'
		});
	});
	$(function(){
		$("#endTime").datepicker({
			showHms:false,
			dateFormat:"yy-mm-dd",
			changeMonth: true,
			changeYear: true,
			showButtonPanel:true,
			maxDate : '+3Y'
		});
	});
window.onload = function(){ 
	var str = '<option value="0">全部</option>';
	$("#classId").append(str);
	$("#mainId").val("${Hd.mainId}");
	$("#startTime").val("${Hd.startTime}");
	$("#endTime").val("${Hd.endTime}");
	$("#type").val("${Hd.type}");
	var classId = "${Hd.classId}";
	var mainId = "${Hd.mainId}";
	$.ajax({
		type:"POST",
		url:"<%=basePath%>analysis/getClass",
		dataType:'JSON',
		data:{id:mainId},
		success: function(data){
			if(data.code==1){
				var str = '<option value="0">全部</option>';
				for(var i=0;i<data.obj.length;i++){
					var Cname = data.obj[i];
					if(classId==Cname.id){
						str += '<option value='+Cname.id+' selected>'+Cname.name+'</option>';
					}else{
						str += '<option value='+Cname.id+'>'+Cname.name+'</option>';
					}
				}
				$("#classId").empty();
				$("#classId").append(str);
			}else{
				alert(data.msg);
			}
		}
	  });
	  $("#classId").val("${Hd.classId}");
} 
function findSysBak(){
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	if(startTime!=''&&endTime!=''&&endTime<startTime){
		alert("<fmt:message key='please_select_the_correct_time_range'/>！");
		return false;
	}
	$("#myform").submit();
}
function getClassId(obj){
	var mainId = obj.value;
	 $.ajax({
		type:"POST",
		url:"<%=basePath%>analysis/getClass",
		dataType:'JSON',
		data:{id:mainId},
		success: function(data){
			if(data.code==1){
				var str = '<option value="0">全部</option>';
				for(var i=0;i<data.obj.length;i++){
					var Cname = data.obj[i];
					str += '<option value='+Cname.id+'>'+Cname.name+'</option>';
				}
				$("#classId").empty();
				$("#classId").append(str);
			}else{
				alert(data.msg);
			}
		}
	  });
}
</script>
  <body>
<form action="analysis/analyze2" method="post" id="myform">
 <div class="xdd-bjgl">
    <div class="xdd-bjgl-ss">
      <div class="left">
        <span class="xdd-bjgl-ss-span">日期：
		        <input type="text" id="startTime" name="startTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
		        至	<input type="text" id="endTime" name="endTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
		        </span>
       	<span class="xdd-bjgl-ss-span">
       		 分析维度：<select name="type" id="type" class="select-ck">
						<option value="d">阅读量</option>
						<option value="f">下载量</option>
						<option value="h">收藏量</option>
					</select>
		</span>
		<span class="xdd-bjgl-ss-span">
		<select id="mainId" class="select-ck" name="mainId" onChange="getClassId(this)" >
										<option value="0">全部</option>
         				            	<option value="1">项目流程</option>
         				            	<option value="2">指导手册</option>
         				            	<option value="3">官方文档</option>
         				            	<option value="4">案例库</option>
        </select>
        </span>
        <span class="xdd-bjgl-ss-span">
        <select id ="classId" name="classId" class="select-ck">
		</select>
		</span>
		
        <span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="findSysBak()"/></span>
      </div>
    </div>
    <table class="tab">
      <tr>
        <td class="tab-bt" width="20%">文件名称</td>
        <td class="tab-bt" width="8%">上传人</td>
        <td class="tab-bt" width="8%">文件分类</td>
        <td class="tab-bt" width="8%">总阅读</td>
        <td class="tab-bt" width="8%">总收藏</td>
        <td class="tab-bt" width="8%">总下载</td>
        <td class="tab-bt" width="8%">合计</td>
        <td class="tab-bt" width="8%">当前阅读</td>
        <td class="tab-bt" width="8%">当前收藏</td>
        <td class="tab-bt" width="8%">当前下载</td>
        <td class="tab-bt" width="8%">合计</td>
      </tr>
      <c:forEach items="${hotDocument }" var="hd" >
	      <tr>
	        <td title="${hd.file_name}">${hd.file_name}</td>
	        <td title="${hd.userName}">${hd.userName}</td>
	        <td title="${hd.typeName}">${hd.typeName}</td>
	        <td title="${hd.read_total}">${hd.read_total}</td>
	        <td title="${hd.collect_total}">${hd.collect_total}</td>
	        <td title="${hd.down_total}">${hd.down_total}</td>
	        <td title="${hd.total}">${hd.total}</td>
	        <td title="${hd.ckNumber}">${hd.ckNumber}</td>
	        <td title="${hd.scNumber}">${hd.scNumber}</td>
	        <td title="${hd.xzNumber}">${hd.xzNumber}</td>
	        <td title="${hd.nowTotal}">${hd.nowTotal}</td>
	      </tr>
      </c:forEach>
      <c:if test="${empty hotDocument}">
      	<tr class="xi">
			<td colspan="10"><fmt:message key='no_relevant_data'/></td>
		</tr>
      </c:if>
    </table>
    ${Hd.page.pageStr}
 </div>
 </form>
</body>
</html>
