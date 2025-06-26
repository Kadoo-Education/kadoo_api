package com.kadoo_academy.kadoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class KadooApplication {

	public static void main(String[] args) {
		SpringApplication.run(KadooApplication.class, args);
	}

}
