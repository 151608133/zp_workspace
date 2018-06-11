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
<title>添加消息</title>
<link href="<%=request.getContextPath()%>/css/hygl.css"  type="text/css" rel="stylesheet" />
<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/zn_datepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/ui.datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jquery-ui.css" />
</head>
<body>
	<form  action="insertnotice"  id="menuForm"  method="post" >	
	<div class="sy-right">
  <div class="xdd-bjgl">
    <div class="yh">
      <div class="yh-bt mt30"><span>添加新信息</span></div>
      <input type="hidden" name="realName" id="realName" value="">
      <div class="tc-nr-wz-hmd"><span class="yh-js"><span class="red">*</span>消息标题：</span>
      <input type="text"/ name="noticeTitle" id="noticeTitle" class="input-bjh">
      </div>
       <div class="tc-nr-wz-hmd"><span class="yh-js">消息简介：</span>
      <input type="text"/ name="smallTitle" id="smallTitle" class="input-bjh">
      </div>
      <div class="tc-nr-wz-hmd">
      <span class="yh-js"><span class="red">*</span>消息内容：</span>
        <textarea rows="4" cols="20" id="noticeContent" name="noticeContent" class="miaoshu jbxx-r" style="height: 120px;"></textarea>        
     </div>
     
     <div class="tc-nr-wz-hmd" ><span class="yh-js"><span class="red">*</span>消息类型：</span>
      <select class="select-lxr" name="sendObject" id="sendObject" onchange="changeType(this.value)">
      <option value="X">请选择</option>
      <option value="A">全体</option>
      <option value="P">个人</option>
      </select>
      </div> 
 
      <div class="tc-nr-wz-hmd" style="display: none" id="train"><span class="yh-js">接收人：</span>
      <select class="select-lxr" name="buserId" id="buserId" >
      <c:forEach items="${userlist}" var="userlist">
      <option value="${userlist.userId}">${userlist.realName}</option>    
      </c:forEach>
      </select>
      </div>  
      
           <div class="xdd-bjgl-tj mt30"><input type="button" value="提交" class="btn-tj" onclick="sub(this)"/></div>
    </div>
  </div>
</div>
</form>

<script type="text/javascript">
function changeType(data){
if(data=="P"){
	$("#train").css("display","block");
      }
if(data=="A"){
	$("#train").css("display","none");
      }
if(data=="X"){
	$("#train").css("display","none");
      }
}
function sub(a){
	
	if(null==$("#noticeTitle").val()||''==$("#noticeTitle").val()){
  		alert("标题不能为空!");
  		return;
  	}
  	 if(null==$("#noticeContent").val()||''==$("#noticeContent").val()){
  		alert("内容不能为空!");
  		return;
  	}
    if($("#sendObject").val()=='X'){
       alert("请选择接收人");
       return;
    }
   
  	
  
  	var form = document.forms['menuForm'];

	form.action = 'insertnotice';
	
	form.submit();
}

</script>
</body>
</html>