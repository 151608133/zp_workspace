function changepwd() {
	getPwdAndChk();
}
function changepwd3(v) {
	var type = $("#type").val();
	if (v == ""&&type =='zh') {
		document.getElementById("password1").style.display = "";
		document.getElementById("password").style.display = "none";
		document.getElementById("password1").value = "密码";
	}else if(v == ""&&type =='en'){
		document.getElementById("password1").style.display = "";
		document.getElementById("password").style.display = "none";
		document.getElementById("password1").value = "Password";
	}
}

function setPwdAndChk() { 

			//取用户名 
	var account = document.getElementById("loginname").value;
	var password = document.getElementById("password").value; 
	debugger;
			//将最后一个用户信息写入到Cookie 
	setLastAccount(account); 
			//如果记住密码选项被选中 
	if (document.getElementById("chkRememberPwd").checked == true) { 
			//取密码值 
		var expdate = new Date();
		expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000)); 

			//将用户名和密码写入到Cookie 
		setCookie(account, password, expdate);
	} else { 

				//如果没有选中记住密码,则立即过期 
		resetCookie();
	}
}
function setLastAccount(account) {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
	var expdate = new Date(); 

		//当前时间加上两周的时间 
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	setCookie(id, account, expdate);
}

	//取Cookie的值 
function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg) {
			return getCookieVal(j);
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) {
			break;
		}
	}
	return null;
}
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1) {
		endstr = document.cookie.length;
	}
	return unescape(document.cookie.substring(offset, endstr));
} 

		//写入到Cookie 
function setCookie(name, value, expires) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
}
function resetCookie() {
	var account = document.getElementById("loginname").value;
	var expdate = new Date();
	setCookie(account, null, expdate);
}
function rempwd() {
	document.getElementById("pwdcheck").value = document.getElementById("chkRememberPwd").checked;
}
function getPwdAndChk() {
	var account = document.getElementById("loginname").value;
	var password = getCookie(account);
	if (password != null) {
		document.getElementById("chkRememberPwd").checked = true;
		document.getElementById("password").value = password;
	} else {
		document.getElementById("chkRememberPwd").checked = false;
		document.getElementById("password").value = "";
	}
}
function lis() {
	document.onkeydown=function(event){ 
        e = event ? event :(window.event ? window.event : null); 
        if(e.keyCode==13){
        	sub();
        } 
    }
}