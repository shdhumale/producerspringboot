package com.siddhu;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProducerspringbootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ProducerspringbootApplication.class, args);
		SpringApplication app = new SpringApplication(ProducerspringbootApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		app.run(args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
