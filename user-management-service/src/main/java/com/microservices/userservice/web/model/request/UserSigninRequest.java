package com.microservices.userservice.web.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSigninRequest {

    private String email;
    private String password;
}
