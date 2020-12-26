package com.microservices.userapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiGatewayApplication.class, args);
	}

}
