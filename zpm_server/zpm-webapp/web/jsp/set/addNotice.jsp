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
    <div class="shezhi-bb">
      <div class="gonggao-c-l"><fmt:message key='customer'/><fmt:message key='role'/>：</div>
      <div class="gonggao-c-r" style="margin-left:0px; height:50px; overflow:auto; ">
      	<!--
      	<c:forEach items="${typeList }" var="type" varStatus="vs">
	      	<input type="checkbox" name="sendObject" class="sendObject" style="margin-left:5px;" value="${type.typeId }"/>
	        <span class="kaiqi">${type.typeName }</span>
      	</c:forEach>
      	-->
      	<input type="checkbox" id="sendObjectAll" class="sendObjectAll" style="margin-left:5px;" />
	        <span class="kaiqi"> <fmt:message key='select'/></span>
      	<c:forEach items="${roleList }" var="role" varStatus="vs">
      		<c:set var="theString" value="${role.roleName }"/>
	      	<c:if test="${fn:contains(theString, '客户')}">
	      	<input type="checkbox" name="sendObject" class="sendObject" style="margin-left:5px;" value="${role.roleId }"/>
	        <span class="kaiqi">${role.roleName }</span>
	        </c:if>
      	</c:forEach>
      </div>
    </div>
     <div class="shezhi-bb">
      <div class="gonggao-c-l"><fmt:message key='other'/><fmt:message key='role'/>：</div>
      <div class="gonggao-c-r" style="margin-left:0px; height:100px; overflow:auto; ">
      	<input type="checkbox" id="sendObjectAll1" class="sendObjectAll" style="margin-left:5px;" />
	        <span class="kaiqi"> <fmt:message key='select'/></span>
      	<c:forEach items="${roleList }" var="role" varStatus="vs">
      		<c:set var="theString" value="${role.roleName }"/>
	      	<c:if test="${!fn:contains(theString, '客户')}">
	      	<input type="checkbox" name="sendObject" class="sendObject1" style="margin-left:5px;" value="${role.roleId }"/>
	        <span class="kaiqi">${role.roleName }</span>
	        </c:if>
      	</c:forEach>
      </div>
    </div>
    <input type="text" name="noticeTitle" id="noticeTitle" class="gongao-fb-bt" value="<fmt:message key='please_fill_in_the_title'/>，<fmt:message key='can_enter_up_to_50_words'/>"  onfocus="if(this.value=='<fmt:message key='please_fill_in_the_title'/>，<fmt:message key='can_enter_up_to_50_words'/>') {this.value='';}" onblur="if(this.value=='') {this.value='<fmt:message key='please_fill_in_the_title'/>，<fmt:message key='can_enter_up_to_50_words'/>';}" required />
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
  
   $("#sendObjectAll").click(function () {//全选  
	   if(this.checked){
	   	  $(".sendObject:checkbox").attr("checked", true);  
	   }else{
	      $(".sendObject:checkbox").attr("checked", false); 
	   }
       
   });  
      $("#sendObjectAll1").click(function () {//全选  
	   if(this.checked){
	   	  $(".sendObject1:checkbox").attr("checked", true);  
	   }else{
	      $(".sendObject1:checkbox").attr("checked", false); 
	   }
       
   }); 
  function addNoticeInfo(){
  	var type=$(".sendObject");
  	var type1=$(".sendObject1");
  	var s="";
  	for(var i=0;i<type.length;i++){
  		if(type[i].checked){
  			s+=type[i].value+",";
  		}
  	}
  	for(var i=0;i<type1.length;i++){
  		if(type1[i].checked){
  			s+=type1[i].value+",";
  		}
  	}
  	if(s==""){
  		alert("<fmt:message key='please_select_the_type_of_user_to_push'/>");
  		return;
  	}
  	
  	var title=$("#noticeTitle").val();
  	if(title=="<fmt:message key='please_fill_in_the_title'/>，<fmt:message key='can_enter_up_to_50_words'/>"){
  		alert("<fmt:message key='please_fill_in_the_title'/>");
  		return;
  	}
  	var content=$("#noticeContent").val();
  	if(content=="<fmt:message key='please_fill_in_the_notice'/>"){
  		alert("<fmt:message key='please_fill_in_the_notice'/>");
  		return;
  	}
 	$.ajax({
			type:"POST",
			url:"message/addNoticeInfo",
			dataType:'JSON',
			data:$("#commentForm").serialize(),
			success: function(data){
					//添加成功
					if(data.code==0){
						alert("<fmt:message key='successful_operation'/>");
						window.location.href="message/addNotice";
					}else{
						alert(data.msg);
					}
			}
	});
  }
  </script>
</html>
