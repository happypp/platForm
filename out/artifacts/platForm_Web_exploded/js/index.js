// JavaScript Document
$(function() {
    $('.banner').unslider({
        speed: 500,               //  The speed to animate each slide (in milliseconds)
        delay: 3000,              //  The delay between slide animations (in milliseconds)
        complete: function() {},  //  A function that gets called after every slide animation
        keys: true,               //  Enable keyboard (left, right) arrow shortcuts
        dots: true,               //  Display dot navigation
        fluid: false              //  Support responsive design. May break non-responsive designs
    });
    $(".banner").css("width","100%");
    $(".banner ul li").css("width","25%");

    var top = null;
    $(window).scroll(function () {
        top = $(window).scrollTop();
        if (top < 100) {

        }
        if (top >= 101) {
            $("#intro").fadeIn(1500);
        }
    });
});