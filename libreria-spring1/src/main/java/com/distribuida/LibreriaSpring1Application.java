package com.distribuida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.distribuida.model")

public class LibreriaSpring1Application {

	public static void main(String[] args) {

		SpringApplication.run(LibreriaSpring1Application.class, args);
	}

}
