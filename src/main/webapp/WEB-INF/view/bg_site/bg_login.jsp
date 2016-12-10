<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/3
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>platForm_后台管理</title>
    <%@ include file="bg_global.jsp"%>
    <script src="<c:url value="/js/xcConfirm.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
    <link rel="stylesheet" href="<c:url value="/css/bg_login.css" />">
    <style type="text/css">
        .sgBtn {
            width: 135px;
            height: 35px;
            line-height: 35px;
            margin-left: 10px;
            margin-top: 10px;
            text-align: center;
            background-color: #0095D9;
            color: #FFFFFF;
            float: left;
            border-radius: 5px;
        }
    </style>
</head>
<body style="background-color: #FFFF99">
    <div class="form-horizontal col-sm-5" role="form">
      <div class="form-group center-block">
        <label for="email" class="col-sm-4 control-label">邮箱</label>
        <div class="col-sm-6">
          <input type="email" class="form-control" id="emailInput"
                 name="email" placeholder="xxxx@xx.com">
        </div>
      </div>
      <div class="form-group">
        <label for="password" class="col-sm-4 control-label">密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="pwdInput"
                 name="password" placeholder="请输入密码">
        </div>
      </div>

      <div class="form-group div-checkbox">
        <div class="col-sm-offset-4 col-sm-8">
          <div class="checkbox">
            <label> <input type="checkbox"> 记住我
            </label>
          </div>
        </div>
      </div>

      <div class="col-sm-10" style="margin-left: 20%;">
        <button id="btnLogin" type="submit" class="btn col-sm-10" onclick="login()">登录</button>
      </div>
    </div>


    <script src="<c:url value="/js/bg_login.js" />"></script>
</body>
</html>
