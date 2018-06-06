<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>备件处理/管理-操作失败</title>
<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/tishi.css"rel="stylesheet" type="text/css" />

</head>

<body>

<div class="sy-right">
  <div class="tishi">
    <p class="tishi-a"><img src="<%=basePath %>images/shibai.png" /><span style="margin-left:10px;"><fmt:message key='operation_failed'/>！</span></p>
    <p class="tishi-y"><fmt:message key='cause_of_failure'/></p>
  <p class="tishi-b">1、<fmt:message key='the_current_network_system_is_not_stable'/>，<fmt:message key='suggest_you_try_again_later'/></p>
  <p class="tishi-b">2、<fmt:message key='current_account_exception'/>，<fmt:message key='cause_this_operation_cannot_be_performed'/>，<fmt:message key='this_situation_need_to_call_contact_customer_service'/></p>
  </div> 
</div>
</body>
</html>
