package com.senyint.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.senyint.common.bean.JsonBean;
import com.senyint.common.bean.ParameterBean;

public class aa {
	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8081/aaa"));
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key", "12345678");
//		map.put("compId", "31");
//		map.put("lang", "2");
//
//		// map.put("name", "13241765786");
//		// map.put("type", "1");
//
//		JsonBean bean = new JsonBean();
//		bean.setVersion("1.0(版本号)");
//		bean.setSource("php(来源)");
//		bean.setCustom("需要返回的参数(自定义消息)");
//		bean.setId("1(业务ID)");
//		bean.setParameter(map);
//
//		String json = bean.toString();
		String aa = "aaaaaa";
//		System.out.println(json);
		String result = client.invoke("multiplier", new Object[] { aa }, String.class);
		System.out.println(result);
	}
}
