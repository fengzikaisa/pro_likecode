package com.likecode.controller;

import com.likecode.bean.Album;
import com.likecode.bean.Blog;
import com.likecode.bean.FriendshipLink;
import com.likecode.bean.ext.BlogExt;
import com.likecode.bean.ext.UserExt;
import com.likecode.common.SystemHelper;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.service.AlbumService;
import com.likecode.service.BlogService;
import com.likecode.service.FriendshipLinkService;
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
@RequestMapping("blog")
public class BlogController extends BaseController {

    @Autowired
    BlogService blogService;

    @Autowired
    FriendshipLinkService friendshipLinkService;


    /**
     * 添加博客页面
     * @param model
     * @return
     */
    @RequestMapping(value="addPage")
    public String addBlogPage(Model model) {
        return "blog/addBlogPage";

    }

    /**
     * 添加博客
     * @param blog
     * @return
     */
    @ResponseBody
    @RequestMapping(value="add",method = RequestMethod.POST)
    public ResultBean addBlog(Blog blog) {
        Integer userId=SystemHelper.currUserId();
        blog.setUserId(userId);
        return blogService.insertBlog(blog);
    }

    /**
     * 博客列表
     * @param model
     * @return
     */
    @RequestMapping("")
    public String blogList(Model model) {
        List<BlogExt> blogList=blogService.getBlogs();
        List<FriendshipLink> friend10= friendshipLinkService.getFriendshipLinkList("10");
        List<FriendshipLink> friend20= friendshipLinkService.getFriendshipLinkList("20");
        model.addAttribute("blogList",blogList);
        model.addAttribute("friend10",friend10);
        model.addAttribute("friend20",friend20);
        return "blog/blogList";
    }

    /**
     * 博客详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    public String detail(Model model,@PathVariable("id") int id) {
        log.info("详情id:"+id);
        BlogExt blog=blogService.selectBlog(id);
        model.addAttribute("blog",blog);
        return "blog/blogDetail";
    }



    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping("msgBoard")
    public String msgBoard(Model model) {
        return "blog/messageBoard";
    }


}
