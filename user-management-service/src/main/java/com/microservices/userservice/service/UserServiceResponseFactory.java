package com.microservices.userservice.service;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.web.model.response.UserCreationResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceResponseFactory {

    public UserCreationResponse createUserCreationResponse(UserEntity userEntity) {

        return UserCreationResponse.builder()
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getFirstName())
                .email(userEntity.getEmail())
                .build();
    }
}
