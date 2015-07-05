// Copyright(C) 2009-2011 CSSMenuTools All rights reserved.
// www.cssmenutools.com
//ProductID:AcrdMenu
// Trial copy.

var eadQJBB = {
dummy:0,
version:'2.1.5.0',
seo_links:0,
menu_width:169,
layer:false,
x:0,
y:0,
valign:0,
is_floating:false,
show_selected:true,
hlparent:false,
expand_selected:false,
expandSelAnimated:false,
one_opened:true,
animation_type:0,
open_speed:10,
OpenByMouseOver:false,
DelayOpenByMouseOver:1000,
ExpByMouseOver:false,
DelayExpByMouseOver:1000,
wrapText:false,
rtl:false,
PREVIEW_BACKGROUND_COLOR:'#FFFFFF',
MENU_NAME:'Orange and Green',
stream:[0,"","",'','','eadQJBB__Top',3,0,false,0,0,"Products","",'','','eadQJBB__Level-1-arrow',30,0,false,3,0,"Product&nbsp;#1","",'','','eadQJBB__Level-2-arrow',27,0,false,3,0,"Windows","",'','','eadQJBB__Level-3',27,0,false,0,0,"Mac","",'','','eadQJBB__Level-3',27,0,false,0,0,"Linux","",'','','eadQJBB__Level-3',27,0,false,0,0,"Product&nbsp;#2","",'','','eadQJBB__Level-2',27,0,false,0,0,"Product&nbsp;#3","",'','','eadQJBB__Level-2',27,0,false,0,0,"Download","",'','','eadQJBB__Level-1',30,0,false,0,0,"Purchase","",'','','eadQJBB__Level-1',30,0,false,0,0,"Support","",'','','eadQJBB__Level-1-arrow',30,0,false,5,0,"Support&nbsp;Center","",'','','eadQJBB__Level-2',27,0,false,0,0,"Online&nbsp;Documentation","",'','','eadQJBB__Level-2',27,0,false,0,0,"Knowledge&nbsp;Base","",'','','eadQJBB__Level-2',27,0,false,0,0,"Product&nbsp;Forums","",'','','eadQJBB__Level-2',27,0,false,0,0,"Contact&nbsp;Support","",'','','eadQJBB__Level-2',27,0,false,0,0,"Company","",'','','eadQJBB__Level-1-arrow',30,0,false,5,0,"About&nbsp;Us","",'','','eadQJBB__Level-2',27,0,false,0,0,"What's&nbsp;New","",'','','eadQJBB__Level-2',27,0,false,0,0,"Our&nbsp;Customers","",'','','eadQJBB__Level-2',27,0,false,0,0,"Testimonials","",'','','eadQJBB__Level-2',27,0,false,0,0,"Contact&nbsp;Us","",'','','eadQJBB__Level-2',27,0,false,0,0,"","",'','','eadQJBB__Bottom',4,0,false,0],
END_PARAMETERS:1,
browser:function(){var ua=navigator.userAgent.toLowerCase()
this.opera=ua.indexOf('opera')>=0
this.safari=ua.indexOf('safari')>=0
this.ie=document.all&&!this.opera
this.macie=this.ie&&ua.indexOf('mac')>=0
this.winie=this.ie&&!this.macie
this.ieCanvas=(this.ie&&document.compatMode=="CSS1Compat")?document.documentElement:document.body
return this},
setPathAdjustment:function(ID){var sl=''
var sc=document.getElementsByTagName('script')
for(var i=0;i<sc.length;i++){if(sc[i].innerHTML.search(ID)>-1)sl=sc[i].src}this.SCRIPT_LOCATION=sl.substr(0, sl.lastIndexOf('/')+1)},
adjustPath:function(path){var idf=path.charAt(0)
if(idf=='*'||idf=='&')return this.SCRIPT_LOCATION+path.substr(1)
return path},
linkScripts:function(aNewScripts){var scripts=document.getElementsByTagName('script')
for(var i=0;i<aNewScripts.length;i++){var bScriptLinked=false
for(var j=0;j<scripts.length;j++){if(aNewScripts[i]==scripts[j].src){bScriptLinked=true;break}}if(!bScriptLinked)document.write("<script src='"+this.adjustPath(aNewScripts[i])+"' type='text/javascript'><\/script>")}},
isCurrent:function(r){if(!r)return false
var l=location.href.replace(/ /g,'%20')
if(r.search('//')==-1){if(r.charAt(0)=='/')
r=l.replace(/(.*\/\/[^\/]*).*/,'$1')+r
else
r=l.replace(/[^\/]*$/,'')+r}do{var r1=r
r=r1.replace(/[^\/]*\/\.\.\//,'')}while(r!=r1)
return r==l},
addLoadEvent:function(f){var done=0
function w(){if(!done){done=1
f()}}if(document.addEventListener){document.addEventListener('DOMContentLoaded', w, false)}if(this.br.ie&&window==top)(function(){try{document.documentElement.doScroll('left')}catch(e){setTimeout(arguments.callee, 0)
return}w()})()
var oldf=window.onload
if(typeof oldf!='function'){window.onload=w}else{window.onload=function(){try{oldf()}catch(e){}w()}}},
Preload:function(){var b=document.getElementsByTagName('body');if(b[0]){var bb=b[0];var d;d=document.createElement('div');d.className='eadQJBB__Level-1-arrow eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-1-arrow_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-1-arrow_SEL eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-1 eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-1_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-1_SEL eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Top eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Top_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Bottom eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Bottom_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2 eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2_SEL eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2-arrow eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2-arrow_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-2-arrow_SEL eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-3 eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-3_MO eadQJBB__Preload';bb.appendChild(d);d=document.createElement('div');d.className='eadQJBB__Level-3_SEL eadQJBB__Preload';bb.appendChild(d);}},
init:function(){var m=this
m.ID=AcrdMenu_ID
m.br=new m.browser()
m.setPathAdjustment('AcrdMenuMenu script ID:'+m.ID+' ')
m.linkScripts(new Array())
m.addLoadEvent(m.onload)},
onload:function(){eadQJBB.start()},
addItem:function(i, p, level, submenuIndex){if(i>=this.stream.length)return i;var li=document.createElement('li');li.bClosed=true;li.bAnimated=false;li.ItemType=this.stream[i++];li.ItemText=this.stream[i++];li.title=this.stream[i++];li.title=li.title.replace(/&quot;/gi, '"');li.ItemURL=this.adjustPath(this.stream[i++]);li.URLTarget=this.stream[i++];if(li.URLTarget.substr(0,3)=='_PL'){li.func=this.stream[i++];li.params=this.stream[i++];}li.classMain=this.stream[i++];li.h=this.stream[i++];var VertInterval=this.stream[i++];//-margin       css 
li.AlwaysStayOpen=this.stream[i++];var nCountChildren=this.stream[i++];li.ItemCountChildren=nCountChildren;li.level=level;li.style.width=this.menu_width+'px';li.sel=false;if(this.show_selected){li.sel=this.isCurrent(li.ItemURL);}li.onclick=this.onclick;if(this.ExpByMouseOver){li.onmouseover=this.limover;li.onmouseout=this.limout;}var curLi=this.submenus[submenuIndex].lis.length;this.submenus[submenuIndex].lis[curLi]=li;if(nCountChildren>0){this.SubMenuId++;this.submenus[this.SubMenuId]=new this.submenu(this, li, this.SubMenuId, submenuIndex);li.ChildSubMenuId=this.SubMenuId;}else{li.ChildSubMenuId=-1;}var curSubMenuInd=this.SubMenuId;while(nCountChildren--){i=this.addItem(i, this.submenus[curSubMenuInd].ul, level+1, curSubMenuInd);}if(level==0){i=this.addItem(i, p, level, submenuIndex);}return i;},
SetAllLiHeight:function(){for(var i=0;i<this.submenus.length;i++){for(var j=0;j<this.submenus[i].lis.length;j++){this.submenus[i].lis[j].h=this.submenus[i].lis[j].offsetHeight;}}for(var i=0;i<this.submenus.length;i++){if(i>0){var d=this.submenus[i].div;d.style.height='0px';d.style.display='none';d.style.position='static';}}},
start:function(){var m=this;m.Preload();m.submenus=new Array;m.SubMenuId=0;m.submenus[m.SubMenuId]=new m.submenu(m, null, 0,-1);var streamImdex=0;m.addItem(streamImdex, m.submenus[m.SubMenuId].ul, 0, m.SubMenuId);var i=0;while(i<m.submenus.length){var ul=m.submenus[i].ul;var lis=m.submenus[i].lis;if(lis){for(var curLi=0;curLi<lis.length;curLi++){var li=lis[curLi];var h=document.createElement('div');if(li.sel){h.className=li.classMain+'_SEL';}else{h.className=li.classMain;h.classMain=li.classMain;h.classMO=li.classMain+'_MO';h.onmouseover=this.onmouseover;h.onmouseout=this.onmouseout;}h.innerHTML=li.ItemText;li.appendChild(h);if(li.ChildSubMenuId>=0){li.appendChild(m.submenus[li.ChildSubMenuId].div);}}}if(i==0){while(ul.firstChild)ul.removeChild(ul.firstChild);}for(var curLi=0;curLi<lis.length;curLi++){ul.appendChild(lis[curLi]);}i++;}m.SetAllLiHeight();if(this.hlparent){this.HLightParents();}this.ExpandAllAlwaysStayOpen();if(this.expand_selected==true){this.ExpandSelected();}},
arr:[],
save_open_speed:0,
moevent:false,
DownAfterWait:function(ind){if(this.arr[ind].SMParent.bAnimated==true){window.setTimeout('eadQJBB.DownAfterWait('+ind+')', 50);return;}this.arr[ind].SMParent.bClosed=false;ind--;if(ind>=0){eval('eadQJBB.sm'+this.arr[ind].SMParent.ChildSubMenuId+'.slidedown(0, 0, 2, 0, 0,-2)');this.DownAfterWait(ind);}else{this.arr.length=0;this.open_speed=this.save_open_speed;}},
ExpandAllAlwaysStayOpen:function(){for(var j=0;j<this.submenus[0].lis.length;j++){//top level only
if(this.submenus[0].lis[j].AlwaysStayOpen==true&&this.submenus[0].lis[j].bClosed==true){this.save_open_speed=this.open_speed;this.open_speed=1000;eval('eadQJBB.sm'+this.submenus[0].lis[j].ChildSubMenuId+'.slidedown(0, 0, 2, 0, 0,-2)');this.open_speed=this.save_open_speed;this.submenus[0].lis[j].bClosed=false;}}},
ExpandSelected:function(){for(var i=0;i<this.submenus.length;i++){for(var j=0;j<this.submenus[i].lis.length;j++){if(this.submenus[i].lis[j].sel==true&&
(i>0&&this.submenus[i].SMParent.bClosed==true||this.submenus[i].lis[j].ChildSubMenuId>=0)){if(this.submenus[i].lis[j].ChildSubMenuId>=0){this.arr.push(this.submenus[this.submenus[i].lis[j].ChildSubMenuId]);}if(this.submenus[i].lis[j].level>0){var parent=this.submenus[i];while(parent.parentSubMenuInd>-1){this.arr.push(parent);parent=this.submenus[parent.parentSubMenuInd];}}if(this.arr.length>0){this.save_open_speed=this.open_speed;if(this.expandSelAnimated==false){this.open_speed=1000;}eval('eadQJBB.sm'+this.arr[this.arr.length-1].SMParent.ChildSubMenuId+'.slidedown(0, 0, 2, 0, 0,-2)');this.DownAfterWait(this.arr.length-1);return;}}}}},
HLightParents:function(){for(var i=0;i<this.submenus.length;i++){for(var j=0;j<this.submenus[i].lis.length;j++){if(this.submenus[i].lis[j].sel==true){if(this.submenus[i].lis[j].level>0){var h;var p=this.submenus[i];while(p){if(p.SMParent){h=p.SMParent.firstChild;if(h){h.className=p.SMParent.classMain+'_SEL';h.onmouseover='';h.onmouseout='';}}if(p.parentSubMenuInd>=0)p=this.submenus[p.parentSubMenuInd];else p=null;}}}}}},
CloseAllLevels:function(level, index){for(var j=0;j<this.submenus[index].lis.length;j++){if(this.submenus[index].lis[j].bClosed==false&&
 this.submenus[index].lis[j].level==level&&
 this.submenus[index].lis[j].AlwaysStayOpen==false){eval('eadQJBB.sm'+this.submenus[index].lis[j].ChildSubMenuId+'.slideup(0, 0, 2)');this.submenus[index].lis[j].bClosed=true;}}},
GetOpened:function(level, index){for(var j=0;j<this.submenus[index].lis.length;j++){if(this.submenus[index].lis[j].bClosed==false&&
 this.submenus[index].lis[j].level==level&&
 this.submenus[index].lis[j].AlwaysStayOpen==false){return this.submenus[index].lis[j].ChildSubMenuId;}}return-1;},
IsOtherAnimated:function(SubMenuId){for(var i=0;i<this.submenus.length;i++){for(var j=0;j<this.submenus[i].lis.length;j++){if(this.submenus[i].lis[j].bAnimated==true&&this.submenus[i].lis[j].ChildSubMenuId!=SubMenuId){return true;}}}return false;},
CurLi:null,
CurLiMO:null,
DelayTimer:null,
DelayTimer2:null,
OpenURL:function(){if(eadQJBB.CurLi){if(!eadQJBB.CurLi.ItemURL)return;if(eadQJBB.CurLi.func)
eval(eadQJBB.CurLi.func+'("AcrdMenuMenu script ID:"+eadQJBB.ID,"'+'http://www.cssmenutools.com/component/content/article/98.html'+'",'+eadQJBB.CurLi.params+')');else{if(eadQJBB.CurLi.URLTarget)window.open('http://www.cssmenutools.com/component/content/article/98.html',eadQJBB.CurLi.URLTarget);else location='http://www.cssmenutools.com/component/content/article/98.html';}}},
onclick:function(evt){var e=eadQJBB.br.ie?event:evt;if(this.level&&this.level>0){if(eadQJBB.br.ie)e.cancelBubble=true;else e.stopPropagation();}eadQJBB.moclick(true,this);},
moclick:function(cli,o){if(cli==false&&eadQJBB.CurLiMO){o=eadQJBB.CurLiMO;eadQJBB.CurLiMO=null;}if(o.sel==true&&o.ChildSubMenuId<0)return;if(o.AlwaysStayOpen==true)return;if((o.sel==false)&&(o.ChildSubMenuId<0||(o.ChildSubMenuId>=0&&o.bClosed==false)||
            (o.ChildSubMenuId>=0&&o.bClosed&&eadQJBB.expand_selected==true))){if(cli==true&&!eadQJBB.OpenByMouseOver){eadQJBB.CurLi=o;eadQJBB.OpenURL();}}if(o.ChildSubMenuId>=0){if(eadQJBB.IsOtherAnimated(-1)==false){if(o.bClosed){if(eadQJBB.one_opened==true){if(eadQJBB.animation_type==1){eval('SubMenuObj=eadQJBB.sm'+o.ChildSubMenuId);if(SubMenuObj){eadQJBB.CloseAllLevels(o.level, SubMenuObj.parentSubMenuInd);}}}eval('eadQJBB.sm'+o.ChildSubMenuId+'.slidedown(0, 0, 2, 0, 0,-2)');o.bClosed=false;}else{eval('eadQJBB.sm'+o.ChildSubMenuId+'.slideup(0, 0, 2)');o.bClosed=true;}}}},
limover:function(evt){eadQJBB.CurLiMO=this;var e=eadQJBB.br.ie?event:evt;if(this.level&&this.level>0){if(eadQJBB.br.ie)e.cancelBubble=true;else e.stopPropagation();}eadQJBB.DelayTimer2=setTimeout('eadQJBB.moclick(false)', eadQJBB.DelayExpByMouseOver);},
limout:function(evt){if(eadQJBB.DelayTimer2){clearTimeout(eadQJBB.DelayTimer2);eadQJBB.DelayTimer2=null;eadQJBB.CurLiMO=null;}},
onmouseover:function(evt){this.className+=' '+this.classMO;if(eadQJBB.OpenByMouseOver){eadQJBB.CurLi=this.parentNode;eadQJBB.DelayTimer=setTimeout('eadQJBB.OpenURL()', eadQJBB.DelayOpenByMouseOver);}},
onmouseout:function(evt){this.className=this.classMain;if(eadQJBB.DelayTimer){clearTimeout(eadQJBB.DelayTimer);eadQJBB.DelayTimer=null;eadQJBB.CurLi=null;}},
getSource:function(evt, CSSClass){var e=this.br.ie?event.srcElement:evt.target;if(e&&e.className&&e.className.substr(0,CSSClass.length)==CSSClass)
return e;return;}};eadQJBB.submenu=function(m, p, index, parentSM){var f=this;f.lis=new Array;if(index==0){f.div=document.getElementById(m.ID+'Div');var uls=f.div.getElementsByTagName('ul');if(uls[0]){f.ul=uls[0];}}else{f.div=document.createElement('div');f.div.style.margin='0px';f.div.style.padding='0px';f.div.style.overflow='hidden';f.div.style.border='0px';f.div.style.position='absolute';f.div.style.top='-1000px';f.div.style.left='0px';p.Child=f;f.ul=document.createElement('ul');f.ul.style.margin='-10000px 0px 0px';f.ul.style.width=m.menu_width+'px';f.div.appendChild(f.ul);}f.parentSubMenuInd=parentSM;f.SubMenuInd=index;f.m=m;f.SMParent=p;f.obj='eadQJBB.sm'+index;f.ParentNeedUp=0;eval(f.obj+'=f');};eadQJBB.submenu.prototype={GetChildSize:function(iSize, iSubMenuId){with(this){for(var i=0;i<m.submenus.length;i++){if(m.submenus[i].parentSubMenuInd==iSubMenuId){if(m.submenus[i].SMParent&&m.submenus[i].SMParent.bClosed==false){for(j=0;j<m.submenus[i].lis.length;j++){iSize+=m.submenus[i].lis[j].h;}iSize=GetChildSize(iSize, m.submenus[i].SubMenuInd);}}}}return iSize;},
slidedown:function(b, endSize, bRecurs, bUp, endSizeUp, Id){with(this){if(m.one_opened==true&&m.animation_type==1){if(bRecurs==2&&m.IsOtherAnimated(SubMenuInd)){window.setTimeout(obj+'.slidedown(0, 0, 2, 0, 0,-1)', 20);return;}}if(bRecurs==2){SMParent.bAnimated=true;}if(m.one_opened==true){if(m.animation_type==0&&bUp>=0){if(Id==-2){Id=m.GetOpened(SMParent.level, parentSubMenuInd);}if(Id>=0&&bUp<=endSizeUp){eval('var valUp=m.sm'+Id+'.slideup(bUp, endSizeUp, 1)');bUp=valUp.b;endSizeUp=valUp.endSize;}}}if(endSize==0){for(i=0;i<lis.length;i++){endSize+=lis[i].h;}endSize=GetChildSize(endSize, SubMenuInd);}if(b<0)b=0;if(b==0){div.style.display='block';}var addb=m.open_speed;if(bRecurs==2){b+=m.open_speed;if((b>endSize)&&(b-endSize)<m.open_speed)b=endSize;}if(ul.style.marginTop!='0px'){ul.style.marginTop=b-endSize+'px';}if(b>0){if(b==endSize){var mod1=endSize % addb;if(mod1!=0)addb=mod1;}div.style.height=div.clientHeight+addb+'px';}if(parentSubMenuInd>0){eval(m.submenus[parentSubMenuInd].obj+'.slidedown('+ b+','+endSize+',0,-1,-1,-1)');}if(bRecurs==0&&bUp==-1&&endSizeUp==-1&&Id==-1&&b>=endSize)
ParentNeedUp++;if(bRecurs==2&&b>=endSize){ParentNeedUp++;SMParent.bAnimated=false;if(m.one_opened==true){if(m.animation_type==0&&bUp>=0){if(Id>=0&&bUp<endSizeUp){eval('m.sm'+Id+'.slideup(bUp, endSizeUp, 2)');}}}return;}if(bRecurs==2){if(b<endSize){window.setTimeout(obj+'.slidedown('+b+','+endSize+',2,'+bUp+','+endSizeUp+','+Id+')', 20);}}}},
slideup:function(b, endSize, bRecurs){with(this){if(bRecurs==2){SMParent.bAnimated=true;}if(endSize==0){endSize=div.clientHeight;}if(b<0)b=0;if(bRecurs>0){if(b<endSize)
b+=m.open_speed;if((b>endSize)&&(b-endSize)<=m.open_speed)b=endSize;}if(bRecurs>0){ul.style.marginTop=-b+'px';}var addb=m.open_speed;if(b>0){if(b==endSize){var mod1=endSize % addb;if(mod1!=0)addb=mod1;}var divh=div.clientHeight-addb;if(divh<0)divh=0;div.style.height=divh+'px';}if(bRecurs>0&&b==endSize){div.style.display='none';}if(parentSubMenuInd>0){if(b<=endSize){if(ParentNeedUp>0){eval(m.submenus[parentSubMenuInd].obj+'.slideup('+ b+','+endSize+',0)');if(b==endSize){ParentNeedUp--;}}}}if(b<endSize){if(bRecurs==2){window.setTimeout(obj+'.slideup('+b+','+endSize+',2)', 20);}}else{SMParent.bAnimated=false;if(bRecurs>0){SMParent.bClosed=true;}}}return{b:b, endSize:endSize};},
dummy:null};eadQJBB.init();