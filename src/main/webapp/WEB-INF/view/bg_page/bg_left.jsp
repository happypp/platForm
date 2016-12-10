<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/6
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-menu" style="margin-bottom: 10px;">
  <c:forEach items="${courseVos}" var="cvo" varStatus="cv">
    <a href="#userMeun${cv.count}" class="nav-header menu-first collapsed" data-toggle="collapse">
        ${cvo.type}</a>
    <ul id="userMeun${cv.count}" class="nav nav-list collapse menu-second">
      <c:forEach items="${cvo.leveCourses}" var="cvos">
        <li><a href="<c:url value="/bg_courseIndex/${cvos.id}" />"> ${cvos.coursename}</a></li>
      </c:forEach>
    </ul>
  </c:forEach>
</div>