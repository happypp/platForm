<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>${sessionScope.user.name}_个人信息</title>
    <%@ include file="part/head_global.jsp"%>
    <script src="<c:url value="/js/xcConfirm.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
    <link rel="stylesheet" href="<c:url value="/css/userMsg.css" />">
    <style type="text/css">
      .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
    </style>
</head>
  <body>
    <jsp:include page="part/header.jsp"></jsp:include>
    <div id="main" style="width: 80%;margin:0 auto;height: 1000px;">
      <div id="main-left">
        <ul>
          <li data-form="userMsg_form" class="liActive">个人信息</li>
          <li data-form="userPwd_form">修改密码</li>
        </ul>
      </div>
      <form action="<c:url value="/upload/editUserInfo" />" enctype="multipart/form-data" role="form"
            class="form-horizontal" style="margin-top: 10%;" id="userMsg_form" method="post">
        <input type="hidden" value="${sessionScope.user.id}" name="uid" />
        <div class="form-group">
          <label  class="col-sm-2 control-label">昵称</label>
          <div class="col-sm-6">
            <input type="text" class="form-control" name="name"
                   placeholder="请输入昵称">
          </div>
          <label style="color:red;" id="msgInfo">*</label>
        </div>
        <div class="form-group">
          <label  class="col-sm-2 control-label">职位</label>
          <div class="col-sm-6">
            <input type="text" class="form-control" name="type"
                    disabled="disabled">
          </div>
          <label style="color:red;">*</label>
        </div>
        <div class="form-group">
          <label for="avatarImg" class="col-sm-2 control-label">更换头像</label>
          <div class="col-sm-6" style="margin-top: 5px;">
            <input type="file" id="avatarImg" name="avatar">
            <label style="color:red;">*</label>
          </div>
        </div>
        <div class="form-group" style="margin-top: 100px;">
          <label class="col-sm-2 control-label"></label>
          <div class="col-sm-6" id="userMsgSave">
            <input type="button" value="保存" class="btn col-sm-12" style="outline-style: none;">
          </div>
        </div>
      </form>
      <!--修改密码-->
      <form role="form" class="form-horizontal" id="userPwd_form" style="display: none;margin-top: 10%;">
        <div class="form-group">
          <label  class="col-sm-2 control-label">原密码</label>
          <div class="col-sm-6">
            <input type="password" class="form-control" name="password"
                   placeholder="请输入原密码">
          </div>
          <label style="color:red;" id="passwordMsg">*</label>
        </div>
        <div class="form-group">
          <label  class="col-sm-2 control-label">新密码</label>
          <div class="col-sm-6">
            <input type="password" class="form-control" name="pwd1"
                   placeholder="请输入新密码">
          </div>
          <label style="color:red;" id="pwd1">*</label>
        </div>
        <div class="form-group">
          <label  class="col-sm-2 control-label">确认密码</label>
          <div class="col-sm-6">
            <input type="password" class="form-control" name="pwd2"
                   placeholder="请输入确认密码">
          </div>
          <label style="color:red;" id="pwd2">*</label>
        </div>
        <div class="form-group" style="margin-top: 100px;">
          <label class="col-sm-2 control-label"></label>
          <div class="col-sm-6" id="userPwdEdit">
            <input type="button" value="保存" class="btn col-sm-12" style="outline-style: none;">
          </div>
        </div>
      </form>
    </div>
    <jsp:include page="part/footer.jsp"></jsp:include>
    <script src="<c:url value="/js/userMsg.js" />"></script>
  </body>
</html>
