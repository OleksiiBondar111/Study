package com.microservices.api.AlbumsResourceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlbumsResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumsResourceServerApplication.class, args);
	}

}
