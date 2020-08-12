package com.simplify.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogbackApplication {

	private static final Logger logger = LoggerFactory.getLogger(LogbackApplication.class);

	public static void main(String[] args) {

<<<<<<< HEAD
		logger.info("logging with root INFO?");
=======
		logger.info("Hello logback");
>>>>>>> 68dee9900f820380016657f9a1b827636e1f179d

		SpringApplication.run(LogbackApplication.class, args);
	}

}
