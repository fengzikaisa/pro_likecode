package com.likecode.controller;

import com.likecode.bean.User;
import com.likecode.bean.ext.UserExt;
import com.likecode.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by wangkai on 2017/9/19.
 */
@Log4j
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value="login")
    public String loginPage(Model model) {
        return "loginPage";
    }

//    @RequestMapping(value="login",method = RequestMethod.POST)
//    public String login(Model model, User user, HttpSession session) {
//        UserExt vo=userService.getUser(user.getUsername());
//        if(vo!=null && vo.getPassword().equals(user.getPassword())){
//            session.setAttribute("user",vo);
//            session.setAttribute("userId",vo.getId());
//            return "redirect:/loginSuccess";
//
//        }
//        return "redirect:/loginPage";
//    }

    @RequestMapping(value="loginSuccess")
    public String loginSuccess(Model model) {
        return "loginSuccess";
    }

    @RequestMapping(value="loginFail")
    public String loginFail(Model model) {
        return "loginFail";
    }
//
//    @RequestMapping(value="logout")
//    public String logout(Model model) {
//        return "redirect:/blog";
//    }
}
