package com.senyint.common.bean;

import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senyint.common.Exception.ParameterException;

@Data
public class JsonBean {
	/** 接口版本号 */
	private String version;
	/** 业务id */
	private String id;//
	/** 来源android java php 等 */
	private String source;//
	/** 自定义消息 */
	private Object custom;//
	/** 接口私有参数 */
	private Object parameter;//

	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;

	}

	public JsonBean(String version, String id, String source, String custom, Object parameter) {
		super();
		this.version = version;
		this.id = id;
		this.source = source;
		this.parameter = parameter;
		this.custom = custom;
	}

	/**
	 * json串转换成ParameterBean对象构造器parameter返回对象.
	 * 
	 * @param json
	 *            json串
	 * @throws Exception
	 *             参数转换异常
	 */
	public JsonBean(String json, Class<?> cls) throws ParameterException {
		super();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonBean dto = null;
		try {
			dto = objectMapper.readValue(json, JsonBean.class);
		} catch (Exception e) {
			throw new ParameterException("接收参数异常");
		}
		this.version = dto.getVersion();
		this.id = dto.getId();
		this.source = dto.getSource();
		this.custom = dto.getCustom();

		// // JSON串中的parameter为List类型
		if (dto.getParameter() instanceof List) {
			JSONArray jsonArray = JSONArray.fromObject(dto.getParameter());
			this.parameter = JSONArray.toList(jsonArray, cls);
		}
		// JSON串中的parameter为Map类型
		else {
			JSONObject jsonobject = JSONObject.fromObject(dto.getParameter());
			this.parameter = JSONObject.toBean(jsonobject, cls);
		}

	}

	/**
	 * json串转换成ParameterBean对象构造器parameter返回map类型适用于多个参数的map
	 * 
	 * @param json
	 *            json串
	 * @throws Exception
	 *             参数转换异常
	 */
	public JsonBean(String json) throws ParameterException {
		super();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonBean dto = null;
		try {
			dto = objectMapper.readValue(json, JsonBean.class);
		} catch (Exception e) {
			throw new ParameterException("接收参数异常");
		}
		this.version = dto.getVersion();
		this.id = dto.getId();
		this.source = dto.getSource();
		this.parameter = dto.getParameter();
		this.custom = dto.getCustom();
	}

	public JsonBean() {
		super();
	}

	public static void main(String args[]) {
		JsonBean setdto = new JsonBean();
		setdto.setId("1");
		setdto.setSource("source");
		setdto.setVersion("version");
		Map<String, String> map = new HashMap<String, String>();

		map.put("id", "id");
		map.put("userName", "userName");
		map.put("password", "password");
		setdto.setParameter(map);
		ObjectMapper mapper = new ObjectMapper();
		String str = null;
		try {
			str = mapper.writeValueAsString(setdto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
