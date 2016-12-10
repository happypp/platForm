$(function(){
    /*更新按钮单击事件*/
    $("#updateBtn").click(function(){
        var email_info = $("#email-info").hasClass("right");
        var code_info = $("#code-info").hasClass("right");
        var pwd_info = $("#pwd-info").hasClass("right");
        var rpwd_info = $("#rpwd-info").hasClass("right");
        if(email_info  && code_info && pwd_info  && rpwd_info ){
            var divHide = $("#hide");
            var width = $(document).width();
            var height = $(document).height();
            $(divHide).css("width",width);
            $(divHide).css("height",height);
            $(divHide).css("display","inline-block");
            $(divHide).css("position","fixed");
            $(divHide).css("z-index","888");
            $(divHide).css("background-color","rgba(0,0,0,.5)");
            $("#form div.spin-icon").show();
            var email = $("#email").val();
            var pwd = $("#pwd").val();
            var url = getProjectRootPath() + "/user/updateUserPwd";
            $.post(url,{
                'email' : email,
                'pwd' : pwd
            },function(data){
                if(data == 1){
                    setTimeout(function(){
                        //window.wxc.xcConfirm("更新成功~！", window.wxc.xcConfirm.typeEnum.info,{
                        //    onOk : function(){
                                location.reload();
                        //}
                        //});
                    },1500);
                }else{
                    window.wxc.xcConfirm("因不知原因更新失败~！", window.wxc.xcConfirm.typeEnum.error);
                }
            });
        } else {
            window.wxc.xcConfirm("请将数据填写完整~！", window.wxc.xcConfirm.typeEnum.error);
        }
    });
    /*验证邮箱格式是否正确及该邮箱是否存在*/
    $("#email").blur(function(){
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; //验证邮箱的正则
        var email = $(this).val();
        if(email.trim() != "" && reg.test(email)){
            /*判断邮箱是否存在*/
            var url = getProjectRootPath() + "/user/forget_email_val";
            $.post(url,{
                'email' : email
            },function(data){
                if(data == 1){
                    $("#email-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
                    $("#email-msg").text("");
                }else{
                    $("#email-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
                    $("#email-msg").text("该邮箱不存在~！");
                }
            });
        }else if(!reg.test(email) && email.trim() != ""){
            $("#email-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#email-msg").text("邮箱格式不正确~！");
        }else{
            $("#email-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#email-msg").text("邮箱不能为空~！");
        }
    });

    /*判断验证码是否正确*/
    $("#code").blur(function(){
        var code = $(this).val();
        var url = getProjectRootPath() + "/user/code_val";
        $.post(url,{
            'code' : code
        },function(data){
            if(data == 1){
                $("#code-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
                $("#pwd").removeAttr("disabled");
                $("#rpwd").removeAttr("disabled");
            }else{
                $("#code-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
                $("#pwd").attr("disabled",true);
                $("#rpwd").attr("disabled",true);
            }
        });
    });
    /*验证密码输入长度是否有误*/
    $("#pwd").blur(function(){
        var pwd = $(this).val();
        var rpwd = $("#rpwd").val();
        if(pwd.length > 5 && pwd.length < 11){
            $("#pwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
            $("#pwd-msg").text("");
            if(rpwd.trim() != ""){
                if(rpwd == pwd){
                    $("#rpwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
                    $("#rpwd-msg").text("");
                }else{
                    $("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
                    $("#rpwd-msg").text("两次密码不相同~!");
                }
            }
        }else{
            $("#pwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#pwd-msg").text("密码长度在（5~10）之间~!");
            if(rpwd != null){
                if(rpwd != pwd){
                    $("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
                    $("#rpwd-msg").text("两次密码不相同~!");
                }
            }
        }
    });
    /*判断两次密码是否相同*/
    $("#rpwd").blur(function(){
        var rpwd = $(this).val();
        var pwd = $("#pwd").val();
        if(rpwd == pwd){
            if(rpwd.length > 5 && rpwd.length < 11){
                $("#rpwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
                $("#rpwd-msg").text("");
            }else{
                $("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
                $("#rpwd-msg").text("密码长度在（5~10）之间~!");
            }
        }else{
            $("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#rpwd-msg").text("两次密码不相同~!");
        }
    });
});