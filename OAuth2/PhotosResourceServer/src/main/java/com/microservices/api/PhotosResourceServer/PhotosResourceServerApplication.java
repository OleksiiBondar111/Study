package com.microservices.api.PhotosResourceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotosResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotosResourceServerApplication.class, args);
	}

}
