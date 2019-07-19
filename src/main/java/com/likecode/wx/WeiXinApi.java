package com.likecode.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.likecode.common.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class WeiXinApi {

    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&state=xinyixy&scope=%s#wechat_redirect";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    private static final String BASE_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    @Autowired
    private WeiXinConfig config;

    /**
     * 公众号用于调用微信JS接口的临时票据
     * @return
     */
    public String getJsApiTicket() {
        String accessToken = getAccessToken();
        JSONObject jsonObject = HttpClientUtil.httpGet(getTicketUrl(accessToken));
        System.out.println(JSON.toJSONString(jsonObject));
        if (jsonObject != null) {
            return jsonObject.getString("ticket");
        }
        return null;
    }

    /**
     * access_token是公众号的全局唯一接口调用凭据
     * @return
     */
    public String getAccessToken() {
        JSONObject jsonObject = HttpClientUtil.httpGet(getTokenUrl());
        System.out.println(JSON.toJSONString(jsonObject));
        if (jsonObject != null) {
            return jsonObject.getString("access_token");
        }
        return null;
    }


    /**
     * 获取token的请求地址
     * @param code
     * @return
     */
    private String getAccessTokenUrl(String code) {
        return String.format(ACCESS_TOKEN_URL, config.getAppId(), config.getAppSecret(), code);
    }

    /**
     * 获取token的请求地址
     * @return
     */
    public String getTokenUrl() {
        return String.format(TOKEN_URL, config.getAppId(), config.getAppSecret());
    }

    /**
     * 获取jsapi_ticket的请求地址
     * @return
     */
    private String getTicketUrl(String accessToken) {
        return String.format(TICKET_URL, accessToken);
    }

    /**
     * 获取用户信息请求地址
     * @param accessToken
     * @return
     */
    private String getUserInfoUrl(String accessToken) {
        return String.format(USER_INFO_URL, accessToken, config.getAppId());
    }

    public String getBaseUserInfoUrl(String accessToken, String opentId) {
        return String.format(BASE_USER_INFO_URL, accessToken, opentId);
    }


    public Map<String, String> sign(String jsApiTicket, String url) {
        Map<String, String> ret = new HashMap<>(6);
        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsApiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", config.getAppId());
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
