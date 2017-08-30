(function ($) {
	
	/**
	 * 展示流程图
	 */
	$.fn.initFlowChart = function(pid){
		var $this = $(this);
		var imageUrl = ctxPath + "/workflow/process/trace/auto/"+pid;
//	    var imageUrl = ctx + "/workflow/resource/process-instance?pid=" + pid + "&type=image";
	    $this.find(".portlet-body").html($("<img src='" + imageUrl + "'/>"));
	};
	
	$.fn.showFlowChart = function (pid){
		var $this = $(this);
	//	blockUI();
		$this.initFlowChart(pid);
		$this.find(".portlet-body img").load(function(){
			$this.show().center().move();
		//	unBlockUI();
		});
	};
	
})(jQuery);
/**
 * 遮罩层函数
 */
function blockUI() {
	$(".page-content").block({
		message : '<img src="' + ctxPath + '/assets/img/loading.gif">',
		css : {
			border: 'none',
            padding: '2px',
            backgroundColor: 'none'
		},
		overlayCSS : {
			backgroundColor: '#000',
            opacity: 0.05,
            cursor: 'wait'
		}
	});
}
