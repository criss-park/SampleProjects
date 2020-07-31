package com.simplify.contextPathExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContextPathExampleApplication {

	public static void main(String[] args) {

		System.setProperty("server.servlet.context-path", "/simplify");

		SpringApplication.run(ContextPathExampleApplication.class, args);
	}

}
