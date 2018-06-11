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
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>意见反馈</title>
	
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.datepicker.js"></script>
  </head>
  <script type="text/javascript">
	$(function(){
		$("#startDate").datepicker({
			showHms:false,
			dateFormat:"yyyy-mm-dd",
			changeMonth: true,
			changeYear: true,
			showButtonPanel:true,
			maxDate : '+3Y'
		});
	});
	$(function(){
		$("#endDate").datepicker({
			showHms:false,
			dateFormat:"yyyy-mm-dd",
			changeMonth: true,
			changeYear: true,
			showButtonPanel:true,
			maxDate : '+3Y'
		});
	});
</script>
  <body>
<div class="sy-right">
  <div class="xdd-bjgl">
  <form action="feedback.html" method="get" name="feedbackDetailForm" id="feedbackDetailForm">
    <div class="xdd-bjgl-ss">
      <div class="left">
     <!--  <fmt:message key="common" />   -->
        <span class="xdd-bjgl-ss-span">
        <fmt:message key='date'/>：<input type="text" name="startDate" id="startDate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
        <fmt:message key='to'/><input type="text" name="endDate" id="endDate" class="input-time" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;"/>
        </span>
        <span class="xdd-bjgl-ss-span">
        <fmt:message key='customer_name'/>：<input type="text" class="input-sr" name="userName" id="userName" /></span>
        <span class="xdd-bjgl-ss-span">
        <fmt:message key='content'/>：<input type="text" class="input-sr" name="problem" id="problem" /></span>
        
        <span class="xdd-bjgl-ss-span">
        <input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="search()"/></span>
        <span class="xdd-bjgl-ss-span">
      </div> 
    </div><!-- xdd-bjgl-ss -->
    <table class="tab ">
    <c:choose>
		<c:when test="${not empty feedbackDetailList }">
	    <c:forEach items="${feedbackDetailList}" var="feedbackDetail" varStatus="vs">
	      <div class="fk-liebiao">
	      <div class="fk-liebiao-l"> <img src="../headimages/${feedbackDetail.head_image}" width="50" height="50"/> </div>
	      <div class="fk-liebiao-c">
	        <p> <!--<a href="#"></a> --> ${feedbackDetail.userName}
	        <span class="fk-shijian"> 
	        	<fmt:formatDate value="${feedbackDetail.createDate }" pattern="yyyy-MM-dd HH:mm:ss" />
	        </span>
	        </p>
	        <p><span class="fk-shijian" style="margin-left: 0px;">${feedbackDetail.back_type }</span>&nbsp;&nbsp; <!--  -->
	        	<c:if test="${feedbackDetail.priority==1 }">
	        		<a href="javacript:void(0);" style="color:red" onclick="getDetail(${feedbackDetail.id})"> ${feedbackDetail.title}</a> 
	        	</c:if>
	        	<c:if test="${feedbackDetail.priority==2 }">
	        		<a href="javacript:void(0);" style="color:green;" onclick="getDetail(${feedbackDetail.id})"> ${feedbackDetail.title}</a> 
	        	</c:if>
	        </p>
	      </div>
	      <div class="fk-liebiao-r"> <a href="javacript:void(0);" onclick="deleteFeed(${feedbackDetail.id})"><img src="images/fk-shanchu.png" /></a> </div>
	    </div>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr class="xi">
				<td colspan="5"><fmt:message key='no_relevant_data'/></td>
			</tr>
		</c:otherwise>
	</c:choose>
	</table>
  	${feedbackDetail.page.pageStr}
	</form>
</div><!-- xdd-bjgl -->
</div>
  </body>
  <script>
 	function search(){
        $("#feedbackDetailForm").submit();
	}
	
	function getDetail(id){
		var info = "<fmt:message key='feedback_information'/>";
		funcAddTab(info,"feedback/getDetail?id="+id);
	}
	
	function deleteFeed(id){
		if(confirm("<fmt:message key='are_you_sure_you_want_to_delete_the_record'/>？")){
			var url = "<%=basePath %>feedback/deleteFeed?id="+id;
			$.get(url,function(data){
				if(data=="success"){
					document.location.reload();
				}
			});
		}
	}
  </script>
</html>
