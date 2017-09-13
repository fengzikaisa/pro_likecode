package com.likecode.common.utils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;

@Component
public class ServletContextUtil {
	private static ServletContext servletContext;

	@Resource
	public void setServletContext(ServletContext sc) {
		ServletContextUtil.servletContext = sc;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static String getHomePath() {
		return ServletContextUtil.servletContext.getRealPath("/");
	}
}
