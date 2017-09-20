package com.likecode.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangkai on 2017/9/15.
 */
@Log4j
@Controller
public class IndexController {

    @RequestMapping(value="index")
    public String index(Model model) {
        return "home";
    }

//    @RequestMapping(value="home")
//    public String home(Model model) {
//        return "/home";
//    }
}
