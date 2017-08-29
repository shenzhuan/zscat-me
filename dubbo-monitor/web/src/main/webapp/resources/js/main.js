var headerUrl = 'http://'+window.location.host;
$(function(){
    //名字的不存在
    //$("#edit_name_btn").click(function(){
    //    alert_change_name();
    //});
    $("#for_fun_btn").click(function(){
        $("#confirm_modal").modal("show");
        //$("#alert_model").modal("show");
        return false;
    });

    //弹性更改浏览器高度
    changeHeightWidth();
    $(window).resize(function () {          //当浏览器大小变化
        changeHeightWidth();
        return;
    });


    //菜单栏的点击事件
    $('.gotoIfreame').click(function(){
        var url = $(this).children('a').data("url");
        $('#mainIframe').attr("src",url);

        $('.page-sidebar-menu li').removeClass('active');

        $(this).addClass('active');

        getFinalUpdateTime();
    });

    // 点击 缩进
    $('.sidebar-toggler').click(function(){
        $('#main-title').toggleClass("hidden");
    });

    getFinalUpdateTime();
});

//弹出改名字的窗口
function alert_change_name(name ){
    if(name == undefined || name == 'null'){
        name = "";
    }
    $("#confirm_modal").modal("show");
}

//动态变幻高度
function changeHeightWidth(){
    var height = document.documentElement.clientHeight;
    var headHeight = $('#main_header').height();


    var newHeight = Number(height)-Number(headHeight)-43-Number($('.page-footer').height());
    $('#mainIframe').css('min-height',newHeight+'px');
    $('#mainIframe').parent().css('min-height',newHeight+'px');


    var width = Number(document.documentElement.clientWidth);
    if($('body').hasClass("page-sidebar-closed")){
        width = width - 74
    }else{
        width = width - 255
    }
    // var newWidth = Number(height)-Number(headHeight)-Number(footHeight)-13;
    $('#mainIframe').css('max-width',width+'px');
    $('#mainIframe').parent().css('max-width',width+'px');


    return;
}


// 获得最后更新时间
function getFinalUpdateTime(){
    $.get(headerUrl+"/monitor/common/getFinalTime",function(resultVO){
        if(resultVO.success){
            $('#final_update_time').text(resultVO.data)
        }
    })
}


//弹出框
var alertContent = function (content, heard_html, width) {
    var hearder = $("#alert_model .modal-header", parent.document);
    if (heard_html == undefined) {
        hearder.addClass('hidden');
    } else {
        hearder.html(heard_html);
        hearder.removeClass('hidden');
    }
    $("#alert_model .modal-body", parent.document).html(content);
    if (width == undefined) {
        width = "500";
    }
    $('#alert_model', parent.document).data("width", width);
    $('#alert_model', parent.document).modal('show');
    //alertHeightChange();
    return false;
};