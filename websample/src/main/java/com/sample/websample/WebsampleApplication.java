package com.sample.websample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsampleApplication.class, args);
	}

}
