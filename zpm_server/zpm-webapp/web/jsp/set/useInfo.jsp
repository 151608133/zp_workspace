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
    <title>使用情况分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/dingdan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/zn_datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery-ui.css" />
	<script type="text/javascript" src="<%=basePath%>js/ui.datepicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/tab.js"></script>
  </head>
  
  <body>
<div class="sy-right">
  <div class="xdd-bjgl">
  <form action="useInfo.html" method="post" name="useInfoForm" id="useInfoForm">
    <div class="xdd-bjgl-ss">
      <div class="left">
        <span class="xdd-bjgl-ss-span"><fmt:message key='date'/>：
        <input type="text" id="startTime" name="startTime" value="${blacklist.startTime }" readonly class="input-srsj" /><fmt:message key='to'/><input type="text" id="endTime" name="endTime" value="${blacklist.endTime }" readonly class="input-srsj" /></span>
        <span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="search()"/></span>
      </div>
    </div>
    <table class="tab">
      <tr>
        <td class="tab-bt" width="10%"><fmt:message key='date'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='deliver_goods'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='goods_receipt'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='order'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='warehouse'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='report_output'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='user'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='set_up'/></td>
        <td class="tab-bt" width="10%"><fmt:message key='advice_feedback'/></td>
      </tr>
       <c:choose>
		<c:when test="${not empty useInfoList }">
	    <c:forEach items="${useInfoList}" var="useInfo" varStatus="vs">
      <tr>
       <td> ${useInfo.date} </td>
        <td>
         <c:choose>
			<c:when test="${useInfo.sfh > 0}">
				<a href="javacript:void(0);" onclick="getDetail(15,'${useInfo.date}')">${useInfo.sfh}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.sfh}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
         <c:choose>
			<c:when test="${useInfo.sh > 0}">
				<a href="javacript:void(0);" onclick="getDetail(14,'${useInfo.date}')">${useInfo.sh}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.sh}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.dd > 0}">
				<a href="javacript:void(0);" onclick="getDetail(1,'${useInfo.date}')">${useInfo.dd}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.dd}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.ck > 0}">
				<a href="javacript:void(0);" onclick="getDetail(3,'${useInfo.date}')">${useInfo.ck}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.ck}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.bgsc > 0}">
				<a href="javacript:void(0);" onclick="getDetail(4,'${useInfo.date}')">${useInfo.bgsc}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.bgsc}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.yh > 0}">
				<a href="javacript:void(0);" onclick="getDetail(5,'${useInfo.date}')">${useInfo.yh}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.yh}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.sz > 0}">
				<a href="javacript:void(0);" onclick="getDetail(6,'${useInfo.date}')">${useInfo.sz}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.sz}
			</c:otherwise>
		</c:choose>
        </td>
        <td>
        <c:choose>
			<c:when test="${useInfo.fk > 0}">
				<a href="javacript:void(0);" onclick="getDetail(7,'${useInfo.date}')">${useInfo.fk}</a>
	        </c:when>
	        <c:otherwise>
				${useInfo.fk}
			</c:otherwise>
		</c:choose>
        </td>
      </tr>
     </c:forEach>
		</c:when>
		<c:otherwise>
			<tr class="xi">
				<td colspan="5"><fmt:message key='no_relevant_data'/></td>
			</tr>
		</c:otherwise>
	</c:choose>
    </table>
    ${blacklist.page.pageStr}
	</form>
  	
</div>
</div>
</body>
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
		
	function search(){
		var startTime=document.getElementById("startTime").value;
   		var endTime=document.getElementById("endTime").value;
		if(startTime>endTime){
			alert("<fmt:message key='please_select_the_correct_time_range'/>");
			return;
		}
	    $("#useInfoForm").submit();
	}
	
	function getDetail(id,date){
		/*var startTime=document.getElementById("startTime").value;
   		var endTime=document.getElementById("endTime").value;
   		if(startTime>endTime){
			alert("请选择正确的时间范围");
			return;
		}*/
		var info = "<fmt:message key='use_details'/>";
		funcAddTab(info,"useInfo/getDetail?funcId="+id+"&startTime="+date+"&endTime="+date);
	}
</script>
</html>
