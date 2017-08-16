package com.senyint.common.bean;

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
	/** ×´Ì¬Âë */
	private String status;
	/** ·µ»ØÖµ */
	private Object result;

	public ResultBean() {
		super();
	}

	public ResultBean(String status, Object result) {
		super();
		this.status = status;
		this.result = result;
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
