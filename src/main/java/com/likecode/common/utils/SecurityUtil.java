package com.likecode.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * <p>公共方法类</p>
 * <p>md5算法实现</p>
 */
public class SecurityUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    /**
     * md5加密算法
     *
     * @param value 欲使用md5算法加密的字符串
     * @return String 已经使用md5算法加密后的字符串
     */
    public static String MD5(String value) {
        StringBuffer result = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes("UTF8"));
            byte s[] = md.digest();
            for (int i = 0; i < s.length; i++) {
                result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).
                        substring(6));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result.toString();
    }

    /**
     * 按照微信签名规则进行签名(md5签名)
     * 将参数名按ascii码升序，然后以key1=value1&key2=value2方式拼接，最后拼接&key=key，取md5，转大写
     *
     * @param map 参数集合
     * @param key 签名key
     * @return
     */
    public static String sign(Map<String, String> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        sb.append("key=");
        sb.append(key);
        //Util.log("Sign Before MD5:" + result);
        String result = MD5.MD5Encode(sb.toString()).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }

}
