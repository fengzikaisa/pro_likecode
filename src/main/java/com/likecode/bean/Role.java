package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/10/9.
 * 角色实体
 */
@Alias("role")
@Data
public class Role implements Serializable {

    private int id;
    private String roleName;
    private String roleDescribe;
}
