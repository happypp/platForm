$(function(){

    fold();
    listVideo();
    courseMenu();
});

//整体折叠效果
function fold(){
    if(isLogin()){
        var videoaddr = $(".userContent p a").attr("data-src");
        var videotitle = $(".userContent p span").text();
        var coursetitle = $(".userContent p strong").text();
        var title = $("#courseList").attr("data-course");
        if(videotitle.length > 0) {
            if(title == coursetitle){
                $("#video").find("video").attr("src", videoaddr);
            }
            var as = $(".panel-body ul li a");
            as.each(function () {
                if ($(this).text() == videotitle) {
                    $("#collapse1").removeClass("in");
                    $(this).css("color", "red");
                    var id = $(this).closest(".collapse").attr("id");
                    $("#" + id).addClass("in");
                    $(".collapse").each(function () {
                        $(this).collapse({
                            toggle: false
                        });
                    });
                    return false;
                }else{
                    listFold();
                }
            });
            $(".coursePost").css("display", "none");
        }else{
            listFold();
        }
    }else{
        listFold();
    }
}

/*视频播放*/
function listVideo(){
    var as = $(".panel-body ul li").find("a");
    as.each(function(){
        $(this).click(function(){
            as.css("color","#787D82");
            $("#video").find("video").attr("src",$(this).attr("data-src"));
            $("#video").find("video").attr("autoplay","autoplay");
            $(this).css("color","red");
            $(this).css("text-decoration","none");
            /*发送异步消息将用户最后一次课程记录下来*/
            if(isLogin()){
                var uid = $("#avatar").attr("data-uid");
                var videotitle = $(this).text();
                $(".userContent p span").text(videotitle);
                var coursetitle = $("#courseList").data("course");
                $(".userContent p strong").text(coursetitle);
                var url = "/user/userVideo";
                url = getProjectRootPath() + url;
                $.post(url,{
                    'uid':uid,
                    'videotitle':videotitle
                },function(data){

                });
                $(".coursePost").css("display","none");
            }
        });
    });
}

/*列表折叠*/
function listFold(){
    $("#collapse1").addClass("in");
    $(".collapse").each(function(){
        $(this).collapse({
            toggle: false
        });
    });
    $(".coursePost").css("display","none");
}

function courseMenu(){
    var lis = $(".courseMenu ul li");
    lis.each(function(){
        $(this).click(function(){
            $(lis).find("a").removeClass("courseMenuActive");
            var page = $(this).data("page");
            var li = ".courseMenu ul li:nth-child(" + page + ")";
            $(li).find("a").addClass("courseMenuActive");
            if(page == 1){
                $(".courseLists").css("display","block");
                $(".coursePost").css("display","none");
            }else{
                $(".courseLists").css("display","none");
                $(".coursePost").css("display","block");
            }
        });
    });
}