package com.likecode.service.impl;

import org.springframework.stereotype.Service;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.likecode.bean.Tbean;
import com.likecode.common.bean.JsonBean;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.service.JsonRpcService;

@Service
@AutoJsonRpcServiceImpl
public class JsonRpcServiceImpl implements JsonRpcService {

	@Override
	public String multiplier(String a) {
		System.out.println("11111111111111111==" + a);
		return "aa";
	}

	@Override
	public ResultBean testMethod(String json) {
		JsonBean getBean = new JsonBean(json, Tbean.class);
		Tbean tbean= (Tbean) getBean.getParameter();
		System.out.println("111");
		return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS, tbean,"");
	}

}
