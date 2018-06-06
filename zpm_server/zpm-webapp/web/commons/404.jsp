<%@ include file="/commons/taglibs.jsp"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>没找到页面</title>
    <link href="<%=basePath%>css/404ym.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/dingdan.css"rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/tishi.css"rel="stylesheet" type="text/css" />
  </head>

  <body>
  
<div class="ljsb">
<div class="sy-right">
  <div class="tishi" style="width: 100%">
    <p class="tishi-a"><img src="<%=basePath %>images/shibai.png" /><span style="margin-left:10px;">操作失败！</span></p>
    <p class="tishi-y">失败原因</p>
  <p class="tishi-b">1、当前用户登陆失效,请重新登陆再访问页面</p>
  <p class="tishi-b">2、当前网络系统不稳定造成的，建议您稍后重新尝试</p>
  <p class="tishi-b">3、当前所使用的帐号异常，导致无法进行此操作，此情况需要致电联系客服</p>
  </div> 
</div>
  
  </body>
  
</html>
