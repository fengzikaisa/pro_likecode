package com.likecode.common.utils;

import org.apache.commons.lang3.StringUtils;

public class EmojiFilterUtils {
    /**
     * 将emoji表情替换成*
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }
}
