<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.zp.sys.entity.User" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("sessionUser");
if(null == user){
	//如果session中的值为空就跳转到登录页面
	request.getRequestDispatcher("login.jsp").forward(request, response);
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>湖南联通无线智能监控</title>

<link href="<%=path %>/css/new_file.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/nav.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/font/iconfont.css">
<link href="<%=path %>/css/easyui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>

<script>
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

function exit(){
	if(confirm("你确定退出吗?")){
		window.location.href="<%=basePath%>logout"; 
	}
}
function toIndex(){
	window.location.reload(); 
}
</script>
</head>
<body>
	<!--top-->
	
	<div class="head" style="background-color: white;" id="hh">
		<div class="head-l"  style="cursor:pointer;" onclick="toIndex()">
			<img style="float:left;margin-right: 8px;margin-top: 15px" src="img/logo.png" />
			<span style=" margin-left:10px;"> </span>
			<span class="bt-head">湖南联通无线智能监控平台</span>
		</div>
		<div class="head-r" >
			<span style="text-align: center;">
				<img src="img/tx.png" class="touxian" >
				<p class="hy"><%=user.getRealName()%>，欢迎您！</p>
				<div class="tuichu-div" onclick="exit()">
					<img src="img/tuic.png">
					<p>退出</p>
				</div>
			</span>
		</div>
		<div class="cont" style="display:none;">
			6<br /> 3<br /> 3
		</div>
	</div>
	
	<div class="nav" height:100%;overflow-x:hidden;overflow-y:scroll;" id="navdiv">
		<script language="javascript">          
		function switchMenustyle(num) {              
			for(var id = 1; id <= 5; id++) {                 
				if (id == num) {                                       
					document.getElementById("mynav" + id).className= "meaus-hover";                 
				}else{                                   
					document.getElementById("mynav" + id).className = "";                 
				}
			}         
		}          
		</script>
    	
        
		<ul style="margin-top: 61px;" id="nav">
			<c:forEach items="${menuList}" var="menu">
				<li href="javascript:;" class="nav-item">
					<c:choose>
					<c:when test="${not empty menu.url and menu.funcName != '首页'}">
						<a href="#Menu=${menu.qtip}" onclick="afreshTab('${menu.funcName}','${menu.url}');">
						<img src="${menu.image}" /><span>${menu.funcName}</span><i class="IC-ar-dwn pull-right"></i>
						</a>
					</c:when>
					<c:when test="${not empty menu.url and menu.funcName == '首页'}">
						<a href="#Menu=${menu.qtip}" >
						<img src="${menu.image}" /><span>${menu.funcName}</span><i class="IC-ar-dwn pull-right"></i>
						</a>
					</c:when>
					<c:otherwise>
						<a href="#Menu=${menu.qtip}" onclick="DoMenu('${menu.qtip}')">
						<img src="${menu.image}" /><span>${menu.funcName}</span><i class="IC-ar-dwn pull-right"></i>
						</a>
					</c:otherwise>
					</c:choose>
					<ul id="${menu.qtip}">
						<c:forEach items="${menu.children}" var="sub">
							<c:choose>
								<c:when test="${not empty sub.url}">
									<li>
										<a href="javascript:void(0);" onclick="afreshTab('${sub.funcName}','${sub.url}');">&nbsp;&nbsp;&nbsp;&nbsp;${sub.funcName}</a>
									</li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:void(0);" target="mainFrame">&nbsp;&nbsp;&nbsp;&nbsp;${sub.funcName}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
        </ul>
    </div>
	<script language="javascript">          
		function switchMenustyle2(num) {              
			for(var id = 1; id <= 3; id++) {                 
				if (id == num) {                                       
					document.getElementById("mynav-ss" + id).className= "tubiao-qh";                 
				}else{                                   
					document.getElementById("mynav-ss" + id).className = "";                 
				}
			}         
		}          
	</script>
	
<!-- 中部右边 start-->
<div class="g-mn1c" id="rigth_c">
	<div id="right" class="sy-right" style="border-left: 1px solid #dfdfdf;float: right;">
		<!--tab 选项卡-->
		<div class="easyui-tabs" fit="true" border="false" id="maintabs">
		</div>
		<div id="mm" class="easyui-menu" style="width:150px;display:none;">
			<div id="mm-tabclose"><fmt:message key='关闭'/></div>
			<div id="mm-tabcloseall"><fmt:message key='全部关闭'/></div>
			<div id="mm-tabcloseother"><fmt:message key='除此以外关闭'/></div>
			<div class="menu-sep"></div>
			<div id="mm-tabcloseright"><fmt:message key='右边全部关闭'/></div>
			<div id="mm-tabcloseleft"><fmt:message key='左边全部关闭'/></div>
		</div>
		<!--tab 选项卡 end -->
	</div>
</div>
	<!-- 中部右边 end-->
	<script type=text/javascript>
	var e12 = document.getElementById("right");
	var e10 = document.getElementById("navdiv");
	var o = document.getElementById("hh");
	var h = o.offsetHeight; //高度
	var w = e10.offsetWidth;
	
	$(window).resize(function() { 
	    var heightwe = parent.document.body.clientHeight - h;
	    var widthwe = parent.document.body.clientWidth - w;
	    e12.style.height=heightwe+"px";
	    e12.style.width=widthwe+"px";
 	})
 	var widthwe = parent.document.body.clientWidth - w;
	var heightwe = parent.document.body.clientHeight - h;
	e12.style.height=heightwe+"px";
	e12.style.width=widthwe+"px";
	
	function aaa(MenuID){
		DoMenu(MenuID)
	}
	   
	//初始化
	$(function() {
		addHomePage(" 首  页 ",'default.html?timestamp='+(new Date()).valueOf());
		//addHomePage(" 首  页 ","homePage?network_name='3G'");
		//initTopMenu();
		//initLeftMenu();
		//setTab('one',1,6);
	});
	
	var LastLeftID = "";
	function menuFix() {
		var obj = document.getElementById("nav").getElementsByTagName("li");
		
		for (var i=0; i<obj.length; i++) {
		obj[i].onmouseover=function() {
		   this.className+=(this.className.length>0? " ": "") + "sfhover";
		}
		obj[i].onMouseDown=function() {
		   this.className+=(this.className.length>0? " ": "") + "sfhover";
		}
		obj[i].onMouseUp=function() {
		   this.className+=(this.className.length>0? " ": "") + "sfhover";
		}
		obj[i].onmouseout=function() {
		   this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
		}
		}
	}
	
	function DoMenu(emid)
	{
		var obj = document.getElementById(emid); 
		obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
		/*if((LastLeftID!="")&&(emid!=LastLeftID)) //关闭上一个Menu
		{
		document.getElementById(LastLeftID).className = "collapsed";
		}*/
		LastLeftID = emid;
	}
	
	function GetMenuID()
	{
		var MenuID="";
		var _paramStr = new String(window.location.href);
		var _sharpPos = _paramStr.indexOf("#");
		
		if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
		{
		_paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
		}
		else
		{
		_paramStr = "";
		}
		
		if (_paramStr.length > 0)
		{
		var _paramArr = _paramStr.split("&");
		if (_paramArr.length>0)
		{
		   var _paramKeyVal = _paramArr[0].split("=");
		   if (_paramKeyVal.length>0)
		   {
		    MenuID = _paramKeyVal[1];
		   }
		}
		/*
		if (_paramArr.length>0)
		{
		   var _arr = new Array(_paramArr.length);
		}
		
		//取所有#后面的，菜单只需用到Menu
		//for (var i = 0; i < _paramArr.length; i++)
		{
		   var _paramKeyVal = _paramArr[i].split('=');
		   
		   if (_paramKeyVal.length>0)
		   {
		    _arr[_paramKeyVal[0]] = _paramKeyVal[1];
		   } 
		}
		*/
		}
		
		if(MenuID != "")
		{
		DoMenu(MenuID)
		}
	}
	GetMenuID(); //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
	menuFix();
</script>
	<script type="text/javascript">
(function(){

	var time = null;
	var list = $("#navlist");
	var box = $("#navbox");
	var lista = list.find("a");
	
	for(var i=0,j=lista.length;i<j;i++){
		if(lista[i].className == "now"){
			var olda = i;
		}
	}
	
	var box_show = function(hei){
		box.stop().animate({
			height:hei,
			opacity:1
		},400);
	}
	
	var box_hide = function(){
		box.stop().animate({
			height:0,
			opacity:0
		},400);
	}
	
	lista.hover(function(){
		lista.removeClass("now");
		$(this).addClass("now");
		clearTimeout(time);
		var index = list.find("a").index($(this));
		box.find(".cont").hide().eq(index).show();
		var _height = box.find(".cont").eq(index).height()+54;
		box_show(_height)
	},function(){
		time = setTimeout(function(){	
			box.find(".cont").hide();
			box_hide();
		},50);
		lista.removeClass("now");
		lista.eq(olda).addClass("now");
	});
	
	box.find(".cont").hover(function(){
		var _index = box.find(".cont").index($(this));
		lista.removeClass("now");
		lista.eq(_index).addClass("now");
		clearTimeout(time);
		$(this).show();
		var _height = $(this).height()+54;
		box_show(_height);
	},function(){
		time = setTimeout(function(){		
			$(this).hide();
			box_hide();
		},50);
		lista.removeClass("now");
		lista.eq(olda).addClass("now");
	});

})();
//changeStoreType();
//拼接全部
var str="<Div class='sf'><div class='shenf'><a href='javascript:setStoreName(-1)'><p>${allStore}</p></a></div><div class='shenf-a'><a href='#'><li></li></a></div></Div>";

<c:if test="${not empty allStore}">
	$(".sf:first").before(str);
</c:if>

function changepwd(){
	afreshTab('<fmt:message key='modify_password'/>','changePwd');
}
</script>
<form action="setStoreName" method="post" id="myform">
	<input type="hidden" name="storeId" id="storeId" />
</form>
<form action="setLanguage" method="post" id="languageform">
	<input type="hidden" name="language" id="language" />
</form>
</body>
</html>