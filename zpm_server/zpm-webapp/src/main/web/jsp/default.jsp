<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.zp.sys.entity.User" %>

<%

User user = (User)request.getSession().getAttribute("sessionUser");

if(null == user){
	//如果session中的值为空就跳转到登录页面
	request.getRequestDispatcher("login.jsp").forward(request, response);
}else{
	Long userid = user.getUserId();
	String roleId = user.getRoleId();
	System.out.println("---------------------roleId: " + roleId + "---" + user.getCityName());
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link href="${ctx}/css/new_file.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/nav.css">
<link rel="stylesheet" type="text/css" href="${ctx}/font/iconfont.css">
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/nav.js"></script>
</head>
<body>
<div>
	<form  action="homePage/change"  id="menuForm"  method="post" >
		<input type="hidden" name=network_name id="network_name" value="${reportWbts.network_name}" />

	<div class="sy-div-a">
		<div class="qiehuan" style="position: relative;">	
			<!-- <div class="cjsj">
       			<p>上次采集时间：2018.1.25</p>
       			<p>距下次采集还有：<span class="day-a">5</span> 天</p>
			</div> -->
			<ul class="ss">					
	  			<li id="mynav1" value="3G" onclick="switchMenustyle(1)"><a href="javascript:void(0);">3G</a></li>
		      	<li id="mynav2" value="4G" onclick="switchMenustyle(2)"><a href="javascript:void(0);">4G</a></li>
		      	<li id="mynav3" value="SBTS" onclick="switchMenustyle(3)"><a href="javascript:void(0);">SBTS</a></li>
			</ul>		
		</div>
		<div class="sjtj-sy">
			<p class="shuju-sy" id="wbts_total" name="wbts_total" value="${reportWbts.wbts_total}">${reportWbts.wbts_total}</p>
			<p class="shuju-mc">WBTS</p>
		</div>
		<div class="sjtj-sy">
			<p class="shuju-sy" id="wcel_total" name="wcel_total" value="${reportWbts.wcel_total}">${reportWbts.wcel_total}</p>
			<p class="shuju-mc">WCEL</p>
		</div>
		<div class="sjtj-sy">
			<p class="shuju-sy" id="wcel_wo_total" name="wcel_wo_total" value="${reportWbts.wcel_wo_total}">${reportWbts.wcel_wo_total}</p>
			<p class="shuju-mc">正常工作小区数量</p>
		</div>
		<div class="sjtj-sy">
			<p class="shuju-sy" id="wcel_nowo_total" name="wcel_nowo_total" value="${reportWbts.wcel_nowo_total}">${reportWbts.wcel_nowo_total}</p>
			<p class="shuju-mc">非正常工作的小区数量</p>
		</div>
	</div>
	</form>
	
	<div class="sy-div-a">       	
		<!-- <div class="bt-div-sy">
			<p class="bt-sy-wz"></p>
			<div class="gj-div-tuli">       	   	
				<p><span class="tuli-gj"style="background: #e7b700"></span>三级告警</p>
				<p><span class="tuli-gj" style="background: #ff6c00"></span>二级告警</p>
				<p><span class="tuli-gj"></span>一级告警</p>
			</div>      	   	
		</div> -->
		<!-- <div class="gaojin-tubiao"></div> -->
			<div id="myChart" style="height:100%;width:100%">
			</div>
		
	
	</div>
	
	<div class="sy-div-rnc">
		<div class="qiehuan-dy" id="4G" style="display: none">			
			<ul class="dy">					
			  	<li class="hover-dy"  id="mynav-aa1" onclick="switchMenustyle3(1)" href="javascript:void(0);"><a href="javascript:void(0);">软件</a></li>
		      	<li id="mynav-aa2" onclick="switchMenustyle3(2)" href="javascript:void(0);"><a href="javascript:void(0);">硬件</a></li>
			</ul>		
		</div>
		<div class="qiehuan-dy" id="3G">			
			<ul class="dy">					
			  	<li class="hover-dy"  id="mynav-a1" onclick="switchMenustyle1(1)" href="javascript:void(0);"><a href="javascript:void(0);">RNC单元状态</a></li>
		      	<li id="mynav-a2" onclick="switchMenustyle1(2)" href="javascript:void(0);"><a href="javascript:void(0);">RNC链路状态</a></li>
		      	<li id="mynav-a3" onclick="switchMenustyle1(3)" href="javascript:void(0);"><a href="javascript:void(0);">RNC Feature统计</a></li>
			</ul>		
		</div>
		<div class="qiehuan-dy-ss" id="UnitType">			
			<ul class="tubiao-qh">					
				<li  id="mynav-ss1" onclick="switchMenustyle2(1)" href="javascript:void(0);"><a href="javascript:void(0);">WO-EX</a></li>
				<li class="tubiao-qh" id="mynav-ss2" onclick="switchMenustyle2(2)" href="javascript:void(0);"><a href="javascript:void(0);">SP-EX</a></li>
				<li id="mynav-ss3" onclick="switchMenustyle2(3)" href="javascript:void(0);"><a href="javascript:void(0);">其他</a></li>
			</ul>		
		</div>
		<div id="myChart2" style="height:70%;width:100%;">
		</div>
	</div>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
    <script type="text/javascript">
    function switchMenustyle(num) {
		var tabid=document.getElementById("mynav" + num).innerText;
		if(num == 1){
			setReport('getReportUnit','SP-EX');
    		document.getElementById("3G").style.display="";
        	document.getElementById("4G").style.display="none";
        	document.getElementById("UnitType").style.display="";
		}else if(num == 2) {
			setSoftWare(tabid);
			document.getElementById("4G").style.display="";
        	document.getElementById("3G").style.display="none";
        	document.getElementById("UnitType").style.display="none";
        	document.getElementById("mynav-aa1").className= "hover-dy";
        	document.getElementById("mynav-aa2").className= " ";
		}else{
			setSoftWare(tabid);
			document.getElementById("4G").style.display="";
        	document.getElementById("3G").style.display="none";
        	document.getElementById("UnitType").style.display="none";
        	document.getElementById("mynav-aa1").className= "hover-dy";
        	document.getElementById("mynav-aa2").className= " ";
		}
		var url = getRealPath()+"/homePage/change?network_name="+tabid;
		 $.ajax({  
            url:url,  
            type:"get",  
            success:function(data){
            	var reportWbts = data.obj.reportWbts;
            	$("#network_name").val(reportWbts.network_name);
            	$("#wbts_total")[0].innerText=reportWbts.wbts_total;
            	$("#wcel_total")[0].innerText=reportWbts.wcel_total;
            	$("#wcel_wo_total")[0].innerText=reportWbts.wcel_wo_total;
            	$("#wcel_nowo_total")[0].innerText=reportWbts.wcel_nowo_total;
            	load();
            	setEcharts();
            	//alert(reportWbts.wbts_total);
            }  
        }); 
		//window.location.href = url;
        //setEcharts();
	}
	function getRealPath(){
		  //获取当前网址，如： http://localhost:8083/myproj/view/my.jsp
		  var curWwwPath=window.document.location.href;
		  //获取主机地址之后的目录，如： myproj/view/my.jsp
		  var pathName=window.document.location.pathname;
		  var pos=curWwwPath.indexOf(pathName);
		  //获取主机地址，如： http://localhost:8083
		  var localhostPaht=curWwwPath.substring(0,pos);
		  //获取带"/"的项目名，如：/myproj
		  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		  //得到了 http://localhost:8083/myproj
		  var realPath=localhostPaht+projectName;
		 return realPath;
	}
	
	load();
	function load(){
		var network_name=$("#network_name").val();
		var num=network_name=='3G'?'1':(network_name=='4G'?'2':(network_name=='SBTS'?'3':0));
		for(var id = 1; id <= 3; id++) {
			if (id == num) {
				document.getElementById("mynav" + id).className= "hover-qh";
			}else{
				document.getElementById("mynav" + id).className= "";
			}
		}
	}
    setEcharts();
    function setEcharts(){
    	var network_name=$("#network_name").val();
    	var url = getRealPath()+"/homePage/getEcharts?network_name="+network_name;
        var myChart = echarts.init(document.getElementById('myChart'));
		 $.ajax({  
           url:url,  
           type:"get",  
           success:function(data){
           		var city = data.obj.city;
           		var cityId = data.obj.cityId-1;
           		if(city.length == 1){
           			var one = [data.obj.one[cityId]];
           			var two = new Array(data.obj.two[cityId]);
               		var three = [data.obj.three[cityId]];
           		}else{
	           		var one = data.obj.one;
	           		var two = data.obj.two;
	           		var three = data.obj.three;
           		}
		        var option = {
		        	    title : {
		        	        text: '全网告警数量',
		        	    },
		        	    tooltip : {
		        	        trigger: 'axis'
		        	    },
		        	    legend: {
		        	        data:['一级告警','二级告警','三级告警']
		        	    },
		        	    calculable : true,
		        	    xAxis : [
		        	        {
		        	            type : 'category',
		        	            data : city
		        	        }
		        	    ],
		        	    yAxis : [
		        	        {
		        	            type : 'value'
		        	        }
		        	    ],
		        	    grid : {
	        	        	x:40,
	        	          	y:40,
	        	          	x2:40,
	        	          	y2:40
	        	          },
		        	    series : [
		        	        {
		        	            name:'一级告警',
		        	            type:'bar',
		        	            data:one,
		        	        },
		        	        {
		        	            name:'二级告警',
		        	            type:'bar',
		        	            data:two,
		        	        },
		        	        {
		        	            name:'三级告警',
		        	            type:'bar',
		        	            data:three,
		        	        }
		        	    ]
		        	};
		        myChart.setOption(option);
           }  
	       });
    }
    setReport('getReportUnit','SP-EX');
    function setReport(method,state){
    	if(method == 'getReportLincence'){
    		setReportLincence();
    	}else{
	    	var url = getRealPath()+"/homePage/"+method;
	    	if(state != null && state !=""){
	    		url = url+"?state="+state;
	    	}
	        var myChart = echarts.init(document.getElementById('myChart2'));
			 $.ajax({  
	           url:url,  
	           type:"get",  
	           success:function(data){
	           		var name = data.obj.name;
	           		var value = data.obj.value;
	           		/* if(city.length == 1){
	           			var one = [data.obj.one[cityId]];
	           			var two = new Array(data.obj.two[cityId]);
	               		var three = [data.obj.three[cityId]];
	           		}else{
		           		var one = data.obj.one;
		           		var two = data.obj.two;
		           		var three = data.obj.three;
	           		} */
			        var option = {
			        	    title : {
			        	        text: '',
			        	    },
			        	    tooltip : {
			        	        trigger: 'axis'
			        	    },
			        	    
			        	    calculable : true,
			        	    xAxis : [
			        	        {
			        	            type : 'category',
			        	            data : name
			        	        }
			        	    ],
			        	    yAxis : [
			        	        {
			        	            type : 'value',
			        	            data : value
			        	        }
			        	    ],
			        	    grid : {
				        	        	x:40,
				        	          	y:20,
				        	          	x2:40,
				        	          	y2:20
				        	          },
			        	    series : [
		        	            	  {
		  		        	            
		  		        	            type:'bar',
		  		        	            data:value,
		  		        	        }, 
	
			        	    ] 
			        	};
			        myChart.setOption(option);
	           }  
	       });
    	}
    }
    
    function setReportLincence(){
    	var url = getRealPath()+"/homePage/getReportLincence";
        var myChart = echarts.init(document.getElementById('myChart2'));
		 $.ajax({  
           url:url,  
           type:"get",  
           success:function(data){
           		var rnc = data.obj.rnc;
           		var featureCode = data.obj.featureCode;
           		var all = data.obj.all;
           		var series=[];
           		 for (var i = 0;i < all.length;i++) {
   					series.push({
   	           	           name: featureCode[i],
   	           	           type: 'line',
   	           	           data: all[i]
   	           	       });
       		} 
           		 option = {
           			    tooltip : {
           			        trigger: 'axis'
           			    },
           			 	legend: {  
	           	            data:featureCode  
	           	        },
           			    calculable : true,
           			    xAxis : [
           			        {
           			            type : 'category',
           			            boundaryGap : false,
           			            data : rnc
           			        }
           			    ],
           			    yAxis : [
           			        {
           			            type : 'value'
           			        }
           			    ],
	           			 grid : {
		        	        	x:40,
		        	          	y:20,
		        	          	x2:40,
		        	          	y2:20
		        	          },
           			    series : series
           			};
		        myChart.setOption(option);
           }  
       }); 
    }
    
     function setHardWare(network_name){
    	var url = getRealPath()+"/homePage/";
   		url = ('SBTS'==network_name)? url+'getHardwareSBTS' : url+'getHardware4G';
        var myChart = echarts.init(document.getElementById('myChart2'));
		 $.ajax({  
           url:url,  
           type:"get",  
           success:function(data){
           		var name = data.obj.name;
           		var value = data.obj.value;
		        var option = {
		        	    title : {
		        	        text: '',
		        	    },
		        	    tooltip : {
		        	        trigger: 'axis'
		        	    },
		        	    
		        	    calculable : true,
		        	    xAxis : [
		        	        {
		        	            type : 'category',
		        	            data : name
		        	        }
		        	    ],
		        	    yAxis : [
		        	        {
		        	            type : 'value',
		        	            data : value
		        	        }
		        	    ],
		        	    grid : {
	        	        	x:40,
	        	          	y:20,
	        	          	x2:40,
	        	          	y2:20
	        	          },
		        	    series : [
	        	            	  {
	  		        	            type:'bar',
	  		        	            data:value,
	  		        	        }, 

		        	    ] 
		        	};
		        myChart.setOption(option);
           }  
       });
    }
     
    function setSoftWare(network_name){
    	var url = getRealPath()+"/homePage/";
   		url = ('4G'==network_name)? url+'getSoftware4G' : url+'getSoftwareSBTS';
        var myChart = echarts.init(document.getElementById('myChart2'));
		 $.ajax({  
           url:url,  
           type:"get",  
           success:function(data){
           		var name = data.obj.name;
           		var value = data.obj.value;
		        var option = {
		        	    title : {
		        	        text: '',
		        	    },
		        	    tooltip : {
		        	        trigger: 'axis'
		        	    },
		        	    
		        	    calculable : true,
		        	    xAxis : [
		        	        {
		        	            type : 'value',
		        	            data : value
		        	        }
		        	    ],
		        	    yAxis : [
		        	        {
		        	            type : 'category',
		        	            data : name
		        	        }
		        	    ],
		        	    grid : {
	        	        	x:'20%',
	        	          	y:20,
	        	          	x2:40,
	        	          	y2:20
	        	          },
		        	    series : [
	        	            	  {
	  		        	            name:name,
	  		        	            type:'bar',
	  		        	            data:value,
	  		        	        }, 

		        	    ] 
		        	};
		        myChart.setOption(option);
           }  
       });
    }
	function switchMenustyle1(num) {
		document.getElementById("UnitType").style.display="none";
		var method = "getReportUnit";
		var state = "";
		if (num == 1){
			document.getElementById("UnitType").style.display="";
			document.getElementById("mynav-ss2").className= "tubiao-qh";
			document.getElementById("mynav-ss1").className= "";
			document.getElementById("mynav-ss3").className= "";
			method = "getReportUnit";
			state = "SP-EX";
		}else if(num == 2){
			method = "getReportLink";
		}
		else if(num == 3){
			method = "getReportLincence";
		}
		for(var id = 1; id <= 3; id++) {
			if (id == num) {
				document.getElementById("mynav-a" + id).className= "hover-dy";
				setReport(method,state);
			}else{
				document.getElementById("mynav-a" + id).className = "";
			}
		}
	} 
	
	function switchMenustyle2(num) {
    	var state=document.getElementById("mynav-ss" + num).innerText;
		state = ('其他'==state)?'3':state;
    	for(var id = 1; id <= 3; id++) {                 
			if (id == num) {                                       
				document.getElementById("mynav-ss" + id).className= "tubiao-qh"; 
				setReport('getReportUnit',state);
			}else{                                   
				document.getElementById("mynav-ss" + id).className = "";                 
			}
		}         
	} 
	
	function switchMenustyle3(num) {
		var network_name=$("#network_name").val();
		for(var id = 1; id <= 2; id++) {                 
			if (id == num) {                                       
				document.getElementById("mynav-aa" + id).className= "hover-dy";
				if(num == '1'){
					setSoftWare(network_name);
				}else{
					setHardWare(network_name);
				}
			}else{                                   
				document.getElementById("mynav-aa" + id).className = "";                 
			}
		}         
	} 
</script>

	<div class="clear"></div>
</div>
</body>
</html>