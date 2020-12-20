package com.microservices.usersdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UsersDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersDiscoveryServiceApplication.class, args);
	}

}
