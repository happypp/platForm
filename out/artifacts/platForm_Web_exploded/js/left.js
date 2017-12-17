// JavaScript Document
$(function() {
    $( "#leveCourseMenu" ).menu();
    $(window).scroll(function () {
        top = $(window).scrollTop();
        alert(top);
        var menu = $("#leveCourseMenu");
        if (top < 400) {
            menu.css("display", "none");
            return;
        }
        if (top >= 400) {
            menu.css("display", "fixed");
        }
    });
  });