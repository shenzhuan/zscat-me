(function(e){function l(){return f==true?false:window.DeviceOrientationEvent!=undefined}
function c(e){x=e.gamma;y=e.beta;if(Math.abs(window.orientation)===90){var t=x;x=y;y=t}
if(window.orientation<0){x=-x;y=-y}
u=u==null?x:u;a=a==null?y:a;return{x:x-u,y:y-a}}
function h(e){if((new Date).getTime()<r+n)return;r=(new Date).getTime();var t=s.offset()!=null?s.offset().left:0,u=s.offset()!=null?s.offset().top:0,a=e.pageX-t,h=e.pageY-u;if(a<0||a>s.width()||h<0||h>s.height())return;if(l()){if(e.gamma==undefined){f=true;return}
values=c(e);a=values.x/30;h=values.y/30}
var p=a/(l()==true?o:s.width()),d=h/(l()==true?o:s.height()),v,m;for(m=i.length;m--;){v=i[m];newX=v.startX+v.inversionFactor*v.xRange*p;newY=v.startY+v.inversionFactor*v.yRange*d;if(v.background){v.obj.css("background-position",newX+"px "+newY+"px")}else{v.obj.css("left",newX).css("top",newY)}}}
var t=25,n=1/t*1e3,r=(new Date).getTime(),i=[],s=e(window),o=1,u=null,a=null,f=false;e.fn.plaxify=function(t){return this.each(function(){var n=-1;var r={xRange:e(this).data("xrange")||0,yRange:e(this).data("yrange")||0,invert:e(this).data("invert")||false,background:e(this).data("background")||false};for(var s=0;s<i.length;s++){if(this===i[s].obj.get(0)){n=s}}
for(var o in t){if(r[o]==0){r[o]=t[o]}}
r.inversionFactor=r.invert?-1:1;r.obj=e(this);if(r.background){pos=(r.obj.css("background-position")||"0px 0px").split(/ /);if(pos.length!=2){return}
x=pos[0].match(/^((-?\d+)\s*px|0+\s*%|left)$/);y=pos[1].match(/^((-?\d+)\s*px|0+\s*%|top)$/);if(!x||!y){return}
r.startX=x[2]||0;r.startY=y[2]||0}else{var u=r.obj.position();r.obj.css({top:u.top,left:u.left,right:"",bottom:""});r.startX=this.offsetLeft;r.startY=this.offsetTop}
r.startX-=r.inversionFactor*Math.floor(r.xRange/2);r.startY-=r.inversionFactor*Math.floor(r.yRange/2);if(n>=0){i.splice(n,1,r)}else{i.push(r)}})};e.plax={enable:function(t){e(document).bind("mousemove.plax",function(n){if(t){s=t.activityTarget||e(window)}
h(n)});if(l()){window.ondeviceorientation=function(e){h(e)}}},disable:function(t){e(document).unbind("mousemove.plax");window.ondeviceorientation=undefined;if(t&&typeof t.clearLayers==="boolean"&&t.clearLayers)i=[]}};if(typeof ender!=="undefined"){e.ender(e.fn,true)}})(function(){return typeof jQuery!=="undefined"?jQuery:ender}());