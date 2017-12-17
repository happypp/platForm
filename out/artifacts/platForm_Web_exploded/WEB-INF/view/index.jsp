<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>在线课程学习</title>
    <%@ include file="part/head_global.jsp"%>
    <link rel="stylesheet" href="<c:url value="/css/index.css" />">
</head>
<body>

    <jsp:include page="part/header.jsp"></jsp:include>
    <div style="min-height: 1100px;padding-top: 60px;">
        <div class="banner">
            <ul>
                <li style="background-image: url(<c:url value='/image/index1.jpg'/>);"></li>
                <li style="background-image: url(<c:url value='/image/index5.jpg' />);"></li>
                <li style="background-image: url(<c:url value='/image/index3.jpg' />);"></li>
                <li style="background-image: url(<c:url value='/image/index4.jpg' />);"></li>
            </ul>
        </div>
        <div class="container" style="position: relative;display: none;" id="intro">
            <div class="intro2-text" style="opacity: 1;"></div>
            <div class="intro2-computer2" style="opacity: 1;"></div>
        </div>
    </div>
    <jsp:include page="part/footer.jsp"></jsp:include>


    <script src="<c:url value="/js/index.js" />"></script>
</body>
<script>
    $(function(){
        var f=$(document).width();
        var d = "<div class='snow' style='position:absolute;top:0;color:#fff;z-index:99999'>❄</div>";
        setInterval(function(){
            var e=(Math.random()*(f-100))+50;
            var o=0.8+Math.random();
            var fon=20+Math.random()*20;
            var l = e - 200 + (400 * Math.random());
            var t = 520 + 100 * Math.random();
            if(l>f-50){ l=f-50}
            if(l<50){ l=50}

            var k=8000 + 3000 * Math.random();
            $(d).clone().appendTo($(document.body)).css({
                left:e+"px",
                opacity:o,
                "font-size":fon
            }).animate({
                top:t+"px",
                left:l+"px",
                opacity:0
            },k,"linear",function(){$(this).remove()})
        },500)
    })
</script>


</html>
