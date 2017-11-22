package com.likecode.common.http;

import org.apache.http.Header;

/**
 * http响应，简单封装
 *
 * @author 杨元
 */
public class SimpleResponse {

    /**
     * 响应状态码
     */
    public int code = 0;

    /**
     * 响应内容
     */
    public String body = null;

    /**
     * 响应头信息
     */
    public Header[] headers = null;

}
