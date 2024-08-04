package com.turismoapp.turismo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.turismoapp.turismo_app")
@EnableJpaRepositories(basePackages = "com.turismoapp.turismo_app.repository")
public class TurismoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurismoAppApplication.class, args);
	}

}
