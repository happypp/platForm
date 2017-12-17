<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/6
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty courses}">
<table class="table table-hover" style="width: 80%;margin: 5% auto;">
  <input type="hidden" name="adminEmail" id="adminEmail" value="${sessionScope.admin.email}">
  <h2 style="margin-left: 2%;margin-top: 2%;"><span id="courseName" style="color: red;">${courseName}</span>_相关课程</h2>
  <thead>
  <tr>
    <th id="first_checkbox"><input type="checkbox" onclick="check()"></th>
    <th>序号</th>
    <th>课程名称</th>
    <th>点击次数</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${courses}" var="c" varStatus="cs">
    <tr>
      <th><input type="checkbox" name="checkCourse" data-id="${c.id}" data-lid="${id}"></th>
      <td>${cs.count}</td>
      <td>${c.title}</td>
      <td>${c.playnum}</td>
      <td><a href="javascript:void(0);" onclick="deleteById(${id},${c.id})">删除</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<button id="deleteCourse" class="btn btn-danger" style="width:20%;float: right;margin-right: 8%;margin-top: 5%;margin-bottom: 5%;">删除</button>
</c:if>