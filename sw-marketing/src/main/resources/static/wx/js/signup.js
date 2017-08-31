/**
 * Created by Carmen on 17/6/30.
 */



$("#commit").on("click",function() {
    console.log("----submit---")
    postRegister();
});

function postRegister() {

    var phone = $("#mobile").val();

    console.log(phone + "--" + vcode + "--");
    $.post("/wx/h5/user/register", {
        "phone": phone
    }, function (data) {
        var code = data.code;
        if(code==0){
            window.location.href=data.data.url;
        }else {
            alert(data.msg);
        }
    });
}