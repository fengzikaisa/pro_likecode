package com.likecode.controller;

import com.likecode.bean.ext.BlogExt;
import com.likecode.service.BlogService;
import com.likecode.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wangkai on 2017/9/19.
 */
@Log4j
@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;

    @RequestMapping(value="login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value="loginSuccess")
    public String loginSuccess(Model model) {
        List<BlogExt> blogList=blogService.getBlogs(null);
        model.addAttribute("blogList",blogList);
        return "loginSuccess";
    }

    @RequestMapping(value="loginFail")
    public String loginFail(Model model) {
        return "loginFail";
    }

    @RequestMapping(value="logout")
    public String logout(Model model) {
        return "redirect:/blog";
    }
}
