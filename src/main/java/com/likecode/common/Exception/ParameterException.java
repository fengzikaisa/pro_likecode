package com.likecode.common.Exception;

import org.springframework.dao.DataAccessException;

/***
 * 自定义异常
 * 
 * @author ly
 *
 */
public class ParameterException extends DataAccessException {
	public ParameterException(String msg) {
		super(msg);
	}
}
