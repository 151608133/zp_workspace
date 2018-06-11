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
<title>角色</title>

<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/tab.js"></script>
</head>
<body>
	<div class="xdd-bjgl">
	<form action="<%=basePath %>role.html" method="post" name="roleForm" id="roleForm">
	<div class="xdd-bjgl-ss">
      <div class="left">
      <span class="xdd-bjgl-ss-span">角色：<input type="text" class="input-sr" name="roleName" id="roleName" value="${role.roleName}"/></span>
      <span class="xdd-bjgl-ss-span"><input type="button" value="查询" class="btn-cx" onclick="search();" /></span>
      <span class="xdd-bjgl-ss-span"><input type="button" value="添加" class="btn-cx" style="background:#3dcb90;" onclick="funcAddTab('添加角色','<%=basePath %>role/add')"/></span>
 	  <input hidden  name="roleId" id="myRoleId" value="${user.roleId}"/>
      
      </div>
    </div>

	<table class="tab ">
      <tr>
        <td class="tab-bt" width="5%">角色</td>
        <td class="tab-bt" width="8%">描述</td>
        <td class="tab-bt" width="5%">下属用户</td>
        <td class="tab-bt" width="7%">创建时间</td>
        <td class="tab-bt" width="8%">操作</td>
      </tr>
				
		<c:choose>
			<c:when test="${not empty roleList }">
				<c:forEach items="${roleList }" var="role">
					<tr>
						<td width="6%">${role.roleName }</td>
						<%-- <td width="5%" title="${role.roleType }">${role.roleType }</td> --%> 
						<td width="5%" title="${role.description }">${role.description }</td> 
						<td width="5%">${role.userCount }</td>
						<td width="12%"><fmt:formatDate value="${role.createDate }" pattern="yyyy-MM-dd HH:mm" /></td>
						<td width="5%">
							<input type="button" value="用户" class="btn-xg" style="background:#3c99ef" onclick="funcAddTab('用户设置','<%=basePath %>user?roleId=${role.roleId }')"/>
							<c:choose>  
							<c:when test="${role.roleId == '1' && user.roleId !='1'}">
							</c:when>  
		  					<c:otherwise>
								<input type="button" value="修改" class="btn-xg" onclick="funcAddTab('修改角色','<%=basePath %>role/edit?roleId=${role.roleId }')" />
								<input type="button" value="删除" class="btn-xg" style="background:#ff6e40;" onclick="delRole(${role.roleId });"/>
		  					</c:otherwise>  
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="xi">
					<td colspan="5">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	${role.page.pageStr}
	</form>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">		
		function delRole(roleId){
			var myRoleId = $("#myRoleId").val();
			if(myRoleId == roleId || roleId =='1'){
				alert("超级管理员及当前用户所属角色不可删除！");
				return;
			}
			if(confirm("确定要删除该记录吗？")){
				var url = "<%=basePath %>role/delete?roleId="+roleId;
				$.get(url,function(data){
					if(data=="fail"){
						alert("该角色下存在用户，不能进行删除操作");
					}
					else if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		function search(a){
			$("#roleForm").submit();
		}
		function addRole(){
			window.location.href="<%=basePath %>role/add";
		}
		function editRole(roleId){
			window.location.href="<%=basePath %>role/edit?roleId="+roleId;
		}
	</script>
</body>
</html>