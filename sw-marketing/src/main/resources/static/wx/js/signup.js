/**
 * Created by Carmen on 17/6/30.
 */
$("#getCode").on("click",function(){


    if($("#mobile").val!="") {
        var mobile=$("#mobile").val();
        $.ajax({
            type: 'GET',
            url: "/wx/h5/api/phoneCode?phone=" + mobile, //获取验证码接口
            success: function (data, status) {
                if (data.code == 200) {
                    $("#getCode").attr("disabled", "disabled");
                    $("#getCode").css({"background-color": "#FCFCFC","color":"#000"});
                    //下面就是实现倒计时的效果代码
                    var d = new Date()*1+119000;
                    var end_time = new Date()*1;
                    //月份是实际月份-1
                    var sys_second =parseInt((d-end_time)/1000);
                    var second = 0;
                    var timer = setInterval(function () {
                            if (sys_second > 1) {
                                sys_second -= 1;
                                var time_text = '';
                                if (sys_second > 0) {
                                    if (sys_second < 10) {
                                        second = '0' + sys_second;
                                    } else {
                                        second = sys_second;
                                    }
                                    time_text = second;
                                }
                                $("#getCode").css("width","110px");
                                $("#getCode").html(time_text+"秒");

                            } else {
                                clearInterval(timer);
                                $("#getCode").removeAttr("disabled");
                                $("#getCode").removeAttr("width");
                                $("#getCode").html('获取验证码');
                                $("#getCode").css({"background-color":"#fff","color":"#1AAD19"});
                            }
                        },
                        1000);

                } else {
                    alert(data.msg);
                }
            },
            error: function (data, status) {
                alert(status);
            }
        });
    }
});


$("#commit").on("click",function() {
    console.log("----submit---")
    postRegister();
});

function postRegister() {

    var phone = $("#mobile").val();
    var vcode = $("#vcode").val();
    var upIfCode = $("#upIfCode").val();
    console.log(phone + "--" + vcode + "--");
    $.post("/wx/h5/user/register", {
        "phone": phone,
        "phoneCode": vcode,
        "upIfCode": upIfCode
    }, function (data) {
        var code = data.code;
        if(code==0){
            window.location.href=data.data.url;
        }else {
            alert(data.msg);
        }
    });
}