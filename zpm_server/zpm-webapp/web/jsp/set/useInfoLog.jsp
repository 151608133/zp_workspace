<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>使用详情</title>
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
		<form action="<%=basePath%>useInfo/log.html" method="post" name="logForm" id="logForm">
			<div class="sy-right">
				<div class="xdd-bjgl">
					<div class="xdd-bjgl-ss">
						<div class="left">
							<!-- <span class="xdd-bjgl-ss-span">日期： 
							<input type="text" id="startTime" name="startTime" value="${operationLog.startTime }" readonly class="input-srsj" />至
							<input type="text" id="endTime" name="endTime" value="${operationLog.endTime }" readonly class="input-srsj" />
							</span>
							<span class="xdd-bjgl-ss-span"><input type="button" value="<fmt:message key='query'/>" class="btn-cx" onclick="search()" /> </span>
							 -->
							<input type="hidden" id="funcId" name="funcId" value="${operationLog.funcId }" />
							<input type="hidden" id="startTime" name="startTime" value="${operationLog.startTime }" />
							<input type="hidden" id="endTime" name="endTime" value="${operationLog.endTime }" />
						</div>
					</div>
					<table class="tab">
						<tr>
							<td class="tab-bt" width="10%">
								ID
							</td>
							<td class="tab-bt" width="10%">
								<fmt:message key='operation_time'/>
							</td>
							<td class="tab-bt" width="10%">
								<fmt:message key='user'/>
							</td>
							<td class="tab-bt" width="10%">
								<fmt:message key='modular'/>
							</td>
							<td class="tab-bt" width="10%">
								<fmt:message key='operation_object'/>
							</td>
							<td class="tab-bt" width="10%">
								<fmt:message key='describe'/>
							</td>
							<!-- <td class="tab-bt" width="10%">
								操作
							</td> -->
						</tr>
						<c:choose>
							<c:when test="${not empty logList }">
								<c:forEach items="${logList }" var="log">
									<tr>
										<td>
											${log.id }
										</td>
										<td>
											${log.oper_time }
										</td>
										<td>
											${log.user_name }
										</td>
										<td>
											${log.modular }
										</td>
										<td>
											${log.operation_obj }
										</td>
										<td>
											${log.description }
										</td>
										<!--  <td>
											<input type="button" value="删除" class="btn-xg"
												style="background: #ff6e40;" onclick="del(${log.id })" />
										</td>
										-->
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="xi">
									<td colspan="7">
										<fmt:message key='no_relevant_data'/>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
					${operationLog.page.pageStr}
				</div>
			</div>
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
		function search(){
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startTime!="" && endTime!="" && startTime > endTime){
				alert("<fmt:message key='please_select_the_correct_time_range'/>！");
				return false;
			}
			$("#logForm").submit();
		}
		function del(obj){
			var logId = obj;
			if(confirm("<fmt:message key='are_you_sure_you_want_to_delete_the_record'/>？")){
				var url = "<%=basePath%>log/delOperationLog?logId="+logId;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		</script>
	</body>
</html>
