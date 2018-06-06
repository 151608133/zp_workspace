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
		<title>操作日志</title>
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
		<link href="<%=basePath %>css/jquery.searchableSelect.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.searchableSelect.js"></script>
	</head>

	<body>
		<form action="<%=basePath%>log.html" method="post" name="logForm" id="logForm">
			<div class="sy-right">
				<div class="xdd-bjgl">
					<div class="xdd-bjgl-ss">
						<div class="left">
							<span class="xdd-bjgl-ss-span">日期： 
							<input type="text" id="startTime" name="startTime" value="${operationLog.startTime }" readonly class="input-srsj" />至
							<input type="text" id="endTime" name="endTime" value="${operationLog.endTime }" readonly class="input-srsj" />
							</span>
							<span class="xdd-bjgl-ss-span">用户：<input type="text" class="input-sr" name="userName" id="userName" value="${operationLog.userName }" /></span>
							<span class="xdd-bjgl-ss-span">描述：<input type="text" class="input-sr" name="description" id="description" value="${operationLog.description }" /></span>
							<span class="xdd-bjgl-ss-span"><input type="button" value="查询" class="btn-cx" onclick="search()" /> </span>
						</div>
					</div>
					<table class="tab">
						<tr>
							<td class="tab-bt" width="10%">
								序号
							</td>
							<td class="tab-bt" width="10%">
								操作时间
							</td>
							<td class="tab-bt" width="10%">
								用户
							</td>
							<td class="tab-bt" width="10%">
								模块
							</td>
							<td class="tab-bt" width="10%">
								操作对象
							</td>
							<td class="tab-bt" width="10%">
								描述
							</td>
							<td class="tab-bt" width="10%">
								操作
							</td>
						</tr>
						<c:choose>
							<c:when test="${not empty logList }">
								<c:forEach items="${logList }" var="log">
									<tr>
										<td>
											${log.id }
										</td>
										<td>
											<fmt:formatDate value="${log.operTime }" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>
											${log.userName }
										</td>
										<td>
											${log.operType }
										</td>
										<td>
											${log.ip }
										</td>
										<td title="${log.description }">
											${log.description }
										</td>
										<td>
											<input type="button" value="删除" class="btn-xg"
												style="background: #ff6e40;" onclick="del(${log.id })" />
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="xi">
									<td colspan="7">
										没有相关数据
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
			$('#guanjianzi').searchableSelect();
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
			var userName = $("#userName").val();
			var description = $("#description").val();
			if(startTime!="" && endTime!="" && startTime > endTime){
				alert("请选择正确的时间范围！");
				return false;
			}
			$("#logForm").submit();
		}
		function del(obj){
			var logId = obj;
			if(confirm("确定要删除该记录吗？")){
				var url = "<%=basePath%>log/delOperationLog?logId="+logId;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		function descAjax(obj){
			alert(obj.value);
		}
		</script>
	</body>
</html>
