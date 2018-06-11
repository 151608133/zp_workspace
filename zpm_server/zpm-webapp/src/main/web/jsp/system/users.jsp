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
<title>用户管理</title>
</head>
<%--
<script type="text/javascript">
 $(function(){
	/* $("#date1").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	}); */
	//选中省 联动仓库
		$("#cityName").change(function(){
			var city = $("#cityName").val();
			$.post("<%=basePath %>getStore",{city:city},function(data){
				$("#storeName").html("");
				if(data!=null){
					for(var i=0;i<data.length;i++){
						var html='<span class="ddfx-ck-nr-ck"><input type="checkbox" name="cityNames['+i+']" value="'+data[i]+'" />&nbsp;'+data[i]+'</span>';
						$("#storeName").append(html);
					}
				}
			},"json");
		});
}); 
$(function(){
	$("#date2").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	});
});
</script>
--%>
<body>
	<form method="post" name="userForm" id="userForm" action="<%=basePath %>user">
	<div class="xdd-bjgl">
	<div class="xdd-bjgl-ss2">
	<div class="left">
		<span class="xdd-bjgl-ss-span">
		用户名：<input type="text" name="userName" id="userName" class="input-sr"/>
		<input hidden  name="userId" id="myUserId" value="${myuser.userId}"/>
		</span>
		<span class="xdd-bjgl-ss-span">
		手机号：<input type="text" name="contactPhone" id="contactPhone"  class="input-sr"/>
		</span>
		<span class="xdd-bjgl-ss-span">
			角色：
			<select name="roleId" id="roleId" class="select-ck">
				<option value="">请选择</option>
				<c:forEach items="${rolelist }" var="rl" varStatus="vs">
					<option value="${rl.roleId }">${rl.roleName }</option>
				</c:forEach>
			</select>
		</span>
		<span class="xdd-bjgl-ss-span">
		状态：<select name="stateCd" id="stateCd" class="select-ck">
			<option value="00F">请选择</option>
			<option value="00A">正常</option>
			<option value="00X">禁用</option>
		</select>
		</span>
		
		<span class="xdd-bjgl-ss-span">
		<input type="button" value="查询" class="btn-cx" onclick="search()"/>
		</span>
		<span class="xdd-bjgl-ss-span">
		</span>
		<span class="xdd-bjgl-ss-span">
		<input type="button" value="添加" class="btn-cx" style="background:#3dcb90;float:right;margin-bottom: 5px;" onclick="funcAddTab('添加用户','<%=basePath %>user/add')" />
		</span>
		</div>
	</div>
	<table class="tab" style="text-align: center;">
		<tr >
			<th class="tab-bt" width="5%">序号</th>
			<th class="tab-bt" width="6%">用户名</th>
			<th class="tab-bt" width="8%">手机号</th>
			<th class="tab-bt" width="5%">姓名</th>
			<th class="tab-bt" width="10%">邮箱</th>
			<th class="tab-bt" width="11%">加入时间</th>
			<th class="tab-bt" width="5%">状态</th>
			<th class="tab-bt" width="8%">角色</th>
			<th class="tab-bt" width="11%">操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty userList}">
				<c:forEach items="${userList}" var="user" varStatus="vs">
				<tr id="p${user.userId}">
				<td>${vs.index+1}</td>
				<td>${user.userName}</td>
				<td>${user.contactPhone}</td>
				<td>${user.realName}</td>
				<td>${user.email }</td>
				<td><fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:choose>  
   						<c:when test="${user.stateCd=='00A'}">正常</c:when>  
   						<c:otherwise><span style="color:red;">该用户已禁用</span></c:otherwise>  
					</c:choose>  
				</td> 		
				<td>${user.roleName }</td>
				<c:choose>  
					<c:when test="${myuser.userId != '1' && user.userId == '1'}">
						<td></td>
					</c:when>  
  					<c:otherwise>
						<td>
						<input type="button"  class="btn-xg" value="修改" onclick="update(${user.userId });"/>
						            <c:if test="${user.stateCd=='00A'}">
									<input id="jin" type="button" value="禁用" class="btn-xg" style="background:#ff6e40;" onclick="delCust(${user.userId });">
									<input id="rec" type="hidden" value="恢复" class="btn-xg" style="background:#3dcb90;" onclick="recovery(${user.userId});">
									</c:if>
									<c:if test="${user.stateCd=='00X'}">
									<input id="rec" type="button" value="恢复" class="btn-xg" style="background:#3dcb90;" onclick="recovery(${user.userId});">
									<input id="jin" type="hidden" value="禁用" class="btn-xg" style="background:#ff6e40;" onclick="delCust(${user.userId});">
									</c:if>	
						</td>
  					</c:otherwise>  
				</c:choose>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="9">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
		${user.page.pageStr}
	</div>
	
	</form>
	
	<script type="text/javascript">
	function changestatus(){
	 	var stateCd = "${stateCd}";
	 	$(".select-ck").val("${stateCd}"); 
		$("#roleId").val("${roleId}");
		$("#stateCd").val("${stateCd}");
		var checkBoxs=$("#storeName").find("input:checkbox");
	}
	changestatus();
	function search(){		
		$("#userForm").submit();
	}
	
	function update(data){
		var myUserId = $("#myUserId").val();
		if(data == '1' && myUserId !='1'){
			alert("非超级管理员不允许修改！");
			return;
		}
		funcAddTab('修改用户','<%=basePath %>user/edit?userId=${user.userId}');
	}
	
	function delCust(data){
		var myUserId = $("#myUserId").val();
		if(myUserId == data || data == '1'){
			alert("超级管理员及当前用户不可被禁用！");
			return;
		}
        var j = $("#p"+data).find("input[id='jin']");
        var r = $("#p"+data).find("input[id='rec']");
		if(confirm("你确定要禁用此用户吗？")){
 		
    	 	$.ajax({
            type:"POST",
            url:'user/jinUser',
            dataType:'JSON',
            data:{"userId":data},
            success:function(data){
                    j.prop('type','hidden');
                     r.prop('type','button'); 
                     $("#userForm").submit();
                   }
        	 	});
		}
	}
	function recovery(data){
		var j = $("#p"+data).find("input[id='jin']");
        var r = $("#p"+data).find("input[id='rec']");
		if(confirm("你确定要恢复此用户吗？")){        	 	
    	 	$.ajax({
            type:"POST",
            url:'user/RecoveryUser',
            dataType:'JSON',
            data:{"userId":data},
            success:function(data){
                     j.prop('type','button');
                     r.prop('type','hidden');
                     $("#userForm").submit();
                   }
        	 	});
		   }
   }
</script>
</body>
</html>