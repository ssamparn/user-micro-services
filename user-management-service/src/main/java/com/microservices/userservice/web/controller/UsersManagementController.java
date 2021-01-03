package com.microservices.userservice.web.controller;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.service.UserService;
import com.microservices.userservice.service.UserServiceResponseFactory;
import com.microservices.userservice.web.model.request.UserSignUpRequest;
import com.microservices.userservice.web.model.response.GetUserResponse;
import com.microservices.userservice.web.model.response.UserCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersManagementController {

    private final Environment environment;
    private final UserService userService;
    private final UserServiceResponseFactory responseFactory;

    @GetMapping("/status/check")
    public String status() {
        return "Users microservice is running on port: " + environment.getProperty("local.server.port");
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserCreationResponse> createNewUser(@Valid @RequestBody UserSignUpRequest newUser) {

        UserEntity userEntity = userService.createUser(newUser);

        UserCreationResponse response = responseFactory.createUserCreationResponse(userEntity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") String userId) {

        UserEntity userEntity = userService.getUserByUserId(userId);

        GetUserResponse response = responseFactory.createGetUserResponse(userEntity);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
