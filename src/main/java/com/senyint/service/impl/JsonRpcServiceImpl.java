package com.senyint.service.impl;

import org.springframework.stereotype.Service;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.senyint.bean.Tbean;
import com.senyint.common.bean.JsonBean;
import com.senyint.common.bean.ResultBean;
import com.senyint.service.JsonRpcService;

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
		return new ResultBean("aa", tbean);
	}

}
