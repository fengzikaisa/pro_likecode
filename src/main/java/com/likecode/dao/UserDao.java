package com.likecode.dao;

import com.likecode.bean.ext.UserExt;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wangkai on 2017/10/9.
 */
@Mapper
public interface UserDao {

    UserExt getUser(String userName);
}
