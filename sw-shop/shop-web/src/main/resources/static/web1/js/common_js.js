/*****宽窄屏幕自适应****/
$(document).ready(function(){
if(screen.width > 1280 && $(window).width() > 1280)
{
	$("html").addClass("root61");
}
});
$(document).ready(function(){
  
  $('li.hd_menu_tit').mousemove(function(){
  $(this).find('div.hd_menu_list,div.hd_Shopping_list').show();//you can give it a speed
  });
  $('li.hd_menu_tit').mouseleave(function(){
  $(this).find('div.hd_menu_list,div.hd_Shopping_list').hide();
  });
 $(function(){
	$(".fixed_qr_close").click(function(){
		$(".mod_qr").hide();
	})
});
//按钮点击事件
$(".modify").click(function(){
	 $('.text').attr("disabled", false);
	 $('.text').addClass("add");
	  $('#Personal').find('.xinxi').addClass("hover");
	
	});
//文本框登录注册事件
    $('#user_text, #tbPassword, #confirm_pwd_text, #Codes_text').blur(function(){
        var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class','frame_style').removeClass(' form_error');
        if($el.val()==''){
            $parent.attr('class','frame_style').addClass(' form_error');
        }
    });
	$("#user_text, #tbPassword, #confirm_pwd_text, #Codes_text").focus(function(){		
		 var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class','frame_style').removeClass(' form_errors');
        if($el.val()==''){
            $parent.attr('class','frame_style').addClass(' form_errors');
        }	
		});
//复选框选择事件
  $('.check_agreement').toggle(function(){
		 	 
	 $('.clearfix').find('.auto_tips').removeClass('on-focus').attr('class','auto_tips').addClass(' on-focus');	
	 $(this).attr('class','').addClass('Select');
	 }
	 ,function(){
		   $('.clearfix').find('.auto_tips').removeClass('auto_tips').attr('class','auto_tips');	
			$(this).attr('class','check_agreement').removeClass("Select");  
		 
	 });

//产品边框样式
 $(".service_list li").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	);
	/*方向*/
	 $("#Brand_Show").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
 //购物车
 $("div.hd_Shopping_list").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
 //支付方式
 $("#payment").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
	//推荐切换图片显示隐藏
	 $(".picScroll").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
	//推荐产品鼠标移动事件
	 $("li.recommend_info").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
	//品牌鼠标移动事件
	 $("li.recommend_info").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
	//
		 $("li.Brands_info").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 
	 $(".pro_ad_slide").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	);
	 $(".list_style li").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	);
	 $(".hot_silde").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	);
	/***边框**/
	 $("ul .collect_p").hover(function(){
			$(this).addClass("hover");

		},function(){
			$(this).removeClass("hover");  
		}
	);
	$("#lists li").hover(function(){
		$(this).find(".Detailed").show();
	},function(){
		$(this).find(".Detailed").hide();
});
	$("#lists li").hover(function(){
												
			$(this).addClass("hover_nav");
												
		},function(){
			$(this).removeClass("hover_nav" );  
		}
	); 
});

$(document).ready(function(){
	$("#nav li.no_sub").hover(function(){
			$(this).addClass("hover1");
		},function(){
			$(this).removeClass("hover1");  
		}
	); 
});
$(document).ready(function(){
		$(".clearfix li.list_name, li.phone_c").hover(function(){
			$(this).addClass("hd_menu_hover");
			$(this).children("ul li.list_name_bg").attr('class','');
		},function(){
			$(this).removeClass("hd_menu_hover");  
			$(this).children("ul li.list_name_bg").attr('class','');
		}
	); 				   
$("#allSortOuterbox li").hover(function(){
		$(this).find(".menv_Detail").show();
	},function(){
		$(this).find(".menv_Detail").hide();
});
	$("#allSortOuterbox li.name").hover(function(){
												
			$(this).addClass("hover_nav");
												
		},function(){
			$(this).removeClass("hover_nav" );  
		});
		$("div.display ").hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover" );  
});	
});
$(document).ready(function(){
$("#lists li").hover(function(){
		$(this).find(".Detailed").show();
	},function(){
		$(this).find(".Detailed").hide();
});
	$("#lists li").hover(function(){
												
			$(this).addClass("hover_nav");
												
		},function(){
			$(this).removeClass("hover_nav" );  
		}
	); 
});
/********************订单js******************/
$(document).ready(function(){
	 $('#select').find('ul').click(function(){
	$('#select').find('ul').removeClass('active');
	$(this).addClass('active');
  }); 
   $("#select ul").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	); 	
	   $(".dowebok li").hover(function(){
			$(this).addClass("hover");
			//$(this).children(".dorpdown-layer").attr('class','');
		},function(){
			$(this).removeClass("hover");  
			//$(this).children(".dorpdown-layer").attr('class','');
		}
	);					
});
/**********鼠标移动效果************/
$(document).ready(function(){
	$("ul.productss li").hover(function() {
		$(this).find(".title").stop()
		.animate({bottom: "0", }, "fast")
		.css("display","block")

	}, function() {
		$(this).find(".title").stop()
		.animate({bottom: "-30",}, "fast")
	});
	//菜单栏效果
			$('.all-sort-list > .item').hover(function(){
			var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
				h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
				s = $(window).scrollTop(),									//获取游览器滚动了多少高度
				i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
				item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
				sort = $('.all-sort-list').height();						//父类分类列表容器的高度
			
			if ( item < sort ){												//如果子类的高度小于父类的高度
				if ( eq == 0 ){
					$(this).children('.item-list').css('top', (i-h));
				} else {
					$(this).children('.item-list').css('top', (i-h)+1);
				}
			} else {
				if ( s > h ) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
					if ( i-s > 0 ){											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
						$(this).children('.item-list').css('top', (s-h)+2 );
					} else {
						$(this).children('.item-list').css('top', (s-h)-(-(i-s))+2 );
					}
				} else {
					$(this).children('.item-list').css('top', 3 );
				}
			}	

			$(this).addClass('hover');
			$(this).children('.item-list').css('display','block');
		},function(){
			$(this).removeClass('hover');
			$(this).children('.item-list').css('display','none');
		});

		$('.item > .item-list > .close').click(function(){
			$(this).parent().parent().removeClass('hover');
			$(this).parent().hide();
		});
});
/*********************点击事件*********************/
$( document).ready(function(){
  $('.fixed_bar').find('li').click(function(){
	$('.fixed_bar').find('li').removeClass('active');
	$(this).addClass('active');
  });	
  $('.infonav_hidden').click(function(){
	$('.infonav_more').find('a').removeClass('active');
	$(this).addClass('active');
	$(this).slideToggle();
  });																
})
/*****************************左右伸缩样式****************************/
$(window).scroll(function() {
/*var flag=1;
$('#rightArrow').click(function(){
	if(flag==1){
		$("#floatDivBoxs").animate({left: '-175px'},300);
		$(this).animate({left: '-5px'},300);
		$(this).css('background-position','-50px 0');
		flag=0;
	}else{
		$("#floatDivBoxs").animate({left: '0'},300);
		$(this).animate({left: '170px'},300);
		$(this).css('background-position','0px 0');
		flag=1;
	}
});*/
});
/***********************商品分类顶部浮动固定层菜单栏**************************/
$(window).scroll(function() {
		var topToolbar = $("#productsMenu");
		var headerH = $("#Preferential_AD").outerHeight();
		var headers = $("#header_outerHeight").outerHeight();
		var scrollTop =$(document.body).scrollTop();	
			if( scrollTop >= headerH + headers ){
				topToolbar.stop(false,true).addClass("fixToTop");
			}else if( scrollTop < headerH + headers ){
				topToolbar.stop(false,true).removeClass("fixToTop"); 
			}
});
$(window).scroll(function() {
		var topToolbar = $(".fixed_out ");
		var headerH = $("#header_top").outerHeight();
		var headers = $("#goodsInfo").outerHeight();
		var headerd = $("#recommnad").outerHeight();
		var scrollTop =$(document.body).scrollTop();	
			if( scrollTop >= headerH + headers +headerd ){
				topToolbar.stop(false,true).addClass("fixToTop");
			}else if( scrollTop < headerH + headers + headerd ){
				topToolbar.stop(false,true).removeClass("fixToTop"); 
			}
});
$(window).scroll(function() {
		var topToolbar = $("#Sorted");
		var headerH = $("#header_top").outerHeight();
		var headers = $("#Filter_style").outerHeight();
		var scrollTop =$(document.body).scrollTop();	
			if( scrollTop >= headerH + headers ){
				topToolbar.stop(false,true).addClass("fixToTop");
			}else if( scrollTop < headerH + headers  ){
				topToolbar.stop(false,true).removeClass("fixToTop"); 
			}
});
/********************密码强度***********************/
   $(document).ready(function(){
        $('#tbPassword').focus(function () {
            $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
            $('#tbPassword').keyup();
        });
        $('#tbPassword').keyup(function () {
            var __th = $(this);
            if (!__th.val()) {
                $('#pwd_tip').hide();
                $('#pwd_err').show();
                Primary();
                return;
            }
            if (__th.val().length < 6) {
                $('#pwd_tip').hide();
                $('#pwd_err').show();
                Weak();
                return;
            }
            var _r = checkPassword(__th);
            if (_r < 1) {
                $('#pwd_tip').hide();
                $('#pwd_err').show();
                Primary();
                return;
            }
            if (_r > 0 && _r < 2) {
                Weak();
            } else if (_r >= 2 && _r < 4) {
                Medium();
            } else if (_r >= 4) {
                Tough();
            }
            $('#pwd_tip').hide();
            $('#pwd_err').hide();
        });
        function Primary() {
            $('#pwdLevel_1').attr('class', 'ywz_zhuce_huixian');
            $('#pwdLevel_2').attr('class', 'ywz_zhuce_huixian');
            $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
        }
        function Weak() {
            $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
            $('#pwdLevel_2').attr('class', 'ywz_zhuce_huixian');
            $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
        }
        function Medium() {
            $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
            $('#pwdLevel_2').attr('class', 'ywz_zhuce_hongxian2');
            $('#pwdLevel_3').attr('class', 'ywz_zhuce_huixian');
        }
        function Tough() {
            $('#pwdLevel_1').attr('class', 'ywz_zhuce_hongxian');
            $('#pwdLevel_2').attr('class', 'ywz_zhuce_hongxian2');
            $('#pwdLevel_3').attr('class', 'ywz_zhuce_hongxian3');
        }
        function checkPassword(pwdinput) {
            var maths, smalls, bigs, corps, cat, num;
            var str = $(pwdinput).val()
            var len = str.length;
            var cat = /.{16}/g
            if (len == 0) return 1;
            if (len > 16) { $(pwdinput).val(str.match(cat)[0]); }
            cat = /.*[\u4e00-\u9fa5]+.*$/
            if (cat.test(str)) {
                return -1;
            }
            cat = /\d/;
            var maths = cat.test(str);
            cat = /[a-z]/;
            var smalls = cat.test(str);
            cat = /[A-Z]/;
            var bigs = cat.test(str);
            var corps = corpses(pwdinput);
            var num = maths + smalls + bigs + corps;
            if (len < 6) { return 1; }
            if (len >= 6 && len <= 8) {
                if (num == 1) return 1;
                if (num == 2 || num == 3) return 2;
                if (num == 4) return 3;
            }
            if (len > 8 && len <= 11) {
                if (num == 1) return 2;
                if (num == 2) return 3;
                if (num == 3) return 4;
                if (num == 4) return 5;
            }
            if (len > 11) {
                if (num == 1) return 3;
                if (num == 2) return 4;
                if (num > 2) return 5;
            }
        }
        function corpses(pwdinput) {
            var cat = /./g
            var str = $(pwdinput).val();
            var sz = str.match(cat)
            for (var i = 0; i < sz.length; i++) {
                cat = /\d/;
                maths_01 = cat.test(sz[i]);
                cat = /[a-z]/;
                smalls_01 = cat.test(sz[i]);
                cat = /[A-Z]/;
                bigs_01 = cat.test(sz[i]);
                if (!maths_01 && !smalls_01 && !bigs_01) { return true; }
            }
            return false;
        } 
})  
/***********购物车*********/
$(document).ready(function(){
(function($) {
    var defaults = {reNumbers: /(-|-\$)?(\d+(,\d{3})*(\.\d{1,})?|\.\d{1,})/g, cleanseNumber: function (v) {
        return v.replace(/[^0-9.\-]/g, "");
    }, useFieldPlugin: (!!$.fn.getValue), onParseError: null, onParseClear: null};
    $.Calculation = {version: "0.4.07",setDefaults: function(options) {
        $.extend(defaults, options);
    }};
    $.fn.parseNumber = function(options) {
        var aValues = [];
        options = $.extend(options, defaults);
        this.each(function () {
            var $el = $(this),sMethod = ($el.is(":input") ? (defaults.useFieldPlugin ? "getValue" : "val") : "text"),v = $.trim($el[sMethod]()).match(defaults.reNumbers, "");
            if (v == null) {
                v = 0;
                if (jQuery.isFunction(options.onParseError)) options.onParseError.apply($el, [sMethod]);
                $.data($el[0], "calcParseError", true);
            } else {
                v = options.cleanseNumber.apply(this, [v[0]]);
                if ($.data($el[0], "calcParseError") && jQuery.isFunction(options.onParseClear)) {
                    options.onParseClear.apply($el, [sMethod]);
                    $.data($el[0], "calcParseError", false);
                }
            }
            aValues.push(parseFloat(v, 10));
        });
        return aValues;
    };
    $.fn.calc = function(expr, vars, cbFormat, cbDone) {
        var $this = this, exprValue = "", precision = 0, $el, parsedVars = {}, tmp, sMethod, _, bIsError = false;
        for (var k in vars) {
            expr = expr.replace((new RegExp("(" + k + ")", "g")), "_.$1");
            if (!!vars[k] && !!vars[k].jquery) {
                parsedVars[k] = vars[k].parseNumber();
            } else {
                parsedVars[k] = vars[k];
            }
        }
        this.each(function (i, el) {
            var p, len;
            $el = $(this);
            sMethod = ($el.is(":input") ? (defaults.useFieldPlugin ? "setValue" : "val") : "text");
            _ = {};
            for (var k in parsedVars) {
                if (typeof parsedVars[k] == "number") {
                    _[k] = parsedVars[k];
                } else if (typeof parsedVars[k] == "string") {
                    _[k] = parseFloat(parsedVars[k], 10);
                } else if (!!parsedVars[k] && (parsedVars[k] instanceof Array)) {
                    tmp = (parsedVars[k].length == $this.length) ? i : 0;
                    _[k] = parsedVars[k][tmp];
                }
                if (isNaN(_[k])) _[k] = 0;
                p = _[k].toString().match(/\.\d+$/gi);
                len = (p) ? p[0].length - 1 : 0;
                if (len > precision) precision = len;
            }
            try {
                exprValue = eval(expr);
                if (precision) exprValue = Number(exprValue.toFixed(Math.max(precision, 4)));
                if (jQuery.isFunction(cbFormat)) {
                    var tmp = cbFormat.apply(this, [exprValue]);
                    if (!!tmp) exprValue = tmp;
                }
            } catch(e) {
                exprValue = e;
                bIsError = true;
            }
            $el[sMethod](exprValue.toString());
        });
        if (jQuery.isFunction(cbDone)) cbDone.apply(this, [this]);
        return this;
    };
    $.each(["sum", "avg", "min", "max"], function (i, method) {
        $.fn[method] = function (bind, selector) {
            if (arguments.length == 0)return math[method](this.parseNumber());
            var bSelOpt = selector && (selector.constructor == Object) && !(selector instanceof jQuery);
            var opt = bind && bind.constructor == Object ? bind : {bind: bind || "keyup", selector: (!bSelOpt) ? selector : null, oncalc: null};
            if (bSelOpt) opt = jQuery.extend(opt, selector);
            if (!!opt.selector) opt.selector = $(opt.selector);
            var self = this, sMethod, doCalc = function () {
                var value = math[method](self.parseNumber(opt));
                if (!!opt.selector) {
                    sMethod = (opt.selector.is(":input") ? (defaults.useFieldPlugin ? "setValue" : "val") : "text");
                    opt.selector[sMethod](value.toString());
                }
                if (jQuery.isFunction(opt.oncalc)) opt.oncalc.apply(self, [value, opt]);
            };
            doCalc();
            return self.bind(opt.bind, doCalc);
        }
    });
    var math = {sum: function (a) {
        var total = 0, precision = 0;
        $.each(a, function (i, v) {
            var p = v.toString().match(/\.\d+$/gi), len = (p) ? p[0].length - 1 : 0;
            if (len > precision) precision = len;
            total += v;
        });
        if (precision) total = Number(total.toFixed(precision));
        return total;
    },avg: function (a) {
        return math.sum(a) / a.length;
    },min: function (a) {
        return Math.min.apply(Math, a);
    },max: function (a) {
        return Math.max.apply(Math, a);
    }};
})(jQuery);
/** ------------- choose -------------------- **/
/* reduce_add */
var setAmount = {
    min:1,
    max:999,
    reg:function(x) {
        return new RegExp("^[1-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
        } else {
            alert("请输入正确的数量！");
            $(obj).val(1);
            $(obj).focus();
        }
        return x;
    },
    reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
            recalc();
        } else {
            alert("商品数量最少为" + this.min);
            $(obj).val(1);
            $(obj).focus();
        }
    },
    add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
            recalc();
        } else {
            alert("商品数量最多为" + this.max);
            $(obj).val(999);
            $(obj).focus();
        }
    },
    modify:function(obj) {
        var x = $(obj).val();
        if (x < this.min || x > this.max || !this.reg(x)) {
            alert("请输入正确的数量！");
            $(obj).val(1);
            $(obj).focus();
        }
    }
}

function BuyUrl(wid) {
    var pcounts = $("input[id^=qty_item_]").val();
    var patrn = /^[0-9]{1,4}$/;
    if (!patrn.exec(pcounts)) {
        pcounts = 1;
    }
    else {
        if (pcounts <= 0)
            pcounts = 1;
        if (pcounts >= 1000)
            pcounts = 999;
    }
}
;

/** total_item **/
$(document).ready(function () {
    $("input[name^=qty_item_]").bind("keyup", recalc);
    recalc();
});

function recalc() {

    $("input[id^=total_item]").val()
    //产品价格统计
    $("[id^=total_item]").calc(

        "qty * price",

        {
            qty: $("input[name^=qty_item_]"),
            price: $("[id^=price_item_]")
        },

        function (s) {

            return "￥" + s.toFixed(2);
        },

        function ($this) {

            var sum = $this.sum();
            $("[id^=total_item]").text(
                "￥" + sum.toFixed(2)
            );
            $("#total_price").val($("[id^=total_item_1],[id^=total_item_2]").text());
        }
    );

    //产品积分统计
    $("[id^=total_points]").calc(

        "qty * price",

        {
            qty: $("input[name^=qty_item_]"),
            price: $("[id^=price_item_1],[id^=price_item_2]")
        },

        function (s) {
            return "" + s.toFixed(0);
        }

    );
	//优惠价格
	 $("[id^=Preferential_price]").calc(

        "(Original_Price - Current_price) * qty",

        {
            Original_Price: $("[id^=Original_Price_]"),
            Current_price: $("[id^=price_item_1],[id^=price_item_2]"),
			qty: $("input[name^=qty_item_]"),			
        },

        function (s) {
            return "" + s.toFixed(2);
        }
    );
	//产品总价格
	 $("[id^=Total_price]").calc(
        "qty * price",
        {
            qty: $("input[name^=qty_item_]"),
            price: $("[id^=price_item_]")
        },

        function (s) {
            return "" + s.toFixed(2);
        }

    );

};
})