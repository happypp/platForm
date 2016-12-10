
$(function(){
    var top = null;
    $(window).scroll(function(){
        top = $(window).scrollTop();
        var up = $("#up");
        if(top < 100){
            up.hide();
            up.find("img").attr("src", "");
        }
        if(top >= 101){
            up.show();
            var data_src = up.find("img").data("src");
            var src = up.find("img").attr("src");
            if (src === "") {
                up.find("img").attr("src", data_src);
            }
        }
    });
    /*置顶*/
    $("#up").scrollToTop(600);

    /*微信二维码接口*/
    $("#img_log img:first").hover(function () {
        $(".WeChatInterface").css("display", "inline-block");
    }, function () {
        $(".WeChatInterface").css("display", "none");
    });

});
