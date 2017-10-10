package com.likecode.service;

import com.likecode.bean.User;
import com.likecode.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by wangkai on 2017/10/9.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    /**
     * 登录验证逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUser(username);
        if( user == null ){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return user;
    }
}
