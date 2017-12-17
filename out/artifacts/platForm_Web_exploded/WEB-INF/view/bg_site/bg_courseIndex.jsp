<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/3
  Time: 13:02
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
  <link rel="stylesheet" href="<c:url value="/css/bg_index.css" />">
  <style type="text/css">
    .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
  </style>
</head>
<%--<frameset rows="20%,*">--%>
<%--<frame name="bg_header" src="<c:url value="/bg_page/bg_header.jsp" />"/>--%>
<%--<frameset cols="15%,*">--%>
<%--<frame name="bg_left" src="<c:url value="/bg_page/bg_left.jsp" />"/>--%>
<%--<frame name="bg_content" src="<c:url value="/bg_page/bg_content.jsp" />"/>--%>
<%--</frameset>--%>
<%--</frameset>--%>
<body style="width: 100%;height: 100%;margin: 0;padding: 0;">
<div id="bg_header" style="height: 20%;border-bottom: 1px solid #ccc">
  <jsp:include page="../bg_page/bg_header.jsp"></jsp:include>
</div>
<div id="bg_left" style="height: 80%;width: 15%;float: left;border-right: 1px solid #ccc">
  <jsp:include page="../bg_page/bg_left.jsp"></jsp:include>
  <a class="btn btn-default" style="width: 100%;margin-top: 5%;margin-bottom: 2%;" href="<c:url value="/bg_index" />">返回</a>
</div>
<div id="bg_content" style="height: 80%;width: 84%;float: left;">
  <jsp:include page="../bg_page/bg_content.jsp"></jsp:include>
</div>
<script src="<c:url value="/js/bg_courseIndex.js" />"></script>
</body>
</html>
