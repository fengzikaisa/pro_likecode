package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangkai on 2017/9/25.
 * 博客实体
 */
@Alias("blog")
@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private String title;//文章标题
    private String author;//作者
    private int type;//文章类型（10:原创、20:转载）
    private String loadUrl;//转载地址
    private String label;//文章标签
    private String description;//内容
    private String img;//缩略图地址
    private String status;//文章状态（10:可用、20:不可用）
    private Date createTime;
    private Date updateTime;
    private String blogAbstract;//摘要



}
