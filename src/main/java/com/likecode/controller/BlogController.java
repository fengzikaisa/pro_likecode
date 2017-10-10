package com.likecode.controller;

import com.likecode.bean.Blog;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.service.BlogService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangkai on 2017/9/25.
 * 博客控制器
 */
@Log4j
@Controller
public class BlogController extends BaseController {

    @Autowired
    BlogService blogService;

    @RequestMapping(value="addBlogPage")
    public String addBlogPage(Model model) {
        return "addBlogPage";
    }

    @ResponseBody
    @RequestMapping(value="addBlog")
    public ResultBean addBlog(Model model, Blog blog) {
        return blogService.insertBlog(blog);
    }
}
