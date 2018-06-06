package com.likecode.config;

import com.likecode.bean.ext.UserExt;
import com.likecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkai on 2017/11/14.
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExt user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+username);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ADMIN"));
        return new CustomUserDetails(username, user, auths);
    }
}
