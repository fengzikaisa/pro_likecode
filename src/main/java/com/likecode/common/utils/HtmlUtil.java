package com.likecode.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class HtmlUtil {

    public static Document parseHtml(String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);//解析HTML字符串返回一个Document实现
        return doc;
    }

    public static String getTextByHtml(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return "";
        }
        Document doc = parseHtml(htmlStr);
        return doc.text();
    }

    public static String getTextByHtml(String htmlStr, int len) {
        String text = getTextByHtml(htmlStr);
        return text.length() <= len ? text : text.substring(0, len);
    }
}
