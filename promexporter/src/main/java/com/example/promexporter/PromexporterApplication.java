package com.example.promexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PromexporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromexporterApplication.class, args);
	}

}
