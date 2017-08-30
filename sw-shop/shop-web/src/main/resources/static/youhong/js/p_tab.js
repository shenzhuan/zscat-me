// JavaScript Document

var jq = jQuery.noConflict();

jQuery(function(){
//优势页面点击子导航
	var subNav_active = jq(".adv_active");
	var subNav_scroll = function(target){
		subNav_active.removeClass	("adv_active");
		target.parent().addClass("adv_active");
		subNav_active = target.parent();
	};
	jq("#subNav a").click(function(){
		subNav_scroll(jq(this));
		var target = jq(this).attr("href");
		var targetScroll = jq(target).offset().top - 80;
		jq("html,body").animate({scrollTop:targetScroll},300);
		return false;
	});
	//页面跳转时定位
	if(window.location.hash){
		var targetScroll = jq(window.location.hash).offset().top - 80;
		jq("html,body").animate({scrollTop:targetScroll},300);
	}
	jQuery(window).scroll(function(){
		var jqthis = jq(this);
		var targetTop = jq(this).scrollTop();
		var footerTop = jq("#footer").offset().top;
		var height = jq(window).height();
		
		if (targetTop >= 520){
			jq("#subNav").addClass("fixedSubNav");
			jq(".empty-placeholder").removeClass("hidden");
		}else{
			jq("#subNav").removeClass("fixedSubNav");
			jq(".empty-placeholder").addClass("hidden");
		}
		if(targetTop < 750){
			subNav_scroll(jq(".adv_door"));
		}else if(targetTop > 1200 && targetTop < 1640){
				subNav_scroll(jq(".adv_source"));
		}else if(targetTop > 2314 && targetTop < 2734){
				subNav_scroll(jq(".adv_price"));
		}else if(targetTop > 2968 && targetTop < 3268){
				subNav_scroll(jq(".adv_transfer"));
		}else if(targetTop > 3327 && targetTop < 3627){
				subNav_scroll(jq(".adv_payment"));
		}else if(targetTop > 3651 && targetTop < 4071){
				subNav_scroll(jq(".adv_promise"));
		}else if(targetTop > 4163 && targetTop < 4473){
				subNav_scroll(jq(".adv_ride"));
		}else if(targetTop > 4580){
			subNav_scroll(jq(".adv_finance"));
		}
	})
	
}());