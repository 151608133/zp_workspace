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
		<div class="left" >
			<span class="xdd-bjgl-ss-span" style="margin-top: 20px;margin-left: 40px;">日期：
		        <input type="text" id="startdate" name="startTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
		        至	<input type="text" id="enddate" name="endTime" class="input-time" readonly="readonly" style="width:120px;height: 30px;border:1px solid #c7d5e0;border-radius: 3px;" />
					分析维度：<select name="stateCd" id="stateCd" class="select-ck">
						<option value="0">阅读量分析</option>
						<option value="1">下载量</option>
						<option value="2">收藏量</option>
					</select>
					<input type="button" value="分析" class="btn-cx" onclick="search();"/>
			</span>
		</div>
<div id="container" style="min-width: 310px; max-width: 600px; height: 400px; margin: 0 auto"></div>
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
var sumList=eval('[{"drilldown":"1","name":"472939A","y":28},{"drilldown":"2","name":"C109500","y":17},{"drilldown":"3","name":"C08790","y":5},{"drilldown":"4","name":"084792A","y":32},{"drilldown":"4","name":"C08798","y":2},{"drilldown":"4","name":"C08773","y":8}]');
var alist=eval('${alist}');
Highcharts.chart('container', {
    chart: {
        type: 'pie'
    },
    title: {
        text: 'NOKIA文库数据统计'
    },
    subtitle: {
        text: ''
    },
    plotOptions: {
        series: {
            dataLabels: {
                enabled: true,
                format: '{point.name}: {point.y:.1f}%'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
    },
    series: [{
        name: '板件号',
        colorByPoint: true,
        data: sumList
    }],
    drilldown: {
        series: alist
    }
});
</script>
</body>
</html>