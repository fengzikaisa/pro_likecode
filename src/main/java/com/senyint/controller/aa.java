package com.senyint.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.senyint.common.bean.JsonBean;
import com.senyint.common.bean.ParameterBean;
import com.senyint.common.bean.ResultBean;

public class aa {
	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8081/aaa"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("aa", "11");
		map.put("bb", "22");
		JsonBean bean = new JsonBean();
		bean.setVersion("1.0");
		bean.setSource("AA");
		bean.setCustom("ASDA");
		bean.setId("1");
		bean.setParameter(map);
		String json = bean.toString();
		ResultBean result = client.invoke("testMethod", new Object[] { json }, ResultBean.class);
		System.out.println(result.toString());
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key", "12345678");
//		map.put("compId", "31");
//		map.put("lang", "2");
//
//		// map.put("name", "13241765786");
//		// map.put("type", "1");
//
//		JsonBean bean = new JsonBean();
//		bean.setVersion("1.0(�汾��)");
//		bean.setSource("php(��Դ)");
//		bean.setCustom("��Ҫ���صĲ���(�Զ�����Ϣ)");
//		bean.setId("1(ҵ��ID)");
//		bean.setParameter(map);
//
////		String json = bean.toString();
//		String aa = "aaaaaa";
////		System.out.println(json);
//		String result = client.invoke("multiplier", new Object[] { aa }, String.class);
//		System.out.println(result);
	}
}
