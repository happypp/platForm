<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="courseInfo">
    <h3 style="font-weight:bold;margin-bottom: 5px;">讲师提示</h3>
    <hr>
    <c:if test="${!empty course.courseware}">
    <a href="${course.courseware}" target="_blank"><i class="fa fa-folder-open fa-2x" style="color: #000;">相关课件下载</i></a>
    <hr>
    </c:if>
  <c:if test="${!empty course.needinfo}">
    <div>
      <h4 style="font-weight:bold;margin-bottom: 10px;">课程须知</h4>
      <p>${course.needinfo}</p>
      <h4 style="font-weight:bold;margin-bottom: 10px;">老师告诉你能学到什么？</h4>
      <p>${course.studyinfo}</p>
    </div>
  </c:if>
</div>