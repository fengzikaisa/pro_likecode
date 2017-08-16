package com.senyint.service;

@com.googlecode.jsonrpc4j.JsonRpcService("/aaa")
public interface JsonRpcService {
	String multiplier(String json);
}
