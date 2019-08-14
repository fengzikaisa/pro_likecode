package com.likecode.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.likecode.common.utils.HttpClientUtil;
import com.likecode.common.utils.UserAgentUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangkai on 2019/8/14.
 */
@Log4j
@Controller
@RequestMapping("img")
public class ImgController {

    private static final String domain="https://cn.bing.com";
    /**
     * 每日一图
     * @param model
     * @return
     */
    @RequestMapping(value="")
    public String img(Model model, HttpServletRequest request) {
        JSONObject jsonObject=HttpClientUtil.httpGet("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
        JSONArray jsonArray=jsonObject.getJSONArray("images");
        JSONObject jsonObject1=jsonArray.getJSONObject(0);
        String u=jsonObject1.getString("url");
        String url=domain+u;
        if(UserAgentUtils.isMobile(request)){
            model.addAttribute("url",url.replaceFirst("1920x1080","480x800"));
        }else{
            model.addAttribute("url",url);
        }
        return "img/index";

    }

}
