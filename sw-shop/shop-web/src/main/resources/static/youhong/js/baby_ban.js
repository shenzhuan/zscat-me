// JavaScript Document

var jq = jQuery.noConflict();
jQuery(document).ready(function() {
	jq('a[href="#"]').each(function() {
		jq(this).attr('href', 'javascript:void(0)')
	});
	jq('.perform li').each(function() {
		var o = jq(this);
		jq(this).find('.s').click(function() {
			var j = jq(this).index();
			o.find('.s').removeClass('on').eq(j).addClass('on');
			o.find('.info').hide().eq(j).fadeIn(500)
		})
	});
	jq('.artist_l li').each(function(m) {
		jq(this).find('a').css('top', -jq(this).height());
		jq(this).hover(function() {
			jq(this).find('a').animate({
				'top': '0'
			},
			200)
		},
		function() {
			jq(this).find('a').animate({
				'top': jq(this).height()
			},
			{
				duration: 200,
				complete: function() {
					jq(this).css('top', -jq(this).parent('li').height())
				}
			})
		})
	});
	jq('#calendar td').live('mouseover',
	function() {
		jq('#calendar td').removeClass('hover');
		jq(this).addClass('hover')
	});
	jq('.category_list .item').each(function(i) {
		jq(this).hover(function() {
			jq('.category_list .item').removeClass('on').eq(i).addClass('on');
			jq('.category_list ol').hide().eq(i).show()
		},
		function() {
			jq(this).find('ol').hide();
			jq(this).removeClass('on')
		})
	});
	jq('.u_city_a li').each(function(i) {
		jq(this).click(function() {
			if (i == 10) return false;
			jq('.u_city_nav li').removeClass('on').eq(i).addClass('on');
			jq('.u_city_nav p').hide().eq(i).show()
		})
	});
	var intIndexCity = 0;
	var intHoverCity = 0;
	jq('.u_city_nav .more').click(function() {
		if (intIndexCity == 1) {
			jq(this).removeClass('on');
			jq('.s_citys').hide(200);
			intIndexCity = 0
		} else {
			jq(this).addClass('on');
			jq('.s_citys').show(200);
			intIndexCity = 1
		}
		return false
	});
	jq('.s_citys').hover(function() {
		intHoverCity = 1
	},
	function() {
		intHoverCity = 0
	});
	jq('body').bind('click',
	function() {
		if (intIndexCity == 1 && intHoverCity == 0) {
			jq('.s_citys').hide(200);
			jq('.u_city_nav .more').removeClass('on');
			intIndexCity = 0
		}
	});
	function scrollList() {
		if (jq('.scroll_txt li').length <= 1) return;
		var temp = jq('.scroll_txt li:last');
		temp.hide();
		jq('.scroll_txt li:last').remove();
		jq('.scroll_txt li:first').before(temp);
		temp.slideDown(500)
	}
	window.setInterval(scrollList, 5000);
	jq('.live_top li').each(function(i) {
		jq(this).hover(function() {
			jq('.live_top li').removeClass('on').eq(i).addClass('on')
		})
	});
	jq('.list_1 li').each(function(i) {
		jq(this).hover(function() {
			jq('.list_1 li').removeClass('on').eq(i).addClass('on')
		})
	});
	jq('.vote_m dd').each(function(i) {
		jq(this).click(function() {
			jq('.vote_m dd').removeClass('on').eq(i).addClass('on')
		})
	});
	jq('.tr_commend dl').each(function(i) {
		jq(this).hover(function() {
			jq('.tr_commend dl').removeClass('on').eq(i).addClass('on')
		})
	});
	jq('.ticketInfo .help').hover(function() {
		jq('.minTips').fadeIn('fast')
	},
	function() {
		jq('.minTips').fadeOut('fast')
	});
	jq('.videoList li').hover(function() {
		jq(this).addClass('on')
	},
	function() {
		jq(this).removeClass('on')
	});
	jq('.min_tip .tab_min_b a').each(function(i) {
		jq(this).click(function() {
			jq('.tab_min_b a').removeClass('on').eq(i).addClass('on')
		})
	});
	jq('.news_list li').hover(function() {
		jq(this).addClass('on')
	},
	function() {
		jq(this).removeClass('on')
	});
	jq('.tr_pic_list li').hover(function() {
		jq(this).addClass('on')
	},
	function() {
		jq(this).removeClass('on')
	});
	jq('.sift .expand').toggle(function() {
		jq('#city').height(24);
		jq(this).html('\u5c55\u5f00')
	},
	function() {
		jq('#city').height('auto');
		jq(this).html('\u6536\u7f29')
	});
	jq('.buy_caption .tab_t a').each(function(i) {
		jq(this).click(function() {
			jq('.buy_caption .tab_t a').removeClass('on').eq(i).addClass('on');
			jq('.buy_caption dl').hide().eq(i).show()
		})
	});
	jq('.vocal_list li .t .c7').click(function() {
		jq(this).parent().parent().find('.t').show();
		jq(this).parent().hide()
	})
});
jQuery(document).ready(function() {
	var t = false;
	var str = '';
	var speed = 500;
	var w = 228;
	var n = jq('#actorba li').length;
	var numWidth = n * 18;
	var _left = (w - (numWidth + 26)) / 2;
	var c = 0;
	jq('#actorba').width(w * n);
	jq('#actorba li').each(function(i) {
		str += '<span></span>'
	});
	jq('#numInner').width(numWidth).html(str);
	jq('#imgPlayba .mc').width(numWidth);
	jq('#imgPlayba .num').css('left', _left);
	jq('#numInner').css('left', _left + 13);
	jq('#numInner span:first').addClass('on');
	function cur(ele, currentClass) {
		ele = jq(ele) ? jq(ele) : ele;
		ele.addClass(currentClass).siblings().removeClass(currentClass)
	}
	jq('.bb_ban #imgPlayba .nextba').click(function() {
		slide(1)
	});
	jq('.bb_ban #imgPlayba .prevba').click(function() {
		slide( - 1)
	});
	function slide(j) {
		if (jq('#actorba').is(':animated') == false) {
			c += j;
			if (c != -1 && c != n) {
				jq('#actorba').animate({
					'marginLeft': -c * w + 'px'
				},
				speed)
			} else if (c == -1) {
				c = n - 1;
				jq("#actorba").css({
					"marginLeft": -(w * (c - 1)) + "px"
				});
				jq("#actorba").animate({
					"marginLeft": -(w * c) + "px"
				},
				speed)
			} else if (c == n) {
				c = 0;
				jq("#actorba").css({
					"marginLeft": -w + "px"
				});
				jq("#actorba").animate({
					"marginLeft": 0 + "px"
				},
				speed)
			}
			cur(jq('#numInner span').eq(c), 'on')
		}
	}
	jq('#numInner span').click(function() {
		c = jq(this).index();
		fade(c);
		cur(jq('#numInner span').eq(c), 'on')
	});
	function fade(i) {
		if (jq('#actorba').css('marginLeft') != -i * w + 'px') {
			jq('#actorba').css('marginLeft', -i * w + 'px');
			jq('#actorba').fadeOut(0,
			function() {
				jq('#actorba').fadeIn(500)
			})
		}
	}
	function start() {
		t = setInterval(function() {
			slide(1)
		},
		5000)
	}
	function stopt() {
		if (t) clearInterval(t)
	}
	jq("#imgPlayba").hover(function() {
		stopt()
	},
	function() {
		start()
	});
	start()
});
jQuery(document).ready(function() {
	var isshowcity = false;
	var ishovercitys = false;
	jq('.s_city .s').click(function() {
		if (isshowcity == false) {
			jq('.s_c_links').show(200);
			jq(this).addClass('on');
			isshowcity = true
		} else {
			jq('.s_c_links').hide(200);
			jq(this).removeClass('on');
			isshowcity = false
		}
		return false
	});
	jq('.s_c_links').hover(function() {
		ishovercitys = true
	},
	function() {
		ishovercitys = false
	});
	jq('body').bind('click',
	function() {
		if (isshowcity == true && ishovercitys == false) {
			jq('.s_c_links').hide(200);
			jq('.s_city .s').removeClass('on');
			isshowcity = false
		}
	})
});
jQuery(document).ready(function() {
	jq('.sd').each(function(i) {
		jq(this).find('.hztitle').click(function() {
			jq('.sd').eq(i).find('p').toggle()
		})
	});
	jq(".hztitle").toggle(function() {
		jq(this).addClass("hztitle-2")
	},
	function() {
		jq(this).removeClass("hztitle-2")
	})
});
function artHeight() {
	var rh = jq('.artists_r').height();
	var lh = jq('.artists_l').height();
	var list = jq('.artists_l .tab_min_in').height();
	var dh = rh - lh;
	if (dh > 0) {
		var h = lh + dh - 12;
		jq('.artists_l').height(h)
	}
	if (rh - list < 90) {
		jq('.artists_l').height('auto')
	}
}