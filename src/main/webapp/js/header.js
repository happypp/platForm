// JavaScript Document
$(function(){
	//$("#search-area").find("a").focus(function(){
	//		$(this).css("background-color","#FFFFFF","border-color","#000");
	//	});
	//
	$("#search-area").find("input").blur(function () {
		var courseText = $("#search-area .search-input").val();
		if (courseText === null || courseText === "") {
			$(this).animate({width: "0"}, "slow");
		}
	});

	$("#search-area").find("a").hover(function () {
		$(this).css("color", "#EBEEFF");
	}, function () {
		$(this).css("color", "#505A62");
	});


	$("#myLogin .alert").hide();
	// 邮箱输入框自动完成
	var mailSuffix = ["@qq.com", "@sina.com", "@163.com", "@gmail.com", "@126.com", "@vip.com", "@hotmail.com", "@foxmail.com", "@sohu.com", "@aliyun.com", "@yahoo.com"];
	// 自动完成.改变候选项
	var auto = function(mailSuffix){
		var hints = new Array(mailSuffix.length);
		var str = $("#emailInput").val();
		str = str.split("@")[0];
		for (var i = 0; i < hints.length; i++) {
			hints[i] = str + mailSuffix[i];
		}
		return hints;
	};
	$("#emailInput").autocomplete({
		source: function(request, response){
			response(auto(mailSuffix));
		}
	});

	/*给头像加前缀*/
	var imageUrl = $(".avatar").attr("data-avatar");
	if (imageUrl != null && imageUrl.length > 0) {
		var avatarImg = getAvatarImage() + imageUrl;
		if(imageUrl.indexOf("/platform_assets/images/avatar/") >= 0){

		}else{
			avatarImg = getAvatarImage() +"/platform_assets/images/avatar/"+ imageUrl;
		}
		$(".avatar").attr("src",avatarImg);
		$(".avatar").show();
	}

	// 登出
	$(".userContent .userLogOut").click(function(){

		if (isLogin()) {
			var url = getProjectRootPath() + "/user/logout";
			$.post(url, null, function (data) {
				if (data == 1) {
					location.reload();
				}
			});
		}
	});

	// 登录警告信息.关闭监听事件
	$("#myLogin .c-close").click(function(){
		$("#myLogin .alert").hide();
	});

	//头像鼠标滑过事件
	//$("#uAvatar").find("a").find("img").hover(function(){
	//	$(this).popover("show");
	//},function(){
	//	$(this).popover("hide");
	//});

	//header置顶
	$(window).scroll(function(){
		var top = $(window).scrollTop();
		if(top > 70){
			$("#nav").css("position","fixed");
			//$("#nav").css("display","block");
			$("#nav").css("width","100%");
			$("#nav").css("background", "rgba(0,0,0,.9)");
		}else{
			$("#nav").css("position","fixed");
			$("#nav").css("top","0");
			//$("#nav").css("display","block");
			$("#nav").css("background","rgba(0,0,0,1)");
		}
	});
	var avatarBorder = null;
	/*用户隐藏信息*/
	$("#avatar").hover(function(){
		$(this).css("border","2px solid red");
		$("#userInfo").stop(true);
		$("#userInfo").fadeIn("fast");
		clearTimeout(avatarBorder);
	},function(){
		$("#userInfo").fadeOut(700);
		avatarBorder = setTimeout(function(){
			$("#avatar").css("border","2px solid #000");
		},400);
		$("#userInfo").hover(function(){
			clearTimeout(avatarBorder);
			$("#avatar").css("border","2px solid red");
			$(this).stop(true);
			$("#userInfo").fadeIn("fast");
		},function(){
			$("#avatar").css("border","2px solid #000");
			$("#userInfo").hide();
		});
	});

	//为搜素框绑定ent键盘事件
	$("#search-area .search-input").bind("keydown",function(event){
		if(event.keyCode == 13){
			var courseText = $("#search-area .search-input").val();
			if (courseText.trim().length == 0) {
			} else {
				location.href = getProjectRootPath() + "/search/index/" + courseText + "/1";
			}
		}
	});

	//为登录框绑定ENT键盘事件
	$("#pwdInput").bind("keydown",function(event){
		if(event.keyCode == 13){
			login();
		}
	});

});

// 登录
function login(){

	var $warn = $("#myLogin .alert strong");
	if ($("#emailInput").val() == "" || $("#pwdInput").val() == "") {
		$warn.text("用户名和密码不能为空！");
		$("#myLogin .alert").show();
		showErrorShane();
		return;
	}
	var url = getProjectRootPath() + "/user/login";
	$.post(url,{
		"email": $("#emailInput").val(),
		"password": $("#pwdInput").val()
	},function(data){
		if (data == 1) {
			location.reload();
		} else if(data == 0){
			$warn.text("用户名或密码错误！");
			$("#myLogin .alert").show();
			showErrorShane();
		}else if(data == 2){
			$warn.text("账号未激活");
			$("#myLogin .alert").show();
			showErrorShane();
		}
	});
}

//错误抖动效果
function showErrorShane() {
	var ErrorShane = $("div[role='alert']");
	if (ErrorShane.css("display") === 'block') {
		ErrorShane.animate({left: '5.9785px'}, 50);
		ErrorShane.animate({left: '-5.9785px'}, 50);
		ErrorShane.animate({left: '0'}, 50);
	}
}

//搜索课程
function searchCourse(){
	var width = $("#search-area").find("input").css("width");
	var inputAnimate = $("#search-area").find("input");
	var courseText = $("#search-area .search-input").val();
	//if(courseText.trim().length == 0){
	//}else{
	//	location.href = getProjectRootPath() + "/search/index/"+courseText+"/1";
	//}
	if (width === "0px") {
		inputAnimate.animate({width: "200px"}, "slow");
		inputAnimate.focus();
	}else{
		if (courseText.trim().length == 0) {
			inputAnimate.animate({width: "0"}, "slow");
		} else {
			location.href = getProjectRootPath() + "/search/index/" + courseText + "/1";
		}
	}
}
