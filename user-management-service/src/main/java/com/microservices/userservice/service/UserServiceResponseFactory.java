package com.microservices.userservice.service;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.web.model.response.AlbumResponse;
import com.microservices.userservice.web.model.response.GetUserResponse;
import com.microservices.userservice.web.model.response.UserCreationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceResponseFactory {

    private final RestTemplate restTemplate;

    public UserCreationResponse createUserCreationResponse(UserEntity userEntity) {

        return UserCreationResponse.builder()
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getFirstName())
                .email(userEntity.getEmail())
                .build();
    }

    public GetUserResponse createGetUserResponse(UserEntity userEntity) {

        return GetUserResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .albums(invokeGetAlbumsService(userEntity.getUserId()))
                .build();
    }

    private List<AlbumResponse> invokeGetAlbumsService(String userId) {

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("ALBUM-MANAGEMENT-MICRO-SERVICE")
                .path("/users/{userId}/albums")
                .buildAndExpand(userId)
                .toUriString();

        ResponseEntity<List<AlbumResponse>> responseEntity = restTemplate
                .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponse>>() {});

        List<AlbumResponse> responseBody = responseEntity.getBody();

        if (responseBody != null) {
            return responseBody;
        }

        return null;
    }
}
