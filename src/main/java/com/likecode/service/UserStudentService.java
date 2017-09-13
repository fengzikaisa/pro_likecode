package com.likecode.service;

import com.likecode.common.bean.ResultBean;

/**
 * Created by wangkai on 2017/8/16.
 */
@com.googlecode.jsonrpc4j.JsonRpcService("/userInfo")
public interface UserStudentService {

    public ResultBean getStudents();

    public Integer getCounts(String a);
}
