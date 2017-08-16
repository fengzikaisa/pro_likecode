package com.senyint.common.Exception;

import org.springframework.dao.DataAccessException;

public class ParameterException extends DataAccessException {
	public ParameterException(String msg) {
		super(msg);
	}
}
