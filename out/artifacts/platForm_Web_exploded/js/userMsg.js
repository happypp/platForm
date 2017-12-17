$(function(){
    var lis = $("#main-left ul li");
    var forms = $("#main form");
    lis.each(function(){
        $(this).click(function(){
            forms.css("display","none");
            lis.removeClass("liActive");
            $(this).addClass("liActive");
            var formId = $(this).data("form");
            $("#"+formId).css("display","block");
        });
    });
    $("#main form input[name='name']").attr("value",$("#userInfo .userHeader ul li:last-child").text());
    $("#main form input[name='type']").attr("value",$("#uAvatar a img").data("content"));


    //用户修改用户名的异步请求
    $("#main form input[name='name']").blur(function(){
        var uid = $("#uAvatar a img").data("uid");
        var nameInput = $(this).val();
        if(nameInput == ""){
            $("#msgInfo").text("昵称不能为空~!");
            return false;
        }
        if(nameInput.length < 2 || nameInput.length > 15){
            $("#msgInfo").text("昵称长度不符合要求(2~15)~!");
            return false;
        }
        var url = "/user/editName";
        url = getProjectRootPath() + url;
        $.post(url,{
            'id':uid,
            'name':nameInput
        },function(data){
            if(data != ""){
                $("#msgInfo").text(data);
            }else{
                $("#msgInfo").text("*");
            }

        });
    });


    //用户基本信息的保存
    $("#userMsgSave input").click(function(){
        var name = $("#main form input[name='name']").val();
        var avatar = $("#main form #avatarImg").val();
        var imgFormat = avatar.substring(avatar.lastIndexOf("\.")+1,avatar.length);
        var txt = null;
        if(imgFormat=='bmp' || imgFormat=='gif' || imgFormat=='jpeg' || imgFormat=='png' || imgFormat=='jpg'){
            if(name.length > 1 && name.length < 16 && avatar.length > 0 ){
                $("#userMsg_form").submit();
                $(this).attr("disable","disable");
                return false;
            }else{
                txt = "亲~！请填写全部正确信息哦~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
                return false;
            }
        }else{
            txt = "亲~！上传的头像格式不正确~！";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            return false;
        }
    });

    //用户原始密码的异步验证
    $("#userPwd_form input[name='password']").blur(function(){
        var uid = $("#uAvatar a img").data("uid");
        var password = $(this).val();
        var url = "/user/passWordExistVal";
        url = getProjectRootPath() + url;
        $.post(url,{
            'uid':uid,
            'password':password
        },function(data){
            if(data != ""){
                $("#passwordMsg").text(data);
            }else{
                $("#passwordMsg").text("*");
            }
        });
    });
    //用户新密码格式的异步验证
    $("#userPwd_form input[name='pwd1']").blur(function(){
        var password = $(this).val();
        var pwd = $("#userPwd_form input[name='password']").val();
        var url = "/user/registVal/password";
        url = getProjectRootPath() + url;
        $.post(url,{
            'password':password
        },function(data){
            if(data != ""){
                $("#pwd1").text(data);
            }else{
                if(password === pwd){
                    $("#pwd1").text("新密码不能和旧密码相同~！");
                }else {
                    $("#pwd1").text("*");
                }
            }
        });
    });
    //用户新密码的重复密码验证
    $("#userPwd_form input[name='pwd2']").blur(function(){
        var password = $("#userPwd_form input[name='pwd1']").val();
        var rpwd = $(this).val();
        var url = "/user/registVal/rpwd";
        url = getProjectRootPath() + url;
        $.post(url,{
            'password':password,
            'rpwd':rpwd
        },function(data){
            if(data != ""){
                $("#pwd2").text(data);
            }else{
                $("#pwd2").text("*");
            }
        });
    });
    //用户新密码的保存
    $("#userPwdEdit input").click(function(){
        var password = $("#userPwd_form input[name='password']").val();
        var pwd = $("#userPwd_form input[name='pwd1']").val();
        var rpwd = $("#userPwd_form input[name='pwd2']").val();
        var uid = $("#uAvatar a img").data("uid");
        if($("#passwordMsg").text() == "*" && $("#pwd1").text() == "*" && $("#pwd2").text() == "*"
                && password.length > 0 && pwd.length > 0 && rpwd.length > 0){
            var url = "/user/editPwd";
            url = getProjectRootPath() + url;
            $.post(url,{
                'uid':uid,
                'password':pwd
            },function(data){
                if(data == 1){
                    location.reload();
                }
            });
        }else{
            var txt = "亲~！请填写好，您自己的数据，方便我们的更新您的密码~！";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        }
    });

});