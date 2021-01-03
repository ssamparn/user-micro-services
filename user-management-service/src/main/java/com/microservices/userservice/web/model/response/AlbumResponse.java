package com.microservices.userservice.web.model.response;

import lombok.Data;

@Data
public class AlbumResponse {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
