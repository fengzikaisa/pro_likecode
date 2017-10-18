package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/10/17.
 */
@Alias("album")
@Data
public class Album implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String url;
    private String type;//10:保密  20:其他
    private String isDel;//10:可用  20:不可用;
}
