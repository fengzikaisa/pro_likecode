package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangkai on 2017/10/9.
 * 用户实体
 */
@Alias("user")
@Data
public class User implements Serializable {


    private int id;
    private String name;
    private String password;
    private Date createTime;
    private Date lastLoginTime;
    /**
     * 1为删除，0为非删除
     */
    private int del;
    private String mobile;
    private String email;
    /**
     * 用户备注
     */
    private String remark;

}
