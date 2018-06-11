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
    <title>公告信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/tishi.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
  </head>
  
  <body>
    <div class="grxx-nr">
    <div class="grxiaoxi-ss">
      <div class="xiaoxi-top"><span>公告信息</span>
        <div class="xiaoti-tu"></div>
      </div>
      
       <c:choose>
			<c:when test="${not empty listNotice}">
		        <c:forEach items="${listNotice }" var="notice" varStatus="vs">
		        	 <div class="gongao" style="padding-top:11px;height:100%; overflow:hidden;width:100%;line-height:25px;border-bottom:1px solid #e6e6e6;margin-bottom:-1px;">
				        <div class="gongao-bt">
				          <p class="gonggaobt-ss"><b>${notice.noticeTitle }</b></p>
				           <a href="javascript:noticpar(${notice.id })" class="gonggaobt-b"><fmt:message key='details'/></a></div>
				        <div class="gongao-xq">
				          <p class="gonggaobt-xq-ss"><b>${notice.noticeContent }</b></p>
				          <a class="gonggaobt-xq-b"><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd HH:mm" /></a>
				         </div>
				      </div>
		       </c:forEach>
        	</c:when>
			<c:otherwise>
				<div class="gongao-liebiao-a">
			          <div class="gongao-bt">
			            <p class="gonggaobt-a"><fmt:message key='latest_news'/></p>
			            <a class="gonggaobt-b"></a> </div>
			          <div class="gongao-xq">
			            <p class="gonggaobt-xq-a"></p>
			            <a class="gonggaobt-xq-b"></a> </div>
			         </div>
			</c:otherwise>
		</c:choose>
		 <div class="fy clear" >
       		${notice.page.pageStr }
  		</div>
    </div>
   </div>
  
  </body>
  
  <script type="text/javascript">
  		function noticpar(data){
      	var info = "<fmt:message key='personal_message_details'/>";
      	funcAddTab(info,"message/notice?id="+data);
      }
      	
  </script>
</html>
