package com.microservices.userservice.web.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class GetUserResponse implements Serializable {

    private static final long serialversionUID = -869364396060797L;

    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private List<AlbumResponse> albums;

}
