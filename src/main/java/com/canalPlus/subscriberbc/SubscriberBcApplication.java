package com.canalPlus.subscriberbc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubscriberBcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriberBcApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
