package com.likecode.service.impl;

import com.likecode.bean.Blog;
import com.likecode.bean.ext.BlogExt;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.BlogDao;
import com.likecode.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangkai on 2017/10/10.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public List<BlogExt> getBlogs(String status) {
        return blogDao.getBlogs(status);
    }

    @Override
    public ResultBean insertBlog(Blog blog) {
        if(!blog.getStatus().equals("20")){
            blog.setStatus("10");
        }
        if(blogDao.insertBlog(blog)>0){
            //初始化博客统计记录
            blogDao.initBlogStat(blog.getId());
            return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS,blog,"");
        }
        return new ResultBean(ConstantDefinition.SYSTEM_ERROR,blog,"添加失败");
    }

    @Override
    public ResultBean updateBlog(Blog blog) {
        if(blogDao.updateBlog(blog)>0){
            return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS,blog,"");
        }
        return new ResultBean(ConstantDefinition.SYSTEM_ERROR,blog,"更新失败");
    }

    @Override
    public BlogExt selectBlog(int bid) {
        return blogDao.selectBlog(bid);
    }

    @Override
    public ResultBean updateBlogStat(int id, String str) {
        if(blogDao.updateBlogStat(id,str)>0){
            return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS,id,"");
        }
        return new ResultBean(ConstantDefinition.SYSTEM_ERROR,id,"博客统计更新失败");
    }

}
