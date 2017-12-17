<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>注册界面</title>
  <%@ include file="part/head_global.jsp"%>
  <link rel="stylesheet" href="<c:url value="/css/regist.css" />">
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
<div style="padding-bottom: 13%;"></div>
<div id="registPage" class="container" style="background-color: rgba(255,255,255,.5);">
  <h2 class="col-sm-offset-4" style="margin-bottom:25px;">欢迎加入PlatForm</h2>
    <form class="form-horizontal" id="form" method="post">
      <div class="form-group has-feedback">
        <label for="lastname" class="col-sm-4 control-label font">
          邮箱 </label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="email" id="email"
                 value="" placeholder="请输入邮箱" aria-describedby="inputStatus">
          <span id="email-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
        </div>
        <span class="glyphicon form-control-feedback"></span>
        <span class="col-sm-4 error text" id="email-msg"></span>
      </div>
      <div class="form-group  has-feedback">
        <label for="firstname" class="col-sm-4 control-label font">
          昵称 </label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="name" id="name" value="" placeholder="昵称长度(2~15)" aria-describedby="inputStatus"	>
          <span id="name-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
        </div>
        <span class="glyphicon form-control-feedback"></span> <span class="col-sm-4 error text" id="name-msg"></span>
      </div>
      <div class="form-group has-feedback">
        <label for="lastname" class="col-sm-4 control-label font">
          密码 </label>
        <div class="col-sm-4">
          <input type="password" class="form-control" name="password" id="password"
                 value="" placeholder="输入密码(5~10)" aria-describedby="inputStatus">
          <span id="pwd-info" class="glyphicon  form-control-feedback" aria-hidden="true"></span>
        </div>
        <span class="glyphicon form-control-feedback"></span> <span
              class="col-sm-4 error text" id="pwd-msg"></span>
      </div>
      <div class="form-group has-feedback">
        <label for="lastname" class="col-sm-4 control-label font">
          确认密码 </label>
        <div class="col-sm-4">
          <input type="password" class="form-control" name="rpwd" id="rpwd"
                 value="" placeholder="确认密码" aria-describedby="inputStatus"><span id="rpwd-info" class="glyphicon  form-control-feedback"
                                                                                  aria-hidden="true"></span>
        </div>
        <span class="glyphicon form-control-feedback"></span> <span
              class="col-sm-4 error text" id="rpwd-msg"></span>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-4 col-sm-10">
          <button id="registBtn" type="" class="btn col-sm-2"
                  onclick="validate()">完成注册</button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="<c:url value="/index" />" type="button" class="btn btn-default">返回首页</a>
        </div>
      </div>
      <div id="warning"></div>
      <div class="spin-icon">
        <i class="fa fa-spinner fa-spin fa-4x"></i>
      </div>
    </form>
  </div>
<p style="float:left;color: #686868;font-size: 14px;position: absolute;bottom: 0;left:5%;">Copyright © 2016 xxxxx.com
  All Rights Reserved | 安徽 555号</p>
  <script src="<c:url value="/js/regist.js" />"></script>
</body>
</html>
