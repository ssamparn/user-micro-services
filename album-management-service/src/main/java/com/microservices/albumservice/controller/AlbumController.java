package com.microservices.albumservice.controller;

import com.microservices.albumservice.entity.AlbumEntity;
import com.microservices.albumservice.model.AlbumResponse;
import com.microservices.albumservice.service.AlbumResponseFactory;
import com.microservices.albumservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumResponseFactory albumResponseFactory;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlbumResponse>> userAlbums(@PathVariable String userId) {

        List<AlbumEntity> albumEntities = albumService.getAlbums(userId);

        List<AlbumResponse> response = new ArrayList<>();

        if (albumEntities == null || albumEntities.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        List<AlbumResponse> userAlbumResponse = albumResponseFactory.createUserAlbumResponse(albumEntities);

        return new ResponseEntity<>(userAlbumResponse, HttpStatus.OK);
    }
}
