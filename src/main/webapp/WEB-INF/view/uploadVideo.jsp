<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>上传视频</title>
  <%@ include file="part/head_global.jsp"%>
  <script src="<c:url value="/js/xcConfirm.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
  <link rel="stylesheet" href="<c:url value="/css/uploadVideo.css" />">
  <style type="text/css">
    .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
  </style>
</head>
<body>
<jsp:include page="part/header.jsp"></jsp:include>
<!--遮罩层开始-->
<div class="masklayer" style="width: 100%;height: 100%;background-color: rgba(0,0,0,.4);position: fixed;z-index: 333;display: none;"></div>
<!--遮罩层结束-->
<div id="uploadContent">
  <form  method="post" action="<c:url value="/upload/video" />" enctype="multipart/form-data" role="form" class="form-horizontal">
    <div class="form-group">
      <div class="form-group">
        <label class="col-sm-2 control-label">二级课程</label>
        <div class="col-sm-6" style="margin-top: 5px;">
          <select name="lid" class="selection-handle">
            <c:forEach items="${leveCourses}" var="lc">
              <option  value="${lc.id}">${lc.coursename}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">课程标题</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="title"
                 placeholder="请输入课程标题">
        </div>
        <label style="color:red;">优先填写有惊喜哦~！</label>
      </div>
      <div class="form-group">
        <label for="courseImg" class="col-sm-2 control-label">课程图片</label>
        <div class="col-sm-6" style="margin-top: 5px;">
          <input type="file" id="courseImg" name="courseimg">
          <label style="color:red;">*</label>
        </div>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">课程tip</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="tip"
                 placeholder="请输入课程tip">
        </div>
        <label style="color:red;">*</label>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">课程需知</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="needinfo"
                 placeholder="请输入课程需求">
        </div>
        <label style="color:red;">*</label>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">课程学习</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="studyinfo"
                 placeholder="请输入课程学习">
        </div>
        <label style="color:red;">*</label>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">章节名称</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="chaptername"
                 placeholder="请输入章节名称">
        </div>
        <label style="color:red;">*</label>
      </div>
      <div class="form-group">
        <label  class="col-sm-2 control-label">视频名称</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" name="videoname"
                 placeholder="请输入视频名称">
        </div>
        <label style="color:red;">*</label>
      </div>
      <%--上传视频--%>
      <%--<div class="form-group">--%>
      <%--<label for="courseVideo" class="col-sm-2 control-label">视频</label>--%>
      <%--<div class="col-sm-6" style="margin-top: 5px;">--%>
      <%--<input type="file" id="courseVideo" name="videoaddr" >--%>
      <%--<label style="color:red;">*</label>--%>
      <%--</div>--%>
      <%--</div>--%>
      <%--改用上传视频连接地址--%>
      <div class="form-group">
        <label for="courseVideo" class="col-sm-2 control-label">视频连接</label>
        <div class="col-sm-6" style="margin-top: 5px;">
          <input type="text" id="courseVideo" name="videoaddr" class="form-control" placeholder="视频连接地址">
        </div>
        <label style="color:red;">*</label>
      </div>
      <div class="form-group">
        <label for="courseVideo" class="col-sm-2 control-label">相关课件</label>
        <div class="col-sm-6" style="margin-top: 5px;">
          <input type="text" class="form-control" id="courseware" name="courseware" placeholder="相关课件的地址">
        </div>
        <label style="color:red;"></label>
      </div>
      <div class="form-group">
        <label for="courseVideo" class="col-sm-2 control-label"></label>
        <div class="col-sm-6">
          <input type="submit" value="上传" class="btn btn-default">
          <input type="reset" value="清空" class="btn btn-default">
        </div>
      </div>
    </div>
  </form>
</div>
<div class="spin-icon" style="color: white;z-index:444;">
  <i class="fa fa-spinner fa-spin fa-4x"></i>
</div>
<jsp:include page="part/footer.jsp"></jsp:include>
<script src="<c:url value="/js/uploadVideo.js" />"></script>
</body>
</html>
