package com.senyint.service;

import com.senyint.bean.UserStudent;
import com.senyint.common.bean.ResultBean;

import java.util.List;

/**
 * Created by wangkai on 2017/8/16.
 */
@com.googlecode.jsonrpc4j.JsonRpcService("/userInfo")
public interface UserStudentService {

    public ResultBean getStudents();

    public Integer getCounts(String a);
}
