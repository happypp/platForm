<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="top-box " id="nav" style="position: fixed;">
	<div id="logo"><a href="<c:url value="/index" />">platform</a></div>
	<div id="menu">
		<ul>
			<li>
				<a href="<c:url value="/course/courseIndex" />">课程</a>
			</li>
			<li><a href="#" >期待ing</a></li>
			<li><a href="#" >期待ing</a></li>
			<li><a href="<c:url value="/discuss/subjectIndex/1" />" >社区</a></li>
		</ul>
	</div>
	<div id="login-area"  style="margin-right: 20px;">
		<ul>
			<c:if test="${empty sessionScope.user}">
				<li class="regist"><a href="<c:url value="/user/regist" />">注册</a></li>
				<li class="login" data-toggle="modal" data-target="#myLogin"><a href="#" >登录</a></li>
			</c:if>
			<c:if test="${!empty sessionScope.user}">
				<c:if test="${sessionScope.user.type == '教师'}">
					<li id="upload" style="margin-right: 50px;width: 60px;">
						<a href="<c:url value="/upload/uploadUI" />">上传</a></li>
				</c:if>
				<c:if test="${sessionScope.user.type == '学生'}">
					<li id="upload" style="margin-right: 50px;width: 60px;">
						<a href="javascript:"></a></li>
				</c:if>
				<c:if test="${sessionScope.user.type == '管理员'}">
					<li id="upload" style="margin-right: 50px;width: 60px;">
						<a href="javascript:"></a></li>
				</c:if>
				<li id="uAvatar" style="padding-top: 16px;padding-left: 25px;">
				<a href="<c:url value="/course/courseIndex" />"><img  class="img-circle avatar" id="avatar" data-uid="${sessionScope.user.id }" data-name="${sessionScope.user.name }"
						data-avatar="${sessionScope.user.avatar }" data-image="" data-placement="bottom"
						  data-content="${sessionScope.user.type }" ></a></li>
			</c:if>
		</ul>
	</div>

	<div id="search-area">
		<a href="javascript:void (0);" class="fa fa-search" title="搜索" onclick="searchCourse()"></a>
		<input class="search-input" style="width: 0;" placeholder="请输入想搜索的内容..." type="text" x-webkit-speech>
	</div>

</div>

<!--鼠标滑过头像出现该用户的基本信息-->
<div id="userInfo" style="display: none;">
	<div class="userHeader">
		<ul>
			<li style="padding: 20px;margin: 0;">
				<img  class="img-circle avatar" data-avatar="${sessionScope.user.avatar }">
			</li>
			<li style="color:white;">${sessionScope.user.name }</li>
		</ul>
	</div>
	<div class="userContent">
		<c:if test="${fn:length(sessionScope.user.videoinfo) > 0}">
			<p style="margin-top: 30px;margin-left: 20px;">
				<strong >${chapterVideoVo.course.title}</strong><br><br>
				<span>${sessionScope.user.videoinfo}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<c:url value="/course/courseVideo/${chapterVideoVo.chapter.courseid}" />" data-src="${chapterVideoVo.video.videoaddr}">继续</a>
			</p>
		</c:if>
		<ul>
			<li style="float:left;margin-left: 10px;"><a href="<c:url value="/user/userInfoUI/${sessionScope.user.id}" />">个人设置</a></li>
			<li class="userLogOut" style="float:right;margin-right: 50px;"><a href="#">退出</a></li>
		</ul>
	</div>
</div>
<!--/结束-->

<!-- 登录对话框 -->
<div  class="modal fade container" id="myLogin" tabindex="-1"
	 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	 data-backdrop="true" style="margin-top:100px;">
	<div class="modal-dialog " style="width:450px;">
		<div class="modal-content">
			<div class="modal-header text-center" style="background-color: red;">
				<h4>
					<span style="color: white;">欢迎登录</span>
					<a id="close" href="javascript:"  data-dismiss="modal"
					   style="position:absolute; right:10px; top:5px;background-color: red;">&times;
					</a>
				</h4>
			</div>
			<div class="modal-body center-block">
				<div class="form-horizontal row">
					<div class="form-group center-block">
						<label for="email" class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="emailInput"
								   name="email" placeholder="xxxx@xx.com">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-4 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="pwdInput"
								   name="password" placeholder="请输入密码">
						</div>
					</div>

					<div class="form-group div-checkbox">
						<div class="col-sm-offset-4 col-sm-8">
							<div class="checkbox">
								<label> <input type="checkbox"> 记住我
								</label>
							</div>
						</div>
					</div>

					<!-- 警告框 -->
					<div class="alert alert-danger alert-dismissible fade in" role="alert"
						 style="text-align: center;position: relative;">
						<button type="button" class="close c-close">
						<span aria-hidden="true">&times;</span>
						</button>
						<strong></strong>
					</div>
					<!-- /警告框 -->

					<div class="col-sm-offset-2 col-sm-10" >
						<button id="btnLogin" type="submit" class="btn col-sm-10" onclick="login()">登录</button>
					</div>
					<div class="col-sm-offset-2 col-sm-10">
						<a class="hoverToRed" href="<c:url value="/user/regist" />"  >创建新账号</a>
						<a class="hoverToRed" href="<c:url value="/user/forgetPwdUI" />" style="margin-left:150px;" onClick="">忘记密码？</a>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!-- /登录对话框 -->