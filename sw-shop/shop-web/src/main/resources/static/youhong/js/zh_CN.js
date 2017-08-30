/**
 * Let's Kill IE6 (http://www.neoease.com/lets-kill-ie6/)
 * @version 2.1
 * @author MG12 (email: wuzhao.mail@gmail.com)
 * @date 2012-04-25
 */

(function() {

LetsKillIE6 = function() {

	this.config = null;

	this.cache = {
		dialog			:null,
		showThread		:null,
		hideThread		:null,
		triggerThread	:null,
		isDispose		:false,
		opacity			:0,
		dialogHeight	:0
	};
};

LetsKillIE6.prototype = {

	init: function(config) {
		this.config = config || this.config;
		var _self = this;

		if(_self._getCookie(_self.config.targetId) != _self.config.targetId) {	
			var pageUrl = window.location.href;
			var protocol = pageUrl.replace(/\/\/.*$/, '');

			_self._loadCss(protocol + _self.config.cssUrl, _self, function(){
				var dialog = document.createElement('div');
				dialog.id = _self.config.targetId;
				dialog.innerHTML = _self.config.html;
				document.body.appendChild(dialog);

				_self.cache.dialog = dialog;
				_self._show({_self:_self});

				window.onscroll = function(){
					if(!_self.cache.isDispose) {
						_self._reset({_self:_self});
					}
				};

				var closeButton = document.getElementById('letskillie6-close');
				closeButton.onclick = function(){
					_self.cache.isDispose = true;
					_self._hide({_self:_self});
					_self._setCookie(_self.config.targetId, _self.config.targetId, _self.config.delay);
					return false;
				};

				var links = dialog.getElementsByTagName('a');
				for(var i=0, len=links.length; i<len; i++) {
					var link = links[i];
					if(links[i].id != 'letskillie6-close') {
						link.onclick = function(ev){
							_self._popup(this);
							return false;
						};
					}
				}
			});
		}
	},

	_popup: function(link) {
		window.open(link.href);
	},

	_reset: function(args) {
		var _self = args._self;

		_self.cache.dialog.style.display = 'none';

		clearTimeout(_self.cache.triggerThread);
		_self.cache.triggerThread = setTimeout(function () {
			clearTimeout(_self.cache.triggerThread);
			_self._show({_self:_self});
		}, 400);
	},

	_show: function(args) {
		var _self = args._self;
		var dialog = _self.cache.dialog;

		if(_self.cache.dialogHeight == 0) {
			_self.cache.dialogHeight = dialog.offsetHeight;
		}
		var height = _self._getScrollY() + document.documentElement.clientHeight - _self.cache.dialogHeight - 10;

		_self.cache.opacity = 0;
		_self._setOpacity({_self:_self, element:dialog, opacity:_self.cache.opacity});
		dialog.style.top = height + 'px';
		dialog.style.visibility = 'visible';
		dialog.style.display = 'inline';

		_self.cache.showThread = setInterval(function(){_self._fadeIn({_self:_self});}, 40);
	},

	_hide: function(args) {
		var _self = args._self;
		var dialog = _self.cache.dialog;
		_self.cache.hideThread = setInterval(function(){_self._fadeOut({_self:_self});}, 40);
	},

	_fadeIn: function(args) {
		var _self = args._self;
		var dialog = _self.cache.dialog;

		_self.cache.opacity += 5;
		if(_self.cache.opacity >= 100) {
			clearTimeout(_self.cache.showThread);
			_self.cache.opacity = 100;
		}
		_self._setOpacity({_self:_self, element:dialog, opacity:_self.cache.opacity});
	},

	_fadeOut: function(args) {
		var _self = args._self;
		var dialog = _self.cache.dialog;

		_self.cache.opacity -= 10;
		if(_self.cache.opacity <= 0) {
			clearTimeout(_self.cache.hideThread);
			_self.cache.opacity = 0;
			dialog.style.display = 'none';
		}
		_self._setOpacity({_self:_self, element:dialog, opacity:_self.cache.opacity});
	},

	_loadCss: function(url, _self, fn) {
		var head = document.getElementsByTagName('head')[0];
		var node = document.createElement('link');
		node.type = 'text/css';
		node.rel = 'stylesheet';
		node.href = url;
		node.media = 'screen';

		_self._styleOnload({node:node, callback:fn, _self:_self});

		head.appendChild(node);
	},

	_setOpacity: function(args) {
		var _self = args._self;
		var element = args.element;
		var opacity = args.opacity;

		if (/MSIE 6/i.test(navigator.userAgent)) {
			element.style.filter = 'alpha(opacity=' + _self.cache.opacity + ')';
		} else {
			element.style.opacity = _self.cache.opacity / 100;
		}
	},

	_setCookie: function(name, value, day) {
		if(typeof LETSKILLIE6_DELAY != 'undefined' && LETSKILLIE6_DELAY != null) {
			day = LETSKILLIE6_DELAY;
		}
		if (value === null) {
			value = '';
		}
		var expires = '';

		date = new Date();
		date.setTime(date.getTime() + (day * 86400000));
		expires = '; expires=' + date.toUTCString();

		var path = '';
		var domain = '';
		var secure = '';
		document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	},

	_getCookie: function(name) {
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookie = cookies[i];
				while(cookie.charAt(0) == ' ') {
					cookie = cookie.substring(1, cookie.length);
				}
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	},

	_getScrollY: function() {
		if(typeof window.pageYOffset != 'undefined') {
			return window.pageYOffset;
		}

		if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
			return document.documentElement.scrollTop;
		}

		return document.body.scrollTop;
	},

	_styleOnload: function(args) {
		var _self = args._self;
		var node = args.node;
		var callback = args.callback;

		if (node.attachEvent) {
			node.attachEvent('onload', callback);
		} else {
			setTimeout(function() {
				_self._poll({node:node, callback:callback, _self:_self});
			}, 0);
		}
	},

	_poll: function(args) {
		var _self = args._self;
		var node = args.node;
		var callback = args.callback;

		if (callback.isCalled) {
			return;
		}

		var isLoaded = false;

		if (/webkit/i.test(navigator.userAgent)) {
			if (node['sheet']) {
				isLoaded = true;
			}
		} else if (node['sheet']) {
			try {
				if (node['sheet'].cssRules) {
					isLoaded = true;
				}
			} catch (ex) {
				if (ex.code === 1000) {
					isLoaded = true;
				}
			}
		}

		if (isLoaded) {
			setTimeout(function() {
				callback();
			}, 1);
		} else {
			setTimeout(function() {
				_self._poll({node:node, callback:callback, _self:_self});
			}, 1);
		}
	}

};

(new LetsKillIE6()).init({
	delay		:30,
	targetId	:'letskillie6',
	cssUrl		:'//letskillie6.googlecode.com/svn/trunk/2/zh.css?v=091403',
	html		:'<div class="letskillie6-r4"></div><div class="letskillie6-r2"></div><div class="letskillie6-r1"></div><div class="letskillie6-r1"></div><div class="letskillie6-content"><a rel="nofollow" id="letskillie6-close" href="javascript:;"></a><span class="letskillie6-pic"></span><div class="letskillie6-desc">' + 
				'您正在使用 Internet Explorer 6 浏览网页，如果您 <strong>升级到 Internet Explorer 8</strong> 或 <strong>转换到另一款浏览器</strong>，可以获得更好的网站浏览体验。' +
				'</div><div style="clear:both;"></div><div class="letskillie6-browsers"><a rel="nofollow" class="letskillie6-ie8" href="http://www.microsoft.com/windows/internet-explorer/">IE 8</a><a rel="nofollow" class="letskillie6-firefox" href="http://www.mozilla.com/">Firefox</a><a rel="nofollow" class="letskillie6-chrome" href="http://www.google.com/chrome/">Chrome</a><a rel="nofollow" class="letskillie6-opera" href="http://www.opera.com/">Opera</a><div style="clear:both;"></div></div><div class="letskillie6-meta">Let\'s kill IE 6, <a href="http://www.neoease.com/lets-kill-ie6/">insert on your website</a>.</div></div><div class="letskillie6-r1"></div><div class="letskillie6-r1"></div><div class="letskillie6-r2"></div><div class="letskillie6-r4"></div>'
});

})();