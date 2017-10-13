package com.likecode.bean.ext;

import com.likecode.bean.Blog;
import lombok.Data;

/**
 * Created by wangkai on 2017/10/13.
 */
@Data
public class BlogExt extends Blog {

    private int readCount;//浏览数
    private int upvote;//点赞数
    private int commentCount;//评论数

}
