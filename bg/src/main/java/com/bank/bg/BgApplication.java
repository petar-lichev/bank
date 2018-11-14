package com.bank.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BgApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgApplication.class, args);
	}
}
