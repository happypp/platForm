<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<script>
	// 为顶部导航栏确定当前所在版块
	var id = $(".middle-box").attr("data-pageid");
	var li = ".top-box #menu ul li:nth-child(" + id + ")";
	$(li).find("a").addClass("active_1");
	$(li).find("a").css("color","white");
</script>

<div id="up" style="display: none;">
	<%--<a href="javascript:" class="fa fa-arrow-up fa-3x" title="置顶"></a>--%>
	<img data-src="<c:url value='/image/upToTop.gif' />" src="" title="飞人起飞了"
		 style="cursor: pointer;width: 80px;height: 100px;">
</div>


<div id="footer" style="padding-top: 20px;">
	<div class="container">
		<div  style="color: #C8CDD2;width: 20%;text-align: center;float: left;">
			<a href="<c:url value="/index" />" style="color: #C8CDD2;font-size: 20px;"><i class="fa fa-home">&nbsp;&nbsp;Home</i></a>
		</div>
		<div  style="color: #C8CDD2;width: 20%;float: left;">
			<i class="fa fa-user" style="font-size: 18px;">&nbsp;&nbsp;关于我们</i>
			<br><br>
			Java群: 6200344 <br>
			Php群: 42125527<br>
			web前端群3: 249860541<br>
			安卓群: 232465453<br>
			在校生群: 187845234<br>
		</div>
		<div  style="color: #C8CDD2;float: left;width: 40%;" id="link">
			<i class="fa fa-paperclip" style="font-size: 18px;">&nbsp;&nbsp;相关链接</i><br><br>
			<span>
				<a href="http://www.imooc.com/" target="_blank">慕课网</a> &nbsp;&nbsp;
				<a href="http://www.bootcss.com/" target="_blank">Bootstrap中文网</a>&nbsp;&nbsp;
				<a href="http://fontawesome.bootstrapcheatsheets.com/" target="_blank">fontawesome</a>&nbsp;&nbsp;
				<a href="http://www.bootcdn.cn/summernote/" target="_blank">summernote</a>
			</span><br>
			<span>
				<a href="http://jquery.com/" target="_blank">jquery</a> &nbsp;&nbsp;
				<a href="http://www.html5cn.org/" target="_blank">html5</a> &nbsp;&nbsp;
				<a href="http://www.admin10000.com/" target="_blank">web开发者</a> &nbsp;&nbsp;
				<a href="http://www.js-css.cn/" target="_blank">js代码网</a> &nbsp;&nbsp;
			</span><br>
			<span>
				<a href="http://www.w3school.com.cn/" target="_blank">w3c在线教程</a> &nbsp;&nbsp;
				<a href="http://www.bootcss.com/p/unslider/" target="_blank">Unslider</a> &nbsp;&nbsp;
				<a href="http://www.php-z.com/" target="_blank">PHP中文网</a> &nbsp;&nbsp;
			</span><br>
			<span>
				<a href="http://www.codeceo.com/" target="_blank">码农网</a> &nbsp;&nbsp;
				<a href="http://www.baidu.com/" target="_blank">百度</a> &nbsp;&nbsp;
				<a href="http://www.google.com/" target="_blank">谷歌</a> &nbsp;&nbsp;
			</span><br>
			<span>
				<a href="http://www.icoolxue.com/" target="_blank">爱酷学习网</a> &nbsp;&nbsp;
			</span>
		</div>

		<div id="img_log">
			<img class="img-circle" src="<c:url value="/image/WeChat.png" />" alt="">

			<a href="http://weibo.com/u/2822442735?is_all=1" target="_blank"><img class="img-circle"
																				  src="<c:url value="/image/Sina.png" />"
																				  alt=""></a>
			<a href="http://user.qzone.qq.com/957655440" target="_blank"><img class="img-circle"
																			  src="<c:url value="/image/qq.png" />"
																			  alt=""></a>
		</div>
		<div class="WeChatInterface" style="display: none;">
			<img src="<c:url value="/image/WeChatInterface.png" />" alt="">
		</div>
	</div>
	<p style="text-align:center;color: #787D82;font-size: 14px;">Copyright © 2016 xxxxx.com All Rights Reserved | 安徽 555号</p>
</div>

<script>

</script>



