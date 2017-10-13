package com.likecode.service;

import com.likecode.bean.Blog;
import com.likecode.bean.ext.BlogExt;
import com.likecode.common.bean.ResultBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangkai on 2017/10/10.
 * 博客服务
 */
public interface BlogService {

    /**
     * 获取博客列表
     * @return
     */
    List<BlogExt> getBlogs();

    /**
     * 添加博客
     * @param blog
     * @return
     */
    ResultBean insertBlog(Blog blog);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    ResultBean updateBlog (Blog blog);

    /**
     * 博客详情
     * @param bid
     * @return
     */
    BlogExt selectBlog(int bid);

    /**
     * 跟新博客统计表
     * @param id 博客id
     * @param str 点击数：readCount  点赞数：upvote  评论数：commentCount
     * @return
     */
    ResultBean updateBlogStat(int id,String str);


}
