package com.simplify.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogbackApplication {

	private static final Logger logger = LoggerFactory.getLogger(LogbackApplication.class);

	public static void main(String[] args) {

		logger.info("Hello logback");

		SpringApplication.run(LogbackApplication.class, args);
	}

}
