package com.likecode.service.impl;

import com.likecode.bean.ext.UserExt;
import com.likecode.dao.UserDao;
import com.likecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangkai on 2017/10/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserExt getUser(String userName) {
        return userDao.getUser(userName);
    }
}
