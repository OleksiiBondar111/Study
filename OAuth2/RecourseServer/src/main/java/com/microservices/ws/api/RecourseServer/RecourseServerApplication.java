package com.microservices.ws.api.RecourseServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RecourseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecourseServerApplication.class, args);
	}

}
