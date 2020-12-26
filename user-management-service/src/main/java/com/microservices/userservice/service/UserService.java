package com.microservices.userservice.service;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.web.model.request.UserSignUpRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserSignUpRequest newUser);

    UserEntity getUserDetailsByEmail(String email);
}
