package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/10/17.
 */
@Alias("picture")
@Data
public class Picture implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String 
}
