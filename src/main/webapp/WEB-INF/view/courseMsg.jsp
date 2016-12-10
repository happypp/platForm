<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>${leveCourse.coursename}课程学习_PlatForm</title>
  <%@ include file="part/head_global.jsp"%>
  <link rel="stylesheet" href="<c:url value="/css/courseMsg.css"/>">
  <%--<meta name="viewport" content="width=device-width, initial-scale=1" />--%>
</head>
<body>
<jsp:include page="part/header.jsp"></jsp:include>

<div class="content">
  <jsp:include page="part/left.jsp"></jsp:include>
  <div  class="middle-box" data-pageid="1">
    <c:if test="${page.currentIndex == 1}">
      <div id="leveCourseDes" >
        <h3 style="margin-top: 0;"><strong style="color:#ff3419;">${leveCourse.coursename}</strong>简介</h3>
        <p style="text-indent: 2em;">${leveCourse.description}</p>
      </div>
    </c:if>
    <div id="courseMsg" class="col-md-12">
      <ul class="courseInfo" class="col-md-12">
          <c:forEach items="${courses}" var="course">
          <li class="col-md-4">
            <a href="<c:url value="/course/courseVideo/${course.id}"/>">
              <img src="${course.courseimg}" alt="">
              <h5><span>${course.title}</span></h5>
              <div class="tips">
                <p  class="text-ellipsis">${course.tip}</p>
                <span>更新完毕</span>
                <c:if test="${course.playnum > 0}">
                  <span style="font-weight: bold;margin-left: 10px;">点播次数：${course.playnum}次</span>
                </c:if>
              </div>
            </a>
          </li>
         </c:forEach>
      </ul>
      <div style="clear: both;margin-top: 10px;margin-bottom:40px;">
        <jsp:include page="part/pageInit.jsp"></jsp:include>
      </div>
    </div>

  </div>
</div>

<jsp:include page="part/footer.jsp"></jsp:include>
  <script src="<c:url value="/js/courseMsg.js"/>"></script>
</body>
</html>
