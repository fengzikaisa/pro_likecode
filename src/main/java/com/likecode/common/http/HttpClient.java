package com.likecode.common.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class HttpClient {

    private static Log log = LogFactory.getLog(HttpClient.class);

    /**
     * 请求配置
     */
    private static RequestConfig globalConfig = RequestConfig.
            custom().
            setCookieSpec(CookieSpecs.BEST_MATCH).
            setSocketTimeout(10000).
            setConnectTimeout(30000).
            setConnectionRequestTimeout(30000).
            build();
    /**
     * cookie容器
     */
    private static CookieStore cookieStore = new BasicCookieStore();

    /**
     * https配置
     */
    private static SSLContext sslContext;

    /**
     * 请求对象
     */
    private static CloseableHttpClient httpclient;

    static {
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;  //ignore all certificate error
                }
            }).build();

            httpclient = HttpClients.
                    custom().
                    setDefaultRequestConfig(globalConfig).
                    setDefaultCookieStore(cookieStore).
                    setSSLContext(sslContext).
                    setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).
                    build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * head请求
     *
     * @param url 请求地址
     * @return
     */
    public static SimpleResponse head(String url) {
        HttpHead request = new HttpHead(url);
        return sendRequest(request);
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return
     */
    public static SimpleResponse get(String url) {
        HttpGet request = new HttpGet(url);
        return sendRequest(request);
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return
     */
    public static SimpleResponse get(String url, Map<String, String> headers) {
        HttpGet request = new HttpGet(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                request.addHeader(key, headers.get(key));
            }
        }
        return sendRequest(request);
    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param content 请求体
     * @return
     */
    public static SimpleResponse post(String url, String content) {
        return post(url, content, null);
    }

    public static void main(String[] args) throws Exception {
        //测试
        String url="https://sandbox.itunes.apple.com/verifyReceipt";
        String data="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";
        Map<String,String> map=new HashMap<>();
        map.put("receipt-data",data);
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        String dataJson=itemJSONObj.toString();
        System.err.print(dataJson);
        SimpleResponse response = post(url,dataJson,null);
        System.out.println("code:"+response.code);
        System.out.println("body:"+response.body);
        JSONObject jsStr = JSONObject.parseObject(response.body);
        System.out.println("status:"+jsStr.getString("status"));

    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param content 请求体
     * @param headers 附加请求头
     * @return
     */
    public static SimpleResponse post(String url, String content, Map<String, String> headers) {
        return post(url, content, headers, null);
    }

    /**
     * post请求
     *
     * @param url         请求地址
     * @param content     请求体
     * @param headers     附加请求头
     * @param contentType 请求类型
     * @return
     */
    public static SimpleResponse post(String url, String content, Map<String, String> headers, String contentType) {
        HttpPost request = new HttpPost(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                request.addHeader(key, headers.get(key));
            }
        }
        StringEntity entity = new StringEntity(content, Charset.forName("UTF-8"));
        if (StringUtils.isEmpty(contentType)) {
            entity.setContentType("text/plain; charset=utf-8");
        } else {
            entity.setContentType(contentType);
        }
        request.setEntity(entity);
        return sendRequest(request);
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param file 请求体
     * @return
     */
    public static SimpleResponse post(String url, File file) {
        HttpPost request = new HttpPost(url);
        FileEntity entity = null;
        try {
            entity = new FileEntity(file);
            entity.setContentType("application/octet-stream");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setEntity(entity);
        return sendRequest(request);
    }

    /**
     * put请求
     *
     * @param url     请求地址
     * @param content 请求体
     * @return
     */
    public static SimpleResponse put(String url, String content) {
        HttpPut request = new HttpPut(url);
        StringEntity entity = null;
        try {
            entity = new StringEntity(content);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        entity.setContentType("text/plain; charset=utf-8");
        request.setEntity(entity);
        return sendRequest(request);
    }

    /**
     * delete请求
     *
     * @param url 请求地址
     * @return
     */
    public static SimpleResponse delete(String url) {
        return delete(url, null);
    }

    /**
     * delete请求
     *
     * @param url     请求地址
     * @param headers 附加请求头
     * @return
     */
    public static SimpleResponse delete(String url, Map<String, String> headers) {
        HttpDelete request = new HttpDelete(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                request.addHeader(key, headers.get(key));
            }
        }
        return sendRequest(request);
    }

    /**
     * 核心请求方法
     *
     * @param request 请求对象，可以是get、post...
     * @return
     */
    private static SimpleResponse sendRequest(HttpRequestBase request) {
        //响应对象
        CloseableHttpResponse response = null;
        //响应封装
        SimpleResponse simpleResponse = new SimpleResponse();

        try {
            //执行请求
            response = httpclient.execute(request);
            //获取响应状态码
            simpleResponse.code = response.getStatusLine().getStatusCode();
            //获取响应header
            simpleResponse.headers = response.getAllHeaders();
            //获取响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                simpleResponse.body = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.error("httpclient请求异常", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                response = null;
            }
        }

        return simpleResponse;
    }
}
