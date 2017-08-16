package com.senyint.service;

import com.senyint.bean.UserStudent;
import java.util.List;

/**
 * Created by wangkai on 2017/8/16.
 */
@com.googlecode.jsonrpc4j.JsonRpcService("/userInfo")
public interface UserStudentService {

    public List<UserStudent> getStudents(String aa);

    public Integer getCounts(String a);
}
