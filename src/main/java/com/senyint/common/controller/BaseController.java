package com.senyint.common.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.senyint.common.bean.ResultBean;

import lombok.extern.log4j.Log4j;

@Log4j
public class BaseController {
	/**
	 * 全局异常返回
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody ResultBean handlerException(Exception e) {
		log.error("ExceptionHandler:", e);
		return new ResultBean("", null);
	}
}
