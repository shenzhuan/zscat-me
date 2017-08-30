// JavaScript Document
var jq = jQuery.noConflict();
jq.fn.extend({
	jselect:function(){
		return jq(this).each(function(){
			var width = this.style.width;//因为ff取不到auto
			jq(this).after("<input type=\"hidden\" /><div class='jslct'><div class='jslct_t'><em></em></div><dl></dl></div>");
			var ipt = jq(this).next("input");
			var lst = ipt.next("div");
			var itms = jq("dl",lst);
			var itm;
			var opt = jq("option",jq(this));
			var opts = jq("option:selected",jq(this));
			var opts_index = opt.index(opts);
			var em = jq("em",lst);
			var fn_change=jq(this).attr("onchange");
			if(width!=""){lst.css("width",jq(this).css("width"));em.css("width","100%");};
			ipt.attr("name",jq(this).attr("name"));
			em.text(jq("option:selected",jq(this)).text());
			opt.each(function(i){
				itms.append("<dd></dd>");
				itm = jq("dd",itms);
				var dd = itm.eq(i);
				dd.attr("val",jq(this).val()).text(jq(this).text());
			});
			itm.eq(0).addClass("noborder")
			itm.eq(opts_index).addClass("jslcted");
			jq(this).remove();
			lst.hover(function(){jq(this).addClass("jslct_hover");return false},function(){jq(this).removeClass("jslct_hover");return false});
			itm.hover(function(){jq(this).addClass("hover")},function(){jq(this).removeClass("hover")});
			itm.width(itms.width()+17);
			itms.css({width:itms.width(),"overflow-x":"hidden","overflow-y":"auto"});
			lst.click(function(){lstshow();});
			jq(document).mouseup(function(){lsthide();});
			itm.click(function(){
							   if(fn_change!=null){(eval(fn_change))();}
							   itm.removeClass("jslcted");
							   jq(this).addClass("jslcted");
							   em.text(jq(this).text());
							   ipt.val(jq(this).attr("val"));
							   lsthide();
							   return false;
							   })
			function lstshow(){
					var maxheight =jq(document).height()-200;
					itms.css({height:"auto"});
					maxheight = itms.height()>maxheight?maxheight:"auto";
					itms.css({height:maxheight});
					itms.show();
					lst.css("z-index","1000")
			};
			function lsthide(){
					jq(".jslct dl").hide();
					jq(".jslct").css("z-index","0")
			};
		});	
	}						
});
 
jq(function(){
	jq(".jj").jselect();
});
