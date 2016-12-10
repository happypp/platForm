$(function(){
    //获取当前界面的二级课程名称
    var course = $("#courseName").text();
    //获取一级课程
    var firstCourse = $("#bg_left .sidebar-menu .menu-first");
    //获取二级课程
    var secondCourse = $("#bg_left .sidebar-menu .nav-list").find("a");
    secondCourse.each(function(){
        if($(this).text().trim() == course){
            //获取满足条件的父元素ID
            var parentId = $(this).parent().parent().attr("id");
            firstCourse.each(function(){
                if($(this).attr("href").split("#")[1] == parentId){
                   $("#"+parentId).addClass("in");
                }
            });
        }
    });


    //checkbox选择设置
    $("#deleteCourse").attr("disabled","disabled");
    $("table tbody input").each(function(){
        $(this).click(function(){
            if($("table tbody input").is(':checked')){
                $("#first_checkbox").find("input").prop("checked",true);
                $("#deleteCourse").removeAttr("disabled");
            }else{
                $("#first_checkbox").find("input").prop("checked",false);
                $("#deleteCourse").attr("disabled","disabled");
            }
        });
    });
    //删除多个课程按钮单击事件
    $("#deleteCourse").click(function(){
        //设置输入框是密码类型
        var txt=  "请输入密码";
        var txt1 = "密码有误~！请重新输入~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.input,{
            onOk:function(v){
                var cid = null;
                var lid = null;
                var url = null;
                var flag = 0;
                if(v.trim().length >= 5 && v.trim().length <= 10){
                    //判断输入的管理员密码是否正确
                    //获取管理员的邮箱
                    var adminEmail = $("#adminEmail").attr("value");
                    var adminPassWord = v;
                    url = getProjectRootPath() + "/valPassWord";
                    $.post(url,{
                        'email':adminEmail,
                        'password':adminPassWord
                    },function(data){
                        if(data == 0){
                            window.wxc.xcConfirm(txt1, window.wxc.xcConfirm.typeEnum.error);
                        }else{
                            //获取被选中的课程
                            var checkCourses = $("table tbody input:checkbox[name='checkCourse']:checked");
                            checkCourses.each(function(){
                                cid = $(this).data("id");
                                lid = $(this).data("lid");
                                url = getProjectRootPath() +"/bg_courseDeleteById" + "/" + lid + "/"+ cid;
                                $.post(url,{},function(data){
                                    if(data == 1){
                                        flag++;
                                        if(flag == $("table tbody input:checkbox[name='checkCourse']:checked").length){
                                            var txt2=  "删除成功~！";
                                            window.wxc.xcConfirm(txt2, window.wxc.xcConfirm.typeEnum.info,{
                                                onOk : function(){
                                                    location.reload();
                                                }
                                            });
                                        }
                                    }
                                });
                            });
                        }
                    });
                }else{
                    window.wxc.xcConfirm(txt1, window.wxc.xcConfirm.typeEnum.error);
                }
            }
        });
    });

});

//checkbox选择设置
function check(){
    var checkbox = $("#first_checkbox").find("input");
    if(checkbox.get(0).checked){
        //选中了
        $("table tbody input").prop("checked",true);
        $("#deleteCourse").removeAttr("disabled");
    }else{
        //没选中
        $("table tbody input").prop("checked",false);
        $("#deleteCourse").attr("disabled","disabled");
    }
}

function deleteById(lid,cid){
    var url = getProjectRootPath() +"/bg_courseDeleteById" + "/" + lid + "/"+ cid;
    $.post(url,{},function(data){
        if(data == 1){
            var txt=  "删除成功~！";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info,{
                onOk : function(){
                    location.reload();
                }
            });
        }
    });
}