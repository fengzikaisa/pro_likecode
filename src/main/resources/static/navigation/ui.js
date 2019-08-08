$(function () {
    $('.more').click(function () {
        $('#tool_tc_bj').show();
        var rollStart = $(this).nextAll('.tool_tc').outerHeight();
        $(this).nextAll('.tool_tc').show().css("marginTop", -(rollStart / 2 - 5) + 'px');
    })
    $('#tool_tc_bj').click(function () {
        $('.tool_tc').removeAttr('style');
        $('#tool_tc_bj').hide();
    })
    $(".more").one("click", function () {
        var more_clone = $(this).nextAll(".like-list").find("li");

        var more_clone_for = $(more_clone).next(".tool_tc");
        $(this).nextAll(more_clone_for).find("ul h4").after(more_clone.clone());
    })
    /*创作为自动识别tool名字载入相应页面*/
    $("#rmd_zh").click(function () {
        $("#tool_ajax").load("/tool/rmb.html #rmb_daxiaoxie");
        var rollStart = $('#tool_ajax').outerHeight();
        $('#tool_ajax').show().css("marginTop", -(rollStart / 2 - 5) + 'px');
        $('#tool_tc_bj').show();
    })

    /*end*/
})

$(document).keyup(function (e) {
    var key = e.which;
    if (key == 27) {
        $('.tool_tc').removeAttr('style');
        $('#tool_tc_bj').hide();
    }
});

