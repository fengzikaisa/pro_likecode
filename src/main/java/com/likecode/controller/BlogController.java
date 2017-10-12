package com.likecode.controller;

import com.likecode.bean.Blog;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.service.BlogService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wangkai on 2017/9/25.
 * 博客控制器
 */
@Log4j
@Controller
public class BlogController extends BaseController {

    @Autowired
    BlogService blogService;

    /**
     * 添加博客页面
     * @param model
     * @return
     */
    @RequestMapping(value="addBlogPage")
    public String addBlogPage(Model model) {
        return "blog/addBlogPage";
    }

    /**
     * 添加博客
     * @param model
     * @param blog
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="addBlog",method = RequestMethod.POST)
    public ResultBean addBlog(Model model, Blog blog, HttpSession session) {
        int userId=session.getAttribute("userId")==null ? 0:Integer.valueOf(session.getAttribute("userId").toString());
        blog.setUserId(userId);
        return blogService.insertBlog(blog);
    }

    /**
     * 博客列表
     * @param model
     * @return
     */
    @RequestMapping("/blogList")
    public String blogList(Model model) {
        List<Blog> blogList=blogService.getBlogs();
        model.addAttribute("blogList",blogList);
        return "blog/blogList";
    }

    /**
     * 博客详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("blog/{id}")
    public String detail(Model model,@PathVariable("id") int id) {
        Blog blog=blogService.selectBlog(id);
        model.addAttribute("blog",blog);
        return "blog/blogDetail";
    }
}
