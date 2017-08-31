function withDraw(){

    var balance = $("#balance").val();
    var level = $("#level").val();
    var account = $("#account").val();
    if(account<1){
        $.toast("提现金额不能为少于1元",'forbidden');
        return false;
    }
    $.post("/wx/h5/withDraw/withDraw",{
        "balance": parseInt(balance*100),
        "level": level,
        "account": parseInt(account*100)
    }, function (data){

        $.toast(data.msg, function() {
            window.location.href="/wx/h5/user/tginfo";
        });

    });
}

$("#create").click(function(){
    withDraw();
    return false;
});
$("#enough").click(function(){
    var balance = $("#balance").val();
    var rate = $("#rate").val();
    var level = $("#level").val();
    if (1 == level || 2==level){
        $("#account").val(balance);
    }else {
        var tmp = balance*rate/100;
        $("#account").val(toDecimal(tmp));
    }
    return false;
});
$("#all").click(function(){
    var balance = $("#balance").val();
    $("#account").val(balance);
    return false;
});
//保留两位小数
//功能：将浮点数四舍五入，取小数点后2位
function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}


function belowIf(pagenum,uid) {
    var _timer = null;
    $.ajax({
        url: "/wx/h5/withDraw/withdrawHistory",
        data: {
            "pagenum":pagenum,
            "uid": uid
        },
        type: 'post',
        dataType: 'text',
        beforeSend: function () {
            clearTimeout(_timer);

        },
        success: function (result) {
            _timer = setTimeout(function () {
                $("#tableInclude").html(result);
            }, 300);
        }
    });
}