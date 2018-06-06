<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
	    <title>用户行为分析</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/zn_datepicker.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.datepicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>
		
		<script src="<%=basePath %>js/Highcharts/code/highcharts.js"></script>
		<script src="<%=basePath %>js/Highcharts/code/modules/data.js"></script>
		<script src="<%=basePath %>js/Highcharts/code/modules/drilldown.js"></script>
		<title>Highcharts Example</title>
	</head>
	<body>
	<form action="analysis/analyze4" method="post" id="myform">
		<input type="hidden" name="iframeAnalyzeSrc" id="iframeAnalyzeSrc" value="" /> 
		<div class="left" style="margin-bottom: 20px;width:100%;">
			<span class="xdd-bjgl-ss-span" style="margin-top: 20px;margin-left: 40px;">日期：
		        <input type="text" id="startTime" name="startTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
		        至	<input type="text" id="endTime" name="endTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
					&nbsp;&nbsp;&nbsp;榜单名称：<select name="type" id="type" class="select-ck">
						<option value="contribution">个人贡献榜</option>
						<option value="read">个人阅读榜</option>
						<option value="down">个人下载榜</option>
						<option value="collect">个人收藏榜</option>
					</select>
				<select id="mainId" class="select-ck" name="mainId" onChange="getClassId(this)" >
										<option value="0">全部</option>
         				            	<option value="1">项目流程</option>
         				            	<option value="2">指导手册</option>
         				            	<option value="3">官方文档</option>
         				            	<option value="4">案例库</option>
        		</select>
        		<select id ="classId" name="classId" class="select-ck">
				</select>
				&nbsp;&nbsp;&nbsp;用户名：<input type="text" name="userName" id="userName" value="${userName}" class="input-sr" style="width:100px;"/>
					&nbsp;&nbsp;&nbsp;<input type="button" value="查看" class="btn-cx" onclick="search();"/>
					<!-- &nbsp;&nbsp;&nbsp;<input type="button" value="导出" class="btn-cx" onclick="exportData();"/> -->
			</span>
		</div>
		 <div class="right" style="margin-bottom: 50px;width:50%;">
			<iframe src="${iframeAnalyzeSrc}" id="iframeAnalyze" frameborder="0" scrolling="no" style="width: 90%;heighy:500px;height: 500px;margin-top: 0px;margin-right: 20px;">
			</iframe>
		</div> 
<div id="container" style="width:50%; height: 480px; margin: 0 0"></div>
</form>
<script type="text/javascript">
$(function(){
	$("#startTime").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	});
});
$(function(){
	$("#endTime").datepicker({
		showHms:false,
		dateFormat:"yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		maxDate : '+3Y'
	});
});
window.onload = function(){ 
	var str = '<option value="0">全部</option>';
	$("#classId").append(str);
	$("#mainId").val("${mainId}");
	$("#startTime").val("${startTime}");
	$("#endTime").val("${endTime}");
	$("#type").val("${type}");
	var classId = "${classId}";
	var mainId = "${mainId}";
	$.ajax({
		type:"POST",
		url:"<%=basePath%>analysis/getClass",
		dataType:'JSON',
		data:{id:mainId},
		success: function(data){
			if(data.code==1){
				var str = '<option value="0">全部</option>';
				for(var i=0;i<data.obj.length;i++){
					var Cname = data.obj[i];
					if(classId==Cname.id){
						str += '<option value='+Cname.id+' selected>'+Cname.name+'</option>';
					}else{
						str += '<option value='+Cname.id+'>'+Cname.name+'</option>';
					}
				}
				$("#classId").empty();
				$("#classId").append(str);
			}else{
				alert(data.msg);
			}
		}
	  });
	  $("#classId").val("${classId}");
}
function getClassId(obj){
	var mainId = obj.value;
	 $.ajax({
		type:"POST",
		url:"<%=basePath%>analysis/getClass",
		dataType:'JSON',
		data:{id:mainId},
		success: function(data){
			if(data.code==1){
				var str = '<option value="0">全部</option>';
				for(var i=0;i<data.obj.length;i++){
					var Cname = data.obj[i];
					str += '<option value='+Cname.id+'>'+Cname.name+'</option>';
				}
				$("#classId").empty();
				$("#classId").append(str);
			}else{
				alert(data.msg);
			}
		}
	  });
}
var sumList=eval('${mainList}');
 Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        credits: {
	    	text: '',
	    	href: ''
		},
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '个数'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y}个'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}个</b><br/>'
        },
        series: [{
            name: '排行榜',
            colorByPoint: true,
            data: sumList
        }],
        drilldown: {
            series: [{
                name: 'Microsoft Internet Explorer',
                id: 'Microsoft Internet Explorer',
                data: [
                    [
                        'v11.0',
                        24.13
                    ],
                    [
                        'v8.0',
                        17.2
                    ],
                    [
                        'v9.0',
                        8.11
                    ],
                    [
                        'v10.0',
                        5.33
                    ],
                    [
                        'v6.0',
                        1.06
                    ],
                    [
                        'v7.0',
                        0.5
                    ]
                ]
            }, {
                name: 'Chrome',
                id: 'Chrome',
                data: [
                    [
                        'v40.0',
                        5
                    ],
                    [
                        'v41.0',
                        4.32
                    ],
                    [
                        'v42.0',
                        3.68
                    ],
                    [
                        'v39.0',
                        2.96
                    ],
                    [
                        'v36.0',
                        2.53
                    ],
                    [
                        'v43.0',
                        1.45
                    ],
                    [
                        'v31.0',
                        1.24
                    ],
                    [
                        'v35.0',
                        0.85
                    ],
                    [
                        'v38.0',
                        0.6
                    ],
                    [
                        'v32.0',
                        0.55
                    ],
                    [
                        'v37.0',
                        0.38
                    ],
                    [
                        'v33.0',
                        0.19
                    ],
                    [
                        'v34.0',
                        0.14
                    ],
                    [
                        'v30.0',
                        0.14
                    ]
                ]
            }, {
                name: 'Firefox',
                id: 'Firefox',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v38',
                        1.02
                    ],
                    [
                        'v31',
                        0.33
                    ],
                    [
                        'v33',
                        0.22
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            }, {
                name: 'Safari',
                id: 'Safari',
                data: [
                    [
                        'v8.0',
                        2.56
                    ],
                    [
                        'v7.1',
                        0.77
                    ],
                    [
                        'v5.1',
                        0.42
                    ],
                    [
                        'v5.0',
                        0.3
                    ],
                    [
                        'v6.1',
                        0.29
                    ],
                    [
                        'v7.0',
                        0.26
                    ],
                    [
                        'v6.2',
                        0.17
                    ]
                ]
            }, {
                name: 'Opera',
                id: 'Opera',
                data: [
                    [
                        'v12.x',
                        0.34
                    ],
                    [
                        'v28',
                        0.24
                    ],
                    [
                        'v27',
                        0.17
                    ],
                    [
                        'v29',
                        0.16
                    ]
                ]
            }]
        }
    });
function search(){
	var type = $("#type").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var mainId = $("#mainId").val();
	var classId = $("#classId").val();
	var userName = $("#userName").val();
	$("#iframeAnalyzeSrc").val("analysis/iframeAnalyze4?type="+type+"&startTime="+startTime+"&endTime="+endTime+"&mainId="+mainId+"&classId="+classId+"&userName="+encodeURI(encodeURI(userName)));
	$("#myform").submit();
}

function exportData(){
	
}
</script>
</body>
</html>