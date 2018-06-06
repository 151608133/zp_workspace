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
	<form action="analysis/analyze" method="post" id="myform">
		<input type="hidden" name="iframeAnalyzeSrc" id="iframeAnalyzeSrc" value="" /> 
		<div class="left" style="margin-bottom: 20px;width:100%;">
			<span class="xdd-bjgl-ss-span" style="margin-top: 20px;margin-left: 40px;">日期：
		        <input type="text" id="startTime" name="startTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
		        至	<input type="text" id="endTime" name="endTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
					&nbsp;&nbsp;&nbsp;分析维度：<select name="type" id="type" class="select-ck">
						<option value="read">阅读量</option>
						<option value="down">下载量</option>
						<option value="collect">收藏量</option>
					</select>
					&nbsp;&nbsp;&nbsp;<input type="button" value="查看" class="btn-cx" onclick="search();"/>
					<!-- &nbsp;&nbsp;&nbsp;<input type="button" value="导出" class="btn-cx" onclick="exportData();"/> -->
			</span>
		</div>
		<div class="right" style="margin-bottom: 50px;width:50%;">
			<iframe src="${iframeAnalyzeSrc}" id="iframeAnalyze" frameborder="0" scrolling="no" style="width: 95%;heighy:500px;height: 500px;margin-top: 0px;margin-right: 20px;">
			</iframe>
		</div>
<div id="container" style="width:50%; height: 400px; margin: 0 0"></div>
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
	$("#type").val("${type}");
	$("#startTime").val("${startTime}");
	$("#endTime").val("${endTime}");
	$("#iframeAnalyzeSrc").val("${iframeAnalyzeSrc}");
}
var sumList=eval('${mainList}');
var alist=eval('${alist}');
Highcharts.chart('container', {
	credits: {
    	text: '',
    	href: ''
	},
    chart: {
        type: 'pie'
    },
    title: {
        text: ''
    },
    subtitle: {
        text: ''
    },
    plotOptions: {
        series: {
            dataLabels: {
                enabled: true,
                format: '{point.name}: {point.y}次'
            },
            cursor: 'pointer', 
            events: { 
                click: function(e) { 
                    var type = $("#type").val();
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					$("#iframeAnalyze").attr("src","analysis/iframeAnalyze?type="+type+"&startTime="+startTime+"&endTime="+endTime+"&pointName="+encodeURI(encodeURI(e.point.name)));
                    //将点击的存到页面，当子frame提交的时候可以获取这个值
                    $("#pointName").val(e.point.name);
                } 
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}次</b><br/>'
    },
    series: [{
        name: '文档类型',
        colorByPoint: true,
        data: sumList
    }],
    drilldown: {
        series: alist
    }
});
function search(){
	var type = $("#type").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	$("#iframeAnalyzeSrc").val("analysis/iframeAnalyze?type="+type+"&startTime="+startTime+"&endTime="+endTime);
	$("#myform").submit();
}

function exportData(){
	
}
</script>
</body>
</html>