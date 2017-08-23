$(function(){
    // 邮箱输入框自动完成
    var mailSuffix = ["@vip.com"];
    // 自动完成.改变候选项
    var auto = function(mailSuffix){
        var hints = new Array(mailSuffix.length);
        var str = $("#emailInput").val();
        str = str.split("@")[0];
        for (var i = 0; i < hints.length; i++) {
            hints[i] = str + mailSuffix[i];
        }
        return hints;
    };
    $("#emailInput").autocomplete({
        source: function(request, response){
            response(auto(mailSuffix));
        }
    });

    //密码输入框，键盘监听事件
    $("#pwdInput").bind("keydown",function(event){
        if(event.keyCode == 13){
            login();
        }
    });

});
//后台管理员登录
function login(){

    var emailValue = $("#emailInput").val();
    if(emailValue.trim() == ""){
        window.wxc.xcConfirm("邮箱不能为空~!", window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    var pwdVaule = $("#pwdInput").val();
    if(pwdVaule == ""){
        window.wxc.xcConfirm("密码不能为空~!", window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    //var admin = emailValue.split("@")[0];
    if ("Adminstrator@vip.com" == emailValue || "administrator@vip.com" == emailValue) {
        var url = getProjectRootPath() + "/valPassWord";
        $.post(url,{
            'email':emailValue,
            'password':pwdVaule
        },function(data){
            if(data == 0){
                window.wxc.xcConfirm("密码有误~！请重新输入~！", window.wxc.xcConfirm.typeEnum.error);
                $("#pwdInput").val("");
            }else{
                location.href = getProjectRootPath() + "/bg_index";
            }
        });
    }else{
        window.wxc.xcConfirm("邮箱输入有误，请重新输入~！", window.wxc.xcConfirm.typeEnum.error);
        $("#emailInput").val("");
        return false;
    }

}