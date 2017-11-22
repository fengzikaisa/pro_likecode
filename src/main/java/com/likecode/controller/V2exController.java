package com.likecode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.likecode.bean.Blog;
import com.likecode.bean.FriendshipLink;
import com.likecode.bean.ext.BlogExt;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.http.HttpClient;
import com.likecode.common.http.SimpleResponse;
import com.likecode.common.utils.HttpClientUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wangkai on 2017/11/22.
 * V2EX接口信息
 */
@Log4j
@Controller
@RequestMapping("v2ex")
public class V2exController {

    @RequestMapping("")
    public String hot(Model model) {
        return "blog/article";
    }

    @ResponseBody
    @RequestMapping(value="data")
    public JSONArray addBlog(String type) {
        log.info("type:"+type);
        String hotUrl="https://www.v2ex.com/api/topics/hot.json";
        String newUrl="https://www.v2ex.com/api/topics/latest.json";
        String url="hot".equals(type)? hotUrl:newUrl;
        SimpleResponse response = HttpClient.get(url);
        if (response.code != 200) {
            throw new RuntimeException("通信异常，异常code[" + response.code + "], 异常信息[" + response.body +"]");
        }
        log.info("内容: " + response.body);
        String str=response.body;
        JSONArray data = JSON.parseArray(str);
        return data;
    }
}
