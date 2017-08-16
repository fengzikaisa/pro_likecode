package com.senyint.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;

@Configuration
public class ApplicationConfig {
	@Bean
	public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
		AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
		return exp;
	}
}
