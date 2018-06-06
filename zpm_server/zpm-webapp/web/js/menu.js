
var resourceList=null;

$(function() {
	tabClose();
	tabCloseEven();
	// 释放内存
	//$.fn.panel.defaults = $.extend({}, $.fn.panel.defaults, {
	//	onBeforeDestroy : function() {
	//		var frame = $('iframe', this);
	//		if (frame.length > 0) {
	//			frame[0].contentWindow.document.write('');
	//			frame[0].contentWindow.close();
	//			frame.remove();
	//		}
	//	}
	//});
	
	
});

var windowIndex=0;
function genWindowIndex(){
	return windowIndex++;
}

/*
 *@param string subtitle	
 *@param string url
 *@result add tab
 */
function addTab(subtitle, url) {
	if(!url){
		return false;
	}
	/*
	var start=url.indexOf('/sairs/')+7;
	var tempUrl=url.substring(start);
	var judge=false;
	//过滤权限 
	if(!resourceList){
		return false;
	}
	$.each(resourceList,function(){
		var $this=this;
		var vali=$this.value;
		var tempVali=vali.substr(0,vali.lastIndexOf('/')+1);
		var reg=new RegExp('^'+tempVali+'[^/]+$');
		if(reg.test(tempUrl)){
			judge=true;
			return false;
		}
	});
	
	judge=true;
	if(!judge){
		alert('对不起、您没有访问权限');
		return false;
	}
	*/
	//debugger;
	//id=genWindowIndex();
	//$.messager.progress({
	//	text : '页面加载中....',
	//	interval : 200
	//});
	//debugger;
	var tabs = $('#maintabs').tabs('tabs');
	if (!$('#maintabs').tabs('exists', subtitle)) {
			//iframe
			$('#maintabs').tabs('add', {
				title:subtitle,
				content:"<iframe id='main_iframe_"+id+"' style='width:100%;height:100%;' frameborder=0 src='"+url+"' ></iframe>",
				border:true,
				closable:true,
				closed: true
			});
	} else {
		$('#maintabs').tabs('select', subtitle);
		$.messager.progress('close');
	}
	tabClose();
}

function addHomePage(subtitle, url){
	id=genWindowIndex();
	//$.messager.progress({
	//	text : '页面加载中....',
	//	interval : 200
	//});
	var tabs = $('#maintabs').tabs('tabs');
	if (!$('#maintabs').tabs('exists', subtitle)) {
			//iframe
			$('#maintabs').tabs('add', {
				title:subtitle,
				content:"<iframe id='main_iframe_"+id+"' style='width:100%;height:100%;' frameborder=0 src='"+url+"' scrolling='no'></iframe>",
				border:true,
				closable:false
			});
	} else {
		$('#maintabs').tabs('select', subtitle);
		$.messager.progress('close');
	}
	tabClose();
}

//刷新已经选中的
function refreshTab(subtitle,url) {
	if ($('#maintabs').tabs('exists', subtitle)) {
			//addmask();
			var tab =  $('#maintabs').tabs('getSelected');
			_refreshTab(tab,url);
			$.messager.progress('close');
	}
}

/*
 *刷新本身未选中的
 *@param string subtitle	
 *@param string url
 *@result refresh No Selectd Tab
 */
function refreshNoSelectdTab(subtitle,url) {
	if ($('#maintabs').tabs('exists', subtitle)) {
			//addmask();
			var tab = $('#maintabs').tabs('getTab',subtitle);
			_refreshTab(tab,url);
			$.messager.progress('close');
	}
}


/*解决刷新update 之后tab 无法关闭
 *@param object tab	
 *@param string url
 *@result refreshtab
 */
function _refreshTab(obj,url){
       var refresh_tab = obj;
       if (refresh_tab && refresh_tab.find('iframe').length > 0)
            {
                var _refresh_ifram = refresh_tab.find('iframe')[0];
                _refresh_ifram.contentWindow.location.href = url;
            } 
}
 
/*
 *关闭tab
 *@param string subtitle	
 *@result closeTab
 */   
function closeTab(subtitle){
	if ($('#maintabs').tabs('exists', subtitle)) {
		$('#maintabs').tabs('close', subtitle);
	}
}

/*
 *对于同一个tab每次都刷新
 *@param string subtitle	
 *@param string url
 *@result afreshTab
 */
function afreshTab(subtitle,url){
	var i = url.indexOf("?");
	if (i <= 0) {//解决诺基亚外网407导致网络缓存问题  保证每次请求的地址不同
		url += "?timestamp="+(new Date()).valueOf();
	} else {
		url += "&&timestamp="+(new Date()).valueOf();
	}
	closeTab(subtitle);
	addTab(subtitle,url);
}

function addmask() {
	$.messager.progress({
		text : '页面加载中....',
		interval : 100
	});
}

/*
 *@param string title
 *@result check is exist tab
 */
function existTab(title){
	if ($('#maintabs').tabs('exists', title)) {
		return true;
	}
	return false;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		var subtitle = $(this).children(".tabs-closable").text();
		$('#mm').data("currtab", subtitle);
		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#maintabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#maintabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		})
	})
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#maintabs').tabs('close', currtab_title);
	})
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if($(n).hasClass("tabs-closable")){
				$('#maintabs').tabs('close', t);
			}
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if($('a:eq(0) span', $(n)).hasClass("tabs-closable")){
				$('#maintabs').tabs('close', t);
			}
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if($('a:eq(0) span', $(n)).hasClass("tabs-closable")){
				$('#maintabs').tabs('close', t);
			}
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

$.parser.onComplete = function() {/* 页面所有easyui组件渲染成功后，隐藏等待信息 */
	if ($.browser.msie && $.browser.version < 7) {/* 解决IE6的PNG背景不透明BUG */
	}
	window.setTimeout(function() {
		$.messager.progress('close');
	}, 200);
};
