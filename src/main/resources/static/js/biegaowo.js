document.onkeydown=function(){
    var e = window.event||arguments[0];
    if(e.keyCode==123){
        alert("欢迎访问--丰子恺撒");
        return false;
    }else if((e.ctrlKey)&&(e.shiftKey)&&(e.keyCode==73)){
        alert("欢迎访问--丰子恺撒");
        return false;
    }else if((e.ctrlKey)&&(e.keyCode==85)){
        alert("欢迎访问--丰子恺撒");
        return false;
    }else if((e.ctrlKey)&&(e.keyCode==83)){
        alert("欢迎访问--丰子恺撒");
        return false;
    }
}
document.oncontextmenu=function(){
    alert("欢迎访问--丰子恺撒");
    return false;
}