package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/10/19.
 * 照片
 */
@Alias("photo")
@Data
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int albumId;
    private String photoName;
    private String url;
    private String isDel;
}
