package com.kadoo_academy.kadoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.config.ConfigDataResource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class KadooApplication {

	public static void main(String[] args) {
		SpringApplication.run(KadooApplication.class, args);
	}

}
