package com.shubham.todolistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class TodolistappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistappApplication.class, args);
	}

}


			// Flow of project

//1. 	Request comes
//2.	Extract username from Jwt token
//3.	use loadbyusername to get user object
//4.	this user object is used to pass with todoinput
//5.	using Auth we extract userId from principal

