package com.senyint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setShowBanner(false);
//		app.run(args);
		
		SpringApplication.run(Application.class, args);
	}

}
