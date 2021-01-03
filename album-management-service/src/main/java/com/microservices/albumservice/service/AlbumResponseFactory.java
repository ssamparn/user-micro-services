package com.microservices.albumservice.service;

import com.microservices.albumservice.entity.AlbumEntity;
import com.microservices.albumservice.model.AlbumResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumResponseFactory {

    public List<AlbumResponse> createUserAlbumResponse(List<AlbumEntity> albumEntities) {
        return albumEntities.stream()
                .map(albumEntity -> {
                    AlbumResponse response = new AlbumResponse();
                    response.setAlbumId(albumEntity.getAlbumId());
                    response.setUserId(albumEntity.getUserId());
                    response.setName(albumEntity.getName());
                    response.setDescription(albumEntity.getDescription());
                    return response;
                }).collect(Collectors.toList());
    }
}
