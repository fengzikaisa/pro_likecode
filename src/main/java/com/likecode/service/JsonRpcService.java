package com.likecode.service;

import com.likecode.common.bean.ResultBean;

@com.googlecode.jsonrpc4j.JsonRpcService("/aaa")
public interface JsonRpcService {
	String multiplier(String json);
	ResultBean testMethod(String json);
}
