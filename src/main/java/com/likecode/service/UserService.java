package com.likecode.service;

import com.likecode.bean.ext.UserExt;

/**
 * Created by wangkai on 2017/10/12.
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    UserExt getUser(String userName);
}
