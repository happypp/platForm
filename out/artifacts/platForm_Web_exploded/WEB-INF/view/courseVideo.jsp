<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>${course.title}_PlatForm</title>
    <%@ include file="part/head_global.jsp"%>
    <link rel="stylesheet" href="<c:url value="/css/courseVideo.css"/>">
</head>
<body>
  <jsp:include page="part/header.jsp"></jsp:include>
  <div class="middle-box" data-pageid="1" >
    <jsp:include page="part/courseVideoHeader.jsp"></jsp:include>
    <div id="courseList" data-course="${course.title}">
        <jsp:include page="part/courseVideoMenu.jsp"></jsp:include>
        <!--折叠效果-->
        <div class="panel-group courseLists" id="accordion">
            <c:forEach items="${chapterVideoVos}" var="cvv" varStatus="vs">
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion"
                         href="#collapse${vs.count}">
                        <h3 class="panel-title">
                            <strong>${cvv.chapter.chaptername}</strong>
                        </h3>
                    </div>
                    <div id="collapse${vs.count}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul>
                                <c:forEach items="${cvv.videos}" var="cv">
                                    <li><a href="#" data-src="${cv.videoaddr}">${cv.videoname}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
            </div>
            </c:forEach>
        </div>

        <%--<div class="coursePost">--%>
        <%--后续制作~！尽请关注~！@xxxxxxxx--%>
        <%--</div>--%>
    </div>
    <jsp:include page="part/courseVideoRight.jsp"></jsp:include>
  </div>
  <jsp:include page="part/footer.jsp"></jsp:include>
  <script src="<c:url value="/js/courseVideo.js"/>"></script>
</body>
</html>
