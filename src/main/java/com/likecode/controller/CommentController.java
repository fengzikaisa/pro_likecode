package com.likecode.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangkai on 2019/8/16.
 */
@Log4j
@Controller
@RequestMapping("comment")
public class CommentController {

    @RequestMapping(value="")
    public String comment(Model model) {
        return "comment";

    }
}
