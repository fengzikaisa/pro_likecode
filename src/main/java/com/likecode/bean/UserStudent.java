package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/8/16.
 */
@Alias("userStudent")
@Data
public class UserStudent implements Serializable {

    private int id;
    private String name;
    private String mobile;
}
