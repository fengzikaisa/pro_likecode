package com.senyint.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.senyint.common.bean.ResultBean;

@com.googlecode.jsonrpc4j.JsonRpcService("/aaa")
public interface JsonRpcService {
	String multiplier(String json);
	ResultBean testMethod(String json);
}
