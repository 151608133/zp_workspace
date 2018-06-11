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
    
    <title>密码修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/MyUtil.js"></script>
  </head>
  
  <body>
<div class="xdd-bjgl">
    <div class="yh">
      <div class="yh-bt"><span><fmt:message key='modify_password'/></span></div>
      <form action="changepwdInfo" id="commentForm" method="post">
     <fieldset>
      <div class="tc-nr-wz-hmd" style="width:520px;"><span class="text-r"><fmt:message key='original_password'/>：</span><input type="password" class="input-bjh" name="oldpassword" id="oldpassword"  required></div>
      <div class="tc-nr-wz-hmd" style="width:520px;"><span class="text-r"><fmt:message key='new_password'/>：</span><input type="password" class="input-bjh" name="password" id="password" required></div>
      <div class="tc-nr-wz-hmd" style="width:520px;"><span class="text-r"><fmt:message key='confirm_password'/>：</span><input type="password" class="input-bjh" name="confirm_password" id="confirm_password" required></div>
      <div class="xdd-bjgl-tj"><input type="button" value="<fmt:message key='confirm_modification'/>" class="btn-tj" onclick="changePwd()"/></div>
     </fieldset>
      </form>
    </div>
  </div>
  </body>
   <script type="text/javascript">
   		var msg = "${msg}";
	  	$().ready(function() {
	    	$("#commentForm").validate();
	    	if(msg != ''){
				alert(msg);
		    }
		});
  		function changePwd(){
  			var password=$("#password").val();
			var confirm_password=$("#confirm_password").val();
			//基础校验
			var easypwd=/^[0-9]+$/;
			if(easypwd.test(password)){
				alert("密码不能为纯数字");
				return;
			}
			if(password != confirm_password){
				alert("确认密码与密码不一致");
				return;
			}
			//密码校验
			var regUpper = /[A-Z]/;
            var regLower = /[a-z]/;
            var regShuzi = /[0-9]/;
            var regTeshu = /[~!@#$%^&*?]/;
            var complex = 0;
            if (regLower.test(password)) {
                ++complex;
            }
            if (regUpper.test(password)) {
                ++complex;
            }
            if (regShuzi.test(password)) {
                ++complex;
            }
            if (regTeshu.test(password)) {
                ++complex;
            }
            if (complex < 3 || password.length < 8) {
                alert("必须包含大写字母,小写字母,数字,特殊字符(~!@#$%^&*?)中的三种及以上,长度至少8位");
                return;
            }
  			$("#oldpassword").val(encode64($("#oldpassword").val()));
  			var pwd=$("#oldpassword").val();
	  		$.ajax({
					type:"POST",
					url:"checkPwd",
					dataType:'JSON',
					data:"password="+pwd,
					success: function(data){
						if(data.code==1){
							alert(data.msg);
							$("#oldpassword").val("");
						}else{
							$("#password").val(encode64(password));
						    $("#confirm_password").val(encode64(confirm_password));
				  			$("#commentForm").submit();
						}
					}
	  		});
  			
  		}

  	// base64加密开始  
	    var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"  
	            + "wxyz0123456789+/" + "=";  
	      
	    function encode64(input) {  
	  
	        var output = "";  
	        var chr1, chr2, chr3 = "";  
	        var enc1, enc2, enc3, enc4 = "";  
	        var i = 0;  
	        do {  
	            chr1 = input.charCodeAt(i++);  
	            chr2 = input.charCodeAt(i++);  
	            chr3 = input.charCodeAt(i++);  
	            enc1 = chr1 >> 2;  
	            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
	            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
	            enc4 = chr3 & 63;  
	            if (isNaN(chr2)) {  
	                enc3 = enc4 = 64;  
	            } else if (isNaN(chr3)) {  
	                enc4 = 64;  
	            }  
	            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)  
	                    + keyStr.charAt(enc3) + keyStr.charAt(enc4);  
	            chr1 = chr2 = chr3 = "";  
	            enc1 = enc2 = enc3 = enc4 = "";  
	        } while (i < input.length);  
	  
	        return output;  
	    }  
	    // base64加密结束 
  </script>
</html>
