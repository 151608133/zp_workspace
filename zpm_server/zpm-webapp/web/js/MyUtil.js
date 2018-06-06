$().ready(function() {
    	$("#commentForm").validate();
});
function getCity(data,ab){
	if(data==-1){
		$("#city").html("<option value='-1'>请选择</option>");
		return false;
	}	
 
		$.ajax({
				type:"POST",
				url:"user/getCity",
				dataType:'JSON',
				data:"uid="+data,
				success: function(data){
					if(data.code==0){
					var str="<option value='-1'>请选择</option>";
						//debugger;
						for(var i=0;i<data.obj.length;i++){
							var item=data.obj[i];
							if(ab==item.id){
								str+="<option selected=\"selected\" value='"+item.id+"'>"+item.name+"</option>";
							} else {
								str+="<option  value='"+item.id+"'>"+item.name+"</option>";
							}
							
						}
						$("#city").html(str);
					}
				
				}
		});
}
/*var cnmsg = {  
	    required: "必填字段",   
	    email: "邮件格式错误",   
	    url: "网址不合法",  
	    date: "日期不合法",   
	    number: "非法数字",   
	    digits: "只能输入整数",   
	    creditcard: "卡号不合法",   
	    equalTo: "确认值不同"
	  };
jQuery.extend(jQuery.validator.messages, cnmsg); */
jQuery.validator.messages.required = "";
jQuery.validator.messages.email = "";
jQuery.validator.messages.number = "";