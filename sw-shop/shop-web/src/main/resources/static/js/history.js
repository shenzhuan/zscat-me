var Webit = Webit || {};
(function(Webit, $, win) {

    var actions = {};
    var ignorChangeOnce = false;
    var location = win.location;
    var hashStartPos = 0;
    var getHash = function() {
        var hash = location.hash;
        return (hash !== null && hash !== "") ? hash.substring(1) : null;
    };
    var setHash = function(hash) {
        location.hash = hash || "";
    };
    var getHashFormUrl = function() {
        if(hashStartPos === 0) {
            hashStartPos = location.href.indexOf("#");
            if(hashStartPos < 0) {
                hashStartPos = location.href.length;
            }
            hashStartPos += 1;
        }
        return location.href.substr(hashStartPos);
    };
    var onHashChange = function(hash) {
        if(ignorChangeOnce) {
            ignorChangeOnce = false;
            return false;
        }
        //不符合规则的Token，忽略
        if(hash == null || hash[0] !== '/') {
            return true;
        }
        var cmdSplit = hash.indexOf('/', 1);
        if(cmdSplit < 0) {
            cmdSplit = hash.length;
        }
        var cmd = hash.substring(1, cmdSplit);
        //分配命令
        var action = actions[cmd];
        if(action) {
            action.fn.call(action, hash.substring(cmdSplit + 1), cmd, hash);
            return true;
        }
        return false;
    };
	
    var isArray = Array.isArray || function(obj) {
		return obj.toString() === '[object Array]';
	};

    var me = Webit.history = {
        init: function() {
            //initListener
            if(win.addEventListener) {
                //符合规范的浏览器 包括IE9+
                win.addEventListener('hashchange', function() {
                    onHashChange(getHash());
                }, false);
            } else if(win.attachEvent && navigator.userAgent.toLowerCase().indexOf("msie 8.0") >= 0) {
                //IE8
                win.attachEvent('onhashchange', function() {
                    onHashChange(getHash());
                });
            } else {
                //IE6-7 性能差
                setInterval((function() {
                    var lastHash = getHashFormUrl();
                    return function() {
                        var hash = getHashFormUrl();
                        if(hash !== lastHash) {
                            onHashChange(lastHash = hash);
                        }
                    };
                })(), 100);
            }
            //invoke at first
            onHashChange(getHashFormUrl());
        },
        add: function(key, fn) {
            if(isArray(key)) {
                for(var i = 0; i < key.length; i++) {
                    me.add(key[i]);
                }
                return;
            }
            var action;
            if(typeof (key) === "object") {
                action = key;
            } else {
                action = {
                    key: key,
                    fn: fn
                };
            }
            if(fn && key) {
                actions[action.key] = action;
                return true;
            }
            return false;
        },
        remove: function(key) {
            delete actions[key];
        },
        justShow: function(hash) {
            ignorChangeOnce = hash && hash !== "" ? true : false;
            setHash(hash);
        },
        go: function(hash) {
            setHash(hash);
        },
        get: function() {
            return getHash();
        },
        getActions:function(){
        	return actions;
        }
    };
})(Webit, jQuery, window);