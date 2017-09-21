$(function(){

    //加载所有弹幕
    loadBarrage();
    setInterval('loadBarrage()',30000);
})

function loadBarrage(){
    $.ajax({
        type:"GET",
        url:"/barrages",
        success:function(data){
            if(data.status='100000'){
                for(var i=0; i<data.result.length; i++)
                {
                    // alert(data.result[i].id+" " + data.result[i].content)
                    sendMessage(data.result[i].content);
                }
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
function keySend(){
    if(time<10){
        alert("请"+time+"秒后发送");
        return false;
    }
    if (event.keyCode==13){
        var msg=$("#send_message").val();
        if(msg.trim()=="" || msg==null){
            alert("你特么倒是写点东西呀。");
            return false;
        }
        $.ajax({
            type:"POST",
            url:"/saveBarrage",
            data:{
                content:msg
            },
            success:function(data){
                if(data.status='100000'){
                    sendMessage("我："+msg);
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
                    alert("加载出错，错误码："+data.status);
                }

            },
            error:function(data){
                console.log("加载失败");
            }
        })

    }
}

function sendMessage(info){
    var getRandomColor = function(){
        return (function(m,s,c){
            return (c ? arguments.callee(m,s,c-1) : '#') +
                s[m.floor(m.random() * 16)]
        })(Math,'0123456789abcdef',5)
    }
    var item={
        img:'img/haha.gif', //图片
        info:info, //文字
//        href:'http://www.jq22.com', //链接
//         close:true, //显示关闭按钮
        speed:1, //延迟,单位秒,默认6
//        bottom:70, //距离底部高度,单位px,默认随机
        color:getRandomColor, //颜色,默认白色
        old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色
    }
    $('body').barrager(item);
}