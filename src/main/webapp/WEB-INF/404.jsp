<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/2
  Time: 17:59
  To change this template use File | Settings | File Templates.
  错误页面404（出错了都来我这吧~！(出了不能增长执行的异常都来我这吧~！)）
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>404</title>
    <script src="<c:url value="/js/jquery.min.js" />"></script>


  </head>
  <body style="margin: 0;padding: 0;">
  <%--<h4><font color="red">本页欢迎您的到来，如果在操作过程中遇到了什么问题，我们深感抱歉~！</font></h4>--%>
  <div>
    <img id="img" style="width: 100%;height: 100%;">
  </div>
  </body>
  <script>
    var num = parseInt(Math.random() * 3) + 1;
    if (num === 1) {
      $('#img').attr("src", "<c:url value='/404_images/404_1.png' />");
    } else if (num === 2) {
      $('#img').attr("src", "<c:url value='/404_images/404_2.png' />");
    } else {
      $('#img').attr("src", "<c:url value='/404_images/404_3.jpg' />");
    }
  </script>
</html>
