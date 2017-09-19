package com.likecode.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangkai on 2017/9/19.
 */
@Log4j
@Controller
public class LoginController {

    @RequestMapping(value="login")
    public String home(Model model) {
        return "/login";
    }
}
