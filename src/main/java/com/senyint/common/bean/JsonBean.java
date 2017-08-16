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
	private String version;// 接口版本号
	private String id;// 业务ID
	private String source;// 来源
	private Object custom;// 自定义消息
	private Object parameter;// 接口参数

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

		// 纯如的JSON串中的parameter为List类型
		if (dto.getParameter() instanceof List) {
			JSONArray jsonArray = JSONArray.fromObject(dto.getParameter());
			this.parameter = JSONArray.toList(jsonArray, cls);
		}
		// 纯如的JSON串中的parameter为Map类型
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
		System.out.println("对象转换成json:" + str);

		System.out.println("===================================");

		/*try {
			System.out.println("str=" + str);
			JsonBean getBean = new JsonBean(str,
					UserBean.class);
			System.out.println("version=" + getBean.getVersion());
			System.out.println("id=" + getBean.getId());
			System.out.println("source=" + getBean.getSource());

			UserBean userBean = (UserBean) getBean.getParameter();
			System.out.println(userBean.getId());
			System.out.println(userBean.getPassword());
			System.out.println(userBean.getUserName());

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
