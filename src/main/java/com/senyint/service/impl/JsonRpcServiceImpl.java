package com.senyint.service.impl;

import org.springframework.stereotype.Service;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.senyint.service.JsonRpcService;

@Service
@AutoJsonRpcServiceImpl
public class JsonRpcServiceImpl implements JsonRpcService {

	@Override
	public String multiplier(String a) {
		System.out.println("11111111111111111=="+a);
		return "aa";
	}

}
