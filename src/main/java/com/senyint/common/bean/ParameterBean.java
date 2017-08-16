package com.senyint.common.bean;

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

	/** �û�id */
	private String uid;
	/** ����ת���ӿ�URL */
	private String interfaceUrl;
	/** ִ�з��� */
	private String method;
	/** �ӿڲ��� */
	private Object parameter;

	public Object getParameter() {
		if (parameter instanceof ArrayList)
			this.parameter = new HashMap<String, Object>();
		if (parameter == null)
			this.parameter = new HashMap<String, Object>();
		return parameter;
	}

	/***
	 * Parameter ��ת��
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
			log.error("����ת������:", e);
			e.printStackTrace();
		}
		return parameter;
	}

	/***
	 * stringתjson
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
	 * ��д
	 */
	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.info("��ת��json����:", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.info("��ת��json����:", e);
			e.printStackTrace();
		}
		return str;
	}

}
