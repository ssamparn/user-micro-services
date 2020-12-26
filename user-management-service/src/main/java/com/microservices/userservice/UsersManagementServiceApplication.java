package com.microservices.userservice;

import com.microservices.userservice.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersManagementServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UsersManagementServiceApplication.class, args);
	}

	@Autowired
	private UserProperties properties;

	@Override
	public void run(String... args) {

		System.out.println("@@@@@@@@@" + properties.getSecret());

		System.out.println("@@@@@@@@" + properties.getExpirationTime());

	}
}
