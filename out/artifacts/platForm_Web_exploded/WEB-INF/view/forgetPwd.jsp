<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>PlatForm_找回密码</title>
  <%@ include file="part/head_global.jsp"%>
  <script src="<c:url value="/js/xcConfirm.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
  <link rel="stylesheet" href="<c:url value="/css/forgetPwd.css" />">
  <style type="text/css">
    .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
  </style>
</head>
<body style="background-repeat: no-repeat;background-size: cover;">
<script>
  var num = parseInt(Math.random() * 4) + 1;
  if (num === 1) {
    $('body').css("background-image", "url('<c:url value='/image/regist1.jpg'/>')");
  } else if (num === 2) {
    $('body').css("background-image", "url('<c:url value='/image/regist2.jpg'/>')");
  } else if (num === 3) {
    $('body').css("background-image", "url('<c:url value='/image/regist3.jpg'/>')");
  } else {
    $('body').css("background-image", "url('<c:url value='/image/regist4.jpg'/>')");
  }
</script>
<div id="hide"></div>
<div style="padding-bottom: 13%;"></div>
<div id="forgetPwdPage" class="container" style="background-color: rgba(255,248,248,.4);">
  <h2 style="text-align: center;margin-bottom: 25px;">重置密码</h2>
  <div class="form-horizontal" id="form">
    <div class="form-group has-feedback">
      <label  class="col-sm-4 control-label font">
        邮箱 </label>
      <div class="col-sm-4">
        <input type="text" class="form-control" name="email" id="email"
               value="" placeholder="请输入邮箱" aria-describedby="inputStatus">
        <span id="email-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
      </div>
      <span class="col-sm-4 error text" id="email-msg" style="margin-top: 0.5%;"></span>
    </div>
    <div class="form-group  has-feedback">
      <label  class="col-sm-4 control-label font">
        验证码 </label>
      <div class="col-sm-4">
        <input disabled="disabled" type="text" class="form-control" name="code" id="code" value="" placeholder="请输入验证码" aria-describedby="inputStatus"	>
        <span id="code-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
      </div>
      <input type="button" class="btn btn-default" style="background-color: #098ddf;" onclick="getCode(this)" value="获取验证码">
    </div>
    <div class="form-group has-feedback">
      <label  class="col-sm-4 control-label font">
        密码 </label>
      <div class="col-sm-4">
        <input type="password" disabled="disabled" class="form-control" name="pwd" id="pwd"
               value="" placeholder="输入密码(5~10)" aria-describedby="inputStatus">
        <span id="pwd-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
      </div>
      <span class="col-sm-4 error text" id="pwd-msg" style="margin-top: 0.5%;"></span>
    </div>
    <div class="form-group has-feedback">
      <label  class="col-sm-4 control-label font">
        确认密码 </label>
      <div class="col-sm-4">
        <input type="password" disabled="disabled" class="form-control" name="rpwd" id="rpwd"
               value="" placeholder="确认密码" aria-describedby="inputStatus"><span id="rpwd-info" class="glyphicon  form-control-feedback"
                                                                                aria-hidden="true"></span>
      </div>
      <span class="col-sm-4 error text" id="rpwd-msg" style="margin-top: 0.5%;"></span>
    </div>
    <div class="form-group" style="text-align: center;">
      <div class="col-sm-10">
        <button id="updateBtn" type="button" class="btn">更新</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<c:url value="/index" />" type="button" class="btn btn-default">返回首页</a>
      </div>
    </div>
    <div id="warning"></div>
    <div class="spin-icon" style="display: none;">
      <i class="fa fa-spinner fa-spin fa-4x"></i>
    </div>
  </div>
</div>
<p style="float:left;color: #686868;font-size: 14px;position: absolute;bottom: 0;left:5%;">Copyright © 2016 xxxxx.com
  All Rights Reserved | 安徽 555号</p>
<script>
  var countdown = 60;
  function getCode(btn_code){
    var email = $("#email").val();
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; //验证邮箱的正则
    var txt = null;
    if(email.trim() != "" && reg.test(email) && $("#email-msg").text()==""){
      if(countdown == 60){
        /*发送邮箱给用户获取其验证码*/
        $("#code").removeAttr("disabled");
        var url = getProjectRootPath() + "/user/emailToUser";
        $.post(url,{
            'email' : email
        },function(data){
            if(data == 1){
              window.wxc.xcConfirm("网络不正常~！请检查网络~！", window.wxc.xcConfirm.typeEnum.error);
            }
        });
        countdown--;
      }else if(countdown == 0){
        btn_code.value="获取验证码";
        countdown = 60;
        $(btn_code).attr("disabled",false);
        return false;
      } else {
        btn_code.value="重新发送(" + countdown + ")";
        countdown--;
        $(btn_code).attr("disabled","disabled");
      }
      setTimeout(function() {
        getCode(btn_code);
      },998);
    }else if($("#email-msg").text()==""){
      window.wxc.xcConfirm("邮箱不能为空~！", window.wxc.xcConfirm.typeEnum.error);
    }else{
      window.wxc.xcConfirm($("#email-msg").text(), window.wxc.xcConfirm.typeEnum.error);
    }
  }
</script>
<script src="<c:url value="/js/forgetPwd.js" />"></script>
</body>
</html>
