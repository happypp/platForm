<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>搜索_${courseText}</title>
  <%@ include file="part/head_global.jsp"%>
  <link rel="stylesheet" href="<c:url value="/css/searchIndex.css" />">
</head>
<body>

<jsp:include page="part/header.jsp"></jsp:include>
<div style="min-height: 1000px;" data-course="${courseText}" id="searchCourseIndex">
  <div  id="searchInput">
    <div style="padding-top: 120px;text-align: center;" class="center-block">
      <div style="position: relative;width: 40%;display: inline-block; margin: 0 auto;" class="search-input">
        <input type="text" class="search-input form-control" placeholder="请输入想搜索的内容..." x-webkit-speech aria-describedby="inputStatus">
        <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="cursor: hand"></span>
      </div>
      <a href="javascript:void (0)" title="搜索" onclick="searchCourseTwo()">搜索</a>
    </div>
  </div>

  <div id="courseList" class="post-contents">
    <div id="courseMenu">
      <ul>
        <li><strong><span style="color: red;">${courseText}</span>_相关课程</strong></li>
      </ul>
    </div>
    <div id="searchCourseInfo">
      <c:if test="${ !empty courseList}">
        <c:forEach items="${courseList}" var="course">
          <ul>
            <li>
              <a href="<c:url value="/course/courseVideo/${course.id}"/>">
              <img src="${course.courseimg}" alt="${course.title}"></a>
            </li>
            <li>${course.tip}<br>${course.needinfo} <br>${course.studyinfo}</li>
          </ul>
        </c:forEach>
      </c:if>
      <c:if test="${empty courseList}">
        <p>抱歉所查找的课程不存在~！</p>
      </c:if>
    </div>
    <div style="float: right;padding: 20px 0 10px;list-style: none;width: 45%;margin: 0 auto;height: 200px;">
      <jsp:include page="part/pageInit.jsp"></jsp:include>
    </div>
  </div>
</div>
<jsp:include page="part/footer.jsp"></jsp:include>

<script src="<c:url value="/js/searchIndex.js" />"></script>
</body>
</html>
