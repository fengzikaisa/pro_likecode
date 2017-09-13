package com.senyint.controller;

import java.net.URL;
import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.senyint.bean.UserStudent;
import com.senyint.common.bean.ResultBean;

public class aa {
	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8081/userInfo"));

		String aa = "";

//		String result = client.invoke("multiplier", new Object[] { aa }, String.class);
//		Integer number= client.invoke("getCounts",new Object[] {aa}, Integer.class);
		ResultBean bean = client.invoke("getStudents", new Object[]{}, ResultBean.class);

		System.out.println(bean);
	}
}

