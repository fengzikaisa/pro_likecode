package com.likecode.common.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.util.Map;


/**
 * https 请求封装
 *
 * @author 杨元
 */
public class HttpsClient {

    private static Log log = LogFactory.getLog(HttpsClient.class);

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    /**
     * 不带证书的构造方法
     *
     * @throws Exception
     */
    public HttpsClient() {
        httpClient = HttpClients.custom().build();

        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                .build();
    }

    /**
     * 带证书的构造方法
     *
     * @param certLocalPath 证书路径
     * @param certPassword  证书密码
     * @throws Exception
     */
    public HttpsClient(String certLocalPath, String certPassword) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(certLocalPath));//加载本地的证书进行https加密传输

            try {
                keyStore.load(instream, certPassword.toCharArray());//设置证书密码
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, certPassword.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            //根据默认超时限制初始化requestConfig
            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout)
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 通过Https往API post 数据
     *
     * @param url     API地址
     * @param data    要提交的XML数据对象
     * @param headers 自定义请求头部
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String sendPost(String url, String data, Map<String, String> headers) {

        String result = null;
        HttpPost httpPost = null;

        try {

            httpPost = new HttpPost(url);

            log.info("API，POST过去的数据是：");
            log.info(data);

            //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
            StringEntity postEntity = new StringEntity(data, "UTF-8");

            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            httpPost.setEntity(postEntity);

            //设置请求器的配置
            httpPost.setConfig(requestConfig);

            log.info("executing request" + httpPost.getRequestLine());

            //执行请求
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            httpPost.abort();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
//        HttpsClient httpsRequest = new HttpsClient();
//        String r = httpsRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", "");
//        
//        System.out.println(r);
    }

}
