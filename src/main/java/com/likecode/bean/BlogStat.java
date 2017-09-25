package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/9/25.
 * 博客统计实体
 */
@Alias("blogStat")
@Data
public class BlogStat implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int blogId;//博客id
    private int readCount;//浏览数
    private int upvote;//点赞数
    private int commentCount;//评论数
}
