<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  #leveCourse {
    margin-top: 60px;
    float: none;
    opacity: .9;
    z-index: 1;
    position: absolute;
    display: inline-block;
  }

  #leveCourseMenu {
    background-color: #FDFDFD;
    box-shadow: 0 2px 3px #888;
  }

  #leveCourseMenu ul {
    list-style: none;
    padding: 0;
    z-index: 777;
  }

  .ui-menu .ui-menu-item a {
    text-decoration: none;
    display: block;
    line-height: 0.9;
    zoom: 1;
    padding: 20px;
    text-align: center;
  }

  /*jqueryUI 菜单界面样式重写*/
  /*二级菜单样式*/
  .ui-widget-content {
    border: 1px solid #ccc;
    background: #fff  50% 50% repeat-x;
    color: #222;
  }
  /*鼠标移开后，以及菜单的样式*/
  .ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active {
    border: 1px solid #FF0000;
    background: #fff  50% 50% repeat-x;
    color: red;
  }
  /*鼠标移动到菜单上面的样式*/
  .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus {
    border: 1px solid #ccc;
    background: #f3eaff 50% 50% repeat-x; /*控制鼠标移动到菜单上菜单的背景颜色*/
    color: red;
  }
</style>
<div id="leveCourse" class="col-md-2">
  <ul id="leveCourseMenu">
    <script>
      // JavaScript Document
      $(document).ready(function(){
        $( "#leveCourseMenu" ).menu();
        var top = null;
        $(window).scroll(function () {
          top = $(window).scrollTop();
          var menu = $("#leveCourse");
          if (top < 250) {
            menu.css("position", "fixed");
          }
          if (top >= 250) {
            menu.css("position", "absolute");
          }
        });
      });
    </script>
    <c:forEach items="${courseVos}" var="cvo">
    <li>
      <a href="#">${cvo.type}</a>
      <ul class="courseMenu"
          style="width: 150px;margin-left:13px;background-color: rgba(255,255,255,.8);border-right: 2px solid #ccc;">
          <c:forEach items="${cvo.leveCourses}" var="cvos">
              <li><a href="<c:url value="/course/courseMsg/${cvos.id}/1"/> ">${cvos.coursename}</a></li>
          </c:forEach>
        </ul>
    </li>
    </c:forEach>
  </ul>

</div>

