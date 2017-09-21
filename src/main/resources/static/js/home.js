$(function(){

    // $(".home_li ").eq(0).click(function(){
    //     location.href="/login";
    // })

    $(".home_li ").eq(1).click(function(){
        hide_tip();
        $("#mask").show();
        $(".ds").show();
    })

    $(".home_li ").eq(2).click(function(){
        hide_tip();
    })

    $(".home_li ").eq(3).click(function(){
        hide_tip();
        $(".flower").show();
    })

    $(".home_li ").eq(4).click(function(){
        hide_tip();
        $.ajax({
            type: "GET",
            url:"love",
            error: function(request) {
                alert("未知错误");
            },
            success: function(result) {
                $(".love").show();
                $(".love").html(result)
            }
        });
    })

    $(".home_name").click(function(){
        hide_tip();
        $(".jump_cat").show();
    })

    $(".ds_button .cancel").click(function(){
        $("#mask").hide();
        $(".ds").hide();
        $(".jump_cat").show();
    })

    $("#send_message").on('click',function(){

    })

})

function hide_tip(){
    $(".jump_cat").hide();
    $(".flower").hide();
    $(".love").hide();
    $("#mask").hide();
    $(".ds").hide();
}

