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
    <title>修改公告</title>
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
<form id="commentForm" action="message/editNoticeInfo" method="post">
<input type="hidden" name="id" value="${mynotice.id }">
<div class="sy-right">
    <div class="gongao-nr">
    <div class="gonggao-nr-bt">
      <p class="gongao-nr-zi"><fmt:message key='modification_announcement'/></p>
    </div>
    <div class="shezhi-bb">
      <div class="gonggao-c-l"><fmt:message key='customer'/><fmt:message key='role'/>：</div>
      <div class="gonggao-c-r" style="margin-left:0px; height:50px; overflow:auto; ">
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
    <input type="text" name="noticeTitle" id="noticeTitle" class="gongao-fb-bt" value="${mynotice.noticeTitle }" required />
    <textarea class="fonggao-fabu-nr" name="noticeContent" id="noticeContent"  required>${mynotice.noticeContent }</textarea>
  </div>
  <div class="xdd-bjgl-tj"><input type="button" value="<fmt:message key='release'/>" class="btn-tj" onclick="editNoticeInfo()"/></div>
</div>
</form>
  </body>
  
  <script type="text/javascript">
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
  $().ready(function() {
	   $("#commentForm").validate();
  });
  function editNoticeInfo(){
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
  		alert("<fmt:message key='please_choose_the_role_of_push'/>");
  		return;
  	}
  	
  	var content=$("#noticeContent").val();
  	if(content==""){
  		alert("<fmt:message key='please_fill_in_the_notice'/>");
  		return;
  	}
  	$("#commentForm").submit();
 	/*$.ajax({
			type:"POST",
			url:"message/editNoticeInfo",
			dataType:'JSON',
			data:$("#commentForm").serialize(),
			success: function(data){
					//修改成功
					if(data.code==0){
						alert("修改成功");
						var id=data.obj;
						window.location.href="message/editNotice?id="+id;
					}else{
						alert(data.msg);
					}
			}
	});*/
  }
  function changeValue(){
  	<c:forEach items="${send}" var="type"  varStatus="vs">
	        $("input:checkbox[value='${type}'][name='sendObject']").attr("checked",'true');
	        $("input:checkbox[value='${type}'][name='sendObject1']").attr("checked",'true');
	</c:forEach>
  }
  changeValue();
  </script>
</html>
