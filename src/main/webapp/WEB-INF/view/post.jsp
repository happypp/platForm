<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
  <title>${subject.title }_社区_PaltForm</title>
  <%@ include file="part/head_global.jsp"%>
  <script src="<c:url value="/js/xcConfirm.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/css/summernote.css" />">
  <link rel="stylesheet" href="<c:url value="/css/xcConfirm.css" />">
  <link rel="stylesheet" href="<c:url value="/css/post.css" />">
  <%--<style>--%>
    <%--&lt;%&ndash;body{&ndash;%&gt;--%>
      <%--&lt;%&ndash;background-image: url(<c:url value="/image/post_bg.png" />);&ndash;%&gt;--%>
      <%--&lt;%&ndash;background-repeat: no-repeat;&ndash;%&gt;--%>
    <%--&lt;%&ndash;}&ndash;%&gt;--%>
  <%--</style>--%>
  <style type="text/css">
    .sgBtn{width: 135px; height: 35px; line-height: 35px; margin-left: 10px; margin-top: 10px; text-align: center; background-color: #0095D9; color: #FFFFFF; float: left; border-radius: 5px;}
  </style>
</head>
<body>
    <jsp:include page="part/header.jsp"></jsp:include>
    <div id="post" class="container middle-box" data-pageid="4" data-sid="${subject.id}">
      <!-- 发帖人 -->
      <div id="post-title" class="title-font">
        <h3><a href="<c:url value="/discuss/subjectIndex/${backPageIndex}" />">${subject.title }</a></h3>
        <div class="subject">
          <a href="<c:url value="/discuss/subjectIndex/${backPageIndex}" />">&nbsp;返回</a>&nbsp;>>
          <a href="#"><span>综合</span></a>
        </div>
      </div>
      <div class="post-contents">
        <div class="row">
          <div class="post-avatar">
            <div class="content">
              <a href="#"><img class="img-circle subjectAvatar" data-avatar="${author.avatar}" data-placement="bottom"
                               data-content="${author.name}"/></a>
            </div>
          </div><!--内容显示-->
          <div class="post-content">
            <div class="authorName">
              ${author.name}
            </div>
          </div>
              <span><small>${createTime}</small></span>
          <div class="article">
            <div class="article-content">
              ${subject.content}
            </div>
            <div class="msg">
              <ul class="clearfix nav navbar-nav">
                <li>
                  <a href="#"><h4>创建者</h4><img class="img-circle subjectAvatar" data-avatar="${author.avatar}" data-placement="bottom"
                                               data-content="${author.name}"/><span>${createTime}</span></a>
                </li>
                <li>
                  <a href="#"><h4>最后回复</h4>
                    <c:if test="${lastPost.userName != null}">
                      <img class="img-circle postAvatar" data-avatar="${lastPost.userAvatar}" data-placement="bottom"
                                                data-content="${lastPost.userName}"/><span>${lastPost.postTime}</span>
                    </c:if>
                  </a>
                </li>
                <li>
                  <a><h4>回复数量</h4>
                    <p>
                      ${num}
                    </p>
                  </a>
                </li>
                <li>
                  <a><h4>浏览次数</h4>
                    <p>
                      ${subject.visits}
                    </p>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!--回复帖子-->
        <c:forEach items="${postVos}" var="pvo">
          <c:if test="${!empty pvo}">
        <div class="row">
          <div class="post-avatar">
            <div class="content">
              <a href="#"><img class="img-circle postAvatar" data-avatar="${pvo.userAvatar}" data-placement="bottom" data-content="${pvo.userName}"></a>
            </div>
          </div><!--内容显示-->
          <div class="post-content">
            <div class="authorName">
                ${pvo.userName}
            </div>
          </div>
             <span><small>${pvo.postTime}</small>
              <c:if test="${sessionScope.user.type == '管理员'}">
                <i class="fa fa-trash-o fa-1x" title="删除" onclick="delete_post(${pvo.pid})" style="cursor: pointer;"></i>
              </c:if>
             </span>

          <div class="article">
            <div class="article-content">
              ${pvo.content}
            </div>
            <div>
              <button style="float: right;" onclick="replyUser_btn(this)" class="btn btn-default">回复</button>
              <br><br>
              <div style="display: none;height: 100px;" data-pid="${pvo.pid}" data-subid="${subject.id}" data-pageindex="${page.currentIndex}">
                <input class="form-control " type="text">
                <br>
                <button  style="float: right;margin-left: 5px;" onclick="reply_off(this)" class="btn btn-default">取消</button>
                <button  style="float: right;" onclick="replyUser_sub(this)" class="btn btn-default">评论</button>
              </div>
            </div>
            <!--reply开始-->
            <c:if test="${!empty pvo.rPostVos}">
              <c:forEach items="${pvo.rPostVos}" var="rp">
            <div class="post-avatar">
              <div class="content">
                <a href="#"><img class="img-circle postAvatar" data-avatar="${rp.user.avatar}" data-placement="bottom" data-content="${rp.user.name}"></a>
              </div>
            </div><!--内容显示-->
            <div class="post-content">
              <div class="authorName">
                  ${rp.user.name}
              </div>
            </div>
             <span><small>${rp.time}</small>
              <c:if test="${sessionScope.user.type == '管理员'}">
                <i class="fa fa-trash-o fa-1x" title="删除" onclick="delete_post(${rp.post.id})" style="cursor: pointer;"></i>
              </c:if>
             </span>

            <div class="article">
              <div class="article-content">
                  ${rp.post.content}
              </div>
            </div>
              </c:forEach>
            </c:if>
            <!--reply结束-->
          </div>
        </div>
          </c:if>

        </c:forEach>
        <jsp:include page="part/pageInit.jsp"></jsp:include>
      </div>


      <div id="editor" style="margin-top:100px;">
        <button style="outline: none;" class="btn btn-default" id="new-reply" data-container="body" data-toggle="popover" data-placement="top" data-content="请登录方可回复~!">
          回复本主题
        </button>&nbsp;&nbsp;<lable style="color: red;display: none;" >*回复内容不能空~！</lable>
        <div id="summernote"></div>
      </div>
      <div class="spin-icon">
        <i class="fa fa-spinner fa-spin fa-5x"></i>
      </div>
      </div>
    <%--</div><!-- 热门主题-->--%>


    <jsp:include page="part/footer.jsp"></jsp:include>
    <script src="<c:url value="/js/summernote.min.js" />"></script>
    <script src="<c:url value="/js/post.js" />"></script>

</body>
</html>
