package com.example.StreamingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StreamingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingApiApplication.class, args);
	}

}
