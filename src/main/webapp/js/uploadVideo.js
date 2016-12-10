$(function(){

    $("input[name='title']").blur(function(){
        var title = $(this).val();
        var courseimg = $("#courseImg");
        var tip = $("input[name='tip']");
        var needinfo = $("input[name='needinfo']");
        var studyinfo = $("input[name='studyinfo']");
        var url = "/upload/course/"+title;
        url = getProjectRootPath() + url;
        $.post(url,null,function(data){
            var mObj=jQuery.parseJSON(data);
            if(data==1){
                courseimg.attr("value","");
                courseimg.removeAttr("disabled");
                tip.attr("value","");
                tip.removeAttr("disabled");
                needinfo.attr("value","");
                needinfo.removeAttr("disabled");
                studyinfo.attr("value","");
                studyinfo.removeAttr("disabled");
            }else{
                courseimg.attr("value",mObj.courseimg);
                courseimg.attr("disabled","disabled");
                tip.attr("value",mObj.tip);
                tip.attr("disabled","disabled");
                needinfo.attr("value",mObj.needinfo);
                needinfo.attr("disabled","disabled");
                studyinfo.attr("value",mObj.studyinfo);
                studyinfo.attr("disabled","disabled");
            }
        });

    });

    $("input[type='submit']").click(function(){
        uploading(this);
    });

});

function uploading(submit){
    var lid = $("select[name='lid']");
    var title = $("input[name='title']");
    var courseimg = $("#courseImg");
    var tip = $("input[name='tip']");
    var needinfo = $("input[name='needinfo']");
    var studyinfo = $("input[name='studyinfo']");
    var chaptername = $("input[name='chaptername']");
    var videoname = $("input[name='videoname']");
    var videoaddr = $("input[name='videoaddr']");
    var reset = $("input[type='reset']");
    var txt = null;
    if(lid.val() != "" && title!= "" && tip.val() != ""
            && needinfo.val() != "" && studyinfo.val() != ""
            && chaptername.val() != "" && videoname.val() != ""
            && videoaddr.val() != ""){
        var courseimgFormat = "";
        if(courseimg.val() != ""){
            courseimgFormat = courseimg.val().substring(courseimg.val().lastIndexOf("\.")+1,courseimg.val().length);
            if (courseimgFormat != 'bmp' && courseimgFormat != 'gif' && courseimgFormat != 'jpeg' && courseimgFormat != 'png' && courseimgFormat != 'jpg') {
                txt = "上传的课程图片格式不正确~!";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
                $(submit).attr("type", "button");
                return;
            }
        }
        //用于判断上传视频的格式
        //var videoFormat = videoaddr.val().substring(videoaddr.val().lastIndexOf("\.") + 1, videoaddr.val().length);
        //if (videoFormat != 'mp4' && videoFormat != 'MP4') {
        //    $(submit).attr("type", "button");
        //    txt = "上传的视频格式只支持MP4格式~!";
        //    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        //    return;
        //}
        //用于判断上传视频地址的格式
        var videoFormat = videoaddr.val().substring(videoaddr.val().lastIndexOf("\.") + 1, videoaddr.val().length);
        if (videoFormat.toUpperCase().indexOf("MP4") < 0) {
            $(submit).attr("type", "button");
            txt = "上传的视频格式只支持MP4格式~!";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
            return;
        }
        $(submit).attr("type","submit");
        $(lid).attr("readonly","readonly");
        $(title).attr("readonly","readonly");
        $(courseimg).attr("readonly","readonly");
        $(tip).attr("readonly","readonly");
        $(needinfo).attr("readonly","readonly");
        $(studyinfo).attr("readonly","readonly");
        $(chaptername).attr("readonly","readonly");
        $(videoname).attr("readonly","readonly");
        $(videoaddr).attr("readonly","readonly");
        $(submit).attr("readonly","readonly");
        $(reset).attr("disabled","disabled");
        $(".spin-icon").css("display","block");
        $(".masklayer").show();
    }else{
        $(submit).attr("type","button");
        txt = "请填写全部内容再上传课件~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    }

}