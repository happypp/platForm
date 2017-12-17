<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>社区_PlatForm</title>
    <%@ include file="part/head_global.jsp"%>
    <link rel="stylesheet" href="<c:url value="/css/summernote.css" />">
    <link rel="stylesheet" href="<c:url value="/css/subject.css" />">
</head>
<body>
    <jsp:include page="part/header.jsp"></jsp:include>

    <div class="middle-box" data-pageid="4" style="min-height: 800px;width: 80%;margin:0 auto;padding-top: 66px;">
      <div class="category">
        <button class="btn btn-default active">综合</button>
        <button class="btn btn-default">经验交流</button>
        <button class="btn btn-default">闲聊</button>
        <button class="btn btn-default pull-right" id="new-subject"
                data-placement="bottom" data-content="请先登录！" style="outline: none;">
          <i class="fa fa-plus" style="margin-right:3px;"></i>新主题
        </button>
      </div>
      <table class="table table-hover table-striped " >
        <thead>
        <tr>
          <th width="570px" style="color: blue;">主题</th>
          <th width="110px">创建</th>
          <th width="130px" class="hidden-xs">参与</th>
          <th style="text-align:center">回复</th>
          <th style="text-align:center">浏览</th>
          <th style="text-align:center" class="hidden-xs">动态</th>
            <c:if test="${sessionScope.user.type == '管理员'}">
          <th style="text-align:center" class="hidden-xs">操作</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${svos }" var="svo" varStatus="status">
            <c:if test="${status.count < 6 && page.currentIndex == 1}">
                <tr style="color:red;">
                    <td class="t-title"><a
                            href="<c:url value="/subject/post/${svo.sid}/1/${page.currentIndex}" />"><font
                            color="red">${svo.title }</font></a></td>

                    <td class="t-author"><a href="#"><img class="img-circle"
                                                          data-image="${svo.author.userAvatar }" data-placement="bottom"
                                                          data-content="${svo.author.userName }"
                            ></a> <time>${svo.author.postTime}</time></td>
                    <td class="hidden-xs"><c:forEach items="${svo.users }" var="info"
                                   varStatus="status">
                        <a href="#"><img class="img-circle"
                                         data-image="${info.userAvatar }" data-placement="bottom"
                                         data-content="${info.userName } 于${info.postTime }"></a>
                    </c:forEach></td>
                    <td class="t-reply">${svo.reply }</td>
                    <td class="t-vivits">${svo.visits > 0? svo.visits - 1:svo.visits}</td>
                    <td class="t-activity hidden-xs">${svo.lastReply }</td>
                    <c:if test="${sessionScope.user.type == '管理员'}">
                        <td class="t-activity hidden-xs"><i class="fa fa-trash-o" title="删除" onclick="deleteSub(${svo.sid})" style="cursor: pointer;"></i></td>
                    </c:if>
                </tr>
            </c:if>
            <c:if test="${status.count >=6 || page.currentIndex != 1}">
                <tr >
                    <td class="t-title"><a
                            href="<c:url value="/subject/post/${svo.sid}/1/${page.currentIndex}" />">${svo.title }</a>
                    </td>

                    <td class="t-author"><a href="#"><img class="img-circle"
                                                          data-image="${svo.author.userAvatar }" data-placement="bottom"
                                                          data-content="${svo.author.userName }"
                            ></a> <time>${svo.author.postTime}</time></td>
                    <td class="hidden-xs"><c:forEach items="${svo.users }" var="info"
                                                     varStatus="status">
                        <a href="#"><img class="img-circle"
                                         data-image="${info.userAvatar }" data-placement="bottom"
                                         data-content="${info.userName } 于${info.postTime }"></a>
                    </c:forEach></td>
                    <td class="t-reply">${svo.reply }</td>
                    <td class="t-vivits">${svo.visits > 0? svo.visits - 1:svo.visits}</td>
                    <td class="t-activity hidden-xs">${svo.lastReply }</td>
                    <c:if test="${sessionScope.user.type == '管理员'}">
                        <td class="t-activity hidden-xs"><i class="fa fa-trash-o" title="删除" onclick="deleteSub(${svo.sid})" style="cursor: pointer;"></i></td>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
      </table>
      <!-- 文本编辑器 -->
      <div id="write-win">
        <div class="title-input">
          <label for="" class="control-label"> 主题 </label> <input
                type="text" class="form-control" id="titleInput" name="text"
                placeholder="不得多于50字">
          <button class="btn btn-default" id="">
            <i class="fa fa-share" style="margin-right:3px;"></i>发表
          </button>
          <div class="info text-danger"></div>
          <div class="spin-icon">
            <i class="fa fa-spinner fa-spin"></i>
          </div>
        </div>
        <button type="button" class="close c-close" style="outline: none;">
          <span aria-hidden="true">&times;</span>
        </button>
        <div id="summernote"></div>
      </div>
      <!-- /文本编辑器 -->
      <!--分页按钮-->
        <jsp:include page="part/pageInit.jsp"></jsp:include>
    </div>

    <jsp:include page="part/footer.jsp"></jsp:include>
    <script src="<c:url value="/js/summernote.min.js" />"></script>
    <script src="<c:url value="/js/subject.js" />"></script>
</body>
</html>
