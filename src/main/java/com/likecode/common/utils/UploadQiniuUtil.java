package com.likecode.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.IOException;


public class UploadQiniuUtil {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "XlrwkhI2KEiAMLU89SYeCoKasX3afSCacYKfRKDJ";
    String SECRET_KEY = "eamcaJbb1VQ52ttVeL8fc5ST5_WXdi4r7RQOvffY";
    //要上传的空间
    String bucketname = "likecode";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);

    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    //上传到七牛后保存的文件名 fileName
    //上传文件的路径 filePath
    public void upload(String filePath,String fileName) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public String uploadFile(File file, String fileName) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(file, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            return res.bodyString();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return "";
    }

}
