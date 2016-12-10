// JavaScript Document

$(function(){

	$(".spin-icon").hide();
	//用户是否登录判断
	$("#new-reply").click(function(){
		if(!isLogin()){
			$(this).popover("show");
			setTimeout("$(\"#new-reply\").popover(\"hide\")", 2000);
			return;
		}
		var sid = $("#post").data("sid");
		var uid = $("#avatar").data("uid");
		var content = $("#summernote").code();
		content = content.replace("<p><br></p>",""); //排除空值输入
		if (content.length > 1 && !/^<p>(&nbsp;\s)*(&nbsp;)+<\/p>$/.test(content)) {
			$(".spin-icon").show();
			$("#new-reply").attr("disabled","disabled");
			$(".note-editable").attr("contenteditable", "false");
			var url = getProjectRootPath() +"/subject/savePost";
			var sensitiveUrl = getProjectRootPath() + "/sensitive/filter";
			$.post(sensitiveUrl,{
				"content":content.replace("\"", "|")
			},function(data){
				$.postJSON(url, {
					"uid": uid,
					"sid": sid,
					"content": data
				}, function (data) {
					if (data == 1) {
						$(".spin-icon").hide();
						location.reload();
					}
				});
			});
			//setTimeout(function(){
			//	//$.postJSON(url, {
			//	//	"uid": uid,
			//	//	"sid": sid,
			//	//	"content": content.replace("\"", "|")
			//	//}, function (data) {
			//	//	if (data == 1) {
			//	//		$(".spin-icon").hide();
			//	//		location.reload();
			//	//	}
			//	//});
			//},1500);
		}else{
			$(this).next().fadeIn();
			setTimeout(function(){
				$("#new-reply").next().fadeOut();
			},3500);
		}
	});
	// 加载发帖人头像
	var imageUrl = $(".subjectAvatar").data("avatar");
	if (imageUrl != null && imageUrl.length > 0) {
		$(".subjectAvatar").attr("src", getAvatarImage() + imageUrl);
	}
	//加载回复帖子人的头像
	$(".postAvatar").each(function () {
		var imageUrl = $(this).data("avatar");
		if (imageUrl != null && imageUrl.length > 0) {
			$(this).attr("src", getAvatarImage() + imageUrl);
		}
	});


	// 为头像增加鼠标移过监听事件
	$(".content a img").hover(function(){
        $(this).popover("show");
	},function(){
        $(this).popover("hide");
	});

	$(".msg ul li a img").hover(function(){
		$(this).popover("show");
	},function(){
		$(this).popover("hide");
	});
	// 初始化Summernote
	$('#summernote').summernote({
		height: 250,
		lang: 'zh-CN',
		toolbar: [['style', ['style', 'bold', 'italic', 'underline', 'clear']], ['font', ['fontname', 'fontsize']], ['font', ['strikethrough', 'superscript', 'subscript']], ['color', ['color']], ['para', ['paragraph']], ['height', ['height']], ['insert', ['hr', 'link', 'picture']], ['misc', ['undo', 'redo', 'codeview', 'fullscreen']]]
	});
});

//回复用户显示框按钮
function replyUser_btn(btn){
	$(btn).next().next().next().show();
	$(btn).next().next().next().find("input").focus();
}
//取消用户回复按钮
function reply_off(off){
	$(off).parent().hide();
	$(off).parent().find("input").val("");
}
//回复用户信息提交按钮
function replyUser_sub(sub){
	var pid = $(sub).parent().data("pid");
	var subid = $(sub).parent().data("subid");
	var pageindex = $(sub).parent().data("pageindex");
	var uid = $("#avatar").data("uid");
	var txt = null;
	if(isLogin()){
		var reply_value = $(sub).prev().prev().prev().val().trim();
		if(reply_value == ""){
			txt = "回复内容不能为空~！";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
		}else{
			var url = getProjectRootPath() + "/subject"+"/post"+"/"+subid+ "/"+pageindex +"/0";
			var sensitiveUrl = getProjectRootPath() + "/sensitive/filter";
			$.post(sensitiveUrl, {
				"content": reply_value
			}, function (data) {
				$.post(url,{
					'pid'  : pid,
					'reply_value' : data,
					'uid' : uid
				}, function () {
					location.reload();
				});
			});
			//$.post(url,{
			//	'pid'  : pid,
			//	'reply_value' : reply_value,
			//	'uid' : uid
			//}, function () {
			//	location.reload();
			//});
		}
	}else{
		txt = "登录方可回复~！";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
	}

}
//管理员删除POST
function delete_post(pid){
	var url = getProjectRootPath() + "/bg_delete_post";
	$.post(url,{
		'pid' : pid
	},function(data){
		if(data == 1){
			location.reload();
		}
	});
}
