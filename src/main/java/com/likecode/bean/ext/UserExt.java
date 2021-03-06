package com.likecode.bean.ext;

import com.likecode.bean.User;
import lombok.Data;

/**
 * Created by wangkai on 2017/10/9.
 * 用户实体扩展
 */
@Data
public class UserExt extends User {

    /**
     * 角色id
     */
    private int roleId;
    private String roleName;
    private String roleDescribe;
}
