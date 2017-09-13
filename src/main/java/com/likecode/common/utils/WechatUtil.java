package com.likecode.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

public final class WechatUtil {

    /**
     * 微信网页端登录授权地址
     *
     * @param apiId       公众号的唯一标识
     * @param redirectUri 授权后重定向的回调链接地址
     * @param scope       应用授权作用域snsapi_base或snsapi_userinfo
     * @param state       重定向后会带上state参数
     */
    public static String loginUrl(String apiId, String redirectUri, String scope, String state) {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code" +
                "&scope=%s&state=%s#wechat_redirect";
        url = String.format(url, apiId, redirectUri, scope, state);
        return "redirect:" + url;
    }

    /**
     * 微信网页端登录授权地址
     */
    public static String loginUrl(String apiId, String redirectUri) {
        return loginUrl(apiId, redirectUri, "snsapi_userinfo", null);
    }

    /**
     * 获取授权后的用户信息
     *
     * @param apiId  公众号的唯一标识
     * @param secret 应用密钥
     * @param code   回调code
     */
    public static JSONObject getUserInfoByCode(String apiId, String secret, String code) throws Exception {
        JSONObject atJson = getAccessToken(apiId, secret, code);
        String wxAccessToken = atJson.getString("access_token"); // 调用凭证
        String openId = atJson.getString("openid"); // 普通用户的标识，对当前开发者帐号唯一
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
        String responseText = HttpClientUtil.doGet(String.format(url, wxAccessToken, openId));

        if (StringUtils.isNotBlank(responseText)) {
            JSONObject obj = JSON.parseObject(responseText);
            if (StringUtils.isNotBlank(obj.getString("errcode"))) { // 错误返回
                throw new Exception("微信用户信息失败" + obj.getString("errcode"));
            }
            obj.put("accesstoken", wxAccessToken);
            return obj;
        }
        return null;
    }

    /**
     * 通过code获取access_token
     */
    public static JSONObject getAccessToken(String apiId, String secret, String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type" +
                "=authorization_code";
        url = String.format(url, apiId, secret, code);
        String responseText = HttpClientUtil.doGet(url);
        if (StringUtils.isNotBlank(responseText)) {
            JSONObject obj = JSON.parseObject(responseText);
            if (StringUtils.isNotBlank(obj.getString("errcode"))) { // 错误返回
                throw new Exception("获取access_token失败" + obj.getString("errcode"));
            }
            return obj;
        } else {
            throw new Exception("获取access_token失败");
        }
    }

    /**
     * 微信网页端登录授权地址
     */
    public static String loginUrlForPC(String apiId, String redirectUri) {
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
        url = String.format(url, apiId, redirectUri, null);
        return "redirect:" + url;
    }

}
