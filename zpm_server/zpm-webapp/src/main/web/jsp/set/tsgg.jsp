<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>发布公告</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
  </head>
  
  <body>
<form id="commentForm" action="message/addNoticeInfo" method="post">
<div class="sy-right">
    <div class="gongao-nr">
    <div class="gonggao-nr-bt">
      <p class="gongao-nr-zi"><fmt:message key='release_a_new_announcement'/></p>
    </div>
 
 
    <textarea class="fonggao-fabu-nr" name="noticeContent" id="noticeContent" required><fmt:message key='please_fill_in_the_notice'/></textarea>
  </div>
  <div class="xdd-bjgl-tj"><input type="button" value="<fmt:message key='release'/>" class="btn-tj" onclick="addNoticeInfo()"/></div>
</div>
</form>
  </body>
  
  <script type="text/javascript">
  $().ready(function() {
	   $("#commentForm").validate();
  });
 /* $("#checkall").click( 
  function(){ 
    if(this.checked){ 
        $("input[name='checkname']").attr('checked', true)
    }else{ 
        $("input[name='checkname']").attr('checked', false)
    } 
  }*/ 
  
 
  function addNoticeInfo(){
 
  	var content=$("#noticeContent").val();
  	if(content=="<fmt:message key='please_fill_in_the_notice'/>"){
  		alert("<fmt:message key='please_fill_in_the_notice'/>");
  		return;
  	}
 	$.ajax({
			type:"POST",
			url:"message/addtsgg",
			dataType:'JSON',
			data:$("#commentForm").serialize(),
			success: function(data){
					//添加成功
					if(data.code==0){
						alert("<fmt:message key='successful_operation'/>");
						window.location.href="message/tsgg";
					}else{
						alert(data.msg);
					}
			}
	});
  }
  </script>
</html>
