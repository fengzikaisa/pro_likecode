package com.likecode.dao;

import com.likecode.bean.Barrage;
import com.likecode.bean.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wangkai on 2017/9/25.
 */
@Mapper
public interface BlogDao {

    List<Blog> getBlogs();

    int insertBlog(@Param("barrage") Blog blog);

    int updateBlog (@Param("barrage") Blog blog);

    Blog selectBlog(int bid);
}
