package com.likecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setShowBanner(false);
//		app.run(args);
		
		SpringApplication.run(Application.class, args);
	}

}
