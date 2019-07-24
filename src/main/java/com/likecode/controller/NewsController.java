package com.likecode.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangkai on 2019/7/23.
 */
@Log4j
@Controller
@RequestMapping("news")
public class NewsController {

    /**
     * @param model
     * @return
     */
    @RequestMapping(value="")
    public String list(Model model) {
        return "news/list";

    }
}
