/**
 * jQuery EasyUI 1.2.6
 *
 * Licensed under the GPL terms
 * To use it on other terms please contact us
 *
 * Copyright(c) 2009-2012 stworthy [ stworthy@gmail.com ]
 *
 */
(function($) {
	var resizable = false;
	$.fn.resizable = function(method, options) {
		if (typeof method == "string") {
			return $.fn.resizable.methods[method](this, options);
		}
		function resize(e) {
			var data = e.data;
			var opts = $.data(data.target, "resizable").options;
			if (data.dir.indexOf("e") != -1) {
				var width = data.startWidth + e.pageX - data.startX;
				width = Math.min(Math.max(width, opts.minWidth), opts.maxWidth);
				data.width = width;
			}
			if (data.dir.indexOf("s") != -1) {
				var height = data.startHeight + e.pageY - data.startY;
				height = Math.min(Math.max(height, opts.minHeight), opts.maxHeight);
				data.height = height;
			}
			if (data.dir.indexOf("w") != -1) {
				data.width = data.startWidth - e.pageX + data.startX;
				if (data.width >= opts.minWidth && data.width <= opts.maxWidth) {
					data.left = data.startLeft + e.pageX - data.startX;
				}
			}
			if (data.dir.indexOf("n") != -1) {
				data.height = data.startHeight - e.pageY + data.startY;
				if (data.height >= opts.minHeight && data.height <= opts.maxHeight) {
					data.top = data.startTop + e.pageY - data.startY;
				}
			}
		};
		function applySize(e) {
			var data = e.data;
			var opts = data.target;
			if (!$.boxModel && $.browser.msie) {
				$(opts).css({
					width: data.width,
					height: data.height,
					left: data.left,
					top: data.top
				});
			} else {
				$(opts).css({
					width: data.width - data.deltaWidth,
					height: data.height - data.deltaHeight,
					left: data.left,
					top: data.top
				});
			}
		};
		function doDown(e) {
			resizable = true;
			$.data(e.data.target, "resizable").options.onStartResize.call(e.data.target, e);
			return false;
		};
		function doMove(e) {
			resize(e);
			if ($.data(e.data.target, "resizable").options.onResize.call(e.data.target, e) != false) {
				applySize(e);
			}
			return false;
		};
		function doUp(e) {
			resizable = false;
			resize(e, true);
			applySize(e);
			$.data(e.data.target, "resizable").options.onStopResize.call(e.data.target, e);
			$(document).unbind(".resizable");
			$("body").css("cursor", "auto");
			return false;
		};
		return this.each(function() {
			var opts = null;
			var resizableData = $.data(this, "resizable");
			if (resizableData) {
				$(this).unbind(".resizable");
				opts = $.extend(resizableData.options, method || {});
			} else {
				opts = $.extend({}, $.fn.resizable.defaults, method || {});
				$.data(this, "resizable", {
					options: opts
				});
			}
			if (opts.disabled == true) {
				return;
			}
			$(this).bind("mousemove.resizable", {target: this}, function(e) {
				if (resizable) {
					return;
				}
				var dir = getDirection(e);
				if (dir == "") {
					$(e.data.target).css("cursor", "");
				} else {
					$(e.data.target).css("cursor", dir + "-resize");
				}
			}).bind("mousedown.resizable", {target: this}, function(e) {
				var dir = getDirection(e);
				if (dir == "") {
					return;
				}
				function getCssValue(css) {
					var val = parseInt($(e.data.target).css(css));
					if (isNaN(val)) {
						return 0;
					} else {
						return val;
					}
				};
				var data = {
					target: e.data.target,
					dir: dir,
					startLeft: getCssValue("left"),
					startTop: getCssValue("top"),
					left: getCssValue("left"),
					top: getCssValue("top"),
					startX: e.pageX,
					startY: e.pageY,
					startWidth: $(e.data.target).outerWidth(),
					startHeight: $(e.data.target).outerHeight(),
					width: $(e.data.target).outerWidth(),
					height: $(e.data.target).outerHeight(),
					deltaWidth: $(e.data.target).outerWidth() - $(e.data.target).width(),
					deltaHeight: $(e.data.target).outerHeight() - $(e.data.target).height()
				};
				$(document).bind("mousedown.resizable", data, doDown);
				$(document).bind("mousemove.resizable", data, doMove);
				$(document).bind("mouseup.resizable", data, doUp);
				$("body").css("cursor", dir + "-resize");
			}).bind("mouseleave.resizable", {target: this}, function(e) {
				$(e.data.target).css("cursor", "");
			});
			function getDirection(e) {
				var tt = $(e.data.target);
				var dir = "";
				var offset = tt.offset();
				var width = tt.outerWidth();
				var height = tt.outerHeight();
				var edge = opts.edge;
				if (e.pageY > offset.top && e.pageY < offset.top + edge) {
					dir += "n";
				} else {
					if (e.pageY < offset.top + height && e.pageY > offset.top + height - edge) {
						dir += "s";
					}
				}
				if (e.pageX > offset.left && e.pageX < offset.left + edge) {
					dir += "w";
				} else {
					if (e.pageX < offset.left + width && e.pageX > offset.left + width - edge) {
						dir += "e";
					}
				}
				var handles = opts.handles.split(",");
				for (var i = 0; i < handles.length; i++) {
					var handle = handles[i].replace(/(^\s*)|(\s*$)/g, "");
					if (handle == "all" || handle == dir) {
						return dir;
					}
				}
				return "";
			};
		});
	};
	$.fn.resizable.methods = {
		options: function(jq) {
			return $.data(jq[0], "resizable").options;
		},
		enable: function(jq) {
			return jq.each(function() {
				$(this).resizable({
					disabled: false
				});
			});
		},
		disable: function(jq) {
			return jq.each(function() {
				$(this).resizable({
					disabled: true
				});
			});
		}
	};
	$.fn.resizable.defaults = {
		disabled: false,
		handles: "n, e, s, w, ne, se, sw, nw, all",
		minWidth: 200,
		minHeight: 100,
		maxWidth: 10000,
		maxHeight: 10000,
		edge: 5,
		onStartResize: function(e) {},
		onResize: function(e) {},
		onStopResize: function(e) {}
	};
})(jQuery);