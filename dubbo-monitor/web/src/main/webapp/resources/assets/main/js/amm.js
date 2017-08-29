
var Amm = function() {

    var changeiframeParentHeight = function (){
        var height = $('body').height();
        $(parent.document).find('iframe').height(height);
    };

    var nowDate = function show(){
        var mydate = new Date();
        var str = "" + mydate.getFullYear() + "-"+(mydate.getMonth()+1)+"-"+mydate.getDate();
        return str;
    };

    return {

        init: function () {
            changeiframeParentHeight();
        },
        alertFuc: function (content, heard_html, width) {
            //提示框
            window.parent.window.alertContent(content, heard_html, width);
        },
        changeiframeParentHeight: function () {
            changeiframeParentHeight();
        },

        getNowDate: function(){
            return nowDate();
        },

        getHourArray:function(){
            // 获得 时刻的小时array
            var time_data_array = ['00时', '01时', '02时', '03时', '04时', '05时', '06时', '07时', '08时', '09时', '10时', '11时', '12时', '13时', '14时', '15时', '16时', '17时', '18时', '19时', '20时', '21时', '22时', '23时'];

            return  time_data_array
        },

        animate:function(scroll_offset){
            $(parent.document).find('body').animate({
                scrollTop: scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
            }, 1200);
        }


    };

}();