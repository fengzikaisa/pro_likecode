package com.likecode.common.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Cookie 工具类
 */
public class CookieUtils {

    private static String cookieKey = "key_cookie";
    private static String domain = "";

    static {
        if (StringUtils.isBlank(domain)) {
            domain = "localhost";
        }
    }

    private static String getName(String name) {
        return "_" + SecurityUtil.MD5(name);
    }

    /**
     * 根据Cookie名称得到Cookie的值，没有返回Null
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name) {

        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            String value = cookie.getValue();
            // 解密value
            CookieCrypt crypt = CookieCrypt.getInstance();
            value = crypt.decryptAES(value, cookieKey);
            return value;
        } else {
            return null;
        }
    }

    /**
     * 根据Cookie名称得到Cookie的值，没有返回Null(当Cookie的值含有空格时使用该方法)
     *
     * @param request
     * @param name
     * @return
     */
    public static String getSpaceCookieValue(HttpServletRequest request, String name) {
        name = getName(name); // key值加密
        String cookies = request.getHeader("Cookie");
        if (cookies == null) {
            return null;
        }
        int start = cookies.indexOf(name + "=");
        if (start == -1) {
            return null;
        }
        String value = cookies.substring(start + name.length() + 1);
        start = value.indexOf(";");
        if (start == -1) {
            start = value.length();
        }
        if (value.startsWith("\"")) {
            value = value.substring(1, start - 1);
        } else {
            value = value.substring(0, start);
        }
        value = value.replace("\\\"", "\"").replace("\\\\", "\\");
        // 解密value
        CookieCrypt crypt = CookieCrypt.getInstance();
        value = crypt.decryptAES(value, cookieKey);
        return value;
    }

    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        name = getName(name); // key值加密
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name)) {
                continue;
            }
            cookie = cookies[i];
            break;

        }
        return cookie;
    }

    /**
     * 删除指定Cookie
     *
     * @param response
     * @param cookie
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * 删除指定Cookie
     *
     * @param response
     * @param cookie
     * @param domain
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String domain) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * 添加一条新的Cookie信息，默认有效时间为一个月
     *
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        int oneMonth = 60 * 60 * 24 * 30;
        setCookie(response, name, value, oneMonth);
    }

    /**
     * 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        if (value == null) {
            value = "";
        }
        name = getName(name); // key值加密
        CookieCrypt crypt = CookieCrypt.getInstance();
        value = crypt.encryptAES(value, cookieKey);
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 添加一条新的Cookie信息，可以设置其Name，Value，MaxAge，Path，Domain(单位：秒)
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path,
                                 String domain) {
        if (value == null) {
            value = "";
        }

        name = getName(name); // key值加密
        CookieCrypt crypt = CookieCrypt.getInstance();
        value = crypt.encryptAES(value, cookieKey);

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public static void setCNCookie(HttpServletResponse response, String name, String value) {
        setCNCookie(response, name, value, 0x278d00);
    }

    /**
     * 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCNCookie(HttpServletResponse response, String name, String value, int maxAge) {
        if (value == null) {
            value = "";
        }

        name = SecurityUtil.MD5(name); // key值加密
        try {
            value = java.net.URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public static String getCNCookieValue(HttpServletRequest request, String name) {

        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            String value = cookie.getValue();
            try {
                value = java.net.URLDecoder.decode(value, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return value;
        } else {
            return null;
        }
    }

    /**
     * 获取不加密的cookie
     *
     * @param request
     * @param name
     * @return
     */
    private static Cookie getENCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name)) {
                continue;
            }
            cookie = cookies[i];
            break;

        }
        return cookie;
    }

    /**
     * 获取不加密的值
     *
     * @param request
     * @param name
     * @return
     */
    public static String getENCookieValue(HttpServletRequest request, String name) {

        Cookie cookie = getENCookie(request, name);
        if (cookie != null) {
            String value = cookie.getValue();
            return value;
        } else {
            return null;
        }
    }

}
