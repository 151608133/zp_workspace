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
    <title>系统设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/dingdan.css"rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/MyUtil.js"></script>
  </head>
  
  <body>
<div class="sy-right">
  <div class="fk-nr">
  <form id="commentForm" method="post" action="param/update_param">
  <input type="hidden" name="id" value="${paramMap.id}"/>
    <div class="shezhi-c">
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='open_message_alert'/>：</div>
        <div class="shezhi-c-r-a">
        <input type="hidden" name="is_alarm" id="is_alarm" value="${paramMap.is_alarm}"/>
          <input type="radio" name="is_alarm_radio" onclick="changeValue('is_alarm',1)" <c:if test="${paramMap.is_alarm == '1'}">checked="checked"</c:if>/>
          <Span class="kaiqi"><fmt:message key='open'/></Span>
          <input type="radio" name="is_alarm_radio" onclick="changeValue('is_alarm',0)"  <c:if test="${paramMap.is_alarm == '0'}">checked="checked"</c:if>style="margin-left:15px;"/>
          <span class="kaiqi"><fmt:message key='close'/></span> 
        </div>
      </div>
      <div class="clear"></div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='alert_mode'/>：</div>
        <div class="shezhi-c-r-a">
        <input type="hidden" name=message  id="message" value="${paramMap.message}"/>
          <input type="checkbox" id="message_check" onclick="changeCheck('message')" disabled="disabled"/> <!-- <c:if test="${paramMap.message == '1'}">checked="checked"</c:if> --> 
          <span class="kaiqi"><fmt:message key='short_message'/></span>
          
          <input type="hidden" name="email" id="email" value="${paramMap.email}"/>
           <input type="checkbox" id="email_check" onclick="changeCheck('email')" <c:if test="${paramMap.email == '1'}">checked="checked"</c:if> style="margin-left:15px;"/ >
          <span class="kaiqi"><fmt:message key='mail'/></span>
          
          <input type="hidden" name="app_message" id="app_message" value="${paramMap.app_message}" />
           <input type="checkbox" id="app_message_check" onclick="changeCheck('app_message')" <c:if test="${paramMap.app_message == '1'}">checked="checked"</c:if> style="margin-left:15px;"/>
          <span class="kaiqi"><fmt:message key='app_message'/></span> 
          </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='whether_to_automatically_back_up_data'/>：</div>
        <div class="shezhi-c-r-a">
        <input type="hidden" name="is_bak" id="is_bak" value="${paramMap.is_bak }"/>
          <input type="radio" name="shuju" onclick="changeValue('is_bak',1)" <c:if test="${paramMap.is_bak == '1'}">checked="checked"</c:if> />
          <Span class="kaiqi"><fmt:message key='yes'/></Span>
          <input type="radio" name="shuju" onclick="changeValue('is_bak',0)" <c:if test="${paramMap.is_bak == '0'}">checked="checked"</c:if>style="margin-left:15px;"/>
          <span class="kaiqi"><fmt:message key='no'/></span> 

          <span style="margin-left:10px;"><fmt:message key='auto_backup_time'/>：&nbsp;&nbsp;<fmt:message key='every_day'/>
          <select name="bak_time" id="bak_time" style="margin-left:10px;" class="shezhi-xiala">
           	<option value="00:00" <c:if test="${paramMap.bak_time=='00:00'}">selected</c:if>>00:00</option>
            <option value="01:00" <c:if test="${paramMap.bak_time=='01:00'}">selected</c:if>>01:00</option>
            <option value="02:00" <c:if test="${paramMap.bak_time=='02:00'}">selected</c:if>>02:00</option>
            <option value="03:00" <c:if test="${paramMap.bak_time=='03:00'}">selected</c:if>>03:00</option>
            <option value="04:00" <c:if test="${paramMap.bak_time=='04:00'}">selected</c:if>>04:00</option>
            <option value="05:00" <c:if test="${paramMap.bak_time=='05:00'}">selected</c:if>>05:00</option>
            <option value="06:00" <c:if test="${paramMap.bak_time=='06:00'}">selected</c:if>>06:00</option>
            <option value="07:00" <c:if test="${paramMap.bak_time=='07:00'}">selected</c:if>>07:00</option>
            <option value="08:00" <c:if test="${paramMap.bak_time=='08:00'}">selected</c:if>>08:00</option>
            <option value="09:00" <c:if test="${paramMap.bak_time=='09:00'}">selected</c:if>>09:00</option>
            <option value="10:00" <c:if test="${paramMap.bak_time=='10:00'}">selected</c:if>>10:00</option>
            <option value="11:00" <c:if test="${paramMap.bak_time=='11:00'}">selected</c:if>>11:00</option>
            <option value="12:00" <c:if test="${paramMap.bak_time=='12:00'}">selected</c:if>>12:00</option>
            <option value="13:00" <c:if test="${paramMap.bak_time=='13:00'}">selected</c:if>>13:00</option>
            <option value="14:00" <c:if test="${paramMap.bak_time=='14:00'}">selected</c:if>>14:00</option>
            <option value="15:00" <c:if test="${paramMap.bak_time=='15:00'}">selected</c:if>>15:00</option>
            <option value="16:00" <c:if test="${paramMap.bak_time=='16:00'}">selected</c:if>>16:00</option>
            <option value="17:00" <c:if test="${paramMap.bak_time=='17:00'}">selected</c:if>>17:00</option>
            <option value="18:00" <c:if test="${paramMap.bak_time=='18:00'}">selected</c:if>>18:00</option>
            <option value="19:00" <c:if test="${paramMap.bak_time=='19:00'}">selected</c:if>>19:00</option>
            <option value="20:00" <c:if test="${paramMap.bak_time=='20:00'}">selected</c:if>>20:00</option>
            <option value="21:00" <c:if test="${paramMap.bak_time=='21:00'}">selected</c:if>>21:00</option>
            <option value="22:00" <c:if test="${paramMap.bak_time=='22:00'}">selected</c:if>>22:00</option>
            <option value="23:00" <c:if test="${paramMap.bak_time=='23:00'}">selected</c:if>>23:00</option>
            
          </select>
          </span>
           </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='short_message_gateway_address'/>：</div>
        <div class="shezhi-c-r">
          <input type="text" name="message_gateway" value="${paramMap.message_gateway }" class="shezhi-srk" />
        </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='sms_password'/>：</div>
        <div class="shezhi-c-r">
          <input type="text" name="message_password" value="${paramMap.message_password }" class="shezhi-srk" />
        </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='mailbox_address'/>：</div>
        <div class="shezhi-c-r">
          <input type="text" name="email_address" value="${paramMap.email_address }" class="shezhi-srk" />
        </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='mailbox_account'/>：</div>
        <div class="shezhi-c-r">
          <input type="text" name="email_account" value="${paramMap.email_account }" class="shezhi-srk" />
        </div>
      </div>
      <div class="shezhi-aa">
        <div class="shezhi-c-l"><fmt:message key='mailbox_password'/>：</div>
        <div class="shezhi-c-r">
          <input type="text" name="email_password" value="${paramMap.email_password }" class="shezhi-srk" />
        </div>
      </div>
    </div>
   <!-- <div class="xdd-bjgl-tj"><input class="btn-tj" type="submit"  value="提交"></div>
    -->
    <div class="xdd-bjgl-tj"><input type="button" value="<fmt:message key='submit'/>" onclick="submitParam()" class="btn-tj"/></div>
   
    </form>
  </div>  
</div>
  </body>
   <script type="text/javascript">
   //${mes};
   //alert(${mes});
	function changeValue(key , value){
		$("#"+key).val(value);
	}
	
	function changeCheck(key){
		var value = $("#"+key).val();
		if(value=="1"){
			$("#"+key).val(0);
		}else{
			$("#"+key).val(1);
		}
	}
	
	function submitParam(){
		var self=$("#commentForm").get(0);
		$.ajax({
				type:"POST",
				url:"param/update_param",
				dataType:'JSON',
				data:$(self).serialize(),
				error: function(request) {
                    alert("<fmt:message key='server_exception'/>！");
                },
				success: function(data){
					if(data.code==1){
						alert(data.msg);
						window.location.href="<%=basePath%>param/systemSet";
					}else{
						alert(data.msg);
					}
				}
			});
		//$("#commentForm").submit();
		//alert("提交成功");
	}
   </script>
</html>
