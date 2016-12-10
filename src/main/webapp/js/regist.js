// JavaScript Document
$("#email").blur(function(){
    var url = "/user/registVal/email?email="+$("#email").val();
    url = getProjectRootPath() + url;
    $.post(url, null, function(data){
        if(data == ""){
            $("#email-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
            $("#email-msg").text("");
        }else{
            $("#email-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#email-msg").text(data);
        }
    });
});


$("#name").blur(function(){
    var url = "/user/registVal/name?name="+$("#name").val();
    url = getProjectRootPath() + url;
    $.post(url, null, function(data){
        if(data == ""){
            $("#name-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
            $("#name-msg").text("");
        }else{

            $("#name-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#name-msg").text(data);
        }
    });
});


$("#password").blur(function(){
    var url = "/user/registVal/password?password="+$("#password").val();
    url = getProjectRootPath() + url;
    $.post(url, null, function(data){
        if(data == ""){
            $("#pwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
            $("#pwd-msg").text("");
        }else{
            $("#pwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#pwd-msg").text(data);
        }
    });
});

$("#rpwd").blur(function(){
    var url = "/user/registVal/rpwd?password="+$("#password").val()+"&rpwd="+$("#rpwd").val();
    url = getProjectRootPath() + url;
    $.post(url, null, function(data){
        if(data == "" && ($("#password").val().length < 11 && $("#password").val().length > 1)){
            $("#rpwd-info").attr("class","glyphicon  form-control-feedback glyphicon-ok right");
            $("#rpwd-msg").text("");
        }else {
            $("#rpwd-info").attr("class","glyphicon glyphicon-remove form-control-feedback error");
            $("#rpwd-msg").text(data);
        }
    });
});


function validate(){
    var url = getProjectRootPath() + "/user/sendEmail";
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; //验证邮箱的正则
    if (($("#name").val().length < 16 && $("#name").val().length > 1)
        && reg.test($("#email").val())
        && !($("#email-msg").text()=="邮箱已存在请重新填写")
        && ($("#password").val().length < 11 && $("#password").val().length > 4)
        && ($("#password").val() == ($("#rpwd").val()))) {
        $("#registBtn").attr("disabled","disabled");
        $("#registBtn").attr("type","button");

        $("#form .fa-spin").show();
        setTimeout(function () {
            $("#warning").html("<div class='alert alert-success' style='text-align: center;'><a href='#' class='close' data-dismiss='alert'>&times;</a><strong>提示！</strong>亲，请前往您的邮箱进行账号激活~！</div>");
            $("#form .fa-spin").hide();
        }, 2000);
        $("#email").attr("disabled","disabled");
        $("#name").attr("disabled","disabled");
        $("#password").attr("disabled","disabled");
        $("#rpwd").attr("disabled","disabled");

        $.post(url,{
            "email":$("#email").val(),
            "name":$("#name").val(),
            "password":$("#password").val()
        },function(data){
        });
    }else{
        $("#registBtn").attr("type","button");
        $("#warning").html("<div class='alert alert-danger' style='position: relative;'><a href='#' class='close' data-dismiss='alert'>&times;</a><strong>警告！</strong>亲，请填写好您的基本资料，方便我们注册~！</div>");
        showErrorShane();
    }
}

//错误抖动效果
function showErrorShane() {
    var ErrorShane = $("#warning .alert");
    ErrorShane.animate({left: '10.9785px'}, 50);
    ErrorShane.animate({left: '-10.9785px'}, 50);
    ErrorShane.animate({left: '0'}, 50);
}

$(function(){
    $("#form .fa-spin").hide();
});

