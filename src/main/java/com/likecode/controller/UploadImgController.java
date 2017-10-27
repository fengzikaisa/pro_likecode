package com.likecode.controller;

import com.alibaba.fastjson.JSONObject;
import com.likecode.common.controller.BaseController;
import com.likecode.common.utils.RandomUtil;
import com.likecode.common.utils.UploadQiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by wangkai on 2017/10/26.
 */
@Log4j
@Controller
@RequestMapping("uploadImg")
public class UploadImgController extends BaseController {

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

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.qiniu}")
    private String uploadQiniu;

    @RequestMapping("editormdPic")
    @ResponseBody
    public JSONObject editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+"_"+ RandomUtil.getRandomNumber(6)+suffix;

//        String path = request.getSession().getServletContext().getRealPath("/");
        String path =uploadPath;
        System.out.println(path);

        File targetFile = new File(path, fileName);
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
            String str=uploadFile(targetFile,fileName);
            log.info("str:"+str);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject res = new JSONObject();
        res.put("url", uploadQiniu+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;

    }

    public String uploadFile(File file, String fileName) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(file, fileName, getUpToken());
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
