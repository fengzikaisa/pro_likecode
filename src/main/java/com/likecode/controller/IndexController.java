package com.likecode.controller;

import com.alibaba.fastjson.JSONObject;
import com.likecode.bean.FriendshipLink;
import com.likecode.bean.ext.BlogExt;
import com.likecode.common.utils.HttpClientUtil;
import com.likecode.common.utils.UserAgentUtils;
import com.likecode.service.BlogService;
import com.likecode.service.FriendshipLinkService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangkai on 2017/9/15.
 */
@Log4j
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    FriendshipLinkService friendshipLinkService;

    @RequestMapping(value="/")
    public String netindex(Model model, HttpServletRequest request) {
        List<BlogExt> blogList=blogService.getBlogs("10");
        List<FriendshipLink> friend10= friendshipLinkService.getFriendshipLinkList("10");
        List<FriendshipLink> friend20= friendshipLinkService.getFriendshipLinkList("20");
        model.addAttribute("blogList",blogList);
        model.addAttribute("friend10",friend10);
        model.addAttribute("friend20",friend20);
        JSONObject jsonObject= HttpClientUtil.httpGet("http://open.iciba.com/dsapi");
        String content=jsonObject.getString("content");
        String note=jsonObject.getString("note");
        if(UserAgentUtils.isMobile(request)){
            model.addAttribute("content",content);
            model.addAttribute("note",note);
            return "mobile/blog";
        }
        return "blog/blogList";
    }

    @RequestMapping(value="index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value="home")
    public String home(Model model) {
        return "home";
    }
}
