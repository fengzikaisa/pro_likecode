package com.likecode.service.impl;

import com.likecode.bean.Blog;
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
    public List<Blog> getBlogs() {
        return blogDao.getBlogs();
    }

    @Override
    public ResultBean insertBlog(Blog blog) {
        if(blogDao.insertBlog(blog)>0){
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
    public Blog selectBlog(int bid) {
        return blogDao.selectBlog(bid);
    }
}
