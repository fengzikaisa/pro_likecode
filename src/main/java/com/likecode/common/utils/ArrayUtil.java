package com.likecode.common.utils;


import org.apache.commons.lang3.StringUtils;

public class ArrayUtil {

    public static Integer[] string2Integer(String[] source) throws Exception {
        Integer[] result = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            String s = source[i];
            if (StringUtils.isNotEmpty(s)) {
                result[i] = Integer.valueOf(s);
            }
        }

        return result;
    }

}
