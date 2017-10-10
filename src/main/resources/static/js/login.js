$(function () {
    $("#login").on('click',function(){
        login();
    })
})
function login(){
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        url: '/login',
        type: 'post',
        data: {username:username,password:password},
        dataType: 'json',
        success: function(data){
            console.log(data);
            if(data.status=='100001') {
                alert(data.msg == null ? "用户名密码不匹配，请确认后再登录":data.msg);
            } else {
                location.href='/';
            }
        }
    });
}