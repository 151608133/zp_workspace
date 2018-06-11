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
<title>添加用户</title>
<link href="<%=request.getContextPath()%>/css/wxlive/hygl.css"  type="text/css" rel="stylesheet" />
<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/zn_datepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/ui.datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jquery-ui.css" />
</head>
<body>
	<form  action="editUser1"  id="menuForm"  method="post" >	
	<div class="sy-right">
  <div class="xdd-bjgl">
    <div class="yh">
      <div class="yh-bt mt60"><span>修改用户信息</span></div>
      <input type="hidden" name="userId" value="${thisuser.userId }" />
      <input type="hidden" name="flag" id="flag" value="${flag}" />
      <input type="hidden" name="oldUserName" id="oldUserName" value="${thisuser.userName }" />
      <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>账号：</span><input type="text"/ value="${thisuser.userName}" name="userName" id="userName" class="input-bjh" onblur="checkUserName()"></div>
      <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>密码：</span><input type="password"/ value="${thisuser.password}" name="password" id="password" class="input-bjh" ></div>     
	  <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>确认密码：</span><input type="password" name="password1" id="password1" class="input-bjh" ></div>     
      <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>手机号码：</span><input type="text"/ value="${thisuser.contactPhone}" name="contactPhone" id="contactPhone" class="input-bjh"></div>
      <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>姓名：</span><input type="text"/ value="${thisuser.realName}" name="realName" id="realName" class="input-bjh"></div>
      <div class="tc-nr-wz-hmd"><span class="yh-js">邮箱：</span><input type="text"/ value="${thisuser.email}" name="email" id="email" class="input-bjh"></div>
      <div class="tc-nr-wz-hmd">
      		<span class="yh-js"><span class="red">*</span>角色：</span>
			<%-- <div class="tc-nr-wz-hmd-role">
			<c:forEach items="${rolelist}" var="rol" varStatus="vs">
			<span><input type="checkbox" id="lsitroles" name="lsitroles" value="${rol.roleId}"/>&nbsp;${rol.roleName }</span>
			</c:forEach>
			</div> --%>
			<select name="roleId" id="roleId" class="select-lxr">
				<option value="">请选择</option>
				<c:forEach items="${rolelist }" var="rol" varStatus="vs">
					<option value="${rol.roleId}">${rol.roleName}</option>
				</c:forEach>
			</select>
   	  </div>
  	  <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>区域：</span>
		<select name="cityId" id="cityId" class="select-lxr">
			<option value="">请选择</option>
			<c:forEach items="${citylist }" var="city" varStatus="vs">
				<option value="${city.id}">${city.name }</option>
			</c:forEach>
		</select>
	 </div>
           <input type="hidden" name="userId" value="${thisuser.userId}">
           <div class="xdd-bjgl-tj mt30"><input type="button" value="提交" class="btn-tj" onclick="sub(this)"/></div>
    </div>
  </div>
</div>
</form>

<script type="text/javascript">

	function sub(a){
	
		var checkphoneno = /^1([3-5]|[7,8])[0-9]{9}$/; //检验手机号 
	    var checkusername = /^[A-Za-z0-9-_]{3,20}$/; //只能英文，数字，下划线，减号
	   	var checkpassword = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)[0-9A-Za-z]{6,12}$/;
	    
	   	var userName = $.trim($("#userName").val());
		if(null==userName || ''==userName){
	  		alert("请输入账号!");
	  		return;
	  	}
		if(!checkusername.test($("#userName").val())){
	        alert("账号由3-20位英文、数字、下划线、减号组成");
	        return;
	    }
	  	if(null==$("#password").val()||''==$("#password").val()){
	  		alert("请输入密码!");
	  		return;
	  	}
	  	if(!checkpassword.test($("#password").val())){
	        alert("密码必须由6-12位大写字母，小写字母，数字组成");
	        return;
	    }
	  	if(null==$("#password1").val()||''==$("#password1").val()){
	   		alert("请再次输入密码!");
	   		return;
	   	}
	  	if($("#password").val()!=$("#password1").val()){
			alert("两次输入的密码不相同，请确认后再次输入!");
			$("#password1").val("");
			return;
		}
	  	if(null==$("#contactPhone").val()||''==$("#contactPhone").val()){
	   		alert("请输入手机号码!");
	   		return;
	   	}
	  	if(!checkphoneno.test($("#contactPhone").val())){
	        alert("手机号格式错误");
	        return;
	    }
	  	if(null==$("#realName").val()||''==$("#realName").val()){
	   		alert("请输入姓名!");
	   		return;
	   	}	
	  	if(null==$("#roleId").val()||''==$("#roleId").val()){
	  		alert("请选择角色!");
	  		return;
	  	}
	  	if(null==$("#cityId").val()||''==$("#cityId").val()){
	  		alert("请选择区域!");
	  		return;
	  	}
	  	var userName=$("#userName").val();
		var oldName=$("#oldUserName").val();
		$("#menuForm").submit();
	  	//setTimeout(freshTab(),1000);

	}
	load();
	function load(){
		$("#password1").val($("#password").val());
		if($("#flag").val()!=null && $("#flag").val()!=''){
			if(confirm("操作成功!")){
				window.parent.afreshTab("用户设置","<%=basePath %>user");
				window.parent.closeTab("修改用户");
			}
		}
	}
	function checkRole(){               
        var lsitroles = document.getElementsByName("lsitroles");               
        var flag = false ;               
        for(var i=0;i<lsitroles.length;i++){
            if(lsitroles[i].checked){
                flag = true ;
                break ;
            }
        }
        if(!flag){
            alert("请选择角色！");
            return ;
        }
    }
	function checkUserName(){
		var userName=$("#userName").val();
		var oldName=$("#oldUserName").val();
		if(userName!=oldName){
	  	$.ajax({
			type:"POST",
			url:"checkUser",
			dataType:'JSON',
			data:"userName="+userName,
			success: function(data){
				if(data.code==1){
					alert(data.msg);
					$("#userName").val("");
				}
			}
		});}
	}
	function changestatus(){
		$("#cityId").val("${thisuser.cityId}");
		$("#roleId").val("${allRolListOfMine.roleId}");
	}
	changestatus();
</script>
</body>
</html>