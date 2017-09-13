package com.likecode.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangkai on 2017/7/12.
 */
public class PayUtil {

    /**
     * 获取终端类型
     *
     * @param request
     * @return 安卓1 、苹果2 、桌面0
     */
    public static Integer getClientType(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String[] androidKeywords = {"Android"};
        String[] iosKeywords = {"iPhone", "iPod", "iPad"};
        // 排除 Windows 桌面系统
        if (userAgent.contains("Windows NT") || (userAgent.contains("Windows NT") && userAgent.contains("compatible; MSIE 9.0;"))) {
            return 0;
        }
        // 排除 苹果桌面系统
        if (userAgent.contains("Windows NT") && !userAgent.contains("Macintosh")) {
            return 0;
        }
        // 是否为苹果终端
        for (String item : iosKeywords) {
            if (userAgent.contains(item.toLowerCase())) {
                return 2;
            }
        }
        // 是否为安卓终端
        for (String item : androidKeywords) {
            if (userAgent.contains(item.toLowerCase())) {
                return 1;
            }
        }
        return 0;
    }
}
