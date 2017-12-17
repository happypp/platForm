<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="pagination center-block" style="margin-top:20px;margin-left:50px;margin-bottom: 70px;">
  <li id="pre"><a
          href="<c:url value="${urlSubfix }/${page.currentIndex-1<1?1:page.currentIndex-1 }"/>"
          >&laquo;</a></li>
  <c:forEach items="${page.navItems }" var="item">
    <li><a
            href="<c:url value="${urlSubfix }/${item }" />"
            >${item }</a></li>
  </c:forEach>
  <li id="next"><a
          href="<c:url value="${urlSubfix }/${page.currentIndex+1>page.pageCount?page.pageCount:page.currentIndex+1 }"/>"
          >&raquo;</a></li>
</ul>
<script>
    //获取当前页数
    var currentIndex = ${page.currentIndex};
    //算出总页数的长度
    var pages = parseInt("${fn:length(page.navItems)}");
    //获取总页数
    var pageCount = ${page.pageCount};

    $(function(){
        if(pages <= 1){
            $(".pagination").hide();
            return;
        }
        $(".pagination li").each(function(){
            var text = $(this).children("a").text();
            if(text == currentIndex){
                $(this).addClass("active");
            }
        });

        if(currentIndex == 1){
            $("#pre").addClass("disabled");
            return;
        }
        if(currentIndex == pageCount){
            $("#next").addClass("disabled");
            return;
        }
    });

</script>