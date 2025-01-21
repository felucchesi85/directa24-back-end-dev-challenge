package com.directa24.directa24_back_end_dev_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.directa24.directa24_back_end_dev_challenge.entity")
@EnableJpaRepositories("com.directa24.directa24_back_end_dev_challenge.repository")
public class Main {

	public static void main(String[] args) {SpringApplication.run(Main.class, args);
	}

}
