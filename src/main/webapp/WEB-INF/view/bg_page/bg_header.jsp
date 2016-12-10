<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/6
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="" style="text-align: center;position: absolute;top: 5%;left: 10%;right: 10%;">
  <h2>platForm_后台管理</h2>
</div>
<div style="float: right;margin-right: 2%;margin-top: 1%;">
  欢迎~！${sessionScope.admin.name} <a href="<c:url value="/bg_logout" />">注销</a>
</div>