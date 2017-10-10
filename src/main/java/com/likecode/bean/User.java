package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by wangkai on 2017/10/9.
 * 用户实体
 */
@Alias("user")
@Data
public class User implements Serializable , UserDetails {


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












    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
