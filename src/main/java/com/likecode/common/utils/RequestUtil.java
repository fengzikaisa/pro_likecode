package com.likecode.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    private static String[] mobileAgents = {"iphone", "android", "phone", "ipad", "ios"};

    /**
     * 判断是否是移动端
     */
    public static boolean isMoblie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String userAgent = request.getHeader("User-Agent");
        boolean isMoblie = false;
        if (userAgent != null) {
            for (String mobileAgent : mobileAgents) {
                if (userAgent.toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    /**
     * 判断ajax请求
     */
    public static boolean isAjax() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
