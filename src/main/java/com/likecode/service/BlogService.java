package com.likecode.service;

import com.likecode.bean.Blog;
import com.likecode.common.bean.ResultBean;

import java.util.List;

/**
 * Created by wangkai on 2017/10/10.
 * 博客服务
 */
public interface BlogService {

    List<Blog> getBlogs();

    ResultBean insertBlog(Blog blog);

    ResultBean updateBlog (Blog blog);

    Blog selectBlog(int bid);
}
