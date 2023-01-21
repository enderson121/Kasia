package com.blog.app.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.dating.app.task.dating.service"})
@ComponentScan(basePackages = {"com.dating.app.task.dating.service.impl"})
@ComponentScan(basePackages = {"com.dating.app.task.dating.controller"})
@ComponentScan(basePackages = {"com.dating.app.task.dating.model"})
@ComponentScan(basePackages = {"com.dating.app.task.dating.repository"})

@SpringBootApplication
public class DatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatingApplication.class, args);
	}

}
