package com.senyint.service;

import com.googlecode.jsonrpc4j.JsonRpcParam;

@com.googlecode.jsonrpc4j.JsonRpcService("/aaa")
public interface JsonRpcService {
	String multiplier(String json);
}
