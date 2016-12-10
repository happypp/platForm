<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>错误页面</title>
</head>
<body>
    <strong style="color: red">${message}</strong>
    <a href="<c:url value="/index" />">返回首页</a>
</body>
</html>
