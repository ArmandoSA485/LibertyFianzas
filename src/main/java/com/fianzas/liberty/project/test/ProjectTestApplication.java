package com.fianzas.liberty.project.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.finanzas.liberty.project.test")
public class ProjectTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTestApplication.class, args);
	}

}
