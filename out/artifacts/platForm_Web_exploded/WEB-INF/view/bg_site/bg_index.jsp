<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/1/3
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>platForm_后台管理</title>
    <%@ include file="bg_global.jsp"%>
    <script src="<c:url value="/js/xcConfirm.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
    <link rel="stylesheet" href="<c:url value="/css/bg_index.css" />">
    <style type="text/css">
      .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
    </style>
</head>
<%--<frameset rows="20%,*">--%>
  <%--<frame name="bg_header" src="<c:url value="/bg_page/bg_header.jsp" />"/>--%>
  <%--<frameset cols="15%,*">--%>
    <%--<frame name="bg_left" src="<c:url value="/bg_page/bg_left.jsp" />"/>--%>
    <%--<frame name="bg_content" src="<c:url value="/bg_page/bg_content.jsp" />"/>--%>
  <%--</frameset>--%>
<%--</frameset>--%>
<body style="width: 100%;height: 100%;margin: 0;padding: 0;">
  <div id="bg_header" style="height: 20%;border-bottom: 1px solid #ccc">
    <jsp:include page="../bg_page/bg_header.jsp"></jsp:include>
  </div>
  <div id="bg_left" style="height: 80%;width: 15%;float: left;border-right: 1px solid #ccc">
    <jsp:include page="../bg_page/bg_left.jsp"></jsp:include>
      <p class="btn btn-default" style="width: 100%;margin-top: 5%;margin-bottom: 2%;" onclick="addLcourse()">添加相关课程</p>
      <p class="btn btn-default" style="width: 100%;margin-bottom: 2%;" onclick="deleteLcourse()">删除相关课程</p>
      <p class="btn btn-default" style="width: 100%;margin-bottom: 2%;" onclick="editUserType()">教师人员的录入</p>
      <p class="btn btn-default" style="width: 100%;margin-bottom: 5%;" onclick="editTeacherType()">教师人员的删除</p>
  </div>
  <div id="bg_content" style="height: 80%;width: 84%;float: left;">
      <!--添加-->
      <div id="addLcourse" style="margin-top: 10%;display: none;">
          <div class="form-group">
              <div class="form-group">
                  <label class="col-sm-2 control-label" style="text-align: right;">课程类型</label>
                  <div class="col-sm-6">
                      <input type="radio" name="course">
                      <select name="lid" class="selection-handle">
                          <c:forEach items="${courseVos}" var="cvo" varStatus="cv">
                            <option  value="${cvo.id}">${cvo.type}</option>
                          </c:forEach>
                      </select>
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="radio" name="course"><input type="text" >
                      <label style="color:red;">*</label>
                  </div>
              </div>
              <br>
              <div class="form-group">
                  <label class="col-sm-2 control-label" style="text-align: right;margin-top: 5px;">一级课程</label>
                  <div class="col-sm-6">
                      <input type="text" name="coursename" class="form-control">
                  </div>
                  <label style="color:red;">*</label>
              </div>
              <br>
              <div class="form-group" >
                  <label class="col-sm-2 control-label" style="text-align: right;margin-top: 5px;">课程描述</label>
                  <div class="col-sm-6" style="margin-bottom: 50px;">
                      <textarea name="description" id="descript" cols="45" rows="10" class="form-control" style="resize: none;"></textarea>
                  </div>
                  <label style="color:red;">*</label>
              </div>
              <div class="form-group" style="margin-top: 100px;">
                  <label class="col-sm-1 control-label"></label>
                  <div class="col-sm-8 addCourse_btn" style="margin-left: 50px;">
                      <input type="button" value="保存" class="btn col-sm-12" style="outline-style: none;" onclick="saveCourse()">
                  </div>
              </div>
          </div>
      </div>
      <!--删除-->
      <div id="deleteLcourse" style="display: block;">
          <table class="table table-hover" style="width: 80%;margin: 5% auto;">
              <input type="hidden" name="adminEmail" id="adminEmail" value="${sessionScope.admin.email}">
              <thead>
              <tr>
                  <th id="first_checkbox"><input type="checkbox" onclick="check()"></th>
                  <th>序号</th>
                  <th>课程类型</th>
                  <th>一级课程名称</th>
                  <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${lcourseTypeVos}" var="lc" varStatus="lct">
                  <tr>
                          <th><input type="checkbox" name="checkCourse" data-id="${lc.ct_id}" data-lid="${lc.l_id}"></th>
                          <td>${lct.count}</td>
                          <td>${lc.ct_name}</td>
                          <td>${lc.l_name}</td>
                          <td><a href="javascript:;" onclick="deleteById(${lc.ct_id},${empty lc.l_id ? 0 : lc.l_id})">删除</a></td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
          <button id="deleteCourse" class="btn btn-danger" style="width:20%;float: right;margin-right: 8%;margin-top: 5%;margin-bottom: 5%;">删除</button>
      </div>
      <div id="editUserType" style="display: none;margin: 15% 0 0 22%;">
          <div class="form-group">
              <label class="col-sm-2 control-label" style="text-align: right;margin-top: 5px;">激活邮箱：</label>
              <div class="col-sm-4">
                  <input type="text" name="userEmail" class="form-control" placeholder="请输入需要激活的邮箱">
              </div>
              <label style="color:red;">*</label>
          </div>
          <div class="form-group" style="margin-top: 100px;">
              <div class="col-sm-6 addCourse_btn" style="margin-left: 10px;">
                  <input type="button" value="激活" class="btn col-sm-12" style="outline-style: none;" onclick="updateUserType()">
              </div>
          </div>
      </div>
      <div id="editTeacherType" style="display: none;margin: 15% 0 0 20%;">
          <div class="form-group">
              <label class="col-sm-3 control-label" style="text-align: right;margin-top: 5px;">教师-->普通用户：</label>
              <div class="col-sm-4">
                  <input type="text" name="teacherEmail" class="form-control" placeholder="请输入需要修改的邮箱">
              </div>
              <label style="color:red;">*</label>
          </div>
          <div class="form-group" style="margin-top: 100px;margin-right: 5%;">
              <div class="col-sm-6 addCourse_btn" style="margin-left: 10px;">
                  <input type="button" value="更新" class="btn col-sm-12" style="outline-style: none;"
                         onclick="updateTeacherType()">
              </div>
          </div>
      </div>
  </div>

  <script src="<c:url value="/js/bg_index.js" />"></script>
</body>
</html>
