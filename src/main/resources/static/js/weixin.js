initConfig();
function initConfig() {
    $.ajax({
        type: "POST",
        url: "/oauth/wxConfig",
        dataType: "json",
        data:{path:location.href},
        success: function(result){
            if (result.status == '100000') {
                wx.config({
                    debug: false,
                    appId: result.result.appId,
                    timestamp: result.result.timestamp,
                    nonceStr: result.result.nonceStr,
                    signature: result.result.signature,
                    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'hideMenuItems']
                    // , 'onMenuShareQQ', 'onMenuShareWeibo','onMenuShareQZone'
                });

                wx.ready(function(){

                    wx.hideMenuItems({
                        menuList: ['menuItem:share:qq','menuItem:share:weiboApp','menuItem:share:facebook','menuItem:share:QZone',
                        'menuItem:openWithQQBrowser','menuItem:openWithSafari', 'menuItem:share:email'] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
                    });

                    var data = getShareData();
                    //分享给朋友
                    wx.onMenuShareAppMessage(data);

                    //分享到朋友圈
                    wx.onMenuShareTimeline(data);
                });

                wx.error(function (res) {
                    console.log("ERROR : " + res);
                });
            }
        }
    });
}

function getShareData() {
    var data = {
        link : location.href,
        title : null,
        desc : null,
        imgUrl : null
    };

    var sharContent = document.getElementsByTagName('meta')['share-data'];
    if (sharContent != null) {
        data.title = sharContent.getAttribute('share-title');
        data.desc = sharContent.getAttribute('share-desc');
        data.imgUrl = sharContent.getAttribute('share-img');
    }
    if (data.title == null || data.title == '') {
        // title = document.getElementsByTagName('meta')['Keywords'].getAttribute('title');
        data.title = document.title;
    }
    if (data.desc == null || data.desc == '') {
        data.desc = document.getElementsByTagName('meta')['Description'].getAttribute('content');
    }
    if (data.imgUrl != null && data.imgUrl.indexOf('http') != 0) {
        data.imgUrl = "http://" + location.hostname + (location.port==''?'':':'+location.port) + data.imgUrl;
    }
    if (data.imgUrl == null || data.imgUrl == '') {
        data.imgUrl = "http://" + location.hostname + (location.port==''?'':':'+location.port) + '/logo.png';
    }
    return data;
}