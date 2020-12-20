package com.microservices.accountmanagementservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountMagementController {

    @GetMapping("/status/check")
    public String getStatus() {
        return "Account Management Service is running";
    }
}
