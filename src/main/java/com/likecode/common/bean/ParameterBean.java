package com.likecode.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/***
 * 
 * @author ly
 *
 */

@Data
@Log4j
public class ParameterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户id */
	private String uid;
	/** 二级转发接口URL */
	private String interfaceUrl;
	/** 执行方法 */
	private String method;
	/** 接口参数 */
	private Object parameter;

	public Object getParameter() {
		if (parameter instanceof ArrayList)
			this.parameter = new HashMap<String, Object>();
		if (parameter == null)
			this.parameter = new HashMap<String, Object>();
		return parameter;
	}

	/***
	 * Parameter 类转换
	 * 
	 * @param cls
	 * @return object
	 */
	public Object getParameter(Class<?> cls) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(parameter);
			parameter = mapper.readValue(json, cls);
		} catch (Exception e) {
			log.error("参数转换出错:", e);
			e.printStackTrace();
		}
		return parameter;
	}

	/***
	 * string转json
	 * 
	 * @param cls
	 * @param str
	 * @return
	 */
	public Object getParameter(Class<?> cls, String str) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			parameter = mapper.readValue(str, cls);
		} catch (Exception e) {
			log.error("param change error", e);
			e.printStackTrace();
		}
		return parameter;
	}

	/***
	 * 重写
	 */
	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.info("类转换json出错:", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("类转换json出错:", e);
			e.printStackTrace();
		}
		return str;
	}

}
