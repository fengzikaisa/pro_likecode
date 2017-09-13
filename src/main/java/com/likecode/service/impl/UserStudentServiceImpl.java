package com.likecode.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.UserStudentDao;
import com.likecode.service.UserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangkai on 2017/8/16.
 */
@Service
@AutoJsonRpcServiceImpl
public class UserStudentServiceImpl implements UserStudentService {
    @Autowired
    UserStudentDao userStudentDao;

    @Override
    public ResultBean getStudents() {

        ResultBean bean =new ResultBean();
        bean.setStatus(ConstantDefinition.SYSTEM_SUCCESS);
        bean.setResult(userStudentDao.getStudents());
        return bean;
    }

    @Override
    public Integer getCounts(String a) {
        return userStudentDao.getCounts();
    }
}
