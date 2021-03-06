$(function(){

    //加载所有弹幕
    loadBarrage();
    // setInterval('loadBarrage()',10000);
    //打开连接
    connect();

    $(".send_button").on('click',function(){
        send();
    })
})

function loadBarrage(){
    $.ajax({
        type:"GET",
        url:"/barrages",
        success:function(data){
            if(data.status='100000'){
                var arr = new Array();
                for(var i=0; i<data.result.length; i++)
                {
                    arr[i]=data.result[i].content;
                }
                if(arr.length==0){
                   return false;
                }
                var i = 0;
                var yanshi=setInterval(
                    function(){
                        sendMessage(arr[i]);
                        if(i==arr.length-1){
                            clearInterval(yanshi);
                        }
                        i++;
                    },1000);
            }else{
                alert("加载出错，错误码："+data.status);
            }
        },
        error:function(data){
            alert("加载失败");
        }
    })
}

var time=10;
function send(){
    if(time<10){
        alert("请"+time+"秒后发送");
        return false;
    }
    var msg=$("#send_message").val();
    if(msg.trim()=="" || msg==null){
        alert("你特么倒是写点东西呀。");
        return false;
    }
    if(msg.indexOf("alert") >= 0 ) {
        alert("个人小站，若搞，请轻搞");
        return false;
    }
    if(msg.indexOf("<script>") >= 0 ) {
        alert("个人小站，若搞，请轻搞");
        return false;
    }
    if(msg.indexOf("$") >= 0 ) {
        alert("个人小站，若搞，请轻搞");
        return false;
    }
    if(msg.length>100){
        alert("最多发送100个字符！");
        return false;
    }
    //调用stomp 发送到后台弹幕
    stompClient.send("/websocketBarrage", {},msg);
    $.ajax({
        type:"POST",
        url:"/saveBarrage",
        data:{
            content:msg
        },
        success:function(data){
            if(data.status=='100000'){
                $("#send_message").val("");
                var djs=setInterval(daojishi,1000);
                function daojishi(){
                    time--;
                    if(time<=0){
                        time=10;
                        clearInterval(djs);
                    }
                }
            }else{
                alert(data.msg);
            }

        },
        error:function(data){
            console.log("加载失败");
        }
    })
}
function sendMessage(info){
    var getRandomColor = function(){
        return (function(m,s,c){
            return (c ? arguments.callee(m,s,c-1) : '#') +
                s[m.floor(m.random() * 16)]
        })(Math,'0123456789abcdef',5)
    }
    var item={
        img:'img/avatar.jpg', //图片
        info:info, //文字
//        href:'http://www.jq22.com', //链接
        close:true, //显示关闭按钮
        speed:0, //延迟,单位秒,默认6
       // bottom:70, //距离底部高度,单位px,默认随机
       //  color:getRandomColor, //颜色,默认白色
        old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色
    }
    $('body').barrager(item);
}

var stompClient = null;
//打开连接
function connect() {
    var socket = new SockJS('/endpointDan');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected:' + frame);
        stompClient.subscribe('/topic/getResponse', function (response) {
            showResponse(JSON.parse(response.body));
        })
    });
}
function showResponse(data) {
    sendMessage(data.msg);
}