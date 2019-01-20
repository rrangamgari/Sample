package com.example.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Account;
import com.example.demo.service.UserService;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleApplication.class);
	}

	@Autowired
	private UserService userService;

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			userService.updateRoles();
			
		};
	}
	
}
