package com.kadoo_academy.kadoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class KadooApplication {

	public static void main(String[] args) {
		SpringApplication.run(KadooApplication.class, args);
	}

}
