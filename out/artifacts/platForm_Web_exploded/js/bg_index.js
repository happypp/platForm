$(function(){
    //展开第一个菜单列表
    $("#userMeun1").addClass("in");

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
        var txt1 = "密码不正确，删除失败！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.input,{
            onOk:function(v){
                var tid = null;
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
                                tid = $(this).data("id");
                                lid = $(this).data("lid");
                                url = getProjectRootPath() +"/bg_T_LcourseDeleteById";
                                $.post(url,{
                                    'lid' : lid,
                                    'tid' : tid
                                },function(data){
                                    if(data == 1){
                                        flag++;
                                        if(flag == $("table tbody input:checkbox[name='checkCourse']:checked").length){
                                            var txt=  "删除成功~！";
                                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info,{
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

function addLcourse(){
    $("#deleteLcourse").css("display","none");
    $("#editUserType").css("display","none");
    $("#editTeacherType").css("display","none");
    $("#addLcourse").css("display","block");
}

function deleteLcourse(){
    $("#addLcourse").css("display","none");
    $("#editUserType").css("display","none");
    $("#editTeacherType").css("display","none");
    $("#deleteLcourse").css("display","block");
}

function editUserType(){
    $("#addLcourse").css("display","none");
    $("#deleteLcourse").css("display","none");
    $("#editTeacherType").css("display","none");
    $("#editUserType").css("display","block");
}

function editTeacherType(){
    $("#addLcourse").css("display","none");
    $("#deleteLcourse").css("display","none");
    $("#editUserType").css("display","none");
    $("#editTeacherType").css("display","block");
}

function saveCourse(){
    var txt = null;
    //找到其用户所选择的radio值
    var radioValue = $("input[type='radio']:checked").next().val();
    if($("input[type='radio']:checked").val() == null){
        txt=  "请选中课程类型中的其中一项~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    if(radioValue == ""){
        txt=  "请填写相对应的课程类型~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    //获取二级课程名
    var coursename = $("input[name='coursename']").val();
    if(coursename.trim() == ""){
        txt=  "请填写相对应的一级课程名";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    //获取课程的相关描述
    var description = $("textarea[name='description']").val();
    if(description.trim() == ""){
        txt=  "请填写相对应的课程描述~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
        return false;
    }
    //保存管理员所添加的课程名
    var url = getProjectRootPath() + "/saveCoursename";
    $.post(url,{
        'name' : radioValue,
        'coursename' : coursename,
        'description' : description
    },function(data){
        if(data == 1){
            var txt=  "添加成功~！";
            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info,{
                onOk : function(){
                    location.reload();
                }
            });
        }
    });

}

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

//更新用户成为教师身份
function updateUserType(){
    var userEamil = $("#editUserType input[name='userEmail']").val();
    var txt = null;
    if(userEamil.trim() != ""){
        var url = getProjectRootPath() + "/updateUserType";
        $.post(url,{
            'email' : userEamil
        },function(data){
            if(data == 1){
                txt=  "该邮箱已激活，请不要重复激活~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                $("#editUserType input[name='userEmail']").val("");
            }else if(data == 0){
                txt=  "激活成功~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                $("#editUserType input[name='userEmail']").val("");
            }else{
                txt=  "该邮箱不存在，请重新输入~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
            }
        });
    }else{
        txt=  "请填写要激活的邮箱~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    }
}
//更新教师--->普通用户
function updateTeacherType(){
    var teacherEmail = $("#editTeacherType input[name='teacherEmail']").val();
    var txt = null;
    if(teacherEmail.trim() != ""){
        var url = getProjectRootPath() + "/updateTeacherType";
        $.post(url,{
            'email' : teacherEmail
        },function(data){
            if(data == 1){
                txt=  "该邮箱已是普通用户~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                $("#editTeacherType input[name='teacherEmail']").val("");
            }else if(data == 0){
                txt=  "修改成功~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                $("#editTeacherType input[name='teacherEmail']").val("");
            }else{
                txt=  "该邮箱不存在，请重新输入~！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
            }
        });
    }else{
        txt=  "请填写要修改为普通用户的邮箱~！";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    }
}

//单个课程的删除
function deleteById(tid,lid){
    if(lid == 0){
        lid = "";
    }
    var url = getProjectRootPath() +"/bg_T_LcourseDeleteById";
    $.post(url,{
        'lid' : lid,
        'tid' : tid
    },function(data){
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
