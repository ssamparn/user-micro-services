package com.microservices.userservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersManagementController {

    @GetMapping("/status/check")
    public String status() {
        return "Users microservice is running";
    }

}
