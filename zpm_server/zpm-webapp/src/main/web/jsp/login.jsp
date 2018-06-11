<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>登录</title>
<link href="css/wk/new_file.css" rel="stylesheet" type="text/css" />
<link href="css/wk/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	//回车登陆
	document.onkeydown = function (event){
		if (event.keyCode==13) //回车键的键值为13
		sub();
	};
});
$(document).ready(function(){
	var errInfo = "${errInfo}";
	if(errInfo != ''){
		$("#nameerr").show();
		alert(errInfo);
	}
	//验证码
	$("#codeImg").bind("click",changeCode);
	$("#password").bind("blur",pwdDisplayControl);
	changeCode();
});
function pwdDisplayControl(){
    if($("#password").val() == ""){
    	$("#password").hide();
    	$("#password1").show();
    }
}
function changeCode(){
	$("#codeImg").attr("src","code.html?t="+genTimestamp());
}
function genTimestamp(){
	var time = new Date();
	return time.getTime();
}
function MM_over(mmObj){
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "block";
	mSubObj.style.backgroundColor = "rgb";
}
function MM_out(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";
	
}
function WD_over(wdObj){
	var wdCk = wdObj.getElementsByTagName("div")[0];
	wdCk.style.display = "block";
}
function WD_out(wdObj){
	var wdCk = wdObj.getElementsByTagName("div")[0];
	wdCk.style.display = "none";
}
function check(){
	var loginName = $("#loginname").val();
	var password = $("#password").val();
	var yzm = $("#yzm").val();
	if(loginName == '' || loginName == '请输入用户名'){
		$("#nameerr").append("请输入用户名");
		return false;
	}else if(password == '' || password == '请输入密码'){
		$("#nameerr").append("请输入密码");
		return false;
	}else if(yzm == '' || yzm == '请输入验证码'){
		$("#nameerr").append("请输入验证码");
		return false;
	}
}

function changetopwd(obj){
	if(obj.type =="text"){
		obj.type = "password";
	}
	if(obj.type == "password" && obj.value == ""){
		obj.type = "text";
	}
}
</script>
</head>

<body>
<form action="login" method="post" name="loginForm" id="loginForm">
<div class="login-top">
    <img style="float:left;margin-right: 8px;" src="img/logo-login.png" />
    <div class="logo-n">湖南联通无线智能监控平台</div>
    <div class="clear"></div>
</div>
<div class="denglu">
  <div class="denglu-c" style="background:url(img/login-bg.png);">
    <div class="denglu-center-r">
      <div class="denglu-kuang-top" style="position:relative;">
        <p style="width:50%; position:absolute; bottom:-2px; float:left; border-bottom:2px #124191 solid;">用户登录</p>
        
      </div>
      
      <div class="login-a-div">
       <p>账号:</p>
       <input class="shuruk-a" id="loginname" name="loginname" type="text" value="请输入你的账号" onfocus="if(value==defaultValue){value='';this.style.color='#616573'}" onblur="if(!value){value=defaultValue;this.style.color='#d1d1d1'}" style="color:#d1d1d1;">
       <input type="password" style="display: none;" value=""/>
      </div>
      <div class="login-a-div">
       <p>密码:</p>
         <input class="shuruk-a" id="password" name="password" type="text" value="请输入你的密码" onfocus="changetopwd(this);if(value==defaultValue){value='';this.style.color='#616573'}" onblur="changetopwd(this);if(!value){value=defaultValue;this.style.color='#d1d1d1'}" style="color:#d1d1d1;">
         <!-- <input class="shuruk-a" id="password" name="password" type="password" value="请输入你的密码" onfocus="changepwd();if(value==defaultValue){value='';this.style.color='#616573'}" onblur="if(!value){value=defaultValue;this.style.color='#e8e8e8'}" style="color:#d1d1d1;"> -->
      </div>
      
      <div class="login-a-div">
       <p>验证码:</p>
         <input id="yzm" name="code" style="float:left;width:155px;color:#d1d1d1; margin-right:5px;" class="shuruk-a" type="text" value="请输入右侧验证码" onfocus="if(value==defaultValue){value='';this.style.color='#616573'}" onblur="if(!value){value=defaultValue;this.style.color='#d1d1d1'}" >

        <img id="codeImg" style="margin-top:5px; width:104px;" alt="验证码" title="验证码" src="" />

      </div>
      
      <div class="zdongdl">
      
      <Div style="float:left; display: none;"><input type="checkbox" name="bike" id="chkRememberPwd">记住密码</Div>
      <!-- <Div style="float:right"><input type="checkbox" name="bike">自动登陆</Div> --> 
        
        </div>
        <input type="button" value="登录" class="denglu-a" onclick="sub()">
    </div>
  </div>
</div>
<p class="banquan"> Copyright © 2018 Nokia Corporation, All Right Reserved, Yinda Support</p>
</form>
<script>
function sub(){
	check();
	//setPwdAndChk();
	//加密
    var password = encode64($("#password").val());  
    $("#password").val(password);
	//首先在这里判断是否密码框中的内容是test666&数据库中初始密码是test666
	var a = $("#loginname").val();
	//$("#pwdcheck222").val(a);
	$.ajax({
		type:"POST",
		url:"loginIsBegin",
		dataType:'JSON',
		data:$("#loginForm").serialize(),
		success: function(data){
			if(data.code==1){
				alert(data.msg);
				$("#password5").val(password);
				$("#confirm_2").attr("style","display:block");
			}else if(data.code==2){
				alert(data.msg);
			}else{
				$("#loginForm").submit();
			}
		}
	});
}
//base64加密开始  
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
</body>
</html>
