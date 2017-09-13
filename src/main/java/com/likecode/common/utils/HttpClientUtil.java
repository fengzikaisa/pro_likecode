package com.likecode.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

public final class HttpClientUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";

    private HttpClientUtil() {
    }

    /**
     * httpclient doGet 默认编码UTF-8
     */
    public static String doGet(String url) {
        return doGet(url, "UTF-8");
    }

    /**
     * httpclient doGet
     */
    public static String doGet(String url, String charset) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        String response;
        try {
            method.addRequestHeader("Connection", "close");
            if (null == url || !url.startsWith("http")) {
                throw new Exception("请求地址格式不对");
            }
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {// 打印服务器返回的状态
                return null;
            }
            // 返回响应消息
            byte[] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());
            response = new String(responseBody, charset);
            method.releaseConnection();
            return response;
        } catch (Exception e) {
            logger.error("请求失败", e);
        } finally {
            try {
                // 释放连接
                method.releaseConnection();
                client.getHttpConnectionManager().closeIdleConnections(1);
            } catch (Exception e) {
                logger.error("释放连接失败", e);
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static String doPost(String url, String jsonParam) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        RequestEntity entity = new StringRequestEntity(jsonParam);
        method.setRequestEntity(entity);
        String response;
        try {
            method.addRequestHeader("Connection", "close");
            if (null == url || !url.startsWith("http")) {
                throw new Exception("请求地址格式不对");
            }
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {// 打印服务器返回的状态
                return null;
            }
            // 返回响应消息
            byte[] responseBody = method.getResponseBody();
            response = new String(responseBody, "UTF-8");
            method.releaseConnection();
            return response;
        } catch (Exception e) {
            logger.error("请求失败", e);
        } finally {
            try {
                // 释放连接
                method.releaseConnection();
                client.getHttpConnectionManager().closeIdleConnections(1);
            } catch (Exception e) {
                logger.error("释放连接失败", e);
            }
        }
        return null;
    }


    /**
     * 发送https请求
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl,
                                          String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(null,requestUrl,new sun.net.www.protocol.https.Handler());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String postData(String urlStr, String data){
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType){
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if(contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    public static JSONObject httpGet(String url){
        JSONObject jsonObject = null;
        String result="";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);//这里发送get请求
            // 获取当前客户端对象
            org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            }
            jsonObject = JSONObject.parseObject(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
        //....result是用户信息,站内业务以及具体的json转换这里也不写了...
    }

    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
