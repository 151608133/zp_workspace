function funcAddTab(name,url){
	window.parent.afreshTab(name,url);
}

function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover-qh":"";
con.style.display=i==cursel?"block":"none";
}
}
