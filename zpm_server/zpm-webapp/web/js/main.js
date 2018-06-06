// tasktab
function setTab(name, cursel, n) {
	for (i = 0; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		if(menu!=null){
			menu.className = i == cursel ? "hover" : "";
		}
		if(con!=null){
			con.style.display = i == cursel ? "block" : "none";
		}
		if(i!=1){
			var conheightwe = parent.document.body.clientHeight-55;
			if(con!=null){
    			con.style.height=conheightwe+"px";
    		}
		}
	}
}
function openShutManager(oSourceObj, oTargetObj, shutAble, oOpenTip, oShutTip,rightObj) {
	alert(1);
	var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
	var openTip = oOpenTip || "";
	var shutTip = oShutTip || "";
	if (targetObj.style.display != "none") {
		if (shutAble) {
			return;
		}
		targetObj.style.display = "none";
		if (openTip && shutTip) {
    	//sourceObj.innerHTML = shutTip; 
		}
		sourceObj.style.marginLeft = "-220px";
		var e12=document.getElementById("right");
		var widthwe = parent.document.body.clientWidth-1;
    	e12.style.width=widthwe+"px";
    	e12.style.marginLeft = "0px";
    	
    	var e122=document.getElementById("maintabs");
    	e122.style.width=widthwe+"px";
    	e122.style.marginLeft = "0px";
    	$('.tabs-header').width(widthwe+"px");
    	$('.tabs-wrap').width(widthwe+"px"); 
    	$('.tabs-panels').width(widthwe+"px");
    	$('.tabs-panels-noborder').width(widthwe+"px");
    	$('.panel').width(widthwe+"px");
    	$('.panel-body').width(widthwe+"px");
    	$('.panel-body-noheader').width(widthwe+"px");
    	$('.panel-body-noborder').width(widthwe+"px");
	} else {
		targetObj.style.display = "block";
		if (openTip && shutTip) {
    	//sourceObj.innerHTML = openTip; 
		}
		sourceObj.style.marginLeft = "0px";
		var e12=document.getElementById("right");
		var widthwe = parent.document.body.clientWidth-205;
    	e12.style.width="";
    	e12.style.marginLeft = "0px";
    	
    	var e122=document.getElementById("maintabs");
    	e122.style.width=widthwe+"px";
    	e122.style.marginLeft = "0px";
    	$('.tabs-header').width(widthwe+"px");
    	$('.tabs-wrap').width(widthwe+"px"); 
    	$('.tabs-panels').width(widthwe+"px");
    	$('.tabs-panels-noborder').width(widthwe+"px");
    	$('.panel').width(widthwe+"px");
    	$('.panel-body').width(widthwe+"px");
    	$('.panel-body-noheader').width(widthwe+"px");
    	$('.panel-body-noborder').width(widthwe+"px");
	}
	alert(2);
}
function MM_over(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "block";
	mSubObj.style.backgroundColor = "#f7f7f7";
}
function MM_out(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";
}

var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;

// open hidden layer
function mopen(id) {	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if (ddmenuitem) {
		ddmenuitem.style.visibility = "hidden";
	}

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = "visible";
}
// close showed layer
function mclose() {
	if (ddmenuitem) {
		ddmenuitem.style.visibility = "hidden";
	}
}

// go close timer
function mclosetime() {
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 

