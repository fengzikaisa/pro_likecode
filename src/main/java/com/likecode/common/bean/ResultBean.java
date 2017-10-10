package com.likecode.common.bean;

import java.io.Serializable;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@Data
@Log4j
public class ResultBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 状态码 */
	private String status;
	/** 返回值 */
	private Object result;
	/**
	 * 返回信息
	 */
	private String msg;

	public ResultBean() {
		super();
	}

	public ResultBean(String status, Object result,String msg) {
		super();
		this.status = status;
		this.result = result;
		this.msg=msg;
	}

	@Override
	public String toString() {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException:", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("JsonProcessingException1:", e);
			e.printStackTrace();
		}
		return str;
	}
}
