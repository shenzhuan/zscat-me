
function page(formId,pageNo){
	var $form = $("#"+formId);
	var pageNoInput = $form.find('input[name="pageNum"]');
	var pageSize = $form.find('input[name="pageSize"]');
	if(pageNoInput.size() == 0){
		$form.append("<input type='hidden'  name = 'pageNum' value='1'/>");
		pageNoInput = $form.find('input[name="pageNum"]');
	}
	if(pageSize.size() == 0){
		$form.append("<input type='hidden'  name = 'pageSize' value='10'/>");
	}
	pageNoInput.val(pageNo);
	$form.ajaxSubmit($form.data("ajaxOptions"));
	return false;
};


(function($){
	
	$.fn.ajaxForms = function(settings){
		return this.each(function(){
			this.opt = $.extend({}, $.fn.ajaxForms.defaults, settings);
			
			var $this = $(this),self = this;
			
			if(self.opt.btnId === undefined ){
				if($("#"+$this.attr("id")+"-btn").size() == 0){
					$this.after("<button style='display:none;' id='"+$this.attr("id")+"-btn'></button>");
				}
				self.opt.btnId = "#"+$this.attr("id")+"-btn";
			}
			if(self.opt.beforeSubmit === undefined){
				self.opt.beforeSubmit = function(formData, jqForm, options){
					if(self.opt.spinner === undefined){
						self.opt.spinner = new Spinner({color: '#3d9bce',width:20,radius:20}).spin($(self.opt.target)[0]);
					}
					var queryString = $.param(formData);
					return true;
				};
			}
			if(self.opt.success === undefined){
				self.opt.success = function(data, statusText, xhr, $form){
					$(self.opt.target).html(data);
					if (self.opt.spinner) {
						(self.opt.spinner).stop();
					}
				};
			}
			
			if( undefined == self.opt.url){
				self.opt.url = $this.attr("action");
			}
			
			$this.data("ajaxOptions",self.opt); //设置ajaxOptions
			//阻止默认的表单提交
			$(document).bind("submit",function(){return false;});
			
			$(document).off("click",self.opt.btnId).on("click",self.opt.btnId,function(){
				if(self.opt.btnClickFn != undefined){
					var flag = self.opt.btnClickFn();
					if(flag == false) return false; 
				}
				if(self.opt.isPage){
					page($this.attr("id"),1);
				}else{
					$this.ajaxSubmit($this.data("ajaxOptions"));
				}
				return false;
			});
			
			if(self.opt.trigger){
				$(self.opt.btnId).trigger("click");
			}
		});
	};
	
	$.fn.formReload = function(){
		page($(this).attr("id"),1);
	};
	
	$.fn.ajaxForms.defaults={
		target:null,  //要填充到的元素(必须元素),格式:"#target"
		dataType:"html",  //预定义返回的类型
		type:"post", // 重新设置 form 表单的 method 属性值：'get' or 'post'
		url:undefined, // 重新指定表单的提交地址
		clearForm:false,  //提交成功后清空表单域
		resetForm:false,  //提交成功后重置表单域
		beforeSubmit:$.noop(),  //ajax提交表单前的操作
		success:$.noop(), //成功后的回调,不设置默认回填html 参数 (data, statusText, xhr, $form)
		btnId:undefined,  //触发提交表单的按钮,如不设置默认添加一个btn提交,格式: "#btn"
		trigger:true, //是否立即触发提交，默认是
		spinner:undefined, //loading样式
		btnClickFn:$.noop(),  //按钮提交切入自定义函数
		isPage:true,  //默认是分页模式
	};
	
})(jQuery);

