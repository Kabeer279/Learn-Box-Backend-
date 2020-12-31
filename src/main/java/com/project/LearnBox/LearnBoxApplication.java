package com.project.LearnBox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class LearnBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnBoxApplication.class, args);
	}

	
}
