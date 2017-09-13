package com.likecode.dao;

import com.likecode.bean.UserStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangkai on 2017/8/16.
 */
@Mapper
public interface UserStudentDao {
    public List<UserStudent> getStudents();

    public Integer getCounts();
}
