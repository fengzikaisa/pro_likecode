package com.likecode.controller;

import com.alibaba.fastjson.JSONObject;
import com.likecode.common.controller.BaseController;
import com.likecode.common.utils.RandomUtil;
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

/**
 * Created by wangkai on 2017/10/26.
 */
@Log4j
@Controller
@RequestMapping("uploadImg")
public class UploadImgController extends BaseController {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.prefix}")
    private String uploadPrefix;

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
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject res = new JSONObject();
        res.put("url", uploadPrefix+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;

    }
}
