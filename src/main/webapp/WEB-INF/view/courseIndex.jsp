<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" href="<c:url value="/image/logo.png" />" type="image/x-icon"/>
    <title>实战开发_PlatForm</title>
    <%@ include file="part/head_global.jsp"%>

    <link rel="stylesheet" href="<c:url value="/css/courseIndex.css"/>">
    <style>
        body{
            background-image: url("<c:url value="/image/course_bg1.jpg"/>");
            background-repeat: no-repeat;
        }
    </style>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1" />--%>
</head>
<body>
    <jsp:include page="part/header.jsp"></jsp:include>

    <div class="content" >
        <jsp:include page="part/left.jsp"></jsp:include>
        <div  class="middle-box" data-pageid="1">

            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<c:url value="/image/bg_2.png" />" alt="Five slide">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/image/bg_5.jpg" />" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="<c:url value="/image/bg_3.jpg" />" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="carousel-control left" href="#myCarousel"
                   data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel"
                   data-slide="next">&rsaquo;</a>
            </div>
            <div id="characters" class="col-md-12">
                <span style="opacity: 1;" class="col-md-4">
                    <img src="<c:url value='/image/char-icon1.png' />" alt="">
                </span>
                <span style="opacity: 1;" class="col-md-4">
                    <img src="<c:url value='/image/char-icon2.png' />" alt="">
                </span>
                <span style="opacity: 1;" class="col-md-4">
                    <img src="<c:url value='/image/char-icon3.png' />" alt="">
                </span>
            </div>

        </div>
    </div>

    <jsp:include page="part/footer.jsp"></jsp:include>
    <script src="<c:url value="/js/courseIndex.js"/>"></script>
</body>
</html>
