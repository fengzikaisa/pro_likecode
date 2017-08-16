package com.senyint.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.senyint.bean.UserStudent;
import com.senyint.dao.UserStudentDao;
import com.senyint.service.UserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangkai on 2017/8/16.
 */
@Service
@AutoJsonRpcServiceImpl
public class UserStudentServiceImpl implements UserStudentService {
    @Autowired
    UserStudentDao userStudentDao;

    @Override
    public List<UserStudent> getStudents(String aa) {

        return userStudentDao.getStudents();
    }

    @Override
    public Integer getCounts(String a) {
        return userStudentDao.getCounts();
    }
}
