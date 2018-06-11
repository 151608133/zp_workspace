<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色-添加</title>

<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/MyUtil.js"></script>
<style type="text/css">
.bianan{
	background: red;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${empty role.roleId}">
			<c:set var="roleId" value="0"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="roleId" value="${role.roleId}"></c:set>
		</c:otherwise>
	</c:choose>
	<form action="addRole" id="menuForm" method="post">
		<input type="hidden" name="roleId" id="roleId" value="${roleId}" />
		<input type="hidden" name="flag" id="flag" value="${flag}" />
		<div class="sy-right">
			<div class="xdd-bjgl">
				<div class="yh2">
					<div class="yh-bt">
						<span><c:choose>
								<c:when test="${not empty role.roleId}">
	              					角色修改
	       						</c:when>
								<c:otherwise>
	              					添加角色
	       						</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="yh-tj mt10">
						<form action="editUser1" id="menuForm" method="post">
							<input type="hidden" name="oldRoleName" id="oldRoleName" value="${role.roleName}" />
							<input type="hidden" name="type" id="type" value="${type}" />
							<div class="xdd-bjgl-nr-nr-time">
								<span class="text-r">
									<span class="red">*</span>角色名称：
								</span>
								<input type="text" name="roleName" id="roleName" value="${role.roleName}" class="input-bjh" onblur="checkRole()">
							</div>
							<div class="xdd-bjgl-nr-nr-time">
								<span class="text-r">
									<span class="red">*</span>角色描述：
								</span>
								<input class="input-bjh" type="text" name="description" id="description" value="${role.description}" />
							</div>
					</div>
					<p class="lh32 f14">
						<span class="red">*</span>权限：
						<span><input type="checkbox" id="all" />全选</span>
					</p>
					<!-- type=0添加,type=1修改 -->
					<c:if test="${type==0}">
						<table class="tabr" style="width: 100%; overflow: auto;">
							<c:forEach items="${orderList}" var="order">
								<tr>
									<td width="10%" class="tabr-bt" 
										rowspan="${fn:length(order.subChildren)}">${order.funcName}
									</td>
									<c:if test="${empty order.subChildren}">
										<td width="30%">
											<input type="checkbox" id="order" name="test" value="${order.funcId}_${order.funcId}"/>&nbsp;${order.funcName}
										</td>
										<td width="60%"></td>
										</tr>
									</c:if>
									<c:forEach items="${order.subChildren}" var="sub">
										<td width="30%">
											<input type="checkbox" id="order" name="test" value="${sub.qtip}"
					  						/>&nbsp;${sub.funcName}
					  					</td>
										<td width="60%">
											<c:if test="${not empty sub.children}">
												<span class="xdd-bjgl-ss-span">
												<input type="checkbox" onclick="all1(this);" />全选 
											</c:if> 
											<c:forEach items="${sub.children}" var="chi">
												<input type="checkbox" name="test" value="${chi.qtip}"
													<c:forEach items="${checkedList }" var="checked">
					  								</c:forEach> 
					  							/>&nbsp;${chi.funcName}	
				    						</c:forEach></span>
				    					</td>
								</tr>
									</c:forEach>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${type==1}">
						<table class="tabr" style="width: 100%; overflow: auto;">
							<c:forEach items="${orderList}" var="order">
								<tr>
									<td width="8%" class="tabr-bt"
										rowspan="${fn:length(order.subChildren)}">${order.funcName}
									</td>
									<c:if test="${empty order.subChildren}">
										<td>
											<input type="checkbox" id="order" name="test" value="${order.funcId}_${order.funcId}"
												<c:forEach items="${cantCheckedList }" var="checked1">
					  							</c:forEach>
												<c:forEach items="${checkedList }" var="checked">
					  							<c:if test="'${order.funcId}_${order.funcId}' == sub.qtip"> checked </c:if>
				  								</c:forEach> 
				  							/>&nbsp;${order.funcName}
										</td></tr>
									</c:if>
									<c:forEach items="${order.subChildren}" var="sub">
										<td width="10%">
											<input type="checkbox" id="order" name="test" value="${sub.qtip}"
												<c:forEach items="${cantCheckedList }" var="checked1">
					  							</c:forEach>
												<c:forEach items="${checkedList }" var="checked">
					  							<c:if test="${checked.funcName == sub.qtip}"> checked </c:if>
				  								</c:forEach> 
				  							/>&nbsp;${sub.funcName}
				  						</td>
										<td width="80%">
											<c:if test="${not empty sub.children}">
												<span class="xdd-bjgl-ss-span">
												<input type="checkbox" onclick="all1(this);" />全选
											</c:if>
											<c:forEach items="${sub.children}" var="chi">
												<input type="checkbox" name="test" value="${chi.qtip}"
													<c:forEach items="${cantCheckedList }" var="checked1">
				  									</c:forEach>
													<c:forEach items="${checkedList }" var="checked">
				  									<c:if test="${checked.funcName == chi.qtip}"> checked </c:if>
				  									</c:forEach> 
				  								/>&nbsp;${chi.funcName}	
		    								</c:forEach></span>
				    					</td>
								</tr>
									</c:forEach>
							</c:forEach>
						</table>
					</c:if>
					<div class="xdd-bjgl-tj">
						<input type="button" value="提交" class="btn-tj" onclick="sub(this)" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
	function sub(a){
		var checkusername = /^[\u4e00-\u9fa5A-Za-z0-9-_]{3,20}$/;
		var roleName = $.trim($("#roleName").val());
	  	if(null==roleName || ''==roleName){
	  		alert("请输入角色名称!");
	  		return;
	  	}
	  	if(!checkusername.test($("#roleName").val())){
	        alert("角色名称由3-20位中英文、数字、下划线、减号组成");
	        return;
	    }
		var description = $.trim($("#description").val());
	  	if(null==description || ''==description){
	  		alert("请输入描述!");
	  		return;
	  	}
	  	if($("#description").val().length>20){
	  		alert("描述不能超过20个字!");
	  		return;
	  	}
	  	if($("input:checked").length==0){	
			alert("请选择权限!");
			return;						
		}
	  	
	  	$("#menuForm").submit();
	  	//setTimeout(freshTab(),2000);
		//var form = document.forms['menuForm'];
		/* form.action = 'addRole';
		$(a).attr("disabled", true);
		form.submit(); */
	}
	load();
	function load(){
		if($("#flag").val()!=null && $("#flag").val()!=''){
			if(confirm("操作成功!")){
		  		window.parent.afreshTab("角色设置","<%=basePath %>role");
				window.parent.closeTab("添加角色");
			}
		}
	}
	
  	function freshTab(){
  		window.parent.closeTab("添加角色");
  		window.parent.afreshTab("角色设置","<%=basePath %>role");
  	}

	$("#all").click(function(){   
	    if(this.checked){   
	    	$("input:checkbox[id='order']").attr("checked",true);  
	    }else{   
	    	$("input:checkbox[id='order']").attr("checked",false);
	    }   
	});
	function all1(obj){
		var flag = obj.checked;
		if(flag){	
			$(obj).parent().children("input:not(:first):not(:checked)").click();
		}else{
			$(obj).parent().children("input:not(:first):checked").click();	
		}
	}
	function checkRole(){
	  	var roleName=$("#roleName").val();
		if($("#type").val()==0){
			checkRoleName(roleName);
	  	}else{
			var oldName=$("#oldRoleName").val();
			if(roleName!=oldName){
				checkRoleName(roleName);
			}
	  	}
		
	}
	function checkRoleName(roleName){
		$.ajax({
			type:"POST",
			url:"checkRole",
			dataType:'JSON',
			data:"roleName="+roleName,
			success: function(data){
				if(data.code!=0){
					alert(data.msg);
					$("#roleName").val("");
				}
			}
		});
	}
 
</script>
</body>
</html>