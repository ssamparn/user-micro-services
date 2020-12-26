package com.microservices.userservice.web.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserCreationResponse implements Serializable {

    private static final long serialversionUID = -953297098295050686L;

    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
