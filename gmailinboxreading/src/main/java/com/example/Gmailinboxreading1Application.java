package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class Gmailinboxreading1Application {

	public static void main(String[] args) {
		SpringApplication.run(Gmailinboxreading1Application.class, args);
	}

}
