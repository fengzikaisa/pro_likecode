package com.likecode.dao;

import com.likecode.bean.Barrage;
import com.likecode.bean.Blog;
import com.likecode.bean.ext.BlogExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wangkai on 2017/9/25.
 */
@Mapper
public interface BlogDao {

    List<BlogExt> getBlogs(@Param("status") String status);

    int insertBlog(Blog blog);

    int updateBlog (Blog blog);

    BlogExt selectBlog(int bid);

    int initBlogStat(int bid);

    int updateBlogStat(@Param("id") int id,@Param("str") String str);
}
