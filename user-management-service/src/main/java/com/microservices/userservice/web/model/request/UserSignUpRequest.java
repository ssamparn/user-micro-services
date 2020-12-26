package com.microservices.userservice.web.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserSignUpRequest {

    @NotNull(message = "FirstName cannot be null")
    @Size(min = 2, message = "Firstname must not be less than two characters")
    private String firstName;

    @NotNull(message = "LastName cannot be null")
    @Size(min = 2, message = "Lastname must not be less than two characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "LastName cannot be null")
    @Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;

}
