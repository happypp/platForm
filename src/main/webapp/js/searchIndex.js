

//获取用户查询的值，填写到searchInput框中
function addCourseTextTosSearchInput(){
    var courseText = $("#searchCourseIndex").data("course");
    $("#searchInput").find("input").attr("value",courseText);
}

//搜索课程
function searchCourseTwo(){
    var courseText = $("#searchInput input").val();
    if(courseText.trim().length == 0){
    }else{
        location.href = getProjectRootPath() + "/search/index/"+courseText+"/1";
    }
}

//获取li中的text将其关键字高亮显示
function keyword(){
    var key = $("#courseMenu ul li strong span").text();
    var replaceKey = "<strong style='color: red;font-size: 20px;'>"+key+"</strong>";
    var liTexts = $("#searchCourseInfo ul li:last-child");
    liTexts.each(function () {
        var courseInfo =  $(this).text().toUpperCase().replace(key,replaceKey);
        $(this).html(courseInfo);
    });
}


$(function(){
    addCourseTextTosSearchInput();

    //去除searchInput框中的数据
    $("#searchInput div .search-input span").hide();
    var inputValue = $("#searchInput input").val();
    if(inputValue.trim().length > 0){
        $("#searchInput div .search-input span").show();
    }
    $("#searchInput div .search-input span").click(function(){
        $("#searchInput input").val("");
        $("#searchInput div .search-input span").hide();
    });


    //为搜素框绑定ent键盘事件
    $("#searchInput input").bind("keydown",function(event){
        if(event.keyCode == 13){
            searchCourseTwo();
        }
    });
    keyword();
});